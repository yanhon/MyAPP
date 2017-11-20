package com.hong_world.bmodle;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

/**
 * Date: 2017/11/20.11:36
 * Author: hong_world
 * Description:
 * Version:
 */
@Interceptor(priority = 2, name = "b拦截器")
public class BInterceptor implements IInterceptor {
    Context mContext;

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        Log.e("test", BInterceptor.class.getName() + " path="+postcard.getPath());
        callback.onContinue(postcard);
    }

    @Override
    public void init(Context context) {
        // 拦截器的初始化，会在sdk初始化的时候调用该方法，仅会调用一次
        this.mContext = context;
        Log.e("test", BInterceptor.class.getName() + " has init.");
    }
}
