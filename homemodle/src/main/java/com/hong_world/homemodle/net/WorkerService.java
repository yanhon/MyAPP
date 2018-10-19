package com.hong_world.homemodle.net;

import com.hong_world.common.net.BaseResponse;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.LifeCache;
import io.rx_cache2.ProviderKey;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Date: 2018/5/18. 14:21
 * Author: hong_world
 * Description:
 * Version:
 */

public interface WorkerService {
    /**
     * 登录
     *
     * @param loginReq
     * @return
     */
    @POST("login")
    Observable<BaseResponse<RegisterResp>> login(@Body LoginReq loginReq);

    /**
     * LifeCache设置缓存过期时间. 如果没有设置@LifeCache , 数据将被永久缓存理除非你使用了 EvictProvider,EvictDynamicKey or EvictDynamicKeyGroup .
     *
     * @param user
     * @param userName        驱逐与一个特定的键使用EvictDynamicKey相关的数据。比如分页，排序或筛选要求
     * @param evictDynamicKey 可以明确地清理指定的数据 DynamicKey.
     * @return
     */
    @ProviderKey("getTask")
    @LifeCache(duration = 1, timeUnit = TimeUnit.SECONDS)
    Observable<BaseResponse<RegisterResp>> getUser(Observable user, DynamicKey userName, EvictDynamicKey evictDynamicKey);

}
