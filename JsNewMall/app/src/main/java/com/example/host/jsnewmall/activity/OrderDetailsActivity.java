package com.example.host.jsnewmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;


import com.example.host.jsnewmall.adapter.OrderTravellerAdapter;
import com.example.host.jsnewmall.model.JsonmModel;
import com.example.host.jsnewmall.model.OrderdetailsEntry;
import com.example.host.jsnewmall.utils.Base64Utils;
import com.example.host.jsnewmall.utils.HttpUtils;
import com.example.host.jsnewmall.utils.JsonUtils;
import com.example.host.jsnewmall.utils.ToastUtils;
import com.example.host.jsnewmall.utils.UrlUtils;
import com.example.host.jsnewmall.view.HomeForthGridView;
import com.example.host.jsnewmall.view.LoadingDialog;
import com.google.gson.Gson;
import com.uu1.nmw.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by host on 2017/3/16.
 */

public class OrderDetailsActivity extends BaseActivity {
    private SimpleDateFormat mSimpleTime;
    private String nTime;
    Gson gson=new Gson();
    private LoadingDialog dialog;
    private static final int FINISH_CODE=100;
    private static final int FINISH_CODE_S=103;
    private OrderdetailsEntry mOrderdetailsInfo;
    private String orderid;
    private LinearLayout mBack;
    private LinearLayout submitpay;
    private int crqtydetails = 0;
    private int rtqtydetails = 0;
    private int lrqtydetails = 0;
    private int xsqtydetails = 0;
    private RelativeLayout mRladdtraveller;
    private ImageView mImageTravel;
    private int personnum;
    private int ordercontact;
    private ListView mListTraveller;
    private List<OrderdetailsEntry.OrdercontactBean> mOrdeContactlist;
    private  OrderTravellerAdapter mOrderTravellerAdapter;
    private ScrollView mScrollview;
    private boolean firstbool=true;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FINISH_CODE:
                    dialog.dismiss();
                    initView();
                    initListener();

                    mOrdeContactlist=mOrderdetailsInfo.getOrdercontact();
                    mOrderTravellerAdapter=new OrderTravellerAdapter(OrderDetailsActivity.this,mOrdeContactlist);
                    mListTraveller.setAdapter(mOrderTravellerAdapter);
                    HomeForthGridView.setListViewHeight(mListTraveller);
                    mOrderTravellerAdapter.notifyDataSetChanged();

                    break;
                case FINISH_CODE_S:
                    mOrdeContactlist.clear();
                    initData();
//                    mOrderTravellerAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;

            };
        }
    };




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details_content);
        dialog=new LoadingDialog(this);
        dialog.show();

        Date d=new Date();
        mSimpleTime=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        nTime=mSimpleTime.format(d);

        getdata();
        initData();

    }
    private void getdata(){
        Intent intent=getIntent();
        orderid=intent.getStringExtra("orderid");
    }

    private void initData(){

        JSONObject jbody=null;
        try {
            jbody = new JSONObject();
            jbody.put("id",orderid);
            jbody.put("method","QueryOrderInfo");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
        dohttpOrderdetailsInfo(UrlUtils.ROUTE_LINE,jbodyB);

    }




    private void initView(){
        TextView mTitlename=(TextView)findViewById(R.id.tv_title_name_change);
        mTitlename.setText("订单详情");
        mTitlename.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_3));
        ImageView mImgMore=(ImageView)findViewById(R.id.img_title_message);//更多图案
        mImgMore.setVisibility(View.GONE);
        mBack=(LinearLayout)findViewById(R.id.iv_back);//返回
        mScrollview=(ScrollView)findViewById(R.id.scrollview_orderdetails);//srcollview布局
        mScrollview.smoothScrollTo(0,0);

        submitpay=(LinearLayout) findViewById(R.id.ln_submit_details_pay);//付款按钮
        TextView mDetailsa=(TextView) findViewById(R.id.tv_order_details_a);//订单号
        TextView mDetailsb=(TextView) findViewById(R.id.tv_order_details_b);//状态
        String ordercode=mOrderdetailsInfo.getOrderdata().getOrder_code();
        String orderstate=mOrderdetailsInfo.getOrderdata().getOrder_state();

        getState(orderstate,mDetailsb);//设置状态显示
        mDetailsa.setText("订单号:"+ordercode);//订单号显示


        TextView mDetailsc=(TextView)findViewById(R.id.tv_order_details_c);//出游标题
        mDetailsc.setText(mOrderdetailsInfo.getOrderteam().getTeam_title());

        TextView mDetailsd=(TextView)findViewById(R.id.tv_order_details_d);//订单金额
        mDetailsd.setText("订单金额："+mOrderdetailsInfo.getOrderdata().getOrder_total_money());

        TextView mDetailse=(TextView)findViewById(R.id.tv_order_details_e);//已支付金额
        mDetailse.setText("已支付金额："+mOrderdetailsInfo.getOrderdata().getOrder_paid_money());

        TextView mDetailsf=(TextView)findViewById(R.id.tv_order_details_f);//出发城市
        mDetailsf.setText("出发城市："+mOrderdetailsInfo.getOrderteam().getOrder_startcity());

        TextView mDetailsg=(TextView)findViewById(R.id.tv_order_details_g);//出游日期
        mDetailsg.setText("出游日期："+mOrderdetailsInfo.getOrderteam().getGodate());

        TextView mDetailsh=(TextView)findViewById(R.id.tv_order_details_h);//返回日期
        mDetailsh.setText("返回日期："+mOrderdetailsInfo.getOrderteam().getBackdate());

        TextView mTvcrqty=(TextView)findViewById(R.id.tv_details_crqty);//成人
        TextView mTvrtqty=(TextView)findViewById(R.id.tv_details_rtqty);//儿童
        TextView mTvlrqty=(TextView)findViewById(R.id.tv_details_lrqty);//老人
        TextView mTvxsqty=(TextView)findViewById(R.id.tv_details_xsqty);//学生


        if (firstbool){
        for (int i=0;i<mOrderdetailsInfo.getOrderproduct().size();i++){
            OrderdetailsEntry.OrderproductBean minfo =mOrderdetailsInfo.getOrderproduct().get(i);
            int crqty=minfo.getCrqty()==null?0:Integer.parseInt(minfo.getCrqty());
            int rtqty=minfo.getRtqty()==null?0:Integer.parseInt(minfo.getRtqty());
            int lrqty=minfo.getLrqty()==null?0:Integer.parseInt(minfo.getLrqty());
            int xsqty=minfo.getXsqty()==null?0:Integer.parseInt(minfo.getXsqty());
            crqtydetails+=crqty;
            rtqtydetails+=rtqty;
            lrqtydetails+=lrqty;
            xsqtydetails+=xsqty;
        }
        }


        if (crqtydetails!=0){
            mTvcrqty.setVisibility(View.VISIBLE);
            mTvcrqty.setText("成人"+crqtydetails);
        }
        if (rtqtydetails!=0){
            mTvrtqty.setVisibility(View.VISIBLE);
            mTvrtqty.setText("儿童"+rtqtydetails);
        }
        if (lrqtydetails!=0){
            mTvlrqty.setVisibility(View.VISIBLE);
            mTvlrqty.setText("老人"+lrqtydetails);
        }
        if (xsqtydetails!=0){
            mTvxsqty.setVisibility(View.VISIBLE);
            mTvxsqty.setText("学生"+xsqtydetails);
        }




        TextView mDetailsi=(TextView)findViewById(R.id.tv_order_details_i);//姓名
        TextView mDetailsj=(TextView)findViewById(R.id.tv_order_details_j);//手机
        TextView mDetailsk=(TextView)findViewById(R.id.tv_order_details_k);//邮箱
        mDetailsi.setText("姓名："+mOrderdetailsInfo.getOrderdata().getLink_man());
        mDetailsj.setText("手机："+mOrderdetailsInfo.getOrderdata().getLink_mobile());
        mDetailsk.setText("邮箱："+mOrderdetailsInfo.getOrderdata().getLink_email());

        mRladdtraveller=(RelativeLayout)findViewById(R.id.rl_order_add_traveller);//旅客信息
        mListTraveller=(ListView)findViewById(R.id.list_order_traveller);//当前旅客信息列表显示




        mImageTravel=(ImageView)findViewById(R.id.img_select_traveller);//箭头显示
        personnum=crqtydetails+rtqtydetails+lrqtydetails+xsqtydetails;//所有人数
        ordercontact=mOrderdetailsInfo.getOrdercontact().size();//已选择了的人数
        if (ordercontact>=personnum){
            mRladdtraveller.setClickable(false);
            mImageTravel.setVisibility(View.GONE);
        }

    }

    private void  initListener(){
        OnClickListenerImpl listener = new OnClickListenerImpl();
        mBack.setOnClickListener(listener);
        submitpay.setOnClickListener(listener);
        mRladdtraveller.setOnClickListener(listener);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(resultCode){
            case 66:
                firstbool=false;
                handler.sendEmptyMessage(FINISH_CODE_S);
                break;
            default:
                break;
        }
    }

    private class OnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            switch (view.getId()) {

                case R.id.iv_back:

                    finish();
                    break;
                case R.id.ln_submit_details_pay:
                    Intent intentre=new Intent(OrderDetailsActivity.this,PayActivity.class);
                    intentre.putExtra("title",mOrderdetailsInfo.getOrderteam().getTeam_title());
                    intentre.putExtra("godate",mOrderdetailsInfo.getOrderteam().getGodate());
                    intentre.putExtra("crqtydetails",crqtydetails);
                    intentre.putExtra("rtqtydetails",rtqtydetails);
                    intentre.putExtra("lrqtydetails",lrqtydetails);
                    intentre.putExtra("xsqtydetails",xsqtydetails);
                    intentre.putExtra("notpaidmoney",mOrderdetailsInfo.getOrderdata().getOrder_notpaid_money());
                    intentre.putExtra("ordercode",mOrderdetailsInfo.getOrderdata().getOrder_code());
                    startActivity(intentre);
                    break;
                //旅客信息选择跳转

                case R.id.rl_order_add_traveller:
                    if (ordercontact<personnum) {
                        Intent intentpre = new Intent(OrderDetailsActivity.this, PerTravellerInfoActivity.class);
                        intentpre.putExtra("contactlist",(Serializable) mOrdeContactlist);
                        intentpre.putExtra("personnum",personnum);
                        intentpre.putExtra("detailsorderid",orderid);

                        startActivityForResult(intentpre,97);
                    }
                    break;

                default:
                    break;
            }
        }
    }



    protected  void dohttpOrderdetailsInfo(String url,JSONObject  paramhash){
        HttpUtils.dopost(url,getApplicationContext(),paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());
                mOrderdetailsInfo=gson.fromJson(body, OrderdetailsEntry.class);
                handler.sendEmptyMessage(FINISH_CODE);
            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }


    private void getState(String state, TextView textView){
        if (state.equals("1")){
            textView.setText("待审核");
        }else if(state.equals("2")){
            textView.setText("待付款");
            submitpay.setVisibility(View.VISIBLE);

//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            layoutParams.setMargins(0,0,0,50);//4个参数按顺序分别是左上右下
//            mScrollview.setLayoutParams(layoutParams);

            mScrollview.setPadding(0,0,0,100);
        }else if(state.equals("3")){
            textView.setText("待确定");
        }else if(state.equals("4")){
            textView.setText("已完成");
        }else if(state.equals("5")){
            textView.setText("已取消");
        }else if(state.equals("6")){
            textView.setText("已删除");
        }else if(state.equals("7")){
            textView.setText("已作废");
        }else if(state.equals("8")){
            textView.setText("待出游");

        }
    }

}
