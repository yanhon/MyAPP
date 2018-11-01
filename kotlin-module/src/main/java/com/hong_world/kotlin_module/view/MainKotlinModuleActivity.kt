package com.hong_world.kotlin_module.view

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.hong_world.common.ProviderManager
import com.hong_world.common.base.BaseActivity
import com.hong_world.kotlin_module.R
import com.hong_world.library.base.BasePresenter
import com.hong_world.library.base.BaseView
import com.hong_world.routerlibrary.provider.IBProvider
import com.hong_world.routerlibrary.provider.IKotlinModuleProvider
import me.yokeyword.fragmentation.ISupportFragment

@Route(path = IKotlinModuleProvider.KOTLIN_MODULE_ACT_MAIN)
class MainKotlinModuleActivity : BaseActivity<BasePresenter<*>>() {

    override fun createPresenter(): BasePresenter<BaseView<*>>? = null

    override fun getLayoutId(): Int = R.layout.activity_main_kotlin_module

    override fun title(): String? = null

    override fun initViews(savedInstanceState: Bundle?) {
        super.initViews(savedInstanceState)
        loadRootFragment(R.id.fl, ProviderManager.getInstance().kotlinProvider.getFragment(IKotlinModuleProvider.KOTLIN_MODULE_FRG_WAN_ANDROID, null) as ISupportFragment)
    }

    fun openBModule(v: View) {
        ProviderManager.getInstance().bProvider.openActivity(IBProvider.B_ACT_B, null)
    }
}
