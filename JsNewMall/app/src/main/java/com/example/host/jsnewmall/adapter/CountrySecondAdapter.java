package com.example.host.jsnewmall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.uu1.nmw.R;
import com.example.host.jsnewmall.model.HomeFifthInfo;
import com.example.host.jsnewmall.model.TravelCountryEntry;
import com.example.host.jsnewmall.utils.BitmapCache;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by host on 2017/2/16.
 */

public class CountrySecondAdapter  extends BaseAdapter{
    private Context mContext;

    private TravelCountryEntry mBodyinfo;
    private RequestQueue queue;
    private ImageLoader imageLoader;

    public CountrySecondAdapter(Context context, TravelCountryEntry bodyinfo, RequestQueue queue){
        this.mContext=context;
        this.mBodyinfo=bodyinfo;
        this.queue=queue;
        imageLoader = new ImageLoader(queue, new BitmapCache());
    }

    @Override
    public int getCount() {
       return mBodyinfo.getList().getHot_rec().size();
    }

    @Override
    public Object getItem(int position) {
        return mBodyinfo.getList().getHot_rec().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final TravelCountryEntry.ListBean.HotRecBean contentinfo=mBodyinfo.getList().getHot_rec().get(position);

        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_fragment_home_fifth_view, null);
            holder.iva = (NetworkImageView) convertView.findViewById(R.id.img_fifth_view);
            holder.tva = (TextView) convertView.findViewById(R.id.tv_fifth_content_a);
            holder.tvb = (TextView) convertView.findViewById(R.id.tv_fifth_content_b);
            holder.tvc = (TextView) convertView.findViewById(R.id.tv_fifth_content_c);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }



        //设置显示图片


        holder.iva.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.iva.setImageUrl(contentinfo.getLine_data().getImage(), imageLoader);

        //设置标题
        holder.tva.setText(contentinfo.getLine_data().getName());
        holder.tvb.setText(contentinfo.getLine_data().getLines_days()+"天"+contentinfo.getLine_data().getLines_daysnight()+"晚");
        holder.tvc.setText(mContext.getResources().getString(R.string.search_h)+
                contentinfo.getLine_data().getPrice()+
                mContext.getResources().getString(R.string.search_i));


        return convertView;
    }
    class Holder {
        NetworkImageView iva;
        TextView tva;
        TextView tvb;
        TextView tvc;

    }
}
