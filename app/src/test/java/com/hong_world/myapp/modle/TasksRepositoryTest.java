package com.hong_world.myapp.modle;

import com.hong_world.myapp.bean.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Date: 2017/11/6.15:02
 * Author: hong_world
 * Description:
 * Version:
 */
public class TasksRepositoryTest {
    private TasksRepository mTasksRepository;

    @Mock
    private TasksDataSource mTasksRemoteDataSource;

    @Mock
    private TasksDataSource mTasksLocalDataSource;

    @Mock
    private TasksDataSource.GetTaskCallback mGetTaskCallback;

    @Mock
    private TasksDataSource.LoadTasksCallback mLoadTasksCallback;

    @Captor
    private ArgumentCaptor<TasksDataSource.GetTaskCallback> mTaskCallbackCaptor;

    @Before
    public void setupTasksRepository() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        mTasksRepository = TasksRepository.getInstance(
                mTasksRemoteDataSource, mTasksLocalDataSource);
    }

    @After
    public void destroyRepositoryInstance() {
        TasksRepository.destroyInstance();
    }

    @Test
    public void loginBean() {
        Task newTask = new Task("123", "Some Task Description");
        mTasksRepository.getTask(newTask,mGetTaskCallback);
        
    }
}