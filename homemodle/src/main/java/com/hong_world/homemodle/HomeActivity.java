package com.hong_world.homemodle;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hong_world.common.base.BaseActivity;
import com.hong_world.routerlibrary.provider.IBProvider;
import com.hong_world.routerlibrary.provider.IHomeProvider;

@Route(path = IHomeProvider.HOME_ACT_MAIN, group = IHomeProvider.HOME_GROUP)
public class HomeActivity extends BaseActivity {
    @Autowired
    String ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ARouter.getInstance().inject(this);
        Toast.makeText(this, ok, Toast.LENGTH_SHORT).show();
    }

    public void startB(View view) {
        ARouter.getInstance().build(IBProvider.B_ACT_B, IBProvider.B_GROUP).navigation();
    }

}
