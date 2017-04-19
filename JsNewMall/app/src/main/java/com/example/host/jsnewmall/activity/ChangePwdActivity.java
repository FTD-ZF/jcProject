package com.example.host.jsnewmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
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
 * Created by host on 2017/3/29.
 */

//修改密码
public class ChangePwdActivity extends BaseActivity {
    private LinearLayout mBack;
    private EditText mEtoldpwd,mEtnewpwd,mEtsubnewpwd;
    private LinearLayout mLnSubpwd;
    private CharSequence temp;
    private CharSequence tempa;
    private LoadingDialog dialog;
    private SimpleDateFormat mSimpleTime;
    private String nTime;
    Gson gson=new Gson();
    private String userid;
    private EditNameSuccessEntry mEtinfo;

    private static final int FINISH_CODE_S=102;
    private static final int FINISH_CODE_E=103;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {

                case FINISH_CODE_S:
                    dialog.dismiss();
                    ToastUtils.show(ChangePwdActivity.this,"修改成功");
                    Intent intentlogin=new Intent(ChangePwdActivity.this,LoginActivity.class);
                    startActivity(intentlogin);
                    finish();
                    break;
                case FINISH_CODE_E:
                    dialog.dismiss();
                    ToastUtils.show(ChangePwdActivity.this,"修改失败");
                    break;

                default:
                    break;

            };
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepwd_content);

        dialog=new LoadingDialog(ChangePwdActivity.this);

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
    }

    private void initView(){
        TextView mTvTitlte=(TextView)findViewById(R.id.tv_title_name_change);//标题
        mTvTitlte.setText("修改密码");
        mTvTitlte.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_6));
        ImageView mImgMessage=(ImageView)findViewById(R.id.img_title_message);//更多图案显示
        mImgMessage.setVisibility(View.GONE);
        mBack=(LinearLayout)findViewById(R.id.iv_back);//返回

        mEtoldpwd=(EditText)findViewById(R.id.et_oldpwd);//原密码
        mEtnewpwd=(EditText)findViewById(R.id.et_newpwd);//新密码
        mEtsubnewpwd=(EditText)findViewById(R.id.et_sub_newpwd);//确认新密码

        mLnSubpwd=(LinearLayout)findViewById(R.id.ln_sub_pwd);//确认提交
        mEtnewpwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp=charSequence;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mEtsubnewpwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tempa=charSequence;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void initListener(){
        OnClickListenerImpl listener = new OnClickListenerImpl();
        mBack.setOnClickListener(listener);
        mLnSubpwd.setOnClickListener(listener);
    }

    private class OnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.iv_back:
                    finish();
                    break;
                //确认提交
                case R.id.ln_sub_pwd:
                    String stroldpwd=mEtoldpwd.getText().toString().trim();
                    String strnewpwd=mEtnewpwd.getText().toString().trim();
                    String strsubnewpwd=mEtsubnewpwd.getText().toString().trim();
                    if (stroldpwd.equals("")){
                        ToastUtils.show(ChangePwdActivity.this,"原密码不能为空");
                        return;
                    }
                    if (strnewpwd.equals("")){
                        ToastUtils.show(ChangePwdActivity.this,"新密码不能为空");
                        return;
                    }
                    if (strsubnewpwd.equals("")){
                        ToastUtils.show(ChangePwdActivity.this,"确认密码不能为空");
                        return;
                    }

                    if (!strnewpwd.equals(strsubnewpwd)){
                        ToastUtils.show(ChangePwdActivity.this,"两次输入密码不一致");
                        return;
                    }
                    if (temp.length()>16||temp.length()<6){
                        ToastUtils.show(ChangePwdActivity.this,"请输入正确的字符数");
                        return;
                    }
                    if (tempa.length()>16||tempa.length()<6){
                        ToastUtils.show(ChangePwdActivity.this,"请输入正确的字符数");
                        return;
                    }






                    JSONObject jbody=null;
                    try {
                        jbody = new JSONObject();
                        jbody.put("uid",userid);
                        jbody.put("oldpassword",stroldpwd);
                        jbody.put("password",strsubnewpwd);
                        jbody.put("method","EditUserPassword");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
                    dohttpputchangepwdinfo(UrlUtils.USER,jbodyB);



                    break;
                default:
                    break;
            }
        }
    }


    protected  void dohttpputchangepwdinfo(String url,JSONObject  paramhash){
        dialog.show();
        HttpUtils.dopost(url,ChangePwdActivity.this,paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {
                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());
                mEtinfo=gson.fromJson(body, EditNameSuccessEntry.class);
                if (mEtinfo.getRes()==1){
                    handler.sendEmptyMessage(FINISH_CODE_S);
                }else{
                    handler.sendEmptyMessage(FINISH_CODE_E);
                }

            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }





}
