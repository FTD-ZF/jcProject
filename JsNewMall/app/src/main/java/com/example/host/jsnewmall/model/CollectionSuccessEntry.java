package com.example.host.jsnewmall.model;

/**
 * Created by host on 2017/3/31.
 */

public class CollectionSuccessEntry {


    /**
     * res : 0
     * msg : \u6570\u636e\u5df2\u6536\u85cf
     * data : 0
     * fav_id : 301
     */

    private int res;
    private String msg;
    private int data;
    private String fav_id;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getFav_id() {
        return fav_id;
    }

    public void setFav_id(String fav_id) {
        this.fav_id = fav_id;
    }
}
