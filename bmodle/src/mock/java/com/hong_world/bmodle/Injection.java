package com.hong_world.bmodle;

import com.hong_world.bmodle.modle.TasksRepository;
import com.hong_world.bmodle.modle.local.TasksLocalDataSource;
import com.hong_world.bmodle.modle.remote.TasksRemoteDataSource;
/**
 * Date: 2017/11/3.11:02
 * Author: hong_world
 * Description: 测试用 模拟假数据 http://www.jianshu.com/p/cf446be43ae8
 * Version:
 */

public class Injection {
    public static String s;
    public static TasksRepository provideTasksRepository() {
        return TasksRepository.getInstance(TasksRemoteDataSource.getInstance(),
                TasksLocalDataSource.getInstance());
    }
}
