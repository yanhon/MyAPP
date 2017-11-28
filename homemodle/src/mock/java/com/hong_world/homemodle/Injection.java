package com.hong_world.homemodle;

import com.hong_world.homemodle.data.FakeTasksRemoteDataSource;
import com.hong_world.homemodle.modle.TasksRepository;
import com.hong_world.homemodle.modle.local.TasksLocalDataSource;
import com.hong_world.homemodle.task.LoginTask;

/**
 * Date: 2017/11/3.11:02
 * Author: hong_world
 * Description: 测试用 模拟假数据 http://www.jianshu.com/p/cf446be43ae8
 * Version:
 */

public class Injection {
    public static TasksRepository provideTasksRepository() {
        return TasksRepository.getInstance(FakeTasksRemoteDataSource.getInstance(),
                TasksLocalDataSource.getInstance());
    }

    public static LoginTask provideLoginTask() {
        return new LoginTask(Injection.provideTasksRepository());
    }
}
