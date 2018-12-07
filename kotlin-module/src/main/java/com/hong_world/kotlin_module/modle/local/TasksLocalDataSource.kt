package com.hong_world.kotlin_module.modle.local

import com.hong_world.kotlin_module.bean.FeedArticleListData
import com.hong_world.kotlin_module.modle.BaseTasksDataSource
import io.reactivex.Observable

/**
 * Date: 2018/9/25. 16:22
 * Author: hong_world
 * Description:
 * Version:
 */
class TasksLocalDataSource : BaseTasksDataSource {


    private constructor()

    companion object {
        @JvmStatic
        fun getInstance() = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = TasksLocalDataSource()
    }

    override fun getFeedArticleList(num: Int): Observable<FeedArticleListData>? = null
}