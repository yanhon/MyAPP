package com.hong_world.library.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;


/**
 * Date: 2017/10/31.17:55
 * Author: hong_world
 * Description:
 * Version:
 */

public abstract class BaseAppCompatFragment extends Fragment {
    /**
     * Log tag
     */
    protected static String TAG_LOG = null;
    /**
     * Screen information
     */
    protected int mScreenWidth = 0;
    protected int mScreenHeight = 0;
    protected float mScreenDensity = 0.0f;

    protected Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG_LOG = this.getClass().getSimpleName();
    }

    protected abstract View onCreateMyView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

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
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        mScreenDensity = displayMetrics.density;
        mScreenHeight = displayMetrics.heightPixels;
        mScreenWidth = displayMetrics.widthPixels;
        if (getArguments() != null) {
            getArgumentsBundle(getArguments());
        }
        initViews(view, savedInstanceState);
        initDataSource();
    }

    protected abstract void initDataSource();

    /**
     * init all views and add events
     */
    protected abstract void initViews(View view, Bundle savedInstanceState);

    /**
     * get bundle data
     *
     * @param arguments
     */

    protected void getArgumentsBundle(Bundle arguments) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hideSoftInput();
    }

    protected void hideSoftInput() {
        if (this.getView() == null || this.getView().getContext() == null) return;
        InputMethodManager imm = (InputMethodManager) this.getView().getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getView().getWindowToken(), 0);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            hideSoftInput();
        }
    }
}
