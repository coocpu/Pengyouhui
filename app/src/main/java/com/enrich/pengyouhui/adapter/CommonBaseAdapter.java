package com.enrich.pengyouhui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by user on 2015/11/24.
 */
public class CommonBaseAdapter extends BaseAdapter {
    public CommonImp imp;

    @Override
    public int getCount() {
        if (imp == null)
            return 0;
        return imp.getCommonCount();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public interface CommonImp {
        int getCommonCount();
    }
}
