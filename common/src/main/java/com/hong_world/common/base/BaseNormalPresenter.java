package com.hong_world.common.base;

import com.hong_world.library.base.BasePresenter;
import com.hong_world.library.base.BaseView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Date: 2017/10/31.17:02
 * Author: hong_world
 * Description:
 * Version:
 */

public abstract class BaseNormalPresenter<V extends BaseView> implements BasePresenter<V> {
    protected V mView;
    //用来存放Disposable的容器
    private CompositeDisposable mCompositeDisposable;

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

    @Override
    public V getView() {
        if (mView == null)
            throw new RuntimeException("You have no binding this view");
        return mView;
    }

    @Override
    public void initData() {
        mView.onLoading();
    }

    @Override
    public void onBack() {
        mView.onBack();
    }

    @Override
    public void onLeftAction() {
        mView.onLeftAction();
    }

    @Override
    public void onRightAction() {
        mView.onRightAction();
    }

    @Override
    public void onRightImage() {
        mView.onRightImage();
    }

    @Override
    public String title() {
        return mView.title();
    }

    //添加指定的请求
    @Override
    public DisposableObserver addDisposable(DisposableObserver disposable) {
        if (mCompositeDisposable == null)
            mCompositeDisposable = new CompositeDisposable();
        mCompositeDisposable.add(disposable);
        return disposable;
    }

    //移除指定的请求
    @Override
    public void removeDisposable(Disposable disposable) {
        if (mCompositeDisposable != null)
            mCompositeDisposable.remove(disposable);
    }

    //取消所有的请求Tag
    @Override
    public void removeAllDisposable() {
        if (mCompositeDisposable != null)
            mCompositeDisposable.clear();
    }
}
