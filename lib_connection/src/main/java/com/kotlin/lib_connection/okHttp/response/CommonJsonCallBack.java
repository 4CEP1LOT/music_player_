package com.kotlin.lib_connection.okHttp.response;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.kotlin.lib_connection.okHttp.exception.OkHttpException;
import com.kotlin.lib_connection.okHttp.listener.DisposeDataHandle;
import com.kotlin.lib_connection.okHttp.listener.DisposeDataListener;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.kotlin.lib_connection.okHttp.request.BaseValueCallback.EMPTY_MSG;
import static com.kotlin.lib_connection.okHttp.request.BaseValueCallback.NETWORK_ERROR;

public class CommonJsonCallBack implements Callback {

    protected final int JSON_ERROR = -2;                    //json解析的异常
    protected final int OTHER_ERROR= -3;                    //未知异常
    private DisposeDataListener mListener;                  //回调到业务层
    private Class<?> mClass;                                //Json数据所要转成类的对象
    private Handler mDeliveryHandler;                       //发送数据到（ui）主线程

    public CommonJsonCallBack(DisposeDataHandle handle){
        mListener = handle.mListener;                       //listener可以从handle对象获取到
        mClass = handle.mClass;                             //Class对象可以通过Handle类获取
        mDeliveryHandler = new Handler(Looper.getMainLooper());     //创建一个新Handler
    }


    @Override
    public void onFailure( Call call, final IOException e) {
        mDeliveryHandler.post(new Runnable() {                          //开辟新线程回调错误
            @Override
            public void run() {
                mListener.onFailure(new OkHttpException(NETWORK_ERROR,e));  //键值对的方法传递错误，NETWORK_ERROR为key，e为value
            }
        });

    }

    @Override
    public void onResponse( Call call,  Response response) throws IOException {
        final String result = response.body().string();
        Log.d("login","call");
        //传递的结果result为字符串类型
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
            }
        });

    }

    /**
     * Json真正的解析逻辑
     */

    private void handleResponse(Object responseObj){
        if (responseObj == null || responseObj.toString().trim().equals(" ")){                         //如果result为空或者为空字符串
            mListener.onFailure(new OkHttpException(NETWORK_ERROR,EMPTY_MSG));
            return;
        }
        try {
            JSONObject result = new JSONObject(responseObj.toString());
            if (mClass == null){                                                                     //如果mClass为空说明应用想要原始数据
               mListener.onSuccess(result);
            }else {
                Gson gson = new Gson();
                Object object = gson.fromJson(responseObj.toString(),mClass);
                if (object != null){
                     mListener.onSuccess(object);


                }else{
                    mListener.onFailure(new OkHttpException(JSON_ERROR,EMPTY_MSG));
                }
            }

        }catch (Exception e){
            mListener.onFailure(new OkHttpException(JSON_ERROR,EMPTY_MSG ));

        }

    }


}
