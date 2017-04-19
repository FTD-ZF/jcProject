package com.example.host.jsnewmall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.uu1.nmw.R;
import com.example.host.jsnewmall.model.TravelCountryEntry;
import com.example.host.jsnewmall.model.TravelFirstInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by host on 2017/2/16.
 */

public class CountryFirstAdapter extends BaseAdapter {


    private Context mContext;
    private TravelCountryEntry mBodyinfo;
    public CountryFirstAdapter(Context context, TravelCountryEntry bodyinfo){
        this.mContext=context;
        this.mBodyinfo=bodyinfo;

    }

    @Override
    public int getCount() {
      return  mBodyinfo.getList().getTop_nav().size();

    }

    @Override
    public Object getItem(int position) {
        return mBodyinfo.getList().getTop_nav().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_fragment_travel_around_first, null);

            holder.tv = (TextView) convertView.findViewById(R.id.tv_travel_around_first);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }


        //设置标题
        holder.tv.setText(mBodyinfo.getList().getTop_nav().get(position).getTitle());
        return convertView;
    }
    class Holder {

        TextView tv;
    }

}
