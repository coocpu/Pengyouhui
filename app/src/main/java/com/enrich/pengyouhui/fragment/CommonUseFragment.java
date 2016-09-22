package com.enrich.pengyouhui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.enrich.pengyouhui.R;
import com.enrich.pengyouhui.fragment.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @describe 常用页面
 * @date 2016年9月8日
 */
public class CommonUseFragment extends BaseFragment {
    @InjectView(R.id.tv_title)
    TextView mTvTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_commonuse, null);
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
        mTvTitle.setText(getResources().getString(R.string.array_main_tabs_use));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
