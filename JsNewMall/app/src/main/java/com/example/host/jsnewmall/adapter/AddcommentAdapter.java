package com.example.host.jsnewmall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uu1.nmw.R;

/**
 * Created by host on 2017/4/17.
 */

public class AddcommentAdapter extends BaseAdapter {
    private Context mContext;
    private String[] arrtype = new String[]{"家庭出游", "情侣出游", "朋友出游","团队出游","单独出游","代报名"};
    private int selectpositon=-1;

    public AddcommentAdapter(Context context){
        this.mContext=context;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void setselectiontypenum(int selectitem){
        this.selectpositon=selectitem;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView= LayoutInflater.from(mContext).inflate(R.layout.item_activity_addcomment_view,null);
        TextView mTva=(TextView)convertView.findViewById(R.id.tv_addcomment_item);//文字显示
        LinearLayout mLnBgcolor=(LinearLayout)convertView.findViewById(R.id.ln_item_addcomment_bg);//背景变化
        mTva.setText(arrtype[position]);


        if (selectpositon==position){
            mLnBgcolor.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.suggestion_item_bg_shape));
            mTva.setTextColor(mContext.getResources().getColor(R.color.white));
        }else {
            mLnBgcolor.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.travel_week_ninth_shape));
            mTva.setTextColor(mContext.getResources().getColor(R.color.dark_6));
        }


        return convertView;
    }
}
