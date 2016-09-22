package com.enrich.pengyouhui.utils;

import android.util.Log;

/**
 * @describe log日志工具类
 * @date 2016年9月8日
 */
public class LogUtils {
    public static boolean isDebug = false;//统一管理日志开启关闭的控制

    private static final String TAG = "鹏友会"; //标识

    /**
     * @param msg
     * @describe 默认标识的log日志打印
     */
    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG, msg);
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG, msg);
    }

    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG, msg);
    }

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG, msg);
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Log.d(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.e(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.v(tag, msg);
    }
}
