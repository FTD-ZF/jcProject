package com.example.host.jsnewmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.auth.AlipaySDK;
import com.alipay.sdk.util.H5PayResultModel;


import com.example.host.jsnewmall.bean.PayResult;
import com.example.host.jsnewmall.model.JsonmModel;

import com.example.host.jsnewmall.model.OrderdetailsEntry;
import com.example.host.jsnewmall.model.ZhifubaoinfoEntry;
import com.example.host.jsnewmall.model.ZhifubaoinfoaEntry;
import com.example.host.jsnewmall.utils.AliPayUtils;
import com.example.host.jsnewmall.utils.Base64Utils;
import com.example.host.jsnewmall.utils.HttpUtils;
import com.example.host.jsnewmall.utils.JsonUtils;
import com.example.host.jsnewmall.utils.SignUtils;
import com.example.host.jsnewmall.utils.ToastUtils;
import com.example.host.jsnewmall.utils.UrlUtils;
import com.example.host.jsnewmall.view.LoadingDialog;
import com.google.gson.Gson;
import com.uu1.nmw.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by host on 2017/3/2.
 */

public class PayActivity extends BaseActivity {
    private TextView mTvDuoci;
    private LinearLayout mDuociLayout;
    private TextView mTvdismissduoci;
    private EditText mEtMoreMoney;
    private LinearLayout mBack;
//    private RelativeLayout mRlweixin;
    private RelativeLayout mRlzhifubao;
//    private ImageView mImgWeixin;
    private ImageView mImgZhifubao;
    private String titlename;
//    private CenterOrderaEntry.OrderlistBean bodyinfoa;
    private String godate;
    private int crqtydetails = 0;
    private int rtqtydetails = 0;
    private int lrqtydetails = 0;
    private int xsqtydetails = 0;
    private String notpaidmoney;
    private TextView mTvpaymoney;
    private String mOrderid;

    private SimpleDateFormat mSimpleTime;
    private String nTime;
    Gson gson=new Gson();
    private OrderdetailsEntry mOrderdetailsInfo;
    private TextView crqty;
    private TextView rtqty;
    private TextView lrqty;
    private TextView xsqty;
    private int crqtydetailsnum = 0;
    private int rtqtydetailsnum = 0;
    private int lrqtydetailsnum = 0;
    private int xsqtydetailsnum = 0;
    private static final int FINISH_CODE=100;
//    private static final int FINISH_CODE_A=102;
    private static final int GET_INFO=101;
    private static final int SUB_CODE=103;
    private TextView mTitle;
    private TextView mTvGoDate;
    private LoadingDialog dialog;
    private TextView mTvallmoney;
    private OrderdetailsEntry.OrderdataBean orderdataBean;
    private double changemoney;
    private String intentordercode;
    private ZhifubaoinfoEntry mZhifubaoInfo;
    private ZhifubaoinfoaEntry mZhifubaoInfoa;
    private final static int SDK_PAY_FLAG = 1;
    private LinearLayout mSubfinalmoney;
    // 商户私钥，pkcs8格式
    public static  String RSA_PRIVATE = "";

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FINISH_CODE:
                    if (mOrderid!=null) {
                        dialog.dismiss();
                        initgethttpdata();
                    }

                    mEtMoreMoney.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        }
                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        }
                        @Override
                        public void afterTextChanged(Editable editable) {
                            if ((mEtMoreMoney.getText().toString().trim()).equals("")){
                                if (mOrderid==null){
                                    mTvallmoney.setText(notpaidmoney);
                                    changemoney=Double.parseDouble(notpaidmoney);
                                }else{
                                    String moneybeana=orderdataBean.getOrder_notpaid_money();
                                    mTvallmoney.setText(moneybeana);
                                    changemoney=Double.parseDouble(moneybeana);
                                }
                            }else {
                                    mTvallmoney.setText(mEtMoreMoney.getText().toString().trim());
                            }
                        }
                    });

                    initgetalipayinfo();
                    initListener();


                    break;



                case GET_INFO:


//                    ToastUtils.show(PayActivity.this,mZhifubaoInfo.getNotify_url()+"");

                    break;

                case SDK_PAY_FLAG:
                    PayResult payResult = new PayResult((String) msg.obj);
                    String resultInfo = "";
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        ToastUtils.show(PayActivity.this, "支付成功");
                        Intent  intentsuccess=new Intent(PayActivity.this,PaySuccessActivity.class);
                        startActivity(intentsuccess);
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            ToastUtils.show(PayActivity.this, "支付结果确认中");

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            ToastUtils.show(PayActivity.this, "支付失败");

                        }
                    }
                    break;
                case SUB_CODE:

                    RSA_PRIVATE=(mZhifubaoInfoa.getData().getPay_config_format().getRsa_private_key().trim()).replace("\n","");

                    String orderinfo= AliPayUtils.getOrderInfo(mZhifubaoInfo.getPartner().toString(),
                            mZhifubaoInfo.getSeller_id().toString(),
                            mZhifubaoInfo.getOut_trade_no().toString(),
                            mZhifubaoInfo.getSubject().toString(),
                            mZhifubaoInfo.getBody().toString(),
                            String.valueOf(changemoney),
                            mZhifubaoInfo.getNotify_url().toString(),
                            mZhifubaoInfo.getService().toString());

//                    final StringBuffer payParams = new StringBuffer();
//                    final StringBuffer payParamsa = new StringBuffer();
//                    payParams.append("partner=" + mZhifubaoInfo.getPartner().toString());
//                    payParams.append("&seller_id=" + mZhifubaoInfo.getSeller_id().toString());
//                    payParams.append("&out_trade_no=" + mZhifubaoInfo.getOut_trade_no().toString());
//                    payParams.append("&subject=" + mZhifubaoInfo.getSubject().toString());
//                    payParams.append("&body=" + mZhifubaoInfo.getBody().toString());
//                    payParams.append("&total_fee=" + String.valueOf(changemoney));
//                    payParams.append("&notify_url=" + mZhifubaoInfo.getNotify_url().toString());
//                    payParams.append("&service=" + mZhifubaoInfo.getService().toString());
//                    payParams.append("&payment_type=" + mZhifubaoInfo.getPayment_type().toString());
//                    payParams.append("&_input_charset="+mZhifubaoInfo.get_input_charset().toString());






                    String signa=sign(String.valueOf(orderinfo));

//                    payParams.append("&sign=" + ((mZhifubaoInfoa.getData().getPay_config_format().getRsa_private_key().trim()).replace("\n","")).replace("/",""));




                    Log.d("你好",signa);


                    try {
                        /**
                         * 仅需对sign 做URL编码
                         */
                        signa = URLEncoder.encode(signa, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    /**
                     * 完整的符合支付宝参数规范的订单信息
                     */
                    final String payInfo = orderinfo + "&sign=\"" + signa + "\"&" + getSignType();




                    Runnable payRunnable = new Runnable() {
                        @Override
                        public void run() {
                            PayTask alipay = new PayTask(PayActivity.this);
                            String result = alipay.pay(payInfo, true);
                            Message msg = new Message();
                            msg.what = SDK_PAY_FLAG;
                            msg.obj = result;
                            handler.sendMessage(msg);
                        }
                    };
                    // 必须异步调用
                    Thread payThread = new Thread(payRunnable);
                    payThread.start();
                    break;
                default:
                    break;

            };
        }
    };



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_content);

        dialog=new LoadingDialog(this);
        Date d=new Date();
        mSimpleTime=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        nTime=mSimpleTime.format(d);

        initGetData();
        initView();


    }
    private void initGetData(){
        Intent intent=getIntent();
        titlename=intent.getStringExtra("title");
        godate=intent.getStringExtra("godate");
        crqtydetails=intent.getIntExtra("crqtydetails",0);
        rtqtydetails=intent.getIntExtra("rtqtydetails",0);
        lrqtydetails=intent.getIntExtra("lrqtydetails",0);
        xsqtydetails=intent.getIntExtra("xsqtydetails",0);
        notpaidmoney=intent.getStringExtra("notpaidmoney");
        intentordercode=intent.getStringExtra("ordercode");



        mOrderid=intent.getStringExtra("orderid");//从列表获取的id

        if (mOrderid!=null){
            dialog.show();
            JSONObject jbody=null;
            try {
                jbody = new JSONObject();
                jbody.put("id",mOrderid);
                jbody.put("method","QueryOrderInfo");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
            dohttpOrderdetailsInfoa(UrlUtils.ROUTE_LINE,jbodyB);
        }




//        bodyinfoa =(CenterOrderaEntry.OrderlistBean)intent.getSerializableExtra("bodyinfo");


    }


    private void initView(){
        mBack=(LinearLayout)findViewById(R.id.iv_back);
        TextView mTvtitle=(TextView)findViewById(R.id.tv_title_name_change);//标题更名
        mTvtitle.setText(getApplicationContext().getResources().getString(R.string.order_content_l));
        mDuociLayout=(LinearLayout) findViewById(R.id.ln_pay_content_a);//是否展开多次输入金额
        mTvDuoci=(TextView)findViewById(R.id.tv_pay_duoci);//确认多次支付按钮
        mTvdismissduoci=(TextView)findViewById(R.id.tv_pay_duoci_dismiss);//取消多次支付
        mEtMoreMoney=(EditText)findViewById(R.id.et_order_money);//获取多次支付金额
//        mRlweixin=(RelativeLayout)findViewById(R.id.rl_weixin_layout);//微信支付
        mRlzhifubao=(RelativeLayout)findViewById(R.id.rl_zhifubao_layout);//支付宝支付
//        mImgWeixin=(ImageView)findViewById(R.id.img_weixin_checked);//微信支付图片
//        mImgZhifubao=(ImageView)findViewById(R.id.img_zhifubao_checked);//支付宝图片
        mTvpaymoney=(TextView)findViewById(R.id.tv_pay_details_a);//需支付金额
        mTvallmoney=(TextView)findViewById(R.id.tv_order_all_money);//下面付款金额显示
        mSubfinalmoney=(LinearLayout)findViewById(R.id.ln_sub_finalpay);//确认支付


        mTitle=(TextView)findViewById(R.id.tv_order_content_a);//出游标题名字
        mTvGoDate=(TextView)findViewById(R.id.tv_order_date);//出游日期
        crqty=(TextView)findViewById(R.id.tv_crqty_pay);//成人
        rtqty=(TextView)findViewById(R.id.tv_rtqty_pay);//儿童
        lrqty=(TextView)findViewById(R.id.tv_lrqty_pay);//老人
        xsqty=(TextView)findViewById(R.id.tv_xsqty_pay);//学生

        if (mOrderid==null){
            mTitle.setText(titlename);
            mTvGoDate.setText(godate);
            mTvpaymoney.setText(notpaidmoney);
            mTvallmoney.setText(notpaidmoney);
            changemoney=Double.parseDouble(notpaidmoney);

            if (crqtydetails!=0){
                crqty.setVisibility(View.VISIBLE);
                crqty.setText("成人"+crqtydetails);
            }
            if (rtqtydetails!=0){
                rtqty.setVisibility(View.VISIBLE);
                rtqty.setText("儿童"+rtqtydetails);
            }
            if (lrqtydetails!=0){
                lrqty.setVisibility(View.VISIBLE);
                lrqty.setText("老人"+lrqtydetails);
            }
            if (xsqtydetails!=0){
                xsqty.setVisibility(View.VISIBLE);
                xsqty.setText("学生"+xsqtydetails);
            }

            handler.sendEmptyMessage(FINISH_CODE);
        }
    }


    private void initListener(){
        OnClickListenerImpl listener = new OnClickListenerImpl();
        mTvDuoci.setOnClickListener(listener);
        mTvdismissduoci.setOnClickListener(listener);
        mBack.setOnClickListener(listener);
//        mRlweixin.setOnClickListener(listener);
        mRlzhifubao.setOnClickListener(listener);
        mSubfinalmoney.setOnClickListener(listener);
    }

    private class OnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.iv_back:
                    finish();
                    break;

                case R.id.tv_pay_duoci:
                    mDuociLayout.setVisibility(View.VISIBLE);
                    break;
                case R.id.tv_pay_duoci_dismiss:
                    mDuociLayout.setVisibility(View.GONE);
                    mEtMoreMoney.setText("");
                    if (mOrderid!=null){
                        String moneyaaa=orderdataBean.getOrder_notpaid_money();
                        double moneybbb=Double.parseDouble(moneyaaa);
                        mTvallmoney.setText(moneyaaa);

                        changemoney=moneybbb;
                    }else{

                        mTvallmoney.setText(notpaidmoney);

                        changemoney=Double.parseDouble(notpaidmoney);
                    }

                    break;

                case R.id.ln_sub_finalpay:

                    String etnum=mEtMoreMoney.getText().toString().trim();
                    if (etnum.equals("")){
                        if (mOrderid==null){
                            changemoney=Double.parseDouble(notpaidmoney);
                        }else{
                            String moneybeana=orderdataBean.getOrder_notpaid_money();
                            changemoney=Double.parseDouble(moneybeana);
                        }
                    }else{
                        changemoney=Double.parseDouble(etnum);
                    }


                    ToastUtils.show(PayActivity.this,changemoney+"");
                    handler.sendEmptyMessage(SUB_CODE);
                    break;
                default:
                    break;
            }

        }
    }



    protected  void dohttpOrderdetailsInfoa(String url,JSONObject  paramhash){
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


    private void initgethttpdata(){
        OrderdetailsEntry.OrderteamBean orderteam =mOrderdetailsInfo.getOrderteam();
        List<OrderdetailsEntry.OrderproductBean> orderproductBean=mOrderdetailsInfo.getOrderproduct();
        orderdataBean=mOrderdetailsInfo.getOrderdata();
        mTitle.setText(orderteam.getTeam_title());
        mTvGoDate.setText(orderteam.getGodate());
        mTvpaymoney.setText(orderdataBean.getOrder_notpaid_money());
        mTvallmoney.setText(orderdataBean.getOrder_notpaid_money());


        changemoney=Double.parseDouble(orderdataBean.getOrder_notpaid_money());


        for (int i=0;i<orderproductBean.size();i++){
            OrderdetailsEntry.OrderproductBean minfo =orderproductBean.get(i);
            int crqtynum=Integer.parseInt(minfo.getCrqty());
            int rtqtynum=Integer.parseInt(minfo.getRtqty());
            int lrqtynum=Integer.parseInt(minfo.getLrqty());
            int xsqtynum=Integer.parseInt(minfo.getXsqty());
            crqtydetailsnum+=crqtynum;
            rtqtydetailsnum+=rtqtynum;
            lrqtydetailsnum+=lrqtynum;
            xsqtydetailsnum+=xsqtynum;
        }



        if (crqtydetailsnum!=0){
            crqty.setVisibility(View.VISIBLE);
            crqty.setText("成人"+crqtydetailsnum);
        }
        if (rtqtydetailsnum!=0){
            rtqty.setVisibility(View.VISIBLE);
            rtqty.setText("儿童"+crqtydetailsnum);
        }
        if (lrqtydetailsnum!=0){
            lrqty.setVisibility(View.VISIBLE);
            lrqty.setText("老人"+crqtydetailsnum);
        }
        if (xsqtydetailsnum!=0){
            xsqty.setVisibility(View.VISIBLE);
            xsqty.setText("学生"+xsqtydetailsnum);
        }
    }



    private void initgetalipayinfo(){
        String ordercodea=null;

        if (mOrderid!=null){
            ordercodea=orderdataBean.getOrder_code();
        }else{

            ordercodea= intentordercode;
        }


        JSONObject jbody=null;
        try {
            jbody = new JSONObject();
            jbody.put("order_coder",ordercodea);//	订单编号
            jbody.put("order_type",1);//订单类型（1 景点 2 邮轮）
            jbody.put("pay_type",21);//支付类型（21=支付宝 22=银联支付 23=财付通 24=微信支付 25=环迅 26=威富通微信支付）
            jbody.put("method","GetPayData_app");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
        dohttpgetpayinfo(UrlUtils.PAY+"?payment_type=APPalipay"+"&order_code="+ordercodea+"&order_source=1",null);
        dohttpgetpayinfoa(UrlUtils.APP_URL,jbodyB);
    }


    protected  void dohttpgetpayinfo(String url,JSONObject  paramhash){
        HttpUtils.dopost(url,getApplicationContext(),paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

//                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
//                String body= Base64Utils.getFromBase64(homeinfoa.getBody());
                mZhifubaoInfo=gson.fromJson(result, ZhifubaoinfoEntry.class);
                handler.sendEmptyMessage(GET_INFO);

            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }

    protected  void dohttpgetpayinfoa(String url,JSONObject  paramhash){
        HttpUtils.dopost(url,getApplicationContext(),paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());
                mZhifubaoInfoa=gson.fromJson(body, ZhifubaoinfoaEntry.class);
                handler.sendEmptyMessage(GET_INFO);

            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }



    private String getSignType() {
        return "sign_type=\"RSA\"";
    }


    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content
     *            待签名订单信息
     */
    private String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }


    //http://open.4008289828.com/pay/pay.php?payment_type=APPalipay&order_code=w170308142240160954&order_source=1
}
