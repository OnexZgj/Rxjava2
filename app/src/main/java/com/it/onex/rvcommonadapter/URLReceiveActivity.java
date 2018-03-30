package com.it.onex.rvcommonadapter;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * 接受Url的跳转的Activity,并进行转发的中间协调层
 */
public class URLReceiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urlreceive);


        //对URI 数据分发
        Uri uri = getIntent().getData();
        ARouter.getInstance().build(uri)
                .navigation(this, new NavCallback() {
            @Override
            public void onArrival(Postcard postcard) {
                finish();
            }
        });

    }
}
