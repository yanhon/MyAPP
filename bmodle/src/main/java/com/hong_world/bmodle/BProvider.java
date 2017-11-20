package com.hong_world.bmodle;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hong_world.routerlibrary.provider.IBProvider;

/**
 * Date: 2017/11/20.9:38
 * Author: hong_world
 * Description:
 * Version:
 */
@Route(path = IBProvider.B_SERVICE,group = IBProvider.B_GROUP)
public class BProvider implements IBProvider {
    private Context context;

    @Override
    public void sayHello(String name) {
        Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void init(Context context) {
        this.context = context;
        Log.i("test", "BProvider初始化");

        Toast.makeText(context, "BProvider初始化", Toast.LENGTH_SHORT).show();
    }
}
