package com.enrich.pengyouhui.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * View工具类
 *
 * @author shawn
 * @QQ 1369104508
 * @since 2016年3月1日
 */
public class ViewUtils {
    /**
     * 获得view 的宽高
     *
     * @param view
     * @return 以数组的形式返回
     */
    public static int[] getViewMesureSpec(View view) {
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        height = view.getMeasuredHeight();
        width = view.getMeasuredWidth();
        return new int[]{width, height};
    }

    public static int dp2px(Activity activity, float dp) {
        float density = ScreenUtils.getScreenDensity(activity);
        return dp2px(density, dp);
    }

    public static int dp2px(float density, float dp) {
        return (int) (dp * density + 0.5f);
    }

    public static int dp2px(Resources res, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
    }

    public static int dp2px(Context context, float dp) {
        if (null != context && context.getResources() != null && context.getResources().getDisplayMetrics() != null) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dp * scale + 0.5f);
        }

        return (int) dp;
    }

    public static int px2dip(Context context, float pxValue) {

        if (null != context && context.getResources() != null && context.getResources().getDisplayMetrics() != null) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (pxValue / scale + 0.5f);
        }

        return (int) pxValue;
    }

    /**
     * 为了匹配设置这种样式的文本————预估价格：%1$d元
     *
     * @param mContext
     * @param tv
     * @param stringId
     * @param textContent
     * @param colorId
     */
    public static void setText(Context mContext, TextView tv, int stringId, String textContent, int colorId) {
        if (mContext == null || tv == null || stringId == 0 || textContent == null || colorId == 0)
            return;
        String text = String.format(mContext.getResources().getString(stringId), textContent);
        int position = text.indexOf(String.valueOf(textContent));
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(colorId)),
                position, position + String.valueOf(textContent).length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        tv.setText(spannableStringBuilder);
    }

    public static void setUnenable(EditText editText) {
        editText.setFilters(
                new InputFilter[]{new InputFilter() {
                    public CharSequence filter(CharSequence src, int start, int end, Spanned dst, int dstart, int dend) {
                        return src.length() < 1 ? dst.subSequence(dstart, dend) : src;
                    }
                }
                });
    }

    public static void limitEdittextLength(EditText mEditText, final int mMaxLenth) {
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                int sourceLen = source.toString().trim().length();
                int destLen = dest.toString().trim().length();
                if (sourceLen + destLen > mMaxLenth) {
                    return "";
                }
                return source;
            }
        };
        mEditText.setFilters(FilterArray);
    }

    public static void setWidthHeight(TextView view, int width, int height) {
        view.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        view.setGravity(Gravity.CENTER_VERTICAL);
    }
}
