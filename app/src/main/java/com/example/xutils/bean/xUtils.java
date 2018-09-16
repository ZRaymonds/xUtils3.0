package com.example.xutils.bean;

import android.os.Environment;

import org.xutils.DbManager;

import java.io.File;

/**
 * Created by ming on 2018/09/16.
 */

public class xUtils {

    private static DbManager.DaoConfig daoConfig;

    public static DbManager.DaoConfig getDaoConfig() {
        if (daoConfig == null){
//            File file = new File(Environment.getDataDirectory().getPath());
            daoConfig = new DbManager.DaoConfig()
                    .setDbName("test.sb")
//                    .setDbDir(file)
                    .setDbVersion(1)
                    .setAllowTransaction(true)
                    .setDbOpenListener(new DbManager.DbOpenListener() {
                        @Override
                        public void onDbOpened(DbManager db) {
                            // 开启WAL, 对写入加速提升巨大
                            db.getDatabase().enableWriteAheadLogging();
                        }
                    })
                    .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                        @Override
                        public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                            // db.addColumn(...);
                            // db.dropTable(...);
                            // ...
                            // or
                            // db.dropDb();
                        }
                    });
        }
        return daoConfig;
    }
}
