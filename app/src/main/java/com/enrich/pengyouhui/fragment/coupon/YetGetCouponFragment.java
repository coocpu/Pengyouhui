package com.enrich.pengyouhui.fragment.coupon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enrich.pengyouhui.R;
import com.enrich.pengyouhui.fragment.base.BaseFragment;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @describe 已领取优惠券
 * @date 2016年9月18日
 */
public class YetGetCouponFragment extends BaseFragment {

    @InjectView(R.id.refresh_view)
    PullToRefreshListView mRefreshView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_coupon_get, null);
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
    }

    @Override
    public void initDate() {
        super.initDate();
    }

    @Override
    public void initEvent() {
        super.initEvent();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
