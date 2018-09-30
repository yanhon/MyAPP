package com.hong_world.kotlin_module

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.hong_world.routerlibrary.provider.IKotlinModuleProvider
import com.orhanobut.logger.Logger

/**
 * Date: 2018/9/20. 15:18
 * Author: hong_world
 * Description:
 * Version:
 */
@Route(path = IKotlinModuleProvider.KOTLIN_MODULE_SERVICE, group = IKotlinModuleProvider.KOTLIN_MODULE_GROUP)
class KotlinModuleProvider : IKotlinModuleProvider {
    override fun init(context: Context?) {
        Logger.i("KotlinModuleProvider初始化")
    }

    override fun sayHello(name: String?) {
    }
}