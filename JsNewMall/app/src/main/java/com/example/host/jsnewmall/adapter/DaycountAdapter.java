package com.example.host.jsnewmall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.host.jsnewmall.model.SearchkeywordEntry;
import com.uu1.nmw.R;

import java.util.List;

/**
 * Created by host on 2017/4/2.
 */

public class DaycountAdapter  extends BaseAdapter {
    private Context mContext;
    private List<SearchkeywordEntry.DaycountdataBean> mdata;
    private int selectItem = -1;

    public DaycountAdapter(Context context,List<SearchkeywordEntry.DaycountdataBean> data) {
        this.mContext = context;
        this.mdata=data;
    }

    @Override
    public int getCount() {
        return mdata == null ? 0 : mdata.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_searchresult_a, null);
            viewHolder.tv1 = (TextView) view.findViewById(R.id.tv_searchresult_item_a);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        if (i==0){
            viewHolder.tv1.setText(mdata.get(i).getDaycount() + "天数");
        }else {
            viewHolder.tv1.setText(mdata.get(i).getDaycount() + "天");
        }
        if (mdata.get(i).isChecked()) {

            viewHolder.tv1.setTextColor(mContext.getResources().getColor(R.color.title_orange));
        } else {

            viewHolder.tv1.setTextColor(mContext.getResources().getColor(R.color.dark_3));
        }



        return view;
    }

    static class ViewHolder {
        public TextView tv1;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }




    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
        this.notifyDataSetChanged();
    }
}
