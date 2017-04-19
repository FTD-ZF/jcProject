package com.example.host.jsnewmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.host.jsnewmall.adapter.CenterHistoryAdapter;
import com.example.host.jsnewmall.bean.IHistoryInterface;
import com.example.host.jsnewmall.dao.CenterHistoryDao;
import com.example.host.jsnewmall.model.CenterHistoryEntry;
import com.example.host.jsnewmall.utils.SpStrListUtil;
import com.example.host.jsnewmall.utils.ToastUtils;
import com.example.host.jsnewmall.view.RefreshScrollView.PullToRefreshLayout;
import com.example.host.jsnewmall.view.RefreshScrollView.PullableListView;
import com.uu1.nmw.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by host on 2017/4/6.
 */

//浏览历史
public class CenterHistoryActivity extends BaseActivity implements IHistoryInterface{
    private RequestQueue queue;
    private LinearLayout mBack;
    private PullToRefreshLayout mPullLayout;
    private PullableListView mPullableListView;
//    private SpStrListUtil dospsavelist;

    private CenterHistoryDao mDbDao;
    private  List<CenterHistoryEntry> bodylist;
    private SimpleDateFormat mSimpleTime;
    private String nTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_history_content);

        Date d=new Date();
        mSimpleTime=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        nTime=mSimpleTime.format(d);
        initGetspData();
        initView();
        initListener();
    }
    private void initGetspData(){

        mDbDao=new CenterHistoryDao(CenterHistoryActivity.this);
        mDbDao.openDataBase();
        bodylist = mDbDao.queryDataList();//查询所有数据
//        Log.d("shujuchaxun", list.get(0).getLineid()+"");
//
//        ToastUtils.show(CenterHistoryActivity.this,list.size()+"");


//        dospsavelist=new SpStrListUtil(getApplicationContext(),"hisarray");
//        Log.d("sp输出",dospsavelist.getDataList("listMap").toString()+"");

//        List<Map<String, String>> listmap = dospsavelist.getDataList("listMap");
//        String name=listmap.get(0).get("titlename");
//        Log.d("mingzi",name);
    }

    private void initView(){

        queue = Volley.newRequestQueue(getApplicationContext());


        TextView mTvTitlte=(TextView)findViewById(R.id.tv_title_name_change);//标题
        mTvTitlte.setText("浏览历史");
        mTvTitlte.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_6));
//        ImageView mImgBack=(ImageView)findViewById(R.id.title_img_back_change);//返回箭头图案
//        mImgBack.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.backicon_a));
        ImageView mImgMessage=(ImageView)findViewById(R.id.img_title_message);//更多图案显示
        mImgMessage.setVisibility(View.GONE);



        mPullLayout=(PullToRefreshLayout)findViewById(R.id.refresh_view_history);//刷新布局
        mBack=(LinearLayout)findViewById(R.id.iv_back);//返回
        mPullableListView=(PullableListView)findViewById(R.id.history_listview);//listview


        initSetAdapter();

    }
    private void initSetAdapter(){
        if (bodylist!=null) {
            CenterHistoryAdapter adapter = new CenterHistoryAdapter(CenterHistoryActivity.this, bodylist, queue, CenterHistoryActivity.this);
            mPullableListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }


    private void initListener(){
        OnClickListenerImpl listener = new OnClickListenerImpl();
        mBack.setOnClickListener(listener);


        mPullLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {

                new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        // 千万别忘了告诉控件刷新完毕了哦！
                        pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                    }
                }.sendEmptyMessageDelayed(0, 3000);
            }

            @Override
            public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {

                new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                    }
                }.sendEmptyMessageDelayed(0,3000);
            }
        });

    }

    //跳转详情页
    @Override
    public void onHistoryIntent(String lineid) {
        Intent intent=new Intent(CenterHistoryActivity.this,RouteDetailsActivity.class);
        intent.putExtra("lineid",lineid);
        startActivity(intent);
    }

    private class OnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.iv_back:
                    mDbDao.closeDataBase();
                    finish();
                    break;

                default:
                    break;


            }
        }
    }

    @Override
    public void onBackPressed() {
        mDbDao.closeDataBase();
        finish();
    }
}
