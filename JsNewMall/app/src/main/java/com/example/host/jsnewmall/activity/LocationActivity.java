package com.example.host.jsnewmall.activity;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.SupportMapFragment;
import com.amap.api.maps.UiSettings;
import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.model.AMapLaneInfo;
import com.amap.api.navi.model.AMapNaviCross;
import com.amap.api.navi.model.AMapNaviInfo;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviPath;
import com.amap.api.navi.model.AMapNaviStaticInfo;
import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.AimLessModeCongestionInfo;
import com.amap.api.navi.model.AimLessModeStat;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.model.NaviLatLng;
import com.amap.api.navi.view.RouteOverLay;
import com.autonavi.tbt.NaviStaticInfo;
import com.autonavi.tbt.TrafficFacilityInfo;
import com.example.host.jsnewmall.utils.ToastUtils;
import com.uu1.nmw.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by host on 2017/4/11.
 */

public class LocationActivity extends AppCompatActivity implements AMapNaviListener, View.OnClickListener {
        /**
         * 导航对象(单例)
         */
        private AMapNavi mAMapNavi;
        private AMap mAMap;
        private NaviLatLng endLatlng;
        private NaviLatLng startLatlng;
        private List<NaviLatLng> startList = new ArrayList<NaviLatLng>();
        private RouteOverLay mRouteOverlay;
        private double mstartlat;
        private double mstartlong;
        private double mendlat;
        private double mendlong;

        /**
         * 途径点坐标集合
         */
        private List<NaviLatLng> wayList = new ArrayList<NaviLatLng>();
        /**
         * 终点坐标集合［建议就一个终点］
         */
        private List<NaviLatLng> endList = new ArrayList<NaviLatLng>();
    /*
        * strategyFlag转换出来的值都对应PathPlanningStrategy常量，用户也可以直接传入PathPlanningStrategy常量进行算路。
        * 如:mAMapNavi.calculateDriveRoute(mStartList, mEndList, mWayPointList,PathPlanningStrategy.DRIVING_DEFAULT);
        */
        int strategyFlag = 0;
        private Button mStartNaviButton;
        private Button mCancel;
        private LinearLayout mLnbtnlayout;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_location_content);
            getIntentData();

            mStartNaviButton = (Button) findViewById(R.id.calculate_route_start_navi);
            mCancel=(Button)findViewById(R.id.btn_finish_map);
            mLnbtnlayout=(LinearLayout)findViewById(R.id.ln_imap_navi);//按钮布局
            mStartNaviButton.setOnClickListener(this);
            mCancel.setOnClickListener(this);
            setUpMapIfNeeded();
            initNavi();
        }
        private void getIntentData(){
            Intent intent=getIntent();
            mstartlat=intent.getDoubleExtra("startlat",0);
            mstartlong=intent.getDoubleExtra("startlong",0);
            mendlat=intent.getDoubleExtra("endlat",0);
            mendlong=intent.getDoubleExtra("endlong",0);
        }

        @Override
        protected void onResume() {
            super.onResume();
            setUpMapIfNeeded();
        }


    private void setUpMapIfNeeded() {
        if (mAMap == null) {
            mAMap = ((SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map)).getMap();
            UiSettings uiSettings = mAMap.getUiSettings();
            if (uiSettings != null) {
                uiSettings.setRotateGesturesEnabled(false);
            }
        }
    }

    /**
     * 驾车路径规划计算
     */
    private void calculateDriveRoute() {
        try {
            strategyFlag = mAMapNavi.strategyConvert(true, false, false, true, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mAMapNavi.calculateDriveRoute(startList, endList, wayList, strategyFlag);
    }

    /**
     * 导航初始化
     */
    private void initNavi() {
        startLatlng = new NaviLatLng(mstartlat, mstartlong);
        endLatlng = new NaviLatLng(mendlat, mendlong);

        startList.add(startLatlng);
        endList.add(endLatlng);
        mAMapNavi = AMapNavi.getInstance(getApplicationContext());
        mAMapNavi.addAMapNaviListener(this);
        calculateDriveRoute();
    }

    private void cleanRouteOverlay() {
        if (mRouteOverlay != null) {
            mRouteOverlay.destroy();
        }
    }

    /**
     * 绘制路径规划结果
     *
     * @param path AMapNaviPath
     */
    private void drawRoutes(AMapNaviPath path) {
        mAMap.moveCamera(CameraUpdateFactory.changeTilt(0));
        mRouteOverlay = new RouteOverLay(mAMap, path, this);
        mRouteOverlay.addToMap();
        mRouteOverlay.zoomToSpan();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.calculate_route_start_navi:
                startNavi();
                break;
            case R.id.btn_finish_map:
                finish();
                break;
            default:
                break;
        }
//        if(view.getId() == R.id.calculate_route_start_navi){
//            startNavi();
//        }


    }
    /**
     * 开始导航
     */
    private void startNavi() {
        Intent gpsintent = new Intent(getApplicationContext(), RouteNaviActivity.class);
        gpsintent.putExtra("gps", true); // gps 为true为真实导航，为false为模拟导航
        startActivity(gpsintent);
    }

    @Override
    public void onCalculateRouteSuccess() {
        cleanRouteOverlay();
        AMapNaviPath path = mAMapNavi.getNaviPath();
        if (path != null) {
            drawRoutes(path);
        }
        mLnbtnlayout.setVisibility(View.VISIBLE);
        mStartNaviButton.setVisibility(View.VISIBLE);
        mCancel.setVisibility(View.VISIBLE);
    }

    @Override
    public void onInitNaviFailure() {

    }

    @Override
    public void onInitNaviSuccess() {

    }

    @Override
    public void onStartNavi(int i) {

    }

    @Override
    public void onTrafficStatusUpdate() {

    }

    @Override
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {

    }

    @Override
    public void onGetNavigationText(int i, String s) {
//        ToastUtils.show(getApplicationContext(),"输出"+s);
    }

    @Override
    public void onEndEmulatorNavi() {

    }

    @Override
    public void onArriveDestination() {

    }

    @Override
    public void onArriveDestination(NaviStaticInfo naviStaticInfo) {

    }

    @Override
    public void onArriveDestination(AMapNaviStaticInfo aMapNaviStaticInfo) {

    }

    @Override
    public void onCalculateRouteFailure(int i) {

    }

    @Override
    public void onReCalculateRouteForYaw() {

    }

    @Override
    public void onReCalculateRouteForTrafficJam() {

    }

    @Override
    public void onArrivedWayPoint(int i) {

    }

    @Override
    public void onGpsOpenStatus(boolean b) {

    }

    @Override
    public void onNaviInfoUpdated(AMapNaviInfo aMapNaviInfo) {

    }

    @Override
    public void onNaviInfoUpdate(NaviInfo naviInfo) {

    }

    @Override
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo aMapNaviTrafficFacilityInfo) {

    }

    @Override
    public void OnUpdateTrafficFacility(TrafficFacilityInfo trafficFacilityInfo) {

    }

    @Override
    public void showCross(AMapNaviCross aMapNaviCross) {

    }

    @Override
    public void hideCross() {

    }

    @Override
    public void showLaneInfo(AMapLaneInfo[] aMapLaneInfos, byte[] bytes, byte[] bytes1) {

    }

    @Override
    public void hideLaneInfo() {

    }

    @Override
    public void onCalculateMultipleRoutesSuccess(int[] ints) {

    }

    @Override
    public void notifyParallelRoad(int i) {

    }

    @Override
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] aMapNaviTrafficFacilityInfos) {

    }

    @Override
    public void updateAimlessModeStatistics(AimLessModeStat aimLessModeStat) {

    }

    @Override
    public void updateAimlessModeCongestionInfo(AimLessModeCongestionInfo aimLessModeCongestionInfo) {

    }




}
