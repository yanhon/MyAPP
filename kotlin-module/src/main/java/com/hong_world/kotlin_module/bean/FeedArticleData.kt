package com.hong_world.kotlin_module.bean

import android.databinding.BaseObservable
import android.os.Parcel
import android.os.Parcelable

import java.io.Serializable


class FeedArticleData : BaseObservable, Parcelable {

    var apkLink: String? = null
    var author: String? = null
    var chapterId: Int = 0
    var chapterName: String? = null
    var isCollect: Boolean = false
    var courseId: Int = 0
    var desc: String? = null
    var envelopePic: String? = null
    var id: Int = 0
    var link: String? = null
    var niceDate: String? = null
    var origin: String? = null
    var projectLink: String? = null
    var superChapterId: Int = 0
    var superChapterName: String? = null
    var publishTime: Long = 0
    var title: String? = null
    var visible: Int = 0
    var zan: Int = 0

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.apkLink)
        dest.writeString(this.author)
        dest.writeInt(this.chapterId)
        dest.writeString(this.chapterName)
        dest.writeByte(if (this.isCollect) 1.toByte() else 0.toByte())
        dest.writeInt(this.courseId)
        dest.writeString(this.desc)
        dest.writeString(this.envelopePic)
        dest.writeInt(this.id)
        dest.writeString(this.link)
        dest.writeString(this.niceDate)
        dest.writeString(this.origin)
        dest.writeString(this.projectLink)
        dest.writeInt(this.superChapterId)
        dest.writeString(this.superChapterName)
        dest.writeLong(this.publishTime)
        dest.writeString(this.title)
        dest.writeInt(this.visible)
        dest.writeInt(this.zan)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.apkLink = `in`.readString()
        this.author = `in`.readString()
        this.chapterId = `in`.readInt()
        this.chapterName = `in`.readString()
        this.isCollect = `in`.readByte().toInt() != 0
        this.courseId = `in`.readInt()
        this.desc = `in`.readString()
        this.envelopePic = `in`.readString()
        this.id = `in`.readInt()
        this.link = `in`.readString()
        this.niceDate = `in`.readString()
        this.origin = `in`.readString()
        this.projectLink = `in`.readString()
        this.superChapterId = `in`.readInt()
        this.superChapterName = `in`.readString()
        this.publishTime = `in`.readLong()
        this.title = `in`.readString()
        this.visible = `in`.readInt()
        this.zan = `in`.readInt()
    }

    companion object {

        val CREATOR: Parcelable.Creator<FeedArticleData> = object : Parcelable.Creator<FeedArticleData> {
            override fun createFromParcel(source: Parcel): FeedArticleData {
                return FeedArticleData(source)
            }

            override fun newArray(size: Int): Array<FeedArticleData?> {
                return arrayOfNulls(size)
            }
        }
    }
}
