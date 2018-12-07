package com.hong_world.kotlin_module.bean

/**
 * Date: 2018/11/30. 14:05
 * Author: hong_world
 * Description:
 * Version:
 */
class KotlinTest2 {
    fun gets(): String {
        var msg = KotlinTest.msg
        return msg
    }

    fun setOnLineListener(nested: Nested) {

    }

    fun getss() {
        var i: Int = 0;
        setOnLineListener(object : Nested() {
            override fun getsd(): String {
                i++;
                return super.getsd();
            }
        })
    }

    open inner class Nested {
        open fun getsd(): String {
            return gets()
        }
    }
}