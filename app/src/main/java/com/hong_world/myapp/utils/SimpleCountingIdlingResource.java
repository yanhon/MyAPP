package com.hong_world.myapp.utils;

import android.support.annotation.NonNull;
import android.support.test.espresso.IdlingResource;
import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Date: 2017/11/7.10:48
 * Author: hong_world
 * Description: Espresso 延时操作
 * Version:
 */

public class SimpleCountingIdlingResource implements IdlingResource {
    private final String mResourceName;

    private final AtomicInteger counter = new AtomicInteger(0);

    private volatile ResourceCallback resourceCallback;

    public SimpleCountingIdlingResource(@NonNull String resourceName) {
        mResourceName = resourceName;
    }

    @Override
    public String getName() {
        return mResourceName;
    }

    @Override
    public boolean isIdleNow() {
        return counter.get() == 0;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        this.resourceCallback = resourceCallback;
    }

    public void increment() {//自动增加1
        counter.getAndIncrement();
    }

    public void decrement() {
        Log.i("test", "counterVal start=" + counter.get());
        int counterVal = counter.decrementAndGet();
        Log.i("test", "counterVal end=" + counterVal);
        if (counterVal == 0) {
            // 通知Espresso此时为空闲
            if (null != resourceCallback) {
                resourceCallback.onTransitionToIdle();
            }
        }

        if (counterVal < 0) {
            throw new IllegalArgumentException("Counter has been corrupted!");
        }
    }
}
