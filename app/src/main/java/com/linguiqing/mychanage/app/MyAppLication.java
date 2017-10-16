package com.linguiqing.mychanage.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.facebook.stetho.Stetho;
import com.linguiqing.mychanage.ui.dagger.AppComponent;
import com.linguiqing.mychanage.ui.dagger.DaggerAppComponent;
import com.linguiqing.mychanage.ui.db.greendao.DaoMaster;
import com.linguiqing.mychanage.ui.db.greendao.DaoSession;
import com.linguiqing.mychanage.ui.db.natives.BookOpenHelper;
import com.linguiqing.mychanage.util.LogUtil;

import org.greenrobot.greendao.database.Database;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/2/26 0026
 * ***************************************
 */

public class MyAppLication extends Application {
    AppComponent mAppComponent;
    private static DaoSession mDaoSession;
    private SQLiteDatabase mBookDao;

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.init("My Change");
        mAppComponent = DaggerAppComponent.create();
        // 配置数据库
        setupDatabaseGreenDao();
        setupDatabaseNative();
        Stetho.initializeWithDefaults(this);
    }

    // 用android 原生的方式创建数据库
    private void setupDatabaseNative() {
        mBookDao = BookOpenHelper.getInstance(this).getWritableDatabase();
    }

    // 用greenDao 的方式创建数据库
    private void setupDatabaseGreenDao() {
        // 创建数据库为 store.db
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "bookstore.db");
        // 获取可写数据库
        Database db = helper.getWritableDb();
        // 获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        // 获取dao管理者
        mDaoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getBookDao() {
        return mBookDao;
    }

    // 获取全局的AppComponent
    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
