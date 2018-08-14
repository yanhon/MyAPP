package com.hong_world.common.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hong_world.common.R;

import java.util.List;

/**
 * Date: 2018/8/14. 10:26
 * Author: hong_world
 * Description:
 * Version:
 */
public class MultiItemDataBindingUseAdapter<T extends MultiItemEntity> extends BaseMultiItemQuickAdapter<T, BaseViewHolder> {

    public MultiItemDataBindingUseAdapter() {
        super(null);
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        ViewDataBinding binding = getViewDataBinding(helper);
        convert(helper, item, binding);
    }

    public ViewDataBinding getViewDataBinding(BaseViewHolder helper) {
        return (ViewDataBinding) helper.itemView.getTag(R.id.BaseQuickAdapter_databinding_support);
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
        return view;
    }

    public void convert(BaseViewHolder helper, T item, ViewDataBinding viewDataBinding) {

    }
}
