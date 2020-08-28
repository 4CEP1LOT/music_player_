package com.kotlin.musiclearning.view.login;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.kotlin.lib_base.api.RequestCenter;
import com.kotlin.lib_common_ui.base.BaseActivity;
import com.kotlin.lib_connection.okHttp.listener.DisposeDataListener;
import com.kotlin.musiclearning.R;
import com.kotlin.lib_base.model.HomeManager;
import com.kotlin.lib_base.model.user.User;
import com.kotlin.lib_base.model.user.UserManager;
import com.kotlin.lib_base.model.MineDetailUser;
import com.kotlin.musiclearning.event.LoginEvent;

import org.greenrobot.eventbus.EventBus;

public class LoginActivity extends AppCompatActivity implements DisposeDataListener {

    private static EditText mUserName;
    private static EditText mPassWord;
    private static String username = " ";
    private static String password = " ";


    public static void setPassWord(EditText mPassWord) {
        LoginActivity.mPassWord = mPassWord;
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        setContentView(R.layout.login_activity);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        super.onCreate(savedInstanceState);
        findViewById(R.id.login_view).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mUserName = findViewById(R.id.userName);
                mPassWord = findViewById(R.id.passWord);
                username = mUserName.getText().toString();
                password = mPassWord.getText().toString();
                RequestCenter.login(LoginActivity.this,username,password);


                Log.d("login", "Success");
            }   //在RequestCenter中拿到用户信息


        });


    }

//
//    public static String getUsername() {
//        return username;
//    }
//
//    public static void setUsername(String username) {
//        LoginActivity.username = username;
//    }
//
//    public static String getPassword() {
//        return password;
//    }
//
//    public static void setPassword(String password) {
//        LoginActivity.password = password;
//    }

    @Override
    public void onSuccess(Object responseObj) {
        Log.d("login", "User");
        //拿到用户信息后处理正常逻辑
        User user = new User();
        MineDetailUser mMineDetailUser = new MineDetailUser();


        if (responseObj.getClass() == user.getClass()) {
            user = (User) responseObj;
            UserManager.getInstance().saveUser(user);                   //把得到的用户信息保存到内存中
            RequestCenter.MineDetailedUser(this);
            EventBus.getDefault().post(new LoginEvent());                   //发送信息
        } else if (responseObj.getClass() == mMineDetailUser.getClass()) {

            mMineDetailUser = (MineDetailUser) responseObj;
            HomeManager.getInstance().setmDetailUser(mMineDetailUser);
            EventBus.getDefault().post(new MineDetailUser());

        }
        Log.d("login", "Eventbus");
        Handler x = new Handler();//定义一个handle对象
        x.postDelayed(new LoginActivity.splashhandler(), 2000);//设置3秒钟延迟执行splashhandler线程。其实你这里可以再新建一个线程去执行初始化工作，如判断SD,网络状态等


    }

    class splashhandler implements Runnable{
        public void run() {
            LoginActivity.this.finish();// 把当前的LaunchActivity结束掉
        }
    }

    @Override
    public void onFailure(Object responseObj) {

    }


}
