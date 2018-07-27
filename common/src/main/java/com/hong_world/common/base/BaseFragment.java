package com.hong_world.common.base;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hong_world.common.GlobalContants;
import com.hong_world.common.R;
import com.hong_world.common.databinding.BaseLayoutBinding;
import com.hong_world.library.net.FragmentLifeCycleEvent;
import com.hong_world.library.base.BaseAppCompatFragment;
import com.hong_world.library.base.BasePresenter;
import com.hong_world.library.base.BaseView;
import com.hong_world.library.utils.StringUtil;
import com.hong_world.library.view.status.callback.EmptyCallback;
import com.hong_world.library.view.status.callback.ErrorCallback;
import com.hong_world.library.view.status.callback.LoadingCallback;
import com.hong_world.library.view.status.callback.TimeoutCallback;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import io.reactivex.subjects.PublishSubject;

/**
 * Date: 2017/10/31.17:56
 * Author: hong_world
 * Description:
 * Version:
 */

public abstract class BaseFragment<P extends BasePresenter, V extends ViewDataBinding> extends BaseAppCompatFragment implements BaseView<P> {
    public final PublishSubject<FragmentLifeCycleEvent> lifecycleSubject = PublishSubject.create();
    protected P mPresenter;
    protected V mBinding;
    protected BaseLayoutBinding baseLayoutBinding;
    protected LoadService mBaseLoadService;

    protected boolean needTopBar() {
        return true;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        lifecycleSubject.onNext(FragmentLifeCycleEvent.CREATE);
        super.onCreate(savedInstanceState);
        if (isBindEventBusHere()) {
//            EventBus.getDefault().register(this);
        }
        ARouter.getInstance().inject(this);
        createPresenter();
    }

    @Override
    protected View onCreateMyView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (needTopBar()) {
            baseLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.base_layout, container, false);
            mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), null, false);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT);
            baseLayoutBinding.idMainFl.addView(mBinding.getRoot(), params);
            initStatusView(baseLayoutBinding.idMainFl);
            return baseLayoutBinding.getRoot();
        } else {
            mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
            initStatusView(mBinding.getRoot());
            return mBinding.getRoot();
        }
    }

    @Override
    public void onPause() {
        lifecycleSubject.onNext(FragmentLifeCycleEvent.PAUSE);
        super.onPause();
    }

    @Override
    public void onStop() {
        lifecycleSubject.onNext(FragmentLifeCycleEvent.STOP);
        super.onStop();
    }

    @LayoutRes
    protected abstract int getLayoutId();

    @Override
    protected void initDataSource() {

    }

    public void initStatusView(View view) {
        mBaseLoadService = LoadSir.getDefault().register(view, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
//                mBaseLoadService.showSuccess();
                onLoading();
                mPresenter.initData();
            }
        });
//        .setCallBack(ErrorCallback.class, new Transport() {
//            @Override
//            public void order(Context context, View view) {
//                TextView mTvEmpty = (TextView) view.findViewById(R.id.id_tv_error);
//                mTvEmpty.setText("12345615121");
//            }
//        });
    }

    protected V getBindView() {
        return mBinding;
    }

    @Override
    public void setPresenter(P presenter) {
        this.mPresenter = presenter;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        if (baseLayoutBinding != null) {
            baseLayoutBinding.setPresenter(mPresenter);
        }
        onSuccess();
    }

    @Override
    public void onDestroyView() {
        lifecycleSubject.onNext(FragmentLifeCycleEvent.DESTROY);
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView(this);
            mPresenter.removeAllDisposable();
            mPresenter = null;
        }
        if (isBindEventBusHere()) {
//            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onBack() {

    }

    @Override
    public void onRightImage() {

    }

    @Override
    public void onRightAction() {

    }

    @Override
    public void onLeftAction() {

    }

    @Override
    public void onSuccess() {
        if (mBaseLoadService != null) {
            mBaseLoadService.showSuccess();
        }
    }

    /**
     * 请求错误信息展示
     *
     * @param type
     * @param msg
     */
    @Override
    public void onDataNotAvailable(String type, String msg) {
        switch (type) {
            case GlobalContants.TIMEOUT:
                onTimeOut();
                break;
            case GlobalContants.DATAEMPTY:
                onEmpty();
                break;
            case GlobalContants.NONETWORK:
                onError();
                break;
            case GlobalContants.TOKENERROR:
                onSuccess();
                break;
            case GlobalContants.GETDATAERROR:
                onSuccess();
                break;
            case GlobalContants.PUTDATAERROR:
                onSuccess();
                break;
            case GlobalContants.NOTFIND:
                onSuccess();
                break;
            default:
                onSuccess();
        }
        if (StringUtil.isNotEmpty(msg))
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmpty() {
        hideSoftInput();
        if (mBaseLoadService != null) {
            mBaseLoadService.showCallback(EmptyCallback.class);
        }
    }

    @Override
    public void onError() {
        hideSoftInput();
        if (mBaseLoadService != null) {
            mBaseLoadService.showCallback(ErrorCallback.class);
        }
    }

    @Override
    public void onLoading() {
        hideSoftInput();
        if (mBaseLoadService != null) {
            mBaseLoadService.showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void onTimeOut() {
        hideSoftInput();
        if (mBaseLoadService != null) {
            mBaseLoadService.showCallback(TimeoutCallback.class);
        }
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    /**
     * is bind eventBus
     *
     * @return
     */
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    public PublishSubject<FragmentLifeCycleEvent> getLifecycleSubject() {
        return lifecycleSubject;
    }

    @Override
    public Activity getActivityContext() {
        return getActivity();
    }
}
