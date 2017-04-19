package com.example.host.jsnewmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uu1.nmw.R;


/**
 * Created by host on 2017/2/28.
 */

/**
 * 支付成功页面
 */

public class PaySuccessActivity extends AppCompatActivity {
    private LinearLayout mBack;
    private TextView mTvWatchOrder;
    private TextView mTvbackmain;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_success_content);
        initView();
        initListener();

    }

    private void initView() {
        TextView mtitleview = (TextView) findViewById(R.id.tv_title_name_change);
        mtitleview.setText(getApplicationContext().getResources().getString(R.string.pay_content_f));
        mBack = (LinearLayout) findViewById(R.id.iv_back);//返回
        mTvWatchOrder=(TextView)findViewById(R.id.pay_submit_a);//查看订单
        mTvbackmain=(TextView)findViewById(R.id.pay_submit_b);//返回首页
    }

    private void initListener() {
        OnClickListenerImpl listener = new OnClickListenerImpl();
        mBack.setOnClickListener(listener);
        mTvWatchOrder.setOnClickListener(listener);
        mTvbackmain.setOnClickListener(listener);
    }

    private class OnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_back:
                    Intent intentfinish=new Intent(PaySuccessActivity.this,MainActivity.class);
                    intentfinish.putExtra("id",2);
                    startActivity(intentfinish);
                    finish();
                    break;
                //查看订单
                case R.id.pay_submit_a:
                    Intent intenta=new Intent(PaySuccessActivity.this,AllOrderActivity.class);
                    startActivity(intenta);

                    break;

                case R.id.pay_submit_b:

//                    if (SharedPreferencesUtils.doSaveIndexItem(OrderSuccessActivity.this,0)) {
//                        MainActivity.mRadioGroup.check(MainActivity.mRadioGroup.getChildAt(1).getId());
////                    }
                    Intent intent=new Intent(PaySuccessActivity.this,MainActivity.class);
                    intent.putExtra("id",1);
                    startActivity(intent);
                    finish();

                    break;
                default:
                    break;
            }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentfinish=new Intent(PaySuccessActivity.this,MainActivity.class);
        intentfinish.putExtra("id",2);
        startActivity(intentfinish);
        finish();
    }
}
