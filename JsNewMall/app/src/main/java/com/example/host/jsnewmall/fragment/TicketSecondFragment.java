package com.example.host.jsnewmall.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.example.host.jsnewmall.view.PhoneView;
import com.uu1.nmw.R;
import com.example.host.jsnewmall.adapter.HotelFirstListAdapter;
import com.example.host.jsnewmall.adapter.TicketSecondAdapter;

/**
 * Created by host on 2017/2/22.
 */

//天目湖
public class TicketSecondFragment extends BaseFragment {
    private View view;
    private TextView mTvFirstContent;
    private ListView mFirstListView;
    private RequestQueue queue;
    private NetworkImageView mNetImageFirst;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_hotel_first_content,null);
        initView();
        return view;
    }
    private void initView(){

        queue = Volley.newRequestQueue(getActivity());
        LayoutInflater  head = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);//Listview的头部

        View headerView = head.inflate(R.layout.fragment_hotel_headerview, null);
        mNetImageFirst=(NetworkImageView) headerView.findViewById(R.id.netimg_hotel_first_a);//头部图片
        mTvFirstContent=(TextView) headerView.findViewById(R.id.tv_hotel_first_content);//显示文字说明

        mNetImageFirst.setBackground(getActivity().getResources().getDrawable(R.mipmap.icon_ticket_second_banner));


        mTvFirstContent.setText("\u3000\u3000"+getActivity().getResources().getString(R.string.ticket_a_b));


        mFirstListView=(ListView) view.findViewById(R.id.list_hotel_view);  //图片显示列表
        mFirstListView.addHeaderView(headerView);
        TicketSecondAdapter mFirstAdapter=new TicketSecondAdapter(getActivity(),queue);
        mFirstListView.setAdapter(mFirstAdapter);


        LinearLayout mCallPhone=(LinearLayout)view.findViewById(R.id.ln_submit_hotel);//电话咨询
        mCallPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneView.onCallphone(getActivity());
            }
        });

    }
}
