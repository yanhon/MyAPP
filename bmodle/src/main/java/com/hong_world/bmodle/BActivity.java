package com.hong_world.bmodle;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hong_world.common.base.BaseActivity;
import com.hong_world.library.base.BasePresenter;
import com.hong_world.routerlibrary.ServiceManager;
import com.hong_world.routerlibrary.provider.IBProvider;

@Route(path = IBProvider.B_ACT_B, group = IBProvider.B_GROUP)
public class BActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(getLayoutId());
        ServiceManager.getInstance().getHomeProvider().sayHello("我shiB");
        String s = Injection.s;
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
        ServiceManager.getInstance().getHomeProvider().openActivity(bundle);
    }

    @Override
    public BasePresenter getPresenter() {
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
