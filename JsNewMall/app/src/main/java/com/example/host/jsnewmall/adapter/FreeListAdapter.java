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
import com.example.host.jsnewmall.model.FreeTravelEntry;
import com.example.host.jsnewmall.utils.BitmapCache;

/**
 * Created by host on 2017/3/30.
 */

public class FreeListAdapter  extends BaseAdapter {

    private Context mContext;

    private RequestQueue queue;
    private ImageLoader imageLoader;
    private FreeTravelEntry mBodyInfo;

    public FreeListAdapter(Context context, RequestQueue queue, FreeTravelEntry bodyinfo) {
        this.mContext = context;
        this.queue = queue;
        this.mBodyInfo = bodyinfo;
        imageLoader = new ImageLoader(queue, new BitmapCache());


    }

    @Override
    public int getCount() {

        return mBodyInfo.getList().getLindes_list().size();
    }

    @Override
    public Object getItem(int position) {
        return mBodyInfo.getList().getLindes_list().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        FreeTravelEntry.ListBean.LindesListBean contentinfo = mBodyInfo.getList().getLindes_list().get(position);
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_fragment_home_sixth_view, null);

            holder.iva = (RelativeLayout) convertView.findViewById(R.id.ll_sixth_layout);//背景
            holder.networkImageView = (NetworkImageView) convertView.findViewById(R.id.network_travel);


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

        holder.networkImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.networkImageView.setImageUrl(contentinfo.getLine_data().getImage(), imageLoader);
        //设置标题
        holder.tva.setText(mContext.getResources().getString(R.string.search_h) +
                contentinfo.getLine_data().getPrice() +
                mContext.getResources().getString(R.string.search_i));
        holder.tvb.setText(contentinfo.getLine_data().getLines_days() + "天" +
                contentinfo.getLine_data().getLines_daysnight() + "晚");
        holder.tvc.setText(contentinfo.getLine_data().getGo_place() + "出发");
        holder.tvd.setText(contentinfo.getLine_data().getTravelmode());
        holder.tve.setText(contentinfo.getLine_data().getName());
        holder.tvf.setText(contentinfo.getLine_data().getTitle());

        return convertView;
    }

    class Holder {
        RelativeLayout iva;
        TextView tva;
        TextView tvb;
        TextView tvc;
        TextView tvd;
        TextView tve;
        TextView tvf;
        NetworkImageView networkImageView;


    }
}