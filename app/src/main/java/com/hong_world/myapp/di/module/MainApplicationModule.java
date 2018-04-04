package com.hong_world.myapp.di.module;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * Date: 2018/4/4. 17:27
 * Author: hong_world
 * Description:
 * Version:
 */
@Module
public abstract class MainApplicationModule {
    @Binds
    abstract Context bindContext(Application application);
}
