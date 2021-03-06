package com.hong_world.homemodle.contract;

import com.hong_world.common.base.BaseNormalPresenter;
import com.hong_world.library.base.BaseView;

/**
 * Date: 2018/7/31. 10:09
 * Author: hong_world
 * Description:
 * Version:
 */
public interface HomeContract {
    interface View extends BaseView {

    }

    abstract class Presenter extends BaseNormalPresenter<HomeContract.View> {

    }
}
