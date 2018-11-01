package com.hong_world.common.providers;

import com.hong_world.common.bean.FeedArticleListData;
import com.hong_world.routerlibrary.provider.IBProvider;

import io.reactivex.Observable;

/**
 * Date: 2018/10/30. 17:55
 * Author: hong_world
 * Description:
 * Version:
 */
public interface IBProviderc extends IBProvider {
    Observable<FeedArticleListData> getFeedArticleList(int num);
}
