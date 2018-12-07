/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hong_world.homemodle.modle;

import android.content.Context;
import android.support.annotation.NonNull;

import com.hong_world.common.bean.Task;
import com.hong_world.homemodle.net.RegisterResp;
import com.hong_world.library.net.FragmentLifeCycleEvent;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;


/**
 * Main entry point for accessing tasks data.
 * <p>
 * For simplicity, only getTasks() and getTask() have callbacks. Consider adding callbacks to other
 * methods to inform the user of network/database errors or successful operations.
 * For example, when a new task is created, it's synchronously stored in cache but usually every
 * operation on database or network should be executed in a different thread.
 */
public interface TasksDataSource {

    interface GetTaskCallback<C> {

        void onTaskLoaded(C bean);

        void onDataNotAvailable(String type, String msg);
    }

    Observable<RegisterResp> getTask(String name, String pwd);

    void getTask(@NonNull Task task, @NonNull GetTaskCallback callback);

    void getTask(@NonNull Task task, Context c, PublishSubject<FragmentLifeCycleEvent> lifecycleSubject, @NonNull GetTaskCallback callback);

    void saveTask(@NonNull Task task);

    void deleteAllTasks();

    void deleteAllTasks2();
}
