package com.hong_world.myapp;

import android.app.Application;

/**
 * Date: 2017/11/13.17:32
 * Author: hong_world
 * Description:
 * Version:
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        ILoaderManager.setLoader(new FrescoLoader());//外部定制图片加载库Fresco
//        ILoaderManager.getLoader().init(this);
    }
}
