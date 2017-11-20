package com.hong_world.routerlibrary;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hong_world.routerlibrary.provider.IAppProvider;
import com.hong_world.routerlibrary.provider.IBProvider;
import com.hong_world.routerlibrary.provider.IHomeProvider;

/**
 * Date: 2017/11/17.14:44
 * Author: hong_world
 * Description:
 * Version:
 */

public class ServiceManager {
    //服务注入看自己的具体实现
    //自动注入
//    @Autowired(name = IAppProvider.APP_MAIN_SERVICE)
//    @Autowired(name = "/service/App")
    IAppProvider appProvider;
    //    @Autowired
    IHomeProvider homeProvider;
    //    @Autowired
    IBProvider bProvider;

    public ServiceManager() {
//        ARouter.getInstance().inject(this);
    }

    private static final class ServiceManagerHolder {
        private static final ServiceManager instance = new ServiceManager();
    }

    public static ServiceManager getInstance() {
        return ServiceManagerHolder.instance;
    }

//    public IAppProvider getAppProvider() {
//        if (appProvider == null) {
//            appProvider = ARouter.getInstance().navigation(IAppProvider.class);
//        }
//        return appProvider;
//    }

    public IHomeProvider getHomeProvider() {
        if (homeProvider == null) {
            homeProvider = ARouter.getInstance().navigation(IHomeProvider.class);
        }
        if(homeProvider==null){
            Log.i("test","为空！！！");
            homeProvider= new IHomeProvider() {
                @Override
                public void openActivity(Bundle bundle) {

                }

                @Override
                public void sayHello(String name) {

                }

                @Override
                public void init(Context context) {

                }
            };
        }
        return homeProvider;
    }

//    public IBProvider getBProvider() {
//        if (bProvider == null) {
//            bProvider = ARouter.getInstance().navigation(IBProvider.class);
//        }
//        return bProvider;
//    }
}
