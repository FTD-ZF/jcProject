package com.example.host.jsnewmall.model;

import java.util.List;

/**
 * Created by host on 2017/3/31.
 */

public class CollectionSearchEntry {

    /**
     * res : 1
     * msg : \u67e5\u8be2\u6210\u529f
     * count : 4
     * allcount : 0
     * data : [{"lines_id":"13510","label":[],"lines_image":"http:\\/\\/img1.uu1.com\\/images\\/0\\/14\\/7472\\/8951732c65ddfdfd639443f6d621cdc5.jpg","lines_title":"\\u81ea\\u7ec4\\u56e2\\uff0c\\u8d60\\u9001\\u63a5\\u9001\\uff0c\\u4e0d\\u542b\\u7b7e\\u8bc1","lines_subtitle":"\\u3010\\u6e05\\u65b0\\u6cf0\\u56fd\\u3011\\u6cf0\\u56fd\\u66fc\\u8c37\\u82ad\\u63d0\\u96c5\\u4e94\\u665a\\u516d\\u65e5\\u6e38 \\uff08\\u5e38\\u5dde\\u5f80\\u8fd4\\uff0c\\u81ea\\u7ec4\\u56e2\\uff09","lines_price":"3099.00","lines_date":"2017-03-07","can_sale":0,"lines_type":"lines","id":"301","data_id":"13510","data_type":"1","user_id":"24025","add_date":"2017-03-31 10:27:06"},{"lines_id":"3459","label":[],"lines_image":"http:\\/\\/img1.uu1.com\\/images\\/0\\/14\\/107\\/29ec8506772f0d71703fdfb2e5d01d9b.jpg","lines_title":"\\u6625\\u79cb\\u81ea\\u7ec4\\u56e2\\uff0c\\u65e0\\u8d2d\\u7269\\uff0c\\u65e0\\u81ea\\u8d39\\uff0c\\u5168\\u7a0b\\u8d60\\u9001\\u4ef7\\u503c588\\u5143\\u5927\\u793c\\u5305\\uff0c\\u8d60\\u9001\\u4e00\\u9910\\u5168\\u805a\\u5fb7\\u70e4\\u9e2d\\uff0c\\u5347\\u7ea7\\u4e00\\u665a\\u6302\\u724c\\u4e94\\u661f\\u9152\\u5e97","lines_subtitle":"\\u4e50\\u4eab\\u666f\\u5c1a--\\u5e1d\\u90fd\\u5168\\u666f\\uff0c\\u6625\\u79cb\\u7206\\u6b3e--\\u5317\\u4eac\\u81fb\\u54c1\\u4e09\\u661f\\u7eaf\\u73a9\\u53cc\\u9ad85\\u65e5\\u6e38\\uff08\\u6ee130\\u4eba\\u6211\\u793e\\u6d3e\\u5168\\u966a\\u5bfc\\u6e38\\uff09\\u6691\\u671f\\u8d60\\u9001\\u6e05\\u534e\\u5317\\u5927\\u6821\\u56ed\\u6e38","lines_price":"2590.00","lines_date":"2016-07-02","can_sale":0,"lines_type":"lines","id":"293","data_id":"3459","data_type":"1","user_id":"24025","add_date":"2017-03-28 20:21:09"},{"lines_id":"3343","label":[],"lines_image":"http:\\/\\/img1.uu1.com\\/images\\/0\\/14\\/3140\\/9080ac721d3277aa1b8625ab7b61e9bd.jpg","lines_title":"\\u65e0\\u9521\\u4e1c\\u822a\\u76f4\\u98de\\uff0c \\u884c\\u674e46\\u516c\\u65a4\\uff0c\\u4e24\\u4eba\\u8d60\\u9001\\u4e00\\u53f0WIFI\\uff0c2\\u5929\\u81ea\\u7531\\u6d3b\\u52a8\\uff0c\\u4e94\\u82b1\\u7279\\u4e8c\\u4f4f\\u5bbf","lines_subtitle":"\\u3010\\u4e50\\u6e38\\u9996\\u5c14\\u3011\\u97e9\\u56fd\\u9996\\u5c145\\u59294\\u665a\\u534a\\u81ea\\u52a9\\uff08\\u65e0\\u9521\\u5f80\\u8fd4 \\u6563\\u62fc\\uff09","lines_price":"3699.00","lines_date":"2016-07-03","can_sale":0,"lines_type":"lines","id":"292","data_id":"3343","data_type":"1","user_id":"24025","add_date":"2017-03-21 13:59:34"},{"lines_id":"1947","label":[],"lines_image":"http:\\/\\/img1.uu1.com\\/images\\/0\\/14\\/2933\\/41b1ba91a36ad7827a6ab05e33d999fa.jpg","lines_title":"\\u5357\\u4eac\\u5f80\\u8fd4\\u6e2f\\u9f99\\u822a\\u7a7a\\u9999\\u6e2f\\u8f6c\\u673a5\\u665a7\\u65e5\\u6e38","lines_subtitle":"1314\\u60c5\\u5b9a\\u666e\\u5409\\u53cc\\u5885\\u53cc\\u98de\\u94bb\\u77f3\\u7248","lines_price":"4580.00","lines_date":"2016-05-06","can_sale":0,"lines_type":"lines","id":"291","data_id":"1947","data_type":"1","user_id":"24025","add_date":"2017-03-13 18:26:02"}]
     */

    private int res;
    private String msg;
    private String count;
    private int allcount;
    private List<DataBean> data;

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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getAllcount() {
        return allcount;
    }

    public void setAllcount(int allcount) {
        this.allcount = allcount;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * lines_id : 13510
         * label : []
         * lines_image : http:\/\/img1.uu1.com\/images\/0\/14\/7472\/8951732c65ddfdfd639443f6d621cdc5.jpg
         * lines_title : \u81ea\u7ec4\u56e2\uff0c\u8d60\u9001\u63a5\u9001\uff0c\u4e0d\u542b\u7b7e\u8bc1
         * lines_subtitle : \u3010\u6e05\u65b0\u6cf0\u56fd\u3011\u6cf0\u56fd\u66fc\u8c37\u82ad\u63d0\u96c5\u4e94\u665a\u516d\u65e5\u6e38 \uff08\u5e38\u5dde\u5f80\u8fd4\uff0c\u81ea\u7ec4\u56e2\uff09
         * lines_price : 3099.00
         * lines_date : 2017-03-07
         * can_sale : 0
         * lines_type : lines
         * id : 301
         * data_id : 13510
         * data_type : 1
         * user_id : 24025
         * add_date : 2017-03-31 10:27:06
         */

        private String lines_id;
        private String lines_image;
        private String lines_title;
        private String lines_subtitle;
        private String lines_price;
        private String lines_date;
        private int can_sale;
        private String lines_type;
        private String id;
        private String data_id;
        private String data_type;
        private String user_id;
        private String add_date;
        private List<?> label;

        public String getLines_id() {
            return lines_id;
        }

        public void setLines_id(String lines_id) {
            this.lines_id = lines_id;
        }

        public String getLines_image() {
            return lines_image;
        }

        public void setLines_image(String lines_image) {
            this.lines_image = lines_image;
        }

        public String getLines_title() {
            return lines_title;
        }

        public void setLines_title(String lines_title) {
            this.lines_title = lines_title;
        }

        public String getLines_subtitle() {
            return lines_subtitle;
        }

        public void setLines_subtitle(String lines_subtitle) {
            this.lines_subtitle = lines_subtitle;
        }

        public String getLines_price() {
            return lines_price;
        }

        public void setLines_price(String lines_price) {
            this.lines_price = lines_price;
        }

        public String getLines_date() {
            return lines_date;
        }

        public void setLines_date(String lines_date) {
            this.lines_date = lines_date;
        }

        public int getCan_sale() {
            return can_sale;
        }

        public void setCan_sale(int can_sale) {
            this.can_sale = can_sale;
        }

        public String getLines_type() {
            return lines_type;
        }

        public void setLines_type(String lines_type) {
            this.lines_type = lines_type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getData_id() {
            return data_id;
        }

        public void setData_id(String data_id) {
            this.data_id = data_id;
        }

        public String getData_type() {
            return data_type;
        }

        public void setData_type(String data_type) {
            this.data_type = data_type;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getAdd_date() {
            return add_date;
        }

        public void setAdd_date(String add_date) {
            this.add_date = add_date;
        }

        public List<?> getLabel() {
            return label;
        }

        public void setLabel(List<?> label) {
            this.label = label;
        }
    }
}
