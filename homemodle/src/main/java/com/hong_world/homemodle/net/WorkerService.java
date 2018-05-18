package com.hong_world.homemodle.net;

import com.hong_world.common.net.BaseResponse;

import io.reactivex.Observable;
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
}
