package com.demo.universal.base;

import android.app.Application;
import android.content.Context;

public class BaseApp extends Application {
    private static Context context;
    private static BaseApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        app = this;

    }

    /**
     * 获取Application上下文
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 获取BaseApp实例
     * @return
     */
    public static BaseApp getApp() {
        return app;
    }
}
