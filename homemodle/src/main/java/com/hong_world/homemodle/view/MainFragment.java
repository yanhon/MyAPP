package com.hong_world.homemodle.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hong_world.common.base.BaseFragment;
import com.hong_world.common.bean.Task;
import com.hong_world.common.net.FragmentLifeCycleEvent;
import com.hong_world.homemodle.net.LoginReq;
import com.hong_world.common.net.MyHttp;
import com.hong_world.common.net.MySubscribe;
import com.hong_world.homemodle.net.RegisterResp;
import com.hong_world.common.net.ServiceGenerator;
import com.hong_world.homemodle.net.WorkerService;
import com.hong_world.homemodle.R;
import com.hong_world.homemodle.contract.MainContract;
import com.hong_world.homemodle.databinding.FragmentLoginsBinding;
import com.hong_world.homemodle.presenter.MainPresenter;
import com.hong_world.routerlibrary.provider.IBProvider;
import com.hong_world.routerlibrary.provider.IHomeProvider;

import io.reactivex.subjects.PublishSubject;

/**
 * Date: 2017/10/31.17:38
 * Author: hong_world
 * Description:
 * Version:
 */
@Route(path = IHomeProvider.HOME_FRG_MIAN, group = IHomeProvider.HOME_GROUP)
public class MainFragment extends BaseFragment<MainPresenter> implements MainContract.View<MainPresenter> {

    private EditText editText;
    private EditText editText1;
    private Button button;
    private FragmentLoginsBinding binding;

    public static MainFragment getInstance() {
        return new MainFragment();
    }

    @Override
    public String title() {
        return "登录";
    }


    @Override
    public MainPresenter getPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void onBack() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_logins;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        super.initViews(view, savedInstanceState);
        binding = (FragmentLoginsBinding) getBindView();
        binding.setView(this);
        binding.setPresenter(mPresenter);
//        ILoaderManager.getLoader().loadResource(binding.imageView,R.mipmap.ic_launcher,null);
        com.orhanobut.logger.Logger.i("开始");
        PublishSubject<FragmentLifeCycleEvent> lifecycleSubject = PublishSubject.create();
        lifecycleSubject.onNext(FragmentLifeCycleEvent.CREATE);

        WorkerService service = ServiceGenerator.createService(WorkerService.class, "http://auth.zhugongbang.com/");
        MyHttp.toBaseResponseSubscribe(getActivity(),service.login(new LoginReq("17742676885", "123456")), new MySubscribe<RegisterResp>() {
            @Override
            public void _onError(String errorMsg) {
                com.orhanobut.logger.Logger.i(errorMsg);
            }

            @Override
            public void _onNext(RegisterResp o) {
                com.orhanobut.logger.Logger.i(o.getId());
            }
        }, FragmentLifeCycleEvent.DESTROY, lifecycleSubject);
    }

//    public void login(View view) {
//        mPresenter.loginTask(editText.getText().toString(), editText1.getText().toString());
//    }

    @Override
    public void onSuccess(Task task) {
        Toast.makeText(getActivity(), task.getPhone() + task.getPwd() + ">", Toast.LENGTH_SHORT).show();
//        ARouter.getInstance().build(IHomeProvider.HOME_ACT_MAIN).navigation();
        ARouter.getInstance().build(IBProvider.B_ACT_B, IBProvider.B_GROUP).navigation();
//        startActivity(new Intent(getActivity(), HomeActivity.class));
    }

    @Override
    protected boolean needTopBar() {
        return true;
    }
}
