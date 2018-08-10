package com.hong_world.view.widget.behavior;

import android.animation.Animator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;

import java.util.logging.Logger;

/**
 * Date: 2018/8/10. 16:01
 * Author: hong_world
 * Description:
 * Version:
 */
public class FooterBehavior extends CoordinatorLayout.Behavior<View> {

    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();

    private int sinceDirectionChange;

    public FooterBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

//    //1.判断滑动的方向 我们需要垂直滑动
//    @Override
//    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child,
//                                       View directTargetChild, View target, int nestedScrollAxes) {
//        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
//    }
//
//    //2.根据滑动的距离显示和隐藏footer view
//    @Override
//    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child,
//                                  View target, int dx, int dy, int[] consumed) {
//        if (dy > 0 && sinceDirectionChange < 0 || dy < 0 && sinceDirectionChange > 0) {
//            child.animate().cancel();
//            sinceDirectionChange = 0;
//        }
//        sinceDirectionChange += dy;
//        int visibility = child.getVisibility();
//        if (sinceDirectionChange > child.getHeight() && visibility == View.VISIBLE) {
//            hide(child);
//        } else {
//            if (sinceDirectionChange < 0 && (visibility == View.GONE || visibility == View
//                    .INVISIBLE)) {
//                show(child);
//            }
//        }
//    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        com.orhanobut.logger.Logger.i(" ,axes =" + axes);
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        com.orhanobut.logger.Logger.i(" ,type =" + type + " ,consumed[1] = " + consumed[1] + ",dy = " + dy);
        if (dy > 0) {
            if (child.getVisibility() == View.VISIBLE)
                child.setVisibility(View.INVISIBLE);
        } else {
            if (child.getVisibility() == View.INVISIBLE) {
                child.setVisibility(View.VISIBLE);
            }

        }
//        }
    }

}
