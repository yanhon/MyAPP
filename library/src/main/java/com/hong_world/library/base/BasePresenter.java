package com.hong_world.library.base;

/**
 * Date: 2017/10/31.17:02
 * Author: hong_world
 * Description:
 * Version:
 */

public interface BasePresenter<V> {
    void detachView(V view);

    void initData();

    void onBack();

    void onLeftAction();

    void onRightAction();

    void onRightImage();

    String title();
}
