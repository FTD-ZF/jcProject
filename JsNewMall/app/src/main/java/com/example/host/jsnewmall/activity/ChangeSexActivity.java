package com.example.host.jsnewmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.host.jsnewmall.model.EditNameSuccessEntry;
import com.example.host.jsnewmall.model.JsonmModel;
import com.example.host.jsnewmall.utils.Base64Utils;
import com.example.host.jsnewmall.utils.HttpUtils;
import com.example.host.jsnewmall.utils.JsonUtils;
import com.example.host.jsnewmall.utils.ToastUtils;
import com.example.host.jsnewmall.utils.UrlUtils;
import com.example.host.jsnewmall.view.LoadingDialog;
import com.google.gson.Gson;
import com.uu1.nmw.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by host on 2017/3/24.
 */

public class ChangeSexActivity extends BaseActivity {

    private String userid;
    private LinearLayout mBack;
    private TextView mTvSubmit;
    private SimpleDateFormat mSimpleTime;
    private String nTime;
    Gson gson=new Gson();
    private LoadingDialog dialog;
    private static final int FINISH_CODE=100;
    private static final int FINISH_CODE_A=101;
    private EditNameSuccessEntry mEtinfo;
    private RelativeLayout mRlmale,mRlfemale;
    private String sextype;
    private TextView mSexa,mSexb;
    private ImageView mImga,mImgb;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FINISH_CODE:
                    dialog.dismiss();
                    ToastUtils.show(ChangeSexActivity.this,"修改成功");
                    setResult(45);
                    finish();
                    break;
                case FINISH_CODE_A:
                    dialog.dismiss();
                    ToastUtils.show(ChangeSexActivity.this,"保存失败");
                    break;
                default:
                    break;

            };
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changesex_content);

        dialog=new LoadingDialog(ChangeSexActivity.this);
        Date d=new Date();
        mSimpleTime=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        nTime=mSimpleTime.format(d);

        getIntentData();
        initView();
        initListener();
    }
    private void getIntentData(){
        Intent intent=getIntent();
        userid=intent.getStringExtra("userid");
        sextype=intent.getStringExtra("sextype");
    }

    private void initView(){

        TextView mTvTitlte=(TextView)findViewById(R.id.tv_title_name_change);//标题
        mTvTitlte.setText("修改性别");
        mTvTitlte.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_6));
        ImageView mImgMessage=(ImageView)findViewById(R.id.img_title_message);//更多图案显示
        mImgMessage.setVisibility(View.GONE);
        mBack=(LinearLayout)findViewById(R.id.iv_back);//返回
        mTvSubmit=(TextView)findViewById(R.id.tv_title_add_traveller);//保存按钮
        mTvSubmit.setVisibility(View.VISIBLE);
        mTvSubmit.setText("保存");

        mRlmale=(RelativeLayout)findViewById(R.id.rl_sex_male);//男布局
        mRlfemale=(RelativeLayout)findViewById(R.id.rl_sex_female);//女布局
        mSexa=(TextView)findViewById(R.id.tv_userinfo_sex_a);//男
        mSexb=(TextView)findViewById(R.id.tv_userinfo_sex_b);//女
        mImga=(ImageView)findViewById(R.id.img_change_a);
        mImgb=(ImageView)findViewById(R.id.img_change_b);




        //0女 1男

        if (sextype.equals("0")){
            mSexa.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_6));
            mSexb.setTextColor(getApplicationContext().getResources().getColor(R.color.title_orange));
            mImga.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.white));
            mImgb.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.icon_select_sex));
        }else if (sextype.equals("1")){
            mSexa.setTextColor(getApplicationContext().getResources().getColor(R.color.title_orange));
            mSexb.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_6));
            mImga.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.icon_select_sex));
            mImgb.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.white));
        }


    }
    private void initListener(){
        OnClickListenerImpl listener = new OnClickListenerImpl();
        mBack.setOnClickListener(listener);
        mTvSubmit.setOnClickListener(listener);
        mRlmale.setOnClickListener(listener);
        mRlfemale.setOnClickListener(listener);

    }


    private class OnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.iv_back:
                    finish();
                    break;

                //保存提交
                case R.id.tv_title_add_traveller:
//
                    JSONObject jbody=null;
                    try {
                        jbody = new JSONObject();
                        jbody.put("uid",userid);
                        jbody.put("sex",sextype);
                        jbody.put("method","EditUserInfo");
                        jbody.put("last_login_time",nTime);
                        jbody.put("last_login_ip","110.110.110.110");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
                    dohttpputuserinfo(UrlUtils.USER,jbodyB);


                    break;
                    //男
                case R.id.rl_sex_male:
                    sextype="1";
                    mSexa.setTextColor(getApplicationContext().getResources().getColor(R.color.title_orange));
                    mSexb.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_6));
                    mImga.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.icon_select_sex));
                    mImgb.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.white));

                    break;
                //女
                case R.id.rl_sex_female:
                    sextype="0";
                    mSexa.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_6));
                    mSexb.setTextColor(getApplicationContext().getResources().getColor(R.color.title_orange));
                    mImga.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.white));
                    mImgb.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.icon_select_sex));

                    break;

                default:
                    break;


            }
        }
    }

    protected  void dohttpputuserinfo(String url,JSONObject  paramhash){
        dialog.show();
        HttpUtils.dopost(url,ChangeSexActivity.this,paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {
                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());
                mEtinfo=gson.fromJson(body, EditNameSuccessEntry.class);
                if (mEtinfo.getRes()==1){
                    handler.sendEmptyMessage(FINISH_CODE);
                }else{
                    handler.sendEmptyMessage(FINISH_CODE_A);
                }

            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }



}
