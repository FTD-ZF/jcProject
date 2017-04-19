package com.example.host.jsnewmall.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uu1.nmw.R;
import com.example.host.jsnewmall.adapter.GridSelecttypeAdapter;
import com.example.host.jsnewmall.model.JsonmModel;
import com.example.host.jsnewmall.model.PostInfoSuccessEntry;
import com.example.host.jsnewmall.utils.Base64Utils;
import com.example.host.jsnewmall.utils.HttpUtils;
import com.example.host.jsnewmall.utils.JsonUtils;
import com.example.host.jsnewmall.utils.ToastUtils;
import com.example.host.jsnewmall.utils.UrlUtils;
import com.example.host.jsnewmall.view.LoadingDialog;
import com.google.gson.Gson;


import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by host on 2017/3/21.
 */

public class TravellerEditInfoActivity extends BaseActivity {
    private LinearLayout mBack;
    private SimpleDateFormat mSimpleTime;
    private String nTime;
    Gson gson=new Gson();
    private LoadingDialog dialog;
    private String contactid,sextype,truename,phonenum,identtype,identtypenum;
    private RelativeLayout mEditSexlayout;
    private TextView mTvEditsex;
    private EditText mEtname;
    private EditText mEtphone;
    private RelativeLayout mEditIdentlayout;
    private TextView mTvIdenttypename;
    private EditText mEtidenty;
    private LinearLayout mSubmit;
    private int sexcode=-1;
    private static final int FINISH_CODE=100;
    private static final int FINISH_CODE_A=103;
    private static final int FINISH_CODE_B=102;
    private static final int FINISH_CODE_C=104;
    private Dialog mDialogSex;
    private TextView mTvmale;
    private TextView mTvfemale;
    private String sextext="";
    private String typename="";
    private List<String> mDatas = new ArrayList<String>(Arrays.asList("身份证",
            "护照", "驾驶证", "军人证","回乡证","港澳通行证","台胞证","其他"));
    //@"身份证",@"护照",@"驾驶证",@"军人证",@"回乡证",@"港澳通行证",@"台胞证",@"其他"

    private Dialog mDialogtype;
    private int identitytype=-1;
    private String userid;
    private PostInfoSuccessEntry rescode;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FINISH_CODE:

                    mTvEditsex.setText(sextext);
                    mTvEditsex.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_6));
                    mDialogSex.dismiss();
                    break;
                case FINISH_CODE_A:
                    mTvIdenttypename.setText(typename);
                    mTvIdenttypename.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_6));
                    mDialogtype.dismiss();

                    break;
                case FINISH_CODE_B:

                    setResult(55);

                    dialog.dismiss();
                    ToastUtils.show(TravellerEditInfoActivity.this,"保存信息成功");
                    finish();
                    break;
                case FINISH_CODE_C:
                    dialog.dismiss();
//                    ToastUtils.show(TravellerEditInfoActivity.this,rescode.getMsg());
                    break;

                default:
                    break;

            };
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traveller_edit_content);
        Date d=new Date();
        mSimpleTime=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        nTime=mSimpleTime.format(d);
        dialog=new LoadingDialog(TravellerEditInfoActivity.this);

        initGetData();
        initView();
        initListener();
    }

    private void initGetData(){
        Intent intent=getIntent();
        contactid=intent.getStringExtra("contactid");
        sextype=intent.getStringExtra("sex");
        truename=intent.getStringExtra("name");
        phonenum=intent.getStringExtra("phone");
        identtype=intent.getStringExtra("type");
        identtypenum=intent.getStringExtra("typenum");
        userid=intent.getStringExtra("userid");

        sexcode=Integer.parseInt(sextype);
        identitytype=Integer.parseInt(identtype);
    }
    private void initView(){
        TextView mtitlename=(TextView)findViewById(R.id.tv_title_name_change);//标题修改
        mtitlename.setText("修改旅客信息");
        mtitlename.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_6));
        ImageView mImgMore=(ImageView)findViewById(R.id.img_title_message);//更多图片显示
        mImgMore.setVisibility(View.GONE);

        mBack=(LinearLayout)findViewById(R.id.iv_back);//返回

        mEditSexlayout=(RelativeLayout)findViewById(R.id.rl_edit_sex_view);//性别选择的点击
        mTvEditsex=(TextView)findViewById(R.id.tv_traveller_edit_sex);//性别显示
        if (sextype.equals("0")){
            mTvEditsex.setText("女");
        }else if (sextype.equals("1")){
            mTvEditsex.setText("男");
        }else if (sextype.equals("2")){
            mTvEditsex.setText("儿童");
        }


        mEtname=(EditText)findViewById(R.id.et_traveller_edit_name);//姓名显示及编辑
        mEtname.setText(truename);
        mEtphone=(EditText)findViewById(R.id.et_traveller_edit_phone);//电话显示
        mEtphone.setText(phonenum);

        mEditIdentlayout=(RelativeLayout)findViewById(R.id.rl_edit_type_view);//证件类型点击选择
        mTvIdenttypename=(TextView)findViewById(R.id.tv_traveller_edit_type);//证件名字显示
        mTvIdenttypename.setText(getIdenttypename(identtype));
        mEtidenty=(EditText)findViewById(R.id.et_traveller_edit_identitynum);//证件号显示及修改
        mEtidenty.setText(identtypenum);

        mSubmit=(LinearLayout)findViewById(R.id.ln_save_edit_traveller);//保存提交旅客信息


    }

    private void initListener(){
        OnClickListenerImpl listener = new OnClickListenerImpl();
        mBack.setOnClickListener(listener);
        mEditSexlayout.setOnClickListener(listener);
        mEditIdentlayout.setOnClickListener(listener);
        mSubmit.setOnClickListener(listener);
    }

    private class OnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_back:
                    finish();
                    break;
                //性别点击选择
                case R.id.rl_edit_sex_view:
                    showdialogsexselect();
                    break;

                //证件类型选择
                case R.id.rl_edit_type_view:
                    showdialogtypeselect();
                    break;
                //提交信息
                case R.id.ln_save_edit_traveller:

                    String etfinalname=mEtname.getText().toString().trim();
                    String etfinalphone=mEtphone.getText().toString().trim();
                    String etfinalidenty=mEtidenty.getText().toString().trim();
                    if (etfinalname.equals("")||etfinalphone.equals("")||etfinalidenty.equals("")){
                        ToastUtils.show(TravellerEditInfoActivity.this,"请输入信息");
                        return;
                    }
                    if (sexcode==-1){
                        ToastUtils.show(TravellerEditInfoActivity.this,"请选择性别");
                        return;
                    }
                    if (identitytype==-1){
                        ToastUtils.show(TravellerEditInfoActivity.this,"请选择证件类型");
                        return;
                    }

                    if (etfinalname.equals(truename)&&etfinalphone.equals(phonenum)&&etfinalidenty.equals(identtypenum)
                            &&sexcode==Integer.parseInt(sextype)&&identitytype==Integer.parseInt(identtype)){

                        handler.sendEmptyMessage(FINISH_CODE_B);
                        return;
                    }




                    JSONObject jbody=null;
                    try {
                        jbody = new JSONObject();
                        jbody.put("contact_id",contactid);
                        jbody.put("uid",userid);
                        jbody.put("true_name",etfinalname);
                        jbody.put("contact_phone",etfinalphone);
                        jbody.put("paper_type",identitytype);
                        jbody.put("paper_num",etfinalidenty);
                        jbody.put("sex",sexcode);
                        jbody.put("method","EditContact");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
                    dohttprequestinfo(UrlUtils.USER,jbodyB);
                    break;
                default:
                    break;

            }
        }
    }

    protected  void dohttprequestinfo(String url,JSONObject  paramhash){
        dialog.show();
        HttpUtils.dopost(url,getApplicationContext(),paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());

                rescode=gson.fromJson(body,PostInfoSuccessEntry.class);
                int finalcode=rescode.getRes();
                if (finalcode==1){
                    handler.sendEmptyMessage(FINISH_CODE_B);
                }else {
                    handler.sendEmptyMessage(FINISH_CODE_C);

                }



            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private String getIdenttypename(String position){
        String typename=null;
        if (position.equals("1")){
            typename="身份证";

        }else if (position.equals("2")){
            typename="护照";

        }else if (position.equals("3")){
            typename="驾驶证";

        }else if (position.equals("4")){
            typename="军人证";

        }else if (position.equals("5")){
            typename="回乡证";

        }else if (position.equals("6")){
            typename="港澳通行证";

        }else if (position.equals("7")){
            typename="台胞证";

        }else if (position.equals("8")){
            typename="其他";

        }
        return typename;
    }


    private void showdialogsexselect() {

        View view = LayoutInflater.from(getApplicationContext()).inflate(
                R.layout.dialog_sex_select, null);
        mDialogSex = new Dialog(TravellerEditInfoActivity.this, R.style.Dialog);
        mDialogSex.setContentView(view);
        setDialogHeight(mDialogSex);//设置弹出框高度
        TextView  mCancel = (TextView) view.findViewById(R.id.tv_cancel_sex);// 取消
        mCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mDialogSex.dismiss();
            }
        });


        mTvmale=(TextView)view.findViewById(R.id.tv_add_male);//男
        mTvfemale=(TextView)view.findViewById(R.id.tv_add_female);//女

        if (sexcode==1){
            mTvmale.setTextColor(getApplicationContext().getResources().getColor(R.color.title_orange));
            mTvfemale.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_6));
        }else if (sexcode==0){
            mTvfemale.setTextColor(getApplicationContext().getResources().getColor(R.color.title_orange));
            mTvmale.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_6));
        }
        mTvmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sexcode=1;
                sextext="男";
                handler.sendEmptyMessage(FINISH_CODE);

            }
        });

        mTvfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sexcode=0;
                sextext="女";
                handler.sendEmptyMessage(FINISH_CODE);

            }
        });


    }







    private void showdialogtypeselect(){


        final GridSelecttypeAdapter mGridtypeadapter = new GridSelecttypeAdapter(
                TravellerEditInfoActivity.this, mDatas);
        View view = LayoutInflater.from(getApplicationContext()).inflate(
                R.layout.dialog_select_traveller_type_view, null);
        mDialogtype = new Dialog(TravellerEditInfoActivity.this, R.style.Dialog);
        mDialogtype.setContentView(view);
        setDialogHeight(mDialogtype);//设置弹出框高度




        TextView  mCancel = (TextView) view.findViewById(R.id.tv_cancel_type);// 取消
        mCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mDialogtype.dismiss();

            }
        });


        //@"身份证",@"护照",@"驾驶证",@"军人证",@"回乡证",@"港澳通行证",@"台胞证",@"其他"
        GridView mGridType = (GridView) view
                .findViewById(R.id.gv_traveller_type);// 展示条目
        mGridType.setAdapter(mGridtypeadapter);
        mGridType.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position==0){
                    typename="身份证";
                    identitytype=1;
                }else if (position==1){
                    typename="护照";
                    identitytype=2;
                }else if (position==2){
                    typename="驾驶证";
                    identitytype=3;
                }else if (position==3){
                    typename="军人证";
                    identitytype=4;
                }else if (position==4){
                    typename="回乡证";
                    identitytype=5;
                }else if (position==5){
                    typename="港澳通行证";
                    identitytype=6;
                }else if (position==6){
                    typename="台胞证";
                    identitytype=7;
                }else if (position==7){
                    typename="其他";
                    identitytype=8;
                }
                mGridtypeadapter.setselectiontypenum(position);
                mGridtypeadapter.notifyDataSetChanged();
                handler.sendEmptyMessage(FINISH_CODE_A);
            }
        });
    }





    private void setDialogHeight(Dialog dialog){
        // 设置向下弹框的宽高
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        WindowManager w = this.getWindowManager();
        Display dw = w.getDefaultDisplay();
        dialog.show();
        lp.height = 440;
        lp.width = dw.getWidth();
        dialogWindow.setAttributes(lp);
    }



}
