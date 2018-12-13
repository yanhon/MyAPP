package com.hong_world.common.base;

import android.arch.lifecycle.LifecycleObserver;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hong_world.common.utils.EspressoIdlingResource;
import com.hong_world.library.base.BasePresenter;
import com.hong_world.library.base.BaseView;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.disposables.Disposable;

/**
 * Date: 2017/11/22.13:55
 * Author: hong_world
 * Description:
 * Version:
 */

public abstract class BaseActivity<P extends BasePresenter> extends BaseAppActivity implements BaseView<P> {

    protected P mPresenter;
    private ViewDataBinding mBinding;

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        super.onCreate(savedInstanceState);
        if (isBindEventBusHere()) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setPresenter(createPresenter());
        if (mPresenter != null) getLifecycle().addObserver((LifecycleObserver) mPresenter);
        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
    }

    protected ViewDataBinding getBindView() {
        return mBinding;
    }

    @Override
    protected void initDataSource() {

    }

    @Override
    public void setPresenter(P presenter) {
        this.mPresenter = presenter;
    }

    @LayoutRes
    protected abstract int getLayoutId();

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

    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onError(String msg) {

    }

//    @Override
//    public void onLoading() {
//
//    }

    @Override
    public void onLoading(@Nullable Disposable disposable, boolean isCancle) {

    }

    @Override
    public void onTimeOut() {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void onDataNotAvailable(String type, String msg) {

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
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (isBindEventBusHere()) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}
