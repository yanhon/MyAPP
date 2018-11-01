package com.hong_world.routerlibrary.provider;

/**
 * Date: 2017/11/17.14:29
 * Author: hong_world
 * Description:
 * Version:
 */

public interface IBProvider extends IBaseProvider {
    String B_SERVICE = "/b/service/bs";
    String B_ACT_B = "/b/act/ba";
    String B_GROUP = "bp";

    String B_FRG_MAIN_PAGER = "/b/frg/mainPager";

    void sayHello(String name);

}
