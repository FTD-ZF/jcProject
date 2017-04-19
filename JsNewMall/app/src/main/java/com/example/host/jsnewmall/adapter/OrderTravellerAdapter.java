package com.example.host.jsnewmall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.example.host.jsnewmall.model.OrderdetailsEntry;
import com.uu1.nmw.R;

import java.util.List;

/**
 * Created by host on 2017/3/22.
 */

public class OrderTravellerAdapter extends BaseAdapter {
    private Context mContext;
    private List<OrderdetailsEntry.OrdercontactBean> mBodyInfoList;
    public  OrderTravellerAdapter(Context context, List<OrderdetailsEntry.OrdercontactBean> list){
        this.mContext=context;
        this.mBodyInfoList=list;
    }
    @Override
    public int getCount() {
        return mBodyInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return mBodyInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        OrderdetailsEntry.OrdercontactBean bodyinfo=mBodyInfoList.get(position);
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.item_order_details_traveller_view,null);
            holder.mTvtraelllera=(TextView)convertView.findViewById(R.id.tv_order_traveller_a);//姓名
            holder.mTvtraelllerb=(TextView)convertView.findViewById(R.id.tv_order_traveller_b);//证件显示
            holder.mTvtraelllerc=(TextView) convertView.findViewById(R.id.tv_order_traveller_c);//手机号码

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }


        String sextext=null;

        if (bodyinfo.getSex().equals("0")){
            sextext="女";
        }else if (bodyinfo.getSex().equals("1")){
            sextext="男";
        }else if (bodyinfo.getSex().equals("2")){
            sextext="儿童";
        }
        holder.mTvtraelllera.setText(bodyinfo.getTrue_name()+"("+sextext+")");//设置姓名


        String type=bodyinfo.getPaper_type();//获取证件类型


        //@"身份证",@"护照",@"驾驶证",@"军人证",@"回乡证",@"港澳通行证",@"台胞证",@"其他"
        if (type.equals("1")){
            holder.mTvtraelllerb.setText("身份证："+bodyinfo.getPaper_num());
        }else if (type.equals("2")){
            holder.mTvtraelllerb.setText("护照："+bodyinfo.getPaper_num());
        }else if (type.equals("3")){
            holder.mTvtraelllerb.setText("驾驶证："+bodyinfo.getPaper_num());
        }else if (type.equals("4")){
            holder.mTvtraelllerb.setText("军人证："+bodyinfo.getPaper_num());
        }else if (type.equals("5")){
            holder.mTvtraelllerb.setText("回乡证："+bodyinfo.getPaper_num());
        }else if (type.equals("6")){
            holder.mTvtraelllerb.setText("港澳通行证："+bodyinfo.getPaper_num());
        }else if (type.equals("7")){
            holder.mTvtraelllerb.setText("台胞证："+bodyinfo.getPaper_num());
        }else if (type.equals("8")){
            holder.mTvtraelllerb.setText("其他："+bodyinfo.getPaper_num());
        }

        holder.mTvtraelllerc.setText("手机号："+bodyinfo.getContact_phone());//设置手机号







        return convertView;
    }


    class Holder {
        TextView mTvtraelllera;
        TextView mTvtraelllerb;
        TextView mTvtraelllerc;


    }

}
