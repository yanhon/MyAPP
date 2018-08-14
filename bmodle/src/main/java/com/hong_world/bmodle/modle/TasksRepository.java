package com.hong_world.bmodle.modle;

import android.support.annotation.NonNull;

import io.reactivex.Observable;

/**
 * Date: 2018/8/13. 10:48
 * Author: hong_world
 * Description:
 * Version:
 */
public class TasksRepository implements TasksDataSource {
    private static TasksRepository INSTANCE = null;
    private final TasksDataSource mTasksRemoteDataSource;

    private final TasksDataSource mTasksLocalDataSource;

    /**
     * @param tasksRemoteDataSource
     * @param tasksLocalDataSource
     */
    private TasksRepository(@NonNull TasksDataSource tasksRemoteDataSource,
                            @NonNull TasksDataSource tasksLocalDataSource) {
        mTasksRemoteDataSource = tasksRemoteDataSource;
        mTasksLocalDataSource = tasksLocalDataSource;
    }

    public static TasksRepository getInstance(TasksDataSource tasksRemoteDataSource,
                                              TasksDataSource tasksLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TasksRepository(tasksRemoteDataSource, tasksLocalDataSource);
        }
        return INSTANCE;
    }

    @Override
    public Observable getFeedArticleList(int num) {
        return mTasksRemoteDataSource.getFeedArticleList(num);
    }
}
