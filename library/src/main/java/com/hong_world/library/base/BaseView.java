package com.hong_world.library.base;

import android.support.annotation.Nullable;

import io.reactivex.disposables.Disposable;

/**
 * Date: 2017/10/31.17:02
 * Author: hong_world
 * Description:
 * Version:
 */

public interface BaseView<P extends BasePresenter> {
    void setPresenter(P presenter);

    P createPresenter();

    void onBack();

    void onLeftAction();

    void onRightAction();

    void onRightImage();

    String title();

    void onEmpty();

    void onError(String msg);

    void onSuccess();

    void onDataNotAvailable(String type, String msg);

//    /**
//     * //不能主动取消网络请求
//     */
//    void onLoading();

    /**
     * //能点击返回按钮取消请求（默认取消当前页面所有的网络请求），适用于数据提交，重写RxBaseObserver中的onStart方法
     *
     * @param disposable
     */
    void onLoading(@Nullable Disposable disposable, boolean isCancle);

    void onTimeOut();

    boolean isActive();

}
