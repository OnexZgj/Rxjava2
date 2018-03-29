package com.it.onex.rvcommonadapter;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * 测试Rxlifecycle的使用
 */
public class TestRxLifeActivity extends RxAppCompatActivity {

    private static final String TAG = "TestRxLifeActivity";
    private TextView textView;
    private Button btnAtrlDis;
    private Disposable subscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_rx_life);

        textView = findViewById(R.id.tv_atrl_show);
        btnAtrlDis = findViewById(R.id.btn_atrl_dis);


        RxView.clicks(btnAtrlDis).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (subscribe!=null && !subscribe.isDisposed()) {
                    Toast.makeText(TestRxLifeActivity.this, "取消了相关消息", Toast.LENGTH_SHORT).show();
                    subscribe.dispose();
                }

            }
        });




        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS);
        subscribe = observable.doOnDispose(new Action() {
            @Override
            public void run() throws Exception {
                Log.d(TAG, "run: 进行取消了相关的订阅事件!");
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        textView.setText(aLong + "");
                    }
                });

        //------------start -----------测试Rxlifecycle的使用 -----
//        Observable.interval(2, TimeUnit.SECONDS)
//                .doOnDispose(new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        Log.i(TAG, "Unsubscribing subscription from onCreate()");
//                    }
//                }).compose(this.<Long>bindUntilEvent(ActivityEvent.STOP))
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long num) throws Exception {
//                        textView.setText(num+"");
//                        Log.i(TAG, "Started in onCreate(), running until onPause(): " + num);
//                    }
//                });
        //------------end -----------测试Rxlifecycle的使用 -----

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
