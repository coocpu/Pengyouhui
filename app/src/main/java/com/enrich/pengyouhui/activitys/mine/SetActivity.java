package com.enrich.pengyouhui.activitys.mine;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.enrich.pengyouhui.R;
import com.enrich.pengyouhui.activitys.MainActivity;
import com.enrich.pengyouhui.activitys.base.BaseActivity;
import com.enrich.pengyouhui.utils.LogUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @describe 设置界面
 * @date 2016年9月13日
 */
public class SetActivity extends BaseActivity {
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.tv_current_version)
    TextView mTvCurrentVersion;
    @InjectView(R.id.iv_title_back)
    ImageView mIvTitleBack;

    private String version;//版本号

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
        setContentView(R.layout.activity_setting);
    }

    @Override
    public void initView() {
        getCurrentVersion();//获取当前版本号
        mIvTitleBack.setVisibility(View.VISIBLE);
        mTvTitle.setText(getString(R.string.str_mine_setting));
        mTvCurrentVersion.setText(String.format(getString(R.string.str_current_version), version));
    }

    @Override
    public void initDate() {

    }

    @Override
    public void initEvent() {

    }

    @OnClick({R.id.iv_title_back, R.id.rl_current_version,
            R.id.tv_change_password, R.id.tv_about, R.id.tv_clear_history_data, R.id.tv_login_out})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                this.finish();
                break;
            case R.id.rl_current_version://当前版本
                break;
            case R.id.tv_change_password://修改密码
                Intent changePwdIntent = new Intent(mContext, ChangePwdActivity.class);
                startActivity(changePwdIntent);
                break;
            case R.id.tv_about://关于我们
                break;
            case R.id.tv_clear_history_data://清空历史数据
                break;
            case R.id.tv_login_out://退出当前账户
                ActivityManager manager = (ActivityManager) SetActivity.this
                        .getSystemService(Context.ACTIVITY_SERVICE);
                manager.killBackgroundProcesses("com.enrich.pengyouhui");
                startActivity(MainActivity.class);
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 获取当前版本信息
     */
    private void getCurrentVersion() {
        // 获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo;
        try {
            packInfo = packageManager.getPackageInfo(getPackageName(), 0);
            version = packInfo.versionName;
            LogUtils.e(version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
