package com.enrich.pengyouhui.activitys.mine.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.enrich.pengyouhui.R;
import com.enrich.pengyouhui.activitys.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @describe 确定订单页面
 * @date 2016年9月13日
 */
public class ConfirmOrderActivity extends BaseActivity {

    @InjectView(R.id.iv_title_back)
    ImageView mIvTitleBack;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;

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
        setContentView(R.layout.activity_confirm_order);
    }

    @Override
    public void initView() {
        mIvTitleBack.setVisibility(View.VISIBLE);
        mTvTitle.setText(getString(R.string.str_order_confirm));
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
