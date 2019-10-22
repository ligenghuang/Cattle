package com.zhifeng.cattle.net.service;


import com.lgh.huanglib.retrofitlib.Api.BaseResultEntity;
import com.zhifeng.cattle.modules.AlipayOrderDto;
import com.zhifeng.cattle.modules.post.OrderCommentPost;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
* 
* @author lgh
* created at 2019/5/13 0013 14:38
*/
public interface HttpPostService {

//
//    @GET(WebUrlUtil.GET_ABOUT)
//    Observable<BaseResultEntity> getAbout();
//
//    @GET(WebUrlUtil.GET_ABOUT)
//    Observable<String> getAboutString();
//
//    @GET(WebUrlUtil.GET_ABOUT)
//    Call<BaseResultEntity> getAboutByCall();
//
//    @GET(WebUrlUtil.URL_GET_MAIN)
//    Observable<BaseResultEntity> getHome();


    /**
     * POST请求
     * @param url
     * @return
     */
    @POST
    Observable<BaseResultEntity> PostData(@Url String url);
    @POST
    Observable<BaseResultEntity> PostData(@Body Map<Object, Object> body, @Url String url);
    @POST
    Observable<BaseResultEntity> PostData(@Body RequestBody body, @Url String url);
    @POST
    Observable<BaseResultEntity> PostData2(@Body OrderCommentPost body, @Url String url);

    /**
     * 支付宝 支付
     * @param body
     * @param url
     * @return
     */
    @POST
    Observable<AlipayOrderDto> PostDataAli(@Body Map<Object, Object> body, @Url String url);

    /**
     * GET请求
     * @param url
     * @return
     */
    @GET
    Observable<BaseResultEntity> GetData(@Url String url);

    /**
     * 带code的get请求
     * @param id
     * @param url
     * @return
     */
    @GET
    Observable<BaseResultEntity> GetData(@Url String url, @Query("code") int id);



}
