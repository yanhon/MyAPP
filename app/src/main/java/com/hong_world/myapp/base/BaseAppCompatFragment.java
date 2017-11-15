package com.hong_world.myapp.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hong_world.library.view.status.StatusLayoutManager;

/**
 * Date: 2017/10/31.17:55
 * Author: hong_world
 * Description:
 * Version:
 */

public abstract class BaseAppCompatFragment extends Fragment {
    protected Activity mActivity;
    protected StatusLayoutManager mStatusLayoutManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected abstract View onCreateMyView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    protected abstract int getLayoutId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = onCreateMyView(inflater, container, savedInstanceState);
        if (view != null) {
            return view;
        } else
            return super.onCreateView(inflater, container, savedInstanceState);
    }

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
