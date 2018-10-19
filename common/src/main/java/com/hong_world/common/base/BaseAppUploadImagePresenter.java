package com.hong_world.common.base;

/**
 * Date: 2018/10/19. 13:55
 * Author: hong_world
 * Description:
 * Version:
 */
public abstract class BaseAppUploadImagePresenter<V extends BaseAppUploadImageView> extends BaseNormalPresenter<V> {

    public void onCamera() {
        mView.onCamera();
    }

    public void onPhoto() {
        mView.onPhoto();
    }

    public void onCancel() {
        mView.onCancel();
    }

    public void onShowPop() {
        mView.onShowPop();
    }
}
