package com.hong_world.library.base;

import android.app.Activity;

import com.hong_world.library.net.FragmentLifeCycleEvent;

import io.reactivex.subjects.PublishSubject;

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

    void onError();

    void onSuccess();

    void onDataNotAvailable(String type, String msg);

    void onLoading();

    void onTimeOut();

    boolean isActive();

    Activity getActivityContext();

    PublishSubject<FragmentLifeCycleEvent> getLifecycleSubject();

}
