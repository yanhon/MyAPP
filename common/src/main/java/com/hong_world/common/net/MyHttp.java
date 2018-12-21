package com.hong_world.common.net;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.Nullable;

import com.hong_world.library.net.down.DownProgress;
import com.hong_world.library.net.down.DownloadManager;
import com.hong_world.library.net.ApiRetryFunc;
import com.hong_world.library.net.FragmentLifeCycleEvent;
import com.hong_world.library.net.exception.APIResultException;
import com.orhanobut.logger.Logger;

import org.reactivestreams.Publisher;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import okhttp3.ResponseBody;

/**
 * Date: 2018/5/17. 17:17
 * Author: hong_world
 * Description:
 * Version:
 */

public class MyHttp {
    //---------------------------------bad method start-------
    public static void toBaseResponseSubscribe(Context c, final PublishSubject<FragmentLifeCycleEvent> lifecycleSubject, Observable ob, final MySubscribe subscriber) {
        ProgressDialog dialog = new ProgressDialog(c);
        //数据预处理
        ObservableTransformer<BaseResponse<Object>, Object> result = handleResult(FragmentLifeCycleEvent.DESTROY, lifecycleSubject);
        Observable observable = ob.compose(result)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Logger.i("展示dialog");
                        dialog.show();
                    }
                }).doOnNext(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Logger.i("关闭展示dialog");
//                        dialog.dismiss();
                    }
                }).doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.i("error关闭展示dialog");
//                        dialog.dismiss();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(subscriber);
    }

    /**
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<BaseResponse<T>, T> handleResult(final FragmentLifeCycleEvent event,
                                                                             final PublishSubject<FragmentLifeCycleEvent> lifecycleSubject) {
        return new ObservableTransformer<BaseResponse<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseResponse<T>> upstream) {
                Observable<FragmentLifeCycleEvent> compareLifecycleObservable =
                        lifecycleSubject.filter(new Predicate<FragmentLifeCycleEvent>() {
                            @Override
                            public boolean test(FragmentLifeCycleEvent fragmentLifeCycleEvent) throws Exception {
                                return fragmentLifeCycleEvent.equals(event);
                            }
                        }).firstElement().toObservable().publish();
                return upstream.flatMap(new Function<BaseResponse<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(BaseResponse<T> result) throws Exception {
                        if (result.isSuccess() == true) {
                            return Observable.create(new ObservableOnSubscribe<T>() {

                                @Override
                                public void subscribe(ObservableEmitter<T> e) throws Exception {
                                    try {
                                        e.onNext(result.getData());
                                        e.onComplete();
                                    } catch (Exception s) {
                                        e.onError(s);
                                    }
                                }
                            });
                        } else {
                            return Observable.error(new APIResultException(result.getErrorCode(), result.getMsg()));
                        }
                    }
                }).takeUntil(compareLifecycleObservable)
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new ObservableOnSubscribe<T>() {

            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                try {
                    e.onNext(data);
                    e.onComplete();
                } catch (Exception s) {
                    e.onError(s);
                }
            }
        });
    }

    //---------------------------------bad method end-------

    public static <T> Observable<T> toBaseResponseSubscribe(Observable<BaseResponse<T>> ob) {
        return ob.compose(apiTransformer());
    }

    public static Observable<DownProgress> todownResponseSubscribe(Observable<ResponseBody> ob, long start, @Nullable String filePath, @Nullable String fileName, String tagUrl) {
        return ob.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .toFlowable(BackpressureStrategy.LATEST)
                .flatMap(new Function<ResponseBody, Publisher<DownProgress>>() {
                    @Override
                    public Publisher<DownProgress> apply(final ResponseBody responseBody) throws Exception {
                        return Flowable.create(new FlowableOnSubscribe<DownProgress>() {
                            @Override
                            public void subscribe(FlowableEmitter<DownProgress> subscriber) throws Exception {
                                String name = DownloadManager.getRealFileName(fileName, responseBody);
                                String path = DownloadManager.getRealFilePath(filePath, name);
                                DownloadManager.saveFile(subscriber, path, start, responseBody, tagUrl);
                            }
                        }, BackpressureStrategy.LATEST);
                    }
                })
//                        .sample(100, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .retryWhen(new ApiRetryFunc(3,
                        1000));
    }


    public static <T> ObservableTransformer<BaseResponse<T>, T> apiTransformer() {
        return new ObservableTransformer<BaseResponse<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseResponse<T>> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .flatMap(new Function<BaseResponse<T>, ObservableSource<T>>() {
                            @Override
                            public Observable<T> apply(BaseResponse<T> result) throws Exception {
                                if (result.isSuccess()) {
                                    return Observable.create(new ObservableOnSubscribe<T>() {

                                        @Override
                                        public void subscribe(ObservableEmitter<T> e) throws Exception {
                                            try {
                                                e.onNext(result.getData());
                                                e.onComplete();
                                            } catch (Exception s) {
                                                e.onError(s);
                                            }
                                        }
                                    });
                                } else {
                                    return Observable.error(new APIResultException(result.getErrorCode(), result.getMsg()));
                                }
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .retryWhen(new ApiRetryFunc(3,
                                1000));
            }
        };
    }

    public static <T> ObservableTransformer<T, T> uiScheduler() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


}
