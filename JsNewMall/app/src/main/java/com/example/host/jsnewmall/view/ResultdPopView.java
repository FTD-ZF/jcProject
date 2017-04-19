package com.example.host.jsnewmall.view;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.host.jsnewmall.adapter.PopFifthAdapter;
import com.example.host.jsnewmall.adapter.PopForthAdapter;
import com.example.host.jsnewmall.adapter.PopSixthAdapter;
import com.example.host.jsnewmall.adapter.PopThirdAdapter;
import com.example.host.jsnewmall.model.SearchkeywordEntry;
import com.example.host.jsnewmall.utils.DateUtils;
import com.example.host.jsnewmall.utils.ToastUtils;
import com.uu1.nmw.R;
import com.example.host.jsnewmall.adapter.ResultcPopAdapter;
import com.example.host.jsnewmall.adapter.ResultdPopAdapter;
import com.example.host.jsnewmall.model.ResultdInfo;

import java.util.Calendar;
import java.util.List;

/**
 * Created by host on 2017/2/23.
 */

public class ResultdPopView extends PopupWindow {
    private Context mContext;
//    private List<ResultdInfo> mData;
    private View contentView;
    private ListView mListSearchb;
    private ResultdPopAdapter mAdapter;

    private PopupWindow popupWindowd;
    private SearchkeywordEntry mBodyinfo;
    private String gotraffica;
    private String backtraffica;
    private String subjectname;
    private String startprice;
    private String endprice;
    private String leavecityid;
    private Calendar calendar;
//    private TextView mTvDatea;
//    private TextView mTvDateb;
    private String godate="";
    private String backdate="";
    private String searchdate;


    public interface CallBackUi{
        void onRequestUi(String searchdate,String pricea,String priceb,String subjectname,String gotraffica,String backtraffica,String leavecity);
    }
    public ResultdPopView(final Activity context, final SearchkeywordEntry mSearchkeywordinfo, final CallBackUi callBackUi) {
        this.mContext=context;
        this.mBodyinfo=mSearchkeywordinfo;

        gotraffica=mSearchkeywordinfo.getGotrafficdata().get(0).getAttrdetailvalue();
        backtraffica=mSearchkeywordinfo.getBacktrafficdata().get(0).getAttrdetailvalue();
        subjectname=mSearchkeywordinfo.getProductdata().get(0).getAttrdetailvalue();


        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.popwindow_result_b, null);





        LinearLayout mLnpoplayout=(LinearLayout)contentView.findViewById(R.id.ln_popwindow_layout_a);//布局
        TextView mTvCancel=(TextView)contentView.findViewById(R.id.tv_popwindow_cancel);//重置
        TextView mTvSubmit=(TextView)contentView.findViewById(R.id.tv_popwindow_submit);//完成


//        mTvDatea=(TextView)contentView.findViewById(R.id.tv_pop_date_a);//开始日期
//        mTvDateb=(TextView)contentView.findViewById(R.id.tv_pop_date_b);//结束日期

        final EditText mEtdatea=(EditText)contentView.findViewById(R.id.et_pop_date_a);//获取第一个价格
        final EditText mEtdateb=(EditText)contentView.findViewById(R.id.et_pop_date_b);//获取第一个价格

        GridView mGvpopview=(GridView)contentView.findViewById(R.id.gv_popview_layout_a);//线路特色
        final PopThirdAdapter thirdadapter=new PopThirdAdapter(mContext,mBodyinfo);
        mGvpopview.setAdapter(thirdadapter);
        HomeForthGridView.setListViewHeightBasedOnChildren(mGvpopview);
        mGvpopview.deferNotifyDataSetChanged();


        GridView mGvpopviewb=(GridView)contentView.findViewById(R.id.gv_popview_layout_b);//去程交通
        final PopForthAdapter forthadapter=new PopForthAdapter(mContext,mBodyinfo);
        mGvpopviewb.setAdapter(forthadapter);
        HomeForthGridView.setListViewHeightBasedOnChildren(mGvpopviewb);
        mGvpopviewb.deferNotifyDataSetChanged();


        GridView mGvpopviewc=(GridView)contentView.findViewById(R.id.gv_popview_layout_c);//返程交通
        final PopFifthAdapter fifthadapter=new PopFifthAdapter(mContext,mBodyinfo);
        mGvpopviewc.setAdapter(fifthadapter);
        HomeForthGridView.setListViewHeightBasedOnChildren(mGvpopviewc);
        mGvpopviewc.deferNotifyDataSetChanged();


        GridView mGvpopviewd=(GridView)contentView.findViewById(R.id.gv_popview_layout_d);//出发城市
        final PopSixthAdapter sixthadapter=new PopSixthAdapter(mContext,mBodyinfo);
        mGvpopviewd.setAdapter(sixthadapter);
        HomeForthGridView.setListViewHeightBasedOnChildren(mGvpopviewd);
        mGvpopviewd.deferNotifyDataSetChanged();


        // 设置当前日期
        calendar = Calendar.getInstance();

//        final DatePickerDialog mDatepicker=new DatePickerDialog(mContext, DateSet, calendar
//                .get(Calendar.YEAR), calendar
//                .get(Calendar.MONTH), calendar
//                .get(Calendar.DAY_OF_MONTH));
//
//        //开始时间
//        mTvDatea.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mDatepicker.show();
//
//            }
//        });
//
//        final DatePickerDialog mDatepickerb=new DatePickerDialog(mContext, DateSetb, calendar
//                .get(Calendar.YEAR), calendar
//                .get(Calendar.MONTH), calendar
//                .get(Calendar.DAY_OF_MONTH));
//        //结束时间
//        mTvDateb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mDatepickerb.show();
//
//            }
//        });




        //线路特色点击
        mGvpopview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                thirdadapter.changeSelected(i);
                mBodyinfo.getProductdata().get(i).setChecked(!mBodyinfo.getProductdata().get(i).isChecked());
//                for (int j = 0; j < mBodyinfo.getProductdata().size(); j++) {
//                    if (j == i) {
//                        continue;
//                    }
//                    mBodyinfo.getProductdata().get(i).setChecked(false);
//                }



                subjectname=mBodyinfo.getProductdata().get(i).getAttrdetailvalue();
            }
        });

        //去程交通
        mGvpopviewb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                forthadapter.changeSelected(i);
                gotraffica=mBodyinfo.getGotrafficdata().get(i).getAttrdetailvalue();
            }
        });

        //返程交通
        mGvpopviewc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                fifthadapter.changeSelected(i);
                backtraffica=mBodyinfo.getBacktrafficdata().get(i).getAttrdetailvalue();
            }
        });

        //出发城市
        mGvpopviewd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sixthadapter.changeSelected(i);
                leavecityid=mBodyinfo.getCitydata().get(i).getCityid();
            }
        });






        //提交收索
        mTvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ToastUtils.show(mContext, );

//                if (!godate.equals("")&&!backdate.equals("")) {
//                    godate = godate + "-00-00-00";
//                    backdate = backdate + "-00-00-00";
////                Log.d("shijian","时间戳"+DateUtils.dataOne(godate));
//
//                    searchdate = DateUtils.dataOne(godate) + "," + DateUtils.dataOne(backdate);
//                }else {
//                    searchdate="";
//                }
                startprice=mEtdatea.getText().toString().trim();
                endprice=mEtdateb.getText().toString().trim();


                callBackUi.onRequestUi(searchdate,startprice,endprice,subjectname,gotraffica,backtraffica,leavecityid);

                popdismiss();
            }
        });



        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                godate="";
                backdate="";
                searchdate="";
//                mTvDatea.setText("开始时间");
//                mTvDatea.setTextColor(mContext.getResources().getColor(R.color.dark_9));
//                mTvDateb.setText("结束时间");
//                mTvDateb.setTextColor(mContext.getResources().getColor(R.color.dark_9));


                mEtdatea.setText("");
                mEtdateb.setText("");
                thirdadapter.changeSelected(0);
                forthadapter.changeSelected(0);
                fifthadapter.changeSelected(0);
                sixthadapter.changeSelected(0);




            }
        });


        int h = ViewGroup.LayoutParams.MATCH_PARENT;
//        int w =  wm.getDefaultDisplay().getWidth()/3*2;

        int w= ViewGroup.LayoutParams.MATCH_PARENT;
        this.setContentView(contentView);
        this.setWidth(w);
        this.setHeight(h);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        this.setBackgroundDrawable(dw);


        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();

    }

    public void showResultaPopup(View parent, final List<ResultdInfo> data) {
//        if (!this.isShowing()) {
//            this.showAsDropDown(parent);
//            mAdapter.notifyDataSetChanged();
//        } else {
//            this.dismiss();
//        }
    }

    private void popdismiss(){
        this.dismiss();
    }


    public void showpop(View parent){
        this.showAtLocation(parent,Gravity.RIGHT,0,0);

    }


    /**
     * @description 日期设置匿名类
     */
    DatePickerDialog.OnDateSetListener DateSet = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // 每次保存设置的日期
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String strdate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;

            godate=strdate;
//            mTvDatea.setText(strdate);
//            mTvDatea.setTextColor(mContext.getResources().getColor(R.color.dark_6));


        }
    };


    /**
     * @description 日期设置匿名类-结束时间
     */
    DatePickerDialog.OnDateSetListener DateSetb = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // 每次保存设置的日期
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String strdate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;

            backdate=strdate;
//            mTvDateb.setText(strdate);
//            mTvDateb.setTextColor(mContext.getResources().getColor(R.color.dark_6));


        }
    };
}
