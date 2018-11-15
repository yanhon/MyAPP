package com.hong_world.common.adapters;

import android.databinding.BindingAdapter;

import com.hong_world.common.widget.ProcessView;

/**
 * Date: 2018/11/15. 14:13
 * Author: hong_world
 * Description:
 * Version:
 */
public class CommonViewBindinAdapter {
    @BindingAdapter(value = {"setColors"}, requireAll = true)
    public static void setIcon(ProcessView processView, int[] colors) {
        processView.setColors(colors);
    }
}
