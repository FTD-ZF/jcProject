package com.example.host.jsnewmall.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.host.jsnewmall.activity.DingzActivity;
import com.example.host.jsnewmall.activity.RouteDetailsActivity;
import com.uu1.nmw.R;
import com.example.host.jsnewmall.adapter.CountrySecondAdapter;
import com.example.host.jsnewmall.adapter.HomeViewPagerAdpter;
import com.example.host.jsnewmall.adapter.TravelOutFirstAdapter;
import com.example.host.jsnewmall.adapter.TravelOutPagerAdapter;
import com.example.host.jsnewmall.adapter.TravelOutSecondAdapter;
import com.example.host.jsnewmall.adapter.TravelOutThirdAdapter;
import com.example.host.jsnewmall.model.HomeViewPagerInfo;
import com.example.host.jsnewmall.model.JsonmModel;
import com.example.host.jsnewmall.model.TravelOutEntry;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by host on 2017/2/16.
 */
//出境游
public class OutTravelFragment extends  BaseFragment {
    private View view;

    private Activity mActivity;
    private ViewPager pager;
    private LinearLayout dot_layout;
    private RequestQueue queue;

    private static final int FINISH_CODE=100;
    private static final int START_PLAY = 1;
    private static final int REFRESH_PLAY = 2;
    private static final int CHANGE_PLAY = 3;
    private static final int STOP_PLAY = 4;
    private static final int MSG_DELAY = 3000;
    private int currentPager = 0;
    private TravelOutPagerAdapter travelOutPagerAdapter;

    private SimpleDateFormat mSimpleTime;
    private String nTime;
    Gson gson=new Gson();
    private int cityid;
    private LoadingDialog dialog;
    private TravelOutEntry bodyinfo;
    private PullableScrollView mOutScrollView;
    private PullToRefreshLayout mPullscroll;
    private ListView mListView;
    private GridView mGvTravelb;
    private TextView mTvSelectRoad;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (handler.hasMessages(REFRESH_PLAY)) {
                handler.removeMessages(REFRESH_PLAY);
            }
            switch (msg.what) {
                case FINISH_CODE:
                    dialog.dismiss();

                    initView();
                    initListener();


                    if (bodyinfo.getList().getBanner().size()!=0) {
                        if (travelOutPagerAdapter == null) {
                            travelOutPagerAdapter = new TravelOutPagerAdapter(mActivity, queue, bodyinfo);

                            pager.setAdapter(travelOutPagerAdapter);

                        } else {
                            travelOutPagerAdapter.notifyDataSetChanged();

                        }
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





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_out_travel_content,null);
        dialog=new LoadingDialog(getActivity());
        dialog.show();
        Date d=new Date();
        mSimpleTime=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        nTime=mSimpleTime.format(d);
        cityid= SharedPreferencesUtils.getCityid(getActivity());
        initData();

        return view;
    }
    private void initData(){
        JSONObject jbody=null;
        try {
            jbody = new JSONObject();
            jbody.put("city_id",cityid);
            jbody.put("topic_name", "cj");
            jbody.put("method","Get_ad_byname");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
        dohttpOut(UrlUtils.APP_URL,jbodyB);

    }

    private void initView(){
        mActivity=getActivity();
        queue = Volley.newRequestQueue(getActivity());


        pager=(ViewPager) view.findViewById(R.id.around_viewpager);//轮播图
        dot_layout = (LinearLayout) view.findViewById(R.id.ll_dot);


        mOutScrollView=(PullableScrollView) view.findViewById(R.id.outtravel_scrollview);//scrollview布局
        mPullscroll=(PullToRefreshLayout)view.findViewById(R.id.refresh_view);//scrollview外层布局用来刷新

        GridView mGvTravela=(GridView)view.findViewById(R.id.gv_travel_out_first);//出境8地选择
        TravelOutFirstAdapter mTravelFirstAdapter=new TravelOutFirstAdapter(mActivity,bodyinfo,queue);
        mGvTravela.setAdapter(mTravelFirstAdapter);
        HomeForthGridView.setListViewHeightBasedOnChildren(mGvTravela);
        mGvTravela.deferNotifyDataSetChanged();



        mGvTravelb=(GridView) view.findViewById(R.id.gv_travel_around_second);//热卖推荐
        DisplayMetrics dm = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        HomeForthGridView.setHorizontalScroll(mGvTravelb,density,bodyinfo.getList().getHot_rec().size(),150);
        TravelOutSecondAdapter mTravelOutAdapter=new TravelOutSecondAdapter(mActivity,bodyinfo,queue);
        mGvTravelb.setAdapter(mTravelOutAdapter);
        mGvTravelb.deferNotifyDataSetChanged();


        mListView= (ListView) view.findViewById(R.id.listview_home_content);//listview 的列表
        TravelOutThirdAdapter mThirdTravelAdapter=new TravelOutThirdAdapter(mActivity,bodyinfo,queue);
        mListView.setAdapter(mThirdTravelAdapter);
        HomeForthGridView.setListViewHeight(mListView);
        mListView.deferNotifyDataSetChanged();

        mTvSelectRoad=(TextView) view.findViewById(R.id.tv_select_travel_route);//底部路线选择


    }



    /**
     * 设置viewpager下面的白点
     */

    private void setDot(int size) {
        for (int i = 0; i < size; i++) {
            View view = new View(mActivity);
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

    private void initListener(){

        //定制游跳转
        mTvSelectRoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentdingz=new Intent(getActivity(), DingzActivity.class);
                startActivity(intentdingz);
                getActivity().finish();
            }
        });



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



        //热卖推荐
        mGvTravelb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (bodyinfo.getList().getHot_rec().get(i).getLine_data()!=null) {
                    Intent intent = new Intent(getActivity(), RouteDetailsActivity.class);
                    intent.putExtra("lineid", bodyinfo.getList().getHot_rec().get(i).getLine_data().getId());
                    startActivity(intent);
                }
            }
        });


        //列表跳转
         mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 if (bodyinfo.getList().getLindes_list().get(i).getLine_data()!=null) {
                     Intent intent = new Intent(getActivity(), RouteDetailsActivity.class);
                     intent.putExtra("lineid", bodyinfo.getList().getLindes_list().get(i).getLine_data().getId());
                     startActivity(intent);
                 }
             }
         });

    }



    protected  void dohttpOut(String url,JSONObject  paramhash){
        HttpUtils.dopost(url,getActivity(),paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());

//                bodyinfo=gson.fromJson(body, AroundEntry.class);
                bodyinfo=gson.fromJson(body, TravelOutEntry.class);

                handler.sendEmptyMessage(FINISH_CODE);

            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }


}
