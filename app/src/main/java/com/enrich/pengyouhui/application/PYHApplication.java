package com.enrich.pengyouhui.application;

import android.app.Application;

import com.enrich.pengyouhui.utils.Constants;
import com.enrich.pengyouhui.utils.LogUtils;
import com.enrich.pengyouhui.utils.SharedPreferencesUtils;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * @describe Application基类
 * @date 2016年9月8日
 */
public class PYHApplication<T> extends Application {
    private static PYHApplication instance;
    private Map<String, T> map = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        if (instance == null) {
            instance = this;
        }
        LogUtils.isDebug = true;//开启日志，关闭为false

        initSharePlat();
    }

    private void initSharePlat() {
        PlatformConfig.setWeixin(Constants.WX.APP_ID, Constants.WX.APP_SECRET);
        PlatformConfig.setSinaWeibo(Constants.Sina.APP_KEY, Constants.Sina.APP_SECRET);
        Config.REDIRECT_URL = "http://sns.whalecloud.com/sina2/callback";//设置回调地址要跟微博开放平台设置的回调地址要一样
        PlatformConfig.setQQZone(Constants.QQ.APP_ID, Constants.QQ.APP_KEY);
    }

    public static synchronized PYHApplication getInstance() {
        if (instance == null) {
            instance = new PYHApplication();
        }
        SharedPreferencesUtils.init(instance, Constants.SHAREPERENCE_NAME);
        return instance;
    }

    public void setT(String key, T t) {
        map.put(key, t);
    }

    public T getT(String key) {
        return map.get(key);
    }

    public void removeT(String key) {
        if (map.containsKey(key))
            map.remove(key);
    }

    public void removeAll() {
        map.clear();
    }
}
