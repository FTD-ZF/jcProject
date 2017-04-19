package com.example.host.jsnewmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.DatePicker;
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
import java.util.Calendar;
import java.util.Date;

/**
 * Created by host on 2017/3/24.
 */

public class ChangeBirthActivity extends BaseActivity {
    private String userid;
    private String date;

    private LinearLayout mBack;
    private TextView mTvSubmit;
    private SimpleDateFormat mSimpleTime;
    private String nTime;
    Gson gson=new Gson();
    private LoadingDialog dialog;
    private static final int FINISH_CODE=100;
    private static final int FINISH_CODE_A=101;
    private EditNameSuccessEntry mEtinfo;
    private String finalbirthday;
    private RelativeLayout mRlcalendar;
    private TextView mTvDate;
    private DatePicker mDatePicker;

    int year = 0;
    int monthOfYear = 0;
    int dayOfMonth = 0;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FINISH_CODE:
                    dialog.dismiss();
                    ToastUtils.show(ChangeBirthActivity.this,"修改成功");
                    setResult(45);
                    finish();
                    break;
                case FINISH_CODE_A:
                    dialog.dismiss();
                    ToastUtils.show(ChangeBirthActivity.this,"保存失败");
                    break;
                default:
                    break;

            };
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changebirth_content);

        dialog=new LoadingDialog(ChangeBirthActivity.this);
        Date d=new Date();
        mSimpleTime=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        nTime=mSimpleTime.format(d);



        initGetIntentData();
        initView();
        initListener();
    }

    private void initGetIntentData(){
        Intent intent=getIntent();
        userid=intent.getStringExtra("userid");
        date=intent.getStringExtra("birthday");
        finalbirthday=date;
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

        mRlcalendar=(RelativeLayout)findViewById(R.id.rl_change_calendar);//点击弹出日历
        mTvDate=(TextView) findViewById(R.id.tv_change_birthday);//日期显示

        mTvDate.setText(date);
        mDatePicker=(DatePicker)findViewById(R.id.datepicker);//日期选择

        // 获得日历对象
        Calendar c = Calendar.getInstance();
        // 获取当前年份
        year = c.get(Calendar.YEAR);
        // 获取当前月份
        monthOfYear = c.get(Calendar.MONTH);
        // 获取当前月份的天数
        dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        mDatePicker.init(year, monthOfYear, dayOfMonth, new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                ChangeBirthActivity.this.year = year;
                ChangeBirthActivity.this.monthOfYear = monthOfYear;
                ChangeBirthActivity.this.dayOfMonth = dayOfMonth;
                showDate(year, monthOfYear + 1, dayOfMonth);
//                String datefinalaaa=year+"-"+monthOfYear+1+"-"+dayOfMonth;
//                finalbirthday=datefinalaaa;

            }

        });


    }

    private void initListener(){
        OnClickListenerImpl listener = new OnClickListenerImpl();
        mBack.setOnClickListener(listener);
        mTvSubmit.setOnClickListener(listener);
        mRlcalendar.setOnClickListener(listener);
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
                        jbody.put("birthday",finalbirthday);
                        jbody.put("method","EditUserInfo");
                        jbody.put("last_login_time",nTime);
                        jbody.put("last_login_ip","110.110.110.110");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
                    dohttpputuserinfo(UrlUtils.USER,jbodyB);


                    break;

                //弹日历
                case R.id.rl_change_calendar:
                    break;
                default:
                    break;


            }
        }
    }


    protected  void dohttpputuserinfo(String url,JSONObject  paramhash){
        dialog.show();
        HttpUtils.dopost(url,ChangeBirthActivity.this,paramhash, new HttpUtils.CallBack() {
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

    //显示日期的方法
    private void showDate(int year, int monthOfYear, int dayOfMonth) {
        mTvDate.setText( year + "-" + monthOfYear + "-" + dayOfMonth);
        finalbirthday=year + "-" + monthOfYear + "-" + dayOfMonth;
    }


}
