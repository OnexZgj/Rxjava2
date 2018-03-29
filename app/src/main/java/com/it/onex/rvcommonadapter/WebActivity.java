package com.it.onex.rvcommonadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = "/com/WebActivity")
public class WebActivity extends AppCompatActivity {

    @BindView(R.id.wb_aw)
    WebView wbAw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);

        wbAw.loadUrl("file:///android_asset/onex.html");
    }
}
