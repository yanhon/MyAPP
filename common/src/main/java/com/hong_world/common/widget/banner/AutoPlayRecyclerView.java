package com.hong_world.common.widget.banner;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hong_world.common.R;
import com.orhanobut.logger.Logger;

/**
 * An implement of {@link RecyclerView} which support auto play.
 */

public class AutoPlayRecyclerView extends RecyclerView implements LifecycleObserver {
    private AutoPlaySnapHelper autoPlaySnapHelper;
    private double mScale = 0.1;

    public AutoPlayRecyclerView(Context context) {
        this(context, null);
    }

    public AutoPlayRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoPlayRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AutoPlayRecyclerView);
        final int timeInterval = typedArray.getInt(R.styleable.AutoPlayRecyclerView_timeInterval, AutoPlaySnapHelper.TIME_INTERVAL);
        final int direction = typedArray.getInt(R.styleable.AutoPlayRecyclerView_direction, AutoPlaySnapHelper.RIGHT);
        typedArray.recycle();
        autoPlaySnapHelper = new AutoPlaySnapHelper(timeInterval, direction);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result = super.dispatchTouchEvent(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (autoPlaySnapHelper != null) {
                    autoPlaySnapHelper.pause();
                }
                break;
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (autoPlaySnapHelper != null) {
                    autoPlaySnapHelper.start();
                }
        }
        return result;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void start() {
        autoPlaySnapHelper.start();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void pause() {
        autoPlaySnapHelper.pause();
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        super.setLayoutManager(layout);
        autoPlaySnapHelper.attachToRecyclerView(this);
    }

    public void setFlingScale(double scale) {
        this.mScale = scale;
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        if (mScale != 0)
            velocityX *= mScale;
        return super.fling(0, velocityY);
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        Logger.i("AutoPlayRecyclerView : onVisibilityChanged " + visibility);
        if (visibility == VISIBLE) start();
        else pause();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        Logger.i("AutoPlayRecyclerView : onWindowFocusChanged " + hasWindowFocus);
        if (!hasWindowFocus && this.getVisibility() == VISIBLE) pause();
        else start();
    }
}
