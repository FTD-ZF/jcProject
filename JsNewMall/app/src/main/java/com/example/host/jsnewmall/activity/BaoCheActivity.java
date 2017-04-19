package com.example.host.jsnewmall.activity;

import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import com.example.host.jsnewmall.adapter.BaocheAdapter;
import com.example.host.jsnewmall.adapter.HomeViewPagerAdpter;
import com.example.host.jsnewmall.model.HomeViewPagerInfo;
import com.example.host.jsnewmall.view.DialogCallPhone;
import com.example.host.jsnewmall.view.HomeForthGridView;
import com.example.host.jsnewmall.view.PhoneView;
import com.example.host.jsnewmall.view.XGridView;
import com.uu1.nmw.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by host on 2017/2/20.
 */

public class BaoCheActivity extends BaseActivity {


    private LinearLayout mBack;
    private ViewPager pager;
    private LinearLayout dot_layout;
    private static final int FINISH_CODE=100;
    private static final int START_PLAY = 1;
    private static final int REFRESH_PLAY = 2;
    private static final int CHANGE_PLAY = 3;
    private static final int STOP_PLAY = 4;
    private static final int MSG_DELAY = 3000;
//    private int currentPager = 0;
    private RequestQueue queue;
//    private List<HomeViewPagerInfo> bannerInfoList;
    private String[] arrTexta = new String[]{"http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg",
            "http://img02.tooopen.com/images/20140504/sy_60294738471.jpg",
            "http://pic73.nipic.com/file/20150724/18576408_175304314596_2.jpg"};
//    private HomeViewPagerAdpter homeViewPagerAdpter;
//    private XGridView mGvBaoche;
    private RelativeLayout mCallphone;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FINISH_CODE:


//                    if (homeViewPagerAdpter == null) {
////                        homeViewPagerAdpter = new HomeViewPagerAdpter(getApplicationContext(),queue,bannerInfoList);
////
////                        pager.setAdapter(homeViewPagerAdpter);
//
//                    } else {
//                        homeViewPagerAdpter.notifyDataSetChanged();
//
//                    }




                default:
                    break;

            };
        }
    };






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baoche_content);
        initView();
//        handler.sendEmptyMessage(FINISH_CODE);
        initListener();
    }


    private void initView(){
        TextView mTvTitle= (TextView) findViewById(R.id.tv_title_name_change);//标题
        mTvTitle.setText(getApplicationContext().getResources().getString(R.string.travel_content_t));
        ImageView mImgCall=(ImageView) findViewById(R.id.img_title_call);//电话图案
        mImgCall.setVisibility(View.GONE);
        mBack=(LinearLayout) findViewById(R.id.iv_back);//返回按钮
        mCallphone=(RelativeLayout)findViewById(R.id.rl_sub_call);//跳转客服电话
        queue = Volley.newRequestQueue(getApplicationContext());
//        bannerInfoList = new ArrayList<>();
//        for (int i=0; i<3; i++){
//            HomeViewPagerInfo pt = new HomeViewPagerInfo(arrTexta[i]);
//            bannerInfoList.add(pt);
//        }

//        pager=(ViewPager) findViewById(R.id.viewpager_dingzhi);//轮播图
        dot_layout = (LinearLayout) findViewById(R.id.ll_dot);
        TextView mTvContenta=(TextView) findViewById(R.id.tv_baoche_content_a);//文本内容
        mTvContenta.setText("\u3000\u3000"+getApplicationContext().getResources().getString(R.string.travel_content_u));

//        mGvBaoche =(XGridView) findViewById(R.id.gv_baoche_gridview);//图片gridview显示
//        BaocheAdapter mBaoCheAdapter=new BaocheAdapter(getApplicationContext(),queue);
//        mGvBaoche.setAdapter(mBaoCheAdapter);
//        HomeForthGridView.setListViewHeightBasedOnChildren(mGvBaoche);
//        mGvBaoche.deferNotifyDataSetChanged();

        ScrollView mScrollBaoche =(ScrollView) findViewById(R.id.activity_baoche_scrollview);
        mScrollBaoche.smoothScrollTo(0,0);

    }


    private void initListener(){
        OnClickListenerImpl listener = new OnClickListenerImpl();

        mBack.setOnClickListener(listener);
        mCallphone.setOnClickListener(listener);

    }






    private class OnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_back:
                    finish();

                case R.id.rl_sub_call:

                    DialogCallPhone.showdialog(BaoCheActivity.this);
                    break;

                default:
                    break;

            }


        }


    }

}
