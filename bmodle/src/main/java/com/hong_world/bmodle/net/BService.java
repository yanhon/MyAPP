package com.hong_world.bmodle.net;

import com.hong_world.bmodle.bean.FeedArticleListData;
import com.hong_world.common.net.BaseResponse;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.LifeCache;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Date: 2018/8/13. 11:05
 * Author: hong_world
 * Description:
 * Version:
 */
public interface BService {
    /**
     * 获取feed文章列表
     *
     * @param num 页数
     * @return feed文章列表数据
     */
    @GET("article/list/{num}/json")
    Observable<BaseResponse<FeedArticleListData>> getFeedArticleList(@Path("num") int num);

    @LifeCache(duration = 15, timeUnit = TimeUnit.SECONDS)
    Observable<BaseResponse<FeedArticleListData>> getFeedArticleList(Observable observable, DynamicKey userName, EvictDynamicKey evictDynamicKey);

}
