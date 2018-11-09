package com.hong_world.bmodle;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hong_world.bmodle.view.coordinatorLayout.StandardCoordinatorFragment;
import com.hong_world.common.ProviderManager;
import com.hong_world.common.base.BaseActivity;
import com.hong_world.common.bean.MyUserInfoLiveData;
import com.hong_world.library.base.BasePresenter;
import com.hong_world.routerlibrary.provider.IBProvider;
import com.orhanobut.logger.Logger;

@Route(path = IBProvider.B_ACT_B)
public class BActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(getLayoutId());
        ProviderManager.getInstance().getHomeProvider().sayHello("我shiB");
        String s = Injection.s;
        loadRootFragment(R.id.fl, StandardCoordinatorFragment.getInstance());
        MyUserInfoLiveData.getInstance(null).observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Logger.i("BActivity " + s);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_b;
    }

    public void startHomes(View view) {
//        ARouter.getInstance().build(IHomeProvider.HOME_ACT_MAIN).navigation();
//      startActivity(new Intent(this, HomeActivity.class));
        Bundle bundle = new Bundle();
        bundle.putString("ok", "123");
        ProviderManager.getInstance().getHomeProvider().openActivity(bundle);
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public String title() {
        return null;
    }

    @Override
    public void onClick(View v) {
        int di = v.getId();
        if (di == R.id.button2) {
            setFullScrrenNoText();
        } else if (di == R.id.button3) {
            setToolBarAndStatusBarSameColor(R.color.colorAccent);
        } else if (di == R.id.button4) {
            setFullScrrenHaveText();
        } else if (di == R.id.button5) {
            setChangeStatusBarColor(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}
