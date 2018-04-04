package com.hong_world.homemodle.di.module;

import com.hong_world.homemodle.di.scoped.ActivityScoped;
import com.hong_world.homemodle.view.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Date: 2018/4/4. 17:57
 * Author: hong_world
 * Description:
 * Version:
 */
@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity addMainActivity();
}
