package com.hong_world.homemodle;

import android.app.Activity;

import com.hong_world.common.bean.Task;
import com.hong_world.homemodle.contract.MainContract;
import com.hong_world.homemodle.modle.TasksDataSource;
import com.hong_world.homemodle.modle.TasksRepository;
import com.hong_world.homemodle.net.RegisterResp;
import com.hong_world.homemodle.presenter.MainPresenter;
import com.hong_world.homemodle.task.LoginTask;
import com.hong_world.library.net.FragmentLifeCycleEvent;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

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
    @Mock
    Activity context;
    PublishSubject<FragmentLifeCycleEvent> lifecycleSubject = PublishSubject.create();
    @Captor
    private ArgumentCaptor<TasksDataSource.GetTaskCallback<Task>> mGetTaskCallbackCaptor;

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
        return new MainPresenter(mMainView, loginTask, mTasksRepository);
    }

    @Test
    public void loginStringSuccess() {
        mainPresenter = getMainPresenter();
        mainPresenter.loginTask(new Task());
        verify(mTasksRepository).getTask(any(Task.class), mGetTaskCallbackCaptor.capture());//断言
        mGetTaskCallbackCaptor.getValue().onTaskLoaded(new Task());
        verify(mMainView).onSuccess();
        verify(mMainView).onSuccess(any(Task.class));
    }

    /**
     * 有毛病
     */
    @Test
    public void loginStringSuccess2() {
        mainPresenter = getMainPresenter();
        Task task = new Task("1232", "321");
        mainPresenter.loginTask2(task);
        when(mMainView.getLifecycleSubject()).thenReturn(lifecycleSubject);
        when(mMainView.getActivityContext()).thenReturn(context);
        verify(mTasksRepository).getTask(any(Task.class), context, mMainView.getLifecycleSubject(), mGetTaskCallbackCaptor.capture());//断言
        mGetTaskCallbackCaptor.getValue().onTaskLoaded(task);
        verify(mMainView).onSuccess(task);
    }

    @Test
    public void loginStringSuccess3() {
        mainPresenter = getMainPresenter();
        when(mTasksRepository.getTask("","")).thenReturn(Observable.just(new RegisterResp()));
        mainPresenter.loginTask3("","");
        verify(mTasksRepository).getTask("","");

//        verify(mMainView).onSuccess(task);
    }

    @Test
    public void loginBeanSuccess() {
        mainPresenter = getMainPresenter();
        Task task = new Task("123", "321");
        mainPresenter.loginTask(task);
        verify(mTasksRepository).getTask(any(Task.class), mGetTaskCallbackCaptor.capture());
        mGetTaskCallbackCaptor.getValue().onTaskLoaded(task);
        verify(mMainView).onSuccess(task);
    }

    @Test
    public void login_empty_show_errorView() {
        mainPresenter = getMainPresenter();
        mainPresenter.loginTaskString("1", null);
        verify(mMainView).onError("");
    }

    @Test
    public void unuse_LoginTask_to_request() {
        mainPresenter = getMainPresenter();
        Task task = new Task("123", "321");
        mainPresenter.loginTaskRepository(task);
        verify(mTasksRepository).getTask(any(Task.class), mGetTaskCallbackCaptor.capture());
        mGetTaskCallbackCaptor.getValue().onTaskLoaded(task);
        verify(mMainView).onSuccess(task);
    }
}