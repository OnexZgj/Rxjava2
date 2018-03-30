package com.it.onex.rvcommonadapter.intercepter;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by Linsa on 2018/3/29:16:06.
 * des:
 */
public class MainLooper extends Handler {
    private static MainLooper instance = new MainLooper(Looper.getMainLooper());

    protected MainLooper(Looper looper) {
        super(looper);
    }

    public static MainLooper getInstance() {
        return instance;
    }

    public static void runOnUiThread(Runnable runnable) {
        if(Looper.getMainLooper().equals(Looper.myLooper())) {
            runnable.run();
        } else {
            instance.post(runnable);
        }

    }
}