package com.example.host.jsnewmall.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uu1.nmw.R;
import com.example.host.jsnewmall.adapter.PointsbAdapter;
import com.example.host.jsnewmall.model.JsonmModel;
import com.example.host.jsnewmall.model.LoginUserEntry;
import com.example.host.jsnewmall.model.PointsaEntry;
import com.example.host.jsnewmall.model.PointsbEntry;
import com.example.host.jsnewmall.utils.Base64Utils;
import com.example.host.jsnewmall.utils.HttpUtils;
import com.example.host.jsnewmall.utils.JsonUtils;
import com.example.host.jsnewmall.utils.SharedPreferencesUtils;
import com.example.host.jsnewmall.utils.ToastUtils;
import com.example.host.jsnewmall.utils.UrlUtils;
import com.example.host.jsnewmall.view.LoadingDialog;
import com.example.host.jsnewmall.view.RefreshScrollView.PullToRefreshLayout;
import com.example.host.jsnewmall.view.RefreshScrollView.PullableListView;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by host on 2017/3/23.
 */

public class PointsbFragment extends BaseFragment {
    private View view;
    private LoadingDialog dialog;

    private static final int FINISH_CODE=100;
    private LoginUserEntry userinfo;
    private SimpleDateFormat mSimpleTime;
    private String nTime;
    Gson gson=new Gson();
    private List<PointsbEntry.DataBean.ListBean> mBodyListbb;
    private boolean refreshState = false;
    private int currentpage=1;
    private PointsbEntry mPointsInfob;
    private PullToRefreshLayout mPullLayout;
    private PullableListView mListview;
    private PointsbAdapter adaptera;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FINISH_CODE:
                    dialog.dismiss();


                    if (mPointsInfob.getData()!=null) {
                        List<PointsbEntry.DataBean.ListBean> mBodyList = mPointsInfob.getData().getList();


                        if (refreshState) {
                            mBodyListbb.clear();
                            refreshState = false;
                        }
                        mBodyListbb.addAll(mBodyList);
                        if (adaptera == null) {
                            adaptera = new PointsbAdapter(getActivity(), mBodyListbb);
                            mListview.setAdapter(adaptera);
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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_points_b_content,null);

        dialog=new LoadingDialog(getActivity());
        dialog.show();
        Date d=new Date();
        mSimpleTime=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        nTime=mSimpleTime.format(d);
        userinfo=gson.fromJson(SharedPreferencesUtils.getUserInfo(getActivity()),LoginUserEntry.class);
        mBodyListbb=new ArrayList<PointsbEntry.DataBean.ListBean>();
        initData();
        initView();
        initListener();


        return view;
    }



    private void initData(){
        JSONObject jbody=null;
        try {
            jbody = new JSONObject();
            jbody.put("user_id",userinfo.getUserid());
            jbody.put("type",2);
            jbody.put("page",currentpage);
            jbody.put("method","QueryPoints");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
        dohttpGetfino(UrlUtils.USER,jbodyB);
    }
    private void initView(){
        mPullLayout=(PullToRefreshLayout)view.findViewById(R.id.refresh_view_pointsb);//布局刷新监听
        mListview=(PullableListView)view.findViewById(R.id.content_view);//listview
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


    protected  void dohttpGetfino(String url,JSONObject  paramhash){
        HttpUtils.dopost(url,getActivity(),paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {
                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());
                mPointsInfob=gson.fromJson(body, PointsbEntry.class);


                handler.sendEmptyMessage(FINISH_CODE);
            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }

}
