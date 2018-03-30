package com.it.onex.rvcommonadapter.base;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by Linsa on 2018/3/29:09:47.
 * des:初始化ARouter组件
 */

public class MyApp extends Application {

    private static Context mContext;
    private boolean isDebug=true;

    public static Context getContext() {

        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (mContext==null){
            mContext=this;
        }

        if (isDebug){
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
