package com.example.host.jsnewmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.host.jsnewmall.adapter.CollectListAdapter;
import com.example.host.jsnewmall.bean.ICollectionInterface;
import com.example.host.jsnewmall.model.CollectionSearchEntry;
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
 * Created by host on 2017/3/31.
 */

//收藏页面
public class CollectListActivity extends BaseActivity implements ICollectionInterface{
    private String userid;
    private SimpleDateFormat mSimpleTime;
    private String nTime;
    Gson gson=new Gson();
    private static final int FINISH_CODE=100;
    private LinearLayout mBack;
    private PullToRefreshLayout mPullLayout;
    private PullableListView mPullableListView;
    private CollectionSearchEntry mSearchinfo;
    private int currentpage=1;
    private List<CollectionSearchEntry.DataBean> mBodyListaa;
    private LoadingDialog dialog;
    private boolean refreshState = false;
    private CollectListAdapter adaptera;
    private RequestQueue queue;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FINISH_CODE:
                    dialog.dismiss();
                    initView();
                    initListener();
                    if (mSearchinfo.getData()!=null) {
                        List<CollectionSearchEntry.DataBean> mBodyList = mSearchinfo.getData();


                        if (refreshState) {
                            mBodyListaa.clear();
                            refreshState = false;
                        }
                        mBodyListaa.addAll(mBodyList);

                        if (adaptera == null) {
                            adaptera = new CollectListAdapter(CollectListActivity.this, mBodyListaa,nTime,queue,CollectListActivity.this);
                            mPullableListView.setAdapter(adaptera);
                        } else {
                            adaptera.notifyDataSetChanged();
                        }

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
        setContentView(R.layout.activity_collection_list_content);
        mBodyListaa=new ArrayList<CollectionSearchEntry.DataBean>();
        dialog=new LoadingDialog(CollectListActivity.this);

        Date d=new Date();
        mSimpleTime=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        nTime=mSimpleTime.format(d);

        queue = Volley.newRequestQueue(getApplicationContext());
        initGetData();
        initData();

        TextView mTvTitlte=(TextView)findViewById(R.id.tv_title_name_change);//标题
        mTvTitlte.setText("我的收藏");
        mTvTitlte.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_6));
//        ImageView mImgBack=(ImageView)findViewById(R.id.title_img_back_change);//返回箭头图案
//        mImgBack.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.backicon_a));
        ImageView mImgMessage=(ImageView)findViewById(R.id.img_title_message);//更多图案显示
        mImgMessage.setVisibility(View.GONE);
    }

    private void initGetData(){
        Intent intent=getIntent();
        userid=intent.getStringExtra("userid");
    }

    private void initData(){

        JSONObject jbody=null;
        try {
            jbody = new JSONObject();
            jbody.put("user_id",userid);
            jbody.put("page",currentpage);
            jbody.put("method","FavList");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
        dohttpGetfino(UrlUtils.CONTENT,jbodyB);

    }


    private void initView(){

        mPullLayout=(PullToRefreshLayout)findViewById(R.id.refresh_view_collection);//刷新布局
        mBack=(LinearLayout)findViewById(R.id.iv_back);//返回
        mPullableListView=(PullableListView)findViewById(R.id.collect_listview);//listview


    }

    private void initListener(){
        OnClickListenerImpl listener = new OnClickListenerImpl();
        mBack.setOnClickListener(listener);

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

    //跳转线路详情
    @Override
    public void onCollectionIntent(String lineid) {
        Intent intent=new Intent(CollectListActivity.this,RouteDetailsActivity.class);
        intent.putExtra("lineid",lineid);
        startActivity(intent);

    }

    private class OnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.iv_back:
                    finish();
                    break;

                default:
                    break;


            }
        }
    }

    protected  void dohttpGetfino(String url,JSONObject  paramhash){
        dialog.show();
        HttpUtils.dopost(url,getApplicationContext(),paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());
                mSearchinfo=gson.fromJson(body, CollectionSearchEntry.class);
                handler.sendEmptyMessage(FINISH_CODE);
            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }
}
