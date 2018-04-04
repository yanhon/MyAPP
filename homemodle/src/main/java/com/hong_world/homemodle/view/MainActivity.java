package com.hong_world.homemodle.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hong_world.common.base.BaseActivity;
import com.hong_world.homemodle.R;
import com.hong_world.library.base.BasePresenter;
import com.hong_world.routerlibrary.provider.IHomeProvider;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

@Route(path = IHomeProvider.HOME_ACT_MIAN, group = IHomeProvider.HOME_GROUP)
public class MainActivity extends BaseActivity {
    @Inject
    @Nullable
    String msg;

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(getLayoutId());
//        AndroidInjection.inject(this);
        addFragmentToActivity(getSupportFragmentManager()
                , MainFragment.getInstance(), R.id.fl);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public String title() {
        return null;
    }
}
