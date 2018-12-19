package com.hong_world.common.base;

import com.hong_world.library.base.BaseView;

/**
 * Date: 2018/10/19. 13:55
 * Author: hong_world
 * Description:
 * Version:
 */
public interface BaseAppUploadImageView extends BaseView {
    void onCamera();

    void onPhoto();

    void onCancel();

    void onShowPop();
}
