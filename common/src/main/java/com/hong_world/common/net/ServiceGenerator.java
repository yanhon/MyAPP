package com.hong_world.common.net;

import com.hong_world.common.net.interceptor.TokenInterceptor;
import com.hong_world.library.BuildConfig;
import com.hong_world.library.base.BaseApplication;
import com.hong_world.library.net.HttpsUtils;
import com.hong_world.library.net.cookie.CookieManger;
import com.hong_world.library.net.interceptor.HeadersInterceptor;
import com.hong_world.library.net.interceptor.HttpErrorInterceptor;
import com.hong_world.library.net.interceptor.HttpLogInterceptor;
import com.orhanobut.logger.Logger;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Date: 2018/5/8. 16:46
 * Author: hong_world
 * Description:
 * Version:
 */

public class ServiceGenerator {
    private static HttpsUtils.SSLParams sslParams;

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        //retrofit
        Retrofit.Builder sBuilder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient());

        Retrofit retrofit = sBuilder.build();
        return retrofit.create(serviceClass);
    }

   public static <S> S createService2(Class<S> serviceClass, String baseUrl) {
        //retrofit
        Retrofit.Builder sBuilder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient2());

        Retrofit retrofit = sBuilder.build();
        return retrofit.create(serviceClass);
    }

    private static OkHttpClient getClient2() {
        setCertificates(null, null);
        //okHttpClient
        return new OkHttpClient().newBuilder()
//                .cache(cache)
                //错误重连
                .retryOnConnectionFailure(true)
                //连接超时
                .connectTimeout(30, TimeUnit.SECONDS)
                //读取超时
                .readTimeout(30, TimeUnit.SECONDS)
                .hostnameVerifier(new DefaultHostnameVerifier())//https的全局访问规则
                .addNetworkInterceptor(new HttpLogInterceptor().setLevel(BuildConfig.DEBUG ? HttpLogInterceptor.Level.BODY : HttpLogInterceptor.Level.BASIC))
//                .addInterceptor(new TokenInterceptor())
//                .addNetworkInterceptor(new HeadersInterceptor(null))
                .addInterceptor(new HttpErrorInterceptor())
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
//                .cookieJar(new CookieJar() {
//                    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();
//
//                    @Override
//                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//                        cookieStore.put(HttpUrl.parse(url.host()), cookies);
//                    }
//
//                    @Override
//                    public List<Cookie> loadForRequest(HttpUrl url) {
//                        List<Cookie> cookies = cookieStore.get(HttpUrl.parse(url.host()));
//                        return cookies != null ? cookies : new ArrayList<Cookie>();
//                    }
//                })
                .cookieJar(new CookieManger(BaseApplication.getInstance()))
                .build();
    }
    private static OkHttpClient getClient() {
        setCertificates(null, null);
        //okHttpClient
        return new OkHttpClient().newBuilder()
//                .cache(cache)
                //错误重连
                .retryOnConnectionFailure(true)
                //连接超时
                .connectTimeout(30, TimeUnit.SECONDS)
                //读取超时
                .readTimeout(30, TimeUnit.SECONDS)
                .hostnameVerifier(new DefaultHostnameVerifier())//https的全局访问规则
                .addNetworkInterceptor(new HttpLogInterceptor().setLevel(BuildConfig.DEBUG ? HttpLogInterceptor.Level.BODY : HttpLogInterceptor.Level.BASIC))
                .addInterceptor(new TokenInterceptor())
                .addNetworkInterceptor(new HeadersInterceptor(null))
                .addInterceptor(new HttpErrorInterceptor())
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
//                .cookieJar(new CookieJar() {
//                    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();
//
//                    @Override
//                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//                        cookieStore.put(HttpUrl.parse(url.host()), cookies);
//                    }
//
//                    @Override
//                    public List<Cookie> loadForRequest(HttpUrl url) {
//                        List<Cookie> cookies = cookieStore.get(HttpUrl.parse(url.host()));
//                        return cookies != null ? cookies : new ArrayList<Cookie>();
//                    }
//                })
                .cookieJar(new CookieManger(BaseApplication.getInstance()))
                .build();
    }

    /**
     * https的全局自签名证书 or https双向认证证书
     */
    public static void setCertificates(InputStream bksFile, String password, InputStream... certificates) {
        sslParams = HttpsUtils.getSslSocketFactory(bksFile, password, certificates);
    }

    /**
     * 此类是用于主机名验证的基接口。 在握手期间，如果 URL 的主机名和服务器的标识主机名不匹配，
     * 则验证机制可以回调此接口的实现程序来确定是否应该允许此连接。策略可以是基于证书的或依赖于其他验证方案。
     * 当验证 URL 主机名使用的默认规则失败时使用这些回调。如果主机名是可接受的，则返回 true
     */
    public static class DefaultHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    public static class UnSafeHostnameVerifier implements HostnameVerifier {
        private String host;

        public UnSafeHostnameVerifier(String host) {
            this.host = host;
            Logger.i("###############　UnSafeHostnameVerifier " + host);
        }

        @Override
        public boolean verify(String hostname, SSLSession session) {
            Logger.i("############### verify " + hostname + " " + this.host);
            if (this.host == null || "".equals(this.host) || !this.host.contains(hostname))
                return false;
            return true;
        }
    }
}
