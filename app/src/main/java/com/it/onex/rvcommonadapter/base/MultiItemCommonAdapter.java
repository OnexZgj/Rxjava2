package com.it.onex.rvcommonadapter.base;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Linsa on 2018/3/21:16:28.
 * des:多种类型的item的支持的Adapter
 */

public abstract  class MultiItemCommonAdapter<T> extends CommonAdapter<T> {


    private MultiItemTypeSupport<T> mMultiItemTypeSupport;

    public MultiItemCommonAdapter(Context context, int layoutId, List<T> datas, MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, -1, datas);
        mMultiItemTypeSupport = multiItemTypeSupport;
    }


    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position,mDatas.get(position));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);
        ViewHolder holder = ViewHolder.createViewHolder(mContext, parent, layoutId);

        return holder;
    }
}
