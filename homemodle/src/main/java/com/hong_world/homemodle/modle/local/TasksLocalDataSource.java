package com.hong_world.homemodle.modle.local;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.hong_world.common.MyApplication;
import com.hong_world.common.bean.Task;
import com.hong_world.homemodle.modle.TasksDataSource;
import com.hong_world.homemodle.net.RegisterResp;
import com.hong_world.library.net.FragmentLifeCycleEvent;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.subjects.PublishSubject;

/**
 * Date: 2017/11/3.13:52
 * Author: hong_world
 * Description: 请求本地数据
 * Version:
 */

public class TasksLocalDataSource implements TasksDataSource {

    private static TasksLocalDataSource INSTANCE;

    private TasksLocalDataSource() {
    }

    public static TasksLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TasksLocalDataSource();
        }
        return INSTANCE;
    }


    @Override
    public Observable getTask(String name, String pwd) {
        return Observable.create(new ObservableOnSubscribe<RegisterResp>() {
            @Override
            public void subscribe(ObservableEmitter<RegisterResp> e) throws Exception {
                RegisterResp registerResp = new RegisterResp();
                registerResp.setUserName("TasksLocalDataSource");
                e.onNext(registerResp);
                e.onComplete();
            }
        });
    }

    @Override
    public void getTask(@NonNull Task task, @NonNull GetTaskCallback callback) {
        //db操作一顿
        task.setPwd(task.getPwd() + "(本地)");
        callback.onTaskLoaded(task);
        List<Task> list = MyApplication.getDaoSession().getTaskDao().queryBuilder()
                .build()
                .list();
        StringBuilder sb = new StringBuilder();
        for (Task bean : list) {
            Log.i("test", bean.getPhone() + "的 ：" + bean.getPwd());
            sb.append(bean.getPhone() + "、");
        }
        Log.i("test", sb.toString());
    }

    @Override
    public void getTask(@NonNull Task task, Context c, PublishSubject<FragmentLifeCycleEvent> lifecycleSubject, @NonNull GetTaskCallback callback) {

    }

    @Override
    public void saveTask(@NonNull Task task) {
        MyApplication.getDaoSession().getTaskDao().insert(task);
    }

    @Override
    public void deleteAllTasks() {
        MyApplication.getDaoSession().getTaskDao().deleteAll();
    }

}
