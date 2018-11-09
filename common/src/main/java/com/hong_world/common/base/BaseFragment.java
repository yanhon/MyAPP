package com.hong_world.common.base;

import android.arch.lifecycle.LifecycleObserver;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hong_world.common.GlobalContants;
import com.hong_world.common.R;
import com.hong_world.common.databinding.BaseLayoutBinding;
import com.hong_world.common.utils.ToastUtils;
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
import com.kingja.loadsir.core.Transport;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.disposables.Disposable;

/**
 * Date: 2017/10/31.17:56
 * Author: hong_world
 * Description:
 * Version:
 */

public abstract class BaseFragment<P extends BasePresenter, V extends ViewDataBinding> extends BaseAppFragment implements BaseView<P> {
    protected P mPresenter;
    protected V mBinding;
    protected BaseLayoutBinding baseLayoutBinding;
    protected LoadService mBaseLoadService;
    protected SmartRefreshLayout smartRefreshLayout;
    private Disposable singleDisposable;
    private View needSetStatusView;

    protected boolean needTopBar() {
        return true;
    }

    protected boolean enableRefresh() {
        return true;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isBindEventBusHere()) {
            EventBus.getDefault().register(this);
        }
        ARouter.getInstance().inject(this);
        setPresenter(createPresenter());
        if (mPresenter != null) getLifecycle().addObserver((LifecycleObserver) mPresenter);
    }

    @Override
    protected View onCreateMyView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView;
        if (needTopBar()) {
            baseLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.base_layout, container, false);
            if (getLayoutId() != 0)
                mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), null, false);
            smartRefreshLayout = baseLayoutBinding.idMainFl;
            if (getLayoutId() != 0)
                smartRefreshLayout.setRefreshContent(mBinding.getRoot(), FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            setNeedSetStatusView(baseLayoutBinding.idMainFl);
            initStatusView();
            rootView = baseLayoutBinding.getRoot();
        } else {
            mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
            smartRefreshLayout = setCustomSmartRefreshLayout();
            if (smartRefreshLayout == null) {
                smartRefreshLayout = new SmartRefreshLayout(inflater.getContext());
                smartRefreshLayout.setRefreshContent(mBinding.getRoot(), FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            }
            setNeedSetStatusView(smartRefreshLayout);
            initStatusView();
            rootView = mBaseLoadService.getLoadLayout();
        }
        initSmartRefreshLayout();
        setOnMultiPurposeListener();
        return rootView;
    }

    public void initSmartRefreshLayout() {
        if (smartRefreshLayout != null) {
            smartRefreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
            smartRefreshLayout.setPrimaryColorsId(R.color.colorPrimary);
            smartRefreshLayout.setEnableRefresh(enableRefresh());
        }
    }

    protected SmartRefreshLayout setCustomSmartRefreshLayout() {
        return null;
    }

    protected SmartRefreshLayout getSmartRefreshLayout() {
        return smartRefreshLayout;
    }

    public void setSmartRefreshEnableRefresh(boolean c) {
        if (getSmartRefreshLayout() != null)
            getSmartRefreshLayout().setEnableRefresh(c);
    }

    public void smartRefreshFinishRefresh() {
        if (getSmartRefreshLayout() != null)
            getSmartRefreshLayout().finishRefresh();
    }

    public void smartRefreshFinishLoadMore() {
        if (getSmartRefreshLayout() != null)
            getSmartRefreshLayout().finishLoadMore();
    }

    public View getNeedSetStatusView() {
        return needSetStatusView;
    }

    public void setNeedSetStatusView(View needSetStatusView) {
        this.needSetStatusView = needSetStatusView;
    }

    public void onRefresh() {

    }

    public void setOnMultiPurposeListener() {
        smartRefreshLayout.setOnMultiPurposeListener(new OnMultiPurposeListener() {
            @Override
            public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                BaseFragment.this.onRefresh();
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {

            }

            @Override
            public void onHeaderReleased(RefreshHeader header, int headerHeight, int maxDragHeight) {

            }

            @Override
            public void onHeaderStartAnimator(RefreshHeader header, int headerHeight, int maxDragHeight) {

            }

            @Override
            public void onHeaderFinish(RefreshHeader header, boolean success) {

            }

            @Override
            public void onFooterMoving(RefreshFooter footer, boolean isDragging, float percent, int offset, int footerHeight, int maxDragHeight) {

            }

            @Override
            public void onFooterReleased(RefreshFooter footer, int footerHeight, int maxDragHeight) {

            }

            @Override
            public void onFooterStartAnimator(RefreshFooter footer, int footerHeight, int maxDragHeight) {

            }

            @Override
            public void onFooterFinish(RefreshFooter footer, boolean success) {

            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @LayoutRes
    protected abstract int getLayoutId();

    @Override
    protected void initDataSource() {

    }

    public void initStatusView() {
        if (getNeedSetStatusView() != null)
            mBaseLoadService = LoadSir.getDefault().register(getNeedSetStatusView(), new Callback.OnReloadListener() {
                @Override
                public void onReload(View v) {
//                mBaseLoadService.showSuccess();
                    onLoading();
                    onRefresh();
                }
            });
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
        super.onDestroyView();
//        if (mPresenter != null) {
//            mPresenter.detachView();
//            mPresenter.removeAllDisposable();
        mPresenter = null;
        com.orhanobut.logger.Logger.i("BaseFragment onDestroyView");
//        }
        if (isBindEventBusHere()) {
            EventBus.getDefault().unregister(this);
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
        smartRefreshFinishRefresh();
        smartRefreshFinishLoadMore();
    }

    boolean isCancleLoading = false;

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
                onSuccess();
                break;
            case GlobalContants.DATAEMPTY:
                onEmpty();
                break;
            case GlobalContants.NONETWORK:
                onError(msg);
                break;
            case GlobalContants.TOKENERROR:
                onError(msg);
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
        smartRefreshFinishRefresh();
        smartRefreshFinishLoadMore();
        isCancleLoading = false;
        if (StringUtil.isNotEmpty(msg))
            ToastUtils.showShort(msg);
    }

    @Override
    public void onEmpty() {
        hideSoftInput();
        if (mBaseLoadService != null) {
            mBaseLoadService.showCallback(EmptyCallback.class);
        }
    }

    @Override
    public void onError(String msg) {
        hideSoftInput();
        if (mBaseLoadService != null) {
            mBaseLoadService.setCallBack(ErrorCallback.class, new Transport() {
                @Override
                public void order(Context context, View view) {
                    if (StringUtil.isEmpty(msg)) return;
                    TextView mTvEmpty = (TextView) view.findViewById(R.id.id_tv_error);
                    mTvEmpty.setText(msg);
                }
            }).showCallback(ErrorCallback.class);
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        if (isCancleLoading) {
            onSuccess();
            isCancleLoading = false;
            mPresenter.removeAllDisposable();
            return true;
        }
        return super.onBackPressedSupport();
    }

    @Override
    public void onLoading() {
        hideSoftInput();
        isCancleLoading = false;
        if (mBaseLoadService != null) {
            mBaseLoadService.setCallBack(LoadingCallback.class, new Transport() {
                @Override
                public void order(Context context, View view) {

                }
            }).showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void onLoading(Disposable disposable) {
        singleDisposable = disposable;
        onLoading();
        isCancleLoading = true;
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

}
