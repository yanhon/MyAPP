package com.hong_world.common.widget.banner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Date: 2018/11/26. 14:21
 * Author: hong_world
 * Description:
 * Version:
 */
public class BannerRecyclerView extends RecyclerView {
    private double mScale=0.1;
    CenterSnapHelper centerSnapHelper;

    public BannerRecyclerView(Context context) {
        this(context, null);
    }

    public BannerRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        centerSnapHelper = new CenterSnapHelper();

    }

    public void setFlingScale(double scale) {
        this.mScale = scale;
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        if (mScale != 0)
            velocityX *= mScale;
        return super.fling(velocityX, velocityY);
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        super.setLayoutManager(layout);
        centerSnapHelper.attachToRecyclerView(this);
    }
}
