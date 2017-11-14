package com.hong_world.myapp;

import android.app.Application;

import com.hong_world.myapp.bean.DaoMaster;
import com.hong_world.myapp.bean.DaoSession;

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
