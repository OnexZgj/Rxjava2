package com.it.onex.rvcommonadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

@Route(path = "/com/URLActivity1")
public class URLActivity1 extends AppCompatActivity {


    @Autowired
    String extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url1);
        ARouter.getInstance().inject(this);
        Toast.makeText(this, "extra : "  +extra , Toast.LENGTH_SHORT).show();

    }
}
