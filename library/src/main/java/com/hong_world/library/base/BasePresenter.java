package com.hong_world.library.base;

import io.reactivex.disposables.Disposable;

/**
 * Date: 2017/10/31.17:02
 * Author: hong_world
 * Description:
 * Version:
 */

public interface BasePresenter<V extends BaseView> {
    void detachView(V view);

    V getView();

    void initData();

    void onBack();

    void onLeftAction();

    void onRightAction();

    void onRightImage();

    String title();

    //添加指定的请求
    void addDisposable(Disposable disposable);

    //移除指定的请求
    void removeDisposable(Disposable disposable);

    //取消所有请求
    void removeAllDisposable();
}
