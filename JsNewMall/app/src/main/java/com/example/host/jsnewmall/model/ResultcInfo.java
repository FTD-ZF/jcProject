package com.example.host.jsnewmall.model;

/**
 * Created by host on 2017/2/23.
 */

public class ResultcInfo {
    private String linkurl;
    private int cityid;
    private boolean isChecked;

    public int getCityid() {
        return cityid;
    }

    public void setCityid(int cityid) {
        this.cityid = cityid;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
    public boolean isChecked() {
        return isChecked;
    }
    public ResultcInfo(String linkurl,int cityid){
        this.linkurl=linkurl;
        this.cityid=cityid;
    }

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
    }
}
