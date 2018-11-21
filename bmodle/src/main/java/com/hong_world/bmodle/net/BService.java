package com.hong_world.bmodle.net;

import com.hong_world.common.bean.FeedArticleListData;
import com.hong_world.common.net.BaseResponse;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.LifeCache;
import io.rx_cache2.ProviderKey;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

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

    @ProviderKey("getFeedArticleList")
    @LifeCache(duration = 60, timeUnit = TimeUnit.SECONDS)
    Observable<FeedArticleListData> getFeedArticleList(Observable observable, DynamicKey userName, EvictDynamicKey evictDynamicKey);

    @Streaming
    @GET()
    Observable<ResponseBody> down(@Header("RANGE") String range, @Url String url);

}
