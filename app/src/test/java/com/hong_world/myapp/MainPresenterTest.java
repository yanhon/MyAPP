package com.hong_world.myapp;

import com.hong_world.myapp.bean.Task;
import com.hong_world.myapp.contract.MainContract;
import com.hong_world.myapp.modle.TasksDataSource;
import com.hong_world.myapp.modle.TasksRepository;
import com.hong_world.myapp.presenter.MainPresenter;
import com.hong_world.myapp.task.LoginTask;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Date: 2017/11/3.18:01
 * Author: hong_world
 * Description:
 * Version:
 */
public class MainPresenterTest {
    @Mock
    private TasksRepository mTasksRepository;
    @Mock
    private MainContract.View mMainView;
    @Captor
    private ArgumentCaptor<TasksDataSource.GetTaskCallback> mGetTaskCallbackCaptor;

    private MainPresenter mainPresenter;

    @Before
    public void setupMocksAndView() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // The presenter wont't update the view unless it's active.
        when(mMainView.isActive()).thenReturn(true);
    }

    public MainPresenter getMainPresenter() {
        LoginTask loginTask = new LoginTask(mTasksRepository);
        return new MainPresenter(mMainView, loginTask);
    }

    @Test
    public void loginStringSuccess() {
        mainPresenter = getMainPresenter();
        mainPresenter.loginTask("123", "321");
        verify(mTasksRepository).getTask(any(Task.class), mGetTaskCallbackCaptor.capture());//断言
        mGetTaskCallbackCaptor.getValue().onTaskLoaded(any(Task.class));
        verify(mMainView).onSuccess(any(Task.class));
    }

    @Test
    public void loginBeanSuccess() {
        mainPresenter = getMainPresenter();
        Task task = new Task("123", "321");
        mainPresenter.loginTask(task);
        verify(mTasksRepository).getTask(any(Task.class), mGetTaskCallbackCaptor.capture());
        mGetTaskCallbackCaptor.getValue().onTaskLoaded(any(Task.class));
        verify(mMainView).onSuccess(any(Task.class));
    }

    @Test
    public void login_empty_show_errorView() {
        mainPresenter = getMainPresenter();
        mainPresenter.loginTask("1", null);
        verify(mMainView).onError();
    }

}