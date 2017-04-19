package com.example.host.jsnewmall.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.uu1.nmw.R;
import com.example.host.jsnewmall.activity.OrderDetailsActivity;
import com.example.host.jsnewmall.activity.PayActivity;
import com.example.host.jsnewmall.model.CenterOrderaEntry;
import com.example.host.jsnewmall.model.CenterOrdercEntry;
import com.example.host.jsnewmall.model.OrderListEntry;

import java.io.Serializable;
import java.util.List;

/**
 * Created by host on 2017/3/15.
 */

public class CenterOrdercAdapter extends BaseAdapter {
    private List<CenterOrderaEntry.OrderlistBean> mBodyInfoList;
    private Context mContext;

    public CenterOrdercAdapter(Context context, List<CenterOrderaEntry.OrderlistBean> mlist) {
        this.mContext = context;
        this.mBodyInfoList = mlist;
    }


    @Override
    public int getCount() {
        return mBodyInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return mBodyInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {


        /**
         *  @{@"1":@"待审核",
         *@"2":@"待付款",
         *@"3":@"待确定",
         *@"4":@"已完成",
         *@"5":@"已取消",
         *@"6":@"已删除",
         *@"7":@"已作废",
         *@"8":@"待出游"};
         */
        final CenterOrderaEntry.OrderlistBean mBodyInfo = mBodyInfoList.get(position);
        String orderstate = mBodyInfo.getOrder_state();//订单状态
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_center_order_c_view, null);
            holder.mOrdera = (TextView) convertView.findViewById(R.id.tv_order_c_a);//订单号
            holder.mOrderb = (TextView) convertView.findViewById(R.id.tv_order_c_b);//订单名字
            holder.mOrderc = (TextView) convertView.findViewById(R.id.tv_order_c_c);//出发日期显示
            holder.mOrderd = (TextView) convertView.findViewById(R.id.tv_order_c_d);//金额显示
            holder.mOrdere = (TextView) convertView.findViewById(R.id.tv_order_c_e);//付款状态显示
            holder.mOrderf = (TextView) convertView.findViewById(R.id.tv_order_c_f);//查看详情按钮
            holder.mOrderg = (TextView) convertView.findViewById(R.id.tv_order_c_g);//立即付款按钮显示与否
            holder.mcrqty = (TextView) convertView.findViewById(R.id.tv_crqtyc);//成人
            holder.mrtqty = (TextView) convertView.findViewById(R.id.tv_rtqtyc);//儿童
            holder.mlrqty = (TextView) convertView.findViewById(R.id.tv_lrqtyc);//老人
            holder.mxsqty = (TextView) convertView.findViewById(R.id.tv_xsqtyc);//学生
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }


        holder.mOrdera.setText("订单号:" + mBodyInfo.getOrder_code());
        holder.mOrderb.setText(mBodyInfo.getTeam_title());
        holder.mOrderc.setText(mBodyInfo.getGodate() + "出发");
        holder.mOrderd.setText(mContext.getResources().getString(R.string.search_h) + mBodyInfo.getOrder_total_money() + "元");
//        holder.mOrderd.setText(mContext.getResources().getString(R.string.search_h)+mBodyInfo.getOrderid()+"元");

        if (orderstate.equals("1")) {
            holder.mOrdere.setText("待审核");
        } else if (orderstate.equals("2")) {
            holder.mOrdere.setText("待付款");
        } else if (orderstate.equals("3")) {
            holder.mOrdere.setText("待确定");
        } else if (orderstate.equals("4")) {
            holder.mOrdere.setText("已完成");
        } else if (orderstate.equals("5")) {
            holder.mOrdere.setText("已取消");
        } else if (orderstate.equals("6")) {
            holder.mOrdere.setText("已删除");
        } else if (orderstate.equals("7")) {
            holder.mOrdere.setText("已作废");
        } else if (orderstate.equals("8")) {
            holder.mOrdere.setText("待出游");
        }


        if (mBodyInfo.getCrqty() != 0) {
            holder.mcrqty.setVisibility(View.VISIBLE);
            holder.mcrqty.setText("成人" + mBodyInfo.getCrqty());
        }
        if (mBodyInfo.getRtqty() != 0) {
            holder.mrtqty.setVisibility(View.VISIBLE);
            holder.mrtqty.setText("儿童" + mBodyInfo.getRtqty());
        }
        if (mBodyInfo.getLrqty() != 0) {
            holder.mlrqty.setVisibility(View.VISIBLE);
            holder.mlrqty.setText("老人" + mBodyInfo.getLrqty());
        }
        if (mBodyInfo.getXsqty() != 0) {
            holder.mxsqty.setVisibility(View.VISIBLE);
            holder.mxsqty.setText("学生" + mBodyInfo.getXsqty());
        }



        //查看详情点击
        holder.mOrderf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String orderid=mBodyInfo.getOrderid();
                Intent intent=new Intent(mContext, OrderDetailsActivity.class);
                intent.putExtra("orderid",orderid);
                mContext.startActivity(intent);
            }
        });


        //立即付款点击
        holder.mOrderg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenta=new Intent(mContext, PayActivity.class);
                String orderid=mBodyInfo.getOrderid();
                intenta.putExtra("orderid",orderid);
//                intenta.putExtra("bodyinfo",(Serializable) mBodyInfo);
                mContext.startActivity(intenta);
            }
        });
        return convertView;
    }

    class Holder {
        TextView mOrdera;
        TextView mOrderb;
        TextView mOrderc;
        TextView mOrderd;
        TextView mOrdere;
        TextView mOrderf;
        TextView mOrderg;
        TextView mcrqty;
        TextView mrtqty;
        TextView mlrqty;
        TextView mxsqty;


    }
}