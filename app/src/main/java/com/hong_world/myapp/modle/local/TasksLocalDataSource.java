package com.hong_world.myapp.modle.local;

import android.support.annotation.NonNull;

import com.hong_world.myapp.bean.Task;
import com.hong_world.myapp.modle.TasksDataSource;

/**
 * Date: 2017/11/3.13:52
 * Author: hong_world
 * Description: 请求本地数据
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
    public void getTasks(@NonNull LoadTasksCallback callback) {

    }

    @Override
    public void getTask(@NonNull Task task, @NonNull GetTaskCallback callback) {
        //db操作一顿
        task.setPwd("本地");
        callback.onTaskLoaded(task);
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
