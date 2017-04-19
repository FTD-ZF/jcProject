package com.example.host.jsnewmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.host.jsnewmall.adapter.SuggestiontypeAdapter;
import com.example.host.jsnewmall.model.JsonmModel;
import com.example.host.jsnewmall.model.PostInfoSuccessEntry;
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
//意见反馈
public class SuggestionActivity extends BaseActivity {


    private LinearLayout mBack;
    private GridView mGvSuggestionType;
    private String[] arrtype = new String[]{"带团导游", "地接导游", "住宿餐饮","旅游线路","购物娱乐","交通问题"};
    private String finaltype="";
    private SuggestiontypeAdapter adapter;
    private LinearLayout mSubmit;
    private EditText mEtproblems,mEtname,mEtphone;
    private LoadingDialog dialog;
    private SimpleDateFormat mSimpleTime;
    private String nTime;
    Gson gson=new Gson();
    private static final int FINISH_CODE=100;
    private String userid;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FINISH_CODE:
                    dialog.dismiss();
                    ToastUtils.show(SuggestionActivity.this,"提交成功");
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
        setContentView(R.layout.activity_suggestion_content);


        dialog=new LoadingDialog(SuggestionActivity.this);

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
        mTvTitlte.setText("意见反馈");
        mTvTitlte.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_6));
        ImageView mImgMessage=(ImageView)findViewById(R.id.img_title_message);//更多图案显示
        mImgMessage.setVisibility(View.GONE);
        mBack=(LinearLayout)findViewById(R.id.iv_back);//返回

        mGvSuggestionType=(GridView)findViewById(R.id.gv_suggestion_view);//类型展示gridview
        adapter=new SuggestiontypeAdapter(SuggestionActivity.this);
        mGvSuggestionType.setAdapter(adapter);

        mSubmit=(LinearLayout)findViewById(R.id.ln_suggestion_sub);//提交

        mEtproblems=(EditText)findViewById(R.id.et_suggestion_problem);//问题编辑
        mEtname=(EditText)findViewById(R.id.et_suggestion_name);//名字编辑
        mEtphone=(EditText)findViewById(R.id.et_suggestion_phone);//电话编辑


    }

    private void initListener(){
        OnClickListenerImpl listener = new OnClickListenerImpl();
        mBack.setOnClickListener(listener);
        mSubmit.setOnClickListener(listener);
        mGvSuggestionType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                finaltype=arrtype[i];
                adapter.setselectiontypenum(i);
                adapter.notifyDataSetChanged();

            }
        });
    }

    private class OnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.iv_back:
                    finish();
                    break;

                //提交按钮
                case R.id.ln_suggestion_sub:
                    String strproblems=mEtproblems.getText().toString().trim();
                    String strname=mEtname.getText().toString().trim();
                    String strphone=mEtphone.getText().toString().trim();
                    if (finaltype.equals("")){
                        ToastUtils.show(SuggestionActivity.this,"请选择反馈类型");
                        return;
                    }

                    if (strproblems.equals("")){
                        ToastUtils.show(SuggestionActivity.this,"描述不能为空");
                        return;
                    }
                    if (strname.equals("")){
                        ToastUtils.show(SuggestionActivity.this,"姓名不能为空");
                        return;
                    }
                    if (strphone.equals("")){
                        ToastUtils.show(SuggestionActivity.this,"电话不能为空");
                        return;
                    }


                    JSONObject jbody=null;
                    try {
                        jbody = new JSONObject();
                        jbody.put("userid",userid);
                        jbody.put("uname",strname);
                        jbody.put("uphone",strphone);
                        jbody.put("utype",finaltype);
                        jbody.put("contents",strproblems);
                        jbody.put("data_source",3);


                        jbody.put("method","CollectData");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
                    dohttprequestinfo(UrlUtils.CONTENT,jbodyB);




                    break;
                default:
                    break;
            }
        }
    }


    protected  void dohttprequestinfo(String url,JSONObject  paramhash){
        dialog.show();
        HttpUtils.dopost(url,getApplicationContext(),paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());

                PostInfoSuccessEntry rescode=gson.fromJson(body,PostInfoSuccessEntry.class);
                int finalcode=rescode.getRes();
                if (finalcode==1){
                    handler.sendEmptyMessage(FINISH_CODE);
                }else {
                    ToastUtils.show(SuggestionActivity.this,"保存信息失败");
                }



            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }

}
