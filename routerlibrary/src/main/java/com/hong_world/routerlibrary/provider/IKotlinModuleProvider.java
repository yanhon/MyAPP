package com.hong_world.routerlibrary.provider;

/**
 * Date: 2018/9/19. 17:34
 * Author: hong_world
 * Description:
 * Version:
 */
public interface IKotlinModuleProvider extends IBaseProvider {
    String KOTLIN_MODULE_SERVICE = "/kotlin_module/service/homes";
    String KOTLIN_MODULE_GROUP = "kotlin_module";
    String KOTLIN_MODULE_ACT_MAIN = "/kotlin_module/act/main";
    String KOTLIN_MODULE_FRG_WAN_ANDROID = "/kotlin_module/frg/wan_android";

    void sayHello(String name);

}
