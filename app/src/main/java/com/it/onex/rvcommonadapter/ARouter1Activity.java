package com.it.onex.rvcommonadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.it.onex.rvcommonadapter.bean.Man;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 测试Arouter的使用
 *
 * @author Linsa
 * @time 2018-03-29
 */
public class ARouter1Activity extends AppCompatActivity {

    @BindView(R.id.tv_show)
    TextView tvShow;
    @BindView(R.id.btn_aa_intent)
    Button btnAaIntent;
    private String TAG= "ARouter1Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arouter1);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_aa_intent})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_aa_intent:

                Man man = new Man("OneX","男");
                ARouter.getInstance().build("/com/ARouter2Activity")
                        .withObject("obj",man)
                        .withString("name","onex")
                        .withInt("age",21)
                        .navigation(this, new NavigationCallback() {
                            @Override
                            public void onFound(Postcard postcard) {
                                Log.d(TAG, "onFound: ");
                            }

                            @Override
                            public void onLost(Postcard postcard) {
                                Log.d(TAG, "onLost: ");
                            }

                            @Override
                            public void onArrival(Postcard postcard) {
                                Log.d(TAG, "onArrival: "+postcard.getPath());
                            }

                            @Override
                            public void onInterrupt(Postcard postcard) {

                            }
                        });

                break;
        }
    }
}
