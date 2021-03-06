package com.hong_world.kotlin_module.presenter

import com.hong_world.common.ProviderManager
import com.hong_world.common.net.RxBaseObserver
import com.hong_world.library.utils.GsonUtils
import com.hong_world.kotlin_module.bean.FeedArticleListData
import com.hong_world.kotlin_module.contract.WanAndroidContract
import com.hong_world.kotlin_module.modle.TasksDataSource
import com.hong_world.kotlin_module.modle.TasksRepository
import com.hong_world.kotlin_module.modle.local.TasksLocalDataSource
import com.hong_world.kotlin_module.modle.remote.TasksRemoteDataSource

/**
 * Date: 2018/9/21. 10:20
 * Author: hong_world
 * Description:
 * Version:
 */
class WanAndroidPresenter(view: WanAndroidContract.View) : WanAndroidContract.Presenter() {
    private var mTasksRepository: TasksDataSource? = null

    init {
        setmView(view)
        mTasksRepository = TasksRepository.getInstance(TasksRemoteDataSource.getInstance(), TasksLocalDataSource.getInstance())
    }

//    override fun getPageList(mCurrentPage: Int, isRefresh: Boolean) {
//        mTasksRepository?.getFeedArticleList(mCurrentPage)?.subscribe(addDisposable(object : RxBaseObserver<FeedArticleListData>(this) {
//            override fun onSuccess(data: FeedArticleListData) {
//                mView.getPageListSuccess(data, isRefresh)
//            }
//
//            override fun onFail(errorCode: String?, errorMsg: String?) {
//            }
//        }))
//    }

    override fun getPageList(mCurrentPage: Int, isRefresh: Boolean) {
        addDisposable(ProviderManager.bProvider?.getFeedArticleList(mCurrentPage)?.subscribeWith(object : RxBaseObserver<com.hong_world.common.bean.FeedArticleListData>(this) {
            override fun onSuccess(data: com.hong_world.common.bean.FeedArticleListData) {
                mView.getPageListSuccess(GsonUtils.fromGson(GsonUtils.toGson(data),FeedArticleListData::class.java), isRefresh)
            }

            override fun onFail(errorCode: String?, errorMsg: String?) {
            }
        }))
    }
//    override fun getPageList(mCurrentPage: Int, isRefresh: Boolean) {
//        addDisposable(mTasksRepository?.getFeedArticleList(mCurrentPage)?.subscribeWith(object : RxBaseObserver<FeedArticleListData>(this) {
//            override fun onSuccess(data: FeedArticleListData) {
//                mView.getPageListSuccess(data, isRefresh)
//            }
//
//            override fun onFail(errorCode: String?, errorMsg: String?) {
//            }
//        }))
//    }
}