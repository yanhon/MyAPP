package com.hong_world.homemodle;

import android.app.Application;

import com.hong_world.common.CommonApplication;
import com.orhanobut.logger.Logger;

/**
 * Date: 2018/10/17. 10:21
 * Author: hong_world
 * Description:
 * Version:
 */
public class HomeAppApplication2 extends CommonApplication {
    @Override
    public void initModuleApp(Application application) {
        super.initModuleApp(application);
        Logger.i(" HomeModule第三方服务初始化");
    }
}
