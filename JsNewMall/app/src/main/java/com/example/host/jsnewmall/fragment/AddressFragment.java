package com.example.host.jsnewmall.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;


import com.example.host.jsnewmall.activity.SearchActivity;
import com.example.host.jsnewmall.adapter.AddressMenuAdapter;
import com.example.host.jsnewmall.adapter.AddressRightAdapter;
import com.example.host.jsnewmall.bean.IRefreshInterface;
import com.example.host.jsnewmall.model.AddressEntry;
import com.example.host.jsnewmall.model.JsonmModel;
import com.example.host.jsnewmall.utils.Base64Utils;
import com.example.host.jsnewmall.utils.HttpUtils;
import com.example.host.jsnewmall.utils.JsonUtils;

import com.example.host.jsnewmall.utils.SharedPreferencesUtils;
import com.example.host.jsnewmall.utils.ToastUtils;
import com.example.host.jsnewmall.utils.UrlUtils;
import com.example.host.jsnewmall.view.LoadingDialog;
import com.google.gson.Gson;
import com.uu1.nmw.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by host on 2017/2/15.
 */

public class AddressFragment extends BaseFragment {
    private View view;
    private ListView mListMenu,mListHome;
    private AddressMenuAdapter mMenuAdapter;
    private LoadingDialog dialog;
    private SimpleDateFormat mSimpleTime;
    private String nTime;
    Gson gson=new Gson();
    private AddressEntry mAddressInfo;
    private static final int FINISH_CODE=100;
    private String strcateid="7";
    private LinearLayout mLnSearch;
    private ImageView mCallphone;
    private int finalcityid;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FINISH_CODE:

                    dialog.dismiss();
//                    ToastUtils.show(getActivity(),mAddressInfo.getList().get(0).getName()+"");

                    if(mAddressInfo.getList()!=null) {
                        List<AddressEntry.ListBean> mList = mAddressInfo.getList();
                        AddressRightAdapter mRightAdapter = new AddressRightAdapter(getActivity(), mList);
                        mListHome.setAdapter(mRightAdapter);
//                    ToastUtils.show(getActivity(),finalcityid+"");
                    }

                    break;

                default:
                    break;

            };
        }
    };




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_address_main_content,null);
        dialog=new LoadingDialog(getActivity());

        Date d=new Date();
        mSimpleTime=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        nTime=mSimpleTime.format(d);


        int cityid= SharedPreferencesUtils.getCityid(getActivity());

        if (cityid!=0){
            finalcityid=cityid;
        }else{
            finalcityid=115;
        }

        initView();
        setData();
        initListener();

        return view;
    }


    private void initView(){
        mListMenu=(ListView)view.findViewById(R.id.lv_address_menu);//左边选择列表
        mListHome=(ListView)view.findViewById(R.id.lv_address_home);//右边展示列表
        mLnSearch=(LinearLayout)view.findViewById(R.id.tv_search_address);//地址搜索跳转
        mCallphone=(ImageView)view.findViewById(R.id.img_title_call);//电话跳转
    }

    private void setData(){
        mMenuAdapter=new AddressMenuAdapter(getActivity());//左边数据填充
        mListMenu.setAdapter(mMenuAdapter);
        getRightdata(strcateid);

    }

    private void initListener(){
        mListMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mMenuAdapter.setSelectItem(position);
                mMenuAdapter.notifyDataSetInvalidated();

                if (position==0){
                    strcateid="7";
                }else if (position==1){
                    strcateid="8";
                }else if (position==2){
                    strcateid="9";
                }

                getRightdata(strcateid);//分别获取右边每组的数据
            }
        });

        mLnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });




    }


    private void getRightdata(String cateid){

        JSONObject jbody=null;
        try {
            jbody = new JSONObject();
            jbody.put("cate_ids",cateid);//分类id 国内7 出境8 周边9
            jbody.put("city_id",finalcityid);//	城市id
            jbody.put("method","Get_ad");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyB= JsonUtils.JsonParseInfo(nTime,jbody);
        dohttpgetaddressinfo(UrlUtils.APP_URL,jbodyB);
    }

    protected  void dohttpgetaddressinfo(String url,JSONObject  paramhash){
        dialog.show();
        HttpUtils.dopost(url,getActivity(),paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {
                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());
                mAddressInfo=gson.fromJson(body, AddressEntry.class);
                handler.sendEmptyMessage(FINISH_CODE);
            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }


    public void loadcityid(int id){

        finalcityid=id;
    }


}
