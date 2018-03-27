package com.hong_world.library.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Date: 2017/11/7.14:51
 * Author: hong_world
 * Description:
 * Version:
 */

public abstract class BaseAppCompatActivity extends AppCompatActivity{
    /**
     * Screen information
     */
    protected int mScreenWidth = 0;
    protected int mScreenHeight = 0;
    protected float mScreenDensity = 0.0f;

    /**
     * context
     */
    protected Context mContext = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }
        initViews(savedInstanceState);
        initDataSource();
    }
    /**
     * get bundle data
     *
     * @param extras
     */
    protected void getBundleExtras(Bundle extras) {

    }
    /**
     * init all views and add events
     */
    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract void initDataSource();
}
