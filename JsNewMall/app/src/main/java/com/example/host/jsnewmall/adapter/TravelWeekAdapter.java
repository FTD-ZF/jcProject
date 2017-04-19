package com.example.host.jsnewmall.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.host.jsnewmall.model.HomeViewPagerInfo;
import com.example.host.jsnewmall.model.TravelWeekendEntry;
import com.example.host.jsnewmall.utils.BitmapCache;

import java.util.List;

/**
 * Created by host on 2017/2/16.
 */

public class TravelWeekAdapter  extends PagerAdapter{

    private Context mContext;
    private ImageLoader imageLoader;
    private RequestQueue queue;
    private TravelWeekendEntry mBodyinfo;
    public TravelWeekAdapter(Context context, RequestQueue queue, TravelWeekendEntry bodyinfo) {

        this.mContext = context;
        this.queue=queue;
        this.mBodyinfo=bodyinfo;
        imageLoader = new ImageLoader(queue, new BitmapCache());


    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final TravelWeekendEntry.ListBean.BannerBean bannerInfo = mBodyinfo.getList().getBanner().get(position
                % mBodyinfo.getList().getBanner().size());
        NetworkImageView imageView = new NetworkImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageUrl(bannerInfo.getImg(), imageLoader);

//        imageView.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.testpicture));
        container.addView(imageView);

        return imageView;
    }
}
