package com.example.host.jsnewmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import com.example.host.jsnewmall.adapter.FreeListAdapter;
import com.example.host.jsnewmall.adapter.FreeViewPagerAdapter;
import com.example.host.jsnewmall.adapter.TravelThirdAdapter;
import com.example.host.jsnewmall.model.FreeTravelEntry;
import com.example.host.jsnewmall.model.JsonmModel;
import com.example.host.jsnewmall.utils.Base64Utils;
import com.example.host.jsnewmall.utils.HttpUtils;
import com.example.host.jsnewmall.utils.JsonUtils;
import com.example.host.jsnewmall.utils.SharedPreferencesUtils;
import com.example.host.jsnewmall.utils.UrlUtils;
import com.example.host.jsnewmall.view.HomeForthGridView;
import com.example.host.jsnewmall.view.LoadingDialog;
import com.example.host.jsnewmall.view.RefreshScrollView.PullToRefreshLayout;
import com.example.host.jsnewmall.view.RefreshScrollView.PullableScrollView;
import com.google.gson.Gson;
import com.uu1.nmw.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by host on 2017/3/30.
 */

//自由行 亲子 海岛
public class FreeTravelActivity extends BaseActivity {

    private LinearLayout mBackTitle;
    private int cityid;
    private LoadingDialog dialog;
    private SimpleDateFormat mSimpleTime;
    private String nTime;
    Gson gson=new Gson();
    private String topicname;
    private String titlename;
    private PullableScrollView mAroundScrollView;
    private PullToRefreshLayout mPullscroll;
    private ViewPager pager;
    private LinearLayout dot_layout;
    private static final int FINISH_CODE=100;
    private static final int START_PLAY = 1;
    private static final int REFRESH_PLAY = 2;
    private static final int CHANGE_PLAY = 3;
    private static final int STOP_PLAY = 4;
    private static final int MSG_DELAY = 3000;
    private int currentPager = 0;
    private FreeTravelEntry bodyinfo;

    private FreeViewPagerAdapter freeViewPagerAdapter;
    private RequestQueue queue;
    private TextView mTvsearchroute,mTvdingzhi;
    private ListView mListView;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (handler.hasMessages(REFRESH_PLAY)) {
                handler.removeMessages(REFRESH_PLAY);
            }
            switch (msg.what) {
                case FINISH_CODE:



                    initView();
                    initListener();
                    dialog.dismiss();

                    if (freeViewPagerAdapter == null) {
                        freeViewPagerAdapter = new FreeViewPagerAdapter(FreeTravelActivity.this,queue,bodyinfo);
                        pager.setAdapter(freeViewPagerAdapter);

                    } else {
                        freeViewPagerAdapter.notifyDataSetChanged();

                    }

                    if (bodyinfo.getList().getBanner().size() != 1) {
                        handler.sendEmptyMessageDelayed(START_PLAY, MSG_DELAY);
                        setDot(bodyinfo.getList().getBanner().size());
                    }


                    break;
                case REFRESH_PLAY:
                    currentPager++;
                    pager.setCurrentItem(currentPager);
                    handler.sendEmptyMessageDelayed(REFRESH_PLAY, MSG_DELAY);
                    break;
                case START_PLAY:
                    handler.sendEmptyMessageDelayed(REFRESH_PLAY, MSG_DELAY);
                    break;
                case CHANGE_PLAY:
                    currentPager = msg.arg1;

                    break;

                default:
                    break;

            };
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_travel_content);

        dialog=new LoadingDialog(FreeTravelActivity.this);

        Date d=new Date();
        mSimpleTime=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        nTime=mSimpleTime.format(d);
        cityid= SharedPreferencesUtils.getCityid(FreeTravelActivity.this);
        queue = Volley.newRequestQueue(FreeTravelActivity.this);
        getIntentData();
        initData();


        TextView mTitle=(TextView) findViewById(R.id.tv_title_name_change);//标题设置
        mTitle.setText(titlename);
        mBackTitle=(LinearLayout) findViewById(R.id.iv_back);//返回按钮
        ImageView mImgmessage=(ImageView)findViewById(R.id.img_title_message);//消息显示
        mImgmessage.setVisibility(View.GONE);
        ImageView imgcall=(ImageView)findViewById(R.id.img_title_call);//电话显示
        imgcall.setVisibility(View.GONE);

        mTvsearchroute=(TextView)findViewById(R.id.tv_free_search_route);//跳转跟多线路
        mTvdingzhi=(TextView)findViewById(R.id.tv_free_dingzhi);//跳转定制游

    }
    private void getIntentData(){
        Intent intent=getIntent();
        topicname=intent.getStringExtra("topicname");
        titlename=intent.getStringExtra("titlename");
    }

    private void initData(){

        JSONObject jbody=null;
        try {
            jbody = new JSONObject();
            jbody.put("city_id",cityid);
            jbody.put("topic_name", topicname);
            jbody.put("method","Get_ad_byname");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
        dohttpFree(UrlUtils.APP_URL,jbodyB);

    }

    private void initView(){


        mAroundScrollView=(PullableScrollView)findViewById(R.id.free_scrollview);//scrollview布局
        mPullscroll=(PullToRefreshLayout)findViewById(R.id.refresh_view);//scrollview外层布局用来刷新



        pager=(ViewPager)findViewById(R.id.free_viewpager);//轮播图
        dot_layout = (LinearLayout)findViewById(R.id.ll_dot);



        mListView= (ListView) findViewById(R.id.listview_home_content);//listview 的列表
        FreeListAdapter adapterlist=new FreeListAdapter(FreeTravelActivity.this,queue,bodyinfo);
        mListView.setAdapter(adapterlist);
        HomeForthGridView.setListViewHeight(mListView);
        mListView.deferNotifyDataSetChanged();


    }
    private void initListener(){
        OnClickListenerImpl listener = new OnClickListenerImpl();
        mBackTitle.setOnClickListener(listener);
        mTvdingzhi.setOnClickListener(listener);
        mTvsearchroute.setOnClickListener(listener);

        //跳转线路详情
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentdetails=new Intent(FreeTravelActivity.this,RouteDetailsActivity.class);
                intentdetails.putExtra("lineid", bodyinfo.getList().getLindes_list().get(i).getLine_data().getId());
                startActivity(intentdetails);
            }
        });


        if (bodyinfo.getList().getBanner().size()!=0) {
            pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {

                    handler.sendMessage(Message.obtain(handler, CHANGE_PLAY,
                            position, 0));
                    currentPager = position % bodyinfo.getList().getBanner().size();
                    for (int i = 0; i < dot_layout.getChildCount(); i++) {

                        dot_layout.getChildAt(i).setEnabled(i == currentPager);

                    }

                }

                @Override
                public void onPageScrolled(int arg0, float arg1, int arg2) {

                }

                @Override
                public void onPageScrollStateChanged(int arg0) {
                    switch (arg0) {
                        case ViewPager.SCROLL_STATE_DRAGGING:
                            handler.sendEmptyMessage(STOP_PLAY);
                            break;
                        case ViewPager.SCROLL_STATE_IDLE:
                            handler.sendEmptyMessageDelayed(REFRESH_PLAY, MSG_DELAY);
                            break;
                        default:
                            break;
                    }
                }
            });

        }



        //下啦刷新 上啦加载
        mPullscroll.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
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

    private class OnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.iv_back:
                    finish();
                    break;

                //跳转第二个主页面
                case R.id.tv_free_search_route:
                    Intent intenta=new Intent(FreeTravelActivity.this,MainActivity.class);
                    intenta.putExtra("id",5);
                    startActivity(intenta);
                    finish();
                    break;
                //定制
                case R.id.tv_free_dingzhi:
                    Intent intentdingzhi=new Intent(FreeTravelActivity.this,DingzActivity.class);
                    startActivity(intentdingzhi);
                    break;


                default:
                    break;
            }
        }
    }



    /**
     * 设置viewpager下面的白点
     */

    private void setDot(int size) {
        for (int i = 0; i < size; i++) {
            View view = new View(FreeTravelActivity.this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20, 20);
            if (i != 0) {
                layoutParams.leftMargin = 25;
            }
            view.setLayoutParams(layoutParams);
            view.setBackgroundResource(R.drawable.dot_shaper);
            dot_layout.addView(view);
            dot_layout.getChildAt(i).setEnabled(i == currentPager);
        }
    }


    protected  void dohttpFree(String url,JSONObject  paramhash){
        dialog.show();
        HttpUtils.dopost(url,FreeTravelActivity.this,paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());

//                bodyinfo=gson.fromJson(body, AroundEntry.class);
                bodyinfo=gson.fromJson(body, FreeTravelEntry.class);

                handler.sendEmptyMessage(FINISH_CODE);

            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }

}
