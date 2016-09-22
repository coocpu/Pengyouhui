package com.enrich.pengyouhui.fragment.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enrich.pengyouhui.R;
import com.enrich.pengyouhui.fragment.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * @describe 待评论
 * @date 2016年9月18日
 */
public class NoCommentFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_order_no_comment, null);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        ButterKnife.inject(this, rootView);
        return rootView;
    }
}
