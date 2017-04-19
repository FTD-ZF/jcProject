package com.example.host.jsnewmall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.host.jsnewmall.model.SearchkeywordEntry;
import com.uu1.nmw.R;

/**
 * Created by host on 2017/4/6.
 */

public class PopSixthAdapter extends BaseAdapter {

    private SearchkeywordEntry mBodyinfo;
    private Context mContext;
    private int mSelect = 0;

    public PopSixthAdapter(Context context, SearchkeywordEntry info) {
        this.mBodyinfo = info;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mBodyinfo.getCitydata().size();
    }

    @Override
    public Object getItem(int position) {
        return mBodyinfo.getCitydata().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public void changeSelected(int positon) { //刷新方法
        if (positon != mSelect) {
            mSelect = positon;
            notifyDataSetChanged();
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_popthird_view, null);
            holder.tvname = (TextView) convertView.findViewById(R.id.tv_popthird_titlename);//名称显示

            holder.lnbgcolor = (LinearLayout) convertView.findViewById(R.id.ln_item_layout_bgcolor);//点击背景变化


            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }


        holder.tvname.setText(mBodyinfo.getCitydata().get(position).getName());


        if (mSelect == position) {
            holder.tvname.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.home_sixth_content_shape));  //选中项背景
            holder.tvname.setTextColor(mContext.getResources().getColor(R.color.white));

        } else {
            holder.tvname.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.activity_search_first_shape));  //其他项背景
            holder.tvname.setTextColor(mContext.getResources().getColor(R.color.dark_6));
        }

//
//        if(mSelect==position){
//            holder.lnbgcolor.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.home_sixth_content_shape));  //选中项背景
//            holder.tvmoney.setTextColor(mContext.getResources().getColor(R.color.white));
//            holder.tvmoneya.setTextColor(mContext.getResources().getColor(R.color.white));
//
//        }else{
//            holder.lnbgcolor.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.home_sixth_content_shape_c));  //其他项背景
//            holder.tvmoney.setTextColor(mContext.getResources().getColor(R.color.title_orange));
//            holder.tvmoneya.setTextColor(mContext.getResources().getColor(R.color.title_orange));
//        }


        return convertView;
    }

    class Holder {
        TextView tvname;

        LinearLayout lnbgcolor;

    }
}