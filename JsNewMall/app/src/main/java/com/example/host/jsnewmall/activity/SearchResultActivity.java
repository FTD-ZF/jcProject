package com.example.host.jsnewmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


import com.example.host.jsnewmall.adapter.SearchResultAdapter;
import com.example.host.jsnewmall.model.HomeViewPagerInfo;
import com.example.host.jsnewmall.model.JsonmModel;
import com.example.host.jsnewmall.model.ResultbInfo;
import com.example.host.jsnewmall.model.ResultcInfo;
import com.example.host.jsnewmall.model.ResultdInfo;
import com.example.host.jsnewmall.model.SearchResultEntry;
import com.example.host.jsnewmall.model.SearchkeywordEntry;
import com.example.host.jsnewmall.utils.Base64Utils;
import com.example.host.jsnewmall.utils.HttpUtils;
import com.example.host.jsnewmall.utils.JsonUtils;
import com.example.host.jsnewmall.utils.SharedPreferencesUtils;
import com.example.host.jsnewmall.utils.UrlUtils;
import com.example.host.jsnewmall.view.HomeForthGridView;
import com.example.host.jsnewmall.view.LoadingDialog;
import com.example.host.jsnewmall.view.RefreshScrollView.PullToRefreshLayout;
import com.example.host.jsnewmall.view.RefreshScrollView.PullableListView;
import com.example.host.jsnewmall.view.ResultaPopView;
import com.example.host.jsnewmall.view.ResultbPopView;
import com.example.host.jsnewmall.view.ResultcPopView;
import com.example.host.jsnewmall.view.ResultdPopView;
import com.google.gson.Gson;
import com.uu1.nmw.R;
import com.yyydjk.library.DropDownMenu;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by host on 2017/2/21.
 */

public class SearchResultActivity extends BaseActivity {


    private static final int FINISH_CODE=100;
    private static final int FINISH_CODE_B=101;
    private static final int FINISH_CODE_C=102;
    private static final int FINISH_CODE_D=103;
    private static final int FINISH_CODE_KEY=104;
    private static final int FINISH_CODE_FINAL=105;
    private LinearLayout mBack;
    private LinearLayout mLnSearchAgain;

    private String headers[] = {"城市", "年龄", "性别"};
    private String citys[] = {"常州", "江阴", "无锡", "南京", "上海"};
    private int cityid[]= {115,1204,113,112,10};
    private String ages[] = {"18岁以下", "18-22岁", "23-26岁", "27-35岁", "35岁以上"};
    private String arrtemps[]={"数据一", "数据二", "数据三", "数据四", "数据五",};


    private int sorta[]={0,1,11,3,33};
    private String sortb[]={"全部","价格低到高","价格高到低","销量高","销量低"};

    private String travelmodea[]={"跟团","自由行","半自助","直通车","其他"};
    private int travelmodeb[]={1,2,4,5,3};


//    private ListView mListResult;
    private RequestQueue queue;
    private LinearLayout mLnResulta;
    private LinearLayout mLnResultb;
    private LinearLayout mLnResultc;
    private LinearLayout mLnResultd;
//    private List<HomeViewPagerInfo> dataList;
    private List<ResultbInfo> dataListb;
    private List<ResultcInfo> dataList;
//    private List<ResultdInfo> dataListd;
    private String resultdata;
    private int sortid;
    private TextView mTvResulta;
    private String resultdatab;
    private TextView mTvResultb;
    private TextView mTvResultc;
    private TextView mTvResultd;
    private String resultdatac;//行程天数
//    private String resultdatad;
    private String keyword;
    private LoadingDialog dialog;
    private SimpleDateFormat mSimpleTime;
    private String nTime;
    Gson gson=new Gson();
    private int cityida;
    private SearchResultEntry bodyinfo;
    private SearchkeywordEntry mSearchkeywordinfo;
    private PullToRefreshLayout mPullLayout;
    private PullableListView mListview;
    private int currentpage=1;
    private boolean refreshState = false;
    private List<SearchResultEntry.DataBean> mBodyinfolist;
    private SearchResultAdapter mListAdapter;
    private int travelId;
    private String subjectname;
    private String gotrafficaid;
    private String backtrafficaid;
    private String mStartprice;
    private String mEndprice;
    private String leavecityid;
//    private String searchdateid;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);

            switch (msg.what) {

                case FINISH_CODE_KEY:



                    break;

                case FINISH_CODE_FINAL:
                    dialog.dismiss();

                    initView();
                    initListener();

                        List<SearchResultEntry.DataBean> mbodylist = bodyinfo.getData();

                        if (refreshState) {
                            mBodyinfolist.clear();
                            refreshState = false;
                        }
                        mBodyinfolist.addAll(mbodylist);
//                    if (bodyinfo.getData().size()!=0) {
                        if (mListAdapter == null) {
                            mListAdapter = new SearchResultAdapter(getApplicationContext(), queue, mBodyinfolist);
                            mListview.setAdapter(mListAdapter);
                        } else {
                            mListAdapter.notifyDataSetChanged();
                        }


//                    }


                    break;

                //第一组条件
                case FINISH_CODE:
//                    mBodyinfolist.clear();
                    refreshState = true;
                    currentpage = 1;
                    setTextViewBackColor(mTvResulta,resultdata);
                    initData();
                    break;
                //第二组条件
                case FINISH_CODE_B:
                    setTextViewBackColor(mTvResultb,resultdatab);

                    refreshState = true;
                    currentpage = 1;

                    initData();
                    break;
                //行程天数
                case FINISH_CODE_C:
//                    mBodyinfolist.clear();
                    refreshState = true;
                    currentpage = 1;

                    if (resultdatac.equals("全部")){
                        setTextViewBackColor(mTvResultc, resultdatac + "天数");
                    }else {
                        setTextViewBackColor(mTvResultc, resultdatac + "天");
                    }

                    initData();
//                    mListview.invalidate();

                    break;
                case FINISH_CODE_D:
//                    setTextViewBackColor(mTvResultd,resultdatad);
                    refreshState = true;
                    currentpage = 1;
                    initData();

                    break;
                default:
                    break;

            };
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result_view);
        dialog=new LoadingDialog(SearchResultActivity.this);

        Date d=new Date();
        mSimpleTime=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        nTime=mSimpleTime.format(d);
        cityida= SharedPreferencesUtils.getCityid(SearchResultActivity.this);

        mBodyinfolist=new ArrayList<>();
        getIntentData();
        initSearchData();
        initData();



        mBack=(LinearLayout) findViewById(R.id.iv_back);//返回按钮
        mPullLayout=(PullToRefreshLayout)findViewById(R.id.refresh_view_search);//布局刷新监听
        mListview=(PullableListView)findViewById(R.id.content_view);//listview


    }

    private void getIntentData(){
        Intent intent=getIntent();
        keyword=intent.getStringExtra("searchcontent");
    }
    private void initSearchData(){
        JSONObject jbodys=null;
        try {
            jbodys = new JSONObject();
            jbodys.put("method","SearchKeyword");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyBs= JsonUtils.JsonParseInfo(nTime,jbodys);
        dohttpSearchkeywordinfo(UrlUtils.APP_URL,jbodyBs);
    }

    private void initData(){
        JSONObject jbody=null;
        try {
            jbody = new JSONObject();
            jbody.put("cityID",cityida);
            jbody.put("keyword",keyword);//搜索关键字(根据主团名称，副标题和线路途经景点搜索,快点自驾酒店名,酒店描述)
            jbody.put("days",resultdatac);//	线路天数
            jbody.put("sort",sortid);//排序(0-默认，1-价钱从低到高，11-价钱从高到
            jbody.put("travelmode",travelId);//出游方式：1跟团、2自由行、3其他、4半自助、5直通车',
            jbody.put("limit",10);//列表限制条数
            jbody.put("page",currentpage);//页数
            jbody.put("subjectname",subjectname);//主题 例如 1,2,3（type=2 不起作用）
            jbody.put("gotrafficattrname",gotrafficaid);//去程交通 （type=2 不起作用）
            jbody.put("backtrafficattrname",backtrafficaid);//返程交通 （type=2 不起作用）
            jbody.put("begin_price",mStartprice);//开始价格
            jbody.put("end_price",mEndprice);//结束价格
            jbody.put("leaveID",leavecityid);//出发城市/出港港口ID
//            jbody.put("godate",searchdateid);//出发日期,2016-01-02 00:00:00格式的时间戳,例如:1465142400,1465142400,1465142400

            jbody.put("method","Search_app");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
        dohttpSearchinfo(UrlUtils.APP_URL,jbodyB);
    }

    private void initView(){


//        dataList = new ArrayList<>();
//        for (int i=0; i<5; i++){
//            HomeViewPagerInfo pt = new HomeViewPagerInfo(arrtemps[i]);
//            dataList.add(pt);
//        }

        //第二组收索条件--出游方式
        dataListb=new ArrayList<>();
        for (int i=0; i<5; i++){
            ResultbInfo ptb = new ResultbInfo(travelmodea[i],travelmodeb[i]);
            dataListb.add(ptb);
        }

        //第一组收索条件
        dataList=new ArrayList<>();
        for (int i=0; i<sorta.length; i++){
            ResultcInfo ptc = new ResultcInfo(sortb[i],sorta[i]);
            dataList.add(ptc);
        }

//        dataListd=new ArrayList<>();
//        for (int i=0; i<3; i++){
//            ResultdInfo ptd = new ResultdInfo(headers[i]);
//            dataListd.add(ptd);
//        }

        queue = Volley.newRequestQueue(getApplicationContext());


        mLnSearchAgain= (LinearLayout) findViewById(R.id.tv_search_address_again);//搜索框点击





//        mListResult=(ListView) findViewById(R.id.result_listview_a);//结果列表

//        mListResult.setAdapter(mListAdapter);
//        HomeForthGridView.setListViewHeight(mListResult);
//        mListResult.deferNotifyDataSetChanged();

        mLnResulta=(LinearLayout) findViewById(R.id.ln_searchresult_a);//推荐排序
        mLnResultb=(LinearLayout) findViewById(R.id.ln_searchresult_b);//游玩方式
        mLnResultc=(LinearLayout) findViewById(R.id.ln_searchresult_c);//行程天数
        mLnResultd=(LinearLayout) findViewById(R.id.ln_searchresult_d);//筛选
        mTvResulta=(TextView) findViewById(R.id.tv_searchresult_a);
        mTvResultb=(TextView) findViewById(R.id.tv_searchresult_b);
        mTvResultc=(TextView) findViewById(R.id.tv_searchresult_c);
        mTvResultd=(TextView) findViewById(R.id.tv_searchresult_d);





    }

    private void initListener(){
        OnClickListenerImpl listener=new OnClickListenerImpl();
        mBack.setOnClickListener(listener);
        mLnSearchAgain.setOnClickListener(listener);
        mLnResulta.setOnClickListener(listener);
        mLnResultb.setOnClickListener(listener);
        mLnResultc.setOnClickListener(listener);
        mLnResultd.setOnClickListener(listener);


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



        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(SearchResultActivity.this,RouteDetailsActivity.class);
                intent.putExtra("lineid",bodyinfo.getData().get(i).getId());
                startActivity(intent);
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
                case R.id.tv_search_address_again:
                    Intent intent=new Intent(getApplicationContext(),SearchActivity.class);
                    startActivity(intent);
                    finish();
                    break;

                //第一组条件
                case R.id.ln_searchresult_a:
                    ResultaPopView mResultaPopView=new ResultaPopView(SearchResultActivity.this,dataList, new ResultaPopView.CallBackUi() {
                        @Override
                        public void onRequestUi(String resulta,int chooseid) {
                             resultdata=resulta;
                             sortid=chooseid;

                            handler.sendEmptyMessage(FINISH_CODE);
                        }
                    });
                    mResultaPopView.showpop(view);
                    break;
                //第二组条件
                case R.id.ln_searchresult_b:

                    ResultbPopView mResultaPopViewb=new ResultbPopView(SearchResultActivity.this, dataListb, new ResultbPopView.CallBackUib() {
                        @Override
                        public void onRequestUi(String resultb,int tid) {
                            resultdatab=resultb;
                            travelId=tid;
                            handler.sendEmptyMessage(FINISH_CODE_B);
                        }
                    });
                    mResultaPopViewb.showpop(view);

                    break;
                //x行程天数点击选择
                case R.id.ln_searchresult_c:
                    ResultcPopView mResultaPopViewc=new ResultcPopView(SearchResultActivity.this, mSearchkeywordinfo.getDaycountdata(), new ResultcPopView.CallBackUi() {
                        @Override
                        public void onRequestUi(String resultc) {
                            resultdatac=resultc;
//                            initData();

                            handler.sendEmptyMessage(FINISH_CODE_C);
                        }
                    });
                    mResultaPopViewc.showpop(view);
                    break;
                //筛选
                case R.id.ln_searchresult_d:
//                    Window win=getWindow();
                    ResultdPopView mResultaPopViewd=new ResultdPopView(SearchResultActivity.this, mSearchkeywordinfo, new ResultdPopView.CallBackUi() {
                        @Override
                        public void onRequestUi(String searchdate,String pricea,String priceb,String subject,String gotraffica,
                                                String backtraffica,String leavecity) {

//                            searchdateid=searchdate;
                            mStartprice=pricea;
                            mEndprice=priceb;
                            subjectname=subject;
                            gotrafficaid=gotraffica;
                            backtrafficaid=backtraffica;
                            leavecityid=leavecity;
                            handler.sendEmptyMessage(FINISH_CODE_D);
                        }
                    });
                    mResultaPopViewd.showpop(view);

                    break;
                default:
                    break;

            }
        }
    }

    //搜索后获取数据
    protected  void dohttpSearchinfo(String url,JSONObject  paramhash){
        dialog.show();
        HttpUtils.dopost(url,SearchResultActivity.this,paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());

                bodyinfo=gson.fromJson(body, SearchResultEntry.class);

                handler.sendEmptyMessage(FINISH_CODE_FINAL);

            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }



    //获取搜索条件
    protected  void dohttpSearchkeywordinfo(String url,JSONObject  paramhash){
        HttpUtils.dopost(url,SearchResultActivity.this,paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());

                mSearchkeywordinfo=gson.fromJson(body, SearchkeywordEntry.class);

                handler.sendEmptyMessage(FINISH_CODE_KEY);

            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }


    private void setTextViewBackColor(TextView mTview,String data){
        mTview.setText(data);
        mTview.setTextColor(getApplicationContext().getResources().getColor(R.color.title_orange));
    }

}
