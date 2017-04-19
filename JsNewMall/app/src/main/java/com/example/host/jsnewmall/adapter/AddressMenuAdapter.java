package com.example.host.jsnewmall.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.uu1.nmw.R;


/**
 * Created by host on 2017/3/27.
 */

public class AddressMenuAdapter extends BaseAdapter {
    private Context mContext;
    private int selectItem = 0;
    private String data[] = {"国内", "出境", "周边"};
    public AddressMenuAdapter(Context context){
        this.mContext=context;
    }

    public int getSelectItem() {
        return selectItem;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }


    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_fragment_address_left_listview, null);
            holder.tv_name = (TextView) convertView.findViewById(R.id.item_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }




        if (position == selectItem) {
            holder.tv_name.setBackgroundColor(Color.WHITE);
            holder.tv_name.setTextColor(mContext.getResources().getColor(R.color.title_orange));
        } else {
            holder.tv_name.setBackgroundColor(mContext.getResources().getColor(R.color.second_bgcolor));
            holder.tv_name.setTextColor(mContext.getResources().getColor(R.color.dark_6));
        }
        holder.tv_name.setText(data[position]);
        return convertView;
    }

    static class ViewHolder {
        private TextView tv_name;
    }
}
