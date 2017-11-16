package com.hong_world.myapp.modle;

import com.hong_world.myapp.bean.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

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
    private TasksDataSource.GetTaskCallback<Task> mGetTaskCallback;

    @Captor
    private ArgumentCaptor<TasksDataSource.GetTaskCallback<Task>> mTaskCallbackCaptor;

    @Before
    public void setupTasksRepository() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        mTasksRepository = TasksRepository.getInstance(
                mTasksRemoteDataSource, mTasksLocalDataSource);
    }

    @Test
    public void loginBean() {
        Task newTask = new Task("1352", "Some Task Description");
        mTasksRepository.getTask(newTask, mGetTaskCallback);
        verify(mTasksRemoteDataSource).getTask(any(Task.class), mTaskCallbackCaptor.capture());
        mTaskCallbackCaptor.getValue().onTaskLoaded(any(Task.class));
    }

    @Test
    public void loginBean135() {
        Task newTask = new Task("135", "Some Task Description");
        mTasksRepository.getTask(newTask, mGetTaskCallback);
//        verify(mTasksRemoteDataSource).getTask(any(Task.class), mTaskCallbackCaptor.capture());
        mGetTaskCallback.onDataNotAvailable("","");
    }

    @After
    public void destroyRepositoryInstance() {
        TasksRepository.destroyInstance();
    }

}