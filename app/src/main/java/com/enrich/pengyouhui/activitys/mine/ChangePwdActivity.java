package com.enrich.pengyouhui.activitys.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.enrich.pengyouhui.R;
import com.enrich.pengyouhui.activitys.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @describe 修改密码
 * @date 2016年9月19日
 */
public class ChangePwdActivity extends BaseActivity {
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.et_user_mobile)
    EditText mEtUserMobile;
    @InjectView(R.id.et_new_pwd)
    EditText mEtNewPwd;
    @InjectView(R.id.et_again_new_pwd)
    EditText mEtAgainNewPwd;
    @InjectView(R.id.iv_title_back)
    ImageView mIvTitleBack;

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
        setContentView(R.layout.activity_change_pwd);
    }

    @Override
    public void initView() {
        mTvTitle.setText(getString(R.string.str_change_password));
        mIvTitleBack.setVisibility(View.VISIBLE);
    }

    @Override
    public void initDate() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.iv_title_back, R.id.tv_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                this.finish();
                break;
            case R.id.tv_ok://确定修改
                break;
        }
    }
}
