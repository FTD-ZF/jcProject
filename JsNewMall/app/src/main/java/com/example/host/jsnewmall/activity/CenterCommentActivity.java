package com.example.host.jsnewmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.host.jsnewmall.adapter.CenterCommentAdapter;
import com.example.host.jsnewmall.model.CenterComentChangeEntry;
import com.example.host.jsnewmall.model.CenterCommentEntry;
import com.example.host.jsnewmall.model.JsonmModel;
import com.example.host.jsnewmall.utils.Base64Utils;
import com.example.host.jsnewmall.utils.HttpUtils;
import com.example.host.jsnewmall.utils.JsonUtils;
import com.example.host.jsnewmall.utils.UrlUtils;
import com.example.host.jsnewmall.view.LoadingDialog;
import com.example.host.jsnewmall.view.RefreshScrollView.PullToRefreshLayout;
import com.example.host.jsnewmall.view.RefreshScrollView.PullableListView;
import com.google.gson.Gson;
import com.uu1.nmw.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by host on 2017/4/18.
 */

//我的评价列表
public class CenterCommentActivity extends BaseActivity {
    private LoadingDialog dialog;
    private SimpleDateFormat mSimpleTime;
    private String nTime;
    Gson gson=new Gson();
    private PullToRefreshLayout mPullLayout;
    private PullableListView mListview;
    private TextView mTvWaitComment;
    private LinearLayout mBack;
    private static final int FINISH_CODE=100;
    private String userid;
    private int currentpage=1;
    private CenterComentChangeEntry mBodyinfo;
    private boolean refreshState = false;
    private RequestQueue queue;
    private List<CenterComentChangeEntry.DataBean.CommentInfoBean> mBodyinfolist;

    private CenterCommentAdapter mListAdapter;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FINISH_CODE:
                    dialog.dismiss();
                    initView();
                    initListener();


                    List<CenterComentChangeEntry.DataBean.CommentInfoBean> mbodylist = mBodyinfo.getData().getComment_info();

                    if (refreshState) {
                        mBodyinfolist.clear();
                        refreshState = false;
                    }
                    mBodyinfolist.addAll(mbodylist);
//                    if (bodyinfo.getData().size()!=0) {
                    if (mListAdapter == null) {
                        mListAdapter = new CenterCommentAdapter(CenterCommentActivity.this, mBodyinfolist,queue);
                        mListview.setAdapter(mListAdapter);
                    } else {
                        mListAdapter.notifyDataSetChanged();
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
        setContentView(R.layout.activity_center_comment_content);

        dialog=new LoadingDialog(CenterCommentActivity.this);

        Date d=new Date();
        mSimpleTime=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        nTime=mSimpleTime.format(d);

        mBodyinfolist=new ArrayList<>();
        queue = Volley.newRequestQueue(getApplicationContext());


        getIntentData();
        initData();
        initCurrentView();

    }

    private void getIntentData(){
        Intent intent=getIntent();
        userid=intent.getStringExtra("userid");
    }


    private void initData(){

        JSONObject jbody=null;
        try {
            jbody = new JSONObject();
            jbody.put("uid",userid);
            jbody.put("page",currentpage);
            jbody.put("pagesize",10);
            jbody.put("method","Lines_comment");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
        dohttpGetfinfo(UrlUtils.ROUTE_LINE,jbodyB);

    }


    private void initCurrentView(){



        TextView mTvTitlte=(TextView)findViewById(R.id.tv_title_name_change);//标题
        mTvTitlte.setText("我的评论");
        mTvTitlte.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_6));
        ImageView mImgMessage=(ImageView)findViewById(R.id.img_title_message);//更多图案显示
        mImgMessage.setVisibility(View.GONE);
        mTvWaitComment=(TextView)findViewById(R.id.tv_title_add_traveller);//右上角标题-待评价订单
        mTvWaitComment.setVisibility(View.VISIBLE);
        mTvWaitComment.setText("待点评订单");
        mBack=(LinearLayout)findViewById(R.id.iv_back);

    }
    private void initView(){
        mPullLayout=(PullToRefreshLayout)findViewById(R.id.refresh_view_comment);//布局刷新监听
        mListview=(PullableListView)findViewById(R.id.comment_listview);//listview

        TextView mTvCommentNum=(TextView) findViewById(R.id.tv_comment_num);//显示评论订单数量
//        mTvCommentNum.setText("共"+mBodyinfo.getCount()+"条评价");



    }

    private void initListener(){
        OnClickListenerImpl listener = new OnClickListenerImpl();
        mBack.setOnClickListener(listener);
        mTvWaitComment.setOnClickListener(listener);

        mPullLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
                refreshState = true;
                currentpage = 1;
                initData();
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
                currentpage++;
                initData();
                new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                    }
                }.sendEmptyMessageDelayed(0,3000);
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
                //进入待评价订单
                case R.id.tv_title_add_traveller:
                    Intent intent=new Intent(CenterCommentActivity.this,WCommentOrderActivity.class);
                    startActivity(intent);
                    break;

                default:
                    break;

            }
        }
    }

    protected  void dohttpGetfinfo(String url,JSONObject  paramhash){
        dialog.show();
        HttpUtils.dopost(url,getApplicationContext(),paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());
                mBodyinfo=gson.fromJson(body, CenterComentChangeEntry.class);
                handler.sendEmptyMessage(FINISH_CODE);
            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }

}
