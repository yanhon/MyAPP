package com.hong_world.homemodle.task;

import android.support.annotation.NonNull;

import com.hong_world.common.bean.Task;
import com.hong_world.homemodle.modle.TasksDataSource;
import com.hong_world.homemodle.modle.TasksRepository;
import com.hong_world.library.base.BaseUseCase;

/**
 * Date: 2017/11/1.17:11
 * Author: hong_world
 * Description:
 * Version:
 */

public class LoginTask extends BaseUseCase<LoginTask.RequestValues, LoginTask.ResponseValue> {

    private final TasksRepository mTasksRepository;

    public LoginTask(TasksRepository mTasksRepository) {
        this.mTasksRepository = mTasksRepository;
    }

    @Override
    protected void executeUseCase(final RequestValues requestValues) {
        mTasksRepository.getTask(requestValues.getTask(), new TasksDataSource.GetTaskCallback<Task>() {
            @Override
            public void onTaskLoaded(Task task) {
                getUseCaseCallback().onSuccess(new ResponseValue(requestValues.getTask()));
            }

            @Override
            public void onDataNotAvailable(String type, String msg) {
                getUseCaseCallback().onError(type,msg);
            }
        });
    }

    public static final class RequestValues implements BaseUseCase.RequestValues {

        private final Task mTask;

        public RequestValues(@NonNull Task task) {
            mTask = task;
        }

        public Task getTask() {
            return mTask;
        }
    }

    public static final class ResponseValue implements BaseUseCase.ResponseValue {

        private Task mTask;

        public ResponseValue(@NonNull Task task) {
            this.mTask = task;
        }

        public Task getTask() {
            return mTask;
        }
    }
}
