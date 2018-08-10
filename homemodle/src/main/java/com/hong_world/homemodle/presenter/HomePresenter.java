package com.hong_world.homemodle.presenter;

import com.hong_world.homemodle.Injection;
import com.hong_world.homemodle.contract.HomeContract;
import com.hong_world.homemodle.modle.TasksDataSource;

/**
 * Date: 2018/8/7. 16:53
 * Author: hong_world
 * Description:
 * Version:
 */

public class HomePresenter extends HomeContract.Presenter {
    TasksDataSource mTasksRepository;

    public HomePresenter(HomeContract.View view) {
        setmView(view);
        mTasksRepository = Injection.provideTasksRepository();
    }
}
