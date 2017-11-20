package com.hong_world.routerlibrary;

import android.content.Context;
import android.os.Bundle;

/**
 * Date: 2017/11/20.17:26
 * Author: hong_world
 * Description:
 * Version:
 */

public class ModleService {
    public static void openHomeActivity(Context context, Bundle bundle) {
        ServiceManager.getInstance().getHomeProvider().openActivity(bundle);
    }
}
