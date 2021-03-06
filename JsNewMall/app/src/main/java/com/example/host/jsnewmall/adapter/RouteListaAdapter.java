package com.example.host.jsnewmall.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.uu1.nmw.R;

import com.example.host.jsnewmall.model.HomeSixthInfo;
import com.example.host.jsnewmall.model.LineRouteEntry;
import com.example.host.jsnewmall.utils.BitmapCache;
import com.example.host.jsnewmall.view.HomeForthGridView;
import com.example.host.jsnewmall.view.MeasureListView;
import com.example.host.jsnewmall.view.MeasureTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by host on 2017/3/1.
 */

public class RouteListaAdapter extends BaseAdapter {
    private Context mContext;
//    private String[] arrTexta = new String[]{"亲子游", "文本标签", "文本标签", "文本标签", "亲子游", "文本标签", "文本标签",};
//    private String[] arrTextb = new String[]{"文本标签", "文本标签", "文本标签", "文本标签", "亲子游", "文本标签", "文本标签",};
//    private String[] arrTextc = new String[]{"文本标签", "文本标签", "文本标签", "文本标签", "亲子游", "文本标签", "文本标签",};
//    private String[] arrTextd = new String[]{"文本标签", "文本标签", "文本标签", "文本标签", "亲子游", "文本标签", "文本标签",};
//    private String[] arrTexte = new String[]{"文本标签", "文本标签", "文本标签", "文本标签", "亲子游", "文本标签", "文本标签",};
//    private String[] arrTextf = new String[]{"文本标签", "文本标签", "文本标签", "文本标签", "亲子游", "文本标签", "文本标签",};
//    private String[] arrTextg = new String[]{"http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg",
//            "http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg",
//            "http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg",
//            "http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg"};
//    private int[] arrImages = new int[]{R.mipmap.testpicture, R.mipmap.testpicture, R.mipmap.testpicture, R.mipmap.testpicture
//            , R.mipmap.testpicture, R.mipmap.testpicture, R.mipmap.testpicture};
//    private List<HomeSixthInfo> pictures;

    private RequestQueue queue;
    private ImageLoader imageLoader;
    private LineRouteEntry mBodyInfo;

    public RouteListaAdapter(Context context, RequestQueue queue, LineRouteEntry bodyinfo) {
        this.mContext = context;
        this.queue = queue;
        this.mBodyInfo=bodyinfo;
        imageLoader = new ImageLoader(queue, new BitmapCache());
//        pictures = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            HomeSixthInfo pt = new HomeSixthInfo(arrTexta[i], arrTextb[i], arrTextc[i], arrTextd[i], arrTexte[i], arrTextf[i], arrImages[i], arrTextg[i]);
//            pictures.add(pt);
//        }
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
//        return pictures.get(position);
        return null;
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
        if (position==0){
            convertView=LayoutInflater.from(mContext).inflate(R.layout.item_route_listview_first_view, null);
             holder.mListItemb=(MeasureListView) convertView.findViewById(R.id.item_routeb_listview);

            RouteListbAdapter adapterb=new RouteListbAdapter(mContext,queue,mBodyInfo);

            holder.mListItemb.setAdapter(adapterb);
            HomeForthGridView.setListViewHeight(holder.mListItemb);

        }
        if(position==1){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_route_third_view, null);//产品特色
             holder.mTVthirdcontent=(TextView) convertView.findViewById(R.id.tv_third_route_item);//内容显示一个

             holder.mTVthirdcontent.setText(mBodyInfo.getData_related().getFeature());

        }
        if(position==2) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_route_second_view, null);//预订须知

            holder.mTva=(TextView)convertView.findViewById(R.id.tv_second_item_a);//4组数据
            holder.mTvb=(TextView)convertView.findViewById(R.id.tv_second_item_b);
            holder.mTvc=(TextView)convertView.findViewById(R.id.tv_second_item_c);
            holder.mTvd=(TextView)convertView.findViewById(R.id.tv_second_item_d);



            if (mBodyInfo.getData_related().getTip()==null){
                holder.mTva.setVisibility(View.GONE);
            }else{
                holder.mTva.setText("【游客须知】"+mBodyInfo.getData_related().getTip()+"");
            }

            if (mBodyInfo.getData_related().getOwncharge()==null){
                holder.mTvb.setVisibility(View.GONE);
            }else{
                holder.mTvb.setText("【自费须知】"+mBodyInfo.getData_related().getOwncharge()+"");
            }

           if (mBodyInfo.getData_related().getFeeincluded()==null){
               holder.mTvc.setVisibility(View.GONE);
           }else{
               holder.mTvc.setText("【费用包含】"+mBodyInfo.getData_related().getFeeincluded());
           }
            if (mBodyInfo.getData_related().getFeenotincluded()==null){
                holder.mTvd.setVisibility(View.GONE);
            }else {
                holder.mTvd.setText("【费用不包含】"+mBodyInfo.getData_related().getFeenotincluded());
            }

        }

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }





        return convertView;
    }

    class Holder{
        MeasureListView mListItemb;
        TextView mTVthirdcontent;
        TextView mTva;
        TextView mTvb;
        TextView mTvc;
        TextView mTvd;
    }



}