package com.hong_world.homemodle.presenter;

import android.databinding.ObservableField;

import com.hong_world.common.bean.Task;
import com.hong_world.homemodle.Injection;
import com.hong_world.homemodle.contract.MainContract;
import com.hong_world.homemodle.modle.TasksDataSource;
import com.hong_world.homemodle.modle.TasksRepository;
import com.hong_world.homemodle.task.LoginTask;
import com.hong_world.library.base.BaseUseCase;

/**
 * Date: 2017/10/31.17:38
 * Author: hong_world
 * Description: 可直接或间接通过(UseCase)LoginTask控制mode(TasksRepository)获取数据、
 * -------------通过databinding控制View(xml)
 * -------------通过BaseView接口控制view(fragment/activity)
 * Version:
 */
public class MainPresenter extends MainContract.Presenter {

    public Task task = new Task();

    public final ObservableField<String> phone = new ObservableField<>();
    public final ObservableField<String> pwd = new ObservableField<>();

    LoginTask loginTask;

    TasksDataSource mTasksRepository;

    @Override
    public void initData() {
        super.initData();
        mView.onSuccess();
    }

    /**
     * base的mvp
     * @param view
     */
    public MainPresenter(MainContract.View view) {
        setmView(view);
        mTasksRepository = Injection.provideTasksRepository();
    }

    /**
     * 加入clean
     * @param view
     * @param loginTask
     */
    public MainPresenter(MainContract.View view, LoginTask loginTask) {
        setmView(view);
        this.loginTask = loginTask;
    }

    /**
     * test
     * @param view
     * @param loginTask
     * @param mTasksRepository
     */
    public MainPresenter(MainContract.View view, LoginTask loginTask, TasksDataSource mTasksRepository) {
        setmView(view);
        this.loginTask = loginTask;
        this.mTasksRepository = mTasksRepository;
    }

    @Override
    public void loginTask(String phone, String pwd) {
//       phone = this.phone.get();
//       pwd = this.pwd.get();
        mView.onLoading();
        Task newTask;
        if (phone != null && pwd != null && phone.length() != 0 && pwd.length() != 0) {
            newTask = new Task(phone, pwd);
        } else {
            mView.onError();
            return;
        }
        loginTask(newTask);
    }
    /**
     * 通过TasksRepository获取数据
     * @param task 实体
     */
    @Override
    public void loginTask(Task task) {
        mTasksRepository.getTask(task, new TasksDataSource.GetTaskCallback<Task>() {
            @Override
            public void onTaskLoaded(Task task) {
                mView.onSuccess();
                mView.onSuccess(task);
            }

            @Override
            public void onDataNotAvailable(String type, String msg) {
                mView.onDataNotAvailable(type, msg);
            }
        });

    }

    /**
     *  通过useCase处理
     */
    public void loginTaskRepository(Task task) {
        if (task.getPhone() == null && task.getPwd() == null && task.getPhone().length() != 0 && task.getPwd().length() != 0) {
            mView.onError();
            return;
        }
        loginTask.setRequestValues(new LoginTask.RequestValues(task));
        loginTask.setUseCaseCallback(new BaseUseCase.UseCaseCallback<LoginTask.ResponseValue>() {
            @Override
            public void onSuccess(LoginTask.ResponseValue response) {
                mView.onSuccess(response.getTask());
            }

            @Override
            public void onError(String type, String msg) {
                mView.onDataNotAvailable(type, msg);
            }
        });
        loginTask.run();
    }
}