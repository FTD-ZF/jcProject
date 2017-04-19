package com.example.host.jsnewmall.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.host.jsnewmall.activity.ArShopDetailsActivity;
import com.example.host.jsnewmall.activity.LocationActivity;
import com.example.host.jsnewmall.model.AroundEntry;
import com.example.host.jsnewmall.model.AroundshopEntry;
import com.example.host.jsnewmall.utils.BitmapCache;
import com.example.host.jsnewmall.view.DialogCallPhone;
import com.example.host.jsnewmall.view.PhoneView;
import com.uu1.nmw.R;

import java.io.Serializable;
import java.util.List;

/**
 * Created by host on 2017/4/13.
 */

public class AroundShopAdapter extends BaseAdapter {
    private double mstartlat;
    private double mstartlong;
    private Context mContext;
    private List<AroundshopEntry.DataBean> bodylist;
    private RequestQueue queue;
    private ImageLoader imageLoader;

    public AroundShopAdapter(Context context, List<AroundshopEntry.DataBean> list, double lat, double longti, RequestQueue queue){
        this.mstartlat=lat;
        this.mstartlong=longti;
        this.mContext=context;
        this.bodylist=list;
        this.queue=queue;
        imageLoader = new ImageLoader(queue, new BitmapCache());



    }

    @Override
    public int getCount() {
        return bodylist.size();
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
        final AroundshopEntry.DataBean contentinfo=bodylist.get(position);
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_activity_aroundshop_view, null);
//            holder.iva = (ImageView) convertView.findViewById(R.id.img);


            holder.networkImageView=(NetworkImageView) convertView.findViewById(R.id.netimg_result);

//            NetworkImageView imageView = new NetworkImageView(mContext);





            holder.tva = (TextView) convertView.findViewById(R.id.tv_item_result_a);//图片上方字一
            holder.mLnBackcolor=(LinearLayout) convertView.findViewById(R.id.ln_item_shop_bgcolor);//图片文字背景


            holder.tvc = (TextView) convertView.findViewById(R.id.tv_result_content_a);//标题

            holder.tvd = (TextView) convertView.findViewById(R.id.tv_result_content_b);//电话
            holder.tve = (TextView) convertView.findViewById(R.id.tv_result_content_c);//地址

            holder.mLnCalllayout=(LinearLayout)convertView.findViewById(R.id.ln_item_call);//打电话
            holder.mLnLocationlayout=(LinearLayout)convertView.findViewById(R.id.ln_item_location);//导航
            holder.mLnDetailslayout=(LinearLayout)convertView.findViewById(R.id.ln_item_details_layout);//布局跳转详情


            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }


        String businesstype=contentinfo.getBusiness_type();
        //营业类型：1、直营店 2、加盟店 3、合作 4、其它
        if (businesstype!=null) {
            if (businesstype.equals("1")) {
                holder.tva.setText("直营店");
                holder.mLnBackcolor.setBackgroundColor(mContext.getResources().getColor(R.color.title_orange));
            } else if (businesstype.equals("2")) {
                holder.tva.setText("加盟店");
                holder.mLnBackcolor.setBackgroundColor(mContext.getResources().getColor(R.color.title_orange));
            } else if (businesstype.equals("3")) {
                holder.tva.setText("合作");
                holder.mLnBackcolor.setBackgroundColor(mContext.getResources().getColor(R.color.title_orange));
            } else if (businesstype.equals("4")) {
                holder.tva.setText("其它");
                holder.mLnBackcolor.setBackgroundColor(mContext.getResources().getColor(R.color.title_orange));
            }
        }

        //设置显示图片
        holder.networkImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        if (contentinfo.getMain_photo().size()!=0) {
            holder.networkImageView.setImageUrl(contentinfo.getMain_photo().get(0).getImg_onlineurl(), imageLoader);
        }else {
            holder.networkImageView.setBackground(mContext.getResources().getDrawable(R.mipmap.icon_null_pics));
        }
        holder.tvc.setText(contentinfo.getStore_name());
        holder.tvd.setText(contentinfo.getStore_contact_phone());
        holder.tve.setText(contentinfo.getStore_address());




        //电话弹窗
        holder.mLnCalllayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contentinfo.getMain_photo().size()!=0) {
                    DialogCallPhone.showshopdialog(mContext, contentinfo.getMain_photo().get(0).getImg_onlineurl(), contentinfo.getStore_contact_phone());
                }else {
                    DialogCallPhone.showshopdialogB(mContext,contentinfo.getStore_contact_phone());
                }

                }
        });

        //到航跳转
        final double endlat=Double.parseDouble(contentinfo.getLatitude());
        final double endlong=Double.parseDouble(contentinfo.getLongitude());
        holder.mLnLocationlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentgps=new Intent(mContext, LocationActivity.class);
                intentgps.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intentgps.putExtra("startlat",mstartlat);
                intentgps.putExtra("startlong",mstartlong);
                intentgps.putExtra("endlat",endlat);
                intentgps.putExtra("endlong",endlong);

                mContext.startActivity(intentgps);
            }
        });



        //详情页跳转
        holder.mLnDetailslayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentdetails=new Intent(mContext, ArShopDetailsActivity.class);

                intentdetails.putExtra("bodyinfo",(Serializable) bodylist);
                intentdetails.putExtra("position",position);
                intentdetails.putExtra("startlat",mstartlat);
                intentdetails.putExtra("startlong",mstartlong);
                mContext.startActivity(intentdetails);

            }
        });





        return  convertView;
    }


    class Holder {

        TextView tva;
        TextView tvb;
        TextView tvc;
        TextView tvd;
        TextView tve;

        NetworkImageView networkImageView;
        LinearLayout mLnBackcolor;
        LinearLayout mLnCalllayout;
        LinearLayout mLnLocationlayout;
        LinearLayout mLnDetailslayout;

    }
}
