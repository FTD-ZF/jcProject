package com.example.host.jsnewmall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.uu1.nmw.R;
import com.example.host.jsnewmall.model.Picture;
import com.example.host.jsnewmall.model.TravelWeekThirdInfo;
import com.example.host.jsnewmall.model.TravelWeekendEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by host on 2017/2/17.
 */

public class TravelweekFifthAdapter extends BaseAdapter {
    private Context mContext;
//    private String[] arrText = new String[]{"千岛湖", "千岛湖", "千岛湖","千岛湖",
//                                            "黄山","黄山","黄山","黄山",};
//    private int[] arrImages = new int[]{R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,
//            R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
//    private List<TravelWeekThirdInfo> pictures;
//    private String[] imgurla=new String[]{"http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg",
//            "http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg",
//            "http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg",
//            "http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg",
//            "http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg",
//            "http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg",
//            "http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg",
//            "http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg"};



    private TravelWeekendEntry mBodyinfo;
    private List<TravelWeekendEntry.ListBean.HotRecBean> hotlistinfo;

    public TravelweekFifthAdapter(Context context, TravelWeekendEntry bodyinfo){
        this.mContext=context;
        this.mBodyinfo=bodyinfo;
//        pictures = new ArrayList<>();
//        for (int i=0; i<8; i++){
//            TravelWeekThirdInfo pt = new TravelWeekThirdInfo(arrText[i], arrImages[i],imgurla[i]);
//            pictures.add(pt);
//        }
//        hotlistinfo=new ArrayList<>();
//        for (int i=0;i<hotlistinfo.size();i++){
//            if(i>3){
//                hotlistinfo.add(bodyinfo.getList().getHot_rec().get(i));
//            }
//        }

    }

    @Override
    public int getCount() {
//        if (null != pictures){
//            return  pictures.size();
//        }else{
//            return 0;
//        }
        return mBodyinfo.getList().getHot_rec().size()-4;

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final TravelWeekendEntry.ListBean.HotRecBean contentinfo=mBodyinfo.getList().getHot_rec().get(position+4);

        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_fragment_travel_week_fifth, null);

            holder.tv = (TextView) convertView.findViewById(R.id.tv_week_fifth_b);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }


        //设置标题
        holder.tv.setText(contentinfo.getTitle());
        return convertView;
    }
    class Holder {
        TextView tv;
    }


}
