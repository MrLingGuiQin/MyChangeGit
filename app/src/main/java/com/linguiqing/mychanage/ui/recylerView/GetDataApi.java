package com.linguiqing.mychanage.ui.recylerView;

import com.linguiqing.mychanage.app.UrlManager;
import com.linguiqing.mychanage.ui.retrofit.BannerData;
import com.linguiqing.mychanage.ui.retrofit.User;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * ***************************************
 * statement: 请求数据api
 * auther: lingguiqin
 * date created : 2017/8/16 0016
 * ***************************************
 */

public interface GetDataApi {

    //1、get请求不携带参数
    @GET(UrlManager.BANNER_URL)
    Call<BannerData> getBannerData();

    //2、get请求携带参数 ---> path
    // {id} 为占位符
    // eg: /homepage/loadAdInfoAndAppLogo/1.jhtml
    // eg: /homepage/loadAdInfoAndAppLogo/2.jhtml
    @GET("/homepage/loadAdInfoAndAppLogo/{id}.jhtml")
    Call<BannerData> getBannerData2(@Path("id") int num);


    //3、get请求携带参数 ---> ?id=1
    // eg : /homepage/loadAdInfoAndAppLogo.jhtml?id=num
    @GET("/homepage/loadAdInfoAndAppLogo.jhtml")
    Call<BannerData> getBannerData3(@Query("id") int num);

    //4、get请求携带多个参数 ---> map
    // eg : /homepage/loadAdInfoAndAppLogo.jhtml?id=num & name=ling
    // map.put("id","num");
    // map.put("name","ling")
    @GET("/homepage/loadAdInfoAndAppLogo.jhtml")
    Call<BannerData> getBannerData4(@QueryMap Map<String, String> map);

    //    post请求

    //4、Post请求携带User Json ---> User
    // eg : /homepage/loadAdInfoAndAppLogo.jhtml
    // body: {"id":"1","name":"ling"}
    // 传bean对象，自动转化成json串 服务器进行接受
    @POST("/homepage/loadAdInfoAndAppLogo.jhtml")
    Call<BannerData> getBannerData5(@Body User user);

    //5、Post请求，表单形式传递参数 给服务器传递 id 、name 两个参数
    // 服务器需要以表单的形式去获取参数
    @FormUrlEncoded
    @POST("/homepage/loadAdInfoAndAppLogo.jhtml")
    Call<BannerData> getBannerData5(@Field("id") String id, @Field("name") String name);

}
