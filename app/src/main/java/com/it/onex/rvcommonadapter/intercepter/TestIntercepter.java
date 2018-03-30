package com.it.onex.rvcommonadapter.intercepter;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

/**
 * Created by Linsa on 2018/3/29:15:42.
 * des:
 */
@Interceptor(priority = 7)
public class TestIntercepter implements IInterceptor {
    Context mContext;

    /**
     * The operation of this interceptor.
     *
     * @param postcard meta
     * @param callback cb
     */
    @Override
    public void process(final Postcard postcard, final InterceptorCallback callback) {
        if ("/com/URLActivity1".equals(postcard.getPath())) {


//            Log.e("onex", "process: 拦截了" );
//            Toast.makeText(mContext, "就是要拦截", Toast.LENGTH_SHORT).show();
//            callback.onInterrupt(null);
            Log.e("onex", "process: 不拦截" );

            postcard.withString("extra", "我是在拦截器中附加的参数");
            callback.onContinue(postcard);


//            final AlertDialog.Builder ab = new AlertDialog.Builder(MyApp.getContext());
//            ab.setCancelable(false);
//            ab.setTitle("温馨提醒");
//            ab.setMessage("想要跳转到Test4Activity么？(触发了\"/inter/test1\"拦截器，拦截了本次跳转)");
//            ab.setNegativeButton("继续", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    callback.onContinue(postcard);
//                }
//            });
//            ab.setNeutralButton("算了", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    callback.onInterrupt(null);
//                }
//            });
//            ab.setPositiveButton("加点料", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    postcard.withString("extra", "我是在拦截器中附加的参数");
//                    callback.onContinue(postcard);
//                }
//            });
//
//            MainLooper.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    ab.create().show();
//                }
//            });

        } else {
            callback.onContinue(postcard);
        }
    }

    /**
     * Do your init work in this method, it well be call when processor has been load.
     *
     * @param context ctx
     */
    @Override
    public void init(Context context) {
        mContext = context;
        Log.e("testService", TestIntercepter.class.getName() + " has init.");
    }

}
