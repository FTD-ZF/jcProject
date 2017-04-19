package com.example.host.jsnewmall.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.host.jsnewmall.activity.SearchResultActivity;
import com.uu1.nmw.R;
import com.example.host.jsnewmall.model.AddressEntry;
import com.example.host.jsnewmall.utils.ToastUtils;
import com.example.host.jsnewmall.view.HomeForthGridView;

import java.util.List;

/**
 * Created by host on 2017/3/27.
 */

public class AddressRightAdapter extends BaseAdapter {
    private List<AddressEntry.ListBean> mBodylist;
    private Context mContext;
    public AddressRightAdapter(Context context, List<AddressEntry.ListBean> list){
        this.mBodylist=list;
        this.mContext=context;
    }
    @Override
    public int getCount() {
        return mBodylist.size();
    }

    @Override
    public Object getItem(int position) {
        return mBodylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final AddressEntry.ListBean bodyinfo=mBodylist.get(position);

        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_fragment_address_rightlistview, null);
            holder.mTitle=(TextView)convertView.findViewById(R.id.tv_item_addrress_title);//设置标题
            holder.mGvAddressView=(GridView)convertView.findViewById(R.id.gv_item_address_view); //地址名字列表展示

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }



        AddressItemGridAdapter gridadapter=new AddressItemGridAdapter(mContext,bodyinfo.getSub_class());
        holder.mGvAddressView.setAdapter(gridadapter);

        HomeForthGridView.setListViewHeightBasedOnChildren(holder.mGvAddressView);

        //点击每个地点的跳转
        holder.mGvAddressView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {


//                ToastUtils.show(mContext,bodyinfo.getSub_class().get(position).getName());

                Intent intent=new Intent(mContext, SearchResultActivity.class);
                intent.putExtra("searchcontent",bodyinfo.getSub_class().get(position).getName());
                mContext.startActivity(intent);
            }
        });


        holder.mTitle.setText(bodyinfo.getName());//填充每行标题内容
        return convertView;
    }

    class Holder {
        TextView mTitle;
        GridView mGvAddressView;
    }
}
