package com.example.host.jsnewmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uu1.nmw.R;
import com.example.host.jsnewmall.adapter.TravellerAdapter;
import com.example.host.jsnewmall.bean.IEditTravellerLayout;
import com.example.host.jsnewmall.model.JsonmModel;
import com.example.host.jsnewmall.model.LoginUserEntry;
import com.example.host.jsnewmall.model.TravellerEntry;
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
 * Created by host on 2017/3/20.
 */

/**
 * 旅客资料列表
 */
public class TravellerInfoActivity extends BaseActivity implements IEditTravellerLayout{
    private LinearLayout  mBack;

    private SimpleDateFormat mSimpleTime;
    private String nTime;
    Gson gson=new Gson();
    private LoginUserEntry userinfo;
    private static final int FINISH_CODE=100;
    private static final int FINISH_CODE_S=103;
    private int currentpage=1;
    private TravellerEntry bodyinfo;
    private PullToRefreshLayout mPullLayout;
    private PullableListView mListview;
    private LoadingDialog dialog;
    private List<TravellerEntry.DataBean> mBodyListaa;
    private boolean refreshState = false;
    private TravellerAdapter adaptera;
    private LinearLayout mLnAddTraveller;;
    private   List<TravellerEntry.DataBean> mBodyList;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FINISH_CODE:
                    dialog.dismiss();

                    mBodyList = bodyinfo.getData();


                    if (refreshState) {
                        mBodyListaa.clear();
                        refreshState = false;
                    }
                    mBodyListaa.addAll(mBodyList);

                    if (adaptera==null) {
                        adaptera = new TravellerAdapter(TravellerInfoActivity.this, mBodyListaa,nTime,TravellerInfoActivity.this);
                        mListview.setAdapter(adaptera);
                    }else {
                        adaptera.notifyDataSetChanged();
                    }





                    break;
                case FINISH_CODE_S:
                    mBodyListaa.clear();
//                    mBodyList.clear();
                    initData();
//                    adaptera.notifyDataSetChanged();
                    break;


                default:
                    break;

            };
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traveller_info_content);
        userinfo=gson.fromJson(SharedPreferencesUtils.getUserInfo(TravellerInfoActivity.this),LoginUserEntry.class);
        Date d=new Date();
        mSimpleTime=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        nTime=mSimpleTime.format(d);
        dialog=new LoadingDialog(TravellerInfoActivity.this);
        dialog.show();
        mBodyListaa = new ArrayList<TravellerEntry.DataBean>();
        initData();
        initView();
        initListener();
    }
    private void initData(){

        JSONObject jbody=null;
        try {
            jbody = new JSONObject();
            jbody.put("uid",userinfo.getUserid());
            jbody.put("page",currentpage);//页数
            jbody.put("method","QueryContact");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
        dohttpgettravellerinfo(UrlUtils.USER,jbodyB);
    }

    private void initView(){

        TextView mtitlename=(TextView)findViewById(R.id.tv_title_name_change);//标题修改
        mtitlename.setText("常用旅客资料");
        mtitlename.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_6));
        ImageView mImgMore=(ImageView)findViewById(R.id.img_title_message);//更多图片显示
        mImgMore.setVisibility(View.GONE);

        mBack=(LinearLayout)findViewById(R.id.iv_back);//返回
        mPullLayout=(PullToRefreshLayout)findViewById(R.id.refresh_view_traveller);//布局刷新
        mListview=(PullableListView)findViewById(R.id.content_view_traveller);//listview
        mLnAddTraveller=(LinearLayout)findViewById(R.id.ln_add_traveller);//新增旅客资料
    }


    private void initListener(){
        OnClickListenerImpl listener = new OnClickListenerImpl();
        mBack.setOnClickListener(listener);
        mLnAddTraveller.setOnClickListener(listener);
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

    @Override
    public void onEditTravellerListener(int position) {
        TravellerEntry.DataBean arrbodyinfo=bodyinfo.getData().get(position);
        Intent intentedit=new Intent(TravellerInfoActivity.this,TravellerEditInfoActivity.class);
        intentedit.putExtra("contactid",arrbodyinfo.getContact_id());
        intentedit.putExtra("sex",arrbodyinfo.getSex());
        intentedit.putExtra("name",arrbodyinfo.getTrue_name());
        intentedit.putExtra("phone",arrbodyinfo.getContact_phone());
        intentedit.putExtra("type",arrbodyinfo.getPaper_type());
        intentedit.putExtra("typenum",arrbodyinfo.getPaper_num());
        intentedit.putExtra("userid",userinfo.getUserid());
        startActivityForResult(intentedit,107);
    }


    private class OnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_back:
                    Intent intentfinish=new Intent(TravellerInfoActivity.this,MainActivity.class);
                    intentfinish.putExtra("id",2);
                    startActivity(intentfinish);
                    finish();
                    break;
                //新增点击
                case  R.id.ln_add_traveller:
                    Intent intent=new Intent(TravellerInfoActivity.this,TravellerAddInfoActivity.class);
                    intent.putExtra("userid",userinfo.getUserid());
                    startActivityForResult(intent,106);
                    break;
                default:
                    break;

            }
        }
    }




    protected  void dohttpgettravellerinfo(String url,JSONObject  paramhash){
        HttpUtils.dopost(url,getApplicationContext(),paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());

                bodyinfo=gson.fromJson(body, TravellerEntry.class);

                handler.sendEmptyMessage(FINISH_CODE);

            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(resultCode){
            case 55:
                handler.sendEmptyMessage(FINISH_CODE_S);

                break;
            default:
                break;
        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(TravellerInfoActivity.this,MainActivity.class);
        intent.putExtra("id",2);
        startActivity(intent);
        finish();

    }

}
