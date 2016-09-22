package com.enrich.pengyouhui.activitys;

import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.enrich.pengyouhui.R;
import com.enrich.pengyouhui.activitys.base.BaseActivity;
import com.enrich.pengyouhui.utils.LogUtils;
import com.enrich.pengyouhui.utils.StringUtils;
import com.enrich.pengyouhui.views.alertview.AlertView;
import com.enrich.pengyouhui.views.alertview.OnItemClickListener;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @describe 公用的商品详情页面
 * @date 2016年9月9日
 */
public class CommonWebActivity extends BaseActivity {

    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.webView)
    WebView mWebView;
    private String pageTitle;//网页标题
    private String url;//网址

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        initView();
        initDate();
        initEvent();
    }

    @Override
    public void onLayout() {
        setContentView(R.layout.activity_shopdetails);
    }

    @Override
    public void initView() {
        mTvTitle.setText(getResources().getString(R.string.str_shop_detail));
    }

    @Override
    public void initDate() {
        if (!StringUtils.isEmpty(url)) {
            mWebView.loadUrl(url);
        } else {
            new AlertView(mContext.getString(R.string.str_base_notify),
                    mContext.getString(R.string.str_commonwebview_url_err),
                    mContext.getString(R.string.str_base_ok), null, null, mContext,
                    AlertView.Style.Alert, new OnItemClickListener() {
                @Override
                public void onItemClick(Object o, int position) {
                    finish();
                }
            }).show();
        }
        initWebView();
    }

    @Override
    public void initEvent() {

    }

    private void initWebView() {
        if (mWebView != null) {
            WebSettings wSet = mWebView.getSettings();
            wSet.setJavaScriptEnabled(true);
            wSet.setAllowFileAccess(true);
            wSet.setBuiltInZoomControls(true);
            wSet.setSupportZoom(true);
            /**
             *  设置网页布局类型：
             *  1、LayoutAlgorithm.NARROW_COLUMNS ： 适应内容大小
             *  2、LayoutAlgorithm.SINGLE_COLUMN: 适应屏幕，内容将自动缩放
             */
            wSet.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            wSet.setDefaultTextEncodingName("utf-8"); //设置文本编码
            wSet.setAppCacheEnabled(false);//修改
            //            wSet.setCacheMode(WebSettings.LOAD_DEFAULT);//设置缓存模式</span>
            wSet.setUseWideViewPort(true);
            wSet.setDomStorageEnabled(true);
            wSet.setDatabaseEnabled(true);
            mWebView.getSettings().setLoadWithOverviewMode(true);
            mWebView.requestFocus();        //任意比例缩放
            /*****************************************************************
             * 在点击请求的是链接时才会调用，重写此方法返回true表明点击网页里
             * 面的链接还是在当前的WebView里跳转，不会跳到浏览器上运行。
             *****************************************************************/
            mWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }

                @Override
                public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                    handler.proceed();//忽略证书（允许https协议）
                }
            });
            mWebView.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onReceivedTitle(WebView view, String title) {
                    super.onReceivedTitle(view, title);
                    pageTitle = title;
                    LogUtils.e("网页加载的title=" + pageTitle);
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.iv_title_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                this.finish();
                break;
            default:
                break;
        }
    }
}
