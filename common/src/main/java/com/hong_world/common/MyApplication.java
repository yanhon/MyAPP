package com.hong_world.common;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hong_world.common.bean.DaoMaster;
import com.hong_world.common.bean.DaoSession;
import com.hong_world.library.view.status.callback.CustomCallback;
import com.hong_world.library.view.status.callback.EmptyCallback;
import com.hong_world.library.view.status.callback.ErrorCallback;
import com.hong_world.library.view.status.callback.LoadingCallback;
import com.hong_world.library.view.status.callback.TimeoutCallback;
import com.kingja.loadsir.core.LoadSir;

import org.greenrobot.greendao.database.Database;

/**
 * Date: 2017/11/13.17:32
 * Author: hong_world
 * Description:
 * Version:
 */

public class MyApplication extends Application {
    /**
     * A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher.
     */
    public static final boolean ENCRYPTED = true;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initILoaderManager();
        initGreenDao();
        initViewStatus();
        initArouter();
    }

    private void initArouter() {
        if (true) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
    }

    private void initViewStatus() {
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "notes-db-encrypted" : "notes-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    private void initILoaderManager() {
//        ILoaderManager.setLoader(new FrescoLoader());//外部定制图片加载库Fresco
//        ILoaderManager.getLoader().init(this);
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }

}
