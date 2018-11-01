package com.hong_world.homemodle;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.hong_world.common.ProviderManager;
import com.hong_world.common.base.BaseActivity;
import com.hong_world.library.base.BasePresenter;
import com.hong_world.routerlibrary.provider.IBProvider;
import com.hong_world.routerlibrary.provider.IHomeProvider;

@Route(path = IHomeProvider.HOME_ACT_MAIN)
public class HomeActivity extends BaseActivity {
    @Autowired
    String ok;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(getLayoutId());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    public void startB(View view) {
        ProviderManager.getInstance().getBProvider().openActivity(IBProvider.B_ACT_B, null);
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
