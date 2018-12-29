package com.hong_world.common.view.status;

import android.view.View;

/**
 * Date: 2017/11/14.14:18
 * Author: hong_world
 * Description: 状态视图显示监听
 * Version:
 */

public interface OnStatusViewListener {
    void onShowView(View view, int id);

    void onHideView(View view, int id);
}
