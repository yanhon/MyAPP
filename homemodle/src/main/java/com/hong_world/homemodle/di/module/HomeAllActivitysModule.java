package com.hong_world.homemodle.di.module;

import com.hong_world.common.di.component.BaseActivityComponent;
import com.hong_world.common.di.scoped.ActivityScope;
import com.hong_world.homemodle.view.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Data: 2018/4/7 0007
 * Author: hong_world
 * Description:
 * Version:
 */
@Module(subcomponents = {
        BaseActivityComponent.class
})
public abstract class HomeAllActivitysModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity contributeMainActivitytInjector();

}
