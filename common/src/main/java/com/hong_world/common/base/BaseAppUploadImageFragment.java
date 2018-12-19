package com.hong_world.common.base;

import android.databinding.ViewDataBinding;

/**
 * Date: 2018/10/18. 16:54
 * Author: hong_world
 * Description:
 * Version:
 */
public abstract class BaseAppUploadImageFragment<P extends BaseAppUploadImagePresenter, V extends ViewDataBinding> extends BaseUploadImageFragment<P, V>
        implements BaseAppUploadImageView {
    /**
     * 弹窗选择器（拍照or相册）
     */
    @Override
    public void onShowPop() {

    }

    /**
     * 关闭弹窗
     */
    @Override
    public void onCancel() {

    }

    /**
     * 相册图片
     */
    @Override
    public void onPhoto() {
        gallery();
    }

    /**
     * 相机拍摄
     */
    @Override
    public void onCamera() {
        cameraPhotoFile();
    }

}
