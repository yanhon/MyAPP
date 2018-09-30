package com.hong_world.kotlin_module.view.adapter

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.hong_world.kotlin_module.R
import com.hong_world.library.iloader.GlideApp

/**
 * Date: 2018/9/26. 15:25
 * Author: hong_world
 * Description:
 * Version:
 */
class ViewBindinAdapter {
    companion object {

        @BindingAdapter("setIcon")
        @JvmStatic
        fun setIcon(imageView: ImageView, id: Int) {
            if (id % 2 == 0)
                GlideApp.with(imageView.context)
                        .load("https://avatar.csdn.net/B/6/6/3_suyimin2010.jpg")
                        .placeholder(R.mipmap.ic_launcher)
                        .into(imageView)
            else
                GlideApp.with(imageView.context)
                        .load("https://bpic.588ku.com/element_water_img/18/07/25/8d02208ced2a8bf180c24ddfbd9c92ac.jpg")
                        .placeholder(R.mipmap.ic_launcher)
                        .into(imageView)
        }
    }


}