package com.hong_world.myapp;

import com.hong_world.myapp.base.BaseUseCase;
import com.hong_world.myapp.bean.Task;

/**
 * Created by hong_world on 2017/10/31.
 */
public class MainPresenter extends MainContract.Presenter {
    LoginTask loginTask;


    public MainPresenter(MainContract.View view) {
        setmView(view);
        loginTask =Injection.provideLoginTask();
    }


    @Override
    void loginTask(String phone, String pwd) {
        Task newTask;
        if (phone != null && pwd != null) {
            newTask = new Task(phone, pwd);
        } else return;
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
}