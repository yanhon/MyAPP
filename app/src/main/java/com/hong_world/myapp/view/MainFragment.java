package com.hong_world.myapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hong_world.myapp.Injection;
import com.hong_world.myapp.contract.MainContract;
import com.hong_world.myapp.presenter.MainPresenter;
import com.hong_world.myapp.R;
import com.hong_world.myapp.base.BaseFragment;
import com.hong_world.myapp.bean.Task;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        editText = root.findViewById(R.id.editText);
        editText1 = root.findViewById(R.id.editText2);
        button = root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(view);
            }
        });
        return root;
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
        Toast.makeText(getActivity(),"错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
