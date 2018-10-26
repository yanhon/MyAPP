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
    protected boolean showLoading;
    protected boolean isCancle;

    public RxBaseObserver(BasePresenter mPresenter) {
        this(mPresenter, false, false);
    }

    public RxBaseObserver(BasePresenter mPresenter, boolean showLoading) {
        this(mPresenter, true, false);
    }

    public RxBaseObserver(BasePresenter mPresenter, boolean showLoading, boolean isCancle) {
        this.view = mPresenter.getView();
        this.isCancle = isCancle;
        this.showLoading = showLoading;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (showLoading) {
            if (isCancle)
                view.onLoading(this);
            else
                view.onLoading();
        }
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
