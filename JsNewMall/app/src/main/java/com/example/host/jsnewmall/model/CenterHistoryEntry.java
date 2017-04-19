package com.example.host.jsnewmall.model;

/**
 * Created by host on 2017/4/9.
 */

public class CenterHistoryEntry {
    private String lineid;
    private String imgurl;
    private String titlename;
    private String totalprice;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLineid() {
        return lineid;
    }

    public void setLineid(String lineid) {
        this.lineid = lineid;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getTitlename() {
        return titlename;
    }

    public void setTitlename(String titlename) {
        this.titlename = titlename;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }


    public CenterHistoryEntry(){

    }
    public CenterHistoryEntry(String lineid,String imgurl,String titlename,String totalprice){
        this.lineid=lineid;
        this.imgurl=imgurl;
        this.titlename=titlename;
        this.totalprice=totalprice;
    }

    @Override
    public String toString() {
        return "History{" +
                "lineid=" + lineid +
                ", titlename='" + titlename + '\'' +
                ", imgurl=" + imgurl +
                ", price=" + totalprice +
                '}';
    }
}
