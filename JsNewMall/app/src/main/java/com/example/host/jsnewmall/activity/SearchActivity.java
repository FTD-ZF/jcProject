package com.example.host.jsnewmall.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.example.host.jsnewmall.adapter.SearchAdapter;
import com.example.host.jsnewmall.adapter.SearchHistoryAdapter;
import com.example.host.jsnewmall.model.JsonmModel;
import com.example.host.jsnewmall.model.SearchHotAddress;
import com.example.host.jsnewmall.utils.Base64Utils;
import com.example.host.jsnewmall.utils.HttpUtils;
import com.example.host.jsnewmall.utils.JsonUtils;
import com.example.host.jsnewmall.utils.RecordSQLiteOpenHelper;
import com.example.host.jsnewmall.utils.UrlUtils;
import com.example.host.jsnewmall.view.HomeForthGridView;
import com.example.host.jsnewmall.view.LoadingDialog;
import com.google.gson.Gson;
import com.uu1.nmw.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by host on 2017/2/21.
 */

//地址搜索界面
public class SearchActivity extends BaseActivity {
    private LinearLayout mLnSearch;
    private EditText mEtSsearch;
    private TextView mTvBack;
    private GridView mGvSearcha;
    private GridView mGvSearchb;

    private LoadingDialog dialog;
    private SimpleDateFormat mSimpleTime;
    private String nTime;
    Gson gson=new Gson();
    private List<SearchHotAddress> mbodyinfo;
    private static final int FINISH_CODE=100;
    private LinearLayout mAddressa;
    private LinearLayout mAddressb;
    private LinearLayout mClearHistory;
    private RecordSQLiteOpenHelper helper = new RecordSQLiteOpenHelper(this);;
    private SQLiteDatabase db;
    private BaseAdapter adapter;



    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);

            switch (msg.what) {

                case FINISH_CODE:

                    initView();
                    // 第一次进入查询所有的历史记录
                    queryData("");

                    initListener();
//                    List<SearchHotAddress> bodylist=new ArrayList<>();
                    SearchAdapter mSearchAdapter=new SearchAdapter(getApplicationContext(),mbodyinfo);
                    mGvSearcha.setAdapter(mSearchAdapter);
                    HomeForthGridView.setListViewHeightBasedOnChildren(mGvSearcha);
                    mGvSearcha.deferNotifyDataSetChanged();


                    break;

                default:
                    break;

            };
        }
    };



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);

        dialog=new LoadingDialog(SearchActivity.this);

        Date d=new Date();
        mSimpleTime=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        nTime=mSimpleTime.format(d);

        initData();


    }

    private void initData(){
        JSONObject jbodys=null;
        try {
            jbodys = new JSONObject();
            jbodys.put("size",10);
            jbodys.put("method","GetHotSearch");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbodyBs= JsonUtils.JsonParseInfo(nTime,jbodys);
        dohttpSearch(UrlUtils.SEARCH,jbodyBs);
    }


    private void initView(){
         mLnSearch=(LinearLayout) findViewById(R.id.ln_search_a);
         mEtSsearch=(EditText) findViewById(R.id.et_search);//搜索框
         mTvBack=(TextView) findViewById(R.id.tv_search_dismiss);//取消按钮

         mGvSearcha=(GridView) findViewById(R.id.gv_search_content_a);//热门搜索

         mGvSearchb=(GridView) findViewById(R.id.gv_search_content_b);//历史记录
//         SearchHistoryAdapter mHistoryAdapter=new SearchHistoryAdapter(getApplicationContext());
//         mGvSearchb.setAdapter(mHistoryAdapter);
         HomeForthGridView.setListViewHeightBasedOnChildren(mGvSearchb);
         mGvSearchb.deferNotifyDataSetChanged();

         mClearHistory=(LinearLayout)findViewById(R.id.ln_search_clear);//q清除历史记录

         mAddressa=(LinearLayout)findViewById(R.id.ln_search_address_a);//目的地大全
         mAddressb=(LinearLayout)findViewById(R.id.ln_search_address_b);//定制线路




    }

    private void initListener(){
        OnClickListenerImpl listener=new OnClickListenerImpl();
        mTvBack.setOnClickListener(listener);
        mAddressa.setOnClickListener(listener);
        mAddressb.setOnClickListener(listener);
        mClearHistory.setOnClickListener(listener);

            mEtSsearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (mEtSsearch.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "请输入搜索内容", Toast.LENGTH_SHORT).show();
                }else{

                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                        // 先隐藏键盘
                        ((InputMethodManager) mEtSsearch.getContext().getSystemService(INPUT_METHOD_SERVICE))
                                .hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


                        // 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存
                        boolean hasData = hasData(mEtSsearch.getText().toString().trim());
                        if (!hasData) {
                            insertData(mEtSsearch.getText().toString().trim());
                            queryData("");
                        }

                        //跳转activity

                        Intent intent = new Intent();
                        intent.setClass(getApplicationContext(), SearchResultActivity.class);
                        intent.putExtra("searchcontent",mEtSsearch.getText().toString().trim());
                        startActivity(intent);
                        finish();


                        // 将查询的数据插入数据库

//                    mDbHelper.insert_search_history(searchText.getText().toString(), getStringDate());

                        return true;
                    }
                }




                return false;
            }
        });




        //热门目的地点击
        mGvSearcha.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intenth = new Intent();
                intenth.setClass(getApplicationContext(), SearchResultActivity.class);
                intenth.putExtra("searchcontent",mbodyinfo.get(i).getCode());
                startActivity(intenth);
                finish();
            }
        });



        mGvSearchb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView textView = (TextView) view.findViewById(R.id.tv_item_search_history);
                String name = textView.getText().toString();

                Intent intentb= new Intent();
                intentb.setClass(getApplicationContext(), SearchResultActivity.class);
                intentb.putExtra("searchcontent",name);
                startActivity(intentb);
                finish();
            }
        });

    }



    //获取热门目的地
    protected  void dohttpSearch(String url,JSONObject  paramhash){
        HttpUtils.dopost(url,SearchActivity.this,paramhash, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {

                JsonmModel homeinfoa=gson.fromJson(result,JsonmModel.class);
                String body= Base64Utils.getFromBase64(homeinfoa.getBody());


//                mbodyinfo=gson.fromJson(body, SearchHotAddress.class);

                mbodyinfo=new ArrayList<SearchHotAddress>();

                try{
                    JSONArray arraylist=new JSONArray(body);
                    for (int i=0;i<arraylist.length();i++){
                        SearchHotAddress info=new SearchHotAddress();
                        JSONObject obj=arraylist.getJSONObject(i);
                        info.setCode(obj.optString("code"));
                        info.setSearchNum(obj.optString("searchNum"));
                        mbodyinfo.add(info);

                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }

                handler.sendEmptyMessage(FINISH_CODE);

            }

            @Override
            public void onRequestErr(String err) {

            }
        });
    }




    private class OnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.tv_search_dismiss:
                    finish();
                    break;

                //目的地大全
                case R.id.ln_search_address_a:
                    Intent intenta=new Intent(SearchActivity.this,MainActivity.class);
                    intenta.putExtra("id",5);
                    startActivity(intenta);
                    finish();

                    break;
                //定制线路
                case R.id.ln_search_address_b:
                    Intent intent=new Intent(SearchActivity.this,DingzActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                //q清除历史
                case R.id.ln_search_clear:
                    deleteData();
                    queryData("");

                    break;
                default:
                    break;

            }


        }

    }



    /**
     * 插入数据
     */
    private void insertData(String tempName) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
        db.close();
    }

    /**
     * 模糊查询数据
     */
    private void queryData(String tempName) {


        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '%" + tempName + "%' order by id desc limit 10 ", null);
        // 创建adapter适配器对象
        adapter = new SimpleCursorAdapter(this, R.layout.item_activity_search_second, cursor, new String[] { "name" },
                new int[] { R.id.tv_item_search_history }, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        // 设置适配器
        mGvSearchb.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        HomeForthGridView.setListViewHeightBasedOnChildren(mGvSearchb);
        mGvSearchb.deferNotifyDataSetChanged();
    }
    /**
     * 检查数据库中是否已经有该条记录
     */
    private boolean hasData(String tempName) {
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name =?", new String[]{tempName});
        //判断是否有下一个
        return cursor.moveToNext();
    }

    /**
     * 清空数据
     */
    private void deleteData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
    }


}
