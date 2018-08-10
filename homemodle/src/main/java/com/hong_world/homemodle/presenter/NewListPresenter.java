package com.hong_world.homemodle.presenter;

import com.hong_world.homemodle.Injection;
import com.hong_world.homemodle.contract.NewListContract;
import com.hong_world.homemodle.modle.TasksDataSource;

/**
 * Date: 2018/7/31. 10:10
 * Author: hong_world
 * Description:
 * Version:
 */
public class NewListPresenter extends NewListContract.Presenter {
    TasksDataSource mTasksRepository;

    public NewListPresenter(NewListContract.View view) {
        setmView(view);
        mTasksRepository = Injection.provideTasksRepository();
    }
}
