package com.gary.weatherdemo.utils;

import android.os.Handler;
import android.os.Looper;

public class HandlerUtil {
    private final static Handler uiHandler = new Handler(Looper.getMainLooper());

    public static boolean isInUIThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static void runInUiThread(Runnable runnable) {
        if (isInUIThread()) {
            runnable.run();
        } else {
            uiHandler.post(runnable);
        }
    }
}
