package com.hong_world.myapp;

import android.app.Application;

import com.hong_world.library.view.status.callback.CustomCallback;
import com.hong_world.library.view.status.callback.EmptyCallback;
import com.hong_world.library.view.status.callback.ErrorCallback;
import com.hong_world.library.view.status.callback.LoadingCallback;
import com.hong_world.library.view.status.callback.TimeoutCallback;
import com.hong_world.myapp.bean.DaoMaster;
import com.hong_world.myapp.bean.DaoSession;
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
