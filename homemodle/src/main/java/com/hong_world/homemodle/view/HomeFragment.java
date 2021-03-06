package com.hong_world.homemodle.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hong_world.common.ProviderManager;
import com.hong_world.common.base.BaseFragment;
import com.hong_world.homemodle.R;
import com.hong_world.homemodle.contract.HomeContract;
import com.hong_world.homemodle.databinding.FragmentHomeBinding;
import com.hong_world.homemodle.presenter.HomePresenter;
import com.hong_world.routerlibrary.provider.IBProvider;
import com.hong_world.routerlibrary.provider.IHomeProvider;
import com.hong_world.routerlibrary.provider.IKotlinModuleProvider;

import me.yokeyword.fragmentation.ISupportFragment;

/**
 * Date: 2018/8/7. 16:55
 * Author: hong_world
 * Description:
 * Version:
 */
@Route(path = IHomeProvider.HOME_FRG_HOME)
public class HomeFragment extends BaseFragment<HomePresenter, FragmentHomeBinding> implements HomeContract.View {
    private ISupportFragment[] mFragments = new ISupportFragment[4];
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOUR = 3;

    public int selectNum = FIRST;

    public static HomeFragment getInstance() {
        return new HomeFragment();
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            mFragments[FIRST] = (ISupportFragment) ProviderManager.getKotlinProvider().getFragment(IKotlinModuleProvider.KOTLIN_MODULE_FRG_WAN_ANDROID, null);
//            mFragments[SECOND] = (ISupportFragment) ProviderManager.getKotlinProvider().getFragment(IKotlinModuleProvider.KOTLIN_MODULE_FRG_WAN_ANDROID, null);
            mFragments[SECOND] = (ISupportFragment) ProviderManager.getBProvider().getFragment(IBProvider.B_FRG_MAIN_PAGER, null);
            mFragments[THIRD] = (ISupportFragment) ProviderManager.getHomeProvider().getFragment(IHomeProvider.HOME_FRG_NEW_LIST, null);
            mFragments[FOUR] = (ISupportFragment) ProviderManager.getHomeProvider().getFragment(IHomeProvider.HOME_FRG_MAIN,null);
            if (mFragments[FIRST] != null)
                loadMultipleRootFragment(R.id.frameLayout, FIRST,
                        mFragments
                );

        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用,也可以通过getChildFragmentManager.getFragments()自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[FIRST] = findChildFragment(((ISupportFragment) ProviderManager.getKotlinProvider().getFragment(IKotlinModuleProvider.KOTLIN_MODULE_FRG_WAN_ANDROID, null)).getClass());
//            mFragments[SECOND] = findChildFragment(((ISupportFragment) ProviderManager.getKotlinProvider().getFragment(IKotlinModuleProvider.KOTLIN_MODULE_FRG_WAN_ANDROID, null)).getClass());
            mFragments[SECOND] = findChildFragment(((ISupportFragment) ProviderManager.getBProvider().getFragment(IBProvider.B_FRG_MAIN_PAGER, null)).getClass());
            mFragments[THIRD] = findChildFragment(((ISupportFragment)ProviderManager.getHomeProvider().getFragment(IHomeProvider.HOME_FRG_NEW_LIST, null)).getClass());
            mFragments[FOUR] = findChildFragment(((ISupportFragment) ProviderManager.getHomeProvider().getFragment(IHomeProvider.HOME_FRG_MAIN,null)).getClass());
        }
//        ProviderManager.getBProvider().openActivity(IBProvider.B_ACT_B, null);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBottomNavigationView();
    }

    private void initBottomNavigationView() {
        mBinding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int i = item.getItemId();
                ProviderManager.getKotlinProvider();
                if (mFragments[FIRST] != null)
                    if (i == R.id.tab_navigation) {
                        showHideFragment(mFragments[FIRST], mFragments[selectNum]);
                        selectNum = FIRST;
                    } else if (i == R.id.tab_knowledge_hierarchy) {
                        showHideFragment(mFragments[SECOND], mFragments[selectNum]);
                        selectNum = SECOND;
                    } else if (i == R.id.tab_project) {
                        showHideFragment(mFragments[THIRD], mFragments[selectNum]);
                        selectNum = THIRD;
                    } else if (i == R.id.tab_main_pager) {
                        showHideFragment(mFragments[FOUR], mFragments[selectNum]);
                        selectNum = FOUR;
                    }
                return true;
            }
        });
    }
}
