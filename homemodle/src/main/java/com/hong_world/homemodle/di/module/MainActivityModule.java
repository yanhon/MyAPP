package com.hong_world.homemodle.di.module;

import android.support.annotation.Nullable;

import com.hong_world.common.di.scoped.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Date: 2018/4/4. 18:00
 * Author: hong_world
 * Description:
 * Version:
 */
@Module
public class MainActivityModule {
    @Provides
    @ActivityScope
    @Nullable
     String provideMsg() {
        return "from MainActivityModule";
    }
}
