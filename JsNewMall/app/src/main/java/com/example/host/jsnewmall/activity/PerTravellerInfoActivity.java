package com.example.host.jsnewmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.host.jsnewmall.adapter.PerTravellerInfoaAdapter;
import com.example.host.jsnewmall.adapter.PerTravellerInfobAdapter;
import com.example.host.jsnewmall.model.JsonmModel;
import com.example.host.jsnewmall.model.LoginUserEntry;
import com.example.host.jsnewmall.model.OrderdetailsEntry;
import com.example.host.jsnewmall.model.TravellerAddSuccessEntry;
import com.example.host.jsnewmall.model.TravellerEntry;
import com.example.host.jsnewmall.utils.Base64Utils;
import com.example.host.jsnewmall.utils.HttpUtils;
import com.example.host.jsnewmall.utils.JsonUtils;
import com.example.host.jsnewmall.utils.SharedPreferencesUtils;
import com.example.host.jsnewmall.utils.ToastUtils;
import com.example.host.jsnewmall.utils.UrlUtils;
import com.example.host.jsnewmall.view.HomeForthGridView;
import com.example.host.jsnewmall.view.LoadingDialog;
import com.google.gson.Gson;
import com.uu1.nmw.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by host on 2017/3/22.
 */

//旅客信息选择界面
public class PerTravellerInfoActivity extends BaseActivity {
    private LinearLayout mBack;
    private TextView mAddTraveller;
    private String perorderid=null;
    private SimpleDateFormat mSimpleTime;
    private String nTime;
    Gson gson=new Gson();
    private LoadingDialog dialog;
    private static final int FINISH_CODE=100;
    private static final int FINISH_CODE_A=101;
    private static final int FINISH_CODE_B=102;
    private static final int FINISH_RESULT_CODE=103;
    private TravellerEntry bodyinfo;
    private int travelpersonnum=0;
    private LoginUserEntry userinfo;
    private List<OrderdetailsEntry.OrdercontactBean> contactlist;

    private ListView mListpera;
    private TextView mTvselectnum;
    private ListView mListperb;
    private int checkNum; // 记录选中的条目数量
    private LinearLayout  mSubmitlayout;
    private int currentsum;

    private String finalorderid;
    private String detailsorderid;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FINISH_CODE:
                    dialog.dismiss();

                    PerTravellerInfobAdapter adapterb=new PerTravellerInfobAdapter(PerTravellerInfoActivity.this,bodyinfo.getData());
                    mListperb.setAdapter(adapterb);
                    HomeForthGridView.setListViewHeight(mListperb);
                    mListperb.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                    adapterb.notifyDataSetChanged();



                    break;
                case FINISH_CODE_A:
                    if (detailsorderid!=null){
                        setResult(66);
                        finish();
                    }else {
                        Intent intent=new Intent(PerTravellerInfoActivity.this,MainActivity.class);
                        intent.putExtra("id",2);
                        startActivity(intent);
                        finish();
                    }

                    break;

                case FINISH_CODE_B:
                    ToastUtils.show(PerTravellerInfoActivity.this,"提交旅客信息失败");
                    break;

                case FINISH_RESULT_CODE:
                    initdata();
                    break;
                default:
                    break;

            };
        }
    };



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pertravller_info_content);
        Date d=new Date();
        mSimpleTime=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        nTime=mSimpleTime.format(d);
        dialog=new LoadingDialog(this);
        userinfo=gson.fromJson(SharedPreferencesUtils.getUserInfo(PerTravellerInfoActivity.this),LoginUserEntry.class);
        getIntentData();
        initdata();
        initView();
        initListener();
    }

    private void getIntentData(){
        Intent intent=getIntent();

        contactlist=(List<OrderdetailsEntry.OrdercontactBean>)intent.getSerializableExtra("contactlist");//详情页穿的实体类

        String writeorder=intent.getStringExtra("write_orderid");//下单成功页面传的订单id
        detailsorderid=intent.getStringExtra("detailsorderid");//详情页穿的订单id

        int writepersonnum=intent.getIntExtra("writepersonnum",0);//下单页面传的总人数
        int detailspersonnum=intent.getIntExtra("personnum",0);//详情页穿的总人数

        if (detailsorderid!=null){
            travelpersonnum=detailspersonnum;
            finalorderid=detailsorderid;
        }else {
            travelpersonnum=writepersonnum;
            finalorderid=writeorder;
        }
    }


    private void initdata(){

        dialog.show();
        JSONObject jbody=null;
        try {
            jbody = new JSONObject();
            jbody.put("uid",userinfo.getUserid());
            jbody.put("page",1);//页数
            jbody.put("pagesize",100);//一页数量
            jbody.put("method","QueryContact");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
        dohttpgettravellerinfo(UrlUtils.USER,jbodyB);

    }

    private void initView(){

        TextView mtitlename=(TextView)findViewById(R.id.tv_title_name_change);//标题修改
        mtitlename.setText("完善出游人");
        mtitlename.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_6));
        ImageView mImgMore=(ImageView)findViewById(R.id.img_title_message);//更多图片显示
        mImgMore.setVisibility(View.GONE);
        mAddTraveller=(TextView)findViewById(R.id.tv_title_add_traveller);//新增出游人
        mAddTraveller.setVisibility(View.VISIBLE);
        mBack=(LinearLayout)findViewById(R.id.iv_back);//返回

        LinearLayout mLnperlayouta=(LinearLayout)findViewById(R.id.ln_perlayout_a);//第一部分listview布局
        if (contactlist==null){
            mLnperlayouta.setVisibility(View.GONE);
        }

        mListpera=(ListView)findViewById(R.id.list_pertravell_a);//第一个listview
        mTvselectnum=(TextView)findViewById(R.id.tv_pertraveller_selectnum);//还可以显示选择人数

        mListperb=(ListView)findViewById(R.id.list_pertravell_b);//第二个listview


        if (contactlist!=null) {
            PerTravellerInfoaAdapter adaptera = new PerTravellerInfoaAdapter(PerTravellerInfoActivity.this, contactlist);
            mListpera.setAdapter(adaptera);//第一组listview数据填充
            HomeForthGridView.setListViewHeight(mListpera);
            adaptera.notifyDataSetChanged();
            currentsum=travelpersonnum-contactlist.size();
        }else {
            currentsum=travelpersonnum;
        }


        mTvselectnum.setText(currentsum+"");//设置可选人数

        mSubmitlayout=(LinearLayout)findViewById(R.id.ln_sub_pertraveller);//确认提交

    }
    private void initListener(){
        OnClickListenerImpl listener = new OnClickListenerImpl();
        mBack.setOnClickListener(listener);
        mAddTraveller.setOnClickListener(listener);
        mSubmitlayout.setOnClickListener(listener);
        mListperb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
                PerTravellerInfobAdapter.ViewHolder holder = ( PerTravellerInfobAdapter.ViewHolder) view.getTag();
                // 改变CheckBox的状态
                holder.mImgchecked.toggle();
                // 将CheckBox的选中状况记录下来
                PerTravellerInfobAdapter.getIsSelected().put(position, holder.mImgchecked.isChecked());
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(resultCode){
            case 48:
                handler.sendEmptyMessage(FINISH_RESULT_CODE);

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
                //新增点击跳新增页面
                case  R.id.tv_title_add_traveller:
                    int PERCODE=47;
                    Intent intent=new Intent(PerTravellerInfoActivity.this,TravellerAddInfoActivity.class);
                    intent.putExtra("PERCODE",PERCODE);
                    intent.putExtra("USER_ID",userinfo.getUserid());
                    startActivityForResult(intent,52);

                    break;
                //确认提交
                case R.id.ln_sub_pertraveller:

                    HashMap<Integer, Boolean> map = PerTravellerInfobAdapter.getIsSelected();
                    int str = 0;
                    int selectnum = 0;
                    List listsum = new ArrayList();
//                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < map.size(); i++) {
                        if (map.get(i)) {
                            str ++;
//                            selectnum += Integer.parseInt(bodyinfo.getData().get(i).getContact_id());
//                            sb.append(bodyinfo.getData().get(i).getContact_id());

                            listsum.add(bodyinfo.getData().get(i).getContact_id());

                        }
                    }
                    PerTravellerInfobAdapter.getIsSelected().get("");


                    if (str>currentsum){
                        ToastUtils.show(PerTravellerInfoActivity.this,"请选择正确的人数");
                        return;
                    }

                    Object s="";
                    for (int j=0;j<listsum.size();j++){
                        if (s=="") {
                            s=listsum.get(j);
                        }else {
                            s=s+","+listsum.get(j);
                        }
                    }


                    JSONObject jbody=null;
                    try {
                        jbody = new JSONObject();
                        jbody.put("order_id",finalorderid);
                        jbody.put("contact_list",s);
                        jbody.put("user_id",userinfo.getUserid());


                        jbody.put("method","AddOrderContact");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
                    dohttpaddtravellerperson(UrlUtils.ROUTE_LINE,jbodyB);



//                    Toast.makeText(getApplicationContext(),
//                            "已选中了" + str + "项,总价钱为:" + s, Toast.LENGTH_SHORT).show();


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



    protected  void dohttpaddtravellerperson(String url,JSONObject  paramhash){
        HttpUtils.dopost(url,getApplicationContext(),paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());

                TravellerAddSuccessEntry requestsuccessinfo=gson.fromJson(body, TravellerAddSuccessEntry.class);

                if (requestsuccessinfo.getRes()==1){
                    handler.sendEmptyMessage(FINISH_CODE_A);
                }else {
                    handler.sendEmptyMessage(FINISH_CODE_B);
                }


            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (detailsorderid!=null){
            setResult(66);
            finish();
        }else {
//            Intent intent=new Intent(PerTravellerInfoActivity.this,MainActivity.class);
//            intent.putExtra("id",2);
//            startActivity(intent);
            finish();
        }

    }
}
