package com.example.host.jsnewmall.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.host.jsnewmall.activity.AllOrderActivity;
import com.example.host.jsnewmall.activity.AroundshopActivity;
import com.example.host.jsnewmall.activity.CenterCommentActivity;
import com.example.host.jsnewmall.activity.CenterHistoryActivity;
import com.example.host.jsnewmall.activity.CollectListActivity;
import com.example.host.jsnewmall.activity.CouponActivity;
import com.example.host.jsnewmall.activity.LocationActivity;
import com.example.host.jsnewmall.activity.LoginActivity;
import com.example.host.jsnewmall.activity.PersonSetUpActivity;
import com.example.host.jsnewmall.activity.PointsActivity;
import com.example.host.jsnewmall.activity.SetUpActivity;
import com.example.host.jsnewmall.activity.TravellerInfoActivity;
import com.example.host.jsnewmall.model.JsonmModel;
import com.example.host.jsnewmall.model.LoginUserEntry;
import com.example.host.jsnewmall.model.LoginYouhuiEntry;
import com.example.host.jsnewmall.model.SetUpEntry;
import com.example.host.jsnewmall.utils.Base64Utils;
import com.example.host.jsnewmall.utils.HttpUtils;
import com.example.host.jsnewmall.utils.JsonUtils;
import com.example.host.jsnewmall.utils.SharedPreferencesUtils;
import com.example.host.jsnewmall.utils.StatusBarUtils;
import com.example.host.jsnewmall.utils.ToastUtils;
import com.example.host.jsnewmall.utils.UrlUtils;
import com.example.host.jsnewmall.view.CenterCircleImageView;
import com.example.host.jsnewmall.view.CircleNetWorkImage;
import com.google.gson.Gson;
import com.uu1.nmw.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by host on 2017/2/15.
 * 个人中心页面
 */

public class CenterFragment extends BaseFragment implements AMapLocationListener {
    private View view;
    private ImageView mShezhi;
    private LinearLayout mCenterAll,mCenterSH,mCenterFK,mCenterCY;
    private RelativeLayout mYouhuiquan,mJifen;
    private RelativeLayout mRlcentera,mRlcenterb,mRlcenterc,mRlcenterd,mRlcentere;
    private TextView mTvcenteryouhui,mTvcenterjifen;
    private TextView mTvUsername, mTvHuiyuanmsg;
    private LinearLayout mLnWeidenglu;
    private RelativeLayout mRlNameMsg;
    Gson gson=new Gson();
    private static final int FINISH_CODE=100;
    private static final int FINISH_CODE_A=101;
    private static final int FINISH_CODE_S=102;
    private static final int FINISH_CODE_REFRESH=103;
    private static final int FINISH_CODE_EXITREFRESH=104;
    private LoginUserEntry userinfo;
    private ImageView mImgTou;
    private CenterCircleImageView circleImageView;
    private SimpleDateFormat mSimpleTime;
    private String nTime;
    private LoginYouhuiEntry discountinfo;
    private SetUpEntry mSetupinfo;

    private AMapLocationClient mlocationClient;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    private double mstartlat;
    private double mstartlong;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FINISH_CODE:

//                    SharedPreferencesUtils.clearUserInfo(getActivity());
                    userinfo=gson.fromJson(SharedPreferencesUtils.getUserInfo(getActivity()),LoginUserEntry.class);
                    if (userinfo!=null){
                        initDatajifen();
                        initUserInfo();
                    }
                    break;

                case FINISH_CODE_A:
                    if (null!=discountinfo) {
                        mTvcenteryouhui.setText(discountinfo.getData().getCoupon_num() + "张");
                        mTvcenterjifen.setText(discountinfo.getData().getPoints_num() + "分");
                    }
                    break;

                case FINISH_CODE_S:

                    circleImageView.readBitmapViaVolley(mSetupinfo.getData().getHeadimgurl(),mImgTou);//头像
                    mRlNameMsg.setVisibility(View.VISIBLE);
                    mLnWeidenglu.setVisibility(View.GONE);

                    mTvUsername.setText(mSetupinfo.getData().getNickname()+"");//用户名
                    mTvHuiyuanmsg.setText(mSetupinfo.getData().getUser_grade_name()+"");

                    break;

                case FINISH_CODE_REFRESH:
                    initDatajifen();
                    initUserInfo();

                    break;
                case FINISH_CODE_EXITREFRESH:
                    userinfo=null;
                    mImgTou.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.icon_touxiang));
                    mRlNameMsg.setVisibility(View.GONE);
                    mLnWeidenglu.setVisibility(View.VISIBLE);
                    mTvcenteryouhui.setText("0" + "张");
                    mTvcenterjifen.setText("0" + "分");
                    break;
                default:
                    break;

            };
        }
    };



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_center_main_content,null);
        Date d=new Date();
        mSimpleTime=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        nTime=mSimpleTime.format(d);

        if (userinfo!=null){
            initDatajifen();
            initUserInfo();
        }
        initLocationData();
        initView();
        initListener();
        handler.sendEmptyMessage(FINISH_CODE);

        return view;
    }

    private void initView(){
         mImgTou=(ImageView)view.findViewById(R.id.center_img_touxiang);//头像
         circleImageView=new CenterCircleImageView(getActivity());
//        circleImageView.readBitmapViaVolley("http://imgtu.5011.net/uploads/content/20170207/5885661486451448.jpg",mImgTou);

        mShezhi=(ImageView)view.findViewById(R.id.img_shezhi);//设置按钮
        mCenterAll=(LinearLayout)view.findViewById(R.id.center_all);//全部
        mCenterSH=(LinearLayout)view.findViewById(R.id.center_shenghe);//待审核
        mCenterFK=(LinearLayout)view.findViewById(R.id.center_fukuan);//待付款
        mCenterCY=(LinearLayout)view.findViewById(R.id.center_chuyou);//待出游
        mYouhuiquan=(RelativeLayout)view.findViewById(R.id.center_rl_youhuiquan);//优惠券
        mJifen=(RelativeLayout)view.findViewById(R.id.center_rl_jifen);//积分
        mRlcentera=(RelativeLayout)view.findViewById(R.id.rl_center_a);//我的收藏
        mRlcenterb=(RelativeLayout)view.findViewById(R.id.rl_center_b);//浏览历史
        mRlcenterc=(RelativeLayout)view.findViewById(R.id.rl_center_c);//我的顾问
        mRlcenterd=(RelativeLayout)view.findViewById(R.id.rl_center_d);//我的成长值
        mRlcentere=(RelativeLayout)view.findViewById(R.id.rl_center_e);//常用旅客资料
        mTvcenteryouhui=(TextView)view.findViewById(R.id.tv_center_youhuiquan);//优惠券显示
        mTvcenterjifen=(TextView)view.findViewById(R.id.tv_center_jifen);//积分显示
        mTvUsername=(TextView)view.findViewById(R.id.tv_username);//用户名
        mTvHuiyuanmsg=(TextView)view.findViewById(R.id.tv_huiyuanmsg);//会员信息
        mLnWeidenglu=(LinearLayout)view.findViewById(R.id.ln_center_weidenglu);//未登录点击
        mRlNameMsg=(RelativeLayout)view.findViewById(R.id.rl_name_msg);//登陆后信息显示



    }


    private void initLocationData(){
        mlocationClient = new AMapLocationClient(getActivity());
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置返回地址信息，默认为true
        mLocationOption.setNeedAddress(true);
        //设置定位监听
        mlocationClient.setLocationListener(this);
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        mlocationClient.startLocation();
    }


    private void initListener(){

        OnClickListenerImpl listener = new OnClickListenerImpl();
        mShezhi.setOnClickListener(listener);
        mCenterAll.setOnClickListener(listener);
        mCenterSH.setOnClickListener(listener);
        mCenterFK.setOnClickListener(listener);
        mCenterCY.setOnClickListener(listener);
        mYouhuiquan.setOnClickListener(listener);
        mJifen.setOnClickListener(listener);
        mRlcentera.setOnClickListener(listener);
        mRlcenterb.setOnClickListener(listener);
        mRlcenterc.setOnClickListener(listener);
        mRlcenterd.setOnClickListener(listener);
        mRlcentere.setOnClickListener(listener);
        mLnWeidenglu.setOnClickListener(listener);
        mImgTou.setOnClickListener(listener);


    }

    private void initDatajifen(){

        JSONObject jbody=null;
        try {
            jbody = new JSONObject();
            jbody.put("user_id",userinfo.getUserid());
            jbody.put("method","User_info_num");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
        dohttpGetfino(UrlUtils.APP_URL,jbodyB);
    }


    private void initUserInfo(){
        JSONObject jbodyuser=null;
        try {
            jbodyuser = new JSONObject();
            jbodyuser.put("uid",userinfo.getUserid());
            jbodyuser.put("method","QueryUserInfo");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyC= JsonUtils.JsonParseInfo(nTime,jbodyuser);
        dohttpGetuserinfo(UrlUtils.USER,jbodyC);
    }




    private class OnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {


            if (userinfo==null){
                Intent  intent=new Intent(getActivity(), LoginActivity.class);
//                    startActivity(intent);
                startActivityForResult(intent,107);
            }else {
                switch (view.getId()) {

//                //未登录点击
//                case R.id.ln_center_weidenglu:
//
//                    break;


                    //设置
                    case R.id.img_shezhi:

                        Intent intentshezhi=new Intent(getActivity(), PersonSetUpActivity.class);
                        intentshezhi.putExtra("userid",userinfo.getUserid());
                        startActivityForResult(intentshezhi,52);
                        break;
                    //全部
                    case R.id.center_all:
                        Intent intentall=new Intent(getActivity(),AllOrderActivity.class);
                        intentall.putExtra("currentindex",0);
                        startActivity(intentall);

                        break;
                    //待审核
                    case R.id.center_shenghe:
                        Intent intentshenhe=new Intent(getActivity(),AllOrderActivity.class);
                        intentshenhe.putExtra("currentindex",1);
                        startActivity(intentshenhe);

                        break;
                    //待付款
                    case R.id.center_fukuan:
                        Intent intentfukuan=new Intent(getActivity(),AllOrderActivity.class);
                        intentfukuan.putExtra("currentindex",2);
                        startActivity(intentfukuan);

                        break;
                    //待出游
                    case R.id.center_chuyou:
                        Intent intentchuyou=new Intent(getActivity(),AllOrderActivity.class);
                        intentchuyou.putExtra("currentindex",3);
                        startActivity(intentchuyou);

                        break;
                    //优惠券
                    case R.id.center_rl_youhuiquan:
                        Intent intentcoupon=new Intent(getActivity(), CouponActivity.class);
                        intentcoupon.putExtra("userid",userinfo.getUserid());
                        startActivity(intentcoupon);
                        break;
                    //积分
                    case R.id.center_rl_jifen:
                        Intent intentjifen=new Intent(getActivity(), PointsActivity.class);
                        intentjifen.putExtra("userid",userinfo.getUserid());
                        startActivity(intentjifen);
                        break;

                    //我的收藏
                    case R.id.rl_center_a:
                        Intent intentcollection=new Intent(getActivity(), CollectListActivity.class);
                        intentcollection.putExtra("userid",userinfo.getUserid());
                        startActivity(intentcollection);
                        break;
                    //浏览历史
                    case R.id.rl_center_b:
                        Intent intenthistory=new Intent(getActivity(), CenterHistoryActivity.class);
                        startActivity(intenthistory);


                        break;
                    //我的顾问改为周边门店
                    case R.id.rl_center_c:

                        Intent intentshop=new Intent(getActivity(), AroundshopActivity.class);
                        intentshop.putExtra("startlat",mstartlat);
                        intentshop.putExtra("startlong",mstartlong);
                        startActivity(intentshop);

                        break;
                    //我的成长值改为我的评价列表
                    case R.id.rl_center_d:
                        Intent intentcomment=new Intent(getActivity(), CenterCommentActivity.class);
                        intentcomment.putExtra("userid",userinfo.getUserid());
                        startActivity(intentcomment);

                        break;
                    //常用旅客资料
                    case R.id.rl_center_e:
                        Intent intente=new Intent(getActivity(), TravellerInfoActivity.class);
                        startActivity(intente);
                        break;

                    default:
                        break;

                }
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       switch(resultCode){
           //登陆界面的刷新
           case 59:
               handler.sendEmptyMessage(FINISH_CODE);
               break;
           //改变人物信息刷新页面
           case 75:
               handler.sendEmptyMessage(FINISH_CODE_REFRESH);
               break;
           //退出当前账户的通知刷新
           case 76:
               handler.sendEmptyMessage(FINISH_CODE_EXITREFRESH);
               break;
           default:
               break;
       }
    }


    protected  void dohttpGetfino(String url,JSONObject  paramhash){
        HttpUtils.dopost(url,getActivity(),paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());

                discountinfo=gson.fromJson(body, LoginYouhuiEntry.class);

                handler.sendEmptyMessage(FINISH_CODE_A);

            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }




    protected  void dohttpGetuserinfo(String url,JSONObject  paramhash){
        HttpUtils.dopost(url,getActivity(),paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {
                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());
                mSetupinfo=gson.fromJson(body, SetUpEntry.class);
                handler.sendEmptyMessage(FINISH_CODE_S);
            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }


    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                mstartlat=amapLocation.getLatitude();//获取纬度
                mstartlong=amapLocation.getLongitude();//获取经度
                amapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);//定位时间
                amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                amapLocation.getCountry();//国家信息
                amapLocation.getProvince();//省信息
                amapLocation.getCity();//城市信息
                amapLocation.getDistrict();//城区信息
                amapLocation.getStreet();//街道信息
                amapLocation.getStreetNum();//街道门牌号信息
                amapLocation.getCityCode();//城市编码
                amapLocation.getAdCode();//地区编码
                amapLocation.getAoiName();//获取当前定位点的AOI信息
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError","location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }



    //退出登陆后返回此界面刷新页面
    public void refreshloginout(){
        handler.sendEmptyMessage(FINISH_CODE_EXITREFRESH);
    }


    //登陆成功后返回此界面刷新页面
    public void refreshlogin(){
        handler.sendEmptyMessage(FINISH_CODE);
    }


}
