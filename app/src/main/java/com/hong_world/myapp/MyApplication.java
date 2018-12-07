package com.hong_world.myapp;

import android.app.Application;

import com.hong_world.common.CommonApplication;

/**
 * Date: 2018/10/17. 9:59
 * Author: hong_world
 * Description:
 * ------------单元测试讲解：http://www.jianshu.com/p/cf446be43ae8
 * ------------Espresso 关键api讲解 https://blog.csdn.net/fei20121106/article/details/70308193#2214-pressback%E5%90%8E%E9%80%80%E9%94%AE-%E7%82%B9%E5%87%BB
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
