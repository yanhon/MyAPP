package com.hong_world.myapp.view;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hong_world.homemodle.HomeActivity;
import com.hong_world.myapp.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Date: 2017/11/21.13:46
 * Author: hong_world
 * Description: 单元测试（独立运行 or library）
 * Version:
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class HomeActivityScreenTest {
    @Rule
    public IntentsTestRule<HomeActivity> mainActivityIntentsTestRule =
            new IntentsTestRule<>(HomeActivity.class);

    @Before
    public void registerIdlingResource() {//监听是同步还是异步状态
        if (true) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(mainActivityIntentsTestRule.getActivity().getApplication());
        Espresso.registerIdlingResources(
                mainActivityIntentsTestRule.getActivity().getCountingIdlingResource());
    }

    @Test
    public void emptyLogin() {
        onView(withId(R.id.button)).perform(click());
    }

    @After
    public void unregisterIdlingResource() {//注销闲置的资源，防止内存泄漏
        Espresso.unregisterIdlingResources(
                mainActivityIntentsTestRule.getActivity().getCountingIdlingResource());
    }
}