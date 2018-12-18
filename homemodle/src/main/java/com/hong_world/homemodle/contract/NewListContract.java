package com.hong_world.homemodle.contract;

import com.hong_world.common.base.BaseNormalPresenter;
import com.hong_world.homemodle.modle.bean.MultipleItem;
import com.hong_world.library.base.BasePresenter;
import com.hong_world.library.base.BaseView;

/**
 * Date: 2018/7/31. 10:09
 * Author: hong_world
 * Description:
 * Version:
 */
public interface NewListContract {
    interface View<P extends BasePresenter> extends BaseView<P> {

        void onItemClick(MultipleItem data);
    }

    abstract class Presenter extends BaseNormalPresenter<NewListContract.View<Presenter>> {

    }
}
