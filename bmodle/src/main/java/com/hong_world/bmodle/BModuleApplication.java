package com.hong_world.bmodle;

import android.app.Application;

import com.hong_world.common.CommonApplication;
import com.orhanobut.logger.Logger;

/**
 * Date: 2018/10/17. 10:22
 * Author: hong_world
 * Description:
 * Version:
 */
public class BModuleApplication extends CommonApplication {
    @Override
    public void initModuleApp(Application application) {
        super.initModuleApp(application);
        Logger.i(" BModule第三方服务初始化");
    }
}
