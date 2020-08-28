package com.kotlin.lib_connection.okHttp.request;

import com.kotlin.lib_connection.okHttp.listener.DisposeDataHandle;
import com.kotlin.lib_connection.okHttp.response.CommonFileCallback;
import com.kotlin.lib_connection.okHttp.response.CommonJsonCallBack;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 用来发送get,POST请求的工具类，包括设置一些请求的共用参数
 */
public class CommonOkHttpClient {
    private static final  int TIME_OUT = 30;                //超时时间
    private static OkHttpClient mOkHttpClient;
    //完成对okHttpClient的初始化
    static {
            OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
            okhttpClientBuilder.hostnameVerifier(new HostnameVerifier() {               //是否信任域名
                @Override
                public boolean verify(String hostname, SSLSession session) {            //返回true 表示默认是信任的
                    return true;
                }
            });
        /**ite
         * 添加公共的自定义请求头
         */
        okhttpClientBuilder.addInterceptor(new Interceptor() {                          //添加拦截器
            @Override
            public Response intercept( Chain chain) throws IOException {            //
                Request request = chain.request().newBuilder().addHeader("User-Agent","Imooc-Mobile").build();

                return chain.proceed(request);                                      //添加request到chain中
            }
        });
        okhttpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);             //定义超时时间
        okhttpClientBuilder.readTimeout(TIME_OUT,TimeUnit.SECONDS);                 //读取超时时间
        okhttpClientBuilder.writeTimeout(TIME_OUT,TimeUnit.SECONDS);                 //写超时时间
        okhttpClientBuilder.followRedirects(true);                                  //允许重定向
        mOkHttpClient = okhttpClientBuilder.build();


    }

    /**
     * get()                            发送get请求
     * post()                           发送post请求
     * downloadFiles                    文件下载
     */
    public static Call get(Request request, DisposeDataHandle handle){
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallBack(handle));
        return call;

    }
    public static Call post(Request request, DisposeDataHandle handle){
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallBack(handle));
        return call;

    }
    public static Call downloadFiles(Request request, DisposeDataHandle handle){
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonFileCallback(handle));
        return call;


    }


}
