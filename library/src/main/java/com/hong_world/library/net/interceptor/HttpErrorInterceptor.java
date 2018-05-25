package com.hong_world.library.net.interceptor;

import com.hong_world.library.net.exception.HttpStatusException;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.net.URLDecoder;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Date: 2018/5/8. 18:15
 * Author: hong_world
 * Description:
 * Version:
 */

public class HttpErrorInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());  //如果401了，会先执行TokenAuthenticator
        Logger.e("HttpErrorInterceptor request url " + response.request().url());
        Logger.e("HttpErrorInterceptor  response code " + response.code());

        HttpStatusException interceptorException;
        if (response.code() == 404) {
//            String msg = URLDecoder.decode(response.header("custom_message"), "utf-8");
            interceptorException = new HttpStatusException(404, "404");
            throw interceptorException;
        } else if (response.code() == 401) {
            interceptorException = new HttpStatusException(401, "401");
            throw interceptorException;
        }
        return response;
    }
}