package com.example.host.jsnewmall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.uu1.nmw.R;
import com.example.host.jsnewmall.utils.BitmapCache;

/**
 * Created by host on 2017/3/30.
 */

public class TicketSecondAdapter extends BaseAdapter {


    private Context mContext;

    private int[] arrImages = new int[]{R.mipmap.icon_ticket_second_a,R.mipmap.icon_ticket_second_b,R.mipmap.icon_ticket_second_c
            ,R.mipmap.icon_ticket_second_d,R.mipmap.icon_ticket_second_e};

    private RequestQueue queue;
    private ImageLoader imageLoader;
    public TicketSecondAdapter(Context context, RequestQueue queue){
        this.mContext=context;
        this.queue=queue;
        imageLoader = new ImageLoader(queue, new BitmapCache());


    }
    @Override
    public int getCount() {

        return arrImages.length;
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
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_fragment_hotel_first, null);

            holder.networkImageView=(NetworkImageView) convertView.findViewById(R.id.netimg_hotel_first_b);

//            NetworkImageView imageView = new NetworkImageView(mContext);

//            holder.networkImageView.setImageUrl(arrImages[position], imageLoader);




            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }


        holder.networkImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.networkImageView.setBackgroundResource(arrImages[position]);

        return convertView;
    }
    class Holder {

        NetworkImageView networkImageView;


    }

}
