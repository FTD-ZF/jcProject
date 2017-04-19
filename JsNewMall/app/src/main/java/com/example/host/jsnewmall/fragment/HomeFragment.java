package com.example.host.jsnewmall.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import com.example.host.jsnewmall.activity.BaoCheActivity;
import com.example.host.jsnewmall.activity.DingzActivity;
import com.example.host.jsnewmall.activity.FreeTravelActivity;
import com.example.host.jsnewmall.activity.HotelActivity;
import com.example.host.jsnewmall.activity.MessageActivity;

import com.example.host.jsnewmall.activity.QianZhengActivity;
import com.example.host.jsnewmall.activity.RouteDetailsActivity;
import com.example.host.jsnewmall.activity.SearchActivity;
import com.example.host.jsnewmall.activity.ShipActivity;
import com.example.host.jsnewmall.activity.TicketActivity;
import com.example.host.jsnewmall.activity.TravelActivity;
import com.example.host.jsnewmall.activity.WriteOrderActivity;
import com.example.host.jsnewmall.adapter.HomeFifthGridAdapter;
import com.example.host.jsnewmall.adapter.HomeForthGridAdapter;
import com.example.host.jsnewmall.adapter.HomeSixthListAdapter;
import com.example.host.jsnewmall.adapter.HomeThirdGridAdapter;
import com.example.host.jsnewmall.adapter.HomeViewPagerAdpter;
import com.example.host.jsnewmall.bean.IRefreshInterface;
import com.example.host.jsnewmall.model.HomeMainEntry;
import com.example.host.jsnewmall.model.HomeViewPagerInfo;
import com.example.host.jsnewmall.model.JsonmModel;
import com.example.host.jsnewmall.model.ResultcInfo;
import com.example.host.jsnewmall.utils.Base64Utils;
import com.example.host.jsnewmall.utils.DoubleClickExitHelper;
import com.example.host.jsnewmall.utils.HttpUtils;
import com.example.host.jsnewmall.utils.JsonUtils;
import com.example.host.jsnewmall.utils.SharedPreferencesUtils;
import com.example.host.jsnewmall.utils.ToastUtils;
import com.example.host.jsnewmall.utils.UrlUtils;
import com.example.host.jsnewmall.view.HomeAddressPopView;
import com.example.host.jsnewmall.view.HomeForthGridView;
import com.example.host.jsnewmall.view.LoadingDialog;
import com.example.host.jsnewmall.view.RefreshScrollView.PullToRefreshLayout;
import com.example.host.jsnewmall.view.RefreshScrollView.Pullable;
import com.example.host.jsnewmall.view.RefreshScrollView.PullableScrollView;
import com.google.gson.Gson;
import com.uu1.nmw.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by host on 2017/2/15.
 */

public class HomeFragment extends BaseFragment {

    private View view;
    private Activity mActivity;
    private ViewPager pager;
    private LinearLayout dot_layout;
    private static final int FINISH_CODE=100;
    private static final int FINISH_CODE_A=101;
    private static final int START_PLAY = 1;
    private static final int REFRESH_PLAY = 2;
    private static final int CHANGE_PLAY = 3;
    private static final int STOP_PLAY = 4;
    private static final int MSG_DELAY = 3000;
    private static final int ERROE_CODE=104;
    private int currentPager = 0;

    private HomeViewPagerAdpter homeViewPagerAdpter;
    private RequestQueue queue;
//    private List<HomeViewPagerInfo> bannerInfoList;
    private String[] arrTexta = new String[]{"http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg",
            "http://img02.tooopen.com/images/20140504/sy_60294738471.jpg",
            "http://pic73.nipic.com/file/20150724/18576408_175304314596_2.jpg"};

    private LinearLayout mLnTravela;
    private PullableScrollView mHomeScrollView;

    private LinearLayout mLnTravelg;
    private LinearLayout mLnTravele;
    private LinearLayout mLnSearch;
    private GridView mGvThird;
    private TextView mTvAddress;
    private LinearLayout mLnChange;
    private String citys[] = {"常州", "江阴", "无锡", "南京", "上海"};
    private int cityid[]= {115,1204,113,112,10};
    private List<ResultcInfo> dataListc;
    private String addressdata;
    private int finalcityid=115;
    private PullToRefreshLayout mPullscroll;
    private RelativeLayout mTitleBackColor;
    private int mHeight;
    private LinearLayout secondlayout;
    private ImageView mImgCity;
    private ImageView mImgCall;
    private ImageView mImgMessage;
    private LinearLayout mLnHomeBgColor;
    private SimpleDateFormat mSimpleTime;
    private String nTime;
    Gson gson=new Gson();
    private  HomeMainEntry bodyinfo;
    private LoadingDialog dialog;
    private boolean dotboolean=true;
    private IRefreshInterface changecity;
    private LinearLayout mLnTravelb,mLnTravelc,mLnTraveld,mLnTravelf,mLnTravelh;

    private GridView mGvForth;
    private GridView mGvFifth;
    private ListView mListView;

    /**
     * {@"常州":@"115",
     *  @"江阴":@"1204",
     * @"无锡":@"113",
     * @"南京":@"112",
     * @"上海":@"10"}
     */

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

//                    ToastUtils.show(getActivity(), bodyinfo.getList().getHot_rec().get(2).getTitle()+"");
                    if (homeViewPagerAdpter == null) {
                        homeViewPagerAdpter = new HomeViewPagerAdpter(mActivity,queue,bodyinfo);

                        pager.setAdapter(homeViewPagerAdpter);

                    } else {
                        homeViewPagerAdpter.notifyDataSetChanged();

                    }
                    handler.sendEmptyMessageDelayed(START_PLAY, MSG_DELAY);
                    if (dotboolean) {
                        if (bodyinfo.getList().getBanner().size() != 1) {
                            setDot(bodyinfo.getList().getBanner().size());
                        }
                        dotboolean=false;
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
                case FINISH_CODE_A:
                    setTextViewBackColor(mTvAddress,addressdata);
                    initData(finalcityid);


                    break;
                case ERROE_CODE:
                    dialog.dismiss();
//                    ToastUtils.show(getActivity(),"暂无正式数据");
                    break;

                default:
                    break;

        };
        }
    };




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home_main_content,null);
        Date d=new Date();
        mSimpleTime=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        nTime=mSimpleTime.format(d);
        dialog=new LoadingDialog(getActivity());
        dialog.show();


        int shareid=SharedPreferencesUtils.getCityid(getActivity());
        String cityname=SharedPreferencesUtils.getCity(getActivity());
        mTvAddress=(TextView) view.findViewById(R.id.tv_title_address_change);//地址选择变化
        //通过缓存城市id名字
        if (cityname!=""){
            finalcityid=shareid;
            addressdata=cityname;
            mTvAddress.setText(cityname);
            initData(finalcityid);


        }else {
            initData(finalcityid);
            mTvAddress.setText("常州");
        }

        return view;
    }

    private void initData(int cityidid){

        JSONObject jbody=null;
        try {
            jbody = new JSONObject();
            jbody.put("city_id",cityidid);
            jbody.put("topic_name", "index");
            jbody.put("method","Get_ad_byname");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
        dohttpHome(UrlUtils.APP_URL,jbodyB);


    }
    private void  initView(){
        mActivity=getActivity();
        queue = Volley.newRequestQueue(getActivity());
//        bannerInfoList = new ArrayList<>();
//        for (int i=0; i<3; i++){
//            HomeViewPagerInfo pt = new HomeViewPagerInfo(arrTexta[i]);
//            bannerInfoList.add(pt);
//        }

        dataListc=new ArrayList<>();
        for (int i=0; i<citys.length; i++){
            ResultcInfo ptc = new ResultcInfo(citys[i],cityid[i]);
            dataListc.add(ptc);
        }



        mHomeScrollView=(PullableScrollView) view.findViewById(R.id.home_scrollview);//scrollview布局
        mPullscroll=(PullToRefreshLayout)view.findViewById(R.id.refresh_view);//scrollview外层布局用来刷新

        mTitleBackColor=(RelativeLayout)view.findViewById(R.id.home_title_bg_change);//标题背景设置





        pager=(ViewPager) view.findViewById(R.id.viewpager_home);//轮播图

        dot_layout = (LinearLayout) view.findViewById(R.id.ll_dot);

        mLnTravela=(LinearLayout)view.findViewById(R.id.ll_item_layout_a);//去哪玩改为出境游
        mLnTravelb=(LinearLayout)view.findViewById(R.id.ll_item_layout_b);//酒店
        mLnTravelc=(LinearLayout)view.findViewById(R.id.ll_item_layout_c);//国内游
        mLnTraveld=(LinearLayout)view.findViewById(R.id.ll_item_layout_d);//周末游
        mLnTravele=(LinearLayout)view.findViewById(R.id.ll_item_layout_e);//景区
        mLnTravelf=(LinearLayout)view.findViewById(R.id.ll_item_layout_f);//巴士游
        mLnTravelg=(LinearLayout)view.findViewById(R.id.ll_item_layout_g);//自由行
        mLnTravelh=(LinearLayout)view.findViewById(R.id.ll_item_layout_h);//定制游

        mLnSearch=(LinearLayout) view.findViewById(R.id.tv_search_address);//搜索框点击
        mLnChange=(LinearLayout) view.findViewById(R.id.tv_home_title_address);//地址选择点击

        mImgCity=(ImageView)view.findViewById(R.id.img_title_xia_change);//箭头
        mImgCall=(ImageView)view.findViewById(R.id.img_title_call);//电话图片
        mImgMessage=(ImageView)view.findViewById(R.id.img_title_message);//信息图片
        mLnHomeBgColor=(LinearLayout)view.findViewById(R.id.home_main_linearlayout);


        secondlayout=(LinearLayout)view.findViewById(R.id.home_second_view_height);//第二个布局高度





        mGvThird= (GridView)view.findViewById(R.id.gv_home_third_content);//文本标签
        HomeThirdGridAdapter mThirdAdapter=new HomeThirdGridAdapter(mActivity,bodyinfo,queue);
        mGvThird.setAdapter(mThirdAdapter);

        mGvForth= (GridView) view.findViewById(R.id.gv_home_forth_content);//热门推荐
        HomeForthGridAdapter mForthAdapter=new HomeForthGridAdapter(mActivity,bodyinfo,queue);
        mGvForth.setAdapter(mForthAdapter);
        HomeForthGridView.setListViewHeightBasedOnChildren(mGvForth);
        mGvForth.deferNotifyDataSetChanged();

        mGvFifth=(GridView)view.findViewById(R.id.gv_home_fifth_content);//精品路线推荐
        DisplayMetrics dm = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        HomeForthGridView.setHorizontalScroll(mGvFifth,density,bodyinfo.getList().getRecommend().size(),150);
        HomeFifthGridAdapter mFifthAdapter=new HomeFifthGridAdapter(mActivity,bodyinfo,queue);
        mGvFifth.setAdapter(mFifthAdapter);
        mGvFifth.deferNotifyDataSetChanged();



        int heightwindow = dm.heightPixels;   // 屏幕高度（像素）
        mHeight=heightwindow/3;


        mListView= (ListView) view.findViewById(R.id.listview_home_content);//listview 的列表
        HomeSixthListAdapter mSixthAdapter=new HomeSixthListAdapter(mActivity,queue,bodyinfo);
        mListView.setAdapter(mSixthAdapter);
        HomeForthGridView.setListViewHeight(mListView);
        mListView.deferNotifyDataSetChanged();



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
        OnClickListenerImpl listener = new OnClickListenerImpl();

        mLnSearch.setOnClickListener(listener);
        mLnChange.setOnClickListener(listener);
        mImgMessage.setOnClickListener(listener);

        mLnTravela.setOnClickListener(listener);
        mLnTravelb.setOnClickListener(listener);
        mLnTravelc.setOnClickListener(listener);
        mLnTraveld.setOnClickListener(listener);
        mLnTravele.setOnClickListener(listener);
        mLnTravelf.setOnClickListener(listener);
        mLnTravelg.setOnClickListener(listener);
        mLnTravelh.setOnClickListener(listener);

        mGvThird.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                //包车跳转
                if (position==0){
                    Intent intentbaoche=new Intent(getActivity(), BaoCheActivity.class);
                    startActivity(intentbaoche);
                }
                //签证
                if (position==1){
                    Intent intentqianz=new Intent(getActivity(), QianZhengActivity.class);
                    startActivity(intentqianz);
                }
                //亲子
                if(position==2){
                    Intent intentfamliy=new Intent(getActivity(), FreeTravelActivity.class);

                    intentfamliy.putExtra("topicname","qz");
                    intentfamliy.putExtra("titlename",bodyinfo.getList().getTop_nav().get(2).getTitle());

                    startActivity(intentfamliy);
                }
                //海岛
                if (position==3){
                    Intent intenthd=new Intent(getActivity(), FreeTravelActivity.class);

                    intenthd.putExtra("topicname","hd");
                    intenthd.putExtra("titlename",bodyinfo.getList().getTop_nav().get(3).getTitle());

                    startActivity(intenthd);
                }
                //游轮
                if (position==4){
                    Intent intentd=new Intent(mActivity, ShipActivity.class);
                    startActivity(intentd);
                }

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

        //监听scrollview滑动标题变化
        mHomeScrollView.setScrollViewListener(new PullableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(View scrollView, int x, int y, int oldx, int oldy) {

                if (y <= 0) {   //设置标题的背景颜色
                    mTitleBackColor.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
                    mTvAddress.setTextColor(getActivity().getResources().getColor(R.color.title_orange));
                    mImgCity.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.icon_jiantou_xia));
//                    mImgCall.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.icon_title_call));
                    mImgMessage.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.icon_title_message));


                } else if (y > 0 && y <=  mHeight) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
                    float scale = (float) y / mHeight;
                    float alpha = (255 * scale);
                    mTitleBackColor.setBackgroundColor((Color.argb((int) alpha,253,119,8)));

                } else {    //滑动到banner下面设置普通颜色
                    mTitleBackColor.setBackgroundColor(getActivity().getResources().getColor(R.color.title_orange));
                    mTvAddress.setTextColor(getActivity().getResources().getColor(R.color.white));
                    mImgCity.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.icon_xiajiantou_white));
//                    mImgCall.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.icon_title_call_b));
                    mImgMessage.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.icon_title_message_b));

                }
            }
        });



        //热门推荐点击item
        mGvForth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //线路详情
                if (bodyinfo.getList().getHot_rec().get(position).getLine_data()!=null) {
                    Intent intentg = new Intent(mActivity, RouteDetailsActivity.class);
                    intentg.putExtra("lineid", bodyinfo.getList().getHot_rec().get(position).getLine_data().getId());
                    startActivity(intentg);
                }
            }
        });

        //精品线路点击item
        mGvFifth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //线路详情
                if (bodyinfo.getList().getRecommend().get(i).getLine_data()!=null) {
                    Intent intentfifth = new Intent(mActivity, RouteDetailsActivity.class);
                    intentfifth.putExtra("lineid", bodyinfo.getList().getRecommend().get(i).getLine_data().getId());
                    startActivity(intentfifth);
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



    }


    private class OnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch(view.getId()){
                //去哪儿改为出境游
                case R.id.ll_item_layout_a:
                    Intent intentchujing=new Intent(getActivity(), TravelActivity.class);
                    intentchujing.putExtra("currentindex",2);
                    startActivity(intentchujing);
                    break;
                //酒店
                case R.id.ll_item_layout_b:
                    Intent intentb=new Intent(mActivity, HotelActivity.class);
                    startActivity(intentb);
                    break;
                //国内游
                case R.id.ll_item_layout_c:

                    Intent intentguonei=new Intent(getActivity(), TravelActivity.class);
                    intentguonei.putExtra("currentindex",1);
                    startActivity(intentguonei);
                    break;
                //周末游
                case R.id.ll_item_layout_d:

                    Intent intentweek=new Intent(getActivity(), TravelActivity.class);
                    intentweek.putExtra("currentindex",3);
                    startActivity(intentweek);
                    break;
                //景区
                case R.id.ll_item_layout_e:
                    Intent intente=new Intent(mActivity, TicketActivity.class);
                    startActivity(intente);
                    break;
                //巴士游
                case R.id.ll_item_layout_f:
                    Intent intentf=new Intent(getActivity(), TravelActivity.class);
                    intentf.putExtra("currentindex",0);
                    startActivity(intentf);
                    break;
                //自由行
                case R.id.ll_item_layout_g:
                    Intent intentg=new Intent(getActivity(), FreeTravelActivity.class);

                    intentg.putExtra("topicname","zy");
                    intentg.putExtra("titlename","自由行");

                    startActivity(intentg);

                    break;

                //定制游
                case R.id.ll_item_layout_h:
                    Intent intenth=new Intent(getActivity(),DingzActivity.class);
                    startActivity(intenth);
                    break;





                case R.id.tv_search_address:
                    Intent intentc=new Intent(getActivity(), SearchActivity.class);
                    startActivity(intentc);
                    break;
                //地址选择
                case R.id.tv_home_title_address:

                    HomeAddressPopView homeAddressPopView=new HomeAddressPopView(getActivity(),dataListc,new HomeAddressPopView.CallBackUi() {
                        @Override
                        public void onRequestUi(String resulta, int cityid) {
                            dialog.show();
                            addressdata=resulta;
                            finalcityid=cityid;
                            SharedPreferencesUtils.savecity(getActivity(),resulta);
                            SharedPreferencesUtils.savecityid(getActivity(),cityid);
                            handler.sendEmptyMessage(FINISH_CODE_A);
                        }
                    });
                    homeAddressPopView.showpop(view);
                    break;
                case R.id.img_title_message:
                    Intent intentd=new Intent(getActivity(), MessageActivity.class);
                    startActivity(intentd);
                    break;
                default:
                    break;

            }


        }


    }
    public int getcityid(){
        return finalcityid;
    }




    protected  void dohttpHome(String url,JSONObject  paramhash){

        HttpUtils.dopost(url,getActivity(),paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

//                try{
//                JSONObject jobj=new JSONObject(result);
//                    Log.d("homehomehomehome",""+ jobj);
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
               JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                if (homeinfoa!=null) {
                    String body = Base64Utils.getFromBase64(homeinfoa.getBody());

                    bodyinfo = gson.fromJson(body, HomeMainEntry.class);


                    handler.sendEmptyMessage(FINISH_CODE);
                }else {
                    handler.sendEmptyMessage(ERROE_CODE);
                }

            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }



    //设置背景变化方法大
    private void setTextViewBackColor(TextView mTview,String data){
        mTview.setText(data);
        mTview.setTextColor(getActivity().getResources().getColor(R.color.title_orange));
    }



}
