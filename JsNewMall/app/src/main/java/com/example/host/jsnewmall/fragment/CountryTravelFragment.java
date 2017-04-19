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
import com.example.host.jsnewmall.adapter.CountryFirstAdapter;
import com.example.host.jsnewmall.adapter.CountrySecondAdapter;
import com.example.host.jsnewmall.adapter.CountryThirdAdapter;
import com.example.host.jsnewmall.adapter.CountryViewPager;
import com.example.host.jsnewmall.adapter.HomeViewPagerAdpter;
import com.example.host.jsnewmall.adapter.TravelThirdAdapter;
import com.example.host.jsnewmall.model.HomeViewPagerInfo;
import com.example.host.jsnewmall.model.JsonmModel;
import com.example.host.jsnewmall.model.TravelCountryEntry;
import com.example.host.jsnewmall.utils.Base64Utils;
import com.example.host.jsnewmall.utils.HttpUtils;
import com.example.host.jsnewmall.utils.JsonUtils;
import com.example.host.jsnewmall.utils.SharedPreferencesUtils;
import com.example.host.jsnewmall.utils.UrlUtils;
import com.example.host.jsnewmall.view.HomeForthGridView;
import com.example.host.jsnewmall.view.LoadingDialog;
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

/**
 * 国内游
 */
public class CountryTravelFragment extends  BaseFragment {
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

    private CountryViewPager countryViewPagerAdpter;


    private SimpleDateFormat mSimpleTime;
    private String nTime;
    Gson gson=new Gson();
    private TravelCountryEntry bodyinfo;
    private int cityid;
    private LoadingDialog dialog;
    private  GridView mGvTravelb;
    private ListView mListView;
    private TextView mTvSelectRoad;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (handler.hasMessages(REFRESH_PLAY)) {
                if (bodyinfo.getList().getBanner().size()==1) {
                   return;
                }else {
                    handler.removeMessages(REFRESH_PLAY);
                }
            }
            switch (msg.what) {
                case FINISH_CODE:

                    initView();

                    initListener();
                    dialog.dismiss();

                    if (bodyinfo.getList().getBanner().size()!=0) {
                        if (countryViewPagerAdpter == null) {
                            countryViewPagerAdpter = new CountryViewPager(mActivity, queue, bodyinfo);

                            pager.setAdapter(countryViewPagerAdpter);

                        } else {
                            countryViewPagerAdpter.notifyDataSetChanged();

                        }
                    }

                    if (bodyinfo.getList().getBanner().size() != 1) {

                        setDot(bodyinfo.getList().getBanner().size());
                    }

                    //判断设置如果只有一张 不轮播
                    if (bodyinfo.getList().getBanner().size()==1) {
                        return;
                    }else {
                        handler.sendEmptyMessageDelayed(START_PLAY, MSG_DELAY);
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

        view=inflater.inflate(R.layout.fragment_travel_around,null);

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
            jbody.put("topic_name", "gn");
            jbody.put("method","Get_ad_byname");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
        dohttpCoutry(UrlUtils.APP_URL,jbodyB);

    }

    private void initView(){
        mActivity=getActivity();
        queue = Volley.newRequestQueue(getActivity());


        pager=(ViewPager) view.findViewById(R.id.around_viewpager);//轮播图
        dot_layout = (LinearLayout) view.findViewById(R.id.ll_dot);


        GridView mGvTravela=(GridView)view.findViewById(R.id.gv_travel_around_first);//热门目的地
        CountryFirstAdapter mTravelFirstAdapter=new CountryFirstAdapter(mActivity,bodyinfo);
        mGvTravela.setAdapter(mTravelFirstAdapter);
        HomeForthGridView.setListViewHeightBasedOnChildren(mGvTravela);
        mGvTravela.deferNotifyDataSetChanged();


        mGvTravelb=(GridView) view.findViewById(R.id.gv_travel_around_second);//热卖推荐
        DisplayMetrics dm = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        HomeForthGridView.setHorizontalScroll(mGvTravelb,density,bodyinfo.getList().getHot_rec().size(),150);
        CountrySecondAdapter mFifthAdapter=new CountrySecondAdapter(mActivity,bodyinfo,queue);
        mGvTravelb.setAdapter(mFifthAdapter);
        mGvTravelb.deferNotifyDataSetChanged();


        mListView= (ListView) view.findViewById(R.id.listview_home_content);//listview 的列表
        CountryThirdAdapter mThirdTravelAdapter=new CountryThirdAdapter(mActivity,bodyinfo,queue);
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
                      if (bodyinfo.getList().getBanner().size()==1) {
                         return;
                      }else {
                          handler.sendEmptyMessageDelayed(REFRESH_PLAY, MSG_DELAY);
                      }
                        break;
                    default:
                        break;
                }
            }
        });

        //热卖推荐
         mGvTravelb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 if (bodyinfo.getList().getHot_rec().get(i).getLine_data()!=null) {
                     Intent intent = new Intent(mActivity, RouteDetailsActivity.class);
                     intent.putExtra("lineid", bodyinfo.getList().getHot_rec().get(i).getLine_data().getId());
                     startActivity(intent);
                 }
             }
         });

        //listview条目点击
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (bodyinfo.getList().getLindes_list().get(i).getLine_data()!=null) {
                    Intent intentlist = new Intent(mActivity, RouteDetailsActivity.class);
                    intentlist.putExtra("lineid", bodyinfo.getList().getLindes_list().get(i).getLine_data().getId());
                    startActivity(intentlist);
                }

            }
        });


        //定制游跳转
        mTvSelectRoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentdingz=new Intent(getActivity(), DingzActivity.class);
                startActivity(intentdingz);
                getActivity().finish();
            }
        });
    }






    protected  void dohttpCoutry(String url,JSONObject  paramhash){
        HttpUtils.dopost(url,getActivity(),paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());
                bodyinfo=gson.fromJson(body, TravelCountryEntry.class);
                handler.sendEmptyMessage(FINISH_CODE);
            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }


}
