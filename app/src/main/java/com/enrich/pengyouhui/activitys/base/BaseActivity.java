package com.enrich.pengyouhui.activitys.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.enrich.pengyouhui.R;
import com.enrich.pengyouhui.bean.event.CommonPostBean;
import com.enrich.pengyouhui.utils.Constants;
import com.enrich.pengyouhui.utils.StringUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * @describe 所有Activity的基类
 * @date 2016年9月8日
 */
public abstract class BaseActivity extends BaseListenerActivity {
    protected Context mContext;

    protected Activity mActivity;

    protected SystemBarTintManager sbtm;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mActivity = this;
        EventBus.getDefault().register(this);
        onLayout();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        sbtm = new SystemBarTintManager(mActivity);
        sbtm.setStatusBarTintEnabled(true);
        sbtm.setStatusBarTintResource(R.color.color_common_bar_base);//设置导航栏背景颜色

    }

    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public abstract void onLayout();

    public abstract void initView();

    public abstract void initDate();

    public abstract void initEvent();

    /**
     * @param commonPostBean 传递的对象类型
     * @describe 用于Eventbus接受数据的方法
     * 注意：修饰符必须为public
     */
    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onMainEvent(CommonPostBean commonPostBean) {
        onEventBusPostResult(commonPostBean);
    }

    protected void onEventBusPostResult(CommonPostBean commonPostBean) {

    }

    public void showToast(String text) {
        if (StringUtils.isEmpty(text))
            return;
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int resId) {
        String test = getString(resId);
        showToast(test);
    }

    public void startActivity(Class cls) {
        startActivity(cls, null);
    }

    public void startActivity(Class cls, String data) {
        if (cls == null)
            return;
        Intent intent = new Intent(mContext, cls);
        if (!StringUtils.isEmpty(data))
            intent.putExtra(Constants.KEY, data);
        startActivity(intent);
    }

    public void showLoad(String msg) {
        mProgressDialog = new ProgressDialog(mContext, R.style.theme_customer_progress_dialog);
        if (StringUtils.isEmpty(msg))
            mProgressDialog.setMessage("正在请求中.....");
        else
            mProgressDialog.setMessage(msg);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
    }

    public void dismiss() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
