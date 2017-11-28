package com.hong_world.homemodle.modle.remote;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.hong_world.common.bean.Task;
import com.hong_world.common.utils.EspressoIdlingResource;
import com.hong_world.homemodle.modle.TasksDataSource;

/**
 * Date: 2017/11/3.13:52
 * Author: hong_world
 * Description: 请求网络数据
 * Version:
 */

public class TasksRemoteDataSource implements TasksDataSource {

    private static TasksRemoteDataSource INSTANCE;

    private TasksRemoteDataSource() {
    }

    public static TasksRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TasksRemoteDataSource();
        }
        return INSTANCE;
    }



    @Override
    public void getTask(@NonNull final Task task, @NonNull final TasksDataSource.GetTaskCallback callback) {
        //net操作一顿
        EspressoIdlingResource.increment();
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {//模拟网络请求延时操作
            @Override
            public void run() {
                if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                    EspressoIdlingResource.decrement(); // Set app as idle.
                }
                task.setPwd(task.getPwd() + "(网络数据)");
                Log.i("test", "网络数据");
                callback.onTaskLoaded(task);
            }
        }, 2000);

    }

    @Override
    public void saveTask(@NonNull Task task) {

    }



    @Override
    public void deleteAllTasks() {

    }


}
