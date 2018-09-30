package com.hong_world.kotlin_module.bean

import android.databinding.BaseObservable


class FeedArticleListData2 : BaseObservable() {

    var curPage: Int = 0
    var datas: List<FeedArticleData>? = null
    var offset: Int = 0
    var isOver: Boolean = false
    var pageCount: Int = 0
    var size: Int = 0
    var total: Int = 0
}
