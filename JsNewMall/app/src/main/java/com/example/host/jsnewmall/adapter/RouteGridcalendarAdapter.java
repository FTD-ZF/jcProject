package com.example.host.jsnewmall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.host.jsnewmall.model.CalendarEntry;
import com.uu1.nmw.R;

import java.util.List;

/**
 * Created by host on 2017/3/31.
 */

public class RouteGridcalendarAdapter extends BaseAdapter {

    private Context mContext;

    //    private int[] arrImages = new int[]{R.mipmap.testpicture,R.mipmap.testpicture,R.mipmap.testpicture,R.mipmap.testpicture};
//    private List<HomeFifthInfo> pictures;
    private int mSelect=0;
    private List<CalendarEntry.DataBean> mBodyinfo;

    public RouteGridcalendarAdapter(Context context,List<CalendarEntry.DataBean> bodyinfo){
        this.mContext=context;
        this.mBodyinfo=bodyinfo;

//        pictures = new ArrayList<>();
//        for (int i=0; i<4; i++){
//            HomeFifthInfo pt = new HomeFifthInfo(arrTexta[i], arrTextb[i],arrTextc[i],arrImages[i]);
//            pictures.add(pt);
//        }
    }

    @Override
    public int getCount() {
        if (mBodyinfo.size()<4){
            return mBodyinfo.size();
        }else {
            return 4;
        }

    }

    @Override
    public Object getItem(int position) {
        return mBodyinfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void changeSelected(int positon){ //刷新方法
        if(positon != mSelect){
            mSelect = positon;
            notifyDataSetChanged();
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {


        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_route_selectdate_gridview, null);
            holder.tvdate=(TextView) convertView.findViewById(R.id.tv_item_date_a);//日期
            holder.tvmoney=(TextView) convertView.findViewById(R.id.tv_routedate_money);//金额
            holder.tvmoneya=(TextView) convertView.findViewById(R.id.tv_routedate_money_a);//金额标记
            holder.lnbgcolor=(LinearLayout) convertView.findViewById(R.id.ln_item_layout_bgcolor);//点击背景变化


            ;

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }


        holder.tvdate.setText(mBodyinfo.get(position).getDate());
        holder.tvmoney.setText(mBodyinfo.get(position).getSaleprice());


        if(mSelect==position){
            holder.lnbgcolor.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.home_sixth_content_shape));  //选中项背景
            holder.tvmoney.setTextColor(mContext.getResources().getColor(R.color.white));
            holder.tvmoneya.setTextColor(mContext.getResources().getColor(R.color.white));

        }else{
            holder.lnbgcolor.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.home_sixth_content_shape_c));  //其他项背景
            holder.tvmoney.setTextColor(mContext.getResources().getColor(R.color.title_orange));
            holder.tvmoneya.setTextColor(mContext.getResources().getColor(R.color.title_orange));
        }




        return convertView;
    }
    class Holder {
        TextView tvdate;
        TextView tvmoney;
        TextView tvmoneya;
        LinearLayout lnbgcolor;

    }
}
