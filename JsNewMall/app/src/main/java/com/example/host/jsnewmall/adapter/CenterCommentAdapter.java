package com.example.host.jsnewmall.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.host.jsnewmall.model.CenterComentChangeEntry;
import com.example.host.jsnewmall.model.CenterCommentEntry;
import com.example.host.jsnewmall.utils.BitmapCache;
import com.example.host.jsnewmall.view.CenterCircleImageView;
import com.example.host.jsnewmall.view.ImagePagerView.ImagePagerActivity;
import com.example.host.jsnewmall.view.starpoints.StarLayoutParams;
import com.example.host.jsnewmall.view.starpoints.StarLinearLayout;
import com.uu1.nmw.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by host on 2017/4/18.
 */

public class CenterCommentAdapter extends BaseAdapter {

    private Context mContext;
    private List<CenterComentChangeEntry.DataBean.CommentInfoBean> mBodylist;
    private RequestQueue queue;
    private ImageLoader imageLoader;
    public CenterCommentAdapter(Context context, List<CenterComentChangeEntry.DataBean.CommentInfoBean> list, RequestQueue queue){
        this.mContext=context;
        this.mBodylist=list;
        this.queue=queue;
        imageLoader = new ImageLoader(queue, new BitmapCache());
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
    public long getItemId(int posititon) {
        return posititon;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final CenterComentChangeEntry.DataBean.CommentInfoBean contentinfo=mBodylist.get(position);
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_activity_centercomment_view, null);
            holder.mImga=(ImageView) convertView.findViewById(R.id.img_comment_touxiang);//头像
            holder.tva=(TextView) convertView.findViewById(R.id.tv_master_name);//名字
            holder.mStarlayout=(StarLinearLayout) convertView.findViewById(R.id.starsLayout);//x星级
            holder.tvb=(TextView) convertView.findViewById(R.id.tv_comment_content);//内容
            holder.mGva=(GridView)convertView.findViewById(R.id.gv_comment_photo);//图片展示
            holder.mLncus=(LinearLayout)convertView.findViewById(R.id.ln_comment_cuslayout);//客服布局
            holder.tvc=(TextView)convertView.findViewById(R.id.tv_answer_content);//客服回答内容



            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }



        CenterCircleImageView circleImageView=new CenterCircleImageView(mContext);
        if (contentinfo.getUser()!=null) {
            circleImageView.readBitmapViaVolley(contentinfo.getUser().get(0).getHeadimgurl(), holder.mImga);//头像
            holder.tva.setText(contentinfo.getUser().get(0).getNickname());
        }


        StarLayoutParams params = new StarLayoutParams();
        params.setNormalStar(mContext.getResources().getDrawable(R.mipmap.icon_collection_c))
                .setSelectedStar(mContext.getResources().getDrawable(R.mipmap.icon_collection_d))
                .setSelectable(false)
                .setSelectedStarNum(Integer.parseInt(contentinfo.getStar()))
                .setTotalStarNum(5)
                .setStarHorizontalSpace(1);
        holder.mStarlayout.setStarParams(params);


        holder.tvb.setText(contentinfo.getComment());



        if (contentinfo.getPicture()!=null) {
            final ArrayList<CenterComentChangeEntry.DataBean.CommentInfoBean.PictureBean> imageUrls = (ArrayList<CenterComentChangeEntry.DataBean.CommentInfoBean.PictureBean>) contentinfo.getPicture();
            CommentPicAdapter picadapter = new CommentPicAdapter(mContext, contentinfo.getPicture(), queue);
            holder.mGva.setAdapter(picadapter);
            picadapter.notifyDataSetChanged();

            holder.mGva.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if(i==3) {
                        imageBrower(0, imageUrls);
                    }else {
                        imageBrower(i, imageUrls);

                    }
                }
            });




        }

        if (contentinfo.getAnswer().size()==0){
            holder.mLncus.setVisibility(View.GONE);

        }else {
            holder.mLncus.setVisibility(View.VISIBLE);
            holder.tvc.setText("客服回复："+contentinfo.getAnswer().get(0).getContent());
        }



        return convertView;
    }



    class Holder {
        ImageView mImga;

        TextView tva;
        StarLinearLayout mStarlayout;
        TextView tvb;
        GridView mGva;
        LinearLayout mLncus;
        TextView tvc;

        NetworkImageView networkImageView;

    }

    /**
     * 打开图片查看器
     *
     * @param position
     * @param urls2
     */
    protected void imageBrower(int position,ArrayList<CenterComentChangeEntry.DataBean.CommentInfoBean.PictureBean> urls2) {
        Intent intent = new Intent(mContext, ImagePagerActivity.class);
        // 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, (Serializable) urls2);
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
        mContext.startActivity(intent);
    }
}
