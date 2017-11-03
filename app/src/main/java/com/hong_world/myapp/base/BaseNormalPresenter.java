package com.hong_world.myapp.base;

/**
 * Date: 2017/10/31.17:02
 * Author: hong_world
 * Description:
 * Version:
 */

public abstract class BaseNormalPresenter<V extends BaseView> implements BasePresenter<V> {
    protected V mView;

    public void setmView(V mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void detachView(V view) {
        if (view != null) {
            view = null;
        }
    }
}
