package com.hong_world.common.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hong_world.common.utils.EspressoIdlingResource;
import com.hong_world.library.base.BaseAppCompatActivity;
import com.hong_world.library.base.BasePresenter;
import com.hong_world.library.base.BaseView;

/**
 * Date: 2017/11/22.13:55
 * Author: hong_world
 * Description:
 * Version:
 */

public abstract class BaseActivity<P extends BasePresenter, B extends ViewDataBinding> extends BaseAppCompatActivity implements BaseView<P> {
    protected P mPresenter;
    protected B mBinding;

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        if (isBindEventBusHere()) {
//            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
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
    public void onError() {

    }

    @Override
    public void onLoading() {

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
            mPresenter.detachView(this);
        }
    }
}
