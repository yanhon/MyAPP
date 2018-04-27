package com.hong_world.myapp;

import com.hong_world.bmodle.IBApplication;
import com.hong_world.common.MyApplication;
import com.hong_world.homemodle.IHomeApplication;
import com.hong_world.homemodle.di.component.DaggerHomeApplicationComponent;
import com.hong_world.homemodle.di.component.HomeApplicationComponent;
import com.hong_world.myapp.di.component.DaggerMainApplicationComponent;

/**
 * Date: 2018/4/4. 16:29
 * Author: hong_world
 * Description:
 * Version:
 */

public class MainApplication extends MyApplication implements IBApplication, IHomeApplication {


    @Override
    public void onCreate() {
        super.onCreate();
//        HomeApplicationComponent homeApplicationComponent = DaggerHomeApplicationComponent.builder().build();
////        homeApplicationComponent.inject(this);
//        DaggerMainApplicationComponent.builder().homeApplicationComponent(homeApplicationComponent).build().inject(this);
        DaggerMainApplicationComponent.create().inject(this);
    }


}
