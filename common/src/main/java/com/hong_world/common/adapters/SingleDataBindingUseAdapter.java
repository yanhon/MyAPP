package com.hong_world.common.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hong_world.common.BR;
import com.hong_world.common.R;

/**
 * Date: 2018/8/13. 17:58
 * Author: hong_world
 * Description: 1、可简单的布局，2、可实现实体不耦合MultiItemEntity实现多种item的复杂布局
 * Version:
 */
public class SingleDataBindingUseAdapter<T, P> extends BaseQuickAdapter<T, BaseViewHolder> {
    private P presenter;

    public SingleDataBindingUseAdapter(int layoutResId, P presenter) {
        super(layoutResId, null);
        this.presenter = presenter;
    }

    /**
     * 多布局代理, new MultiTypeDelegate
     *
     * @param presenter
     */
    public SingleDataBindingUseAdapter(P presenter) {
        super(null);
        this.presenter = presenter;
//        //Step.1
//        setMultiTypeDelegate(new MultiTypeDelegate<Entity>() {
//            @Override
//            protected int getItemType(Entity entity) {
//                //根据你的实体类来判断布局类型
//                return entity.type;
//            }
//        });
//        //Step.2
//        getMultiTypeDelegate()
//                .registerItemType(Entity.TEXT, R.layout.item_text_view)
//                .registerItemType(Entity.IMG, R.layout.item_image_view);
    }


    @Override
    protected void convert(BaseViewHolder helper, T item) {
        ViewDataBinding binding = getViewDataBinding(helper);
        binding.setVariable(BR.date, item);
        binding.setVariable(BR.presenter, presenter);
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
