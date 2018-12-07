package com.hong_world.kotlin_module.modle.remote

import com.hong_world.common.net.MyHttp
import com.hong_world.common.net.ServiceGenerator
import com.hong_world.kotlin_module.bean.FeedArticleListData
import com.hong_world.kotlin_module.modle.BaseTasksDataSource
import com.hong_world.kotlin_module.net.KotlinModuleService
import com.hong_world.library.base.BaseApplication
import io.reactivex.Observable
import io.rx_cache2.DynamicKey
import io.rx_cache2.EvictDynamicKey
import io.rx_cache2.internal.RxCache
import io.victoralbertos.jolyglot.GsonSpeaker

/**
 * Date: 2018/9/25. 16:27
 * Author: hong_world
 * Description:
 * Version:
 */
class TasksRemoteDataSource : BaseTasksDataSource {

    var kotlinModuleService: KotlinModuleService = ServiceGenerator.createService(KotlinModuleService::class.java, "http://www.wanandroid.com/")
    var kotlinModuleServiceProviders: KotlinModuleService = RxCache.Builder().persistence(BaseApplication.getInstance().externalCacheDir, GsonSpeaker())
            .using(KotlinModuleService::class.java)

    private constructor()

    companion object {
        @JvmStatic
        fun getInstance() = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = TasksRemoteDataSource()
    }

    override fun getFeedArticleList(num: Int): Observable<FeedArticleListData>? = kotlinModuleServiceProviders.getFeedArticleList(MyHttp.toBaseResponseSubscribe<FeedArticleListData>(kotlinModuleService.getFeedArticleList(num)), DynamicKey(num), EvictDynamicKey(false))
}