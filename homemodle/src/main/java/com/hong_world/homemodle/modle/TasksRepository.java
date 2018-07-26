package com.hong_world.homemodle.modle;

import android.content.Context;
import android.support.annotation.NonNull;

import com.hong_world.common.GlobalContants;
import com.hong_world.common.bean.Task;
import com.hong_world.common.net.MyHttp;
import com.hong_world.common.net.MySubscribe;
import com.hong_world.common.net.ServiceGenerator;
import com.hong_world.homemodle.net.LoginReq;
import com.hong_world.homemodle.net.RegisterResp;
import com.hong_world.homemodle.net.WorkerService;
import com.hong_world.library.net.FragmentLifeCycleEvent;
import com.orhanobut.logger.Logger;

import java.util.Optional;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;

/**
 * Date: 2017/11/1.17:24
 * Author: hong_world
 * Description:
 * -------------1、判断选择数据来源（网络or本地）
 * -------------2、判断请求参数、返回数据有效性（无效：
 * ---------------------------------2.1、请求参数错误（show Toast）: 1
 * ---------------------------------2.2、网络请求超时（show error view）:2
 * ---------------------------------2.3、服务器返回数据为空(show empty view):3
 * ---------------------------------2.4、服务器返回参数错误码（
 * -----------------------------------------------------2.4.1、展示型数据（show error view）:4
 * -----------------------------------------------------2.4.2、提交型数据（show Toast）:5
 * -----------------------------------------------------2.4.3、token异常（login again）:6
 * -----------------------------------------------------）
 * ---------------------------------2.5、网络未连接(show Toast):7
 * ---------------------------------）
 * Version:
 */

public class TasksRepository implements TasksDataSource {
    private static TasksRepository INSTANCE = null;
    private final TasksDataSource mTasksRemoteDataSource;

    private final TasksDataSource mTasksLocalDataSource;

    /**
     * @param tasksRemoteDataSource
     * @param tasksLocalDataSource
     */
    private TasksRepository(@NonNull TasksDataSource tasksRemoteDataSource,
                            @NonNull TasksDataSource tasksLocalDataSource) {
        mTasksRemoteDataSource = tasksRemoteDataSource;
        mTasksLocalDataSource = tasksLocalDataSource;
    }

    public static TasksRepository getInstance(TasksDataSource tasksRemoteDataSource,
                                              TasksDataSource tasksLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TasksRepository(tasksRemoteDataSource, tasksLocalDataSource);
        }
        return INSTANCE;
    }


    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public Observable getTask(String name, String pwd) {
        Observable r = mTasksRemoteDataSource.getTask(name, pwd).doOnNext(new Consumer<RegisterResp>() {
            @Override
            public void accept(RegisterResp registerResp) throws Exception {
                Logger.i("保存到数据库");
            }
        });
        Observable d = mTasksLocalDataSource.getTask(name, pwd).filter(new Predicate<RegisterResp>() {
            @Override
            public boolean test(RegisterResp o) throws Exception {
                if (o.getPhone() == null) {
                    Logger.i("数据库数据失效");
                    return false;
                } else
                    Logger.i("数据库数据有效");
                return true;
            }
        });
//        return Observable.concat(d, r).firstElement().toObservable();
        return r;
    }

    @Override
    public void getTask(@NonNull Task task, @NonNull final GetTaskCallback callback) {
        Logger.i("wowo");
        if (task.getPhone().equals("135")) {
            callback.onDataNotAvailable(GlobalContants.DATAEMPTY, "");
            return;
        }
        mTasksRemoteDataSource.getTask(task, new GetTaskCallback<Task>() {
            @Override
            public void onTaskLoaded(Task task) {
                callback.onTaskLoaded(task);
            }

            @Override
            public void onDataNotAvailable(String type, String msg) {
                callback.onDataNotAvailable(type, msg);
            }
        });
    }

    @Override
    public void getTask(@NonNull Task task, Context c, PublishSubject<FragmentLifeCycleEvent> lifecycleSubject, @NonNull final GetTaskCallback callback) {
        mTasksRemoteDataSource.getTask(task, c, lifecycleSubject, new GetTaskCallback<Task>() {
            @Override
            public void onTaskLoaded(Task task) {
                callback.onTaskLoaded(task);
            }

            @Override
            public void onDataNotAvailable(String type, String msg) {
                callback.onDataNotAvailable(type, msg);
            }
        });
    }

    @Override
    public void saveTask(@NonNull Task task) {

    }

    @Override
    public void deleteAllTasks() {

    }

}
