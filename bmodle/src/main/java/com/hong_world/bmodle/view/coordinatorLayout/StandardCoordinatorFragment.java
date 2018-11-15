package com.hong_world.bmodle.view.coordinatorLayout;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hong_world.bmodle.R;
import com.hong_world.bmodle.databinding.FragmentStandardCoordinatorBinding;
import com.hong_world.common.bean.MyUserInfoLiveData;
import com.hong_world.library.base.BaseSupportFragment;
import com.orhanobut.logger.Logger;

/**
 * Date: 2018/8/10. 10:34
 * Author: hong_world
 * Description:
 * app:layout_scrollFlags="scroll|snap" 不足一半高度的时候就会回弹，高于一半的时候就会全部隐藏（即要么全部显示，要么全部隐藏）
 * app:layout_scrollFlags="scroll|exitUntilCollapsed" TabLayout不能完全滚出屏幕,当滚入屏幕时，等RecyclerView到达边界的时候才会继续滚入
 * app:layout_scrollFlags="scroll|enterAlways|enterAlwaysCollapsed" 滚入屏幕时，TabLayout会和RecyclerView一起滚入最小高度，然后等RecyclerView滚入到边界的时候再滚入剩下的
 * app:layout_scrollFlags="scroll|enterAlways" 只要往下拉，TabLayout就会显示出来，不必等着RecyclerView到顶部才可以显示
 * app:layout_scrollFlags="scroll" RecyclerView向上滚动的时候，TabLayout也跟着滚动，等到TabLayout滚出屏幕之后RecyclerView还在继续滚动，下拉的时候等到拉到RecyclerView的头部TabLayout才会进入屏幕
 * Version:
 */

public class StandardCoordinatorFragment extends BaseSupportFragment {

    private FragmentStandardCoordinatorBinding viewDataBinding;

    public static StandardCoordinatorFragment getInstance() {
        return new StandardCoordinatorFragment();
    }

    @Override
    protected View onCreateMyView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_standard_coordinator, container, false);
        return viewDataBinding.getRoot();
    }

    @Override
    protected void initDataSource() {

    }

    int i;

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
//        MyUserInfoLiveData.getInstance("wowo").observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                Logger.i("StandardCoordinatorFragment " + s);
//            }
//        });
        viewDataBinding.mainBackdrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUserInfoLiveData.getInstance("wowo" + i++);
            }
        });
        viewDataBinding.setLifecycleOwner(this);
        viewDataBinding.tv01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDataBinding.pv.setArcProgress(95);
                MyUserInfoLiveData.getInstance(null).observe(StandardCoordinatorFragment.this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        Logger.i("StandardCoordinatorFragment " + s);
                    }
                });
            }
        });
        viewDataBinding.setColors(new int[]{Color.BLUE,Color.WHITE});
        viewDataBinding.setArcProcess(50f);
    }
}
