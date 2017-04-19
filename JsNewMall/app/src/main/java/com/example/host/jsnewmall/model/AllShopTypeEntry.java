package com.example.host.jsnewmall.model;

/**
 * Created by host on 2017/4/14.
 */

public class AllShopTypeEntry {
    private String typename;
    private String  type;
    private boolean ischecked;

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean ischecked() {
        return ischecked;
    }

    public void setIschecked(boolean ischecked) {
        this.ischecked = ischecked;
    }
    public AllShopTypeEntry(String name,String type){
        this.typename=name;
        this.type=type;
    }
}
