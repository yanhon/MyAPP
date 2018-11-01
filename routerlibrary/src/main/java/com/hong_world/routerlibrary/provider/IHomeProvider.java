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
    String HOME_ACT_WEB = "/home/act/web";
    String HOME_FRG_MAIN = "/home/frg/main";
    String HOME_FRG_HOME = "/home/frg/home";
    String HOME_FRG_IMAGE = "/home/frg/image";
    String HOME_FRG_WEB = "/home/frg/web";
    String HOME_FRG_NEW_LIST = "/home/frg/newList";

    void sayHello(String name);

    void openActivity(Bundle bundle);
}
