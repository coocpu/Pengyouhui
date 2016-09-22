package com.enrich.pengyouhui.utils;

/**
 * @describe 常量管理类
 * @date 2016年9月8日
 */
public class Constants {

    public static final String SHAREPERENCE_NAME = "pengyouhui";
    public static final String KEY = "key";
    /**
     * 测试机地址
     */
    public static final String BASE_URL = "http://shopdev.3bund.com/";
    public static final String STR_URL = "url";
    public static final String TITLE = "title";

    public static final class QQ {
        public static final String APP_ID = "1105239809";
        public static final String APP_KEY = "L1Fr40D5PK9Mswnw";
    }

    public static final class WX {
        public static final String APP_ID = "wx1fc0fea331b0547f";
        public static final String APP_SECRET = "7e97368a32e90ba4f30878a64830b59c";
    }

    //3310986646
    public static final class Sina {
        public static final String APP_ID = "866641367";
        public static final String APP_KEY = "4093640779";
        public static final String APP_SECRET = "11d6420727303fa8780fcb07c4218985";
    }

    public static final class Alipay {
        public static final String APP_ID = "2016080101690395";
        public static final String PARTNER = "2088021780233408";
        public static final String SELLER = "2088021780233408";
        public static final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANj49FZu8+KUTURWclrmAuUfEJNC1pJhCnAEkJEpQ55oN5PanFB5B3d0bHjyKbRXondVrAGYckto/Z0kdJr2QDVVygVX8GxBXgJeOwtZEvBMMnDo6m1n1vMVMQrodRlnuT17rX3cHkkPovwj09wrTug6mxl0oAdMcOU8DpZabYydAgMBAAECgYEAmm1O0rnzoZ+2At6YU7QD+XmLvZRDzjl9SU8mafr5oqRAcl6VBP2vX8kgb1DEeBQrkU4Lt7L+HkgiwhaxW53LAhs7CIxleqVXWiUuudVHu20juysBsBwU7EIlT65m0eMZNmWTmfIFu04/kZkd38rvyMNkuMUpD8Ssp/iKU3CkbSECQQD19JrJwmIlpAq3ZNIHH9LSkYxZHGOmPQMVw0TYDkXaFfUx8Pnnmp95rOneYLPBh3HIS/QJ1URhtp8oHX1ZxAI1AkEA4dVXMihyW9y+bDNvvHffUNVXqyMd0/4H0mqbn/oze6nIgu9gt5GlDXac4QuDNOFO/YyY6xTCkS3GsyH4gyKtyQJBAJUAnbFzIHB7Q768xnxobBkC8gqeJ0Rzm+XYSWG2q9ymBsg9m7gStWBKP9hnrx6kRms4kFxQpj+oGh18WA9Erq0CQFBZ7fpktbvFoOMysfDm3cgPAHBdwEErQ3E0iUa0aRj44eTkcw8dfRJ7P3o69GYePFKuccJC0q+BEA+waJGQBzECQAgKFNdVYnOuA9zyXP4G9EUMZuqdJO86fXCMOQryyKxDhKg5eTHHkl6Biee3NXVtbBZdpZ6OWnJQvX9nAvo8CiA=";
    }

    public static final class RSA {
        public static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCq9sgl/MnfAtxGGzKfhFDXf7YcBXgYCktUMv4/OuHPoLvEoC3HWkPL2XwmwZ0pJX18B5hIsXmBGRClqwCh7xX+mluGYSb42GyuV6qMEDyYV30ItGzXZNfffYlJuVJouQg+IPIQsTI3w17m/IXsfOi+/+xAK9JFyLyxfIz2TdEQ6wIDAQAB";
    }

    /**
     * Intent 标志
     */
    public static class IntentTag {

        public static final String PWD_FLAG = "pwd_flag";//忘记密码和注册的标志
    }

    /**
     * fragmentTag管理
     */
    public static class FragmentTag {
        public static final String MAINFRAGMENT_TAG = "MainFragment";
        public static final String COMMONUSEFRAGMENT_TAG = "commonUseFragment";
        public static final String TICKETFRAGMENT_TAG = "TicketFragment";
        public static final String MINEFRAGMENT_TAG = "MineFragment";
        public static final String PRODUCTFRAGMENT_TAG = "ProductFragment";
    }

    /**
     * eventBus请求常量
     */
    public static class EventPostCode {
        public static final int THRID_LOGIN_HN = 0x0000;//海航登陆
        public static final int TOMIANFRAGMEN = 0x0001;//产品页面跳转首页
        public static final int TOCOMMONUSEFRAGMEN = 0x0002;//产品页面跳转常用
        public static final int TOTICKETFRAGMEN = 0x0003;//产品页面跳转券
        public static final int TOMINEFRAGMEN = 0x0004;//产品页面跳转我的
        public static final int SELECT_CITY_CODE = 0x0005;//选择城市
    }

    /**
     * 网络请求常量
     */
    public static class HttpPostCode {

    }

    /**
     * 用户信息类
     */
    public class UserInfo {
        public static final String USER_ACCOUNT = "userAccount";//用户名
    }


}
