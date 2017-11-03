package com.hong_world.myapp;

import com.hong_world.myapp.modle.TasksLocalDataSource;
import com.hong_world.myapp.modle.TasksRemoteDataSource;
import com.hong_world.myapp.modle.TasksRepository;

/**
 * Date: 2017/11/3.11:02
 * Author: hong_world
 * Description: 正式版本
 * Version:
 */

public class Injection {

    public static TasksRepository provideTasksRepository() {
        return TasksRepository.getInstance(TasksRemoteDataSource.getInstance(),
                TasksLocalDataSource.getInstance());
    }

    public static LoginTask provideLoginTask() {
        return new LoginTask(Injection.provideTasksRepository());
    }
}
