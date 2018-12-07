package com.hong_world.kotlin_module.bean;

/**
 * Date: 2018/11/30. 13:54
 * Author: hong_world
 * Description:
 * Version:
 */
public class JavaTest {
    public void gets(){
        KotlinTest kotlinTest = new KotlinTest("name");
        String msg = kotlinTest.getMSG();
       String msg2 = KotlinTest.msg;
       Datas datas = new Datas();
    }
}
