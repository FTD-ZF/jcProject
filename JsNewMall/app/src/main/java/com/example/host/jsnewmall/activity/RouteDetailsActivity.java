package com.example.host.jsnewmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;



import com.example.host.jsnewmall.adapter.RouteGridAdapter;
import com.example.host.jsnewmall.adapter.RouteGridcalendarAdapter;
import com.example.host.jsnewmall.adapter.RouteListaAdapter;
import com.example.host.jsnewmall.adapter.RouteViewPagerAdapter;
import com.example.host.jsnewmall.dao.CenterHistoryDao;
import com.example.host.jsnewmall.model.CalendarEntry;
import com.example.host.jsnewmall.model.CenterHistoryEntry;
import com.example.host.jsnewmall.model.CollectionSearchEntry;
import com.example.host.jsnewmall.model.CollectionSuccessEntry;
import com.example.host.jsnewmall.model.EditNameSuccessEntry;
import com.example.host.jsnewmall.model.HomeViewPagerInfo;
import com.example.host.jsnewmall.model.JsonmModel;
import com.example.host.jsnewmall.model.LineRouteEntry;
import com.example.host.jsnewmall.model.LoginUserEntry;
import com.example.host.jsnewmall.utils.Base64Utils;
import com.example.host.jsnewmall.utils.HttpUtils;
import com.example.host.jsnewmall.utils.JsonUtils;
import com.example.host.jsnewmall.utils.SharedPreferencesUtils;
import com.example.host.jsnewmall.utils.SpStrListUtil;
import com.example.host.jsnewmall.utils.ToastUtils;
import com.example.host.jsnewmall.utils.UrlUtils;
import com.example.host.jsnewmall.view.DialogCallPhone;
import com.example.host.jsnewmall.view.HomeForthGridView;
import com.example.host.jsnewmall.view.LoadingDialog;
import com.google.gson.Gson;
import com.uu1.nmw.R;


import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by host on 2017/2/27.
 */

public class RouteDetailsActivity extends BaseActivity {
    private ImageView mImgMore,mImgCall;


    private  TextView mTvRouteDetaila,mTvRouteDetailaa;
    private TextView mTvRouteDetailb,mTvRouteDetailbb;
    private  TextView mTvRouteDetailc,mTvRouteDetailcc;
    private ImageView  mImgRoutea,mImgRouteaa;
    private ImageView  mImgRouteb,mImgRoutebb;
    private ImageView  mImgRoutec,mImgRoutecc;

    private LinearLayout mBack;
    private ViewPager mBannnerpager;


    private String[] arrTexta = new String[]{"http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg",
            "http://img02.tooopen.com/images/20140504/sy_60294738471.jpg",
            "http://pic73.nipic.com/file/20150724/18576408_175304314596_2.jpg"};
    private RequestQueue queue;
    private static final int FINISH_CODE=100;
    private static final int START_PLAY = 1;
    private static final int REFRESH_PLAY = 2;
    private static final int CHANGE_PLAY = 3;
    private static final int STOP_PLAY = 4;
    private static final int MSG_DELAY = 3000;
    private static final int FINISH_CODE_S=101;
    private static final int FINISH_CODE_C=102;

    private int currentPager = 0;

    private ListView mRouteListViewa;
    private  LinearLayout mLinearBtnGroup;
    private View headerbanner;
    private View headerbtngroup;
    private View footerView;
    private View headerdateselect;
    private TextView mTvDingzhiTravel;
    private GridView mGvDateView;
    private RouteGridAdapter mGvAdapter;
    private SimpleDateFormat mSimpleTime;
    private String nTime;
    Gson gson=new Gson();
    private LineRouteEntry bodyinfo;
    private LinearLayout dot_layout;
    private RouteViewPagerAdapter mRouteViewPagerAdapter;
    private TextView mTvtitlea;
    private TextView mTvPrice;
    private TextView mTvDate;
    private TextView mTvSubmit;
    private LinearLayout mLnDateSelectLayout;
    private LoadingDialog dialog;
    private String lineid;
    private LinearLayout mLncall,mLnShoucang;
    private boolean mCollectionable = true;
    private ImageView mImgcollection;
    private LoginUserEntry userinfo;
    private CollectionSearchEntry collectionsearchinfo;
    private String cancelid;
    private CollectionSuccessEntry  collectionSuccessinfo;
    private EditNameSuccessEntry  collectioncancelinfo;
    private int cityid;
    private CalendarEntry mCalendarinfo;
    private String mPtPrice;
    private String mPtselectDate;
    private String datateamid;
//    private SpStrListUtil dospsavelist;
    private CenterHistoryDao mDbDao;

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

                    mDbDao=new CenterHistoryDao(RouteDetailsActivity.this);
                    mDbDao.openDataBase();
//                    mDbDao.insertData("13510","图片地址","标题","价格");

                    List<CenterHistoryEntry> historylist=mDbDao.queryDataAll(lineid);
                    if ((bodyinfo.getData_base().getLines_from()).equals("3")) {
                            if (historylist==null) {

                                mDbDao.insertData(new CenterHistoryEntry(lineid, bodyinfo.getData_img().get(0).getImg_url(),
                                        bodyinfo.getData_base().getLines_name(), bodyinfo.getData_team().get(0).getLines_team_price()));
                            }
                        } else {
                        if (historylist==null) {
                            if (bodyinfo.getData_img().size()!=0) {
                                mDbDao.insertData(new CenterHistoryEntry(lineid, bodyinfo.getData_img().get(0).getImg_url(),
                                        bodyinfo.getData_base().getLines_name(), bodyinfo.getData_base().getSaleprice()));
                            }
                        }
                    }


                    mDbDao.closeDataBase();



//                    ArrayList<Map<String, String>>  msplist=new ArrayList<Map<String, String>>();
//                    Map<String, String> map=new HashMap<String, String>();
//                    if (dospsavelist.getDataList("listMap").size()==0){
//                        map.put("lineid", lineid);
//                        map.put("imgurl", bodyinfo.getData_img().get(0).getImg_url());
//                        map.put("titlename", bodyinfo.getData_base().getLines_name());
//                        if ((bodyinfo.getData_base().getLines_from()).equals("3")) {
//                            map.put("linesprice", bodyinfo.getData_team().get(0).getLines_team_price());
//                        } else {
//                            map.put("linesprice", bodyinfo.getData_base().getSaleprice());
//                        }
//
//                        msplist.add(map);
//                        dospsavelist.setDataList("listMap",msplist);
////                        dospsavelist.saveInfo(getApplicationContext(), "routelist", msplist);
//                    }else {
//
//                        for (int i = 0; i < dospsavelist.getDataList("routelist").size(); i++) {
//                            String strlineid = dospsavelist.getInfo(getApplicationContext(), "routelist").get(i).get("lineid");
//                            if (!strlineid.equals(lineid)) {
//                                map.put("lineid", lineid);
//                                map.put("imgurl", bodyinfo.getData_img().get(0).getImg_url());
//                                map.put("titlename", bodyinfo.getData_base().getLines_name());
//                                if ((bodyinfo.getData_base().getLines_from()).equals("3")) {
//                                    map.put("linesprice", bodyinfo.getData_team().get(0).getLines_team_price());
//                                } else {
//                                    map.put("linesprice", bodyinfo.getData_base().getSaleprice());
//                                }
//
//                                msplist.add(map);
//
//
////                                dospsavelist.saveInfo(getApplicationContext(), "routelist", msplist);
//                            }
//                        }
//                        dospsavelist.setDataList("listMap",msplist);
//                    }




                    if (mRouteViewPagerAdapter == null) {
                        mRouteViewPagerAdapter = new RouteViewPagerAdapter(getApplicationContext(),queue,bodyinfo);
                        mBannnerpager.setAdapter(mRouteViewPagerAdapter);
                    } else {
                        mRouteViewPagerAdapter.notifyDataSetChanged();
                    }

                    if (bodyinfo.getData_img().size() != 1) {
                        handler.sendEmptyMessageDelayed(START_PLAY, MSG_DELAY);
                        setDot(bodyinfo.getData_img().size());
                    }
                    break;
                case REFRESH_PLAY:
                    currentPager++;
                    mBannnerpager.setCurrentItem(currentPager);
                    handler.sendEmptyMessageDelayed(REFRESH_PLAY, MSG_DELAY);
                    break;
                case START_PLAY:
                    handler.sendEmptyMessageDelayed(REFRESH_PLAY, MSG_DELAY);
                    break;
                case CHANGE_PLAY:
                    currentPager = msg.arg1;

                    break;

                case FINISH_CODE_S:
                    dialog.dismiss();
                    if (collectionSuccessinfo.getRes()==1){
                        ToastUtils.show(RouteDetailsActivity.this,"收藏成功");
                        cancelid=collectionSuccessinfo.getFav_id();
                    }else {
                        ToastUtils.show(RouteDetailsActivity.this,collectionSuccessinfo.getMsg());
                    }
                    break;
                case FINISH_CODE_C:
                    dialog.dismiss();
                    if (collectioncancelinfo.getRes()==1){
                        ToastUtils.show(RouteDetailsActivity.this,"收藏取消");
                    }else {
                        ToastUtils.show(RouteDetailsActivity.this,collectioncancelinfo.getMsg());
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
        setContentView(R.layout.activity_route_details_content);
        dialog=new LoadingDialog(RouteDetailsActivity.this);
        Date d=new Date();
        mSimpleTime=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        nTime=mSimpleTime.format(d);
        userinfo=gson.fromJson(SharedPreferencesUtils.getUserInfo(RouteDetailsActivity.this),LoginUserEntry.class);
        cityid= SharedPreferencesUtils.getCityid(RouteDetailsActivity.this);
//        dospsavelist=new SpStrListUtil(getApplicationContext(),"hisarray");

        getIntentData();
        initCalendarData();
        initData();
        if (userinfo!=null) {
            initCollectData();
        }

        mImgMore=(ImageView)findViewById(R.id.img_title_message);
        mImgCall=(ImageView) findViewById(R.id.img_title_call);
        mImgCall.setVisibility(View.GONE);
        mImgMore.setBackgroundResource(R.mipmap.icon_search_more);
        TextView mTvtitle=(TextView) findViewById(R.id.tv_title_name_change);
        mTvtitle.setText(getApplicationContext().getResources().getString(R.string.route_details_b));


    }

    private void getIntentData(){
        Intent intent=getIntent();
        lineid=intent.getStringExtra("lineid");
    }

    private void initData(){

        JSONObject jbody=null;
        try {
            jbody = new JSONObject();
//            jbody.put("lines_id",13510);
            jbody.put("lines_id",lineid);
            jbody.put("method","GetLines");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
        doRouteline(UrlUtils.ROUTE_LINE,jbodyB);

    }


    private void initCollectData(){



        JSONObject jbodycc=null;
        try {
            jbodycc = new JSONObject();

            jbodycc.put("data_id",lineid);
            jbodycc.put("data_type",1);//数据类型:1.线路 2.邮轮 3.门票
            jbodycc.put("user_id",userinfo.getUserid());
            jbodycc.put("page",1);
            jbodycc.put("method","FavList");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyBcc= JsonUtils.JsonParseInfo(nTime,jbodycc);
        dohttpsearchcollect(UrlUtils.CONTENT,jbodyBcc);
    }

    private void initCalendarData(){
        JSONObject jbodycla=null;
        try {
            jbodycla = new JSONObject();

            jbodycla.put("city_id",cityid);
            jbodycla.put("lines_id",lineid);
            jbodycla.put("method","GetCalendar");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyBcla= JsonUtils.JsonParseInfo(nTime,jbodycla);
        dohttpdate(UrlUtils.ROUTE_LINE,jbodyBcla);
    }

    private void initView(){
        queue = Volley.newRequestQueue(getApplicationContext());
//        bannerInfoList = new ArrayList<>();
//        for (int i=0; i<3; i++){
//            HomeViewPagerInfo pt = new HomeViewPagerInfo(arrTexta[i]);
//            bannerInfoList.add(pt);
//        }

        mBack=(LinearLayout)findViewById(R.id.iv_back);//返回键



        mRouteListViewa=(ListView) findViewById(R.id.lv_route_a);//第一个listview
        mLinearBtnGroup=(LinearLayout) findViewById(R.id.ln_route_a);//影藏的按钮组(3个)

        headerbanner= LayoutInflater.from(RouteDetailsActivity.this).inflate(R.layout.activity_route_details_first_view, null);//添加轮播图为头部
        headerbtngroup= LayoutInflater.from(RouteDetailsActivity.this).inflate(R.layout.activity_route_details_second_viewpager_title, null);
        headerdateselect= LayoutInflater.from(RouteDetailsActivity.this).inflate(R.layout.headerview_route_gridview_selectdate, null);//团期选择


        footerView=LayoutInflater.from(RouteDetailsActivity.this).inflate(R.layout.footerview_route_first_listview, null);
        mTvDingzhiTravel=(TextView)footerView.findViewById(R.id.route_dingzhi);//跳转定制游
        mTvSubmit=(TextView)findViewById(R.id.tv_change_first_content_c);//我要预订
        mTvSubmit.setText("我要预订");

        mGvDateView=(GridView) headerdateselect.findViewById(R.id.gv_route_date_select);//gridview展示的4个选择框


        //平台线路与内网线路的团期显示不同 平台data_team 内网日历数据
        if ((bodyinfo.getData_base().getLines_from()).equals("3")) {
            mGvAdapter = new RouteGridAdapter(getApplicationContext(),bodyinfo.getData_team());
            mGvDateView.setAdapter(mGvAdapter);
        }else {
            RouteGridcalendarAdapter mGvAdaptera = new RouteGridcalendarAdapter(getApplicationContext(),mCalendarinfo.getData());
            mGvDateView.setAdapter(mGvAdaptera);
        }

        mLnDateSelectLayout=(LinearLayout)headerdateselect.findViewById(R.id.route_tuanqi_select);//团期选择整个布局
//        if((bodyinfo.getData_base().getLines_from()).equals("3")){
//            mLnDateSelectLayout.setVisibility(View.GONE);
//        }else {
//            mLnDateSelectLayout.setVisibility(View.VISIBLE);
//        }


        mRouteListViewa.addFooterView(footerView);//添加底部布局(定制点击)

        mRouteListViewa.addHeaderView(headerbanner);
        //平台线路就显示团期选择共4个

        mRouteListViewa.addHeaderView(headerdateselect);

        mRouteListViewa.addHeaderView(headerbtngroup);


        RouteListaAdapter adaptera=new RouteListaAdapter(getApplicationContext(),queue,bodyinfo);//第一个listview数据填充
        mRouteListViewa.setAdapter(adaptera);


        mRouteListViewa.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem >= 2) {
                    mLinearBtnGroup.setVisibility(View.VISIBLE);
                } else {

                    mLinearBtnGroup.setVisibility(View.GONE);
                }
            }
        });


         mBannnerpager=(ViewPager)headerbanner.findViewById(R.id.route_details_viewpager);//轮播图
         dot_layout = (LinearLayout) headerbanner.findViewById(R.id.ll_dot);//轮播点布局
         mTvtitlea =(TextView)headerbanner.findViewById(R.id.tv_route_details_title_a);//第一个标题
         mTvPrice=(TextView)headerbanner.findViewById(R.id.tv_route_content_c);//轮播图下价格显示
         mTvDate=(TextView)headerbanner.findViewById(R.id.tv_route_content_b);//轮播图下日期显示

         mTvRouteDetaila=(TextView) headerbtngroup.findViewById(R.id.tv_routedetail_a);//tab1
         mTvRouteDetailb=(TextView) headerbtngroup.findViewById(R.id.tv_routedetail_b);
         mTvRouteDetailc=(TextView) headerbtngroup.findViewById(R.id.tv_routedetail_c);
         mImgRoutea=(ImageView)headerbtngroup.findViewById(R.id.img_routedetail_a);//tab1的下滑线
         mImgRouteb=(ImageView)headerbtngroup.findViewById(R.id.img_routedetail_b);
         mImgRoutec=(ImageView)headerbtngroup.findViewById(R.id.img_routedetail_c);

        mTvRouteDetailaa=(TextView) mLinearBtnGroup.findViewById(R.id.tv_routedetail_a);//tab1
        mTvRouteDetailbb=(TextView) mLinearBtnGroup.findViewById(R.id.tv_routedetail_b);
        mTvRouteDetailcc=(TextView) mLinearBtnGroup.findViewById(R.id.tv_routedetail_c);
        mImgRouteaa=(ImageView)mLinearBtnGroup.findViewById(R.id.img_routedetail_a);//tab1的下滑线
        mImgRoutebb=(ImageView)mLinearBtnGroup.findViewById(R.id.img_routedetail_b);
        mImgRoutecc=(ImageView)mLinearBtnGroup.findViewById(R.id.img_routedetail_c);


        mTvtitlea.setText(bodyinfo.getData_base().getLines_name());
        //平台线路与内网线路取得金额不一样
        if((bodyinfo.getData_base().getLines_from()).equals("3")) {
            mTvPrice.setText(bodyinfo.getData_team().get(0).getLines_team_price());
            mPtPrice=bodyinfo.getData_team().get(0).getLines_team_price();

            String calendardate=bodyinfo.getData_team().get(0).getLines_team_date();
            String spStr[] = calendardate.split(" ");
            String dataA=spStr[0];
            mPtselectDate=dataA;

            datateamid=bodyinfo.getData_team().get(0).getId();

        }else {
            mTvPrice.setText(bodyinfo.getData_base().getSaleprice());
        }
        mTvDate.setText(bodyinfo.getData_base().getLines_days()+"天"+bodyinfo.getData_base().getLines_daysnight()+"晚");



        mLncall=(LinearLayout)findViewById(R.id.ln_routedetails_call);//打电话
        mLnShoucang=(LinearLayout)findViewById(R.id.ln_routedetails_shoucang);//收藏

        mImgcollection=(ImageView)findViewById(R.id.img_change_first_b);//收藏图片

        if (userinfo!=null) {
            if (collectionsearchinfo.getData().size() != 0) {
                mImgcollection.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.icon_collection_d));
                mCollectionable = false;
            } else {
                mImgcollection.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.icon_collection_c));
                mCollectionable = true;
            }
        }


    }
    private void initListener(){

        OnClickListenerImpl listener = new OnClickListenerImpl();
        mBack.setOnClickListener(listener);
        mTvDingzhiTravel.setOnClickListener(listener);
        mTvRouteDetaila.setOnClickListener(listener);
        mTvRouteDetailb.setOnClickListener(listener);
        mTvRouteDetailc.setOnClickListener(listener);
        mTvRouteDetailaa.setOnClickListener(listener);
        mTvRouteDetailbb.setOnClickListener(listener);
        mTvRouteDetailcc.setOnClickListener(listener);
        mTvSubmit.setOnClickListener(listener);
        mLnDateSelectLayout.setOnClickListener(listener);
        mLncall.setOnClickListener(listener);
        mLnShoucang.setOnClickListener(listener);

            //团期单个选择
            mGvDateView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if((bodyinfo.getData_base().getLines_from()).equals("3")) {
                        mGvAdapter.changeSelected(position);
                        mTvPrice.setText(bodyinfo.getData_team().get(position).getLines_team_price());

                        mPtPrice=bodyinfo.getData_team().get(position).getLines_team_price();
                        String calendardate=bodyinfo.getData_team().get(position).getLines_team_date();
                        String spStr[] = calendardate.split(" ");
                        String dataA=spStr[0];
                        mPtselectDate=dataA;
                        datateamid=bodyinfo.getData_team().get(position).getId();


                    }else{
                        Intent intentcalendara=new Intent(getApplicationContext(),SelectDateActivity.class);
                        intentcalendara.putExtra("routetitle",bodyinfo.getData_base().getLines_name());
                        intentcalendara.putExtra("linesfrom",bodyinfo.getData_base().getLines_from());
                        intentcalendara.putExtra("lineid",lineid);
                        startActivity(intentcalendara);
                    }
                }
            });

        if (bodyinfo.getData_img().size()!=0) {
            mBannnerpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {

                    handler.sendMessage(Message.obtain(handler, CHANGE_PLAY,
                            position, 0));

                    currentPager = position % bodyinfo.getData_img().size();
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
    }

    /**
     * 设置viewpager下面的白点
     */

    private void setDot(int size) {
        for (int i = 0; i < size; i++) {
            View view = new View(getApplicationContext());
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

    private class OnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.iv_back:
                    finish();
                    break;
                //定制游跳转
                case R.id.route_dingzhi:
                    Intent intentdingz=new Intent(RouteDetailsActivity.this,DingzActivity.class);
                    startActivity(intentdingz);

                    break;

                case R.id.tv_routedetail_a:
                    mRouteListViewa.setSelection(2);

//                    setTextaBgColor(mTvRouteDetaila,mImgRoutea);
//                    setTextbBgColor(mTvRouteDetailb,mImgRouteb);
//                    setTextbBgColor(mTvRouteDetailc,mImgRoutec);

                    setTextaBgColor(mTvRouteDetailaa,mImgRouteaa);
                    setTextbBgColor(mTvRouteDetailbb,mImgRoutebb);
                    setTextbBgColor(mTvRouteDetailcc,mImgRoutecc);


                    break;
                case R.id.tv_routedetail_b:
                    mRouteListViewa.setSelection(4);

//                    setTextaBgColor(mTvRouteDetailb,mImgRouteb);
//                    setTextbBgColor(mTvRouteDetaila,mImgRoutea);
//                    setTextbBgColor(mTvRouteDetailc,mImgRoutec);


                    setTextaBgColor(mTvRouteDetailbb,mImgRoutebb);
                    setTextbBgColor(mTvRouteDetailaa,mImgRouteaa);
                    setTextbBgColor(mTvRouteDetailcc,mImgRoutecc);

                    break;


                case R.id.tv_routedetail_c:
                    mRouteListViewa.setSelection(6);
//                    setTextaBgColor(mTvRouteDetailc,mImgRoutec);
//                    setTextbBgColor(mTvRouteDetaila,mImgRoutea);
//                    setTextbBgColor(mTvRouteDetailb,mImgRouteb);

                    setTextaBgColor(mTvRouteDetailcc,mImgRoutecc);
                    setTextbBgColor(mTvRouteDetailaa,mImgRouteaa);
                    setTextbBgColor(mTvRouteDetailbb,mImgRoutebb);

                    break;
                /**
                 *  3  //平台线路  没有日历控件   填写订单页面不需要人数
                 *  1和2 //内网线路   有日历控件 填写订单页面需要人数
                 */
                case R.id.tv_change_first_content_c:

                    if((bodyinfo.getData_base().getLines_from()).equals("3")){
//                        ToastUtils.show(getApplicationContext(),"3");
                        Intent intentpt=new Intent(RouteDetailsActivity.this,WriteOrderActivity.class);
                        intentpt.putExtra("lineid",lineid);
                        intentpt.putExtra("allprice",Double.parseDouble(mPtPrice));
                        intentpt.putExtra("selectdate",mPtselectDate);
                        intentpt.putExtra("titleroutename",bodyinfo.getData_base().getLines_name());
                        intentpt.putExtra("linesfrom",bodyinfo.getData_base().getLines_from());
                        intentpt.putExtra("datateamid",datateamid);//平台线路id

                        startActivity(intentpt);

                    }else{
//                        ToastUtils.show(getApplicationContext(),bodyinfo.getData_base().getLines_from());
                        Intent intent=new Intent(getApplicationContext(),SelectDateActivity.class);
                        intent.putExtra("routetitle",bodyinfo.getData_base().getLines_name());
                        intent.putExtra("linesfrom",bodyinfo.getData_base().getLines_from());
                        intent.putExtra("lineid",lineid);
                        startActivity(intent);
                    }
                    break;
                //选择团期跳转日历
                case R.id.route_tuanqi_select:
                    Intent intentcalendar=new Intent(getApplicationContext(),SelectDateActivity.class);
                    intentcalendar.putExtra("routetitle",bodyinfo.getData_base().getLines_name());
                    intentcalendar.putExtra("linesfrom",bodyinfo.getData_base().getLines_from());
                    startActivity(intentcalendar);

                break;

                //打电话
                case R.id.ln_routedetails_call:
                    DialogCallPhone.showdialog(RouteDetailsActivity.this);

                    break;
                //收藏
                case R.id.ln_routedetails_shoucang:
                    if(userinfo==null){
                        ToastUtils.show(RouteDetailsActivity.this,"请先登录");
                        return;
                    }


                if (mCollectionable){
                    mImgcollection.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.icon_collection_d));

                    JSONObject jbody=null;
                    try {
                        jbody = new JSONObject();

                        jbody.put("data_id",lineid);
                        jbody.put("user_id",userinfo.getUserid());
                        jbody.put("data_type",1);
                        jbody.put("method","FavAdd");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
                    dohttpCollect(UrlUtils.CONTENT,jbodyB);
                    mCollectionable=false;
                }else {

                    mImgcollection.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.icon_collection_c));
                    JSONObject jbody=null;
                    try {
                        jbody = new JSONObject();

                        jbody.put("id",cancelid);
                        jbody.put("method","FavCancel");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
                    dohttpCollectcancel(UrlUtils.CONTENT,jbodyB);


                    mCollectionable=true;
                }
                    break;

                default:
                    break;

            }
        }
    }


    //线路详情
    protected  void doRouteline(String url,JSONObject  paramhash){
        dialog.show();
        HttpUtils.dopost(url,getApplicationContext(),paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());

                bodyinfo=gson.fromJson(body, LineRouteEntry.class);

                handler.sendEmptyMessage(FINISH_CODE);

            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }

    protected  void dohttpdate(String url,JSONObject  paramhash){
        HttpUtils.dopost(url,getApplicationContext(),paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());

                mCalendarinfo=gson.fromJson(body, CalendarEntry.class);



            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }

    //收藏
    protected  void dohttpCollect(String url,JSONObject  paramhash){
        dialog.show();
        HttpUtils.dopost(url,getApplicationContext(),paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());

                collectionSuccessinfo=gson.fromJson(body, CollectionSuccessEntry.class);
                handler.sendEmptyMessage(FINISH_CODE_S);


            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }


    //收藏取消
    protected  void dohttpCollectcancel(String url,JSONObject  paramhash){
        dialog.show();
        HttpUtils.dopost(url,getApplicationContext(),paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());

                collectioncancelinfo=gson.fromJson(body,EditNameSuccessEntry.class);
                handler.sendEmptyMessage(FINISH_CODE_C);

            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }


    //收藏数据查询
    protected  void dohttpsearchcollect(String url,JSONObject  paramhash){
        dialog.show();
        HttpUtils.dopost(url,getApplicationContext(),paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());

                collectionsearchinfo=gson.fromJson(body, CollectionSearchEntry.class);
                if (collectionsearchinfo.getData().size()!=0) {
                    cancelid = collectionsearchinfo.getData().get(0).getId();//获取当前线路收藏id
                }else{
                    cancelid="0";
                }
//                if (collectionSuccessinfo.getRes()==1){
//                    ToastUtils.show(RouteDetailsActivity.this,collectionSuccessinfo.getMsg());
//                }else {
//                    ToastUtils.show(RouteDetailsActivity.this,collectionSuccessinfo.getMsg());
//                }


            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }




    private void setTextaBgColor(TextView mTva,ImageView mImga){

        mTva.setTextColor(getApplicationContext().getResources().getColor(R.color.title_orange));
        mImga.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.title_orange));
    }
    private void setTextbBgColor(TextView mTvb,ImageView mImgb){
        mTvb.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_3));
        mImgb.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.travel_title_bar));
    }

}
