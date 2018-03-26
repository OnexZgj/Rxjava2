package com.it.onex.rvcommonadapter.base;

/**
 * Created by Linsa on 2018/3/21:16:25.
 * des:
 */

public interface MultiItemTypeSupport<T> {
    int getLayoutId(int itemType);

    int getItemViewType(int position, T t);
}
