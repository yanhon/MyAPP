package com.hong_world.homemodle.modle.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.chad.library.adapter.base.entity.IExpandable;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hong_world.homemodle.BR;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2018/7/31. 15:03
 * Author: hong_world
 * Description:
 * Version:
 */

public class Level0Item extends BaseObservable implements MultiItemEntity, IExpandable<Level1Item> {
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

    public Level0Item(int itemType, String text) {
        this.itemType = itemType;
        this.text = text;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    protected boolean mExpandable = false;

    protected List<Level1Item> mSubItems;

    @Override
    public boolean isExpanded() {
        return mExpandable;
    }

    @Override
    public void setExpanded(boolean expanded) {
        mExpandable = expanded;
    }

    @Override
    public List<Level1Item> getSubItems() {
        return mSubItems;
    }

    public boolean hasSubItem() {
        return mSubItems != null && mSubItems.size() > 0;
    }

    public void setSubItems(List<Level1Item> list) {
        mSubItems = list;
    }

    public Level1Item getSubItem(int position) {
        if (hasSubItem() && position < mSubItems.size()) {
            return mSubItems.get(position);
        } else {
            return null;
        }
    }

    public int getSubItemPosition(Level1Item subItem) {
        return mSubItems != null ? mSubItems.indexOf(subItem) : -1;
    }

    public void addSubItem(Level1Item subItem) {
        if (mSubItems == null) {
            mSubItems = new ArrayList<>();
        }
        mSubItems.add(subItem);
    }

    public void addSubItem(int position, Level1Item subItem) {
        if (mSubItems != null && position >= 0 && position < mSubItems.size()) {
            mSubItems.add(position, subItem);
        } else {
            addSubItem(subItem);
        }
    }

    public boolean contains(Level1Item subItem) {
        return mSubItems != null && mSubItems.contains(subItem);
    }

    public boolean removeSubItem(Level1Item subItem) {
        return mSubItems != null && mSubItems.remove(subItem);
    }

    public boolean removeSubItem(int position) {
        if (mSubItems != null && position >= 0 && position < mSubItems.size()) {
            mSubItems.remove(position);
            return true;
        }
        return false;
    }
}
