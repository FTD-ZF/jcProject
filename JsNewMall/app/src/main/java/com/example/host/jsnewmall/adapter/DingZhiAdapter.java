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
import com.uu1.nmw.R;
import com.example.host.jsnewmall.model.DingzEntry;
import com.example.host.jsnewmall.model.HomeSixthInfo;
import com.example.host.jsnewmall.utils.BitmapCache;
import com.example.host.jsnewmall.view.XRoundNetImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by host on 2017/2/20.
 */

public class DingZhiAdapter extends BaseAdapter{


    private Context mContext;

    private RequestQueue queue;
    private ImageLoader imageLoader;
    private DingzEntry mBodyinfo;
    public DingZhiAdapter(Context context, RequestQueue queue, DingzEntry bodyinfo){
        this.mContext=context;
        this.queue=queue;
        this.mBodyinfo=bodyinfo;
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
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_activity_dingzhi_view, null);

            holder.networkImageView=(XRoundNetImageView) convertView.findViewById(R.id.item_list_round_view);

//            NetworkImageView imageView = new NetworkImageView(mContext);

            //设置显示图片
//            holder.iva.setBackgroundResource(arrImages[position]);

            holder.tva = (TextView) convertView.findViewById(R.id.tv_dingzhi_item_a);
            holder.tvb = (TextView) convertView.findViewById(R.id.tv_dingzhi_item_b);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.networkImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.networkImageView.setImageUrl(mBodyinfo.getList().getLindes_list().get(position).getImg(), imageLoader);

        //设置标题
//        holder.tva.setText(arrTexta[position]);
//        holder.tvb.setText(arrTextb[position]);


        return convertView;
    }
    class Holder {

        TextView tva;
        TextView tvb;

        XRoundNetImageView networkImageView;


    }

}
