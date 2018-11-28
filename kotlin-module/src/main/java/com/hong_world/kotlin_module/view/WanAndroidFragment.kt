package com.hong_world.kotlin_module.view

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.hong_world.common.adapters.SingleDataBindingUseAdapter
import com.hong_world.common.base.BaseFragment
import com.hong_world.common.utils.StatusBarUtil
import com.hong_world.kotlin_module.R
import com.hong_world.kotlin_module.bean.FeedArticleData
import com.hong_world.kotlin_module.bean.FeedArticleListData
import com.hong_world.kotlin_module.contract.WanAndroidContract
import com.hong_world.kotlin_module.databinding.FragmentWanAndroidBinding
import com.hong_world.kotlin_module.presenter.WanAndroidPresenter
import com.hong_world.routerlibrary.provider.IKotlinModuleProvider
import kotlinx.android.synthetic.main.fragment_wan_android.*
import java.util.*

/**
 * Date: 2018/9/21. 10:22
 * Author: hong_world
 * Description:
 * Version:
 */
@Route(path = IKotlinModuleProvider.KOTLIN_MODULE_FRG_WAN_ANDROID)
class WanAndroidFragment : BaseFragment<WanAndroidPresenter, FragmentWanAndroidBinding>(), WanAndroidContract.View<WanAndroidPresenter> {
    lateinit var mAdapter: SingleDataBindingUseAdapter<FeedArticleData, *>
    var mCurrentPage: Int = 0
    override fun createPresenter(): WanAndroidPresenter = WanAndroidPresenter(this)

    override fun title(): String = "首页-kotlin"

    override fun getLayoutId(): Int = R.layout.fragment_wan_android

    override fun onSupportVisible() {
        super.onSupportVisible()
        StatusBarUtil.setColor(_mActivity, ContextCompat.getColor(mActivity, R.color.colorPrimaryDark), 50)
    }

    override fun initViews(view: View?, savedInstanceState: Bundle?) {
        super.initViews(view, savedInstanceState)
        getSmartRefreshLayout().setEnableOverScrollDrag(true)
        mBinding.presenter = mPresenter
        mAdapter = SingleDataBindingUseAdapter<FeedArticleData, Any>(R.layout.item_wan_android, mPresenter)
        rv.adapter = mAdapter
        rv.layoutManager = LinearLayoutManager(this.context)
        mAdapter.setOnLoadMoreListener({
            getSmartRefreshLayout().setEnableRefresh(false)
            mPresenter.getPageList(mCurrentPage, false)
        }, rv)
        mPresenter?.getPageList(mCurrentPage, true)
    }

    override fun onRefresh() {
        super.onRefresh()
        mCurrentPage = 0
        mAdapter.loadMoreEnd()
        mAdapter.setEnableLoadMore(false)
        mPresenter.getPageList(mCurrentPage, true)
    }

    override fun onDataNotAvailable(type: String?, msg: String?) {
        super.onDataNotAvailable(type, msg)
        mAdapter.setEnableLoadMore(true)
        getSmartRefreshLayout().setEnableRefresh(true)
    }

    override fun getPageListSuccess(data: FeedArticleListData, isRefresh: Boolean) {
        mCurrentPage++
        if (isRefresh) {
            mAdapter.replaceData(data.datas as ArrayList<FeedArticleData>)
            mAdapter.setEnableLoadMore(true)
        } else {
            if (data.isOver) {
                mAdapter.loadMoreEnd()
            } else {
                mAdapter.loadMoreComplete()
            }
            mAdapter.addData(data.datas as ArrayList<FeedArticleData>)
            getSmartRefreshLayout().setEnableRefresh(true)
        }
    }
}