package com.hong_world.common.net;

import com.hong_world.library.base.BasePresenter;
import com.hong_world.library.base.BaseView;
import com.hong_world.library.net.exception.DefaultErrorBundle;

import io.reactivex.observers.DisposableObserver;

import static com.hong_world.common.GlobalContants.DATAERROR;

/**
 * Date: 2018/5/24. 10:28
 * Author: hong_world
 * Description:
 * Version:
 */

public abstract class RxBaseObserver<T> extends DisposableObserver<T> {
    protected BaseView view;
    protected boolean showLoadingView;
    protected boolean isCancle;
    protected boolean showDataErrorView;//是否展示API数据类型错误的错误页
    protected boolean showOtherErrorView;//是否展示其它类型的错误页

    public RxBaseObserver(BasePresenter mPresenter) {
        this(mPresenter, true, false, true);
    }

    public RxBaseObserver(BasePresenter mPresenter, boolean showLoadingView) {
        this(mPresenter, showLoadingView, false, true);
    }

    public RxBaseObserver(BasePresenter mPresenter, boolean isCancle, boolean showDataErrorView) {
        this(mPresenter, true, isCancle, showDataErrorView);
    }

    /**
     * @param mPresenter
     * @param showLoadingView   请求数据加载页
     * @param isCancle          能否取消请求
     * @param showDataErrorView 数据类型错误页
     */
    public RxBaseObserver(BasePresenter mPresenter, boolean showLoadingView, boolean isCancle, boolean showDataErrorView) {
        this(mPresenter, showLoadingView, isCancle, showDataErrorView, true);
    }

    public RxBaseObserver(BasePresenter mPresenter, boolean showLoadingView, boolean isCancle, boolean showDataErrorView, boolean showOtherErrorView) {
        this.view = mPresenter.getView();
        this.isCancle = isCancle;
        this.showLoadingView = showLoadingView;
        this.showDataErrorView = showDataErrorView;
        this.showOtherErrorView = showOtherErrorView;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (showLoadingView) {
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
        if (msg.getCode().equals(DATAERROR)) {
            if (!showDataErrorView) {
                view.onDataNotAvailable("", msg.getMsg());
                return;
            }
        } else {
            if (!showOtherErrorView) {
                view.onDataNotAvailable("", msg.getMsg());
                return;
            }
        }
        view.onDataNotAvailable(msg.getCode(), msg.getMsg());
    }

    @Override
    public void onComplete() {

    }

    protected abstract void onSuccess(T data);

    protected abstract void onFail(String errorCode, String errorMsg);

}
