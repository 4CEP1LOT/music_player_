package com.kotlin.lib_pullalive.app;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class AliveJobService extends JobService {

    private JobScheduler mJobScheduler;
    private static final int PULL_ALIVE = 0x01;

    private Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case PULL_ALIVE:
                    Log.d("TAG","PULL ALIVE");
                    jobFinished((JobParameters)msg.obj,true);                       //一旦执行之后，系统进程里面的job就会被移除掉
                    break;
                case 0x02:

                    break;
            }
        }
    };
    public static void start(Context context){
        Intent intent = new Intent(context,AliveJobService.class);
        context.startService(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mJobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);      //初始化JobService

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        JobInfo job = initJobInfo(startId);
        //提交job到system process中
        if ( mJobScheduler.schedule(job) <= 0) {
            Log.e("TAG","调用job失败");
        } else {
            Log.e("TAG","调用job成功");

        }
        return START_STICKY;                        //粘性回收，保证在合适的时间重启
    }
    //初始化JobInfo
    private JobInfo initJobInfo(int startId) {
        JobInfo.Builder builder = new JobInfo.Builder(startId,new ComponentName(getPackageName(),AliveJobService.class.getName()));
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.N) {
            //不同的job重试机制,至少要设置一个条件要不然会报错
            builder.setMinimumLatency(JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS)
                    .setOverrideDeadline(JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS)                                        //
                    .setBackoffCriteria(JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS, JobInfo.BACKOFF_POLICY_LINEAR);         //线性重试方案

        } else {
            builder.setPeriodic(JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS);

        }
        builder.setPersisted(false);         //是否持久化
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_NONE);      //因为是保活不需要网络
        builder.setRequiresCharging(false);                         //是否需要充电的时候执行
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        }
        return builder.build();
    }




    //开始任务
    @Override
    public boolean onStartJob(JobParameters params) {
        mHandler.sendMessage(Message.obtain(mHandler,1,params));
        return true;
    }
    //结束任务

    @Override
    public boolean onStopJob(JobParameters params) {
        mHandler.removeCallbacksAndMessages(null);
        return false;
    }
}
