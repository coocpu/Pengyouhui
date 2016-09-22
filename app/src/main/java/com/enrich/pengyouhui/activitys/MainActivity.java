package com.enrich.pengyouhui.activitys;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.enrich.pengyouhui.R;
import com.enrich.pengyouhui.activitys.base.BaseActivity;
import com.enrich.pengyouhui.bean.event.CommonPostBean;
import com.enrich.pengyouhui.fragment.CommonUseFragment;
import com.enrich.pengyouhui.fragment.MainFragment;
import com.enrich.pengyouhui.fragment.MineFragment;
import com.enrich.pengyouhui.fragment.TicketFragment;
import com.enrich.pengyouhui.utils.Constants;
import com.enrich.pengyouhui.views.ExpandableMenuView;
import com.enrich.pengyouhui.views.ExpandableMenuView.ExpandMenuListener;
import com.enrich.pengyouhui.views.ExpandableMenuView.ExpandType;
import com.enrich.pengyouhui.views.ExpandableMenuView.SwitchStatus;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @describe 测试首页界面
 */
public class MainActivity extends BaseActivity {

    @InjectView(R.id.expandableMenuView)
    ExpandableMenuView mExpandableMenuView;

    private FragmentManager mFragmentManager = getSupportFragmentManager();

    /**
     * 在该页面上的所有fragment
     */
    private MainFragment mMainFragment = new MainFragment();
    private CommonUseFragment mCommonUseFragment = new CommonUseFragment();
    private TicketFragment mTicketFragment = new TicketFragment();
    private MineFragment mMineFragment = new MineFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        initView();
        initDate();
        initEvent();
    }

    @Override
    public void onLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        mFragmentManager.beginTransaction()
                .add(R.id.fragment, mMainFragment, Constants.FragmentTag.MAINFRAGMENT_TAG)
                .add(R.id.fragment, mCommonUseFragment, Constants.FragmentTag.COMMONUSEFRAGMENT_TAG)
                .add(R.id.fragment, mTicketFragment, Constants.FragmentTag.TICKETFRAGMENT_TAG)
                .add(R.id.fragment, mMineFragment, Constants.FragmentTag.MINEFRAGMENT_TAG)
                .hide(mCommonUseFragment)
                .hide(mTicketFragment)
                .hide(mMineFragment)
                .show(mMainFragment)
                .commitAllowingStateLoss();
    }

    @Override
    public void initDate() {

    }

    @Override
    public void initEvent() {
        mExpandableMenuView.setExpandMenuListener(new ExpandMenuListener() {
            @Override
            public void onExpandClickListener(SwitchStatus status, ExpandType expandType, int position) {
                switch (position) {
                    case 0:
                        mFragmentManager.beginTransaction()
                                .hide(mFragmentManager.findFragmentByTag(Constants.FragmentTag.COMMONUSEFRAGMENT_TAG))
                                .hide(mFragmentManager.findFragmentByTag(Constants.FragmentTag.TICKETFRAGMENT_TAG))
                                .hide(mFragmentManager.findFragmentByTag(Constants.FragmentTag.MINEFRAGMENT_TAG))
                                .show(mFragmentManager.findFragmentByTag(Constants.FragmentTag.MAINFRAGMENT_TAG))
                                .commitAllowingStateLoss();
                        break;
                    case 1:
                        mFragmentManager.beginTransaction()
                                .hide(mFragmentManager.findFragmentByTag(Constants.FragmentTag.MAINFRAGMENT_TAG))
                                .hide(mFragmentManager.findFragmentByTag(Constants.FragmentTag.TICKETFRAGMENT_TAG))
                                .hide(mFragmentManager.findFragmentByTag(Constants.FragmentTag.MINEFRAGMENT_TAG))
                                .show(mFragmentManager.findFragmentByTag(Constants.FragmentTag.COMMONUSEFRAGMENT_TAG))
                                .commitAllowingStateLoss();
                        break;
                    case 2:
                        mFragmentManager.beginTransaction()
                                .hide(mFragmentManager.findFragmentByTag(Constants.FragmentTag.MAINFRAGMENT_TAG))
                                .hide(mFragmentManager.findFragmentByTag(Constants.FragmentTag.COMMONUSEFRAGMENT_TAG))
                                .hide(mFragmentManager.findFragmentByTag(Constants.FragmentTag.MINEFRAGMENT_TAG))
                                .show(mFragmentManager.findFragmentByTag(Constants.FragmentTag.TICKETFRAGMENT_TAG))
                                .commitAllowingStateLoss();
                        break;
                    case 3:
                        mFragmentManager.beginTransaction()
                                .hide(mFragmentManager.findFragmentByTag(Constants.FragmentTag.MAINFRAGMENT_TAG))
                                .hide(mFragmentManager.findFragmentByTag(Constants.FragmentTag.COMMONUSEFRAGMENT_TAG))
                                .hide(mFragmentManager.findFragmentByTag(Constants.FragmentTag.TICKETFRAGMENT_TAG))
                                .show(mFragmentManager.findFragmentByTag(Constants.FragmentTag.MINEFRAGMENT_TAG))
                                .commitAllowingStateLoss();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void onEventBusPostResult(CommonPostBean commonPostBean) {
        super.onEventBusPostResult(commonPostBean);
        if (commonPostBean != null) {
            switch (commonPostBean.getPostCode()) {
                case Constants.EventPostCode.TOMIANFRAGMEN://首页
                    mFragmentManager.beginTransaction()
                            .hide(mFragmentManager.findFragmentByTag(Constants.FragmentTag.COMMONUSEFRAGMENT_TAG))
                            .hide(mFragmentManager.findFragmentByTag(Constants.FragmentTag.TICKETFRAGMENT_TAG))
                            .hide(mFragmentManager.findFragmentByTag(Constants.FragmentTag.MINEFRAGMENT_TAG))
                            .show(mFragmentManager.findFragmentByTag(Constants.FragmentTag.MAINFRAGMENT_TAG))
                            .commitAllowingStateLoss();
                    break;
                case Constants.EventPostCode.TOCOMMONUSEFRAGMEN://常用
                    mFragmentManager.beginTransaction()
                            .hide(mFragmentManager.findFragmentByTag(Constants.FragmentTag.MAINFRAGMENT_TAG))
                            .hide(mFragmentManager.findFragmentByTag(Constants.FragmentTag.TICKETFRAGMENT_TAG))
                            .hide(mFragmentManager.findFragmentByTag(Constants.FragmentTag.MINEFRAGMENT_TAG))
                            .show(mFragmentManager.findFragmentByTag(Constants.FragmentTag.COMMONUSEFRAGMENT_TAG))
                            .commitAllowingStateLoss();
                    break;
                case Constants.EventPostCode.TOTICKETFRAGMEN://券
                    mFragmentManager.beginTransaction()
                            .hide(mFragmentManager.findFragmentByTag(Constants.FragmentTag.MAINFRAGMENT_TAG))
                            .hide(mFragmentManager.findFragmentByTag(Constants.FragmentTag.COMMONUSEFRAGMENT_TAG))
                            .hide(mFragmentManager.findFragmentByTag(Constants.FragmentTag.MINEFRAGMENT_TAG))
                            .show(mFragmentManager.findFragmentByTag(Constants.FragmentTag.TICKETFRAGMENT_TAG))
                            .commitAllowingStateLoss();
                    break;
                case Constants.EventPostCode.TOMINEFRAGMEN://我的
                    mFragmentManager.beginTransaction()
                            .hide(mFragmentManager.findFragmentByTag(Constants.FragmentTag.MAINFRAGMENT_TAG))
                            .hide(mFragmentManager.findFragmentByTag(Constants.FragmentTag.COMMONUSEFRAGMENT_TAG))
                            .hide(mFragmentManager.findFragmentByTag(Constants.FragmentTag.TICKETFRAGMENT_TAG))
                            .show(mFragmentManager.findFragmentByTag(Constants.FragmentTag.MINEFRAGMENT_TAG))
                            .commitAllowingStateLoss();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
