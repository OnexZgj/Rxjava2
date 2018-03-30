package com.it.onex.rvcommonadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.it.onex.rvcommonadapter.service.IService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = "/com/WebActivity")
public class WebActivity extends AppCompatActivity {

    @BindView(R.id.wb_aw)
    WebView wbAw;
    @BindView(R.id.btn_aw_service1)
    Button btnAwService1;
    @BindView(R.id.btn_aw_service2)
    Button btnAwService2;
    @BindView(R.id.btn_aw_test_intercepter)
    Button btnAwTestIntercepter;


    @Autowired(name = "/service/hello")
    IService service;

    @Autowired(name = "/service/hello2")
    IService service2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
        wbAw.loadUrl("file:///android_asset/onex.html");
    }

    @OnClick({R.id.btn_aw_service1, R.id.btn_aw_service2, R.id.btn_aw_test_intercepter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_aw_service1:
                service.sayHello(this);

                break;
            case R.id.btn_aw_service2:
                service2.sayHello(this);
                break;
            case R.id.btn_aw_test_intercepter:
                //测试拦截器的使用
                ARouter.getInstance()
                        .build("/com/URLActivity1")
                        .navigation(this, new NavigationCallback() {
                            @Override
                            public void onFound(Postcard postcard) {
                                Log.d("ARouter", "被拦截了");
                            }

                            @Override
                            public void onLost(Postcard postcard) {

                                Log.d("ARouter", "被拦截了");
                            }


                            @Override
                            public void onArrival(Postcard postcard) {
                                Log.d("ARouter", "收到了相关的路由" + postcard.getPath());
                            }


                            @Override
                            public void onInterrupt(Postcard postcard) {
                                Log.d("ARouter", "被拦截了");
                            }

                        });


                break;
        }
    }
}
