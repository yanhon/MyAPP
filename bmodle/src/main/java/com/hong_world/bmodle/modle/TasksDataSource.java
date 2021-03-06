package com.hong_world.bmodle.modle;

import com.hong_world.common.bean.FeedArticleListData;

import io.reactivex.Observable;

/**
 * Date: 2018/8/13. 10:45
 * Author: hong_world
 * Description:
 * Version:
 */
public interface TasksDataSource {

    interface GetTaskCallback<C> {

        void onTaskLoaded(C bean);

        void onDataNotAvailable(String type, String msg);
    }
    Observable<FeedArticleListData> getFeedArticleList(int num);
}
