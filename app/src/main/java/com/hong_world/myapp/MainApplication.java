package com.hong_world.myapp;

import android.app.Activity;

import com.hong_world.bmodle.IBApplication;
import com.hong_world.common.MyApplication;
import com.hong_world.homemodle.IHomeApplication;
import com.hong_world.myapp.di.component.DaggerMainApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Date: 2018/4/4. 16:29
 * Author: hong_world
 * Description:
 * Version:
 */

public class MainApplication extends MyApplication implements IBApplication, IHomeApplication, HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerMainApplicationComponent.create().inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

}
