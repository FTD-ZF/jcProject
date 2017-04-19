package com.example.host.jsnewmall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.uu1.nmw.R;
import com.example.host.jsnewmall.model.HomeSixthInfo;
import com.example.host.jsnewmall.model.TravelOutEntry;
import com.example.host.jsnewmall.utils.BitmapCache;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by host on 2017/2/16.
 */

public class TravelOutThirdAdapter extends BaseAdapter {
    private Context mContext;
    private TravelOutEntry mBodyinfo;
    private RequestQueue queue;
    private ImageLoader imageLoader;

    public TravelOutThirdAdapter(Context context, TravelOutEntry bodyinfo, RequestQueue queue){
        this.mContext=context;
        this.mBodyinfo=bodyinfo;
        this.queue=queue;
        imageLoader = new ImageLoader(queue, new BitmapCache());

    }
    @Override
    public int getCount() {
        return mBodyinfo.getList().getLindes_list().size();
    }

    @Override
    public Object getItem(int position) {
        return mBodyinfo.getList().getLindes_list().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final TravelOutEntry.ListBean.LindesListBean contentinfo=mBodyinfo.getList().getLindes_list().get(position);
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_fragment_home_sixth_view, null);
//            holder.iva = (ImageView) convertView.findViewById(R.id.img);
//            holder.iva = (RelativeLayout) convertView.findViewById(R.id.ll_sixth_layout);
            holder.mImg=(NetworkImageView) convertView.findViewById(R.id.network_travel);//设置背景图片
            holder.tva = (TextView) convertView.findViewById(R.id.tv_sixth_content_a);
            holder.tvb = (TextView) convertView.findViewById(R.id.tv_sixth_content_b);
            holder.tvc = (TextView) convertView.findViewById(R.id.tv_sixth_content_c);
            holder.tvd = (TextView) convertView.findViewById(R.id.tv_sixth_content_d);
            holder.tve = (TextView) convertView.findViewById(R.id.tv_sixth_content_e);
            holder.tvf = (TextView) convertView.findViewById(R.id.tv_sixth_content_f);



            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        //设置显示图片
        holder.mImg.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.mImg.setImageUrl(contentinfo.getLine_data().getImage(), imageLoader);

        //设置标题

        holder.tva.setText(mContext.getResources().getString(R.string.search_h)+
                contentinfo.getLine_data().getPrice()+
                mContext.getResources().getString(R.string.search_i));

        holder.tvb.setText(contentinfo.getLine_data().getLines_days()+"天"+
                contentinfo.getLine_data().getLines_daysnight()+"晚");
        holder.tvc.setText(contentinfo.getLine_data().getGo_place()+"出发");
        holder.tvd.setText(contentinfo.getLine_data().getTravelmode());
        holder.tve.setText(contentinfo.getLine_data().getName());
        holder.tvf.setText(contentinfo.getLine_data().getTitle());

        return convertView;
    }
    class Holder {
        NetworkImageView mImg;
        TextView tva;
        TextView tvb;
        TextView tvc;
        TextView tvd;
        TextView tve;
        TextView tvf;


    }
}
