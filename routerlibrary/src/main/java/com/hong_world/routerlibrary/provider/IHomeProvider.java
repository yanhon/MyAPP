package com.hong_world.routerlibrary.provider;

import android.os.Bundle;

/**
 * Date: 2017/11/17.14:29
 * Author: hong_world
 * Description:
 * Version:
 */

public interface IHomeProvider extends IBaseProvider {
    String HOME_SERVICE = "/home/service/homes";
    String HOME_GROUP = "home";
    String HOME_ACT_MAIN = "/home/act/home";
    String HOME_ACT_MIAN = "/home/act/main";
    String HOME_FRG_MIAN = "/home/frg/main";
    void openActivity( Bundle bundle);
}
