package com.example.host.jsnewmall.activity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.uu1.nmw.R;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by host on 2017/3/30.
 */

public class SplashActivity extends AppCompatActivity{
    private static final String TAG = "JPush";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_content);

        Log.d(TAG, "[ExampleApplication] onCreate");
        JPushInterface.setDebugMode(true);
        JPushInterface.init(getApplicationContext());
        /**
         * 首先进入到欢迎界面, 在欢迎界面停留3秒钟 ，再跳转登录界面
         */
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this,
                        MainActivity.class);
                startActivity(intent);
                finish();

            }
        }, 3000);
    }
}
