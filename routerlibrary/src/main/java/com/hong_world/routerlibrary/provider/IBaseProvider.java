package com.hong_world.routerlibrary.provider;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * Date: 2017/11/17.14:27
 * Author: hong_world
 * Description: 只有第一次被调用的时候才会初始化，之后将不会
 * Version:
 */

public interface IBaseProvider extends IProvider {
    void sayHello(String name);

}
