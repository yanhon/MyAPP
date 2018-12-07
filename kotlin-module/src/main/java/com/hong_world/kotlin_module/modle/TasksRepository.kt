package com.hong_world.kotlin_module.modle

import com.hong_world.kotlin_module.bean.FeedArticleListData
import io.reactivex.Observable

/**
 * Date: 2018/9/25. 16:28
 * Author: hong_world
 * Description:
 * Version:
 */
class TasksRepository : BaseTasksDataSource {

    private var mTasksRemoteDataSource: TasksDataSource? = null

    private var mTasksLocalDataSource: TasksDataSource? = null

    override fun getFeedArticleList(num: Int): Observable<FeedArticleListData>? = mTasksRemoteDataSource?.getFeedArticleList(num)

    private constructor(mTasksRemoteDataSource: TasksDataSource,
                        mTasksLocalDataSource: TasksDataSource) {
        this.mTasksLocalDataSource = mTasksLocalDataSource
        this.mTasksRemoteDataSource = mTasksRemoteDataSource

    }

    companion object {
        @JvmStatic
        fun getInstance(tasksRemoteDataSource: TasksDataSource,
                        tasksLocalDataSource: TasksDataSource): TasksRepository = Holder.getHolder(tasksRemoteDataSource, tasksLocalDataSource)
    }

    private object Holder {
        var tasksRepository: TasksRepository? = null
        fun getHolder(tasksRemoteDataSource: TasksDataSource,
                      tasksLocalDataSource: TasksDataSource): TasksRepository {
            if (tasksRepository == null) tasksRepository = TasksRepository(tasksRemoteDataSource, tasksLocalDataSource)
            return tasksRepository as TasksRepository
        }
    }
}
