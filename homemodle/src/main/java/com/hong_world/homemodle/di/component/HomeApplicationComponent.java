package com.hong_world.homemodle.di.component;

import android.app.Application;

import com.hong_world.homemodle.IHomeApplication;
import com.hong_world.homemodle.di.module.HomeAllActivitysModule;

import dagger.Component;
import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Data: 2018/4/7 0007
 * Author: hong_world
 * Description:
 * Version:
 */
@Subcomponent(modules = {AndroidInjectionModule.class,
        HomeAllActivitysModule.class,
        AndroidSupportInjectionModule.class})
public interface HomeApplicationComponent {
//    void inject(Object application);
}
