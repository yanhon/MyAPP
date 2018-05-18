package com.hong_world.common.net;

import io.reactivex.observers.DisposableObserver;

/**
 * Date: 2018/5/18. 14:14
 * Author: hong_world
 * Description:
 * Version:
 */

public abstract class MySubscribe<T> extends DisposableObserver<T> {


    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        _onError(e.toString());
    }

    @Override
    public void onComplete() {

    }

    public abstract void _onError(String errorMsg);

    public abstract void _onNext(T t);

}