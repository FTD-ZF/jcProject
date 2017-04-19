package com.example.host.jsnewmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.host.jsnewmall.adapter.AroundShopAdapter;
import com.example.host.jsnewmall.model.AllShopTypeEntry;
import com.example.host.jsnewmall.model.AroundshopEntry;
import com.example.host.jsnewmall.model.JsonmModel;
import com.example.host.jsnewmall.utils.Base64Utils;
import com.example.host.jsnewmall.utils.HttpUtils;
import com.example.host.jsnewmall.utils.JsonUtils;
import com.example.host.jsnewmall.utils.SharedPreferencesUtils;
import com.example.host.jsnewmall.utils.UrlUtils;
import com.example.host.jsnewmall.view.AllShopTypeView;
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
 * Created by host on 2017/4/13.
 */

//周边门店
public class AroundshopActivity extends BaseActivity {
    private TextView mTvshopname;
    private double mstartlat;
    private double mstartlong;
    private LinearLayout mBack;
    private LinearLayout mLnAllShoplayout,mLnAllTypelayout,mSearchshop,mShowSearchlayout;
    private TextView mTvAllShopname,mTvAllTypename,mTvDismiss;
    private EditText mEtSearchContent;
    private boolean changeshopname=true;
    private TextView mTvTitlte;
    private LinearLayout mLineSearch;
    private int cityida;
    private LoadingDialog dialog;
    private SimpleDateFormat mSimpleTime;
    private String nTime;
    Gson gson=new Gson();
    private int currentpage=1;
    private AroundshopEntry bodyinfo;

    private static final int FINISH_CODE=100;
    private static final int FINISH_CODE_ALL=104;
    private static final int FINISH_CODE_ZB=105;
    private static final int FINISH_CODE_B=101;
    private static final int FINISH_CODE_C=102;
    private String businesstype="";//直营类型
    private String storename;//店名

    private PullToRefreshLayout mPullLayout;
    private PullableListView mListview;
    private boolean refreshState = false;
    private List<AroundshopEntry.DataBean> mBodyinfolist;
    private RequestQueue queue;
    private AroundShopAdapter mListAdapter;
    private LinearLayout mSearchallshop;


    private String shoptype[]={"1","2","3","4"};
    private String shoptypeb[]={"直营店","加盟店","合作","其他"};

    private List<AllShopTypeEntry> shoptypelist;
    private String shoptypename="";

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);

            switch (msg.what) {

                case FINISH_CODE:
                    dialog.dismiss();

                    initView();
                    initListener();

                    List<AroundshopEntry.DataBean> mbodylist = bodyinfo.getData();

                    if (refreshState) {
                        mBodyinfolist.clear();
                        refreshState = false;
                    }
                    mBodyinfolist.addAll(mbodylist);
//                    if (bodyinfo.getData().size()!=0) {
                    if (mListAdapter == null) {
                        mListAdapter = new AroundShopAdapter(AroundshopActivity.this,  mBodyinfolist,mstartlat,mstartlong,queue);
                        mListview.setAdapter(mListAdapter);
                    } else {
                        mListAdapter.notifyDataSetChanged();
                    }




                    break;
                case FINISH_CODE_ALL:
                    mShowSearchlayout.setVisibility(View.GONE);
                    mLineSearch.setVisibility(View.GONE);
                    mBodyinfolist.clear();
                    initAllData();


                    break;
                case FINISH_CODE_ZB:
                    mBodyinfolist.clear();
                    initZbData();
                    break;

                case FINISH_CODE_B:
                    mBodyinfolist.clear();
                    initAllData();
                    mTvAllTypename.setText(shoptypename);
                    mTvAllTypename.setTextColor(getApplicationContext().getResources().getColor(R.color.title_orange));
                    break;

                default:
                    break;

            };
        }
    };



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aroundshop_content);
        dialog=new LoadingDialog(AroundshopActivity.this);

        Date d=new Date();
        mSimpleTime=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        nTime=mSimpleTime.format(d);
        cityida= SharedPreferencesUtils.getCityid(AroundshopActivity.this);
        mBodyinfolist=new ArrayList<>();
        queue = Volley.newRequestQueue(getApplicationContext());

        getIntentData();
        initZbData();
        initCurrentView();

    }

    private void getIntentData(){
        Intent intent=getIntent();
        mstartlat=intent.getDoubleExtra("startlat",0);
        mstartlong=intent.getDoubleExtra("startlong",0);

    }

    //周边门店的请求
    private void initZbData(){
        JSONObject jbody=null;
        try {
            jbody = new JSONObject();
            jbody.put("longitude",mstartlong);//
            jbody.put("latitude",mstartlat);//
            jbody.put("distance",50);//范围(km)
            jbody.put("page",currentpage);
            jbody.put("pagesize",10);

            jbody.put("method","QueryNearbyStore");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
        dohttpSearchshopinfo(UrlUtils.USER,jbodyB);
    }


    //所有店铺请求
    private void initAllData(){
        JSONObject jbody=null;
        try {
            jbody = new JSONObject();
            jbody.put("city_id",cityida);
            jbody.put("business_type",businesstype);//营业类型：1、直营店 2、加盟店 3、合作 4、其它
            jbody.put("store_name",storename);//店名
            jbody.put("page",currentpage);
            jbody.put("pagesize",10);

            jbody.put("method","QueryStoreList");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
        dohttpSearchshopinfo(UrlUtils.USER,jbodyB);
    }

    private void initCurrentView(){
        mPullLayout=(PullToRefreshLayout)findViewById(R.id.refresh_view_search);//布局刷新监听
        mListview=(PullableListView)findViewById(R.id.content_view);//listview

        mTvTitlte=(TextView)findViewById(R.id.tv_title_name_change);//标题
        mTvTitlte.setText("周边门店");
        mTvTitlte.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_6));
        ImageView mImgMessage=(ImageView)findViewById(R.id.img_title_message);//更多图案显示
        mImgMessage.setVisibility(View.GONE);
        mTvshopname=(TextView)findViewById(R.id.tv_title_add_traveller);//右上角标题-全部门店
        mTvshopname.setVisibility(View.VISIBLE);
        mTvshopname.setText("全部门店");
        mBack=(LinearLayout)findViewById(R.id.iv_back);


        shoptypelist=new ArrayList<>();
        for (int i=0;i<shoptype.length;i++){
            AllShopTypeEntry shopinfo=new AllShopTypeEntry(shoptypeb[i],shoptype[i]);
            shoptypelist.add(shopinfo);

        }
    }

    private void initView(){

        mLnAllShoplayout=(LinearLayout)findViewById(R.id.ln_allshop_layout);//全部城区-布局
        mTvAllShopname=(TextView)findViewById(R.id.tv_allshop_name);//城区名字显示
        mLnAllTypelayout=(LinearLayout)findViewById(R.id.ln_allshop_type_layout);//全部分类-布局
        mTvAllTypename=(TextView)findViewById(R.id.tv_allshop_type_name);//分类名字显示

        mSearchshop=(LinearLayout)findViewById(R.id.ln_search_shop_layout);//收索布局
        mShowSearchlayout=(LinearLayout)findViewById(R.id.ln_edit_layout);//收索布局的展开
        mEtSearchContent=(EditText)findViewById(R.id.et_search_all);//搜索内容获取
        mTvDismiss=(TextView)findViewById(R.id.tv_search_shop_dismiss);//取消搜索按钮
        mLineSearch=(LinearLayout)findViewById(R.id.ln_search_line);//搜索下的线

        mSearchallshop=(LinearLayout)findViewById(R.id.ln_all_search_layout);//全部店铺显示搜索条件


    }

    private void initListener(){
        OnClickListenerImpl listener = new OnClickListenerImpl();
        mTvshopname.setOnClickListener(listener);
        mBack.setOnClickListener(listener);
        mLnAllShoplayout.setOnClickListener(listener);
        mLnAllTypelayout.setOnClickListener(listener);
        mSearchshop.setOnClickListener(listener);
        mTvDismiss.setOnClickListener(listener);





        mPullLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
                refreshState = true;
                currentpage = 1;

                if (!changeshopname){
                    initAllData();
                }else {
                    initZbData();
                }

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

                if (!changeshopname){
                    initAllData();
                }else {
                    initZbData();
                }

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
                    //全部门店点击切换周边门店
                    case R.id.tv_title_add_traveller:
                        mShowSearchlayout.setVisibility(View.GONE);
                        mLineSearch.setVisibility(View.GONE);
                        currentpage=1;
                        mBodyinfolist.clear();

                        mTvAllTypename.setText("全部分类");
                        mTvAllTypename.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_3));
                        businesstype="";
                        storename="";

                        if (changeshopname){
                            mTvTitlte.setText("全部门店");
                            mTvshopname.setText("周边门店");
                            handler.sendEmptyMessage(FINISH_CODE_ALL);
                            changeshopname=false;
                            mSearchallshop.setVisibility(View.VISIBLE);
                        }else {

                            mTvTitlte.setText("周边门店");
                            mTvshopname.setText("全部门店");
                            mSearchallshop.setVisibility(View.GONE);
                            handler.sendEmptyMessage(FINISH_CODE_ZB);
                            changeshopname=true;
                        }

                        break;
                    //全部城区点击展开条件
                    case R.id.ln_allshop_layout:
                        mShowSearchlayout.setVisibility(View.GONE);
                        mLineSearch.setVisibility(View.GONE);
                        mEtSearchContent.setText("");

                        break;
                    //全部分类点击展开
                    case R.id.ln_allshop_type_layout:
                        mShowSearchlayout.setVisibility(View.GONE);
                        mLineSearch.setVisibility(View.GONE);
                        mEtSearchContent.setText("");
                        AllShopTypeView typeview=new AllShopTypeView(AroundshopActivity.this, shoptypelist, new AllShopTypeView.CallBackUi() {
                            @Override
                            public void onRequestUi(String resulta, String chooseid) {
                                businesstype=chooseid;
                                shoptypename=resulta;
                                handler.sendEmptyMessage(FINISH_CODE_B);
                            }
                        });

                        typeview.showpop(view);

                        break;

                    //展开搜索布局
                    case R.id.ln_search_shop_layout:
                        mShowSearchlayout.setVisibility(View.VISIBLE);
                        mLineSearch.setVisibility(View.VISIBLE);

                        mEtSearchContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                            @Override
                            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                                if (mEtSearchContent.getText().toString().trim().equals("")) {
//                                    Toast.makeText(getApplicationContext(), "请输入搜索内容", Toast.LENGTH_SHORT).show();
//                                }else{

                                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                                        // 先隐藏键盘
                                        ((InputMethodManager) mEtSearchContent.getContext().getSystemService(INPUT_METHOD_SERVICE))
                                                .hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                                        storename=mEtSearchContent.getText().toString().trim();
                                        handler.sendEmptyMessage(FINISH_CODE_ALL);

                                        return true;
                                    }
//                                }

                                return false;
                            }
                        });



                        break;

                    //取消收索布局
                    case R.id.tv_search_shop_dismiss:
                        mShowSearchlayout.setVisibility(View.GONE);
                        mLineSearch.setVisibility(View.GONE);
                        mEtSearchContent.setText("");
                        break;
                    default:
                        break;

                }
        }
    }





    //搜索后获取数据
    protected  void dohttpSearchshopinfo(String url,JSONObject  paramhash){
        dialog.show();
        HttpUtils.dopost(url,AroundshopActivity.this,paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());

                bodyinfo=gson.fromJson(body, AroundshopEntry.class);

                handler.sendEmptyMessage(FINISH_CODE);

            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }



}
