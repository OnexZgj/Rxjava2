package com.it.onex.rvcommonadapter.service;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * Created by Linsa on 2018/3/29:14:52.
 * des:
 */
@Route(path = "/service/hello2", name = "测试服务")
public class MyService2 implements IService {
    @Override
    public void sayHello(Context context) {
        Toast.makeText(  context , "hello2", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void init(Context context) {

    }
}
