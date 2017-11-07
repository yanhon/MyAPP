package com.hong_world.myapp.view;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

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
 * Description:
 * Version:
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
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
        onView(withId(R.id.editText)).perform(typeText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.editText2)).perform(typeText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());
    }

    @Test
    public void emptyLogin2() {
        onView(withId(R.id.editText)).perform(typeText("11"), closeSoftKeyboard());
        onView(withId(R.id.editText2)).perform(typeText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());
    }

    @After
    public void unregisterIdlingResource() {//注销闲置的资源，防止内存泄漏
        Espresso.unregisterIdlingResources(
                mainActivityIntentsTestRule.getActivity().getCountingIdlingResource());
    }
}