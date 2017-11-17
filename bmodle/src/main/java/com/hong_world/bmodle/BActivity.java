package com.hong_world.bmodle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

@Route(path = "/B/act")
public class BActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
    }

    public void startHome(View view) {
//        ServiceManager.getInstance().getHomeProvider().sayHello("mike");
        ARouter.getInstance().build("/home/act").navigation();
//      startActivity(new Intent(this, HomeActivity.class));
    }
}
