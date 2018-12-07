package com.hong_world.kotlin_module.bean

/**
 * Date: 2018/11/30. 13:56
 * Author: hong_world
 * Description:
 * Version:
 */
class  KotlinTest public constructor(names :String){
    var MSG: String?
        get() {
            return getName()
        }
        set(value) {
            setName(value)
        }

    fun setName(name: String?) {
        val name = name
        Datas()
    }

    fun getName(): String {
        return msg
    }

    companion object {
        const val msg: String = "ww"
    }
}