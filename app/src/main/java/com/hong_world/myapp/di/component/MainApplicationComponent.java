package com.hong_world.myapp.di.component;

import android.app.Application;

import com.hong_world.common.MyApplication;
import com.hong_world.homemodle.di.module.HomeAllActivitysModule;
import com.hong_world.homemodle.di.module.MainModule;
import com.hong_world.myapp.MainApplication;
import com.hong_world.myapp.di.module.AllActivitysModule;
import com.hong_world.myapp.di.module.MainApplicationModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Date: 2018/4/4. 16:14
 * Author: hong_world
 * Description:
 * Version:
 */

@Component(modules = {
        MainApplicationModule.class,
        AndroidInjectionModule.class,
//        MainModule.class,
        HomeAllActivitysModule.class,
        AllActivitysModule.class,
        AndroidSupportInjectionModule.class} )
public interface MainApplicationComponent {
    void inject(MainApplication application);
//    @Component.Builder
//    interface Builder {
//
//        @BindsInstance
//        MainApplicationComponent.Builder application(Application application);
//
//        MainApplicationComponent build();
//    }
}
