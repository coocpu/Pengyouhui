package com.enrich.pengyouhui.fragment.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.enrich.pengyouhui.R;
import com.enrich.pengyouhui.bean.event.CommonPostBean;
import com.enrich.pengyouhui.utils.StringUtils;

import java.util.concurrent.CopyOnWriteArrayList;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * @describe Fragment基类
 * @date 2016年9月8日
 */
public class BaseFragment extends BaseListenerFragment {
    protected Context mContext;
    protected Activity mActivity;
    protected View rootView;

    /**
     * 异步任务的集合
     */
    protected CopyOnWriteArrayList<AsyncTask> asyncTasksList = new CopyOnWriteArrayList<>();

    private ProgressDialog mProgressDialog;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = activity;
        this.mActivity = activity;
        EventBus.getDefault().register(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initDate();
        initEvent();
    }

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

    public void initView() {
    }

    public void initDate() {
    }

    public void initEvent() {
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

    /**
     * 将异步任务的对象放入全局集合
     *
     * @param task
     */
    protected void putAsyncTask(AsyncTask task) {
        if (asyncTasksList != null) {
            if (!asyncTasksList.contains(task)) {
                asyncTasksList.add(task);
            }
        }
    }

    /**
     * 取消指定异步任务
     *
     * @param task
     */
    protected void cancleAsyncTask(AsyncTask task) {
        task.cancel(true);
        if (asyncTasksList != null && asyncTasksList.contains(task))
            asyncTasksList.remove(task);
    }

    /**
     * 取消所有异步任务
     */
    protected void cancleAllAsyncTask() {
        if (asyncTasksList != null)
            for (AsyncTask task : asyncTasksList) {
                if (task != null && !task.isCancelled())
                    task.cancel(true);
                asyncTasksList.remove(task);
            }
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
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }
}
