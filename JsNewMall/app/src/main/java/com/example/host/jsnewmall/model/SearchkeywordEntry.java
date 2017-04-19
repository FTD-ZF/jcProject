package com.example.host.jsnewmall.model;

import java.util.List;

/**
 * Created by host on 2017/4/2.
 */

public class SearchkeywordEntry {

    /**
     * res : 1
     * msg : \u67e5\u8be2\u6210\u529f
     * citydata : [{"cityid":"0","name":"\\u5168\\u90e8"},{"cityid":"2","name":"\\u5317\\u4eac\\u5e02"},{"cityid":"3","name":"\\u5929\\u6d25\\u5e02"},{"cityid":"4","name":"\\u6cb3\\u5317\\u7701"},{"cityid":"10","name":"\\u4e0a\\u6d77\\u5e02"},{"cityid":"11","name":"\\u6c5f\\u82cf\\u7701"},{"cityid":"112","name":"\\u5357\\u4eac\\u5e02"},{"cityid":"113","name":"\\u65e0\\u9521\\u5e02"},{"cityid":"115","name":"\\u5e38\\u5dde\\u5e02"},{"cityid":"116","name":"\\u82cf\\u5dde\\u5e02"}]
     * daycountdata : [{"daycount":"\\u5168\\u90e8"},{"daycount":"1"},{"daycount":"2"},{"daycount":"3"},{"daycount":"4"},{"daycount":"5"},{"daycount":"6"},{"daycount":"7"},{"daycount":"8"},{"daycount":"9"},{"daycount":"10"},{"daycount":"11"},{"daycount":"12"},{"daycount":"14"},{"daycount":"18"}]
     * accommodationdata : [{"attrdetailname":"\\u5168\\u90e8","attrdetailvalue":"0"},{"attrdetailname":"2-3\\u4eba\\u6807\\u51c6\\u95f4","attrdetailvalue":"1455"},{"attrdetailname":"\\u6302\\u724c\\u4e09\\u661f\\u53cc\\u6807\\u95f4","attrdetailvalue":"1456"},{"attrdetailname":"\\u6302\\u724c\\u56db\\u661f\\u53cc\\u6807\\u95f4","attrdetailvalue":"1457"},{"attrdetailname":"\\u6302\\u724c\\u4e94\\u661f\\u53cc\\u6807\\u95f4","attrdetailvalue":"1458"},{"attrdetailname":"\\u5883\\u5916\\u4e09\\u661f\\u7ea7\\u9152\\u5e97","attrdetailvalue":"1459"},{"attrdetailname":"\\u5883\\u5916\\u56db\\u661f\\u7ea7\\u9152\\u5e97","attrdetailvalue":"1460"},{"attrdetailname":"\\u5883\\u5916\\u4e94\\u661f\\u7ea7\\u9152\\u5e97","attrdetailvalue":"1461"},{"attrdetailname":"\\u81ea\\u5b9a\\u4e49","attrdetailvalue":"1462"},{"attrdetailname":"\\u53cc\\u4eba\\u6807\\u51c6\\u95f4","attrdetailvalue":"1463"}]
     * gotrafficdata : [{"attrdetailname":"\\u5168\\u90e8","attrdetailvalue":"0"},{"attrdetailname":"\\u98de\\u673a","attrdetailvalue":"1470"},{"attrdetailname":"\\u706b\\u8f66","attrdetailvalue":"1471"},{"attrdetailname":"\\u6c7d\\u8f66","attrdetailvalue":"1472"},{"attrdetailname":"\\u8f6e\\u8239","attrdetailvalue":"1473"},{"attrdetailname":"\\u5176\\u4ed6","attrdetailvalue":"1474"},{"attrdetailname":"\\u81ea\\u7406","attrdetailvalue":"1475"}]
     * backtrafficdata : [{"attrdetailname":"\\u5168\\u90e8","attrdetailvalue":"0"},{"attrdetailname":"\\u98de\\u673a","attrdetailvalue":"1470"},{"attrdetailname":"\\u706b\\u8f66","attrdetailvalue":"1471"},{"attrdetailname":"\\u6c7d\\u8f66","attrdetailvalue":"1472"},{"attrdetailname":"\\u8f6e\\u8239","attrdetailvalue":"1473"},{"attrdetailname":"\\u5176\\u4ed6","attrdetailvalue":"1474"},{"attrdetailname":"\\u81ea\\u7406","attrdetailvalue":"1475"}]
     * productdata : [{"attrdetailname":"\\u5168\\u90e8","attrdetailvalue":"0"},{"attrdetailname":"\\u7f8e\\u98df","attrdetailvalue":"1181"},{"attrdetailname":"\\u4eb2\\u5b50","attrdetailvalue":"1182"},{"attrdetailname":"\\u8d2d\\u7269","attrdetailvalue":"1183"},{"attrdetailname":"\\u871c\\u6708","attrdetailvalue":"1184"},{"attrdetailname":"\\u6d77\\u6ee8\\u6d77\\u5c9b","attrdetailvalue":"1185"},{"attrdetailname":"\\u6c11\\u4fd7\\u98ce\\u60c5","attrdetailvalue":"1186"},{"attrdetailname":"\\u5915\\u9633\\u7ea2","attrdetailvalue":"1187"},{"attrdetailname":"\\u5b97\\u6559\\u6587\\u5316","attrdetailvalue":"1188"},{"attrdetailname":"\\u53e4\\u9547\\u6751\\u843d","attrdetailvalue":"1189"},{"attrdetailname":"\\u7530\\u56ed\\u5ea6\\u5047","attrdetailvalue":"1190"},{"attrdetailname":"\\u6bd5\\u4e1a\\u65c5\\u884c","attrdetailvalue":"1191"},{"attrdetailname":"\\u6625\\u8282\\u626b\\u8d27","attrdetailvalue":"1192"},{"attrdetailname":"\\u540d\\u5c71\\u80dc\\u6c34","attrdetailvalue":"1193"},{"attrdetailname":"\\u6ed1\\u96ea\\u6e29\\u6cc9","attrdetailvalue":"1194"},{"attrdetailname":"\\u4eba\\u6587\\u5386\\u53f2","attrdetailvalue":"1195"},{"attrdetailname":"\\u4e3b\\u9898\\u6e38\\u4e50","attrdetailvalue":"1196"},{"attrdetailname":"\\u8349\\u539f\\u5927\\u6f20","attrdetailvalue":"1197"}]
     */

    private int res;
    private String msg;
    private List<CitydataBean> citydata;
    private List<DaycountdataBean> daycountdata;
    private List<AccommodationdataBean> accommodationdata;
    private List<GotrafficdataBean> gotrafficdata;
    private List<BacktrafficdataBean> backtrafficdata;
    private List<ProductdataBean> productdata;

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

    public List<CitydataBean> getCitydata() {
        return citydata;
    }

    public void setCitydata(List<CitydataBean> citydata) {
        this.citydata = citydata;
    }

    public List<DaycountdataBean> getDaycountdata() {
        return daycountdata;
    }

    public void setDaycountdata(List<DaycountdataBean> daycountdata) {
        this.daycountdata = daycountdata;
    }

    public List<AccommodationdataBean> getAccommodationdata() {
        return accommodationdata;
    }

    public void setAccommodationdata(List<AccommodationdataBean> accommodationdata) {
        this.accommodationdata = accommodationdata;
    }

    public List<GotrafficdataBean> getGotrafficdata() {
        return gotrafficdata;
    }

    public void setGotrafficdata(List<GotrafficdataBean> gotrafficdata) {
        this.gotrafficdata = gotrafficdata;
    }

    public List<BacktrafficdataBean> getBacktrafficdata() {
        return backtrafficdata;
    }

    public void setBacktrafficdata(List<BacktrafficdataBean> backtrafficdata) {
        this.backtrafficdata = backtrafficdata;
    }

    public List<ProductdataBean> getProductdata() {
        return productdata;
    }

    public void setProductdata(List<ProductdataBean> productdata) {
        this.productdata = productdata;
    }

    public static class CitydataBean {
        /**
         * cityid : 0
         * name : \u5168\u90e8
         */

        private String cityid;
        private String name;

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class DaycountdataBean {
        /**
         * daycount : \u5168\u90e8
         */

        private String daycount;
        private boolean isChecked;
        public void setChecked(boolean checked) {
            isChecked = checked;
        }
        public boolean isChecked() {
            return isChecked;
        }

        public String getDaycount() {
            return daycount;
        }

        public void setDaycount(String daycount) {
            this.daycount = daycount;
        }
    }

    public static class AccommodationdataBean {
        /**
         * attrdetailname : \u5168\u90e8
         * attrdetailvalue : 0
         */

        private String attrdetailname;
        private String attrdetailvalue;

        public String getAttrdetailname() {
            return attrdetailname;
        }

        public void setAttrdetailname(String attrdetailname) {
            this.attrdetailname = attrdetailname;
        }

        public String getAttrdetailvalue() {
            return attrdetailvalue;
        }

        public void setAttrdetailvalue(String attrdetailvalue) {
            this.attrdetailvalue = attrdetailvalue;
        }
    }

    public static class GotrafficdataBean {
        /**
         * attrdetailname : \u5168\u90e8
         * attrdetailvalue : 0
         */

        private String attrdetailname;
        private String attrdetailvalue;

        public String getAttrdetailname() {
            return attrdetailname;
        }

        public void setAttrdetailname(String attrdetailname) {
            this.attrdetailname = attrdetailname;
        }

        public String getAttrdetailvalue() {
            return attrdetailvalue;
        }

        public void setAttrdetailvalue(String attrdetailvalue) {
            this.attrdetailvalue = attrdetailvalue;
        }
    }

    public static class BacktrafficdataBean {
        /**
         * attrdetailname : \u5168\u90e8
         * attrdetailvalue : 0
         */

        private String attrdetailname;
        private String attrdetailvalue;

        public String getAttrdetailname() {
            return attrdetailname;
        }

        public void setAttrdetailname(String attrdetailname) {
            this.attrdetailname = attrdetailname;
        }

        public String getAttrdetailvalue() {
            return attrdetailvalue;
        }

        public void setAttrdetailvalue(String attrdetailvalue) {
            this.attrdetailvalue = attrdetailvalue;
        }
    }

    public static class ProductdataBean {
        /**
         * attrdetailname : \u5168\u90e8
         * attrdetailvalue : 0
         */

        private String attrdetailname;
        private String attrdetailvalue;
        private boolean isChecked;

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        public String getAttrdetailname() {
            return attrdetailname;
        }

        public void setAttrdetailname(String attrdetailname) {
            this.attrdetailname = attrdetailname;
        }

        public String getAttrdetailvalue() {
            return attrdetailvalue;
        }

        public void setAttrdetailvalue(String attrdetailvalue) {
            this.attrdetailvalue = attrdetailvalue;
        }
    }
}
