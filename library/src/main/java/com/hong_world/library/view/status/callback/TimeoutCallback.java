package com.hong_world.library.view.status.callback;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.hong_world.library.R;
import com.kingja.loadsir.callback.Callback;


/**
 * Date: 2017/11/15.10:23
 * Author: hong_world
 * Description:
 * Version:
 */

public class TimeoutCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_timeout;
    }

    @Override
    protected boolean onReloadEvent(Context context, View view) {
        Toast.makeText(context.getApplicationContext(),"Connecting to the network again!",Toast.LENGTH_SHORT).show();
        return false;
    }

}
