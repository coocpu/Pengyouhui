package com.enrich.pengyouhui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.enrich.pengyouhui.R;
import com.enrich.pengyouhui.activitys.mine.CouponActivity;
import com.enrich.pengyouhui.activitys.mine.LoginActivity;
import com.enrich.pengyouhui.activitys.mine.MyCollectActivity;
import com.enrich.pengyouhui.activitys.mine.account.MyAccountActivity;
import com.enrich.pengyouhui.activitys.mine.RegisterActivity;
import com.enrich.pengyouhui.activitys.mine.SetActivity;
import com.enrich.pengyouhui.activitys.mine.order.MyOrderActivity;
import com.enrich.pengyouhui.fragment.base.BaseFragment;
import com.enrich.pengyouhui.views.CircleImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @describe 我的界面
 * @date 2016年9月8日
 */
public class MineFragment extends BaseFragment {
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.civ_head)
    CircleImageView mCivHead;
    @InjectView(R.id.tv_login)
    TextView mTvLogin;
    @InjectView(R.id.tv_register)
    TextView mTvRegister;
    @InjectView(R.id.rl_no_login)
    RelativeLayout mRlNoLogin;
    @InjectView(R.id.tv_username)
    TextView mTvUsername;
    @InjectView(R.id.rl_has_login)
    RelativeLayout mRlHasLogin;
    @InjectView(R.id.rl_login)
    RelativeLayout mRlLogin;
    @InjectView(R.id.tv_no_payment_num)
    TextView mTvNoPaymentNum;
    @InjectView(R.id.line_no_payment)
    LinearLayout mLineNoPayment;
    @InjectView(R.id.tv_coupon_num)
    TextView mTvCouponNum;
    @InjectView(R.id.line_coupon)
    LinearLayout mLineCoupon;
    @InjectView(R.id.tv_collect_num)
    TextView mTvCollectNum;
    @InjectView(R.id.line_collect)
    LinearLayout mLineCollect;
    @InjectView(R.id.tv_myorder)
    TextView mTvMyorder;
    @InjectView(R.id.tv_account)
    TextView mTvAccount;
    @InjectView(R.id.tv_coupon)
    TextView mTvCoupon;
    @InjectView(R.id.tv_invoice)
    TextView mTvInvoice;
    @InjectView(R.id.tv_collect)
    TextView mTvCollect;
    @InjectView(R.id.tv_setting)
    TextView mTvSetting;
    @InjectView(R.id.iv_title_back)
    ImageView mIvTitleBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_mine, null);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void initView() {
        super.initView();
        mTvTitle.setText(getResources().getString(R.string.array_main_tabs_me));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.tv_login, R.id.tv_register, R.id.line_no_payment, R.id.line_coupon, R.id.line_collect, R.id.tv_myorder, R.id.tv_account, R.id.tv_coupon, R.id.tv_invoice, R.id.tv_collect, R.id.tv_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                Intent loginIntent = new Intent(mActivity, LoginActivity.class);
                startActivity(loginIntent);
                break;
            case R.id.tv_register:
                Intent registerIntent = new Intent(mActivity, RegisterActivity.class);
                startActivity(registerIntent);
                break;
            case R.id.line_no_payment:
                break;
            case R.id.line_coupon:
                break;
            case R.id.line_collect:
                break;
            case R.id.tv_myorder://我的订单
                Intent orderIntent = new Intent(mActivity, MyOrderActivity.class);
                startActivity(orderIntent);
                break;
            case R.id.tv_account://亲情账户
                Intent accountIntent = new Intent(mActivity, MyAccountActivity.class);
                startActivity(accountIntent);
                break;
            case R.id.tv_coupon://优惠券
                Intent couponIntent = new Intent(mActivity, CouponActivity.class);
                startActivity(couponIntent);
                break;
            case R.id.tv_invoice://发票
                showToast("你点击了发票");
                break;
            case R.id.tv_collect://收藏
                Intent collectIntent = new Intent(mActivity, MyCollectActivity.class);
                startActivity(collectIntent);
                break;
            case R.id.tv_setting://设置
                Intent setIntent = new Intent(mActivity, SetActivity.class);
                startActivity(setIntent);
                break;
            default:
                break;
        }
    }
}
