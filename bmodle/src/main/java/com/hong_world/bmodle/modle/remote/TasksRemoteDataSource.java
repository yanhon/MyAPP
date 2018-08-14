package com.hong_world.bmodle.modle.remote;

import com.hong_world.bmodle.modle.TasksDataSource;
import com.hong_world.bmodle.net.BService;
import com.hong_world.common.net.MyHttp;
import com.hong_world.common.net.ServiceGenerator;
import com.hong_world.common.utils.NetworkUtils;
import com.hong_world.library.base.BaseApplication;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;

/**
 * Date: 2018/8/13. 10:44
 * Author: hong_world
 * Description:
 * Version:
 */
public class TasksRemoteDataSource implements TasksDataSource {
    private static TasksRemoteDataSource INSTANCE;
    private static BService bService;
    private static BService bServiceProviders;

    private TasksRemoteDataSource() {
    }

    public static TasksRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TasksRemoteDataSource();
            bService = ServiceGenerator.createService(BService.class, "http://www.wanandroid.com/");
            bServiceProviders = new RxCache.Builder()
                    .persistence(BaseApplication.getInstance().getExternalCacheDir(), new GsonSpeaker())
                    .using(BService.class);
        }
        return INSTANCE;
    }

    @Override
    public Observable getFeedArticleList(int num) {
        return bServiceProviders.getFeedArticleList( MyHttp.toBaseResponseSubscribe(bService.getFeedArticleList(num)),new DynamicKey(num),new EvictDynamicKey(false));
    }
}