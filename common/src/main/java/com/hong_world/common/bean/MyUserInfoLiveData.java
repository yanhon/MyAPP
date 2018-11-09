package com.hong_world.common.bean;

import android.arch.lifecycle.LiveData;

import com.hong_world.library.utils.StringUtil;
import com.orhanobut.logger.Logger;

import io.reactivex.disposables.Disposable;

/**
 * Date: 2018/11/2. 10:08
 * Author: hong_world
 * Description: livedata 下次订阅时会返回上一次的订阅消息
 * Version:
 */
public class MyUserInfoLiveData extends LiveData<String> {
    private static MyUserInfoLiveData sInstance;
    private String data;
    private Disposable ob;

    public MyUserInfoLiveData(String data) {
        this.data = data;
//        ob = Observable.interval(10, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        setValue(aLong.toString());
//                    }
//                });
    }

    @Override
    protected void onActive() {
        super.onActive();
        Logger.i("MyUserInfoLiveData : onActive");
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        Logger.i("MyUserInfoLiveData : onInactive");
//        ob.dispose();
    }

    public static MyUserInfoLiveData getInstance(String data) {
        if (sInstance == null) {
            Logger.i("MyUserInfoLiveData null");
            sInstance = new MyUserInfoLiveData(data);
        }
//        Logger.i("MyUserInfoLiveData not null " + sInstance.getValue());
        if (StringUtil.isNotEmpty(data))
            sInstance.setValue(data);
        return sInstance;
    }
}
