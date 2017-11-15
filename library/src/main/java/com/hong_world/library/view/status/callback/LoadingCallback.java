package com.hong_world.library.view.status.callback;

import android.content.Context;
import android.view.View;

import com.hong_world.library.R;
import com.kingja.loadsir.callback.Callback;


/**
 * Date: 2017/11/15.10:23
 * Author: hong_world
 * Description:
 * Version:
 */

public class LoadingCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_loading;
    }

    //是否在显示Callback视图的时候显示原始图(SuccessView)，返回true显示，false隐藏
    @Override
    public boolean getSuccessVisible() {
        return true;
    }

    @Override
    protected boolean onReloadEvent(Context context, View view) {
        return true;
    }
}
