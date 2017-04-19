package com.example.host.jsnewmall.model;

/**
 * Created by host on 2017/2/23.
 */

public class ResultbInfo {
    private String linkurl;
    private int travelid;
    private boolean isChecked;

    public int getTravelid() {
        return travelid;
    }

    public void setTravelid(int travelid) {
        this.travelid = travelid;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
    public boolean isChecked() {
        return isChecked;
    }
    public ResultbInfo(String linkurl,int tavelid){
        this.linkurl=linkurl;
        this.travelid=travelid;
    }

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
    }
}
