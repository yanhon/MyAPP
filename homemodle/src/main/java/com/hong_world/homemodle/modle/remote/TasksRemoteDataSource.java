package com.hong_world.homemodle.modle.remote;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.hong_world.common.BuildConfig;
import com.hong_world.common.bean.Task;
import com.hong_world.common.net.BaseResponse;
import com.hong_world.common.net.MyHttp;
import com.hong_world.common.net.MySubscribe;
import com.hong_world.common.net.ServiceGenerator;
import com.hong_world.common.utils.EspressoIdlingResource;
import com.hong_world.homemodle.modle.TasksDataSource;
import com.hong_world.homemodle.net.LoginReq;
import com.hong_world.homemodle.net.RegisterResp;
import com.hong_world.homemodle.net.WorkerService;
import com.hong_world.library.base.BaseApplication;
import com.hong_world.library.net.FragmentLifeCycleEvent;
import com.hong_world.library.net.interceptor.HttpUtil;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.LifeCache;
import io.rx_cache2.ProviderKey;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;

import static com.hong_world.common.GlobalContants.NONETWORK;

/**
 * Date: 2017/11/3.13:52
 * Author: hong_world
 * Description: 请求网络数据
 * Version:
 */

public class TasksRemoteDataSource implements TasksDataSource {

    private static TasksRemoteDataSource INSTANCE;
    private static WorkerService workerService;
    private static WorkerService workerServiceProviders;

    private TasksRemoteDataSource() {
    }

    public static TasksRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TasksRemoteDataSource();
            workerService = ServiceGenerator.createService(WorkerService.class, BuildConfig.API_HOST);
            workerServiceProviders = new RxCache.Builder()
                    .persistence(BaseApplication.getInstance().getExternalCacheDir(), new GsonSpeaker())
                    .using(WorkerService.class);
        }
        return INSTANCE;
    }


    @Override
    public void getTask(@NonNull final Task task, @NonNull final TasksDataSource.GetTaskCallback callback) {
        //net操作一顿
        EspressoIdlingResource.increment();
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {//模拟网络请求延时操作
            @Override
            public void run() {
                if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                    EspressoIdlingResource.decrement(); // Set app as idle.
                }
                task.setPwd(task.getPwd() + "(网络数据)");
                Log.i("test", "网络数据");
                callback.onTaskLoaded(task);
            }
        }, 2000);

    }

    @Override
    public void getTask(@NonNull final Task task, Context c, PublishSubject<FragmentLifeCycleEvent> lifecycleSubject, @NonNull final GetTaskCallback callback) {
        Logger.i("开始");
        WorkerService service = ServiceGenerator.createService(WorkerService.class, "http://auth.zhugongbang.com/");
        MyHttp.toBaseResponseSubscribe(c, lifecycleSubject, service.login(new LoginReq(task.getPhone(), task.getPwd())), new MySubscribe<RegisterResp>() {
            @Override
            public void _onError(String errorMsg) {
                Logger.i(errorMsg);
                callback.onDataNotAvailable(NONETWORK, errorMsg);
            }

            @Override
            public void _onNext(RegisterResp o) {
                Logger.i(o.getId());
                callback.onTaskLoaded(task);
            }
        });
        lifecycleSubject.onNext(FragmentLifeCycleEvent.DESTROY);
    }

    @Override
    public void saveTask(@NonNull Task task) {

    }

    @Override
    public Observable getTask(String name, String pwd) {
        Observable observable = MyHttp.toBaseResponseSubscribe(workerService.login(new LoginReq(name, pwd)));
        return workerServiceProviders.getUser(observable, new DynamicKey("getTask"), new EvictDynamicKey(false))
                .compose(MyHttp.uiScheduler());
//        return observable;
    }

    @Override
    public void deleteAllTasks() {

    }

}
