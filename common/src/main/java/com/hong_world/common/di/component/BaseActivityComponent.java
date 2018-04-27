package com.hong_world.common.di.component;

import com.hong_world.common.base.BaseActivity;
import com.hong_world.library.base.BaseAppCompatActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Data: 2018/4/7 0007. 15:27
 * Author: hong_world
 * Description:
 * Version:
 */
@Subcomponent(modules = {
        AndroidInjectionModule.class,
})
public interface BaseActivityComponent extends AndroidInjector<BaseAppCompatActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseAppCompatActivity> {
    }

}
