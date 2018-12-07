package com.hong_world.myapp.modle.view;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.hong_world.homemodle.view.MainActivity;
import com.hong_world.myapp.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Date: 2017/11/7.9:41
 * Author: hong_world
 * Description: Espresso https://blog.csdn.net/fei20121106/article/details/70308193#2214-pressback%E5%90%8E%E9%80%80%E9%94%AE-%E7%82%B9%E5%87%BB
 * Version:
 */
@RunWith(AndroidJUnit4.class)
public class MainFragmentScreenTest {
    @Rule
    public IntentsTestRule<MainActivity> mainActivityIntentsTestRule =
            new IntentsTestRule<>(MainActivity.class);

    @Before
    public void registerIdlingResource() {//监听是同步还是异步状态
        Espresso.registerIdlingResources(
                mainActivityIntentsTestRule.getActivity().getCountingIdlingResource());
    }

    @Test
    public void emptyLogin() {
        onView(withId(R.id.tab_main_pager)).perform(click());
        onView(withId(R.id.editText)).perform(typeText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.editText2)).perform(typeText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.mbutton)).perform(click());
    }

    @Test
    public void emptyLogin2() {
        onView(withId(R.id.tab_main_pager)).perform(click());
        onView(withId(R.id.editText)).perform(typeText("135"), closeSoftKeyboard());
        onView(withId(R.id.editText2)).perform(typeText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.mbutton)).perform(click());
    }

    @After
    public void unregisterIdlingResource() {//注销闲置的资源，防止内存泄漏
        Espresso.unregisterIdlingResources(
                mainActivityIntentsTestRule.getActivity().getCountingIdlingResource());
    }
}