package com.hong_world.kotlin_module.net

import com.hong_world.common.net.BaseResponse
import com.hong_world.kotlin_module.bean.FeedArticleListData
import io.reactivex.Observable
import io.rx_cache2.DynamicKey
import io.rx_cache2.EvictDynamicKey
import io.rx_cache2.LifeCache
import io.rx_cache2.ProviderKey
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

/**
 * Date: 2018/9/26. 10:48
 * Author: hong_world
 * Description:
 * Version:
 */
public interface KotlinModuleService {
    @GET("article/list/{num}/json")
    fun getFeedArticleList(@Path("num") num: Int): Observable<BaseResponse<FeedArticleListData>>

    @ProviderKey("getFeedArticleList2")
    @LifeCache(duration = 5, timeUnit = TimeUnit.SECONDS)
    fun getFeedArticleList(observable: Observable<FeedArticleListData>, userName: DynamicKey, evictDynamicKey: EvictDynamicKey): Observable<FeedArticleListData>
}