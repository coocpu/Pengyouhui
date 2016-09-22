package com.enrich.pengyouhui.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.enrich.pengyouhui.R;
import com.enrich.pengyouhui.activitys.ProductActivity;
import com.enrich.pengyouhui.activitys.city.CitySelectActivity;
import com.enrich.pengyouhui.adapter.CommonAdapter;
import com.enrich.pengyouhui.adapter.ViewHolder;
import com.enrich.pengyouhui.bean.event.CommonPostBean;
import com.enrich.pengyouhui.bean.home.HomeBean;
import com.enrich.pengyouhui.fragment.base.BaseFragment;
import com.enrich.pengyouhui.utils.Constants;
import com.enrich.pengyouhui.utils.ScreenUtils;
import com.enrich.pengyouhui.views.GridViewForScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @describe 主页
 * @date 2016年9月8日
 */
public class MainFragment extends BaseFragment {

    @InjectView(R.id.tv_location)
    TextView mTvLocation;
    @InjectView(R.id.tv_weather)
    TextView mTvWeather;
    @InjectView(R.id.iv_home_scan)
    ImageView mIvHomeScan;
    @InjectView(R.id.gridView)
    GridViewForScrollView mGridView;

    private CommonAdapter<HomeBean> mAdapter;

    private HomeBean mHomeBean;//测试

    private List<HomeBean> mHomeList = new ArrayList<>();
    private int gvWidth;//GridView的宽度

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.frament_main, null);
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
        //        mTvTitle.setText(getResources().getString(R.string.array_main_tabs_home));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            gvWidth = (ScreenUtils.getScreenWidth(mContext) - mGridView.getHorizontalSpacing()
                    - mGridView.getPaddingLeft() - mGridView.getPaddingRight()) / 2;//计算GridView的宽度
            mGridView.setColumnWidth(gvWidth);
        }
        mAdapter = new CommonAdapter<HomeBean>(mContext, mHomeList, R.layout.item_gv_home) {
            @Override
            public void convert(ViewHolder helper, HomeBean item, int position) {
                helper.setText(R.id.cv_item_text, item.getTitle());
                CardView cardView = helper.getView(R.id.cardView);
                cardView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, gvWidth * 3 / 4));
                ImageView imageView = helper.getView(R.id.cv_item_img);
                Glide.with(mContext).load(item.getImgurl()).asBitmap().into(imageView);
            }
        };
        mGridView.setAdapter(mAdapter);
    }

    @Override
    public void initDate() {
        for (int i = 0; i < 10; i++) {
            mHomeBean = new HomeBean();
            mHomeBean.setTitle("我是标题" + i);
            mHomeBean.setImgurl(R.mipmap.ic_launcher);
            mHomeList.add(mHomeBean);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void initEvent() {
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent productIntent = new Intent(mActivity, ProductActivity.class);
                startActivity(productIntent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.tv_location, R.id.iv_home_scan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_location:
                Intent locationIntent = new Intent(mContext, CitySelectActivity.class);
                locationIntent.putExtra("city", mTvLocation.getText().toString().trim());
                startActivity(locationIntent);
                break;
            case R.id.iv_home_scan:
                showToast("你点击了扫一扫");
                break;
        }
    }

    @Override
    protected void onEventBusPostResult(CommonPostBean commonPostBean) {
        super.onEventBusPostResult(commonPostBean);
        if (commonPostBean != null) {
            switch (commonPostBean.getPostCode()) {
                case Constants.EventPostCode.SELECT_CITY_CODE://城市选择
                    String cityName = (String) commonPostBean.getT();
                    mTvLocation.setText(cityName);
                    break;
                default:
                    break;
            }
        }
    }
}
