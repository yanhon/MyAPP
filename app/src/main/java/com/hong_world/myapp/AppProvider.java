package com.hong_world.myapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hong_world.routerlibrary.provider.IAppProvider;
import com.orhanobut.logger.Logger;

/**
 * Date: 2017/11/20.9:37
 * Author: hong_world
 * Description:
 * Version:
 */
@Route(path = IAppProvider.APP_SERVICE_PATH)
public class AppProvider implements IAppProvider {
    private Context context;

    @Override
    public void sayHello(String name) {
        Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void init(Context context) {
        this.context = context;
        Log.i("test", "AppProvider初始化");
        Toast.makeText(context, "AProvider初始化", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openActivity(String path, Bundle bundle) {
        ARouter.getInstance()
                .build(path)
                .with(bundle)
                .navigation(context, new NavigationCallback() {
                    @Override
                    public void onFound(Postcard postcard) {
                        Logger.i("找到了");
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        Logger.i("找不到了");

                    }

                    @Override
                    public void onArrival(Postcard postcard) {
                        Logger.i("跳转完了");
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        Logger.i("被拦截了");
                    }
                });
    }

    @Override
    public Fragment getFragment(String path, Bundle bundle) {
        return (Fragment) ARouter.getInstance().build(path).with(bundle).navigation(context, new NavigationCallback() {
            @Override
            public void onFound(Postcard postcard) {
                Logger.i("找到了");
            }

            @Override
            public void onLost(Postcard postcard) {
                Logger.i("找不到了");

            }

            @Override
            public void onArrival(Postcard postcard) {
                Logger.i("跳转完了");
            }

            @Override
            public void onInterrupt(Postcard postcard) {
                Logger.i("被拦截了");
            }
        });
    }
}
