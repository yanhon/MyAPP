package com.hong_world.routerlibrary.provider;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * Date: 2017/11/17.14:27
 * Author: hong_world
 * Description: 只有第一次被调用的时候才会初始化，之后将不会,
 * -----------1、往工程中添加非Arouter Nmodle时，依赖本module,并在Nmodle中实现IProvider
 * -----------2、（打开Activity可以通过void openActivity( Bundle bundle);传多值，fragment类似）
 * -----------3、如a需模块调用b模块的api 可以在b模块的provider接口中定如Observable<FeedArticleListData> getFeedArticleList(int num);让A模块调用，
 * -----------如不想将实体抽入common中可将FeedArticleListData转换成jsonString,
 * Version:
 */

public interface IBaseProvider extends IProvider {

    void openActivity(String path, @Nullable Bundle bundle);

    Fragment getFragment(String path, @Nullable Bundle bundle);
}
