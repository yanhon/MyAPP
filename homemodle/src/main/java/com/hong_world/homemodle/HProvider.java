package com.hong_world.homemodle;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hong_world.routerlibrary.provider.IHomeProvider;

/**
 * Date: 2017/11/20.9:38
 * Author: hong_world
 * Description:
 * Version:
 */
@Route(path = IHomeProvider.HOME_SERVICE, group = IHomeProvider.HOME_GROUP)
public class HProvider implements IHomeProvider {
    private Context context;

    @Override
    public void sayHello(String name) {
        Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void init(Context context) {
        this.context = context;
        Log.i("test", "HProvider初始化");
        Toast.makeText(context, "HProvider初始化", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openActivity(Bundle bundle) {
        ARouter.getInstance()
                .build(IHomeProvider.HOME_ACT_MAIN, IHomeProvider.HOME_GROUP)
                .with(bundle)
                .navigation(context, new NavigationCallback() {
                    @Override
                    public void onFound(Postcard postcard) {
                        Log.i("test", "找到了");
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        Log.i("test", "找不到了");

                    }

                    @Override
                    public void onArrival(Postcard postcard) {
                        Log.i("test", "跳转完了");
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        Log.i("test", "被拦截了");
                    }
                });
    }
}
