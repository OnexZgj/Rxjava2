package com.it.onex.rvcommonadapter.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by Linsa on 2018/3/29:09:47.
 * des:初始化ARouter组件
 */

public class MyApp extends Application {

    private boolean isDebug=true;

    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebug){
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
