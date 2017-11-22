package com.hong_world.common.base;

import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;

import com.hong_world.common.utils.EspressoIdlingResource;
import com.hong_world.library.base.BaseAppCompatActivity;

/**
 * Date: 2017/11/22.13:55
 * Author: hong_world
 * Description:
 * Version:
 */

public class BaseActivity extends BaseAppCompatActivity {
    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }
}
