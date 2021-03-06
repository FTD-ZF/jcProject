package com.example.host.jsnewmall.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.example.host.jsnewmall.adapter.OrdePersonAdapter;
import com.example.host.jsnewmall.model.CalendarEntry;
import com.example.host.jsnewmall.utils.SharedPreferencesUtils;
import com.example.host.jsnewmall.utils.ToastUtils;
import com.example.host.jsnewmall.view.HorizontalListView;
import com.uu1.nmw.R;

import java.util.List;

/**
 * Created by host on 2017/2/28.
 */

/**
 * 下单成功页面
 */
public class OrderSuccessActivity extends AppCompatActivity {

    private String namea,phonea,emaila;
    private String titleroutename;
    private double allprice;
    private List<CalendarEntry.DataBean.DescBean> mDecList;
    private String selectdate;
    private HorizontalListView mListperson;
    private LinearLayout mBack;
    private TextView mBackHome;
    private TextView mTvwatchorder;
    private TextView mTvpersoninfo;
    private String orderid;
    private int writepersonnum;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_order_success_content);
        getIntentData();
        initView();
        initListener();
    }

    private void getIntentData(){
        Intent intent=getIntent();
        namea=intent.getStringExtra("name");
        phonea=intent.getStringExtra("phone");
        emaila=intent.getStringExtra("email");
        titleroutename=intent.getStringExtra("titleroutename");
        allprice=intent.getDoubleExtra("allprice",0);
        selectdate=intent.getStringExtra("selectdate");
        orderid=intent.getStringExtra("write_orderid");
        writepersonnum=intent.getIntExtra("writepersonnum",0);

        mDecList=(List<CalendarEntry.DataBean.DescBean>)intent.getSerializableExtra("personlist");

    }




    private void initView(){

        TextView mTvtitle=(TextView)findViewById(R.id.tv_title_name_change);
        mTvtitle.setText(getApplicationContext().getResources().getString(R.string.pay_content_m));

        mBack=(LinearLayout)findViewById(R.id.iv_back);//返回
        mBackHome=(TextView)findViewById(R.id.pay_submit_b);//返回首页
        mTvwatchorder=(TextView)findViewById(R.id.pay_submit_a);//查看订单
        mTvpersoninfo=(TextView)findViewById(R.id.tv_order_submit);//完善个人信息



        TextView mTvRouteTitle=(TextView)findViewById(R.id.tv_order_content_a);//路线标题
        mTvRouteTitle.setText(titleroutename);

        TextView mTvDate=(TextView)findViewById(R.id.tv_order_date);//日期
        mTvDate.setText(selectdate+"");

        TextView mTvname=(TextView)findViewById(R.id.tv_order_namea);
        TextView mTvphone=(TextView)findViewById(R.id.tv_order_phonea);
        TextView mTvemail=(TextView)findViewById(R.id.tv_order_emaila);
        mTvname.setText(namea);
        mTvphone.setText(phonea);
        mTvemail.setText(emaila);


        TextView mTvmoney=(TextView)findViewById(R.id.tv_order_money);//金额显示
        mTvmoney.setText(allprice+"");

        TextView mTvpersonnum=(TextView)findViewById(R.id.tv_personnum_success);//平台人数显示

        mListperson=(HorizontalListView)findViewById(R.id.personnuma_listview);
        if (mDecList!=null) {
            OrdePersonAdapter personAdapter = new OrdePersonAdapter(getApplicationContext(), mDecList);
            mListperson.setAdapter(personAdapter);
            mTvpersonnum.setVisibility(View.GONE);
        }else {
            mListperson.setVisibility(View.GONE);
            mTvpersonnum.setText("人数  "+writepersonnum);
        }
    }

    private void initListener(){
        OnClickListenerImpl listener = new OnClickListenerImpl();
        mBack.setOnClickListener(listener);
        mBackHome.setOnClickListener(listener);
        mTvwatchorder.setOnClickListener(listener);
        mTvpersoninfo.setOnClickListener(listener);
    }



    private class OnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_back:
                    Intent intentback=new Intent(OrderSuccessActivity.this,MainActivity.class);
                    intentback.putExtra("id",1);
                    startActivity(intentback);

                    finish();
                    break;
                case R.id.pay_submit_b:

//                    if (SharedPreferencesUtils.doSaveIndexItem(OrderSuccessActivity.this,0)) {
//                        MainActivity.mRadioGroup.check(MainActivity.mRadioGroup.getChildAt(1).getId());
////                    }
                    Intent intent=new Intent(OrderSuccessActivity.this,MainActivity.class);
                    intent.putExtra("id",1);
                    startActivity(intent);
                    finish();

                    break;

                //查看订单
                case R.id.pay_submit_a:
                    Intent intenta=new Intent(OrderSuccessActivity.this,AllOrderActivity.class);
                    startActivity(intenta);

                    break;

                //完善个人信息
                case R.id.tv_order_submit:
                    Intent intenttraveller=new Intent(OrderSuccessActivity.this,PerTravellerInfoActivity.class);
                    intenttraveller.putExtra("write_orderid",orderid);
                    intenttraveller.putExtra("writepersonnum",writepersonnum);
                    startActivity(intenttraveller);

                    break;

                default:
                    break;

            }
        }
    }


    @Override
    public void onBackPressed() {
        Intent intent=new Intent(OrderSuccessActivity.this,MainActivity.class);
        intent.putExtra("id",1);
        startActivity(intent);
        finish();
    }
}
