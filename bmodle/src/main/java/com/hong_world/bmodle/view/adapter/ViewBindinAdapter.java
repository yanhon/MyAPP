package com.hong_world.bmodle.view.adapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.hong_world.bmodle.R;
import com.hong_world.library.iloader.GlideApp;

/**
 * Date: 2018/8/15. 11:51
 * Author: hong_world
 * Description:
 * Version:
 */
public class ViewBindinAdapter {

    @BindingAdapter("setIcon")
    public static void setIcon(ImageView imageView, int id) {
        if (id % 2 == 0)
            GlideApp.with(imageView.getContext())
                    .load("https://avatar.csdn.net/B/6/6/3_suyimin2010.jpg")
                    .placeholder(R.mipmap.ic_launcher)
                    .into(imageView);
        else
            GlideApp.with(imageView.getContext())
                    .load("https://bpic.588ku.com/element_water_img/18/07/25/8d02208ced2a8bf180c24ddfbd9c92ac.jpg")
                    .placeholder(R.mipmap.ic_launcher)
                    .into(imageView);

    }
}
