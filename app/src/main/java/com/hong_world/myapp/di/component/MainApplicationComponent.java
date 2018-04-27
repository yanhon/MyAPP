package com.hong_world.myapp.di.component;

import com.hong_world.homemodle.di.module.HomeAllActivitysModule;
import com.hong_world.myapp.MainApplication;
import com.hong_world.myapp.di.module.AppAllActivitysModule;
import com.hong_world.myapp.di.module.MainApplicationModule;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
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
        HomeAllActivitysModule.class,
        AppAllActivitysModule.class,
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
