package com.hong_world.library.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Date: 2018/3/27. 15:39
 * Author: hong_world
 * Description:
 * Version:
 */

public class BaseApplication extends Application {
    protected String TAG = this.getClass().getSimpleName();
    private static BaseApplication sInstance;

    /**
     * 获取全局Application对象
     *
     * @return BaseApplication
     */
    public static BaseApplication getInstance() {
        if (sInstance == null) {
            sInstance = new BaseApplication();
        }
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        initActList();
    }

    private List<Activity> activityList;

    public void initActList() {
        activityList = Collections.synchronizedList(new ArrayList<Activity>());
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Log.i(TAG, "create" + activity.getPackageName());
                activityList.add(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Log.i(TAG, "start");
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.i(TAG, "resume");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.i(TAG, "pause");
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Log.i(TAG, "stop");
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Log.i(TAG, "SaveInstanceState");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.i("test", "destroy " + activity.getLocalClassName());
                activityList.remove(activity);
            }
        });
    }

    public void closeApp() {
        Iterator<Activity> iterator = activityList.iterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            activity.finish();
            iterator.remove();
        }
        activityList.clear();
    }
}
