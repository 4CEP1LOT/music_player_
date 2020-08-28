package com.kotlin.musiclearning.view.loading;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import com.kotlin.lib_common_ui.base.BaseActivity;
import com.kotlin.lib_common_ui.base.constant.Constant;
import com.kotlin.lib_pullalive.app.AliveJobService;
import com.kotlin.musiclearning.R;

public class LoadingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        pullAliveService();
        if (hasPermission(Constant.WRITE_READ_EXTERNAL_PERMISSION)){
            doSDCardPermission();
        }
    }




 
    private void pullAliveService() {                                   //保活组件的调用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AliveJobService.start(this);
        }

    }
}