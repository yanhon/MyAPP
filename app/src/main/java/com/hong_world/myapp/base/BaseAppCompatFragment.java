package com.hong_world.myapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Date: 2017/10/31.17:55
 * Author: hong_world
 * Description:
 * Version:
 */

public abstract class BaseAppCompatFragment extends Fragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view, savedInstanceState);
    }

    /**
     * init all views and add events
     */
    protected abstract void initViews(View view, Bundle savedInstanceState);

}
