package com.it.onex.rvcommonadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = "/com/URLActivity2")
public class URLActivity2 extends AppCompatActivity {

    @BindView(R.id.tv_au_show)
    TextView tvAuShow;

    @Autowired
    String name;

    @Autowired
    int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url2);
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);

        tvAuShow.setText("name ：" + name + " , age:" + age);
    }
}
