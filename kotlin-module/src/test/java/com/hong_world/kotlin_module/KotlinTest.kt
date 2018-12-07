package com.hong_world.kotlin_module

import org.junit.Test
import kotlin.properties.Delegates

/**
 * Date: 2018/12/3. 16:29
 * Author: hong_world
 * Description:
 * Version:
 */
class KotlinTest {
    val lazyValue: String by lazy(LazyThreadSafetyMode.PUBLICATION) {
        println("okoko")
        "hahah"
    }
    var name: String by Delegates.observable("null") { property, oldValue, newValue ->
        println("$oldValue->$newValue")
    }

    @Test
    fun testLazy() {
        println(lazyValue)
        Thread {
            run {
                print(lazyValue)
            }
        }.start()

    }

    @Test
    fun testObservable() {
        name = "frist"
        println(name)
        name = "second"
        println(name)
    }

    @Test
    fun testVararg() {
        varargs(*arrayOf("1", "2", "3"))
    }

    fun varargs(vararg strings: String) {
        for (item in strings) {
            println("$item")
        }
    }

    @Test
    fun testUnit() {
        println(units("tom"))
    }

    fun units(name: String?): Unit {
        if (name == null) {
            println("name is null")
        } else {
            println("$name")
            return
        }
    }

    lateinit var m: String

    @Test
    fun testT() {
        println(testTs("ok"))
        println(testTs(1))
        m = "lateinit"
        println(m)
    }

    fun <T> testTs(data: T): T {
        return data;
    }

    var ss: String? = "sss"
        set(value) {
            field = value + "d"
        }

    @Test
    fun testAlso() {

        val letdata: String? = "let"
        val dataLet = letdata?.let {
            println("let:" + it.length);
            "newlet"
        }
        println(dataLet)
        val datawith: String? = "with"

        val withData = with(datawith) {
            println(this?.length)
            "newwith"
        }
        println("with:" + withData)

        val rundata: String? = "run"
        val datarun = rundata?.run {
            println("datarun:" + this)
            this?.apply {
                1000
            }
        }
        println(datarun)

        val apply: String? = "apply"
        val dataapply = apply.apply {
            100
        }
        println("apply: " + dataapply)

        val data: String = "also".also {
            println("also:" + it.length);
            100
        }
        println(data)

    }
}