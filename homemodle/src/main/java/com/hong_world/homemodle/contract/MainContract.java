package com.hong_world.homemodle.contract;

import com.hong_world.common.base.BaseNormalPresenter;
import com.hong_world.common.bean.Task;
import com.hong_world.library.base.BasePresenter;
import com.hong_world.library.base.BaseView;

/**
 * Created by hong_world on 2017/10/31.
 */
public interface MainContract {
    interface View<P extends BasePresenter> extends BaseView<P> {

        void onSuccess(Task task);

        boolean isActive();

    }

    abstract class Presenter extends BaseNormalPresenter<View> {
        public abstract void loginTask(String phone, String pwd);

        public abstract void loginTask(Task task);

    }
}