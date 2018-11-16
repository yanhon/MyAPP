package com.hong_world.common.widget;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.SeekBar;

import com.orhanobut.logger.Logger;

/**
 * Date: 2018/11/5. 17:54
 * Author: hong_world
 * Description:
 * Version:
 */
public class PhilSeekBar extends AppCompatSeekBar {
    private static InverseBindingListener mInverseBindingListener;

    public PhilSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @BindingAdapter(value = "app:philprogress", requireAll = false)
    public static void setPhilProgress(PhilSeekBar seekBar, int progress) {
        Logger.i("philprogress ：setPhilProgress");
        if (getPhilProgress2(seekBar) != progress) {
            seekBar.setProgress(progress);
        }
    }

    @InverseBindingAdapter(attribute = "app:philprogress", event = "app:philprogressAttrChanged")
    public static int getPhilProgress2(PhilSeekBar seekBar) {
        Logger.i("philprogress ：getPhilProgress2");
        return seekBar.getProgress();
    }

    @BindingAdapter(value = {"app:philprogressAttrChanged"}, requireAll = false)
    public static void setPhilProgressAttrChanged(PhilSeekBar seekBar, final InverseBindingListener inverseBindingListener) {
        Logger.i("philprogress ：setPhilProgressAttrChanged");
        if (inverseBindingListener == null) {
            Log.e("错误！", "InverseBindingListener为空!");
        } else {
            seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    //触发反向数据的传递
                    inverseBindingListener.onChange();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }
    }
}

