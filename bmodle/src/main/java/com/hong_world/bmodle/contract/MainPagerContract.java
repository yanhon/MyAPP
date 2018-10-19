package com.hong_world.bmodle.contract;

import com.hong_world.bmodle.bean.FeedArticleData;
import com.hong_world.bmodle.bean.FeedArticleListData;
import com.hong_world.common.base.BaseNormalPresenter;
import com.hong_world.library.base.BasePresenter;
import com.hong_world.library.base.BaseView;

/**
 * Date: 2018/7/31. 10:09
 * Author: hong_world
 * Description:
 * Version:
 */
public interface MainPagerContract {
    interface View<P extends BasePresenter> extends BaseView<P> {
        void getPageListSuccess(FeedArticleListData data, boolean isRefresh);

        void onItemClick(FeedArticleData data);
    }

    abstract class Presenter extends BaseNormalPresenter<MainPagerContract.View> {

        public abstract void getPageList(int mCurrentPage, boolean isRefresh);
    }
}
