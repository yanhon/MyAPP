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
import com.hong_world.library.iloader.ILoaderManager;
import com.hong_world.library.net.FragmentLifeCycleEvent;
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
@Route(path = IHomeProvider.HOME_FRG_MAIN, group = IHomeProvider.HOME_GROUP)
public class MainFragment extends BaseFragment<MainPresenter, FragmentLoginsBinding> implements MainContract.View<MainPresenter> {

    public static MainFragment getInstance() {
        return new MainFragment();
    }

    @Override
    public String title() {
        return "登录";
    }


    @Override
    public MainPresenter createPresenter() {
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
        mBinding.setView(this);
        mBinding.setPresenter(mPresenter);
//        ILoaderManager.getLoader().loadResource(mBinding.imageView,R.mipmap.ic_launcher,null);
    }

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
