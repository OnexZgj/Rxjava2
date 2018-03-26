package com.it.onex.rvcommonadapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Linsa on 2018/3/21:11:30.
 * des:
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {


    public List<T> mDatas;
    private int mLayoutId;
    private LayoutInflater mInflater;
    public Context mContext;

    public CommonAdapter(Context context, int layoutId, List<T> datas)
    {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ViewHolder viewHolder = ViewHolder.createViewHolder(mContext, parent, mLayoutId);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        convert(holder, mDatas.get(position),position);
    }

    public abstract  void convert(ViewHolder holder, T t,int position) ;

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public List<T> getDatas() {
        return mDatas;
    }

}
