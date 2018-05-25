package com.hong_world.common.net;

import com.hong_world.library.base.BasePresenter;
import com.hong_world.library.base.BaseView;
import com.hong_world.library.net.exception.DefaultErrorBundle;

import io.reactivex.observers.DisposableObserver;

/**
 * Date: 2018/5/24. 10:28
 * Author: hong_world
 * Description:
 * Version:
 */

public abstract class RxBaseObserver<T> extends DisposableObserver<T> {
    protected BaseView view;

    public RxBaseObserver(BasePresenter mPresenter) {
        this.view = mPresenter.getView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        view.onLoading();
    }

    @Override
    public void onNext(T t) {
        view.onSuccess();
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        ErrorMsgBean msg = ErrorMessageFactory.create(new DefaultErrorBundle((Exception) e).getException());
        onFail(msg.getCode(), msg.getMsg());
        view.onDataNotAvailable(msg.getCode(), msg.getMsg());
    }

    @Override
    public void onComplete() {

    }

    protected abstract void onSuccess(T data);

    protected abstract void onFail(String errorCode, String errorMsg);

}
