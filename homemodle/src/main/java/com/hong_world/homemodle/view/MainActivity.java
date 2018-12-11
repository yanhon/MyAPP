package com.hong_world.homemodle.view;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hong_world.common.base.BaseActivity;
import com.hong_world.common.utils.ToastUtils;
import com.hong_world.homemodle.R;
import com.hong_world.library.base.BaseApplication;
import com.hong_world.library.base.BasePresenter;
import com.hong_world.routerlibrary.provider.IHomeProvider;

import java.util.Timer;
import java.util.TimerTask;

@Route(path = IHomeProvider.HOME_ACT_MIAN)
public class MainActivity extends BaseActivity {
    private boolean isExit = false;
    private Timer mTimer;
    private TimerTask mTimerTask;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(getLayoutId());
//        addFragmentToActivity(getSupportFragmentManager()
//                , NewListFragment.getInstance(), R.id.fl);
        loadRootFragment(R.id.fl, HomeFragment.getInstance());
        mTimer = new Timer();
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

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            super.onBackPressedSupport();
        } else {
            if (isExit) {
                BaseApplication.getInstance().closeApp();
                //杀死OA进程
                ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                //杀死当前进程
                android.os.Process.killProcess(android.os.Process.myPid());
            } else {
                isExit = true;
                ToastUtils.showShort("再按一次退出程序");
                mTimerTask = new TimerTask() {
                    @Override
                    public void run() {
                        isExit = false;
                    }
                };
                mTimer.schedule(mTimerTask, 2000);
            }

        }
    }
}
