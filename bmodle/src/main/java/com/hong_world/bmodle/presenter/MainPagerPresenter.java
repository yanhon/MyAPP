package com.hong_world.bmodle.presenter;

import com.hong_world.bmodle.Injection;
import com.hong_world.bmodle.bean.FeedArticleData;
import com.hong_world.bmodle.bean.FeedArticleListData;
import com.hong_world.bmodle.contract.MainPagerContract;
import com.hong_world.bmodle.modle.TasksDataSource;
import com.hong_world.common.net.RxBaseObserver;

/**
 * Date: 2018/7/31. 10:10
 * Author: hong_world
 * Description:
 * Version:
 */
public class MainPagerPresenter extends MainPagerContract.Presenter {
    TasksDataSource mTasksRepository;

    public MainPagerPresenter(MainPagerContract.View view) {
        setmView(view);
        mTasksRepository = Injection.provideTasksRepository();
    }

    @Override
    public void getPageList(int mCurrentPage, final boolean isRefresh) {
        addDisposable(mTasksRepository.getFeedArticleList(mCurrentPage).subscribeWith(new RxBaseObserver<FeedArticleListData>(this) {
            @Override
            protected void onSuccess(FeedArticleListData data) {
                getView().getPageListSuccess(data, isRefresh);
            }

            @Override
            protected void onFail(String errorCode, String errorMsg) {

            }
        }));
    }

    public void onItemClick(FeedArticleData data) {
        mView.onItemClick(data);
    }
}
