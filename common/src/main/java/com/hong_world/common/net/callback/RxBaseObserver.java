package com.hong_world.common.net.callback;

import com.hong_world.common.net.BaseResponse;

import io.reactivex.observers.DisposableObserver;

/**
 * Date: 2018/5/17. 17:06
 * Author: hong_world
 * Description:
 * Version:
 */

public abstract class RxBaseObserver<T> extends DisposableObserver<BaseResponse<T>> {
    @Override
    public void onNext(BaseResponse<T> tBaseResponse) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
