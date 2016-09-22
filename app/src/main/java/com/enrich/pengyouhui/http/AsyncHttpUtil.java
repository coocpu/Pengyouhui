package com.enrich.pengyouhui.http;

import com.enrich.pengyouhui.application.PYHApplication;
import com.enrich.pengyouhui.http.impl.HttpResponseListener;
import com.enrich.pengyouhui.utils.LogUtils;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * @describe AsyncHttpClient的封装工具类
 */
public class AsyncHttpUtil {
    private static AsyncHttpClient mHttpClient = new AsyncHttpClient();
    private static Gson gson = new Gson();

    public static void post(String url, RequestParams params, HttpResponseListener listener, final int requestCode) {
        if (params != null) {
            LogUtils.e("请求的接口：" + url);
            LogUtils.e("请求的参数：" + params.toString());
        }
        PersistentCookieStore mCookieStore = new PersistentCookieStore(PYHApplication.getInstance());
        mHttpClient.setCookieStore(mCookieStore);
        mHttpClient.post(PYHApplication.getInstance(), url, params, new JsonHttpResponseHandler() {
            /**
             * 解析为JsonObject
             * @param statusCode
             * @param headers
             * @param jsonObject
             */
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonObject) {
                super.onSuccess(statusCode, headers, jsonObject);
                if (jsonObject != null)
                    LogUtils.e("下载成功的JSONObject数据：" + jsonObject.toString());
                switch (requestCode) {
                    default:
                        break;
                }
            }

            /**
             * 解析成jsonArray
             * @param statusCode
             * @param headers
             * @param jsonArray
             */
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {
                super.onSuccess(statusCode, headers, jsonArray);
                if (jsonArray != null)
                    LogUtils.e("下载成功的JSONArray数据：" + jsonArray.toString());
                switch (requestCode) {
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
                super.onFailure(statusCode, headers, errorResponse, throwable);
                LogUtils.e("下载数据失败statusCode：" + statusCode + ",errorResponse：" + errorResponse);
                switch (requestCode) {
                    default:
                        break;
                }
            }
        });
    }

}
