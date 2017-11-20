package com.hong_world.myapp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hong_world.routerlibrary.provider.IAppProvider;

/**
 * Date: 2017/11/20.9:37
 * Author: hong_world
 * Description:
 * Version:
 */
@Route(path = IAppProvider.APP_SERVICE_PATH, group = IAppProvider.APP_GROUP)
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
}
