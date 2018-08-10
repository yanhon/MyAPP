package com.hong_world.homemodle.modle.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hong_world.homemodle.BR;

/**
 * Date: 2018/7/31. 15:03
 * Author: hong_world
 * Description:
 * Version:
 */

public class BeanItem extends BaseObservable {
    private String text;
    private boolean select;
    public int scrollOffset;
    public int scrollPosition;

    public BeanItem(String text) {
        this.text = text;
    }

    @Bindable
    public int getScrollOffset() {
        return scrollOffset;
    }

    public void setScrollOffset(int scrollOffset) {
        this.scrollOffset = scrollOffset;
        notifyPropertyChanged(BR.scrollOffset);
    }

    @Bindable
    public int getScrollPosition() {
        return scrollPosition;
    }

    public void setScrollPosition(int scrollPosition) {
        this.scrollPosition = scrollPosition;
        notifyPropertyChanged(BR.scrollPosition);

    }

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

}
