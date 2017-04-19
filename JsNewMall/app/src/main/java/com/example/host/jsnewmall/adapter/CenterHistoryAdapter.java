package com.example.host.jsnewmall.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
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
import com.example.host.jsnewmall.bean.IHistoryInterface;
import com.example.host.jsnewmall.dao.CenterHistoryDao;
import com.example.host.jsnewmall.model.CenterHistoryEntry;
import com.example.host.jsnewmall.utils.BitmapCache;
import com.example.host.jsnewmall.utils.JsonUtils;
import com.example.host.jsnewmall.utils.ToastUtils;
import com.example.host.jsnewmall.utils.UrlUtils;
import com.google.gson.Gson;
import com.uu1.nmw.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by host on 2017/4/10.
 */

public class CenterHistoryAdapter extends BaseAdapter {
    private Context mContext;
    private List<CenterHistoryEntry> mBodylist;
    private RequestQueue queue;
    private IHistoryInterface mIHistoryInterface;
    private ImageLoader imageLoader;
    private int positiondelete;
    private static final int RES_CODE=106;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case RES_CODE:

                    ToastUtils.show(mContext,"删除成功");

                    CenterHistoryDao mDbDao=new CenterHistoryDao(mContext);
                    mDbDao.openDataBase();
                    mBodylist = mDbDao.queryDataList();//查询所有数据
                    notifyDataSetChanged();
                    mDbDao.closeDataBase();
                    break;


                default:
                    break;

            };
        }
    };



    public CenterHistoryAdapter(Context context, List<CenterHistoryEntry> list, RequestQueue queue, IHistoryInterface mIhistoryinterface){
        this.mContext=context;
        this.mBodylist=list;
        this.queue=queue;
        this.mIHistoryInterface=mIhistoryinterface;
        imageLoader = new ImageLoader(queue, new BitmapCache());
    }

    @Override
    public int getCount() {
        if (mBodylist!=null) {
            return mBodylist.size();
        }else {
            return 0;
        }
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
    public View getView(final int position, View convertView, ViewGroup viewGroup) {

        final CenterHistoryEntry mInfo=mBodylist.get(position);
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_activity_collectsearch_view, null);
            holder.mImg=(NetworkImageView)convertView.findViewById(R.id.net_collection_imageview);
            holder.mTvtitle=(TextView)convertView.findViewById(R.id.tv_collection_line_title);
            holder.mTvmoney=(TextView)convertView.findViewById(R.id.tv_collection_line_money);
            holder.mTvCancel=(TextView)convertView.findViewById(R.id.tv_delete_collection);//删除
            holder.mTvCancel.setTag(position);

            holder.mLnlayout=(LinearLayout) convertView.findViewById(R.id.collection_item_content);//布局点击跳转

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }


        holder.mLnlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIHistoryInterface.onHistoryIntent(mInfo.getLineid());
            }
        });




        holder.mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positiondelete = (Integer) v.getTag();
                switch (v.getId())
                {


                    case R.id.tv_delete_collection:


                        CenterHistoryDao mDBDao=new CenterHistoryDao(mContext);
                        mDBDao.openDataBase();
                        mDBDao.deleteData(mBodylist.get(positiondelete).getId());
                        mDBDao.closeDataBase();
                        handler.sendEmptyMessage(RES_CODE);

                        break;
                    default:
                        break;
                }
                //刷新ListView内容
                notifyDataSetChanged();
            }
        });





        holder.mTvCancel.setText("   删除   ");
        holder.mImg.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.mImg.setImageUrl(mInfo.getImgurl(), imageLoader);
        holder.mTvtitle.setText(mInfo.getTitlename());
        holder.mTvmoney.setText(mContext.getResources().getString(R.string.search_h)+mInfo.getTotalprice());

        return convertView;
    }

    class Holder {
        NetworkImageView mImg;
        TextView mTvtitle;
        TextView mTvmoney;
        TextView mTvCancel;
        LinearLayout mLnlayout;
    }


}
