package com.hong_world.myapp;

import com.hong_world.myapp.base.BaseNormalPresenter;
import com.hong_world.myapp.base.BasePresenter;
import com.hong_world.myapp.base.BaseView;
import com.hong_world.myapp.bean.Task;

/**
 * Created by hong_world on 2017/10/31.
 */
public interface MainContract {
    interface View<P extends BasePresenter> extends BaseView<P> {
        void initDate();

        void onSuccess(Task task);

        void onError();
    }

    abstract class Presenter extends BaseNormalPresenter<View> {
        abstract void loginTask(String phone, String pwd);
    }
}