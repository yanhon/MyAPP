package com.hong_world.myapp.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Date: 2017/10/31.17:56
 * Author: hong_world
 * Description:
 * Version:
 */

public abstract class BaseFragment<P extends BasePresenter> extends BaseAppCompatFragment implements BaseView<P> {
    protected P mPresenter;
    private ViewDataBinding mBinding;

    @Override
    protected View onCreateMyView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return mBinding.getRoot();
    }

    protected ViewDataBinding getBindView() {
        return mBinding;
    }

    @Override
    public void setPresenter(P presenter) {
        this.mPresenter = presenter;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        getPresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView(this);
        }
    }
}
