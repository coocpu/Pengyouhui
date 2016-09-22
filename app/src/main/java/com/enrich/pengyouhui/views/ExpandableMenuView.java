package com.enrich.pengyouhui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enrich.pengyouhui.R;

/***
 * @describe 底部导航条
 */
public class ExpandableMenuView extends LinearLayout {

    private ImageView tip;
    private TextView menu0;
    private TextView menu1;
    private TextView menu2;
    private TextView menu3;
    private LayoutInflater inflater;
    private Context context;
    private View contentView;

    private boolean isExpand = false;

    private OnClickListener onClickListener;
    private ExpandMenuListener expandMenuListener;
    private int mContentHeight = 0;
    private View mContentView;
    private int mTitleHeight = 0;
    private Animation animationDown;
    private Animation animationUp;

    public ExpandableMenuView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public ExpandableMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
        initEvent();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.mContentHeight == 0) {
            this.mContentView.measure(widthMeasureSpec, 0);
            this.mContentHeight = this.mContentView.getMeasuredHeight();
        }
        if (this.mTitleHeight == 0) {
            this.tip.measure(widthMeasureSpec, 0);
            this.mTitleHeight = this.tip.getMeasuredHeight();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void initView() {
        inflater = LayoutInflater.from(context);
        contentView = inflater.inflate(R.layout.layout_view_expandmenu, null);
        tip = (ImageView) contentView.findViewById(R.id.id_tip);
        mContentView = contentView.findViewById(R.id.id_layout_menu);
        menu0 = (TextView) contentView.findViewById(R.id.tv_menu_0);
        menu1 = (TextView) contentView.findViewById(R.id.tv_menu_1);
        menu2 = (TextView) contentView.findViewById(R.id.tv_menu_2);
        menu3 = (TextView) contentView.findViewById(R.id.tv_menu_3);
        mContentView.setVisibility(GONE);
        onClickListener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.id_tip:
                        if (expandMenuListener != null)
                            expandMenuListener.onExpandClickListener(isExpand ? SwitchStatus.ON : SwitchStatus.OFF, ExpandType.TIP, -1);
                        setExpandable();
                        break;
                    case R.id.tv_menu_0:
                        if (expandMenuListener != null)
                            expandMenuListener.onExpandClickListener(isExpand ? SwitchStatus.ON : SwitchStatus.OFF, ExpandType.MENU, 0);
                        break;
                    case R.id.tv_menu_1:
                        if (expandMenuListener != null)
                            expandMenuListener.onExpandClickListener(isExpand ? SwitchStatus.ON : SwitchStatus.OFF, ExpandType.MENU, 1);
                        break;
                    case R.id.tv_menu_2:
                        if (expandMenuListener != null)
                            expandMenuListener.onExpandClickListener(isExpand ? SwitchStatus.ON : SwitchStatus.OFF, ExpandType.MENU, 2);
                        break;
                    case R.id.tv_menu_3:
                        if (expandMenuListener != null)
                            expandMenuListener.onExpandClickListener(isExpand ? SwitchStatus.ON : SwitchStatus.OFF, ExpandType.MENU, 3);
                        break;
                    default:
                        break;
                }
            }
        };
        LayoutParams lParams = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        this.addView(contentView, lParams);
    }

    private void initEvent() {
        tip.setOnClickListener(onClickListener);
        menu0.setOnClickListener(onClickListener);
        menu1.setOnClickListener(onClickListener);
        menu2.setOnClickListener(onClickListener);
        menu3.setOnClickListener(onClickListener);
    }

    public void setExpandMenuListener(ExpandMenuListener expandMenuListener) {
        this.expandMenuListener = expandMenuListener;
    }

    public void setExpandable() {
        clearAnimation();
        if (!isExpand) {
            if (animationDown == null) {
                animationDown = new DropDownAnim(mContentView,
                        mContentHeight, true);
                animationDown.setDuration(300); // SUPPRESS CHECKSTYLE
            }
            startAnimation(animationDown);
            tip.setImageResource(R.mipmap.icon_down);
            isExpand = true;
        } else {
            isExpand = false;
            if (animationUp == null) {
                animationUp = new DropDownAnim(mContentView,
                        mContentHeight, false);
                animationUp.setDuration(300); // SUPPRESS CHECKSTYLE
            }
            startAnimation(animationUp);
            tip.setImageResource(R.mipmap.icon_up);
        }
        //            mContentView.setVisibility(isExpand ? View.GONE : View.VISIBLE);
        //            isExpand = !isExpand;
    }

    //    public void setExpandable(boolean isExpand) {
    //        this.isExpand = isExpand;
    //        clearAnimation();
    //        if (!isExpand) {
    //            if (animationDown == null) {
    //                animationDown = new DropDownAnim(mContentView,
    //                        mContentHeight, true);
    //                animationDown.setDuration(300); // SUPPRESS CHECKSTYLE
    //            }
    //            startAnimation(animationDown);
    //        } else {
    //            if (animationUp == null) {
    //                animationUp = new DropDownAnim(mContentView,
    //                        mContentHeight, false);
    //                animationUp.setDuration(300); // SUPPRESS CHECKSTYLE
    //            }
    //            startAnimation(animationUp);
    //        }
    //            mContentView.setVisibility(isExpand ? View.VISIBLE : View.GONE);
    //            this.isExpand = isExpand;
    //    }

    public interface ExpandMenuListener {
        void onExpandClickListener(SwitchStatus status, ExpandType expandType, int position);
    }

    class DropDownAnim extends Animation {
        /**
         * 目标的高度
         */
        private int targetHeight;
        /**
         * 目标view
         */
        private View view;
        /**
         * 是否向下展开
         */
        private boolean down;

        /**
         * 构造方法
         *
         * @param targetview 需要被展现的view
         * @param vieweight  目的高
         * @param isdown     true:向下展开，false:收起
         */
        public DropDownAnim(View targetview, int vieweight, boolean isdown) {
            this.view = targetview;
            this.targetHeight = vieweight;
            this.down = isdown;
        }

        //down的时候，interpolatedTime从0增长到1，这样newHeight也从0增长到targetHeight
        @Override
        protected void applyTransformation(float interpolatedTime,
                                           Transformation t) {
            int newHeight;
            if (down) {
                newHeight = (int) (targetHeight * interpolatedTime);
            } else {
                newHeight = (int) (targetHeight * (1 - interpolatedTime));
            }
            view.getLayoutParams().height = newHeight;
            view.requestLayout();
            if (view.getVisibility() == View.GONE) {
                view.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void initialize(int width, int height, int parentWidth,
                               int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
        }

        @Override
        public boolean willChangeBounds() {
            return true;
        }
    }

    public enum ExpandType {
        TIP, MENU
    }

    public enum SwitchStatus {
        ON, OFF
    }
}
