package com.it.onex.rvcommonadapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
@Route(path = "/com/ARouter2Activity")
public class ARouter2Activity extends AppCompatActivity {

    @Autowired
    public String name;
    @Autowired
    int age;

    @BindView(R.id.tv_show2)
    TextView tvShow2;
    @BindView(R.id.btn_aa_intent2)
    Button btnAaIntent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arouter2);
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);


        tvShow2.setText("name :" +name +", age : "+age);

    }

    @OnClick({R.id.tv_show2, R.id.btn_aa_intent2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_show2:
                break;
            case R.id.btn_aa_intent2:
                startActivity(new Intent(ARouter2Activity.this,WebActivity.class));
                break;
        }
    }
}
