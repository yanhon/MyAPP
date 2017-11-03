package com.hong_world.myapp.base;

/**
 * Date: 2017/10/31.17:02
 * Author: hong_world
 * Description:
 * Version:
 */

public interface BaseView<P extends BasePresenter> {
    void setPresenter(P presenter);

    P getPresenter();

    void onBack();
}
