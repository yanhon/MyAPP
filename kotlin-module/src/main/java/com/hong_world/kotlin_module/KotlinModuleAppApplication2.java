package com.hong_world.kotlin_module;

import android.app.Application;

import com.hong_world.common.CommonApplication;
import com.orhanobut.logger.Logger;

/**
 * Date: 2018/10/17. 10:06
 * Author: hong_world
 * Description:
 * Version:
 */
public class KotlinModuleAppApplication2 extends CommonApplication {
    @Override
    public void initModuleApp(Application application) {
        super.initModuleApp(application);
        Logger.i("KotlinModule第三方服务初始化");
    }
}