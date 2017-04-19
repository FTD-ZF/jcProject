package com.example.host.jsnewmall.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.host.jsnewmall.adapter.AddcommentAdapter;
import com.example.host.jsnewmall.adapter.FullyGridLayoutManager;
import com.example.host.jsnewmall.adapter.GridImageAdapter;
import com.example.host.jsnewmall.model.AddCommentEntry;
import com.example.host.jsnewmall.model.JsonmModel;
import com.example.host.jsnewmall.model.LoginUserEntry;
import com.example.host.jsnewmall.utils.Base64Utils;
import com.example.host.jsnewmall.utils.HttpUtils;
import com.example.host.jsnewmall.utils.JsonUtils;
import com.example.host.jsnewmall.utils.SharedPreferencesUtils;
import com.example.host.jsnewmall.utils.ToastUtils;
import com.example.host.jsnewmall.utils.UrlUtils;
import com.example.host.jsnewmall.view.HomeForthGridView;
import com.example.host.jsnewmall.view.LoadingDialog;
import com.example.host.jsnewmall.view.starpoints.StarLayoutParams;
import com.example.host.jsnewmall.view.starpoints.StarLinearLayout;
import com.google.gson.Gson;
import com.luck.picture.lib.model.FunctionConfig;
import com.luck.picture.lib.model.PictureConfig;
import com.uu1.nmw.R;
import com.yalantis.ucrop.entity.LocalMedia;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by host on 2017/4/15.
 */

//我要评价
public class AddCommentActivity  extends PicBaseActivity{
    private TextView mTvSubcommment;
    private LinearLayout mBack;
    private GridView mGvSelectType;
    private String[] arrtype = new String[]{"家庭出游", "情侣出游", "朋友出游","团队出游","单独出游","代报名"};
    private int traveltype =0;
    private AddcommentAdapter adapter;
    private String orderid;
    private RecyclerView recyclerView;
    private GridImageAdapter photoadapter;
    private List<LocalMedia> selectMedia = new ArrayList<>();
    private Context mContext;
    private EditText mEtcontent;
    private StarLinearLayout starsLayout;
    private int selectstartype=4;
    private SimpleDateFormat mSimpleTime;
    private String nTime;
    Gson gson=new Gson();
    private LoadingDialog dialog;
    private LoginUserEntry userinfo;
    private String strcontent;

    private static final int FINISH_CODE=100;
    private AddCommentEntry bodyinfo;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FINISH_CODE:
                    dialog.dismiss();
                    ToastUtils.show(AddCommentActivity.this,bodyinfo.getMsg());
                    finish();

                default:
                    break;

            };
        }
    };



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcomment_content);
        userinfo=gson.fromJson(SharedPreferencesUtils.getUserInfo(AddCommentActivity.this),LoginUserEntry.class);

        dialog=new LoadingDialog(AddCommentActivity.this);

        Date d=new Date();
        mSimpleTime=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        nTime=mSimpleTime.format(d);

        mContext = this;
        getIntentData();
        initView();
        initListener();


    }
    private void getIntentData(){
        Intent intent=getIntent();
        orderid=intent.getStringExtra("orderid");
    }

    private void initView(){


        TextView mTvTitlte=(TextView)findViewById(R.id.tv_title_name_change);//标题
        mTvTitlte.setText("发表评论");
        mTvTitlte.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_6));
        ImageView mImgMessage=(ImageView)findViewById(R.id.img_title_message);//更多图案显示
        mImgMessage.setVisibility(View.GONE);
        mTvSubcommment=(TextView)findViewById(R.id.tv_title_add_traveller);//右上角标题-发布
        mTvSubcommment.setVisibility(View.VISIBLE);
        mTvSubcommment.setText("发布");
        mBack=(LinearLayout)findViewById(R.id.iv_back);


        mGvSelectType=(GridView)findViewById(R.id.gv_select_commenttype);//选择类型
        adapter=new AddcommentAdapter(AddCommentActivity.this);
        mGvSelectType.setAdapter(adapter);
        HomeForthGridView.setListViewHeightBasedOnChildren(mGvSelectType);

        recyclerView=(RecyclerView)findViewById(R.id.photo_recycler);//上传照片

        FullyGridLayoutManager manager = new FullyGridLayoutManager(AddCommentActivity.this, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        photoadapter = new GridImageAdapter(AddCommentActivity.this, onAddPicClickListener);
        photoadapter.setSelectMax(9);
        recyclerView.setAdapter(photoadapter);

        mEtcontent=(EditText)findViewById(R.id.et_addcomment_content);//输入框内容

        starsLayout=(StarLinearLayout)findViewById(R.id.starsLayout);//评分布局
        StarLayoutParams params = new StarLayoutParams();
        params.setNormalStar(getResources().getDrawable(R.mipmap.icon_collection_c))
                .setSelectedStar(getResources().getDrawable(R.mipmap.icon_collection_d))
                .setSelectable(true)
                .setSelectedStarNum(4)
                .setTotalStarNum(5)
                .setStarHorizontalSpace(25);
        starsLayout.setStarParams(params);



    }

    private void initListener(){

        OnClickListenerImpl listener = new OnClickListenerImpl();
        mBack.setOnClickListener(listener);
        mTvSubcommment.setOnClickListener(listener);

        mGvSelectType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                traveltype=i+1;
                adapter.setselectiontypenum(i);
                adapter.notifyDataSetChanged();


            }
        });


        photoadapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                // 这里可预览图片
                PictureConfig.getPictureConfig().externalPicturePreview(mContext, position, selectMedia);
            }
        });


        //评分星星点击
        starsLayout.setonClick(new StarLinearLayout.ICoallBack() {
            @Override
            public void onClickButton(int s) {
                selectstartype=s;
                ToastUtils.show(getApplicationContext(),s+"");
            }
        });


    }

    private class OnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.iv_back:
                    finish();
                    break;
                //发布
                case R.id.tv_title_add_traveller:
                     strcontent=mEtcontent.getText().toString();



                    if (traveltype==0){
                        ToastUtils.show(AddCommentActivity.this,"请选择出游类型");
                        return;
                    }
                    if (strcontent.equals("")){
                        ToastUtils.show(AddCommentActivity.this,"请输入内容");
                        return;
                    }


                    if(selectMedia.size()==0){
                        ToastUtils.show(AddCommentActivity.this,"请选择图片");
                        return;
                    }



                    initputData();;
                    break;


                default:
                    break;

            }
        }
    }



    private void initputData(){

        JSONObject jbody=null;
        try {
            jbody = new JSONObject();
            jbody.put("order_id",orderid);
            jbody.put("user_id", userinfo.getUserid());
            jbody.put("star",selectstartype);//星级
            jbody.put("comment",strcontent);//	评论内容
            jbody.put("travel_type",traveltype);//出游性质(0其它、1家庭、2情侣、3朋友、4团队、5单独、6代报名)
            jbody.put("addtime",mSimpleTime);

            jbody.put("method","AddOrderComment");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
        dohttpAddComment(UrlUtils.ROUTE_LINE,jbodyB);

    }




    //压缩图片
    private Bitmap convertToBitmap(String path , int w, int h) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        // 设置为ture只获取图片大小
        opts.inJustDecodeBounds = true;
        opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
        // 返回为空
        BitmapFactory.decodeFile(path, opts);
        int width = opts.outWidth;
        int height = opts.outHeight;
        float scaleWidth = 0.f, scaleHeight = 0.f;
        if (width > w || height > h) {
            // 缩放
            scaleWidth = ((float) width) / w;
            scaleHeight = ((float) height) / h;
        }
        opts.inJustDecodeBounds = false;
        float scale = Math.max(scaleWidth, scaleHeight);
        opts.inSampleSize = (int)scale;
        WeakReference<Bitmap> weak = new WeakReference<Bitmap>(BitmapFactory.decodeFile(path, opts));
        return Bitmap.createScaledBitmap(weak.get(), w, h, true);
    }




    //线路详情
    protected  void dohttpAddComment(String url,JSONObject  paramhash){
        dialog.show();
        HttpUtils.dopost(url,getApplicationContext(),paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());

                bodyinfo=gson.fromJson(body, AddCommentEntry.class);

                handler.sendEmptyMessage(FINISH_CODE);

            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }


    /**
     * 删除图片回调接口
     */

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick(int type, int position) {
            switch (type) {
                case 0:
                    // 进入相册
                    /**
                     * type --> 1图片 or 2视频
                     * copyMode -->裁剪比例，默认、1:1、3:4、3:2、16:9
                     * maxSelectNum --> 可选择图片的数量
                     * selectMode         --> 单选 or 多选
                     * isShow       --> 是否显示拍照选项 这里自动根据type 启动拍照或录视频
                     * isPreview    --> 是否打开预览选项
                     * isCrop       --> 是否打开剪切选项
                     * isPreviewVideo -->是否预览视频(播放) mode or 多选有效
                     * ThemeStyle -->主题颜色
                     * CheckedBoxDrawable -->图片勾选样式
                     * cropW-->裁剪宽度 值不能小于100  如果值大于图片原始宽高 将返回原图大小
                     * cropH-->裁剪高度 值不能小于100
                     * isCompress -->是否压缩图片
                     * setEnablePixelCompress 是否启用像素压缩
                     * setEnableQualityCompress 是否启用质量压缩
                     * setRecordVideoSecond 录视频的秒数，默认不限制
                     * setRecordVideoDefinition 视频清晰度  Constants.HIGH 清晰  Constants.ORDINARY 低质量
                     * setImageSpanCount -->每行显示个数
                     * setCheckNumMode 是否显示QQ选择风格(带数字效果)
                     * setPreviewColor 预览文字颜色
                     * setCompleteColor 完成文字颜色
                     * setPreviewBottomBgColor 预览界面底部背景色
                     * setBottomBgColor 选择图片页面底部背景色
                     * setCompressQuality 设置裁剪质量，默认无损裁剪
                     * setSelectMedia 已选择的图片
                     * setCompressFlag 1为系统自带压缩  2为第三方luban压缩
                     * 注意-->type为2时 设置isPreview or isCrop 无效
                     * 注意：Options可以为空，默认标准模式
                     */
//                    String ws = et_w.getText().toString().trim();
//                    String hs = et_h.getText().toString().trim();

//                    if (!isNull(ws) && !isNull(hs)) {
//                        cropW = Integer.parseInt(ws);
//                        cropH = Integer.parseInt(hs);
//                    }

//                    if (!isNull(et_compress_width.getText().toString()) && !isNull(et_compress_height.getText().toString())) {
//                        compressW = Integer.parseInt(et_compress_width.getText().toString());
//                        compressH = Integer.parseInt(et_compress_height.getText().toString());
//                    }

                    int selector = R.drawable.select_cb;
                    FunctionConfig config = new FunctionConfig();
                    config.setType(1);
//                    config.setCopyMode(copyMode);
//                    config.setCompress(isCompress);
                    config.setEnablePixelCompress(true);
                    config.setEnableQualityCompress(true);
                    config.setMaxSelectNum(9);
                    config.setSelectMode(1);
                    config.setShowCamera(true);
                    config.setEnablePreview(true);
                    config.setEnableCrop(false);
                    config.setPreviewVideo(false);
//                    config.setRecordVideoDefinition(FunctionConfig.HIGH);// 视频清晰度
//                    config.setRecordVideoSecond(60);// 视频秒数
//                    config.setCropW(cropW);
//                    config.setCropH(cropH);
                    config.setCheckNumMode(false);
                    config.setCompressQuality(100);
                    config.setImageSpanCount(4);
                    config.setSelectMedia(selectMedia);
//                    config.setCompressFlag(compressFlag);
//                    config.setCompressW(compressW);
//                    config.setCompressH(compressH);
//                    if (theme) {
//                        config.setThemeStyle(ContextCompat.getColor(PictureSelectActivity.this, R.color.blue));
//                        // 可以自定义底部 预览 完成 文字的颜色和背景色
//                        if (!isCheckNumMode) {
//                            // QQ 风格模式下 这里自己搭配颜色，使用蓝色可能会不好看
//                            config.setPreviewColor(ContextCompat.getColor(PictureSelectActivity.this, R.color.white));
//                            config.setCompleteColor(ContextCompat.getColor(PictureSelectActivity.this, R.color.white));
//                            config.setPreviewBottomBgColor(ContextCompat.getColor(PictureSelectActivity.this, R.color.blue));
//                            config.setBottomBgColor(ContextCompat.getColor(PictureSelectActivity.this, R.color.blue));
//                        }
//                    }
//                    if (selectImageType) {
//                        config.setCheckedBoxDrawable(selector);
//                    }

                    // 先初始化参数配置，在启动相册
                    PictureConfig.init(config);
                    PictureConfig.getPictureConfig().openPhoto(mContext, resultCallback);

                    // 只拍照
                    //PictureConfig.getPictureConfig().startOpenCamera(mContext, resultCallback);
                    break;
                case 1:
                    // 删除图片
                    selectMedia.remove(position);
                    photoadapter.notifyItemRemoved(position);
                    break;
            }
        }
    };


    /**
     * 图片回调方法
     */
    private PictureConfig.OnSelectResultCallback resultCallback = new PictureConfig.OnSelectResultCallback() {
        @Override
        public void onSelectSuccess(List<LocalMedia> resultList) {
            selectMedia = resultList;
            Log.i("callBack_result", selectMedia.size() + "");

            if (selectMedia != null) {
                photoadapter.setList(selectMedia);
                photoadapter.notifyDataSetChanged();
            }
        }
    };



    /**
     * 判断 一个字段的值否为空
     *
     * @param s
     * @return
     * @author Michael.Zhang 2013-9-7 下午4:39:00
     */

    public boolean isNull(String s) {
        if (null == s || s.equals("") || s.equalsIgnoreCase("null")) {
            return true;
        }

        return false;
    }




}
