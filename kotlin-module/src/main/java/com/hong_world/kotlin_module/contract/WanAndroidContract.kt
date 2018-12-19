package com.hong_world.kotlin_module.contract

import com.hong_world.common.base.BaseNormalPresenter
import com.hong_world.kotlin_module.bean.FeedArticleListData
import com.hong_world.library.base.BaseView

/**
 * Date: 2018/9/21. 10:10
 * Author: hong_world
 * Description:
 * Version:
 */
interface WanAndroidContract {
    interface View : BaseView {
        abstract fun getPageListSuccess(data: FeedArticleListData, isRefresh: Boolean)
    }

    abstract class Presenter : BaseNormalPresenter<View>() {
        abstract fun getPageList(mCurrentPage: Int, isRefresh: Boolean)
    }
}