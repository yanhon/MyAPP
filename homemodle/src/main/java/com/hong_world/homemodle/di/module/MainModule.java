package com.hong_world.homemodle.di.module;

import android.support.annotation.Nullable;

import com.hong_world.homemodle.di.scoped.ActivityScoped;
import com.hong_world.homemodle.view.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Date: 2018/4/4. 18:00
 * Author: hong_world
 * Description:
 * Version:
 */
@Module
public abstract class MainModule {
    @Provides
    @ActivityScoped
    @Nullable
    static String provideMsg(MainActivity activity) {
        return "from MainModule";
    }
}
