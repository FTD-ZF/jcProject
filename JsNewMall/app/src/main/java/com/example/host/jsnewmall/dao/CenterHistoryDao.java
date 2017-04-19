package com.example.host.jsnewmall.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.host.jsnewmall.model.CenterHistoryEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by host on 2017/4/9.
 */

//缓存浏览历史数据-数据库封装类
public class CenterHistoryDao {

    private static final String DB_NAME = "history.db";//数据库名称
    private static final String TABLE_NAME = "historyinfo";//数据表名称
    private static final int DB_VERSION = 1;//数据库版本

    //表的字段名
    private static String KEY_ID = "id";
    private static String KEY_LINEID = "lineid";
    private static String KEY_IMG = "imgurl";
    private static String KEY_PRICE = "price";
    private static String KEY_TITLE="titlename";

    private SQLiteDatabase mDatabase;
    private Context mContext;
    private TreeDBOpenHelper mDbOpenHelper;//数据库打开帮助类


    public CenterHistoryDao(Context context) {
        mContext = context;
    }

    //打开数据库
    public void openDataBase() {
        mDbOpenHelper = new TreeDBOpenHelper(mContext, DB_NAME, null, DB_VERSION);
        try {
            mDatabase = mDbOpenHelper.getWritableDatabase();//获取可写数据库
        } catch (SQLException e) {
            mDatabase = mDbOpenHelper.getReadableDatabase();//获取只读数据库
        }
    }

    //关闭数据库
    public void closeDataBase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    //插入一条数据
    public long insertData(CenterHistoryEntry tree) {
        ContentValues values = new ContentValues();
        values.put(KEY_LINEID, tree.getLineid());
        values.put(KEY_IMG, tree.getImgurl());
        values.put(KEY_PRICE, tree.getTotalprice());
        values.put(KEY_TITLE,tree.getTitlename());

        return mDatabase.insert(TABLE_NAME, null, values);
    }

    //删除一条数据
    public long deleteData(long id) {
        return mDatabase.delete(TABLE_NAME, KEY_ID + "=" + id, null);
    }

    //删除所有数据
    public long deleteAllData() {
        return mDatabase.delete(TABLE_NAME, null, null);
    }

    //更新一条数据
    public long updateData(long id, CenterHistoryEntry tree) {
        ContentValues values = new ContentValues();
        values.put(KEY_LINEID, tree.getLineid());
        values.put(KEY_IMG, tree.getImgurl());
        values.put(KEY_PRICE, tree.getTotalprice());
        values.put(KEY_TITLE,tree.getTitlename());
        return mDatabase.update(TABLE_NAME, values, KEY_ID + "=" + id, null);
    }

    //查询一条数据
    public List<CenterHistoryEntry> queryData(long id) {
        Cursor results = mDatabase.query(TABLE_NAME, new String[]{KEY_ID, KEY_LINEID, KEY_IMG, KEY_PRICE,KEY_TITLE},
                KEY_ID + "="  +id, null, null, null, null);
//        Cursor results=mDatabase.rawQuery("SELECT id FROM  historyinfo   where id='"+id+"'",null);
        return convertToTree(results);
    }

    //查询所有数据
    public List<CenterHistoryEntry> queryDataList() {
        Cursor results = mDatabase.query(true,TABLE_NAME, new String[]{KEY_ID, KEY_LINEID, KEY_IMG, KEY_PRICE,KEY_TITLE},
                null, null, null, null, null,null);
        return convertToTree(results);

    }



    //通过lineid过滤查询
    public List<CenterHistoryEntry> queryDataAll(String lineid) {
        Cursor results = mDatabase.query(true,TABLE_NAME, new String[]{KEY_ID, KEY_LINEID, KEY_IMG, KEY_PRICE,KEY_TITLE},
                KEY_LINEID+"=?", new String[]{lineid}, null, null, null,null);
        return convertToTree(results);

        }



    private List<CenterHistoryEntry> convertToTree(Cursor cursor) {
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) {
            return null;
        }
        List<CenterHistoryEntry> mTreeList = new ArrayList<>();
        for (int i = 0; i < resultCounts; i++) {
            CenterHistoryEntry tree = new CenterHistoryEntry();
            tree.setId(cursor.getInt(0));
            tree.setLineid(cursor.getString(cursor.getColumnIndex(KEY_LINEID)));
            tree.setImgurl(cursor.getString(cursor.getColumnIndex(KEY_IMG)));
            tree.setTotalprice(cursor.getString(cursor.getColumnIndex(KEY_PRICE)));
            tree.setTitlename(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
            mTreeList.add(tree);
            cursor.moveToNext();
        }
        return mTreeList;
    }

    /**
     * 数据表打开帮助类
     */
    private static class TreeDBOpenHelper extends SQLiteOpenHelper {

        public TreeDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            final String sqlStr = "CREATE TABLE if not exists " + TABLE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_LINEID + " text not null, " + KEY_IMG + " String, " + KEY_PRICE +" float, "+KEY_TITLE+ " String);";
            db.execSQL(sqlStr);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            final String sqlStr = "DROP TABLE IF EXISTS " + TABLE_NAME;
            db.execSQL(sqlStr);
            onCreate(db);
        }
    }


}
