package com.hong_world.homemodle.view;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hong_world.common.base.BaseUploadImageFragment;
import com.hong_world.common.utils.BottomNavigationViewHelper;
import com.hong_world.homemodle.R;
import com.hong_world.homemodle.contract.HomeContract;
import com.hong_world.homemodle.databinding.FragmentHomeBinding;
import com.hong_world.homemodle.presenter.HomePresenter;
import com.hong_world.routerlibrary.provider.IHomeProvider;

import me.yokeyword.fragmentation.ISupportFragment;

/**
 * Date: 2018/8/7. 16:55
 * Author: hong_world
 * Description:
 * Version:
 */
@Route(path = IHomeProvider.HOME_FRG_IMAGE, group = IHomeProvider.HOME_GROUP)
public class ImageFragment extends BaseUploadImageFragment<HomePresenter, FragmentHomeBinding> implements HomeContract.View<HomePresenter> {
    private ISupportFragment[] mFragments = new ISupportFragment[4];
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOUR = 3;

    public int selectNum = FIRST;

    public static ImageFragment getInstance() {
        return new ImageFragment();
    }

    @Override
    protected boolean enableRefresh() {
        return false;
    }

    @Override
    protected boolean needTopBar() {
        return false;
    }

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public String title() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBottomNavigationView();
    }

    private void initBottomNavigationView() {
        BottomNavigationViewHelper.disableShiftMode(mBinding.bottomNavigationView);
        mBinding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int i = item.getItemId();
                if (!checkPermission("存储空间、相机", 0, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE))
                    return true;

                if (i == R.id.tab_navigation) {
                    galleryCrop();
                } else if (i == R.id.tab_knowledge_hierarchy) {
                    cameraCropPhoto();
                } else if (i == R.id.tab_project) {
                    cameraPhotoFile();
                } else if (i == R.id.tab_main_pager) {
                    selectMultipleImage();
                }
                return true;
            }
        });
    }
}
