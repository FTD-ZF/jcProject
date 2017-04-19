package com.example.host.jsnewmall.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.host.jsnewmall.model.EditNameSuccessEntry;
import com.example.host.jsnewmall.model.JsonmModel;
import com.example.host.jsnewmall.model.PicUploadEntry;
import com.example.host.jsnewmall.model.SetUpEntry;
import com.example.host.jsnewmall.utils.Base64;
import com.example.host.jsnewmall.utils.Base64Utils;
import com.example.host.jsnewmall.utils.HttpUtils;
import com.example.host.jsnewmall.utils.JsonUtils;
import com.example.host.jsnewmall.utils.SavePhotoUtils;
import com.example.host.jsnewmall.utils.ToastUtils;
import com.example.host.jsnewmall.utils.UrlUtils;
import com.example.host.jsnewmall.view.CenterCircleImageView;
import com.example.host.jsnewmall.view.LoadingDialog;
import com.google.gson.Gson;
import com.uu1.nmw.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by host on 2017/3/24.
 */

public class SetUpActivity extends BaseActivity {

    private SimpleDateFormat mSimpleTime;
    private String nTime;
    Gson gson=new Gson();
    private LinearLayout mBack;
    private RelativeLayout mRlsetupa,mRlsetupb,mRlsetupc,mRlsetupd,mRlsetupe,mRlsetupf;
    private ImageView mImgtouxiang;
    private TextView mTvUsername,mTvNickname,mTvRealname,mTvSex,mTvBirthday,mTvIdentitynum;
    private LoadingDialog dialog;
    private  String userid;
    private SetUpEntry mSetupinfo;
    private static final int FINISH_CODE=100;
    private static final int FINISH_CODE_A=101;
    private static final int FINISH_CODE_S=102;
    private static final int FINISH_CODE_E=103;
    private static final int FINISH_CODE_G=104;
    private CenterCircleImageView circleImageView;
    private String dateb;

    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;
    private String imagePath;//上传头像url
    private EditNameSuccessEntry mEtinfo;
    private  AlertDialog dialogphoto;//照相弹出
    private PicUploadEntry mPicInfo;//返回的图片路径
    private Bitmap  photo;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FINISH_CODE:
                    dialog.dismiss();
                    initView();
                    initListener();
                    break;
                case FINISH_CODE_A:
                    initData();
                    break;
                case FINISH_CODE_S:
                    dialog.dismiss();
                    ToastUtils.show(SetUpActivity.this,"保存成功");
                    break;
                case FINISH_CODE_E:
                    dialog.dismiss();
                    ToastUtils.show(SetUpActivity.this,"保存失败");
                    break;
                case  FINISH_CODE_G:
                    dialog.dismiss();
                    circleImageView.readBitmapViaVolley(mPicInfo.getMsg(),mImgtouxiang);
                    initPutdata(mPicInfo.getMsg());
//                    initPutimg(mPicInfo.getMsg());


                    break;
                default:
                    break;

            };
        }
    };




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_content);
        dialog=new LoadingDialog(SetUpActivity.this);
        dialog.show();
        Date d=new Date();
        mSimpleTime=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        nTime=mSimpleTime.format(d);
        getIntentData();
        initData();

        TextView mTvTitlte=(TextView)findViewById(R.id.tv_title_name_change);//标题
        mTvTitlte.setText("个人资料");
        mTvTitlte.setTextColor(getApplicationContext().getResources().getColor(R.color.dark_6));
        ImageView mImgMessage=(ImageView)findViewById(R.id.img_title_message);//更多图案显示
        mImgMessage.setVisibility(View.GONE);
    }

    private void getIntentData(){
        Intent intent=getIntent();
        userid=intent.getStringExtra("userid");
    }

    private void initData(){
        JSONObject jbody=null;
        try {
            jbody = new JSONObject();
            jbody.put("uid",userid);
            jbody.put("method","QueryUserInfo");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
        dohttpGetiserinfo(UrlUtils.USER,jbodyB);
    }

    private void initView(){


        mBack=(LinearLayout)findViewById(R.id.iv_back);//返回

        mRlsetupa=(RelativeLayout)findViewById(R.id.rl_setup_a);//头像点击
        mRlsetupb=(RelativeLayout)findViewById(R.id.rl_setup_b);//昵称点击
        mRlsetupc=(RelativeLayout)findViewById(R.id.rl_setup_c);//真实姓名点击
        mRlsetupd=(RelativeLayout)findViewById(R.id.rl_setup_d);//性别点击
        mRlsetupe=(RelativeLayout)findViewById(R.id.rl_setup_e);//生日点击
        mRlsetupf=(RelativeLayout)findViewById(R.id.rl_setup_f);//身份证点击

        mImgtouxiang=(ImageView)findViewById(R.id.img_setup_touxiang);//头像显示
        mTvUsername=(TextView)findViewById(R.id.tv_username);//用户名
        mTvNickname=(TextView)findViewById(R.id.tv_nickname);//昵称
        mTvRealname=(TextView)findViewById(R.id.tv_realname);//真实姓名
        mTvSex=(TextView)findViewById(R.id.tv_sex);//性别
        mTvBirthday=(TextView)findViewById(R.id.tv_birthday);//生日
        mTvIdentitynum=(TextView)findViewById(R.id.tv_identitynum);//身份证


        circleImageView=new CenterCircleImageView(SetUpActivity.this);//设置网络圆形头像
        circleImageView.readBitmapViaVolley(mSetupinfo.getData().getHeadimgurl(),mImgtouxiang);//设置头像
        mTvUsername.setText(mSetupinfo.getData().getUser_name());
        mTvNickname.setText(mSetupinfo.getData().getNickname());
        mTvRealname.setText(mSetupinfo.getData().getRealname());

        String sextext="";
        if (mSetupinfo.getData().getSex().equals("0")){
            sextext="女";
        }else if (mSetupinfo.getData().getSex().equals("1")){
            sextext="男";
        }
        mTvSex.setText(sextext);
        if (mSetupinfo.getData().getBirthday()!=null) {
            String[] datea = mSetupinfo.getData().getBirthday().split(" ");
            dateb = datea[0];
            mTvBirthday.setText(dateb);
        }else {
            mTvBirthday.setText("");
        }
        mTvIdentitynum.setText(mSetupinfo.getData().getCard_number());


    }
    private void initListener(){
        OnClickListenerImpl listener = new OnClickListenerImpl();
        mBack.setOnClickListener(listener);
        mRlsetupa.setOnClickListener(listener);
        mRlsetupb.setOnClickListener(listener);
        mRlsetupc.setOnClickListener(listener);
        mRlsetupd.setOnClickListener(listener);
        mRlsetupe.setOnClickListener(listener);
        mRlsetupf.setOnClickListener(listener);
    }


    private class OnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.iv_back:
//                    setResult(75);
                    finish();
                    break;
                //头像点击
                case R.id.rl_setup_a:

                    showChoosePicDialog();

                    break;
                //昵称点击
                case R.id.rl_setup_b:
                    Intent intentb=new Intent(SetUpActivity.this,ChangeUserInfoActivity.class);
                    intentb.putExtra("usercode","1");
                    intentb.putExtra("userid",userid);
                    intentb.putExtra("editname",mSetupinfo.getData().getNickname());
                    startActivityForResult(intentb,36);
                    break;
                //真实姓名
                case R.id.rl_setup_c:
                    Intent intentc=new Intent(SetUpActivity.this,ChangeUserInfoActivity.class);
                    intentc.putExtra("usercode","2");
                    intentc.putExtra("userid",userid);
                    intentc.putExtra("editname",mSetupinfo.getData().getRealname());
                    startActivityForResult(intentc,37);
                    break;
                //性别
                case R.id.rl_setup_d:
                    Intent intentd=new Intent(SetUpActivity.this,ChangeSexActivity.class);
                    intentd.putExtra("userid",userid);
                    intentd.putExtra("sextype",mSetupinfo.getData().getSex());
                    startActivityForResult(intentd,39);
                    break;
                //生日点击
                case R.id.rl_setup_e:
                    Intent intente=new Intent(SetUpActivity.this,ChangeBirthActivity.class);
                    intente.putExtra("userid",userid);
                    intente.putExtra("birthday",dateb);
                    startActivityForResult(intente,40);

                    break;
                //身份证
                case R.id.rl_setup_f:
                    Intent intentf=new Intent(SetUpActivity.this,ChangeUserInfoActivity.class);
                    intentf.putExtra("usercode","3");
                    intentf.putExtra("userid",userid);
                    intentf.putExtra("editname",mSetupinfo.getData().getCard_number());
                    startActivityForResult(intentf,38);
                    break;

                default:
                    break;


            }
        }
    }




    protected  void dohttpGetiserinfo(String url,JSONObject  paramhash){
        HttpUtils.dopost(url,SetUpActivity.this,paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {
                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());
                mSetupinfo=gson.fromJson(body, SetUpEntry.class);
                handler.sendEmptyMessage(FINISH_CODE);
            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==45){
            handler.sendEmptyMessage(FINISH_CODE_A);
        }


        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上

                        dialogphoto.dismiss();

                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        //参数1转换类型，参数2压缩质量，参数3字节流资源
                        photo.compress(Bitmap.CompressFormat.JPEG, 100, out);
                        byte[] appicon = out.toByteArray();// 转为byte数组
                        try {
                            out.flush();
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        initUploadimg(Base64.encode(appicon));//上传图片获取地址
                    }
                    break;
            }
        }

    }

    @Override
    public void onBackPressed() {
//        setResult(75);
        finish();
    }

    private void initUploadimg(String photo){
        JSONObject jbody=null;
        try {
            jbody = new JSONObject();

            jbody.put("photo",photo);
            jbody.put("method","App_load_pic");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
        dohttpputfileupload(UrlUtils.APP_URL,jbodyB);
    }


    private void initPutdata(String imgurl){
        JSONObject jbody=null;
        try {
            jbody = new JSONObject();
            jbody.put("uid",userid);
            jbody.put("headimgurl",imgurl);
            jbody.put("method","EditUserInfo");
            jbody.put("last_login_time",nTime);
            jbody.put("last_login_ip","110.110.110.110");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
        dohttpputuserinfo(UrlUtils.USER,jbodyB);
    }


    private void initPutimg(String imgurl){
        JSONObject jbody=null;
        try {
            jbody = new JSONObject();
            jbody.put("typeID",19);
            jbody.put("file",imgurl);
            jbody.put("cityID",115);
            jbody.put("method","UploadImg");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
        dohttpputimginfo(UrlUtils.USER,jbodyB);
    }


    /**
     * 显示修改头像的对话框
     */
    protected void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(SetUpActivity.this, R.layout.alertdialog_view, null);
        builder.setView(view);
//        builder.setTitle("设置头像");
//        String[] items = { "选择本地照片", "拍照" };
//        builder.setNegativeButton("取消", null);
//        builder.setItems(items, new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                switch (which) {
//                    case CHOOSE_PICTURE: // 选择本地照片
//                        Intent openAlbumIntent = new Intent(
//                                Intent.ACTION_GET_CONTENT);
//                        openAlbumIntent.setType("image/*");
//                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
//                        break;
//                    case TAKE_PICTURE: // 拍照
//                        Intent openCameraIntent = new Intent(
//                                MediaStore.ACTION_IMAGE_CAPTURE);
//                        tempUri = Uri.fromFile(new File(Environment
//                                .getExternalStorageDirectory(), "image.jpg"));
//                        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
//                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
//                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
//                        break;
//                }
//            }
//        });
//        builder.create().show();
        dialogphoto = builder.create();
        dialogphoto.show();
        LinearLayout mLntakephotoa=(LinearLayout)view.findViewById(R.id.ln_take_photo_a);//选择照片
        LinearLayout mLntakephotob=(LinearLayout)view.findViewById(R.id.ln_take_photo_b);//拍照
        LinearLayout mLntakephotoc=(LinearLayout)view.findViewById(R.id.ln_take_photo_c);//取消
        mLntakephotoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
            }
        });

        mLntakephotob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openCameraIntent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        tempUri = Uri.fromFile(new File(Environment
                                .getExternalStorageDirectory(), "image.jpg"));
                        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
            }
        });

        mLntakephotoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogphoto.dismiss();
            }
        });

    }


    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            return;
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
//        intent.putExtra("outputX", 150);
//        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }



    /**
     * 保存裁剪之后的图片数据
     *
     * @param
     *
     * @param
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
              photo = extras.getParcelable("data");
//            photo = Utils.toRoundBitmap(photo, tempUri); // 这个时候的图片已经被处理成圆形的了
//            mImgtouxiang.setImageBitmap(photo);//直接显示选的图片
            uploadPic(photo);

        }
    }


    private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了

        imagePath = SavePhotoUtils.savePhoto(bitmap, Environment
                .getExternalStorageDirectory().getAbsolutePath(), String
                .valueOf(System.currentTimeMillis()));


    }


    /**
     * 上传图片url更改头像
     * @param url
     * @param paramhash
     */
    protected  void dohttpputuserinfo(String url,JSONObject  paramhash){
        dialog.show();
        HttpUtils.dopost(url,SetUpActivity.this,paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {
                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());
                mEtinfo=gson.fromJson(body, EditNameSuccessEntry.class);
                if (mEtinfo.getRes()==1){
                    handler.sendEmptyMessage(FINISH_CODE_S);
                }else{
                    handler.sendEmptyMessage(FINISH_CODE_E);
                }

            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }

    /**
     * 上传图片获取路劲
     * @param url
     * @param paramhash
     */
    protected  void dohttpputfileupload(String url,JSONObject  paramhash){
        dialog.show();
        HttpUtils.dopost(url,SetUpActivity.this,paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {
                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());
                mPicInfo=gson.fromJson(body,PicUploadEntry.class);
//                Log.d("shuchu",mPicInfo.getMsg()+"");


                handler.sendEmptyMessage(FINISH_CODE_G);
            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }


    /**
     * 把图片保存至服务器
     * @param url
     * @param paramhash
     */
    protected  void dohttpputimginfo(String url,JSONObject  paramhash){
        dialog.show();
        HttpUtils.dopost(url,SetUpActivity.this,paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {
                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());


            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }


}
