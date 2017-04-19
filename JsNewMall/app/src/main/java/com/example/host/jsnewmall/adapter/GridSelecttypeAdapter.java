package com.example.host.jsnewmall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.uu1.nmw.R;

import java.util.List;

/**
 * Created by host on 2017/3/20.
 */

public class GridSelecttypeAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mDatas;

    private int selectpositon=-1;
    public GridSelecttypeAdapter(Context context,List<String> datas){

        this.mContext=context;
        this.mDatas=datas;
    }
    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setselectiontypenum(int selectitem){
        this.selectpositon=selectitem;
    }
    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        convertview= LayoutInflater.from(mContext).inflate(R.layout.item_select_traveller_type_view,null);
        TextView mTvTypename=(TextView)convertview.findViewById(R.id.tv_item_traveller_type_name);//类型名字显示
        mTvTypename.setText(mDatas.get(position));


        if (selectpositon==position){
            mTvTypename.setTextColor(mContext.getResources().getColor(R.color.title_orange));
        }else {
            mTvTypename.setTextColor(mContext.getResources().getColor(R.color.dark_6));
        }




        return convertview;
    }
}
