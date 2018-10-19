package com.hong_world.myapp;

import android.app.Application;

import com.hong_world.common.CommonApplication;

/**
 * Date: 2018/10/17. 9:59
 * Author: hong_world
 * Description:
 * Version:
 */
public class MyApplication extends CommonApplication {
    public static String[] moduleApps = {
            "com.hong_world.kotlin_module.KotlinModuleAppApplication2",
            "com.hong_world.bmodle.BModuleApplication",
            "com.hong_world.homemodle.HomeAppApplication2"
    };

    @Override
    public void initModuleApp(Application application) {
        super.initModuleApp(application);
        for (String moduleApp : moduleApps) {
            try {
                Class clazz = Class.forName(moduleApp);
                CommonApplication baseApp = (CommonApplication) clazz.newInstance();
                baseApp.initModuleApp(this);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}
