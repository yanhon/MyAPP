package com.hong_world.homemodle.modle;

import android.content.Context;
import android.support.annotation.NonNull;

import com.hong_world.common.bean.Task;
import com.hong_world.library.net.FragmentLifeCycleEvent;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Date: 2017/11/3.13:52
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
    public Observable getTask(String name, String pwd) {
        return null;
    }

    @Override
    public void getTask(@NonNull Task task, @NonNull GetTaskCallback callback) {
    }

    @Override
    public void getTask(@NonNull Task task, Context c, PublishSubject<FragmentLifeCycleEvent> lifecycleSubject, @NonNull GetTaskCallback callback) {

    }

    @Override
    public void saveTask(@NonNull Task task) {
    }

    @Override
    public void deleteAllTasks() {
    }

    @Override
    public void deleteAllTasks2() {

    }

}
