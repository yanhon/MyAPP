package com.hong_world.routerlibrary.provider;

/**
 * Date: 2017/11/17.14:29
 * Author: hong_world
 * Description:
 * Version:
 */

public interface IAppProvider extends IBaseProvider {
    String APP_GROUP = "app";
    String APP_SERVICE_PATH = "/app/service/apps";
    String APP_ACT_MIAN = "/app/act/main";
    String APP_FRG_MIAN = "/app/frg/main";

    void sayHello(String name);

}
