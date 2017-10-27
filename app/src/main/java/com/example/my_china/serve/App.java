package com.example.my_china.serve;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.alex.greendao.gen.DaoMaster;
import com.alex.greendao.gen.DaoSession;
import com.mob.MobApplication;

/**
 * Created by 吴肖光 on 2017/10/26.
 */
public class App extends MobApplication {
    static App app;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        getDaoSession();
    }

    public static App getApp(){
        return app;
    }

    public void getDaoSession() {
        DaoMaster.DevOpenHelper userDao = new DaoMaster.DevOpenHelper(this, "UserDao");
        SQLiteDatabase db = userDao.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getSessionDao(){
        return daoSession;
    }
}
