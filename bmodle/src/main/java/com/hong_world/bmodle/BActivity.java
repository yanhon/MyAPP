package com.hong_world.bmodle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hong_world.routerlibrary.ServiceManager;
import com.hong_world.routerlibrary.provider.IBProvider;

@Route(path = IBProvider.B_ACT_B, group = IBProvider.B_GROUP)
public class BActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        ServiceManager.getInstance().getHomeProvider().sayHello("我shiB");
    }

    public void startHomes(View view) {
//        ARouter.getInstance().build(IHomeProvider.HOME_ACT_MAIN).navigation();
//      startActivity(new Intent(this, HomeActivity.class));
        Bundle bundle = new Bundle();
        bundle.putString("ok", "123");
        ServiceManager.getInstance().getHomeProvider().openActivity(bundle);
    }
}
