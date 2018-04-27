package com.hong_world.myapp.di.module;

import com.hong_world.common.di.component.BaseActivityComponent;

import dagger.Module;

/**
 * Data: 2018/4/7 0007
 * Author: hong_world
 * Description:
 * Version:
 */
@Module(subcomponents = {
        BaseActivityComponent.class
})
public abstract class AppAllActivitysModule {
//    @ActivityScope
//    @ContributesAndroidInjector(modules = MainActivityModule.class)
//    abstract MainActivity contributeMainActivitytInjector();
}
