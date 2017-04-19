package com.example.host.jsnewmall.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uu1.nmw.R;
import com.example.host.jsnewmall.activity.TravellerEditInfoActivity;
import com.example.host.jsnewmall.bean.IEditTravellerLayout;
import com.example.host.jsnewmall.model.JsonmModel;
import com.example.host.jsnewmall.model.PostInfoSuccessEntry;
import com.example.host.jsnewmall.model.TravellerEntry;
import com.example.host.jsnewmall.utils.Base64Utils;
import com.example.host.jsnewmall.utils.HttpUtils;
import com.example.host.jsnewmall.utils.JsonUtils;
import com.example.host.jsnewmall.utils.ToastUtils;
import com.example.host.jsnewmall.utils.UrlUtils;
import com.example.host.jsnewmall.view.SwipeLayout;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by host on 2017/3/20.
 */

public class TravellerAdapter extends BaseAdapter {

    Gson gson=new Gson();
    private Context mContext;
    private List<TravellerEntry.DataBean> mBodyList;
    private int positiondelete;

    /**
     * 屏幕宽度,由于我们用的是HorizontalScrollView,所以按钮选项应该在屏幕外
     */
    private int mScreentWidth;
    private String nTime;

    private static final int RES_CODE=106;
    private static final int RES_CODE_FAIL=107;
    private IEditTravellerLayout mIEditTravellerLayout;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case RES_CODE:
                    mBodyList.remove(positiondelete);
                    ToastUtils.show(mContext,"删除成功");
                    notifyDataSetChanged();
                    break;

                case RES_CODE_FAIL:
                    ToastUtils.show(mContext,"删除失败");
                    break;

                default:
                    break;

            };
        }
    };


    public TravellerAdapter(Context context, List<TravellerEntry.DataBean> list, String nTime, IEditTravellerLayout iEditTravellerLayout){
        this.mBodyList=list;
        this.mContext=context;
        this.nTime=nTime;
        this.mIEditTravellerLayout=iEditTravellerLayout;
    }

    @Override
    public int getCount() {
        return mBodyList.size();
    }

    @Override
    public Object getItem(int position) {
        return mBodyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {

       TravellerEntry.DataBean mBodyinfo=mBodyList.get(position);
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.item_activity_traveller_view,null);

            holder.mTvtraelllera=(TextView) convertView.findViewById(R.id.tv_traveller_a);//姓名
            holder.mTvtraelllerb=(TextView) convertView.findViewById(R.id.tv_traveller_b);//身份证
            holder.mTvtraelllerc=(TextView) convertView.findViewById(R.id.tv_traveller_c);//手机号
            holder.mLinerTravellerEdit=(LinearLayout)convertView.findViewById(R.id.traveller_item_content);//点击布局跳转编辑

            holder.mSwipelayout=(SwipeLayout)convertView.findViewById(R.id.swipe_traveller_layout);//滑动布局

            holder.mBtndelete=(TextView)convertView.findViewById(R.id.tv_delete_traveller);//删除按钮
            holder.mBtndelete.setTag(position);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }




        holder.mLinerTravellerEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIEditTravellerLayout.onEditTravellerListener(position);

            }
        });





        holder.mBtndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positiondelete = (Integer) v.getTag();
                switch (v.getId())
                {


                    case R.id.tv_delete_traveller:


                        JSONObject jbody=null;
                        try {
                            jbody = new JSONObject();
                            jbody.put("contact_id",mBodyList.get(positiondelete).getContact_id());
                            jbody.put("method","DeleteContact");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
                        dohttpdeleteitem(UrlUtils.USER,jbodyB);
                        break;
                    default:
                        break;
                }
                //刷新ListView内容
                notifyDataSetChanged();
            }
        });






        String sextext=null;

        if (mBodyinfo.getSex().equals("0")){
            sextext="女";
        }else if (mBodyinfo.getSex().equals("1")){
            sextext="男";
        }else if (mBodyinfo.getSex().equals("2")){
            sextext="儿童";
        }
        holder.mTvtraelllera.setText(mBodyinfo.getTrue_name()+"("+sextext+")");//设置姓名
        String type=mBodyinfo.getPaper_type();


        //@"身份证",@"护照",@"驾驶证",@"军人证",@"回乡证",@"港澳通行证",@"台胞证",@"其他"
        if (type.equals("1")){
            holder.mTvtraelllerb.setText("身份证："+mBodyinfo.getPaper_num());
        }else if (type.equals("2")){
            holder.mTvtraelllerb.setText("护照："+mBodyinfo.getPaper_num());
        }else if (type.equals("3")){
            holder.mTvtraelllerb.setText("驾驶证："+mBodyinfo.getPaper_num());
        }else if (type.equals("4")){
            holder.mTvtraelllerb.setText("军人证："+mBodyinfo.getPaper_num());
        }else if (type.equals("5")){
            holder.mTvtraelllerb.setText("回乡证："+mBodyinfo.getPaper_num());
        }else if (type.equals("6")){
            holder.mTvtraelllerb.setText("港澳通行证："+mBodyinfo.getPaper_num());
        }else if (type.equals("7")){
            holder.mTvtraelllerb.setText("台胞证："+mBodyinfo.getPaper_num());
        }else if (type.equals("8")){
            holder.mTvtraelllerb.setText("其他："+mBodyinfo.getPaper_num());
        }
        holder.mTvtraelllerc.setText("手机号："+mBodyinfo.getContact_phone());
        return convertView;
    }



    class Holder {
        TextView mTvtraelllera;
        TextView mTvtraelllerb;
        TextView mTvtraelllerc;
        SwipeLayout mSwipelayout;
        TextView mBtndelete;
        LinearLayout mLinerTravellerEdit;

    }




    protected  void dohttpdeleteitem(String url,JSONObject  paramhash){
        HttpUtils.dopost(url,mContext,paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());
                PostInfoSuccessEntry rescode=gson.fromJson(body,PostInfoSuccessEntry.class);
                int finalcode=rescode.getRes();
                if (finalcode==1){
                   handler.sendEmptyMessage(RES_CODE);
                }else {
                    handler.sendEmptyMessage(RES_CODE_FAIL);
                }
            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }





}

