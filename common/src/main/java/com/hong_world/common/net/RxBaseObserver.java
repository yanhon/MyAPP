package com.hong_world.common.net;

import com.hong_world.common.utils.EspressoIdlingResource;
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
    protected boolean showLoadingView = true;
    protected boolean isCancle;
    protected boolean showDataErrorView;//是否展示API数据类型错误的错误页
    protected boolean showOtherErrorView;//是否展示其它类型的错误页

    public RxBaseObserver showLoadingView(boolean showLoadingView) {
        this.showLoadingView = showLoadingView;
        return this;
    }

    public RxBaseObserver isCancle(boolean isCancle) {
        this.isCancle = isCancle;
        return this;
    }

    public RxBaseObserver showDataErrorView(boolean showDataErrorView) {
        this.showDataErrorView = showDataErrorView;
        return this;
    }

    public RxBaseObserver showOtherErrorView(boolean showOtherErrorView) {
        this.showOtherErrorView = showOtherErrorView;
        return this;
    }

    public RxBaseObserver(BasePresenter mPresenter) {
        this.view = mPresenter.getView();
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
        // The network request might be handled in a different thread so make sure Espresso knows
        // that the app is busy until the response is handled.
        EspressoIdlingResource.increment();// App is busy until further notice
        if (showLoadingView) {
            view.onLoading(this, isCancle);
        }
    }

    @Override
    public void onNext(T t) {
        // This callback may be called twice, once for the cache and once for loading
        // the data from the server API, so we check before decrementing, otherwise
        // it throws "Counter has been corrupted!" exception.
        if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
            EspressoIdlingResource.decrement(); // Set app as idle.
        }
        view.onSuccess();
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
            EspressoIdlingResource.decrement(); // Set app as idle.
        }
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
        if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
            EspressoIdlingResource.decrement(); // Set app as idle.
        }
    }

    protected abstract void onSuccess(T data);

    protected abstract void onFail(String errorCode, String errorMsg);

}
