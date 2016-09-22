package com.enrich.pengyouhui.activitys;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.enrich.pengyouhui.R;
import com.enrich.pengyouhui.activitys.base.BaseActivity;
import com.enrich.pengyouhui.fragment.CommonUseFragment;
import com.enrich.pengyouhui.fragment.MainFragment;
import com.enrich.pengyouhui.fragment.MineFragment;
import com.enrich.pengyouhui.fragment.TicketFragment;
import com.enrich.pengyouhui.support.DropDownAnim;
import com.enrich.pengyouhui.support.FragmentTabHost;
import com.enrich.pengyouhui.utils.LogUtils;
import com.enrich.pengyouhui.utils.ViewUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainTestActivity extends BaseActivity {

    FragmentTabHost mTabhost;
    @InjectView(R.id.id_tip)
    ImageView mIdTip;

    private String texts[];//底部导航条文字

    private int current = 0;//当前页

    private boolean isVisibile = false;
    private int imageButton[] = {
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
    };

    private Class fragmentArray[] = {
            MainFragment.class,
            CommonUseFragment.class,
            TicketFragment.class,
            MineFragment.class
    };
    private MainFragment mainFragment;
    private CommonUseFragment commonUseFragment;
    private TicketFragment ticketFragment;
    private MineFragment mineFragment;
    private Animation animationDown;
    private Animation animationUp;
    private boolean isExpand = false;

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
        setContentView(R.layout.activity_main_test);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof MainFragment) {
            mainFragment = (MainFragment) fragment;
        } else if (fragment instanceof CommonUseFragment) {
            commonUseFragment = (CommonUseFragment) fragment;
        } else if (fragment instanceof TicketFragment) {
            ticketFragment = (TicketFragment) fragment;
        } else if (fragment instanceof MineFragment) {
            mineFragment = (MineFragment) fragment;
        }
    }

    @Override
    public void initView() {
        mTabhost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabhost.setup(mContext, getSupportFragmentManager(), R.id.maincontent);
        texts = getResources().getStringArray(R.array.array_main_tabs);//获取底部导航条文字
        for (int i = 0; i < texts.length; i++) {
            TabHost.TabSpec spec = mTabhost.newTabSpec(texts[i]).setIndicator(getView(i));
            mTabhost.addTab(spec, fragmentArray[i], null);
            mTabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#aa1c1c1c"));
        }
        mTabhost.setCurrentTab(current);
        mTabhost.setVisibility(View.GONE);
    }

    @Override
    public void initDate() {

    }

    @Override
    public void initEvent() {

    }

    private View getView(int pos) {
        View view = View.inflate(MainTestActivity.this, R.layout.fragment_tabhost_tabcontent, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        TextView textView = (TextView) view.findViewById(R.id.text);
        imageView.setImageResource(imageButton[pos]);
        textView.setText(texts[pos]);
        return view;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @OnClick({R.id.id_tip})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_tip:
                LogUtils.e("你点击了。。。。");
                int height = ViewUtils.getViewMesureSpec(mTabhost)[1];
                //                clearAnimation();
                if (!isExpand) {
                    if (animationDown == null) {
                        animationDown = new DropDownAnim(mTabhost,
                                height, true);
                        animationDown.setDuration(300); // SUPPRESS CHECKSTYLE
                    }
                    mTabhost.startAnimation(animationDown);
                    mIdTip.startAnimation(animationDown);
                    mIdTip.setImageResource(R.mipmap.update_detail_down);
                    isExpand = true;
                } else {
                    isExpand = false;
                    if (animationUp == null) {
                        animationUp = new DropDownAnim(mTabhost,
                                height, false);
                        animationUp.setDuration(300); // SUPPRESS CHECKSTYLE
                    }
                    mIdTip.startAnimation(animationUp);
                    mTabhost.startAnimation(animationUp);
                    mIdTip.setImageResource(R.mipmap.update_detail_up);
                }
                break;
            default:
                break;
        }
    }
}
