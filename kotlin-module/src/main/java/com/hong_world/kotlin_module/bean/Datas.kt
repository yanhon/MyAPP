package com.hong_world.kotlin_module.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * Date: 2018/12/4. 17:31
 * Author: hong_world
 * Description:
 * Version:
 */
data class Datas(var name: String = "",var sex :String="") :Parcelable{
    var age: Int = 0

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString()) {
        age = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(sex)
        parcel.writeInt(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Datas> {
        override fun createFromParcel(parcel: Parcel): Datas {
            return Datas(parcel)
        }

        override fun newArray(size: Int): Array<Datas?> {
            return arrayOfNulls(size)
        }
    }
}