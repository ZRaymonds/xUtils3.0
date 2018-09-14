package com.example.xutils;

import android.app.Application;

import org.xutils.x;

/**
 * Created by ming on 2018/09/12.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this); //初始化
        x.Ext.setDebug(false); //是否输出debug日志, 开启debug会影响性能
    }
}
