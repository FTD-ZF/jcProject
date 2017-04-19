package com.example.host.jsnewmall.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.host.jsnewmall.utils.DataCleanManager;
import com.example.host.jsnewmall.utils.SharedPreferencesUtils;
import com.uu1.nmw.R;

/**
 * Created by host on 2017/3/28.
 */

public class PersonSetUpActivity extends BaseActivity {

    private LinearLayout mBack;
    private  String userid;
    private RelativeLayout mRelapersona,mRelapersonb,mRelapersonc,mRelapersond,mRelapersone;
    private LinearLayout mExitUser;
    private TextView mTvacahe;
    private String size="0KB";
    private  AlertDialog dialogcleardata;//清理缓存弹出
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_setup_content);
        try {
            size=  DataCleanManager.getTotalCacheSize(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        getIntentData();
        initView();
        initListener();
    }

    private void getIntentData(){
        Intent intent=getIntent();
        userid=intent.getStringExtra("userid");
    }

    private void initView(){
        TextView mTvTitlte=(TextView)findViewById(R.id.tv_title_name_change);//标题
        mTvTitlte.setText("设置");
        mTvTitlte.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_6));
        ImageView mImgMessage=(ImageView)findViewById(R.id.img_title_message);//更多图案显示
        mImgMessage.setVisibility(View.GONE);

        mBack=(LinearLayout)findViewById(R.id.iv_back);//返回
        mRelapersona=(RelativeLayout)findViewById(R.id.rl_person_set_a);//g个人资料
        mRelapersonb=(RelativeLayout)findViewById(R.id.rl_person_set_b);//修改密码
        mRelapersonc=(RelativeLayout)findViewById(R.id.rl_person_set_c);//g清理缓存
        mRelapersond=(RelativeLayout)findViewById(R.id.rl_person_set_d);//意见反馈
        mRelapersone=(RelativeLayout)findViewById(R.id.rl_person_set_e);//关于我们
        mExitUser=(LinearLayout)findViewById(R.id.ln_exit_user);//退出当前账户
        mTvacahe=(TextView)findViewById(R.id.tv_aache);//缓存


        mTvacahe.setText(size);
    }



    private void initListener(){
        OnClickListenerImpl listener = new OnClickListenerImpl();
        mBack.setOnClickListener(listener);
        mRelapersona.setOnClickListener(listener);
        mRelapersonb.setOnClickListener(listener);
        mRelapersonc.setOnClickListener(listener);
        mRelapersond.setOnClickListener(listener);
        mRelapersone.setOnClickListener(listener);
        mExitUser.setOnClickListener(listener);
    }


    private class OnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.iv_back:
                    setResult(75);
                    finish();
                    break;
                //个人资料
                case R.id.rl_person_set_a:
                    Intent intentsetup=new Intent(PersonSetUpActivity.this, SetUpActivity.class);
                    intentsetup.putExtra("userid",userid);
                    startActivity(intentsetup);
                    break;
                //修改密码
                case R.id.rl_person_set_b:
                    Intent intentchangepwd=new Intent(PersonSetUpActivity.this,ChangePwdActivity.class);
                    intentchangepwd.putExtra("userid",userid);
                    startActivity(intentchangepwd);
                    break;
                //g清理缓存
                case R.id.rl_person_set_c:
                    showcleardatadialog();
                    break;
                //意见反馈
                case R.id.rl_person_set_d:
                    Intent intentsuggest=new Intent(PersonSetUpActivity.this,SuggestionActivity.class);
                    intentsuggest.putExtra("userid",userid);
                    startActivity(intentsuggest);

                    break;
                //关于我们
                case R.id.rl_person_set_e:
                    Intent intentabout=new Intent(PersonSetUpActivity.this,AboutActivity.class);
                    startActivity(intentabout);
                    break;

                //退出当前账户
                case R.id.ln_exit_user:
                    SharedPreferencesUtils.clearUserInfo(PersonSetUpActivity.this);
//                    Intent intent=new Intent(PersonSetUpActivity.this,MainActivity.class);
//                    intent.putExtra("id",2);
//                    startActivity(intent);
                    setResult(76);
                    finish();

                    break;

                default:
                    break;


            }
        }
    }


    @Override
    public void onBackPressed() {
        setResult(75);
        finish();
    }


    /**
     * 清除缓存弹窗
     */
    private void showcleardatadialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(PersonSetUpActivity.this, R.layout.alertdialog_clear_data_view, null);
        builder.setView(view);
        dialogcleardata = builder.create();
        dialogcleardata.show();
        TextView mTvsubclear=(TextView) view.findViewById(R.id.tv_sub_clear);//确认
        TextView mTvcancel=(TextView)view.findViewById(R.id.tv_cancle_clear);//取消
        mTvsubclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataCleanManager.clearAllCache(getApplicationContext());
                String sizeA=null;
                try {
                    sizeA=  DataCleanManager.getTotalCacheSize(getApplicationContext());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mTvacahe.setText(sizeA);

                dialogcleardata.dismiss();

            }
        });

        mTvcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogcleardata.dismiss();
            }
        });

    }


}
