package com.it.onex;


import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Linsa on 2018/3/26:10:46.
 * des:
 */

public interface GetRequest_Interface {

    //获取金山词霸的网络请求

    @GET("ajax.php?a=fy&f=auto&t=auto&w=love")
    Observable<Translation> getCall();


    @GET("ajax.php?a=fy&f=auto&t=auto&w=register")
    Observable<Translation1> getCall1();

    @GET("ajax.php?a=fy&f=auto&t=auto&w=login")
    Observable<Translation2> getCall_2();
}
