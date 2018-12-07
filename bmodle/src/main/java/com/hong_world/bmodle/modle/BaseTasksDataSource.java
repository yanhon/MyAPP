package com.hong_world.bmodle.modle;

import com.hong_world.common.bean.FeedArticleListData;

import io.reactivex.Observable;

/**
 * Date: 2018/8/13. 10:44
 * Author: hong_world
 * Description:
 * Version:
 */
public class BaseTasksDataSource implements TasksDataSource {
    protected static BaseTasksDataSource INSTANCE;

    protected BaseTasksDataSource() {
    }

    public static BaseTasksDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BaseTasksDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Observable<FeedArticleListData> getFeedArticleList(int num) {
        return null;
    }
}
