package com.example.host.jsnewmall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.uu1.nmw.R;
import com.example.host.jsnewmall.model.AddressEntry;

import java.util.List;

/**
 * Created by host on 2017/3/27.
 */

public class AddressItemGridAdapter extends BaseAdapter {
    private Context mContext;
    private List<AddressEntry.ListBean.SubClassBean> mBodylist;
    public AddressItemGridAdapter(Context context, List<AddressEntry.ListBean.SubClassBean> list){
        this.mContext=context;
        this.mBodylist=list;
    }
    @Override
    public int getCount() {
        return mBodylist.size();
    }

    @Override
    public Object getItem(int position) {
        return mBodylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        AddressEntry.ListBean.SubClassBean bodyinfo=mBodylist.get(position);
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_fragment_address_item_gridview, null);
            holder.mTvContent=(TextView) convertView.findViewById(R.id.tv_content_address_griditem);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.mTvContent.setText(bodyinfo.getName());

        return convertView;
    }

    class Holder {
        TextView mTvContent;

    }
}
