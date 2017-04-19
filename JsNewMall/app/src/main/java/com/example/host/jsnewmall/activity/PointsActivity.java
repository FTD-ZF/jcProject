package com.example.host.jsnewmall.activity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.host.jsnewmall.adapter.MyFragmentPagerAdapter;
import com.example.host.jsnewmall.fragment.PointsaFragment;
import com.example.host.jsnewmall.fragment.PointsbFragment;
import com.example.host.jsnewmall.model.JsonmModel;
import com.example.host.jsnewmall.model.PointsaEntry;
import com.example.host.jsnewmall.model.PointsbEntry;
import com.example.host.jsnewmall.utils.Base64Utils;
import com.example.host.jsnewmall.utils.HttpUtils;
import com.example.host.jsnewmall.utils.JsonUtils;
import com.example.host.jsnewmall.utils.ToastUtils;
import com.example.host.jsnewmall.utils.UrlUtils;
import com.google.gson.Gson;
import com.uu1.nmw.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by host on 2017/3/23.
 */

public class PointsActivity extends BaseActivity {
    private String userid;
    private SimpleDateFormat mSimpleTime;
    private String nTime;
    Gson gson=new Gson();
    private static final int FINISH_CODE=100;
    private PointsaEntry mPointsInfoa;
    private LinearLayout mBack;
    private TextView mTvpointsa,mTvpointsb,mTvpointsc;
    private ImageView mImgPointsb,mImgPointsc;
    private List<Fragment> fragmentList;
    private TextView barText;
    private ViewPager mPager;
    private int currIndex = 1;// 当前页卡编号

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FINISH_CODE:

                    if (mPointsInfoa.getData()!=null){
                        mTvpointsa.setText(mPointsInfoa.getData().getAll()+"积分");
                    }else {
                        ToastUtils.show(PointsActivity.this,mPointsInfoa.getMsg());
                        mTvpointsa.setText(0+"积分");
                    }

                break;
                default:
                    break;

            };
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points_content);
        Date d=new Date();
        mSimpleTime=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        nTime=mSimpleTime.format(d);

        getIntentData();
        initData();
        initView();
        initListener();
    }


    private void getIntentData(){
        Intent intent=getIntent();
        userid=intent.getStringExtra("userid");//获取用户id
    }

    private void initData(){

        JSONObject jbody=null;
        try {
            jbody = new JSONObject();
            jbody.put("user_id",userid);
            jbody.put("type",1);
            jbody.put("page",1);
            jbody.put("method","QueryPoints");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
        dohttpGetfino(UrlUtils.USER,jbodyB);
    }


    private void initView(){

        TextView mTitlename=(TextView)findViewById(R.id.tv_title_name_change);
        mTitlename.setText("我的积分");
        mTitlename.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_3));
        ImageView mImgMore=(ImageView)findViewById(R.id.img_title_message);//更多图案
        mImgMore.setVisibility(View.GONE);
        mBack=(LinearLayout)findViewById(R.id.iv_back);//返回
        mTvpointsa=(TextView)findViewById(R.id.tv_points_a);//积分显示
        mTvpointsb=(TextView)findViewById(R.id.tv_points_b);//积分获取
        mTvpointsc=(TextView)findViewById(R.id.tv_points_c);//积分支出
        mImgPointsb=(ImageView)findViewById(R.id.img_points_b);//线一
        mImgPointsc=(ImageView)findViewById(R.id.img_points_c);//线二

        barText = (TextView) findViewById(R.id.cursoraa);// 动画效果的线此处无效
        mPager=(ViewPager)findViewById(R.id.viewpagera);// viewpager

        fragmentList = new ArrayList<Fragment>();
        PointsaFragment mFirstFragment=new PointsaFragment();
        PointsbFragment mSecondFragment=new PointsbFragment();
        fragmentList.add(mFirstFragment);
        fragmentList.add(mSecondFragment);

        mPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), (ArrayList<Fragment>) fragmentList));
        mPager.setCurrentItem(0);// 设置当前显示标签页为第一页

        mPager.setOnPageChangeListener(new MyOnPageChangeListener()); // 页面变化时的监听器

    }
    private void initListener(){
        OnClickListenerImpl listener = new OnClickListenerImpl();
        mBack.setOnClickListener(listener);

        mTvpointsb.setOnClickListener(new txListener(0));
        mTvpointsc.setOnClickListener(new txListener(1));
    }

    private class OnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            switch (view.getId()) {

                case R.id.iv_back:
                    Intent intent=new Intent(PointsActivity.this,MainActivity.class);
                    intent.putExtra("id",2);
                    startActivity(intent);
                    finish();
                    break;
                default:
                    break;
            }
        }
    }

    protected  void dohttpGetfino(String url,JSONObject  paramhash){
        HttpUtils.dopost(url,PointsActivity.this,paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {
                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());
                mPointsInfoa=gson.fromJson(body, PointsaEntry.class);
                handler.sendEmptyMessage(FINISH_CODE);
            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }


    public class txListener implements View.OnClickListener {
        private int index = 0;

        public txListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            mPager.setCurrentItem(index);

        }
    }


    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub
            // 取得该控件的实例
            LinearLayout.LayoutParams ll = (android.widget.LinearLayout.LayoutParams) barText
                    .getLayoutParams();

            if (currIndex == arg0) {
                ll.leftMargin = (int) (currIndex * barText.getWidth() + arg1
                        * barText.getWidth());
            } else if (currIndex > arg0) {
                ll.leftMargin = (int) (currIndex * barText.getWidth() - (1 - arg1)
                        * barText.getWidth());
            }
            barText.setLayoutParams(ll);

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageSelected(int arg0) {
            // TODO Auto-generated method stub
            currIndex = arg0;
            int i = currIndex + 1;

            switch (arg0) {
                case 0:
                    getBgcolorlayout(mTvpointsb,mImgPointsb);
                    getBgcolorlayoutb(mTvpointsc,mImgPointsc);



                    break;
                case 1:
                    getBgcolorlayoutb(mTvpointsb,mImgPointsb);
                    getBgcolorlayout(mTvpointsc,mImgPointsc);

                    break;



                default:
                    break;
            }

        }
    }


    private void getBgcolorlayout(TextView tvfirst,ImageView imgfirst){
        tvfirst.setTextColor(getApplicationContext().getResources().getColor(R.color.title_orange));
        imgfirst.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.title_orange));
    }

    private void getBgcolorlayoutb(TextView tvsecond,ImageView imgsecond){
        tvsecond.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_3));
        imgsecond.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.travel_title_bar));
    }

}
