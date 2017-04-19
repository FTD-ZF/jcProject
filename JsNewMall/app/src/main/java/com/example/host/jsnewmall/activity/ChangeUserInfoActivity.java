package com.example.host.jsnewmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class ChangeUserInfoActivity extends BaseActivity {
    private String userid;
    private String settext;
    private TextView mTvSubmit;
    private LinearLayout mBack;
    private String methodname;
    private String editname;
    private EditText mEtname;
    private SimpleDateFormat mSimpleTime;
    private String nTime;
    Gson gson=new Gson();
    private LoadingDialog dialog;
    private static final int FINISH_CODE=100;
    private static final int FINISH_CODE_A=101;
    private EditNameSuccessEntry mEtinfo;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FINISH_CODE:
                    dialog.dismiss();
                    ToastUtils.show(ChangeUserInfoActivity.this,"修改成功");
                    setResult(45);
                    finish();
                    break;
                case FINISH_CODE_A:
                    dialog.dismiss();
                    ToastUtils.show(ChangeUserInfoActivity.this,"保存失败");
                    break;
                default:
                    break;

            };
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo_content);
        dialog=new LoadingDialog(ChangeUserInfoActivity.this);
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
        String usercode=intent.getStringExtra("usercode");
        String editnameintent=intent.getStringExtra("editname");
        if (usercode.equals("1")){
            settext="昵称";
            methodname="nickname";
            editname=editnameintent;
        }else if (usercode.equals("2")){
            settext="真实姓名";
            methodname="realname";
            editname=editnameintent;
        }else if (usercode.equals("3")){
            settext="身份证";
            methodname="card_number";
            editname=editnameintent;
        }

    }

    private void initView(){

        TextView mTvTitlte=(TextView)findViewById(R.id.tv_title_name_change);//标题
        mTvTitlte.setText("修改"+settext);
        mTvTitlte.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_6));
        ImageView mImgMessage=(ImageView)findViewById(R.id.img_title_message);//更多图案显示
        mImgMessage.setVisibility(View.GONE);
        mBack=(LinearLayout)findViewById(R.id.iv_back);//返回
        mTvSubmit=(TextView)findViewById(R.id.tv_title_add_traveller);//保存按钮
        mTvSubmit.setVisibility(View.VISIBLE);
        mTvSubmit.setText("保存");

        TextView mTvname=(TextView)findViewById(R.id.tv_userinfo_name);//名字显示
        mTvname.setText(settext);
        mEtname=(EditText)findViewById(R.id.et_change_user);//编辑name
        mEtname.setText(editname);
    }
    private void initListener(){
        OnClickListenerImpl listener = new OnClickListenerImpl();
        mBack.setOnClickListener(listener);
        mTvSubmit.setOnClickListener(listener);
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
                    String finaleditname=mEtname.getText().toString().trim();
                    JSONObject jbody=null;
                    try {
                        jbody = new JSONObject();
                        jbody.put("uid",userid);
                        jbody.put(methodname,finaleditname);
                        jbody.put("method","EditUserInfo");
                        jbody.put("last_login_time",nTime);
                        jbody.put("last_login_ip","110.110.110.110");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
                    dohttpputuserinfo(UrlUtils.USER,jbodyB);


                    break;
                default:
                    break;


            }
        }
    }




    protected  void dohttpputuserinfo(String url,JSONObject  paramhash){
        dialog.show();
        HttpUtils.dopost(url,ChangeUserInfoActivity.this,paramhash, new HttpUtils.CallBack() {
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
