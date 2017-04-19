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
import com.example.host.jsnewmall.bean.ICollectionInterface;
import com.example.host.jsnewmall.model.CollectionSearchEntry;
import com.example.host.jsnewmall.model.EditNameSuccessEntry;
import com.example.host.jsnewmall.model.JsonmModel;
import com.example.host.jsnewmall.model.PostInfoSuccessEntry;
import com.example.host.jsnewmall.utils.Base64Utils;
import com.example.host.jsnewmall.utils.BitmapCache;
import com.example.host.jsnewmall.utils.HttpUtils;
import com.example.host.jsnewmall.utils.JsonUtils;
import com.example.host.jsnewmall.utils.ToastUtils;
import com.example.host.jsnewmall.utils.UrlUtils;
import com.google.gson.Gson;
import com.uu1.nmw.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by host on 2017/3/31.
 */

public class CollectListAdapter  extends BaseAdapter{
    private Context mContext;
    private List<CollectionSearchEntry.DataBean> mBodyinfo;
    private String nTime;
    private int positiondelete;
    Gson gson=new Gson();
    private static final int RES_CODE=106;
    private static final int RES_CODE_FAIL=107;
    private RequestQueue queue;

    private ImageLoader imageLoader;

    private ICollectionInterface iCollectionInterface;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case RES_CODE:
                    mBodyinfo.remove(positiondelete);
                    ToastUtils.show(mContext,"取消收藏");
                    notifyDataSetChanged();
                    break;

                case RES_CODE_FAIL:
                    ToastUtils.show(mContext,"取消失败");
                    break;

                default:
                    break;

            };
        }
    };


    public CollectListAdapter(Context context, List<CollectionSearchEntry.DataBean> bodyinfo, String nTime,
                              RequestQueue queue, ICollectionInterface iCollectionInterface){
        this.mBodyinfo=bodyinfo;
        this.mContext=context;
        this.nTime=nTime;
        this.queue=queue;
        this.iCollectionInterface=iCollectionInterface;
        imageLoader = new ImageLoader(queue, new BitmapCache());
    }
    @Override
    public int getCount() {
        return mBodyinfo.size();
    }

    @Override
    public Object getItem(int position) {
        return mBodyinfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final CollectionSearchEntry.DataBean mInfo=mBodyinfo.get(position);
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
                iCollectionInterface.onCollectionIntent(mInfo.getLines_id());
            }
        });


        holder.mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positiondelete = (Integer) v.getTag();
                switch (v.getId())
                {


                    case R.id.tv_delete_collection:


                        JSONObject jbody=null;
                        try {
                            jbody = new JSONObject();
                            jbody.put("id",mBodyinfo.get(positiondelete).getId());
                            jbody.put("method","FavCancel");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
                        dohttpdeleteitem(UrlUtils.CONTENT,jbodyB);
                        break;
                    default:
                        break;
                }
                //刷新ListView内容
                notifyDataSetChanged();
            }
        });




        holder.mImg.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.mImg.setImageUrl(mInfo.getLines_image(), imageLoader);
        holder.mTvtitle.setText(mInfo.getLines_title());
        holder.mTvmoney.setText(mContext.getResources().getString(R.string.search_h)+mInfo.getLines_price());


        return convertView;
    }

    class Holder {
        NetworkImageView mImg;
        TextView mTvtitle;
        TextView mTvmoney;
        TextView mTvCancel;
        LinearLayout mLnlayout;
    }



    protected  void dohttpdeleteitem(String url,JSONObject  paramhash){
        HttpUtils.dopost(url,mContext,paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());
                EditNameSuccessEntry rescode=gson.fromJson(body,EditNameSuccessEntry.class);
                int finalcode=rescode.getRes();
                if (finalcode==1){
                    handler.sendEmptyMessage(RES_CODE);
                }else {
                    handler.sendEmptyMessage(RES_CODE_FAIL);
                }
            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }
}
