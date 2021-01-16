package com.demo.universal.util;

import android.app.Activity;
import android.app.ActivityManager;

import com.demo.universal.activity.LoginActivity;

import java.util.HashSet;

public class ActivityManagerUtil {
    private static ActivityManagerUtil instance = new ActivityManagerUtil();
    private static HashSet<Activity> hashSet = new HashSet<>();

    private ActivityManagerUtil() {
    }

    public static ActivityManagerUtil getInstance() {
        return instance;
    }

    /**
     * 每一个Activity 在 onCreate 方法的时候，可以装入当前this
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        try {
            hashSet.add(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用此方法用于销毁所有的Activity
     */
    public void exit() {
        try {
            for (Activity activity : hashSet) {
                if (activity != null) {
                    boolean isLogin = activity instanceof LoginActivity;
                    //如果是登录页面就不销毁
                    if (!isLogin) {
                        activity.finish();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroyeActivity(Activity activity) {
        if (null == hashSet && hashSet.isEmpty()) {
            return;
        }
        if (hashSet.contains(activity)) {
            /**
             *  监听到 Activity销毁事件 将该Activity 从set中移除
             */
//            activity.finish();
            removeActivity(activity);
        }
    }

    private void removeActivity(Activity activity) {
        if (hashSet != null) {
            if (hashSet.contains(activity)) {
                hashSet.remove(activity);
            }
        }
    }
}
