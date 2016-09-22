package com.enrich.pengyouhui.activitys.mine.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.enrich.pengyouhui.R;
import com.enrich.pengyouhui.activitys.base.BaseActivity;
import com.enrich.pengyouhui.adapter.CommonAdapter;
import com.enrich.pengyouhui.adapter.ViewHolder;
import com.enrich.pengyouhui.bean.TestAccountBean;
import com.enrich.pengyouhui.utils.StringUtils;
import com.enrich.pengyouhui.views.RoundImageView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @describe 亲情账户
 * @date 2016年9月18日
 */
public class MyAccountActivity extends BaseActivity {

    @InjectView(R.id.iv_title_back)
    ImageView mIvTitleBack;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.tv_accountdetail_totalaccount)
    TextView totalaccount;
    @InjectView(R.id.refresh_view)
    PullToRefreshListView mRefreshView;
    @InjectView(R.id.iv_accountdetail_icon)
    RoundImageView mIvAccountdetailIcon;

    private TestAccountBean mAccountBean;

    private List<TestAccountBean> list = new ArrayList<>();

    private CommonAdapter<TestAccountBean> adapter;

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
        setContentView(R.layout.activity_account);
    }

    @Override
    public void initView() {
        mIvTitleBack.setVisibility(View.VISIBLE);
        mTvTitle.setText(getResources().getString(R.string.str_mine_account));
    }

    @Override
    public void initDate() {
        adapter = new CommonAdapter<TestAccountBean>(mContext, list, R.layout.item_wallet_operation) {
            @Override
            public void convert(ViewHolder helper, TestAccountBean item, int position) {
                helper.setText(R.id.id_textview2, item.getTransactionTime());
                if (StringUtils.isEqual("1", item.getTransactionType())) {
                    helper.setText(R.id.id_textview, String.format(mContext.getString(R.string.str_accountdetail_fill), item.getTransactionAmount()));
                } else {
                    helper.setText(R.id.id_textview, String.format(mContext.getString(R.string.str_accountdetail_pay), item.getTransactionAmount()));
                }
            }
        };
        mRefreshView.setAdapter(adapter);
    }

    @Override
    public void initEvent() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.iv_title_back, R.id.tv_recharge, R.id.tv_to_others_recharge})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                this.finish();
                break;
            case R.id.tv_recharge://充值
                Intent rechargeIntent = new Intent(mActivity, RechargeActivity.class);
                startActivity(rechargeIntent);
                break;
            case R.id.tv_to_others_recharge://为他人充值
                Intent othersRechargeIntent = new Intent(mActivity, OthersRechargeActivity.class);
                startActivity(othersRechargeIntent);
                break;
        }
    }
}
