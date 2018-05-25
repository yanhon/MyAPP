package com.hong_world.common.net;

import com.hong_world.library.net.exception.DefaultErrorBundle;

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
        _onError(ErrorMessageFactory.create(new DefaultErrorBundle((Exception) e).getException()).getMsg());
    }

    @Override
    public void onComplete() {

    }

    public abstract void _onError(String errorMsg);

    public abstract void _onNext(T t);

}