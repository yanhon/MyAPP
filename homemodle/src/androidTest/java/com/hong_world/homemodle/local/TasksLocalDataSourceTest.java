package com.hong_world.homemodle.local;

import android.support.test.runner.AndroidJUnit4;

import com.hong_world.common.bean.Task;
import com.hong_world.homemodle.modle.TasksDataSource;
import com.hong_world.homemodle.modle.local.TasksLocalDataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Date: 2017/11/7.14:42
 * Author: hong_world
 * Description: 数据库有关DB测试
 * Version:
 */
@RunWith(AndroidJUnit4.class)
public class TasksLocalDataSourceTest {
    private TasksLocalDataSource mLocalDataSource;

    @Before
    public void setup() {
        mLocalDataSource = TasksLocalDataSource.getInstance();
    }

    @Test
    public void saveTask() throws Exception {
        Task task = new Task("13", "123");
        mLocalDataSource.saveTask(task);
    }

    @Test
    public void getTask() throws Exception {
        Task task = new Task("1325", "123");
        mLocalDataSource.getTask(task, new TasksDataSource.GetTaskCallback<Task>() {
            @Override
            public void onTaskLoaded(Task bean) {

            }

            @Override
            public void onDataNotAvailable(String type, String msg) {

            }
        });
    }

    @After
    public void cleanUp() {
        mLocalDataSource.deleteAllTasks();
    }
}