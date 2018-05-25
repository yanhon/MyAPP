package com.hong_world.homemodle.data;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.hong_world.common.bean.Task;
import com.hong_world.common.utils.EspressoIdlingResource;
import com.hong_world.homemodle.modle.TasksDataSource;
import com.hong_world.library.net.FragmentLifeCycleEvent;

import io.reactivex.subjects.PublishSubject;

/**
 * Date: 2017/11/3.11:03
 * Author: hong_world
 * Description: 模拟网络数据
 * Version:
 */

public class FakeTasksRemoteDataSource implements TasksDataSource {
    private static FakeTasksRemoteDataSource INSTANCE;

    // Prevent direct instantiation.
    private FakeTasksRemoteDataSource() {
    }

    public static FakeTasksRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FakeTasksRemoteDataSource();
        }
        return INSTANCE;
    }


    @Override
    public void getTask(@NonNull final Task task, @NonNull final GetTaskCallback callback) {
        //假数据操作一顿
        EspressoIdlingResource.increment();
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {//模拟网络请求延时操作
            @Override
            public void run() {
                if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                    EspressoIdlingResource.decrement(); // Set app as idle.
                }
                task.setPwd(task.getPwd() + "(假数据)");
                callback.onTaskLoaded(task);
            }
        }, 2000);

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


}
