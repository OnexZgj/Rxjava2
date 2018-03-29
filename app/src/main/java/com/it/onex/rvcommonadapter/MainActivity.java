package com.it.onex.rvcommonadapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.it.onex.rvcommonadapter.bean.Translation;
import com.it.onex.rvcommonadapter.bean.Translation1;
import com.it.onex.rvcommonadapter.bean.Translation2;
import com.it.onex.rvcommonadapter.base.CommonAdapter;
import com.it.onex.rvcommonadapter.base.ViewHolder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//TODO rxbus base on Rxjava2 implements
public class MainActivity extends AppCompatActivity {

    List<String> mData = Arrays.asList(Cheeses.NAMES);
    ArrayList<String> mDatas=new ArrayList<>(mData);
    private String TAG = "Main";
    private CommonAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnAmRxbindIng = findViewById(R.id.btn_am_rxbinding);
        EditText etAmTest = findViewById(R.id.et_am_test);
        SearchView searchView = findViewById(R.id.sv_am);

        CheckBox checkBox =  findViewById(R.id.cb_am);

        RxCompoundButton.checkedChanges(checkBox).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                btnAmRxbindIng.setEnabled(aBoolean);
                btnAmRxbindIng.setBackgroundColor(aBoolean? getResources().getColor(R.color.colorAccent) : getResources().getColor(R.color.colorPrimary));
            }
        });

        RxView.clicks(btnAmRxbindIng).throttleFirst(2,TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {

                        startActivity(new Intent(MainActivity.this,TestRxLifeActivity.class));
                    }

                });

        //查看文本相关的变化
        RxTextView.textChanges(etAmTest).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence charSequence) throws Exception {
                btnAmRxbindIng.setText(charSequence.toString());
            }
        });





        RxTextView.textChanges(etAmTest)
                .debounce(5000, TimeUnit.MILLISECONDS)
                .map(new Function<CharSequence, String>() {
                    @Override
                    public String apply(CharSequence charSequence) throws Exception {


                        return charSequence.toString();
                    }
                }).observeOn(Schedulers.io())
                .map(new Function<String, List<String>>() {
                    @Override
                    public List<String> apply(String s) throws Exception {
                        ArrayList<String> list=new ArrayList<>();
                        if (mDatas.contains(s)){
                            list.add(s);
                            return list;
                        }else {
                            return mDatas;
                        }
                        
                    
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Exception {
                        if (strings.size()==1) {
                            mDatas.add(0,strings.get(0));
                            if (adapter != null) {
                                adapter.notifyDataSetChanged();
                            }
                            Toast.makeText(MainActivity.this, "检索到：" + strings.get(0) +"已添加到第一项 " , Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "not find anything!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


        //how to use rxbinding implements the searchView




        //
//        getIntervalData();


//        getMap();


//        getResLogin();

//        getZip();


//        getContentCache();

        RecyclerView recyclerView = findViewById(R.id.rv_am);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        adapter = new CommonAdapter<String>(this, R.layout.item, mDatas) {

            @Override
            public void convert(ViewHolder holder, String s, final int position) {

                holder.setText(R.id.tv_item, s);
                holder.setOnClickListener(R.id.tv_item, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, mDatas.get(position), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);


    }

    /**
     * 获取内容缓存
     */
    private void getContentCache() {

        final String memoryCache = null ;
        final String diskCache="从硬盘上进行获取数据";

        //从内存中进行获取相关的数据
        Observable<String> memoryCacheObserable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

                if (memoryCache != null) {
                    e.onNext(memoryCache);
                } else {
                    e.onComplete();
                }
            }
        });


        //从硬盘上进行获取数据
        Observable<String> diskCacheObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

                if (diskCache != null) {
                    e.onNext(diskCache);
                } else {
                    e.onComplete();
                }
            }
        });

        //从网络上进行获取数据

        Observable<String> netObservable = Observable.just("从网络上获取相关数据");

        /*
         * concatenate 串联
         * 通过concat() 和 firstElement() 操作符实现缓存功能
         **/

        // 1. 通过concat() 合并memory、disk、network 3个被观察者的事件（即检查内存缓存、磁盘缓存 & 发送网络请求）
        //    并将它们按顺序串联成队列

        Observable.concat(memoryCacheObserable,diskCacheObservable,netObservable)
                .firstElement()
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("最终获取的数据来源 =  "+ s);
                    }
                });

    }

    /**
     * Map的操作符的使用
     */
    private void getMap() {
        Observable.just("1", "2", "3").map(new Function<String, String>() {
            @Override
            public String apply(String s) throws Exception {
                return s + "xxx";
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("s : " + s);
            }
        });
    }


    /**
     * 注册完自动登录的操作
     */
    private void getResLogin() {

        // 定义Observable接口类型的网络请求对象
        Observable<Translation1> observable1;
        final Observable<Translation2> observable2;

        // 步骤1：创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .build();

        // 步骤2：创建 网络请求接口 的实例
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        // 步骤3：采用Observable<...>形式 对 2个网络请求 进行封装
        observable1 = request.getCall1();
        observable2 = request.getCall_2();


        observable1.subscribeOn(Schedulers.io())               // （初始被观察者）切换到IO线程进行网络请求1
                .observeOn(AndroidSchedulers.mainThread())  // （新观察者）切换到主线程 处理网络请求1的结果
                .doOnNext(new Consumer<Translation1>() {
                    @Override
                    public void accept(Translation1 result) throws Exception {
                        System.out.println("第1次网络请求成功");
                        System.out.println(result.getContent().getWord_mean().get(0));
                        // 对第1次网络请求返回的结果进行操作 = 显示翻译结果
                    }
                })

                .observeOn(Schedulers.io())                 // （新被观察者，同时也是新观察者）切换到IO线程去发起登录请求
                // 特别注意：因为flatMap是对初始被观察者作变换，所以对于旧被观察者，它是新观察者，所以通过observeOn切换线程
                // 但对于初始观察者，它则是新的被观察者
                .flatMap(new Function<Translation1, ObservableSource<Translation2>>() { // 作变换，即作嵌套网络请求
                    @Override
                    public ObservableSource<Translation2> apply(Translation1 result) throws Exception {
                        // 将网络请求1转换成网络请求2，即发送网络请求2
                        return observable2;
                    }
                })

                .observeOn(AndroidSchedulers.mainThread())  // （初始观察者）切换到主线程 处理网络请求2的结果
                .subscribe(new Consumer<Translation2>() {
                    @Override
                    public void accept(Translation2 result) throws Exception {
                        System.out.println("第2次网络请求成功");
                        System.out.println(result.getContent().getWord_mean().get(0));

                        // 对第2次网络请求返回的结果进行操作 = 显示翻译结果
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("登录失败");
                    }
                });
    }

    /**
     * 实现网络无限次请求的操作
     */
    private void getIntervalData() {
        //进行网络轮训请求的操作
        //该例子发送的事件特点：延迟2s后发送事件，每隔1秒产生1个数字（从0开始递增1，无限个）
        Observable.interval(2, 2, TimeUnit.SECONDS)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        //在此处为无限次的调用此方法中的代码


                        System.out.println("第 " + aLong + " 次轮询");

                        Log.d(TAG, "第 " + aLong + " 次轮询");


                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
                                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                                .build();

                        // b. 创建 网络请求接口 的实例
                        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

                        Observable<Translation> observable = request.getCall();
                        observable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<Translation>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(Translation value) {
                                        Log.d(TAG, value.getContent().getWord_mean().get(0));
                                        System.out.println(value.getContent().getWord_mean().get(0));
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.d(TAG, "请求失败");
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }
                }).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Long value) {
                Log.d(TAG, "onNext: subscribe : " + value);
                System.out.println("onNext: subscribe : " + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    /**
     * 测试zip的操作的使用， 1A,2B,3C,4D
     * 按照最少的发送数据为observable的数量发送给observe
     *
     * return   最终接受的数据为:n. 登记，注册;记录;登记簿;自动记录器;  --------  n. 注册;登录;
     */
    private void getZip() {

        // 定义Observable接口类型的网络请求对象
        Observable<Translation1> observable1;
        Observable<Translation2> observable2;


        // 步骤1：创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .build();

        // 步骤2：创建 网络请求接口 的实例
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        // 步骤3：采用Observable<...>形式 对 2个网络请求 进行封装
        observable1 = request.getCall1().subscribeOn(Schedulers.io()); // 新开线程进行网络请求1
        observable2 = request.getCall_2().subscribeOn(Schedulers.io());// 新开线程进行网络请求2


        Observable.zip(observable1, observable2, new BiFunction<Translation1, Translation2, String>() {
            @Override
            public String apply(Translation1 translation1, Translation2 translation2) throws Exception {
                return translation1.getContent().getWord_mean().get(0) + "  --------  " + translation2.getContent().getWord_mean().get(0);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("最终接受的数据为:" + s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("转化单词失败!");
                    }
                });

    }
    // 可重试次数
    int maxConnectCount = 10;
    // 当前已重试次数
    int currentRetryCount = 0;
    // 重试等待时间
    int waitRetryTime = 0;

    /**
     * 创建一个网络异常的重试请求的链接
     */
    private void retryWhen(){


        // 步骤1：创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .build();

        // 步骤2：创建 网络请求接口 的实例
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        // 步骤3：采用Observable<...>形式 对 网络请求 进行封装
        Observable<Translation> observable = request.getCall();

        //步骤4: 进行请求失败后重试的代码的逻辑
        observable.retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {

              return  throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Exception {

                        System.out.println("发生了异常信息" +throwable.getMessage().toString());

                        /**
                         * 需求1：根据异常类型选择是否重试
                         * 即，当发生的异常 = 网络异常 = IO异常 才选择重试
                         */
                        if (throwable instanceof IOException){
                            System.out.println("属于IO异常的相关信息");


                            /**
                             * 需求2：限制重试次数
                             * 即，当已重试次数 < 设置的重试次数，才选择重试
                             */
                            if (currentRetryCount < maxConnectCount){
                                currentRetryCount++;
                                System.out.println("重试次数 = " + currentRetryCount);
                                // 设置等待时间，根据请求的次数的增多，延时的时间也增加
                                waitRetryTime = 1000 + currentRetryCount* 1000;
                                return Observable.just(1).delay(waitRetryTime, TimeUnit.MILLISECONDS);

                            }else{
                                return Observable.error(new Throwable("重试次数已超过设置次数 = " +currentRetryCount  + "，即 不再重试"));
                            }

                        }else{
                            return Observable.error(new Throwable("发生了非网络异常（非I/O异常）"));
                        }
                    }
                });
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Translation>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Translation value) {
                        // 接收服务器返回的数据
                        Log.d(TAG,  "发送成功");
                        System.out.println("发送成功" + value.getContent().getWord_mean().get(0));
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.getMessage().toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

}
