package com.hong_world.common.utils;

import android.support.test.espresso.IdlingResource;

/**
 * Date: 2017/11/7.11:28
 * Author: hong_world
 * Description:
 * Version:
 */

public class EspressoIdlingResource {
    private static final String RESOURCE = "GLOBAL";

    private static final SimpleCountingIdlingResource DEFAULT_INSTANCE =
            new SimpleCountingIdlingResource(RESOURCE);

    public static void increment() {//增加
        DEFAULT_INSTANCE.increment();
    }

    public static void decrement() {//递减
        DEFAULT_INSTANCE.decrement();
    }

    public static IdlingResource getIdlingResource() {
        return DEFAULT_INSTANCE;
    }
}
