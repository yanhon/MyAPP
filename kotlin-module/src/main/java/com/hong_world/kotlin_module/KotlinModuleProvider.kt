package com.hong_world.kotlin_module

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.hong_world.routerlibrary.provider.IKotlinModuleProvider
import com.orhanobut.logger.Logger

/**
 * Date: 2018/9/20. 15:18
 * Author: hong_world
 * Description:
 * Version:
 */
@Route(path = IKotlinModuleProvider.KOTLIN_MODULE_SERVICE)
class KotlinModuleProvider : IKotlinModuleProvider {
    private var context: Context? = null
    override fun init(context: Context?) {
        Logger.i("KotlinModuleProvider初始化")
        this.context = context
    }

    override fun sayHello(name: String?) {
    }

    override fun openActivity(path: String, bundle: Bundle?) {
        ARouter.getInstance()
                .build(path)
                .with(bundle)
                .navigation(context, object : NavigationCallback {
                    override fun onFound(postcard: Postcard) {
                        Logger.i("找到了")
                    }

                    override fun onLost(postcard: Postcard) {
                        Logger.i("找不到了")

                    }

                    override fun onArrival(postcard: Postcard) {
                        Logger.i("跳转完了")
                    }

                    override fun onInterrupt(postcard: Postcard) {
                        Logger.i("被拦截了")
                    }
                })
    }

    override fun getFragment(path: String, bundle: Bundle?): Fragment {
        return ARouter.getInstance().build(path).with(bundle).navigation(context, object : NavigationCallback {
            override fun onFound(postcard: Postcard) {
                Logger.i("找到了")
            }

            override fun onLost(postcard: Postcard) {
                Logger.i("找不到了")

            }

            override fun onArrival(postcard: Postcard) {
                Logger.i("跳转完了")
            }

            override fun onInterrupt(postcard: Postcard) {
                Logger.i("被拦截了")
            }
        }) as Fragment
    }
}