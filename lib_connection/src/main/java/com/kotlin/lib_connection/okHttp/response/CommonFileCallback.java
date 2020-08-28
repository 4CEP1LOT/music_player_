package com.kotlin.lib_connection.okHttp.response;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.google.gson.Gson;
import com.kotlin.lib_connection.okHttp.exception.OkHttpException;
import com.kotlin.lib_connection.okHttp.listener.DisposeDataHandle;
import com.kotlin.lib_connection.okHttp.listener.DisposeDownloadListener;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.logging.LogRecord;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import static com.kotlin.lib_connection.okHttp.request.BaseValueCallback.EMPTY_MSG;
import static com.kotlin.lib_connection.okHttp.request.BaseValueCallback.NETWORK_ERROR;


/**
 * 处理文件类型的响应
 */

public class CommonFileCallback implements Callback {

    protected final int IO_ERROR = -2; // the JSON relative error


    /**
     * 将其他线程的数据转发到ui线程
     */
    private static final  int PROGRESS_MESSAGE = 0x01;      //处理进度的实现类型
    private Handler mDeliveryHandler;                       //数据转发
    private DisposeDownloadListener mListenr;               //业务层的回调
    private String mFilePath;                               //文件路径
    private int mProgress;                                  //当前进度
    private Class<?> mClass;

    public CommonFileCallback(DisposeDataHandle handle){
        this.mListenr = (DisposeDownloadListener) handle.mListener;
        this.mFilePath = handle.mSource;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper()) {               //发送到主线程


            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case PROGRESS_MESSAGE:
                        mListenr.onProgress((int)msg.obj);
                        break;
                }
                }
            };


        }

    /**
     * 处理响应逻辑
     * @param call
     * @param e
     * @function OkHttpException() 自定义的异常类，方便快速定位到具体的exception
     */
    @Override
    public void onFailure( Call call, final IOException e) {
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListenr.onFailure(new OkHttpException(NETWORK_ERROR,e));
            }
        });

    }

    private File handleResponse(Response response){
        if(response == null){
            return null;
        }
        /**
         * 计算项目进度下面的几个变量
         */
        InputStream inputStream = null;
        File file;
        FileOutputStream fos =null;
        byte[] buffer = new byte[2048];
        double currentlength = 0;
        int length;
        double sumLength = 0;
        try{
            checkLocalFilePath(mFilePath);                      //判断当前文件是否存在，不存在的话创建一下
            file = new File(mFilePath);                         //得出文件的下载进度
            fos = new FileOutputStream(file);
            inputStream = response.body().byteStream();
            sumLength = response.body().contentLength();
            while((length = inputStream.read(buffer)) != -1){          //buffer缓存字节码不等于-1代表没有读完，读的时候就把length赋值，避免再次调用
                fos.write(buffer,0,buffer.length);              //把buffer数据全写到流中
                currentlength += length;                             //对currentLength进行累加 ，currentLength当前进度
                mProgress = (int)(currentlength /sumLength * 100);          //计算进度，当前进度/总进度 *100
                mDeliveryHandler.obtainMessage(PROGRESS_MESSAGE,mProgress).sendToTarget();          //把进度以键值对的形式发给接收对象，
                                                                                                    // PROGRESS_MESSAGE为key，
                                                                                                    // mProgress为value
            }
            fos.flush();                                                    //在读写完成后关掉流
        }catch (Exception e){
            file = null;                                                    //只要产生异常就清空file

        }finally {
            try {
            if (fos!=null){                                                 //输出流关闭
                fos.close();
            }
            if (inputStream != null){
                inputStream.close();                                         //输入流关闭
            }
        }catch (Exception e){
            file = null;}
        }
        return null;

    }


    @Override
    public void onResponse(Call call,  Response response) throws IOException {
        final File file = handleResponse(response);                         //通过HandleResponse方法处理响应
        mDeliveryHandler.post(new Runnable() {                              //
            @Override
            public void run() {
                if(file != null){                                                        //文件不为空
                    mListenr.onSuccess(file);                                           //file文件回调到业务层
                }else {
                    mListenr.onFailure(new OkHttpException(IO_ERROR,EMPTY_MSG));      //如果失败爆出okhttp异常
                }

            }
        });

    }
    private void checkLocalFilePath(String localFilePath) {                             //判断当前要下载的文件是否存在
        File path = new File(localFilePath.substring(0,localFilePath.lastIndexOf("/")+1));          //为下载的文件添加路径

        File file = new File(localFilePath);                                                    //初始化路径

        if (path.exists()){                                 //如果路径存在，创建多级路径
            path.mkdirs();
        }
        if (file.exists()){                                     //如果file存在
            try {
                file.createNewFile();                           //表示是否创建文件成功
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
