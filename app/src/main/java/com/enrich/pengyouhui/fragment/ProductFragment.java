package com.enrich.pengyouhui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.enrich.pengyouhui.R;
import com.enrich.pengyouhui.activitys.CommonWebActivity;
import com.enrich.pengyouhui.bean.AdverBean;
import com.enrich.pengyouhui.bean.event.CommonPostBean;
import com.enrich.pengyouhui.bean.home.HomeBean;
import com.enrich.pengyouhui.fragment.base.BaseFragment;
import com.enrich.pengyouhui.utils.Constants;
import com.enrich.pengyouhui.views.sliderlayout.DescriptionAnimation;
import com.enrich.pengyouhui.views.sliderlayout.SliderLayout;
import com.enrich.pengyouhui.views.sliderlayout.SliderTypes.BaseSliderView;
import com.enrich.pengyouhui.views.sliderlayout.SliderTypes.TextSliderView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @describe product页面
 * @date 2016年9月12日
 */
public class ProductFragment extends BaseFragment {
    @InjectView(R.id.lv_refresh)
    PullToRefreshListView mRefresh;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    /**
     * 广告轮播
     */
    private SliderLayout sliderLayout;
    private TextSliderView textSliderView;
    private List<AdverBean> adverList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.frament_product, null);
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
        mTvTitle.setText(getString(R.string.app_name));
        ListView listView = mRefresh.getRefreshableView();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View headView = inflater.inflate(R.layout.product_headview, null);//添加产品的头部广告
        sliderLayout = (SliderLayout) headView.findViewById(R.id.sl_product_slider);
        listView.addHeaderView(headView);
    }

    /**
     * 初始化广告数据
     */
    private void initAdvData() {
        sliderLayout.removeAllSliders();
        for (final AdverBean ab : adverList) {
            textSliderView = new TextSliderView(mContext);
            textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {
                    Intent commonIntent = new Intent(mContext, CommonWebActivity.class);
                    commonIntent.putExtra(Constants.STR_URL, ab.getAdverHref());
                    commonIntent.putExtra(Constants.TITLE, ab.getStoreyName());
                    startActivity(commonIntent);
                }
            });
            textSliderView.description(ab.getStoreyName())
                    .image(ab.getAdverImgSrc());
            textSliderView.getBundle().putString("extra", ab.getStoreyName());
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
    }

    @Override
    protected void onEventBusPostResult(CommonPostBean commonPostBean) {
        super.onEventBusPostResult(commonPostBean);
        if (commonPostBean != null) {
            switch (commonPostBean.getPostCode()) {
                case 0:
                    HomeBean homeBean = (HomeBean) commonPostBean.getT();
                    mTvTitle.setText(homeBean.getTitle());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
