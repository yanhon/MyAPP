package com.hong_world.bmodle.modle.local;

import com.hong_world.bmodle.modle.TasksDataSource;

import io.reactivex.Observable;

/**
 * Date: 2018/8/13. 10:43
 * Author: hong_world
 * Description:
 * Version:
 */
public class TasksLocalDataSource implements TasksDataSource {
    private static TasksLocalDataSource INSTANCE;

    private TasksLocalDataSource() {
    }

    public static TasksLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TasksLocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Observable getFeedArticleList(int num) {
        return null;
    }
}
