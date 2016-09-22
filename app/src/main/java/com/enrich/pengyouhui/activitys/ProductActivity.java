package com.enrich.pengyouhui.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.enrich.pengyouhui.R;
import com.enrich.pengyouhui.activitys.base.BaseActivity;
import com.enrich.pengyouhui.bean.event.CommonPostBean;
import com.enrich.pengyouhui.utils.Constants;
import com.enrich.pengyouhui.views.ExpandableMenuView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

/**
 * @describe 产品界面
 * @date 2016年9月18日
 */
public class ProductActivity extends BaseActivity {

    @InjectView(R.id.expandableMenuView)
    ExpandableMenuView mExpandableMenuView;

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
        setContentView(R.layout.activity_product);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initDate() {

    }

    @Override
    public void initEvent() {
        mExpandableMenuView.setExpandMenuListener(new ExpandableMenuView.ExpandMenuListener() {
            @Override
            public void onExpandClickListener(ExpandableMenuView.SwitchStatus status, ExpandableMenuView.ExpandType expandType, int position) {
                switch (position) {
                    case 0:
                        startActivity(MainActivity.class);
                        EventBus.getDefault().post(new CommonPostBean(Constants.EventPostCode.TOMIANFRAGMEN, null));
                        break;
                    case 1:
                        startActivity(MainActivity.class);
                        EventBus.getDefault().post(new CommonPostBean(Constants.EventPostCode.TOCOMMONUSEFRAGMEN, null));
                        break;
                    case 2:
                        startActivity(MainActivity.class);
                        EventBus.getDefault().post(new CommonPostBean(Constants.EventPostCode.TOTICKETFRAGMEN, null));
                        break;
                    case 3:
                        startActivity(MainActivity.class);
                        EventBus.getDefault().post(new CommonPostBean(Constants.EventPostCode.TOMINEFRAGMEN, null));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
