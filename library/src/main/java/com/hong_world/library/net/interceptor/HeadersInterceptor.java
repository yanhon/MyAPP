package com.hong_world.library.net.interceptor;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Date: 2018/5/8. 17:18
 * Author: hong_world
 * Description: 请求头拦截
 * Version:
 */
public class HeadersInterceptor implements Interceptor {

    private Map<String, String> headers;

    public HeadersInterceptor(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        if (headers != null && headers.size() > 0) {
            Set<String> keys = headers.keySet();
            for (String headerKey : keys) {
                builder.addHeader(headerKey, headers.get(headerKey)).build();
            }
        }
        return chain.proceed(builder.build());
    }
}
