package com.example.host.jsnewmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.host.jsnewmall.model.EditNameSuccessEntry;
import com.example.host.jsnewmall.model.JsonmModel;
import com.example.host.jsnewmall.utils.Base64Utils;
import com.example.host.jsnewmall.utils.HttpUtils;
import com.example.host.jsnewmall.utils.JsonUtils;
import com.example.host.jsnewmall.utils.SharedPreferencesUtils;
import com.example.host.jsnewmall.utils.ToastUtils;
import com.example.host.jsnewmall.utils.UrlUtils;
import com.google.gson.Gson;
import com.uu1.nmw.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by host on 2017/3/29.
 */
//注册界面
public class RegistActivity extends BaseActivity {
    private ImageView mImgBack;
    private EditText mEtregistera,mEtregisterb,mEtregisterc,mEtregisterd;
    private TextView mTvBindNum;
    private TextView mTvSuggestion;
    private Button mBtnRegister;
    private String registera,registerb,registerc,registerd;
    private SimpleDateFormat mSimpleTime;
    private String nTime;
    Gson gson=new Gson();

    private static final int FINISH_CODE=100;
    private static final int FINISH_CODE_S=101;
    private static final int FINISH_CODE_OUT=102;
    private EditNameSuccessEntry bodyinfo;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FINISH_CODE:

//                    setResult(59);
               ToastUtils.show(RegistActivity.this,bodyinfo.getMsg());
                    break;
                case FINISH_CODE_OUT:
//                    ToastUtils.show(RegistActivity.this,"请输入正确的账号或密码");
                    break;
                case FINISH_CODE_S:
                    ToastUtils.show(RegistActivity.this,"注册成功");
                    finish();
                    break;

                default:
                    break;

            };
        }
    };



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_content);

        Date d=new Date();
        mSimpleTime=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        nTime=mSimpleTime.format(d);
        initView();
        initListener();
    }

    private void initView(){
        mImgBack=(ImageView)findViewById(R.id.img_back);//返回
        mEtregistera=(EditText)findViewById(R.id.et_register_a);//获取账号
        mEtregisterb=(EditText)findViewById(R.id.et_register_b);//获取密码
        mEtregisterc=(EditText)findViewById(R.id.et_register_c);//获取手机号
        mEtregisterd=(EditText)findViewById(R.id.et_register_d);//获取验证码
        mTvBindNum=(TextView)findViewById(R.id.bind_phone_num_register);//绑定手机倒计时
        mTvSuggestion=(TextView)findViewById(R.id.tv_register_suggestion);//协议跳转
        mBtnRegister=(Button)findViewById(R.id.btn_register);//按钮点击注册






    }
    private void initListener(){
        OnClickListenerImpl listener = new OnClickListenerImpl();
        mImgBack.setOnClickListener(listener);
        mTvBindNum.setOnClickListener(listener);
        mTvSuggestion.setOnClickListener(listener);
        mBtnRegister.setOnClickListener(listener);
    }

    private class OnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch(view.getId()){

                //返回
                case R.id.img_back:
                    finish();
                    break;

                //绑定手机倒计时
                case R.id.bind_phone_num_register:
                    registerc=mEtregisterc.getText().toString().trim();
                    if (registerc.equals("")){
                        ToastUtils.show(RegistActivity.this,"请输入手机号");
                        return;
                    }




                    JSONObject jbody=null;
                    try {
                        jbody = new JSONObject();
                        jbody.put("phone",registerc);
                        jbody.put("type", 1);
                        jbody.put("method","SendSmsMsg");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
                    dohttpbindphone(UrlUtils.USER,jbodyB);
                    new CountDownTimerImpl(59000,1000).start();

                    break;

                //协议跳转
                case R.id.tv_register_suggestion:
                    break;
                //点击注册
                case R.id.btn_register:

                    registera=mEtregistera.getText().toString().trim();
                    registerb=mEtregisterb.getText().toString().trim();
                    registerc=mEtregisterc.getText().toString().trim();
                    registerd=mEtregisterd.getText().toString().trim();

                    if (registera.equals("")){
                        ToastUtils.show(RegistActivity.this,"请输入账号");
                        return;
                    }
                    if (registerb.equals("")){
                        ToastUtils.show(RegistActivity.this,"请输入密码");
                        return;
                    }
                    if (registerc.equals("")){
                        ToastUtils.show(RegistActivity.this,"请输入手机号");
                        return;
                    }
                    if (registerd.equals("")){
                        ToastUtils.show(RegistActivity.this,"请输入验证码");
                        return;
                    }



                    JSONObject jbodyr=null;
                    try {
                        jbodyr = new JSONObject();
                        jbodyr.put("user_name",registera);
                        jbodyr.put("password",registerb);
                        jbodyr.put("phone",registerc);
                        jbodyr.put("code",registerd);
                        jbodyr.put("ip","110.110.110.110");
                        jbodyr.put("reg_source", "3");
                        jbodyr.put("method","Register");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONObject jbodyBr= JsonUtils.JsonParseInfo(nTime,jbodyr);
                    dohttpregister(UrlUtils.USER,jbodyBr);



                    break;
                default:
                    break;

            }
        }
    }




    private class CountDownTimerImpl extends CountDownTimer {

        public CountDownTimerImpl(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onTick(long millisUntilFinished) {
            mTvBindNum.setClickable(false);
            Date date = new Date(millisUntilFinished);
            SimpleDateFormat sdf = new SimpleDateFormat("剩余ss秒");
            mTvBindNum.setText(sdf.format(date));
        }

        @Override
        public void onFinish() {
            mTvBindNum.setText("获取验证码");
            mTvBindNum.setClickable(true);
        }
    }



    protected  void dohttpbindphone(String url,JSONObject  paramhash){
        HttpUtils.dopost(url,getApplicationContext(),paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                if(!result.equals("")) {

                    JsonmModel homeinfoa = gson.fromJson(result, JsonmModel.class);
                    String body = Base64Utils.getFromBase64(homeinfoa.getBody());

                    bodyinfo = gson.fromJson(body, EditNameSuccessEntry.class);


                    handler.sendEmptyMessage(FINISH_CODE);
                }else {
                    handler.sendEmptyMessage(FINISH_CODE_OUT);
                }

            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }



    protected  void dohttpregister(String url,JSONObject  paramhash){
        HttpUtils.dopost(url,getApplicationContext(),paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                if(!result.equals("")) {

                    JsonmModel homeinfoa = gson.fromJson(result, JsonmModel.class);
                    String body = Base64Utils.getFromBase64(homeinfoa.getBody());

//                    bodyinfo = gson.fromJson(body, LoginUserEntry.class);


                    handler.sendEmptyMessage(FINISH_CODE_S);
                }else {
                    handler.sendEmptyMessage(FINISH_CODE_OUT);
                }

            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }

}
