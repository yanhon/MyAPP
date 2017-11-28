package com.hong_world.homemodle.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hong_world.common.base.BaseActivity;
import com.hong_world.homemodle.R;
import com.hong_world.routerlibrary.provider.IHomeProvider;

@Route(path = IHomeProvider.HOME_ACT_MIAN,group = IHomeProvider.HOME_GROUP)
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragmentToActivity(getSupportFragmentManager()
                , MainFragment.getInstance(), R.id.fl);
    }

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

}
