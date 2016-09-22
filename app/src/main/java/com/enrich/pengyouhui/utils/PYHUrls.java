package com.enrich.pengyouhui.utils;

/**
 * @describe 请求网络地址类
 * @date 2016年9月8日
 */
public class PYHUrls {
    /**
     * HN登录
     *
     * @return
     */
    public static String getHNLoginUrl() {
        return Constants.BASE_URL + "/app/sso-v1/toLogin.htm";
    }
}
