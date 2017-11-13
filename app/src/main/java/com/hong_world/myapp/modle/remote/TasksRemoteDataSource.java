package com.hong_world.myapp.modle.remote;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.hong_world.myapp.bean.Task;
import com.hong_world.myapp.modle.TasksDataSource;
import com.hong_world.myapp.utils.EspressoIdlingResource;

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
    public void getTask(@NonNull final Task task, @NonNull final GetTaskCallback callback) {
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
    public void completeTask(@NonNull Task task) {

    }

    @Override
    public void completeTask(@NonNull String taskId) {

    }

    @Override
    public void activateTask(@NonNull Task task) {

    }

    @Override
    public void activateTask(@NonNull String taskId) {

    }

    @Override
    public void clearCompletedTasks() {

    }

    @Override
    public void refreshTasks() {

    }

    @Override
    public void deleteAllTasks() {

    }

    @Override
    public void deleteTask(@NonNull String taskId) {

    }
}
