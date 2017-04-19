package com.example.host.jsnewmall.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.host.jsnewmall.view.DialogCallPhone;
import com.example.host.jsnewmall.view.PhoneView;
import com.uu1.nmw.R;


/**
 * Created by host on 2017/3/28.
 */

//签证
public class QianZhengActivity extends BaseActivity {
    private LinearLayout mBackTitle;
    private RelativeLayout mRlBtnsub;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qianzheng_content);
        initView();
        initListener();
    }
    private void initView(){

        TextView mTitle=(TextView) findViewById(R.id.tv_title_name_change);//标题设置
        mTitle.setText("签证");
        mBackTitle=(LinearLayout) findViewById(R.id.iv_back);//返回按钮
        ImageView mImgmessage=(ImageView)findViewById(R.id.img_title_message);//消息显示
        mImgmessage.setVisibility(View.GONE);
        ImageView imgcall=(ImageView)findViewById(R.id.img_title_call);//电话显示
        imgcall.setVisibility(View.GONE);
        mRlBtnsub=(RelativeLayout)findViewById(R.id.tv_submit_qianz);//点击咨询

    }
    private void initListener(){

        OnClickListenerImpl listener = new OnClickListenerImpl();
        mBackTitle.setOnClickListener(listener);
        mRlBtnsub.setOnClickListener(listener);

    }

    private class OnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.iv_back:
                    finish();
                    break;
                //咨询
                case R.id.tv_submit_qianz:
                    DialogCallPhone.showdialog(QianZhengActivity.this);
                    break;

                default:
                    break;
            }
        }
    }
}
