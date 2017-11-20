package com.hong_world.routerlibrary;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;

/**
 * Date: 2017/11/20.18:12
 * Author: hong_world
 * Description: 请求地址发生错误时，回调
 * Version:
 */
@Route(path = "/xxx/xxx")
public class DegradeServiceImpl implements DegradeService {
    private Context context;

    @Override
    public void onLost(Context context, Postcard postcard) {
        Log.i("test", "DegradeServiceImpl降级处理！");
    }

    @Override
    public void init(Context context) {
        this.context = context;
    }
}
