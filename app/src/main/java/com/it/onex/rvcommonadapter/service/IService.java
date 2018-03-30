package com.it.onex.rvcommonadapter.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * Created by Linsa on 2018/3/29:14:50.
 * des:自定义Service实现方法
 */

public interface IService extends IProvider {

    void sayHello(Context context);
}
