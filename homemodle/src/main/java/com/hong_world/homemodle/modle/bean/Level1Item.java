package com.hong_world.homemodle.modle.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.chad.library.adapter.base.entity.IExpandable;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hong_world.homemodle.BR;

/**
 * Date: 2018/7/31. 15:03
 * Author: hong_world
 * Description:
 * Version:
 */

public class Level1Item extends BaseObservable implements MultiItemEntity {
    private int itemType;
    private String text;
    private boolean select;

    @Bindable
    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
        notifyPropertyChanged(BR.select);
    }

    @Bindable
    public String getText() {
        return text == null ? "" : text;
    }

    public void setText(String text) {
        this.text = text;
        notifyPropertyChanged(BR.text);
    }

    public Level1Item(int itemType, String text) {
        this.itemType = itemType;
        this.text = text;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
