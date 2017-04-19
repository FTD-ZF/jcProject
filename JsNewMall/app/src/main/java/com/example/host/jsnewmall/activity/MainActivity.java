package com.example.host.jsnewmall.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.example.host.jsnewmall.fragment.AddressFragment;
import com.example.host.jsnewmall.fragment.CenterFragment;
import com.example.host.jsnewmall.fragment.CustomerFragment;
import com.example.host.jsnewmall.fragment.HomeFragment;
import com.example.host.jsnewmall.utils.DoubleClickExitHelper;
import com.example.host.jsnewmall.utils.PhoneUtils;
import com.example.host.jsnewmall.utils.ToastUtils;
import com.example.host.jsnewmall.view.DialogCallPhone;
import com.example.host.jsnewmall.view.HomeForthGridView;
import com.example.host.jsnewmall.view.PhoneView;
import com.uu1.nmw.R;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends FragmentActivity {

    public static RadioGroup mRadioGroup;
    private FragmentManager fManager;
    private HomeFragment homeFragment;
    private AddressFragment addressFragment;
    private CustomerFragment customerFragment;
    private CenterFragment centerFragment;
    private FragmentTransaction fragmentTransaction;
    private int cityid;
    private RadioButton mCallphone;
    private  DoubleClickExitHelper backpress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        initView();
        initFragment();
        initListener();
         backpress=new DoubleClickExitHelper(MainActivity.this);

    }


    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.rg_activity_main_group);

        mCallphone=(RadioButton)findViewById(R.id.rbtn_activity_main_mine);//电话拨打

        RadioButton mHomeButton = (RadioButton) findViewById(R.id.rbtn_activity_main_home_page);
        RadioButton mAddressButton = (RadioButton) findViewById(R.id.rbtn_activity_main_address);
        RadioButton mCustomerButton = (RadioButton) findViewById(R.id.rbtn_activity_main_customer);
        RadioButton mMineButton = (RadioButton) findViewById(R.id.rbtn_activity_main_mine);

        Drawable drawableWeiHui = getResources().getDrawable(R.drawable.main_bottom_home_selector);
        drawableWeiHui.setBounds(0, 0, 40, 40);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        mHomeButton.setCompoundDrawables(null, drawableWeiHui, null, null);//只放上面

        Drawable drawableWeiHuia = getResources().getDrawable(R.drawable.main_bottom_address_selector);
        drawableWeiHuia.setBounds(0, 0, 40, 40);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        mAddressButton.setCompoundDrawables(null, drawableWeiHuia, null, null);//只放上面

        Drawable drawableWeiHuib = getResources().getDrawable(R.drawable.main_bottom_mine_selector);
        drawableWeiHuib.setBounds(0, 0, 40, 40);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        mCustomerButton.setCompoundDrawables(null, drawableWeiHuib, null, null);//只放上面

        Drawable drawableWeiHuic = getResources().getDrawable(R.drawable.main_bottom_customer_selector);
        drawableWeiHuic.setBounds(0, 0, 40, 40);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        mMineButton.setCompoundDrawables(null, drawableWeiHuic, null, null);//只放上面
    }


    private void initFragment() {
        fManager = getSupportFragmentManager();
        homeFragment = (HomeFragment) fManager.findFragmentById(R.id.home);
        addressFragment = (AddressFragment) fManager.findFragmentById(R.id.donation);
        customerFragment = (CustomerFragment) fManager.findFragmentById(R.id.offered);
        centerFragment = (CenterFragment) fManager.findFragmentById(R.id.center);
        fragmentTransaction = fManager.beginTransaction().hide(homeFragment).hide(addressFragment)
                .hide(customerFragment).hide(centerFragment);
        fragmentTransaction.show(homeFragment).commit();


        mRadioGroup.check(mRadioGroup.getChildAt(0).getId());
    }

    private void initListener() {
        OnCheckedChangeListenerImpl listener = new OnCheckedChangeListenerImpl();
        mRadioGroup.setOnCheckedChangeListener(listener);

        mCallphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogCallPhone.showdialog(MainActivity.this);
            }
        });
    }


    private class OnCheckedChangeListenerImpl implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            fragmentTransaction = fManager.beginTransaction().hide(homeFragment).hide(addressFragment)
                    .hide(customerFragment).hide(centerFragment);
            switch (checkedId) {
                case R.id.rbtn_activity_main_home_page:
                    fragmentTransaction.show(homeFragment).commitAllowingStateLoss();
                    // homePageFragment.lazyLoad();


                    break;
                case R.id.rbtn_activity_main_address:
                    cityid = homeFragment.getcityid();
                    addressFragment.loadcityid(cityid);
                    fragmentTransaction.show(addressFragment).commitAllowingStateLoss();
                    break;
                case R.id.rbtn_activity_main_customer:

                    fragmentTransaction.show(centerFragment).commitAllowingStateLoss();

                    break;
                case R.id.rbtn_activity_main_mine:
//                centerFragment.lazyLoad();
//                    fragmentTransaction.show(customerFragment).commitAllowingStateLoss();

//                    PhoneView.onCallphone(MainActivity.this);
//                    DialogCallPhone.showdialog(MainActivity.this);
                    break;
                default:
                    break;
            }
        }

    }




//    @Override
//    protected void onStart() {
//        super.onStart();
//        Intent intent=getIntent();
//        int id =intent.getIntExtra("id",0);
//        if (id==1){
//            fragmentTransaction = fManager.beginTransaction().hide(homeFragment).hide(addressFragment)
//                    .hide(customerFragment).hide(centerFragment);
//            fragmentTransaction.show(homeFragment).commit();
//
//
//            mRadioGroup.check(mRadioGroup.getChildAt(0).getId());
//        }else if (id==2){
//            fragmentTransaction = fManager.beginTransaction().hide(centerFragment).hide(addressFragment)
//                    .hide(customerFragment).hide(homeFragment);
//            fragmentTransaction.show(centerFragment).commit();
//
//
//            mRadioGroup.check(mRadioGroup.getChildAt(3).getId());
//        }
//    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int id =intent.getIntExtra("id",0);
        if (id==1){
            fragmentTransaction = fManager.beginTransaction().hide(homeFragment).hide(addressFragment)
                    .hide(customerFragment).hide(centerFragment);
            fragmentTransaction.show(homeFragment).commit();


            mRadioGroup.check(mRadioGroup.getChildAt(0).getId());
        }else if (id==2){
            fragmentTransaction = fManager.beginTransaction().hide(centerFragment).hide(addressFragment)
                    .hide(customerFragment).hide(homeFragment);
            fragmentTransaction.show(centerFragment).commit();


            mRadioGroup.check(mRadioGroup.getChildAt(2).getId());
        }
        else if (id==3){
            fragmentTransaction = fManager.beginTransaction().hide(centerFragment).hide(addressFragment)
                    .hide(customerFragment).hide(homeFragment);
            fragmentTransaction.show(centerFragment).commit();

            centerFragment.refreshloginout();
            mRadioGroup.check(mRadioGroup.getChildAt(2).getId());
        }else if (id==4){
            fragmentTransaction = fManager.beginTransaction().hide(centerFragment).hide(addressFragment)
                    .hide(customerFragment).hide(homeFragment);
            fragmentTransaction.show(centerFragment).commit();

            centerFragment.refreshlogin();
            mRadioGroup.check(mRadioGroup.getChildAt(2).getId());
        }else if (id==5){
            fragmentTransaction = fManager.beginTransaction().hide(addressFragment).hide(centerFragment)
                    .hide(customerFragment).hide(homeFragment);
            fragmentTransaction.show(addressFragment).commit();


            mRadioGroup.check(mRadioGroup.getChildAt(1).getId());
        }
    }


//    /** 上次点击返回键的时间 */
//    private long lastBackPressed;
//    /** 两次点击的间隔时间 */
//    private static final int QUIT_INTERVAL = 2000;
//
//    @Override
//    public void onBackPressed() {
//        long backPressed = System.currentTimeMillis();
//        if (backPressed - lastBackPressed > QUIT_INTERVAL) {
//            lastBackPressed = backPressed;
//            Toast.makeText(this, "再按一次退出", Toast.LENGTH_LONG).show();
//        } else {
//            finish();
//            System.exit(0);
//        }
//    }


//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        ToastUtils.show(MainActivity.this,"ssssss");
//    }


//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        backpress.onKeyDown(keyCode,event);
//        return super.onKeyDown(keyCode, event);
//    }
}
