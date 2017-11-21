package com.hong_world.homemodle.debug;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hong_world.library.view.status.callback.CustomCallback;
import com.hong_world.library.view.status.callback.EmptyCallback;
import com.hong_world.library.view.status.callback.ErrorCallback;
import com.hong_world.library.view.status.callback.LoadingCallback;
import com.hong_world.library.view.status.callback.TimeoutCallback;
import com.kingja.loadsir.core.LoadSir;

/**
 * Date: 2017/11/21.10:26
 * Author: hong_world
 * Description:
 * Version:
 */

public class HomeDebugAppliction extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initArouter();
        initILoaderManager();
        initViewStatus();
    }

    private void initArouter() {
        if (true) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
    }

    private void initViewStatus() {
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }

    private void initILoaderManager() {
//        ILoaderManager.setLoader(new FrescoLoader());//外部定制图片加载库Fresco
//        ILoaderManager.getLoader().init(this);
    }
}
