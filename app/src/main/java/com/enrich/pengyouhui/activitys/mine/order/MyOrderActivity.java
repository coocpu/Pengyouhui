package com.enrich.pengyouhui.activitys.mine.order;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.enrich.pengyouhui.R;
import com.enrich.pengyouhui.activitys.base.BaseActivity;
import com.enrich.pengyouhui.fragment.base.BaseFragment;
import com.enrich.pengyouhui.fragment.order.AllOrderFragment;
import com.enrich.pengyouhui.fragment.order.NoCommentFragment;
import com.enrich.pengyouhui.fragment.order.NoPayFragment;
import com.enrich.pengyouhui.support.FragmentTabHost;
import com.enrich.pengyouhui.utils.ScreenUtils;
import com.enrich.pengyouhui.utils.ViewUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @describe 我的订单界面
 * @date 2016年9月18日
 */
public class MyOrderActivity extends BaseActivity {

    @InjectView(R.id.iv_title_back)
    ImageView mIvTitleBack;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(android.R.id.tabhost)
    FragmentTabHost fragmentTabHost;
    @InjectView(R.id.iv_scroll_line)
    ImageView ivScrollLine;
    @InjectView(R.id.id_scrollview)
    LinearLayout scrollview;


    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 图片宽度
    private int offset;// 动画图片偏移量

    private FragmentManager fragmentManager;
    private final Class<?>[] classes = {AllOrderFragment.class, NoPayFragment.class, NoCommentFragment.class};
    private final String[] tag = {"AllOrderFragment", "NoPayFragment", "NoCommentFragment"};
    private final int[] menu_layout = {
            R.layout.layout_tab_order_all, R.layout.layout_tab_order_nopay, R.layout.layout_tab_order_nocomment};
    private Fragment[] fragments = new Fragment[classes.length];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        initView();
        initDate();
        initEvent();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment != null) {
            if (fragment instanceof AllOrderFragment) {
                if (fragments[0] == null)
                    fragments[0] = fragment;
            } else if (fragment instanceof NoPayFragment) {
                if (fragments[1] == null)
                    fragments[1] = fragment;
            } else if (fragment instanceof NoCommentFragment) {
                if (fragments[2] == null)
                    fragments[2] = fragment;
            }
        }
    }

    @Override
    public void onLayout() {
        setContentView(R.layout.activity_myorder);
    }

    @Override
    public void initView() {
        mIvTitleBack.setVisibility(View.VISIBLE);
        mTvTitle.setText(getString(R.string.str_mine_myorder));
        bmpW = ScreenUtils.getScreenWidth(mContext) / classes.length;
        offset = bmpW;//此处特殊处理
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        ivScrollLine.setImageMatrix(matrix);// 设置动画初始位置
        fragmentManager = getSupportFragmentManager();
        fragmentTabHost.setup(mContext, fragmentManager, R.id.realtabcontent);
        for (int i = 0; i < classes.length; i++) {
            // 为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tab = fragmentTabHost.newTabSpec(tag[i]).setIndicator(getTabView(i));
            // 将对应Fragment添加到TabHost中
            BaseFragment bf = (BaseFragment) fragmentTabHost.addTab(tab, classes[i], null);
        }
        fragmentTabHost.getTabWidget().setDividerDrawable(null);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) scrollview.getLayoutParams();
        params.setMargins(0, ViewUtils.getViewMesureSpec(fragmentTabHost.getTabWidget().getChildAt(0))[1], 0, 0);
        scrollview.setLayoutParams(params);
    }

    @Override
    public void initDate() {

    }

    @Override
    public void initEvent() {
        for (int i = 0; i < classes.length; i++) {
            final int finalI = i;
            fragmentTabHost.getTabWidget().getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    scroll(finalI);
                    fragmentTabHost.setCurrentTab(finalI);
                }
            });
        }
    }

    private View getTabView(int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(mActivity);
        View view = layoutInflater.inflate(menu_layout[position], null);
        return view;
    }

    private void scroll(int index) {
        if (ivScrollLine == null)
            return;
        Animation animation = new TranslateAnimation(currIndex * bmpW, bmpW
                * index, 0, 0);
        currIndex = index;
        animation.setFillAfter(true);// True:图片停在动画结束位置
        animation.setDuration(400);
        ivScrollLine.startAnimation(animation);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
