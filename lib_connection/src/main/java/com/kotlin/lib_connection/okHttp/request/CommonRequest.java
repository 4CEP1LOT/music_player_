package com.kotlin.lib_connection.okHttp.request;

import android.app.DownloadManager;
import android.util.Log;

import com.kotlin.lib_connection.okHttp.request.RequestParams;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;


/**
 * 对外提供get/post文件请求
 */

public class CommonRequest {


    public static Request createPostRequest(String url, RequestParams params) {
        return createPostRequest(url, params, null);
    }

    /**
     * 可以带请求头的POST请求
     *
     * @param url     请求的url地址
     * @param params  请求参数
     * @param headers 请求头
     * @return
     */
    public static Request createPostRequest(String url, RequestParams params, RequestParams headers) {
        FormBody.Builder mFormBodyBuild = new FormBody.Builder();
        if (params != null) {
            //参数遍历
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                mFormBodyBuild.add(entry.getKey(), entry.getValue());
            }
        }

        /**
         *  添加请求头
         */
        Headers.Builder mHeaderBuild = new Headers.Builder();
        //请求头遍历
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.urlParams.entrySet()) {
                mHeaderBuild.add(entry.getKey(), entry.getValue());
            }
        }
        FormBody mFormBody = mFormBodyBuild.build();
        Headers mHeader = mHeaderBuild.build();
        Request request = new Request.Builder().url(url)
                .post(mFormBody)
                .headers(mHeader)
                .build();
        return request;
    }


//    public static Request createSearchGetRequest(String url, RequestParams params) {
//        Log.d("login","okhttp");
//
//        return createSearchGetRequest(url, params, null);
//    }
//
//
//    public static Request createSearchGetRequest(String url, RequestParams params, RequestParams headers) {
//        StringBuilder urlBuilder = new StringBuilder(url).append("?");
//        if (params != null) {
//            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
//                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue());      //通过HashMap中的append方法，添加出来GET请求的Url网址
//            }
//        }
//
//
//        //添加请求头
//        Headers.Builder mHeaderBuild = new Headers.Builder();
//        if (headers != null) {
//            for (Map.Entry<String, String> entry : headers.urlParams.entrySet()) {
//                mHeaderBuild.add(entry.getKey(), entry.getValue());
//            }
//        }
//
//        Headers mHeader = mHeaderBuild.build();
//
//        return new Request.Builder()
//                .url(urlBuilder.substring(0, urlBuilder.length() - 1))
//                .get()
//                .headers(mHeader)
//                .build();
//    }


    public static Request createSimpleGetRequest(String url) {
        //添加请求头

        return new Request.Builder()
                .url(url)
                .get()
                .build();
    }


    public static Request createGetRequest(String url, RequestParams params) {
        Log.d("login", "okhttp");

        return createGetRequest(url, params, null);
    }

    public static Request createGetRequest(String url, RequestParams params, RequestParams headers) {
        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&"); //通过HashMap中的append方法，添加出来GET请求的Url网址
            }
        }
        Headers.Builder mHeaderBuild = new Headers.Builder();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.urlParams.entrySet()) {
                mHeaderBuild.add(entry.getKey(), entry.getValue());
            }
        }

        Headers mHeader = mHeaderBuild.build();

        return new Request.Builder()
                .url(urlBuilder.substring(0, urlBuilder.length() - 1))
                .get()
                .headers(mHeader)
                .build();
    }

    public static Request createGetPlaylistRequest(String url, List<?> params) {
        Log.d("login", "okhttp");

        return createGetPlaylistRequest(url, params, null);
    }

    public static Request createGetPlaylistRequest(String url, List<?> params, RequestParams headers) {
        StringBuilder urlBuilder = new StringBuilder(url);
        if (params != null) {
            for (int i = 0;i<params.size();i++) {
                urlBuilder.append(params.get(i)).append(","); //通过HashMap中的append方法，添加出来GET请求的Url网址
            }
        }
        Headers.Builder mHeaderBuild = new Headers.Builder();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.urlParams.entrySet()) {
                mHeaderBuild.add(entry.getKey(), entry.getValue());
            }
        }

        Headers mHeader = mHeaderBuild.build();

        return new Request.Builder()
                .url(urlBuilder.substring(0, urlBuilder.length() - 1))
                .get()
                .headers(mHeader)
                .build();
    }

    public static Request createSongGetRequest(String url, String stringList) {
        Log.d("login", "okhttp");

        return createSongGetRequest(url, stringList,null);
    }


    public static Request createSongGetRequest(String url,String strings,RequestParams headers) {
        StringBuilder urlBuilder = new StringBuilder(url);
                urlBuilder.append(strings).append("&br=320000"); //通过HashMap中的append方法，添加出来GET请求的Url网址


        Headers.Builder mHeaderBuild = new Headers.Builder();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.urlParams.entrySet()) {
                mHeaderBuild.add(entry.getKey(), entry.getValue());
            }
        }

        Headers mHeader = mHeaderBuild.build();

        return new Request.Builder()
                .url(String.valueOf(urlBuilder))
                .get()
                .headers(mHeader)
                .build();
    }

//
//    /**
//     * 定义多媒体请求类型
//     *  MediaTYpe是okHttp用来处理多媒体文件请求的一个工具类
//     *
//     *
//     */
//    private static final MediaType FILE_TYPE = MediaType.parse("application/octal_stream");                                          //模仿浏览器定义请求
//
//    public static Request createMultiPostRequest(String url, RequestParams params) {
//        MultipartBody.Builder requestBody = new MultipartBody.Builder();
//        requestBody.setType(MultipartBody.FORM);                                                       //指定上传方式为表单提交                                                                         //指定上传方式为表单提交
//        if (params != null) {                                                                       //遍历请求参数
//            for (Map.Entry<String, Object> entry : params.fileParams.entrySet()) {
//                if (entry.getValue() instanceof File) {                                              //判断如果entry.getValue是文件
//                            requestBody.addPart(Headers.of("Content-Disposition", "form=data; name\"" + entry.getKey() + "\" "),        //第一个参数为Header请求头，第二个参数是name，
//                            RequestBody.create(FILE_TYPE, (File) entry.getValue()));                //请求的参数的具体内容（文件类型，具体的文件，需要强制转换成file类型）
//                } else if (entry.getValue() instanceof String) {
//                    requestBody.addPart(Headers.of("Content-Disposition", "form-data; name = \"" + entry.getKey() + "\""),          // 如果是string类型，说明是Json的字符串或者是实体类型
//                            RequestBody.create(null, (String) entry.getValue()));                                  //
//                }
//            }
//        }
//        return new Request.Builder().url(url).post(requestBody.build()).build();
//    }
}

