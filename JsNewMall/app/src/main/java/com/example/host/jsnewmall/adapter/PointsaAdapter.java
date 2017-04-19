package com.example.host.jsnewmall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.uu1.nmw.R;
import com.example.host.jsnewmall.model.PointsaEntry;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by host on 2017/3/23.
 */

public class PointsaAdapter extends BaseAdapter {
    private Context mContext;
    private List<PointsaEntry.DataBean.ListBean> mBodyinfolist;

    public PointsaAdapter(Context context, List<PointsaEntry.DataBean.ListBean> list){
        this.mContext=context;
        this.mBodyinfolist=list;

    }

    @Override
    public int getCount() {
        return mBodyinfolist.size();
    }

    @Override
    public Object getItem(int position) {
        return mBodyinfolist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        PointsaEntry.DataBean.ListBean mInfo=mBodyinfolist.get(position);

        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_fragment_points_a_view, null);
            holder.mTva=(TextView) convertView.findViewById(R.id.tv_item_points_aa);
            holder.mTvb=(TextView) convertView.findViewById(R.id.tv_item_points_bb);
            holder.mTvc=(TextView) convertView.findViewById(R.id.tv_item_points_cc);


            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }


        holder.mTva.setText(mInfo.getRemark());
        holder.mTvb.setText(mInfo.getAdd_date());
        holder.mTvc.setText("+"+mInfo.getPoint_num());


        return convertView;
    }
    class Holder {
        TextView mTva;
        TextView mTvb;
        TextView mTvc;
    }


}
