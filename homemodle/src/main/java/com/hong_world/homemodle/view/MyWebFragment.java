package com.hong_world.homemodle.view;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.hong_world.common.base.BaseWebViewFragment;
import com.hong_world.homemodle.R;
import com.hong_world.homemodle.contract.HomeContract;
import com.hong_world.homemodle.databinding.FragmentHomeBinding;
import com.hong_world.homemodle.presenter.HomePresenter;
import com.hong_world.routerlibrary.provider.IHomeProvider;

import me.yokeyword.fragmentation.ISupportFragment;

/**
 * Date: 2018/8/7. 16:55
 * Author: hong_world
 * Description:
 * Version:
 */
@Route(path = IHomeProvider.HOME_FRG_WEB)
public class MyWebFragment extends BaseWebViewFragment<HomePresenter, FragmentHomeBinding> implements HomeContract.View<HomePresenter> {
    private ISupportFragment[] mFragments = new ISupportFragment[4];
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOUR = 3;
    @Autowired
    String urls;
    public int selectNum = FIRST;

    public static MyWebFragment getInstance() {
        return new MyWebFragment();
    }

    @Override
    protected boolean enableRefresh() {
        return false;
    }

    @Override
    protected boolean needTopBar() {
        return false;
    }

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public String title() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        super.initViews(view, savedInstanceState);
        mBinding.bottomNavigationView.setVisibility(View.GONE);
        initWebView(mBinding.frameLayout, urls);
    }
}
