package com.hong_world.homemodle.presenter;

import android.databinding.ObservableField;

import com.hong_world.common.bean.Task;
import com.hong_world.common.net.RxBaseObserver;
import com.hong_world.homemodle.Injection;
import com.hong_world.homemodle.contract.MainContract;
import com.hong_world.homemodle.modle.TasksDataSource;
import com.hong_world.homemodle.net.RegisterResp;
import com.hong_world.homemodle.task.LoginTask;
import com.hong_world.library.base.BaseUseCase;

/**
 * Date: 2017/10/31.17:38
 * Author: hong_world
 * Description: 可直接或间接通过(UseCase)LoginTask控制mode(TasksRepository)获取数据、
 * -------------通过databinding控制View(xml)
 * -------------通过BaseView接口控制view(fragment/activity)
 * Version:
 */
public class MainPresenter extends MainContract.Presenter {


    public Task task = new Task();

    public final ObservableField<String> phone = new ObservableField<>();
    public final ObservableField<String> pwd = new ObservableField<>();

    LoginTask loginTask;

    TasksDataSource mTasksRepository;

    @Override
    public void initData() {
        super.initData();
        mView.onSuccess();
    }

    /**
     * base的mvp
     *
     * @param view
     */
    public MainPresenter(MainContract.View view) {
        setmView(view);
        mTasksRepository = Injection.provideTasksRepository();
    }

    /**
     * 加入clean
     *
     * @param view
     * @param loginTask
     */
    public MainPresenter(MainContract.View view, LoginTask loginTask) {
        setmView(view);
        this.loginTask = loginTask;
    }

    /**
     * test
     *
     * @param view
     * @param loginTask
     * @param mTasksRepository
     */
    public MainPresenter(MainContract.View view, LoginTask loginTask, TasksDataSource mTasksRepository) {
        setmView(view);
        this.loginTask = loginTask;
        this.mTasksRepository = mTasksRepository;
    }

    public void loginTaskString(String phone, String pwd) {
        phone = this.phone.get();
        pwd = this.pwd.get();
        mView.onLoading();
        Task newTask;
        if (phone != null && pwd != null && phone.length() != 0 && pwd.length() != 0) {
            newTask = new Task(phone, pwd);
        } else {
            mView.onError("");
            return;
        }
    }

    @Override
    public void loginTask(String phone, String pwd) {
//       phone = this.phone.get();
//       pwd = this.pwd.get();
//        mView.onLoading();
//        Task newTask;
//        if (phone != null && pwd != null && phone.length() != 0 && pwd.length() != 0) {
//            newTask = new Task(phone, pwd);
//        } else {
//            mView.onError();
//            return;
//        }
//        loginTask2(newTask);
        loginTask3(phone, pwd);
//        Task task = new Task();
//        task.setPhone("000");
//        task.setPwd(pwd);
//        mView.onSuccess(task);
    }

    /**
     * 通过TasksRepository获取数据，
     *
     * @param task 实体
     */
    @Override
    public void loginTask(Task task) {
        mTasksRepository.getTask(task, new TasksDataSource.GetTaskCallback<Task>() {
            @Override
            public void onTaskLoaded(Task task) {
                mView.onSuccess();
                mView.onSuccess(task);
            }

            @Override
            public void onDataNotAvailable(String type, String msg) {
                mView.onDataNotAvailable(type, msg);
            }
        });

    }

    /**
     * 可以使用rxLifecycle,当前方法绑定生命周期有点问题
     *
     * @param task
     */
    public void loginTask2(Task task) {
        mTasksRepository.getTask(task, mView.getActivityContext(), mView.getLifecycleSubject(), new TasksDataSource.GetTaskCallback<Task>() {
            @Override
            public void onTaskLoaded(Task task) {
                mView.onSuccess();
                mView.onSuccess(task);
            }

            @Override
            public void onDataNotAvailable(String type, String msg) {
                mView.onDataNotAvailable(type, msg);
            }
        });
    }

    /**
     * 使用CompositeDisposable 实现生命周期管理请求，简洁结合mvp
     */
    public void loginTask3(final String name, final String pwd) {
        mTasksRepository.getTask(name, pwd).subscribe(addDisposable(new RxBaseObserver<RegisterResp>(this) {
            @Override
            protected void onSuccess(RegisterResp data) {
//                mView.onSuccess();
                Task task = new Task();
                task.setPhone(name);
                task.setPwd(pwd);
                mView.onSuccess(task);
            }

            @Override
            protected void onFail(String errorCode, String errorMsg) {

            }
        }));
    }

    /**
     * 通过useCase处理
     */
    public void loginTaskRepository(Task task) {
        if (task.getPhone() == null && task.getPwd() == null && task.getPhone().length() != 0 && task.getPwd().length() != 0) {
            mView.onError("");
            return;
        }
        loginTask.setRequestValues(new LoginTask.RequestValues(task));
        loginTask.setUseCaseCallback(new BaseUseCase.UseCaseCallback<LoginTask.ResponseValue>() {
            @Override
            public void onSuccess(LoginTask.ResponseValue response) {
                mView.onSuccess(response.getTask());
            }

            @Override
            public void onError(String type, String msg) {
                mView.onDataNotAvailable(type, msg);
            }
        });
        loginTask.run();
    }
}