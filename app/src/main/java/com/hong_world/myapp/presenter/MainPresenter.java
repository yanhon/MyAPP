package com.hong_world.myapp.presenter;

import com.hong_world.myapp.Injection;
import com.hong_world.myapp.LoginTask;
import com.hong_world.myapp.contract.MainContract;
import com.hong_world.myapp.base.BaseUseCase;
import com.hong_world.myapp.bean.Task;

/**
 * Created by hong_world on 2017/10/31.
 */
public class MainPresenter extends MainContract.Presenter {
    LoginTask loginTask;


    public MainPresenter(MainContract.View view) {
        setmView(view);
        loginTask = Injection.provideLoginTask();
    }

    public MainPresenter(MainContract.View view, LoginTask loginTask) {
        setmView(view);
        this.loginTask = loginTask;
    }

    @Override
    public void loginTask(String phone, String pwd) {
        Task newTask;
        if (phone != null && pwd != null && phone.length() != 0 && pwd.length() != 0) {
            newTask = new Task(phone, pwd);
        } else {
            mView.onError();
            return;
        }
        loginTask.setRequestValues(new LoginTask.RequestValues(newTask));
        loginTask.setUseCaseCallback(new BaseUseCase.UseCaseCallback<LoginTask.ResponseValue>() {
            @Override
            public void onSuccess(LoginTask.ResponseValue response) {
                mView.onSuccess(response.getTask());
            }

            @Override
            public void onError() {
                mView.onError();
            }
        });
        loginTask.run();
    }

    @Override
    public void loginTask(Task task) {
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
            public void onError() {
                mView.onError();
            }
        });
        loginTask.run();
    }
}