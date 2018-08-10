package com.hong_world.homemodle.modle.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2018/7/31. 11:21
 * Author: hong_world
 * Description:
 * Version:
 */

public class MultipleItem implements MultiItemEntity {
    public static final int TEXT = 1;
    public static final int IMG_TEXT = 3;
    private String text;
    private int itemType;
    private List<BeanItem> beanItemList;
    public int scrollOffset;
    public int scrollPosition;

    public int getScrollOffset() {
        return scrollOffset;
    }

    public void setScrollOffset(int scrollOffset) {
        this.scrollOffset = scrollOffset;
    }

    public int getScrollPosition() {
        return scrollPosition;
    }

    public void setScrollPosition(int scrollPosition) {
        this.scrollPosition = scrollPosition;
    }

    public List<BeanItem> getBeanItemList() {
        if (beanItemList == null) {
            return new ArrayList<>();
        }
        return beanItemList;
    }

    public void setBeanItemList(List<BeanItem> beanItemList) {
        this.beanItemList = beanItemList;
    }

    public MultipleItem(String text, int itemType) {
        this.text = text;
        this.itemType = itemType;
    }

    public MultipleItem(String text, int itemType, List<BeanItem> beanItemList) {
        this.text = text;
        this.itemType = itemType;
        this.beanItemList = beanItemList;
    }

    public String getText() {
        return text == null ? "" : text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
