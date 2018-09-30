package com.hong_world.kotlin_module.modle

import com.hong_world.kotlin_module.bean.FeedArticleListData
import io.reactivex.Observable

/**
 * Date: 2018/9/25. 16:21
 * Author: hong_world
 * Description:
 * Version:
 */
interface TasksDataSource {
    interface GetTaskCallback<C> {

        fun onTaskLoaded(bean: C)

        fun onDataNotAvailable(type: String, msg: String)
    }

    abstract fun getFeedArticleList(num: Int): Observable<FeedArticleListData>?
}