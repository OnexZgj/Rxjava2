package com.it.onex.rvcommonadapter.adapter;

import android.content.Context;

import com.it.onex.rvcommonadapter.base.MultiItemCommonAdapter;
import com.it.onex.rvcommonadapter.base.MultiItemTypeSupport;
import com.it.onex.rvcommonadapter.base.ViewHolder;

import java.util.List;

/**
 * Created by Linsa on 2018/3/21:17:25.
 * des:
 */

public class SectionAdapter<T> extends MultiItemCommonAdapter<T>{


    public SectionAdapter(Context context, int layoutId, List<T> datas, MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, layoutId, datas, multiItemTypeSupport);
    }

    @Override
    public void convert(ViewHolder holder, T t, int position) {

    }
}
