package com.hong_world.bmodle.presenter;

import com.hong_world.bmodle.Injection;
import com.hong_world.bmodle.contract.MainPagerContract;
import com.hong_world.bmodle.modle.TasksDataSource;
import com.hong_world.common.bean.FeedArticleData;
import com.hong_world.common.bean.FeedArticleListData;
import com.hong_world.common.net.RxBaseObserver;

/**
 * Date: 2018/7/31. 10:10
 * Author: hong_world
 * Description:
 * Version:
 */
public class MainPagerPresenter extends MainPagerContract.Presenter {
    TasksDataSource mTasksRepository;

    public MainPagerPresenter(MainPagerContract.View view) {
        setmView(view);
        mTasksRepository = Injection.provideTasksRepository();
    }

    @Override
    public void getPageList(int mCurrentPage, final boolean isRefresh) {
        addDisposable(mTasksRepository.getFeedArticleList(mCurrentPage).subscribeWith(new RxBaseObserver<FeedArticleListData>(this) {
            @Override
            protected void onSuccess(FeedArticleListData data) {
                getView().getPageListSuccess(data, isRefresh);
            }

            @Override
            protected void onFail(String errorCode, String errorMsg) {

            }
        }).isCancle(true));
      addDisposable(mTasksRepository.getFeedArticleList(mCurrentPage).subscribeWith(new RxBaseObserver<FeedArticleListData>(this) {
            @Override
            protected void onSuccess(FeedArticleListData data) {
                getView().getPageListSuccess(data, isRefresh);
            }

            @Override
            protected void onFail(String errorCode, String errorMsg) {

            }
        }).isCancle(true));
    }

    public void onItemClick(FeedArticleData data) {
        mView.onItemClick(data);
//        String fileName = "w.apk";
//        String path = FileUtils.getDiskCachePath(BaseApplication.getInstance()) + File.separator + "down";
//        String url = "http://imtt.dd.qq.com/16891/1A8EA15110A5DA113EBD2F955615C7EC.apk?fsname=com.moji.mjweather_7.0103.02_7010302.apk&csr=1bbd";
//        File file = DownloadManager.checkDownFile(fileName, path, url);
//        long start = file.length();
//        fileName = file.getName();
//        BService bService = ServiceGenerator.createService2(BService.class, "http://imtt.dd.qq.com/");
//        addDisposable(MyHttp.todownResponseSubscribe(
//                bService.down("bytes=" + start + "-", url)
//                , start, path, fileName, url)
//                .subscribeWith(new RxBaseObserver<DownProgress>(this, false) {
//
//                    @Override
//                    protected void onSuccess(DownProgress data) {
//                        Logger.i(data.toString());
//                    }
//
//                    @Override
//                    protected void onFail(String errorCode, String errorMsg) {
//
//                    }
//                }));
    }
}
