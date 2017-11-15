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
public class CustomCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_custom;
    }

    @Override
    protected boolean onReloadEvent(final Context context, View view) {
        Toast.makeText(context.getApplicationContext(), "Hello buddy, how r u! :p", Toast.LENGTH_SHORT).show();
//        (view.findViewById(R.id.iv_gift)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context.getApplicationContext(), "It's your gift! :p", Toast.LENGTH_SHORT).show();
//            }
//        });
        return false;
    }

    @Override
    public boolean getSuccessVisible() {
        return true;
    }
}
