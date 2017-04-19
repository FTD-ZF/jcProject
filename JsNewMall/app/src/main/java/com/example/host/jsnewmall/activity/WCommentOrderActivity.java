package com.example.host.jsnewmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.host.jsnewmall.adapter.CenterOrderaAdapter;
import com.example.host.jsnewmall.model.CenterOrderaEntry;
import com.example.host.jsnewmall.model.JsonmModel;
import com.example.host.jsnewmall.model.LoginUserEntry;
import com.example.host.jsnewmall.utils.Base64Utils;
import com.example.host.jsnewmall.utils.HttpUtils;
import com.example.host.jsnewmall.utils.JsonUtils;
import com.example.host.jsnewmall.utils.SharedPreferencesUtils;
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

//待评论订单页面
public class WCommentOrderActivity extends BaseActivity {
    private PullToRefreshLayout mPullLayout;
    private static final int FINISH_CODE=100;

    private SimpleDateFormat mSimpleTime;
    private String nTime;
    Gson gson=new Gson();
    private LoginUserEntry userinfo;
    private CenterOrderaEntry mOrderaInfo;
    private PullableListView mListview;
    private int currentpage=1;
    private CenterOrderaAdapter adaptera;
    private List<CenterOrderaEntry.OrderlistBean> mBodyListaa;
    private boolean refreshState = false;
    private LoadingDialog dialog;
    private LinearLayout mBack;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FINISH_CODE:
                    dialog.dismiss();
                    List<CenterOrderaEntry.OrderlistBean> mBodyList = mOrderaInfo.getOrderlist();


                    if (refreshState) {
                        mBodyListaa.clear();
                        refreshState = false;
                    }
                    mBodyListaa.addAll(mBodyList);
                    if (adaptera==null) {
                        adaptera = new CenterOrderaAdapter(WCommentOrderActivity.this, mBodyListaa);
                        mListview.setAdapter(adaptera);
                    }else {
                        adaptera.notifyDataSetChanged();
                    }

                    //条目点击进入详情
                    mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            String orderid=mBodyListaa.get(position).getOrderid();
                            Intent intent=new Intent(WCommentOrderActivity.this, OrderDetailsActivity.class);
                            intent.putExtra("orderid",orderid);
                            startActivity(intent);

                        }
                    });

                    break;
                default:
                    break;

            };
        }
    };




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_center_order_a_content);

        dialog=new LoadingDialog(WCommentOrderActivity.this);
        dialog.show();
        Date d=new Date();
        mSimpleTime=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        nTime=mSimpleTime.format(d);
        mBodyListaa = new ArrayList<CenterOrderaEntry.OrderlistBean>();
        userinfo=gson.fromJson(SharedPreferencesUtils.getUserInfo(WCommentOrderActivity.this),LoginUserEntry.class);
        initData();
        initView();
        initListener();

        LinearLayout mLnlayout=(LinearLayout)findViewById(R.id.ln_wcomment_titlebar);
        mLnlayout.setVisibility(View.VISIBLE);

        TextView mTvTitlte=(TextView)findViewById(R.id.tv_title_name_change);//标题
        mTvTitlte.setText("待评论订单");
        mTvTitlte.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_6));
        ImageView mImgMessage=(ImageView)findViewById(R.id.img_title_message);//更多图案显示
        mImgMessage.setVisibility(View.GONE);
        mBack=(LinearLayout)findViewById(R.id.iv_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void initData(){


        JSONObject jbody=null;
        try {
            jbody = new JSONObject();
            jbody.put("user_id",userinfo.getUserid());
            jbody.put("order_state",0);//
            jbody.put("page",currentpage);//页数
            jbody.put("method","QueryOrderList");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
        dohttpOrderaInfo(UrlUtils.ROUTE_LINE,jbodyB);

    }


    private void initView(){
        mPullLayout=(PullToRefreshLayout)findViewById(R.id.refresh_view_ordera);//布局刷新监听
        mListview=(PullableListView)findViewById(R.id.content_view);//listview

    }
    private void initListener(){


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


    protected  void dohttpOrderaInfo(String url,JSONObject  paramhash){
        HttpUtils.dopost(url,WCommentOrderActivity.this,paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());
                mOrderaInfo=gson.fromJson(body, CenterOrderaEntry.class);
                handler.sendEmptyMessage(FINISH_CODE);
            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }
}
