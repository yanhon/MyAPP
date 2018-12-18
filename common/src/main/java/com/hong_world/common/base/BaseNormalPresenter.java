package com.hong_world.common.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import com.hong_world.library.base.BasePresenter;
import com.hong_world.library.base.BaseView;
import com.orhanobut.logger.Logger;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Date: 2017/10/31.17:02
 * Author: hong_world
 * Description:
 * Version:
 */

public abstract class BaseNormalPresenter<V extends BaseView> implements BasePresenter, LifecycleObserver {
    protected V mView;
    //用来存放Disposable的容器
    private CompositeDisposable mCompositeDisposable;

    public BaseNormalPresenter() {
    }

    public BaseNormalPresenter(V mView) {
        this.mView = mView;
    }

    public void setmView(V mView) {
        this.mView = mView;
//        mView.setPresenter(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    @Override
    public void detachView() {
        Logger.i("BaseNormalPresenter detachView");
        if (mView != null) {
            mView = null;
        }
    }


    public V getView() {
        if (mView == null)
            throw new RuntimeException("You have no binding this view");
        return mView;
    }

    @Override
    public BaseView getBaseView() {
        return mView;
    }

    @Override
    public void initData() {
//        mView.onLoading();
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
        if (disposable != null)
            mCompositeDisposable.add(disposable);
        return disposable;
    }

    //移除指定的请求
    @Override
    public void removeDisposable(Disposable disposable) {
        if (mCompositeDisposable != null && disposable != null)
            mCompositeDisposable.remove(disposable);
    }

    //取消所有的请求Tag
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    @Override
    public void removeAllDisposable() {
        Logger.i("BaseNormalPresenter removeAllDisposable");
        if (mCompositeDisposable != null)
            mCompositeDisposable.clear();
//        mView.onSuccess();
    }
}
