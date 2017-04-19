package com.example.host.jsnewmall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.uu1.nmw.R;
import com.example.host.jsnewmall.model.OrderdetailsEntry;
import com.example.host.jsnewmall.model.TravellerEntry;

import java.util.HashMap;
import java.util.List;

/**
 * Created by host on 2017/3/22.
 */

public class PerTravellerInfobAdapter extends BaseAdapter {


    // 用来控制CheckBox的选中状况
    private static HashMap<Integer,Boolean> isSelected;
    private List<TravellerEntry.DataBean> mBodyinfolist;
    private Context mContext;
    public PerTravellerInfobAdapter(Context context, List<TravellerEntry.DataBean> list){
        this.mContext=context;
        this.mBodyinfolist=list;
        isSelected = new HashMap<Integer, Boolean>();

        for(int i=0; i<list.size();i++) {
            getIsSelected().put(i,false);
        }
    }

    @Override
    public int getCount() {
        return mBodyinfolist.size();
    }

    @Override
    public Object getItem(int position) {
        return mBodyinfolist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        TravellerEntry.DataBean bodyinfo=mBodyinfolist.get(position);
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.item_per__traveller_b_view,null);

            holder.mTvtraelllera=(TextView) convertView.findViewById(R.id.tv_pertraveller_a);//名字
            holder.mTvtraelllerb=(TextView) convertView.findViewById(R.id.tv_pertraveller_b);//名字
            holder.mTvtraelllerc=(TextView) convertView.findViewById(R.id.tv_pertraveller_c);//名字

            holder.mImgchecked=(CheckBox) convertView.findViewById(R.id.img_pertraveller_view);//选中状态



            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        // 监听checkBox并根据原来的状态来设置新的状态
        holder.mImgchecked.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (isSelected.get(position)) {
                    isSelected.put(position, false);
                    setIsSelected(isSelected);
                } else {
                    isSelected.put(position, true);
                    setIsSelected(isSelected);
                }

            }
        });

        // 根据isSelected来设置checkbox的选中状况
        holder.mImgchecked.setChecked(getIsSelected().get(position));



        String sextext=null;

        if (bodyinfo.getSex().equals("0")){
            sextext="女";
        }else if (bodyinfo.getSex().equals("1")){
            sextext="男";
        }else if (bodyinfo.getSex().equals("2")){
            sextext="儿童";
        }
        holder.mTvtraelllera.setText(bodyinfo.getTrue_name()+"("+sextext+")");//设置姓名
        String type=bodyinfo.getPaper_type();


        //@"身份证",@"护照",@"驾驶证",@"军人证",@"回乡证",@"港澳通行证",@"台胞证",@"其他"
        if (type.equals("1")){
            holder.mTvtraelllerb.setText("身份证："+bodyinfo.getPaper_num());
        }else if (type.equals("2")){
            holder.mTvtraelllerb.setText("护照："+bodyinfo.getPaper_num());
        }else if (type.equals("3")){
            holder.mTvtraelllerb.setText("驾驶证："+bodyinfo.getPaper_num());
        }else if (type.equals("4")){
            holder.mTvtraelllerb.setText("军人证："+bodyinfo.getPaper_num());
        }else if (type.equals("5")){
            holder.mTvtraelllerb.setText("回乡证："+bodyinfo.getPaper_num());
        }else if (type.equals("6")){
            holder.mTvtraelllerb.setText("港澳通行证："+bodyinfo.getPaper_num());
        }else if (type.equals("7")){
            holder.mTvtraelllerb.setText("台胞证："+bodyinfo.getPaper_num());
        }else if (type.equals("8")){
            holder.mTvtraelllerb.setText("其他："+bodyinfo.getPaper_num());
        }
        holder.mTvtraelllerc.setText("手机号："+bodyinfo.getContact_phone());

        return convertView;
    }

    public static class ViewHolder {
        public  TextView mTvtraelllera;
        public  TextView mTvtraelllerb;
        public  TextView mTvtraelllerc;
        public  CheckBox mImgchecked;

    }




    public static HashMap<Integer,Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer,Boolean> isSelected) {
        PerTravellerInfobAdapter.isSelected = isSelected;
    }

}
