package com.hong_world.myapp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hong_world.myapp.Injection;
import com.hong_world.myapp.R;
import com.hong_world.myapp.base.BaseFragment;
import com.hong_world.myapp.bean.Task;
import com.hong_world.myapp.contract.MainContract;
import com.hong_world.myapp.databinding.FragmentLoginBinding;
import com.hong_world.myapp.presenter.MainPresenter;

/**
 * Date: 2017/10/31.17:38
 * Author: hong_world
 * Description:
 * Version:
 */

public class MainFragment extends BaseFragment<MainPresenter> implements MainContract.View<MainPresenter> {

    private EditText editText;
    private EditText editText1;
    private Button button;
    private FragmentLoginBinding binding;

    public static MainFragment getInstance() {
        return new MainFragment();
    }

    @Override
    public void initDate() {

    }

    @Override
    public MainPresenter getPresenter() {
        return new MainPresenter(this, Injection.provideLoginTask());
    }

    @Override
    public void onBack() {

    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
//        View root = binding.getRoot();
//        //        View root = inflater.inflate(R.layout.fragment_login, container, false);
//        return root;
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        super.initViews(view, savedInstanceState);
        binding= (FragmentLoginBinding) getBindView();
        binding.setView(this);
        binding.setPresenter(mPresenter);
//        ILoaderManager.getLoader().loadResource(binding.imageView,R.mipmap.ic_launcher,null);
    }

    public void login(View view) {
        mPresenter.loginTask(editText.getText().toString(), editText1.getText().toString());
    }

    @Override
    public void onSuccess(Task task) {
        Toast.makeText(getActivity(), task.getPhone() + task.getPwd(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError() {
        Toast.makeText(getActivity(), "错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

}
