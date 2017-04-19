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
import com.example.host.jsnewmall.model.CenterComentChangeEntry;
import com.example.host.jsnewmall.utils.BitmapCache;
import com.uu1.nmw.R;

import java.util.List;

/**
 * Created by host on 2017/4/18.
 */

public class CommentPicAdapter extends BaseAdapter {

    private RequestQueue queue;
    private Context mContext;
    private List<CenterComentChangeEntry.DataBean.CommentInfoBean.PictureBean> bodylist;
    private ImageLoader imageLoader;

    public CommentPicAdapter(Context context, List<CenterComentChangeEntry.DataBean.CommentInfoBean.PictureBean> list,RequestQueue queue){
        this.mContext=context;
        this.queue=queue;
        this.bodylist=list;
        imageLoader = new ImageLoader(queue, new BitmapCache());
    }
    @Override
    public int getCount() {
        if (bodylist.size()>4){
            return 4;
        }else {
            return bodylist.size();
        }


    }

    @Override
    public Object getItem(int position) {
        return bodylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_item_comment_grid_picture_view, null);
            holder.networkImageView=(NetworkImageView)convertView.findViewById(R.id.item_item_picture);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        if (position==3){
            holder.networkImageView.setBackground(mContext.getResources().getDrawable(R.mipmap.icon_commentlist_pic));
        }else {
            holder.networkImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            holder.networkImageView.setImageUrl(bodylist.get(position).getImg_url(), imageLoader);
        }





        return convertView;
    }

    class Holder {

        NetworkImageView networkImageView;
    }
}
