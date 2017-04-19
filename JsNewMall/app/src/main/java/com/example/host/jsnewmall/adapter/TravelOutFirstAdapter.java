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
import com.example.host.jsnewmall.model.Picture;
import com.example.host.jsnewmall.model.TravelOutEntry;
import com.example.host.jsnewmall.utils.BitmapCache;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by host on 2017/2/16.
 */

public class TravelOutFirstAdapter extends BaseAdapter{
    private Context mContext;
    private TravelOutEntry mBodyinfo;
    private RequestQueue queue;
    private ImageLoader imageLoader;

    public TravelOutFirstAdapter(Context context, TravelOutEntry bodyinfo, RequestQueue queue){
        this.mContext=context;
        this.mBodyinfo=bodyinfo;
        this.queue=queue;
        imageLoader = new ImageLoader(queue, new BitmapCache());
    }

    @Override
    public int getCount() {
    return mBodyinfo.getList().getTop_nav().size();

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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_fragment_out_first_view, null);
            holder.iv = (NetworkImageView) convertView.findViewById(R.id.img_third_view);

            holder.tv = (TextView) convertView.findViewById(R.id.tv_third_content);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }


        //设置显示图片

        holder.iv.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.iv.setImageUrl(mBodyinfo.getList().getTop_nav().get(position).getImg(), imageLoader);


        //设置标题
        holder.tv.setText(mBodyinfo.getList().getTop_nav().get(position).getTitle());

        return convertView;
    }
    class Holder {
        NetworkImageView iv;
        TextView tv;
    }



}
