package com.hong_world.homemodle.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hong_world.common.base.BaseActivity;
import com.hong_world.homemodle.R;
import com.hong_world.library.base.BasePresenter;
import com.hong_world.routerlibrary.provider.IHomeProvider;

import me.yokeyword.fragmentation.ISupportFragment;

@Route(path = IHomeProvider.HOME_ACT_WEB, group = IHomeProvider.HOME_GROUP)
public class MyWebActivity extends BaseActivity {
    @Autowired
    String urls;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(getLayoutId());
//        addFragmentToActivity(getSupportFragmentManager()
//                , NewListFragment.getInstance(), R.id.fl);
        loadRootFragment(R.id.fl, (ISupportFragment) ARouter.getInstance().build(IHomeProvider.HOME_FRG_WEB).withString("urls", urls).navigation());

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public String title() {
        return null;
    }

}
