package com.example.host.jsnewmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.host.jsnewmall.fragment.CenterFragment;
import com.example.host.jsnewmall.model.EditNameSuccessEntry;
import com.example.host.jsnewmall.model.JsonmModel;
import com.example.host.jsnewmall.model.LoginUserEntry;
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
 * Created by host on 2017/3/13.
 */

public class LoginActivity extends BaseActivity {
    private ImageView mBACK;
    private TextView mTvlogna;
    private TextView mTvlognb;
    private LinearLayout mLnlayouta,mLnlayoutb;
    private EditText mEtUsernamea,mEtPassworda;
    private TextView mTvzhucea,mTvzhaohuia;
    private Button mBtnlogina;
    private EditText mEtUsernameb,mEtPasswordb;
    private TextView mTvzhuceb,mTvzhaohuib;
    private Button mBtnloginb;
    private TextView mTvBindNum;
    private SimpleDateFormat mSimpleTime;
    private String nTime;
    Gson gson=new Gson();
    private static final int FINISH_CODE=100;
    private static final int FINISH_CODE_S=101;
    private static final int FINISH_CODE_OUT=102;
    private static final int FINISH_CODE_T=103;

    private  LoginUserEntry bodyinfo;


    //15961167765  123456

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FINISH_CODE:

//                    setResult(59);
                    ToastUtils.show(LoginActivity.this,bodyinfo.getMsg());
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("id",4);
                    startActivity(intent);
                    finish();
                    break;
                case FINISH_CODE_OUT:
                    ToastUtils.show(LoginActivity.this,"请输入正确的账号或密码");
                    break;

                case FINISH_CODE_S:
                    ToastUtils.show(LoginActivity.this,bodyinfo.getMsg());
                    Intent intents=new Intent(LoginActivity.this,MainActivity.class);
                    intents.putExtra("id",4);
                    startActivity(intents);
                    finish();
                    break;
                case FINISH_CODE_T:
                    ToastUtils.show(LoginActivity.this,bodyinfo.getMsg());

                    break;

                default:
                    break;

            };
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login_view);
        Date d=new Date();
        mSimpleTime=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        nTime=mSimpleTime.format(d);

        initView();
        initListener();

    }
    private void initView(){
        mBACK =(ImageView)findViewById(R.id.img_back);
        mTvlogna=(TextView)findViewById(R.id.tv_zhanghaodenglu);//账号登陆按钮
        mTvlognb=(TextView)findViewById(R.id.tv_dongtaidenglu);//动态密码登陆按钮
        mLnlayouta=(LinearLayout)findViewById(R.id.ln_login_zhanghao);//账号登陆布局
        mLnlayoutb=(LinearLayout)findViewById(R.id.ln_login_dongtai);//动态密码布局
        mEtUsernamea=(EditText)findViewById(R.id.et_username_a);//账号登陆手机号
        mEtPassworda=(EditText)findViewById(R.id.et_password_a);//账号登陆密码
        mTvzhucea=(TextView)findViewById(R.id.tv_zhuce_a);//注册a
        mTvzhaohuia=(TextView)findViewById(R.id.tv_zhaohuipwd_a);//找回密码a
        mBtnlogina=(Button)findViewById(R.id.btn_login_a);//登陆按钮a


        mEtUsernameb=(EditText)findViewById(R.id.et_username_b);//动态登陆手机号
        mEtPasswordb=(EditText)findViewById(R.id.et_password_b);//动态登陆密码
        mTvzhuceb=(TextView)findViewById(R.id.tv_zhuce_b);//注册b
        mTvzhaohuib=(TextView)findViewById(R.id.tv_zhaohuipwd_b);//找回密码b
        mBtnloginb=(Button)findViewById(R.id.btn_login_b);//登陆按钮b
        mTvBindNum=(TextView)findViewById(R.id.bind_phone_num);//获取验证码倒计时


    }
    private void initListener(){
        OnClickListenerImpl listener = new OnClickListenerImpl();
        mBACK.setOnClickListener(listener);
        mTvlogna.setOnClickListener(listener);
        mTvlognb.setOnClickListener(listener);
        mTvzhucea.setOnClickListener(listener);
        mTvzhaohuia.setOnClickListener(listener);
        mTvzhuceb.setOnClickListener(listener);
        mTvzhaohuib.setOnClickListener(listener);
        mBtnlogina.setOnClickListener(listener);
        mBtnloginb.setOnClickListener(listener);
        mTvBindNum.setOnClickListener(listener);

    }


    private class OnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch(view.getId()){

                //返回
                case R.id.img_back:
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("id",3);
                    startActivity(intent);
                  finish();
                    break;

                case R.id.tv_zhanghaodenglu:
                    setBackTextViewa();
                    mLnlayouta.setVisibility(View.VISIBLE);
                    mLnlayoutb.setVisibility(View.GONE);
                    break;
                case R.id.tv_dongtaidenglu:
                    setBackTextViewb();
                    mLnlayoutb.setVisibility(View.VISIBLE);
                    mLnlayouta.setVisibility(View.GONE);
                    break;

                //注册
                case R.id.tv_zhuce_a:
                    Intent intentzhucea=new Intent(LoginActivity.this,RegistActivity.class);
                    startActivity(intentzhucea);
                    break;
                case R.id.tv_zhuce_b:
                    Intent intentzhuceb=new Intent(LoginActivity.this,RegistActivity.class);
                    startActivity(intentzhuceb);
                    break;
                //找回
                case R.id.tv_zhaohuipwd_a:
                    break;
                case R.id.tv_zhaohuipwd_b:
                    break;
                //登录按钮
                case R.id.btn_login_a:
                    String namea=mEtUsernamea.getText().toString().trim();
                    String pwda=mEtPassworda.getText().toString().trim();
                    if (namea.equals("")||pwda.equals("")){
                        ToastUtils.show(LoginActivity.this,"请输入账号或密码");
                        return;
                    }


                    JSONObject jbody=null;
                    try {
                        jbody = new JSONObject();
                        jbody.put("user_name",namea);
                        jbody.put("password", pwda);
                        jbody.put("method","Login");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
                    dohttpLogin(UrlUtils.USER,jbodyB);


                    break;
                //验证码登陆点击
                case R.id.btn_login_b:
                    String strnuma=mEtUsernameb.getText().toString().trim();//电话获取
                    String strnumb=mEtPasswordb.getText().toString().trim();//动态密码
                    if (strnuma.equals("")){
                        ToastUtils.show(LoginActivity.this,"请输入手机号");
                        return;
                    }
                    if (strnumb.equals("")){
                        ToastUtils.show(LoginActivity.this,"请输入验证码");
                        return;
                    }


                    JSONObject jbodyr=null;
                    try {
                        jbodyr = new JSONObject();

                        jbodyr.put("phone",strnuma);
                        jbodyr.put("code",strnumb);
                        jbodyr.put("ip","110.110.110.110");
                        jbodyr.put("method","LoginByCode");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONObject jbodyBr= JsonUtils.JsonParseInfo(nTime,jbodyr);
                    dohttpphonelogin(UrlUtils.USER,jbodyBr);


                    break;

                //验证码发送
                case R.id.bind_phone_num:
                    String strnum=mEtUsernameb.getText().toString().trim();//电话获取
                    JSONObject jbodynum=null;
                    try {
                        jbodynum = new JSONObject();
                        jbodynum.put("phone",strnum);
                        jbodynum.put("type", 2);
                        jbodynum.put("method","SendSmsMsg");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONObject jbodyC= JsonUtils.JsonParseInfo(nTime,jbodynum);
                    dohttpbindphone(UrlUtils.USER,jbodyC);
                    new CountDownTimerImpl(59000,1000).start();
                    break;

                default:
                    break;

            }
        }
    }



    //账号密码登陆请求
    protected  void dohttpLogin(String url,JSONObject  paramhash){
        HttpUtils.dopost(url,getApplicationContext(),paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                if(!result.equals("")) {

                    JsonmModel homeinfoa = gson.fromJson(result, JsonmModel.class);
                    String body = Base64Utils.getFromBase64(homeinfoa.getBody());

                    bodyinfo = gson.fromJson(body, LoginUserEntry.class);

                    SharedPreferencesUtils.saveUserInfo(LoginActivity.this, gson.toJson(bodyinfo));
                    //User user = gson.fromJson(UserUtils.getUserInfo(), User.class);使用
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


    //动态密码登陆请求
    protected  void dohttpphonelogin(String url,JSONObject  paramhash){
        HttpUtils.dopost(url,getApplicationContext(),paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                if(!result.equals("")) {

                    JsonmModel homeinfoa = gson.fromJson(result, JsonmModel.class);
                    String body = Base64Utils.getFromBase64(homeinfoa.getBody());

                    bodyinfo = gson.fromJson(body, LoginUserEntry.class);

                    if (bodyinfo.getRes()!=0){
                        SharedPreferencesUtils.saveUserInfo(LoginActivity.this, gson.toJson(bodyinfo));
                        //User user = gson.fromJson(UserUtils.getUserInfo(), User.class);使用
                        handler.sendEmptyMessage(FINISH_CODE_S);
                    }else {
                        handler.sendEmptyMessage(FINISH_CODE_T);
                    }
                }else {

                    handler.sendEmptyMessage(FINISH_CODE_OUT);
                }

            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }




    //验证码发送请求
    protected  void dohttpbindphone(String url,JSONObject  paramhash){
        HttpUtils.dopost(url,getApplicationContext(),paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                if(!result.equals("")) {

                    JsonmModel homeinfoa = gson.fromJson(result, JsonmModel.class);
                    String body = Base64Utils.getFromBase64(homeinfoa.getBody());

                    EditNameSuccessEntry bindinfo = gson.fromJson(body, EditNameSuccessEntry.class);


                    ToastUtils.show(LoginActivity.this,bindinfo.getMsg());
                }else {
                    handler.sendEmptyMessage(FINISH_CODE_OUT);
                }

            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setBackTextViewa(){
        mTvlogna.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.drawable.login_a_shape));
        mTvlognb.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.transparent));
    }
    private void setBackTextViewb(){
        mTvlognb.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.drawable.login_b_shape));
        mTvlogna.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.transparent));
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
        intent.putExtra("id",3);
        startActivity(intent);

        finish();
    }






}
