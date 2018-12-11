package com.hong_world.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hong_world.common.bean.FeedArticleListData;
import com.hong_world.common.providers.IBProviderc;
import com.hong_world.routerlibrary.provider.IAppProvider;
import com.hong_world.routerlibrary.provider.IHomeProvider;
import com.hong_world.routerlibrary.provider.IKotlinModuleProvider;
import com.orhanobut.logger.Logger;

import io.reactivex.Observable;

/**
 * Date: 2017/11/17.14:44
 * Author: hong_world
 * Description:
 * Version:
 */

public class ProviderManager {
    //服务注入看自己的具体实现
    //自动注入
//    @Autowired(name = IAppProvider.APP_MAIN_SERVICE)
//    @Autowired(name = "/service/App")
    IAppProvider appProvider;
    //    @Autowired(name = IHomeProvider.HOME_SERVICE)
    public static IHomeProvider homeProvider;
    //    @Autowired(name = IBProvider.B_SERVICE)
    public static IBProviderc bProvider;
    //    @Autowired(name = IKotlinModuleProvider.KOTLIN_MODULE_SERVICE)
    public static IKotlinModuleProvider kotlinModuleProvider;

    public ProviderManager() {
        ARouter.getInstance().inject(this);
    }

    private static final class ServiceManagerHolder {
        private static final ProviderManager instance = new ProviderManager();
    }

    public static ProviderManager getInstance() {
        return ServiceManagerHolder.instance;
    }

//    public IAppProvider getAppProvider() {
//        if (appProvider == null) {
//            appProvider = ARouter.getInstance().navigation(IAppProvider.class);
//        }
//        return appProvider;
//    }

    public static IHomeProvider getHomeProvider() {
        if (homeProvider == null) {
            homeProvider = ARouter.getInstance().navigation(IHomeProvider.class);
        }
        if (homeProvider == null) {
            Log.i("test", "为空！！！");
            homeProvider = new IHomeProvider() {
                @Override
                public void openActivity(Bundle bundle) {

                }

                @Override
                public void sayHello(String name) {

                }

                @Override
                public void openActivity(String path, @Nullable Bundle bundle) {

                }

                @Override
                public Fragment getFragment(String path, @Nullable Bundle bundle) {
                    return null;
                }

                @Override
                public void init(Context context) {

                }
            };
        }
        Logger.i("homeProvider address: " + homeProvider.toString());
        return homeProvider;
//        return ARouter.getInstance().navigation(IHomeProvider.class);
    }

    public static IBProviderc getBProvider() {
        if (bProvider == null) {
            bProvider = ARouter.getInstance().navigation(IBProviderc.class);
        }
        if (bProvider == null) {
            bProvider = new IBProviderc() {
                @Override
                public Observable<FeedArticleListData> getFeedArticleList(int num) {
                    return null;
                }

                @Override
                public void sayHello(String name) {

                }

                @Override
                public void openActivity(String path, @Nullable Bundle bundle) {

                }

                @Override
                public Fragment getFragment(String path, @Nullable Bundle bundle) {
                    return null;
                }

                @Override
                public void init(Context context) {

                }
            };
        }
        return bProvider;
    }

    public static IKotlinModuleProvider getKotlinProvider() {
        if (kotlinModuleProvider == null) {
            kotlinModuleProvider = ARouter.getInstance().navigation(IKotlinModuleProvider.class);
        }
        if (kotlinModuleProvider == null) kotlinModuleProvider = new IKotlinModuleProvider() {
            @Override
            public void sayHello(String name) {

            }

            @Override
            public void openActivity(String path, @Nullable Bundle bundle) {

            }

            @Override
            public Fragment getFragment(String path, @Nullable Bundle bundle) {
                return null;
            }

            @Override
            public void init(Context context) {

            }
        };
        return kotlinModuleProvider;
    }
}
