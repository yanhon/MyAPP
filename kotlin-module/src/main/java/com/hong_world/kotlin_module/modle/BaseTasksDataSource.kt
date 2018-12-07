package com.hong_world.kotlin_module.modle

import com.hong_world.kotlin_module.bean.FeedArticleListData
import io.reactivex.Observable

/**
 * Date: 2018/9/25. 16:22
 * Author: hong_world
 * Description:
 * Version:
 */
open class BaseTasksDataSource : TasksDataSource {


    protected constructor()

//    companion object {
//        @JvmStatic
//        fun getInstance() = Holder.INSTANCE
//    }
//
//    protected object Holder {
//        val INSTANCE = BaseTasksDataSource()
//    }

    override fun getFeedArticleList(num: Int): Observable<FeedArticleListData>? = null
}