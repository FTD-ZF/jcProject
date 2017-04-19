package com.example.host.jsnewmall.view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.host.jsnewmall.utils.DataCleanManager;
import com.example.host.jsnewmall.utils.PhoneUtils;
import com.uu1.nmw.R;

/**
 * Created by host on 2017/4/10.
 */

public class DialogCallPhone {
    public static void showdialog(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = View.inflate(context, R.layout.alertdialog_callphone_view, null);
        builder.setView(view);
        final AlertDialog dialogcleardata = builder.create();
        dialogcleardata.show();
        LinearLayout mTvsubcall=(LinearLayout) view.findViewById(R.id.ln_subcall);//确认
        TextView mTvcontent=(TextView)view.findViewById(R.id.tv_dialog_content);//显示内容
        mTvcontent.setText("客服热线："+ PhoneUtils.PHONE);
        mTvsubcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                PhoneView.onCallphone(context);
                dialogcleardata.dismiss();

            }
        });

    }



    public static void showshopdialog(final Context context, String imgurl, final String phonenum){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = View.inflate(context, R.layout.alertdialog_callphone_view, null);
        builder.setView(view);
        final AlertDialog dialogcleardata = builder.create();
        dialogcleardata.show();
        LinearLayout mTvsubcall=(LinearLayout) view.findViewById(R.id.ln_subcall);//确认
        TextView mTvcontent=(TextView)view.findViewById(R.id.tv_dialog_content);//显示内容
        ImageView mImagecus=(ImageView)view.findViewById(R.id.img_cus_phone);//图片显示

        CenterCircleImageView circleImageView=new CenterCircleImageView(context);
        circleImageView.readBitmapViaVolley(imgurl,mImagecus);

        mTvcontent.setText("客服热线："+ phonenum);
        mTvsubcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                PhoneView.onCallShopPhone(context,phonenum);
                dialogcleardata.dismiss();

            }
        });
    }



    public static void showshopdialogB(final Context context, final String phonenum){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = View.inflate(context, R.layout.alertdialog_callphone_view, null);
        builder.setView(view);
        final AlertDialog dialogcleardata = builder.create();
        dialogcleardata.show();
        LinearLayout mTvsubcall=(LinearLayout) view.findViewById(R.id.ln_subcall);//确认
        TextView mTvcontent=(TextView)view.findViewById(R.id.tv_dialog_content);//显示内容
        mTvcontent.setText("客服热线："+ phonenum);
        mTvsubcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                PhoneView.onCallphone(context);
                dialogcleardata.dismiss();

            }
        });

    }


}
