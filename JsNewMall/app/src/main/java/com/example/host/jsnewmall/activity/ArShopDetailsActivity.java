package com.example.host.jsnewmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.example.host.jsnewmall.model.AroundshopEntry;
import com.example.host.jsnewmall.utils.BitmapCache;
import com.example.host.jsnewmall.view.DialogCallPhone;
import com.uu1.nmw.R;

import java.util.List;


/**
 * Created by host on 2017/4/14.
 */

public class ArShopDetailsActivity extends BaseActivity {
    private AroundshopEntry.DataBean bodyinfo;
    private List<AroundshopEntry.DataBean> bodylist;
    private RequestQueue queue;
    private ImageLoader imageLoader;
    private NetworkImageView mNetimg;
    private LinearLayout mBack;
    private RelativeLayout mRlCallphone,mRlLocation;
    private double mstartlat;
    private double mstartlong;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_aroundshop_details_content);
        queue = Volley.newRequestQueue(getApplicationContext());
        imageLoader = new ImageLoader(queue, new BitmapCache());
        getIntentData();
        initView();
        initListener();
    }


    private void getIntentData(){
        Intent intent=getIntent();
//        bodyinfo= (AroundshopEntry.DataBean) intent.getSerializableExtra("bodyinfo");
        bodylist= (List<AroundshopEntry.DataBean>) intent.getSerializableExtra("bodyinfo");
        int position=intent.getIntExtra("position",0);
        bodyinfo=bodylist.get(position);
        mstartlat=intent.getDoubleExtra("startlat",0);
        mstartlong=intent.getDoubleExtra("startlong",0);

    }

    private void initView(){
        TextView mTvTitlte=(TextView)findViewById(R.id.tv_title_name_change);//标题
        mTvTitlte.setText(bodyinfo.getStore_name());
        mTvTitlte.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_6));
        ImageView mImgMessage=(ImageView)findViewById(R.id.img_title_message);//更多图案显示
        mImgMessage.setVisibility(View.GONE);
        mBack=(LinearLayout)findViewById(R.id.iv_back);

        mNetimg=(NetworkImageView)findViewById(R.id.netimg_shop_details);//头部图片

        //设置显示图片
        mNetimg.setScaleType(ImageView.ScaleType.FIT_XY);
        if (bodyinfo.getMain_photo().size()!=0) {
            mNetimg.setImageUrl(bodyinfo.getMain_photo().get(0).getImg_onlineurl(), imageLoader);
        }

        TextView mTvshopname=(TextView)findViewById(R.id.tv_item_result_a);//店铺名字
        mTvshopname.setText(bodyinfo.getStore_name());

        TextView mTvdistance=(TextView)findViewById(R.id.tv_item_result_b);//距离多少
        mTvdistance.setText("距离"+bodyinfo.getDistance()+"km");

        TextView mTvshopnum=(TextView)findViewById(R.id.tv_shop_phonenum);//门店电话
        mTvshopnum.setText(bodyinfo.getStore_contact_phone());

        TextView mTvshopaddress=(TextView)findViewById(R.id.tv_shop_address);//门店地址
        mTvshopaddress.setText(bodyinfo.getStore_address());

        TextView mTvshoptime=(TextView)findViewById(R.id.tv_shop_time);//营业时间
        if (bodyinfo.getService_time()!=null) {
            mTvshoptime.setText(bodyinfo.getService_time() + "");
        }else {
            mTvshoptime.setText("");
        }


        TextView mTvshoptype=(TextView)findViewById(R.id.tv_shop_type);//门店类型
        String businesstype=bodyinfo.getBusiness_type();
        //营业类型：1、直营店 2、加盟店 3、合作 4、其它
        if (businesstype!=null) {
            if (businesstype.equals("1")) {
                mTvshoptype.setText("直营店");
            } else if (businesstype.equals("2")) {
                mTvshoptype.setText("加盟店");
            } else if (businesstype.equals("3")) {
                mTvshoptype.setText("合作");
            } else if (businesstype.equals("4")) {
                mTvshoptype.setText("其它");
            }
        }else {
            mTvshoptype.setText("");
        }


        TextView mTvshopmaster=(TextView)findViewById(R.id.tv_shop_master);//店长名字
        mTvshopmaster.setText(bodyinfo.getShop_owner());

        TextView mTvshopmasterphone=(TextView)findViewById(R.id.tv_shop_master_phone);//店长电话
        if (bodyinfo.getShop_owner_phone()!=null) {
            mTvshopmasterphone.setText(bodyinfo.getShop_owner_phone() + "");
        }else {
            mTvshopmasterphone.setText("");
        }
        TextView mTvshopcontent=(TextView)findViewById(R.id.tv_shop_content);//店铺说明
        mTvshopcontent.setText(bodyinfo.getStore_description());


        mRlCallphone=(RelativeLayout)findViewById(R.id.rl_call_shop);//点击打电话
        mRlLocation=(RelativeLayout)findViewById(R.id.rl_shop_location);//导航点击


    }


    private void initListener(){

        OnClickListenerImpl listener = new OnClickListenerImpl();
        mBack.setOnClickListener(listener);
        mRlCallphone.setOnClickListener(listener);
        mRlLocation.setOnClickListener(listener);

    }

    private class OnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.iv_back:
                    finish();
                    break;
                //打电话
                case R.id.rl_call_shop:

                    if (bodyinfo.getMain_photo().size()!=0) {
                        DialogCallPhone.showshopdialog(ArShopDetailsActivity.this, bodyinfo.getMain_photo().get(0).getImg_onlineurl(), bodyinfo.getStore_contact_phone());
                    }else {
                        DialogCallPhone.showshopdialogB(ArShopDetailsActivity.this,bodyinfo.getStore_contact_phone());
                    }
                    break;
                //导航
                case R.id.rl_shop_location:

                    final double endlat=Double.parseDouble(bodyinfo.getLatitude());
                    final double endlong=Double.parseDouble(bodyinfo.getLongitude());
                    Intent intentgps=new Intent(ArShopDetailsActivity.this, LocationActivity.class);

                    intentgps.putExtra("startlat",mstartlat);
                    intentgps.putExtra("startlong",mstartlong);
                    intentgps.putExtra("endlat",endlat);
                    intentgps.putExtra("endlong",endlong);

                    startActivity(intentgps);


                    break;

                default:
                    break;

            }
        }
    }
}
