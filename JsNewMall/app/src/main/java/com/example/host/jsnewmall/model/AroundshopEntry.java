package com.example.host.jsnewmall.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by host on 2017/4/13.
 */

public class AroundshopEntry {

    /**
     * res : 1
     * msg : \u67e5\u8be2\u6210\u529f
     * count : 9
     * data : [{"store_id":"206","store_name":"\\u6e05\\u6f6d\\u65b0\\u5efa\\u8425\\u4e1a\\u90e8\\u6e05\\u6f6d\\u65b0\\u5efa\\u8425\\u4e1a\\u90e8\\u6e05\\u6f6d\\u65b0\\u5efa\\u8425\\u4e1a\\u90e8\\u6e05\\u6f6d\\u65b0\\u5efa\\u8425\\u4e1a\\u90e8\\u6e05\\u6f6d\\u65b0\\u5efa\\u8425\\u4e1a\\u90e8","store_company_name":"\\u6e05\\u6f6d\\u65b0\\u5efa\\u8425\\u4e1a\\u90e8","userid":"0","store_contact_name":"\\u8521\\u79c0\\u534e","store_contact_phone":"18796949518","store_owner_card":"320411197603140828","store_contact_qq":null,"store_contact_weixin":null,"area_id":"115","area_info":null,"store_address":"\\u6e05\\u6f6d\\u65b0\\u5efa\\u6751\\u59d4\\u697c\\u4e0b","store_zip":"213000","store_tel":"18796949518","store_state":"1","store_close_info":"","store_keywords":"\\u6e05\\u6f6d\\u65b0\\u5efa\\u8425\\u4e1a\\u90e8","store_description":"\\u6e05\\u6f6d\\u65b0\\u5efa\\u8425\\u4e1a\\u90e8","description":"     \\u6e05\\u6f6d\\u95e8\\u5e97\\u7b80\\u4ecb","timestamp":"2017-03-31 09:49:49","xclearingprice":"2","city_id":"115","longitude":"119.935493","latitude":"31.766714","kilometer_x":"11329.88871047589","kilometer_y":"3529.6348888888892","store_type":"1","service_time":"9:00-19:00","bus_station":"\\u6e05\\u6f6d\\u7ad9","business_type":"1","shop_owner":"xue1","shop_owner_phone":"15189711636","province":"\\u6c5f\\u82cf\\u7701","province_id":"11","city":"\\u5e38\\u5dde\\u5e02","district":"\\u949f\\u697c\\u533a","district_id":"1219","distance":4411,"main_photo":[{"img_id":"67408","file_name":"846a4043cb417190e45db79e8e59ea49.jpg","img_onlineurl":"http:\\/\\/img.4008289828.com\\/images\\/0\\/34\\/206\\/846a4043cb417190e45db79e8e59ea49.jpg"}],"store_photo":[{"img_id":"67409","file_name":"e1fb4c42d48520c0ba13c7770aa4cd4e.jpg","img_onlineurl":"http:\\/\\/img.4008289828.com\\/images\\/0\\/21\\/206\\/e1fb4c42d48520c0ba13c7770aa4cd4e.jpg"}]},{"store_id":"193","store_name":"\\u6c38\\u7ea2\\u8425\\u4e1a\\u90e8\\uff08\\u517c\\uff09","store_company_name":"\\u6c38\\u7ea2\\u8425\\u4e1a\\u90e8\\uff08\\u517c\\uff09","userid":"18406","store_contact_name":"\\u9648\\u7f8e\\u6625","store_contact_phone":"18961163998","store_owner_card":"530326198402161721","store_contact_qq":"914807994","store_contact_weixin":"http:\\/\\/img1.uu1.com\\/images\\/0\\/0\\/0\\/0f864c09a6dd03424e52b7e7a5cb8bf3.jpg","area_id":"1","area_info":null,"store_address":"\\u949f\\u697c\\u533a\\u6e05\\u6f6d\\u8def229\\u53f7","store_zip":"213000","store_tel":"18961163998","store_state":"1","store_close_info":"","store_keywords":"\\u6c38\\u7ea2\\u8425\\u4e1a\\u90e8\\uff08\\u517c\\uff09","store_description":"\\u6c38\\u7ea2\\u8425\\u4e1a\\u90e8\\uff08\\u517c\\uff09","description":"","timestamp":"2017-03-06 13:40:34","xclearingprice":"2","city_id":null,"longitude":"119.9315340","latitude":"31.7683780","kilometer_x":"11329.31096699","kilometer_y":"3529.8197777778","store_type":"1","service_time":null,"bus_station":null,"business_type":null,"shop_owner":null,"shop_owner_phone":null,"province":null,"province_id":null,"city":null,"district":null,"district_id":null,"distance":4766,"main_photo":[],"store_photo":[{"img_id":"62425","file_name":"0526dd51f552f2426259092212af6a82.jpg","img_onlineurl":"http:\\/\\/img1.uu1.com\\/images\\/0\\/21\\/193\\/0526dd51f552f2426259092212af6a82.jpg"}]},{"store_id":"179","store_name":"\\u666f\\u5c1a\\u7f51\\u7edc\\u6d4b\\u8bd5\\u90e8","store_company_name":"\\u666f\\u5c1a\\u7f51\\u7edc\\u6d4b\\u8bd5\\u90e8","userid":"23460","store_contact_name":"\\u6731\\u78ca","store_contact_phone":"18661109996","store_owner_card":"32155449456456","store_contact_qq":null,"store_contact_weixin":null,"area_id":"115","area_info":null,"store_address":"\\u666f\\u5c1a\\u7f51\\u7edc\\u6d4b\\u8bd5\\u90e8","store_zip":"","store_tel":"18661109996","store_state":"1","store_close_info":"","store_keywords":"\\u666f\\u5c1a\\u7f51\\u7edc\\u6d4b\\u8bd5\\u90e8","store_description":"\\u666f\\u5c1a\\u7f51\\u7edc\\u6d4b\\u8bd5\\u90e8","description":"      \\u666f\\u5c1a\\u7f51\\u7edc\\u6d4b\\u8bd5\\u90e8      ","timestamp":"2017-03-14 11:17:24","xclearingprice":"2","city_id":null,"longitude":"119.923515","latitude":"31.78408","kilometer_x":"11326.63046092091","kilometer_y":"3531.5644444444447","store_type":"1","service_time":null,"bus_station":null,"business_type":null,"shop_owner":null,"shop_owner_phone":null,"province":null,"province_id":null,"city":null,"district":null,"district_id":null,"distance":5689,"main_photo":[],"store_photo":[{"img_id":"65484","file_name":"44567e4d05fe3c4897d31f240928a535.jpg","img_onlineurl":"http:\\/\\/img1.uu1.com\\/images\\/0\\/21\\/179\\/44567e4d05fe3c4897d31f240928a535.jpg"}]},{"store_id":"204","store_name":"\\u91d1\\u575b\\u4e30\\u7530\\u8def\\u95e8\\u5e97","store_company_name":"\\u91d1\\u575b\\u4e30\\u7530\\u8def\\u95e8\\u5e97","userid":"23800","store_contact_name":"\\u674e\\u5a1c","store_contact_phone":"18118005569","store_owner_card":"32021119840813192X","store_contact_qq":null,"store_contact_weixin":null,"area_id":"115","area_info":null,"store_address":"\\u91d1\\u575b\\u4e30\\u7530\\u8def174\\u53f7\\u91d1\\u6c5f\\u82d1\\u5c0f\\u533aD5-S5","store_zip":"213000","store_tel":"18118005569","store_state":"1","store_close_info":"","store_keywords":"\\u91d1\\u575b\\u4e30\\u7530\\u8def\\u95e8\\u5e97","store_description":"\\u91d1\\u575b\\u4e30\\u7530\\u8def\\u95e8\\u5e97","description":"","timestamp":"2017-03-10 12:59:21","xclearingprice":"2","city_id":null,"longitude":"119.98186105","latitude":"31.63139674","kilometer_x":"11354.78374297694","kilometer_y":"3511.0891111111114","store_type":"1","service_time":null,"bus_station":null,"business_type":null,"shop_owner":null,"shop_owner_phone":null,"province":null,"province_id":null,"city":null,"district":null,"district_id":null,"distance":15558,"main_photo":[],"store_photo":[]},{"store_id":"205","store_name":"\\u90ae\\u8f6e\\u540c\\u884c\\u6d4b\\u8bd5\\u95e8\\u5e97","store_company_name":"12345\\u5e97","userid":"0","store_contact_name":"\\u6731\\u78ca","store_contact_phone":"13800000001","store_owner_card":"320555","store_contact_qq":"QQ110","store_contact_weixin":null,"area_id":"115","area_info":null,"store_address":"\\u6c5f\\u82cf\\u7701\\u5e38\\u5dde\\u5e02\\u65b0\\u5317\\u533a\\u4e09\\u4e95\\u8857\\u9053\\u5e38\\u5dde\\u5e02\\u4eba\\u6c11\\u653f\\u5e9c\\u4f1a\\u8bae\\u4e2d\\u5fc3\\u5e38\\u5dde\\u5e02\\u4eba\\u6c11\\u653f\\u5e9c","store_zip":"","store_tel":"888888","store_state":"1","store_close_info":"","store_keywords":"\\u666f\\u6625","store_description":"\\u63cf\\u8ff0","description":"\\u65e0","timestamp":"2017-04-06 15:43:08","xclearingprice":"3","city_id":"115","longitude":"119.993665","latitude":"31.599802","kilometer_x":"11355.78374297694","kilometer_y":"3511.0891111111114","store_type":"2","service_time":"9:00-17:00","bus_station":"\\u6e38\\u8f6e\\u7ad9","business_type":"1","shop_owner":"xue","shop_owner_phone":"15589711646","province":"\\u6c5f\\u82cf\\u7701","province_id":"11","city":"\\u5e38\\u5dde\\u5e02","district":"\\u5e02\\u8f96\\u533a","district_id":"1217","distance":19101,"main_photo":[{"img_id":"67455","file_name":"e601302754d19318bf1f7446b3d344ad.jpg","img_onlineurl":"http:\\/\\/img.4008289828.com\\/images\\/0\\/34\\/205\\/e601302754d19318bf1f7446b3d344ad.jpg"}],"store_photo":[{"img_id":"67454","file_name":"e5411ea137eb21bc297f5bbe42c913ed.jpg","img_onlineurl":"http:\\/\\/img.4008289828.com\\/images\\/0\\/21\\/205\\/e5411ea137eb21bc297f5bbe42c913ed.jpg"}]},{"store_id":"207","store_name":"\\u859b\\u7684\\u6625\\u79cb\\u56fd\\u65c52","store_company_name":"\\u6625\\u79cb\\u56fd\\u65c5\\u6d4b\\u8bd5\\u5e97","userid":"0","store_contact_name":"xue","store_contact_phone":"15189711636","store_owner_card":"3233333","store_contact_qq":null,"store_contact_weixin":null,"area_id":"115","area_info":null,"store_address":"\\u54c8\\u5c14\\u6ee8\\u5e02\\u4e2d\\u5fc3","store_zip":"33333","store_tel":"11233","store_state":"1","store_close_info":"","store_keywords":"\\u6625\\u79cb\\u56fd\\u65c5","store_description":"good","description":"                ","timestamp":"2017-04-06 15:42:01","xclearingprice":"2","city_id":"115","longitude":"119.669568","latitude":"31.772752","kilometer_x":"11304.029940097023","kilometer_y":"3530.305777777778","store_type":"1","service_time":"9:00-16:00","bus_station":"\\u516c\\u4ea4","business_type":"1","shop_owner":"\\u6492\\u5730\\u65b9","shop_owner_phone":"12341243","province":"\\u6c5f\\u82cf\\u7701","province_id":"11","city":"\\u5e38\\u5dde\\u5e02","district":"\\u5e02\\u8f96\\u533a","district_id":"1217","distance":29504,"main_photo":[{"img_id":"67401","file_name":"22b93faa5105b1a21852dd72cc9468a1.jpg","img_onlineurl":"http:\\/\\/img.4008289828.com\\/images\\/0\\/34\\/207\\/22b93faa5105b1a21852dd72cc9468a1.jpg"}],"store_photo":[{"img_id":"67402","file_name":"d5d5e9ca5d1a8db7bcd4386ddafeb3f3.jpg","img_onlineurl":"http:\\/\\/img.4008289828.com\\/images\\/0\\/21\\/207\\/d5d5e9ca5d1a8db7bcd4386ddafeb3f3.jpg"},{"img_id":"67456","file_name":"0e6ffc1d83fc99e7cf368365e23c21b7.jpg","img_onlineurl":"http:\\/\\/img.4008289828.com\\/images\\/0\\/21\\/207\\/0e6ffc1d83fc99e7cf368365e23c21b7.jpg"}]},{"store_id":"201","store_name":"\\u65e0\\u9521\\u6c5f\\u9634\\u534e\\u58eb\\u8425\\u4e1a\\u90e8","store_company_name":"\\u65e0\\u9521\\u6c5f\\u9634\\u534e\\u58eb\\u8425\\u4e1a\\u90e8","userid":"23287","store_contact_name":"\\u859b\\u56fd\\u82f1","store_contact_phone":"15961631989","store_owner_card":"320219196309043264","store_contact_qq":null,"store_contact_weixin":null,"area_id":"113","area_info":null,"store_address":"\\u6c5f\\u9634\\u534e\\u58eb\\u9547\\u548c\\u5e73\\u65b0\\u67511\\u53f7\\u534e\\u58eb\\u8425\\u4e1a\\u90e8","store_zip":"","store_tel":"15961631989","store_state":"1","store_close_info":"","store_keywords":"\\u65e0\\u9521\\u6c5f\\u9634\\u534e\\u58eb\\u8425\\u4e1a\\u90e8","store_description":"\\u65e0\\u9521\\u6c5f\\u9634\\u534e\\u58eb\\u8425\\u4e1a\\u90e8","description":"   ","timestamp":"2017-03-08 10:19:01","xclearingprice":"2","city_id":null,"longitude":"120.4706641","latitude":"31.8369600","kilometer_x":"11371.796089884027","kilometer_y":"3537.44","store_type":"1","service_time":null,"bus_station":null,"business_type":null,"shop_owner":null,"shop_owner_phone":null,"province":null,"province_id":null,"city":null,"district":null,"district_id":null,"distance":46734,"main_photo":[],"store_photo":[]}]
     */

    private int res;
    private String msg;
    private String count;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * store_id : 206
         * store_name : \u6e05\u6f6d\u65b0\u5efa\u8425\u4e1a\u90e8\u6e05\u6f6d\u65b0\u5efa\u8425\u4e1a\u90e8\u6e05\u6f6d\u65b0\u5efa\u8425\u4e1a\u90e8\u6e05\u6f6d\u65b0\u5efa\u8425\u4e1a\u90e8\u6e05\u6f6d\u65b0\u5efa\u8425\u4e1a\u90e8
         * store_company_name : \u6e05\u6f6d\u65b0\u5efa\u8425\u4e1a\u90e8
         * userid : 0
         * store_contact_name : \u8521\u79c0\u534e
         * store_contact_phone : 18796949518
         * store_owner_card : 320411197603140828
         * store_contact_qq : null
         * store_contact_weixin : null
         * area_id : 115
         * area_info : null
         * store_address : \u6e05\u6f6d\u65b0\u5efa\u6751\u59d4\u697c\u4e0b
         * store_zip : 213000
         * store_tel : 18796949518
         * store_state : 1
         * store_close_info :
         * store_keywords : \u6e05\u6f6d\u65b0\u5efa\u8425\u4e1a\u90e8
         * store_description : \u6e05\u6f6d\u65b0\u5efa\u8425\u4e1a\u90e8
         * description :      \u6e05\u6f6d\u95e8\u5e97\u7b80\u4ecb
         * timestamp : 2017-03-31 09:49:49
         * xclearingprice : 2
         * city_id : 115
         * longitude : 119.935493
         * latitude : 31.766714
         * kilometer_x : 11329.88871047589
         * kilometer_y : 3529.6348888888892
         * store_type : 1
         * service_time : 9:00-19:00
         * bus_station : \u6e05\u6f6d\u7ad9
         * business_type : 1
         * shop_owner : xue1
         * shop_owner_phone : 15189711636
         * province : \u6c5f\u82cf\u7701
         * province_id : 11
         * city : \u5e38\u5dde\u5e02
         * district : \u949f\u697c\u533a
         * district_id : 1219
         * distance : 4411
         * main_photo : [{"img_id":"67408","file_name":"846a4043cb417190e45db79e8e59ea49.jpg","img_onlineurl":"http:\\/\\/img.4008289828.com\\/images\\/0\\/34\\/206\\/846a4043cb417190e45db79e8e59ea49.jpg"}]
         * store_photo : [{"img_id":"67409","file_name":"e1fb4c42d48520c0ba13c7770aa4cd4e.jpg","img_onlineurl":"http:\\/\\/img.4008289828.com\\/images\\/0\\/21\\/206\\/e1fb4c42d48520c0ba13c7770aa4cd4e.jpg"}]
         */

        private String store_id;
        private String store_name;
        private String store_company_name;
        private String userid;
        private String store_contact_name;
        private String store_contact_phone;
        private String store_owner_card;
        private Object store_contact_qq;
        private Object store_contact_weixin;
        private String area_id;
        private Object area_info;
        private String store_address;
        private String store_zip;
        private String store_tel;
        private String store_state;
        private String store_close_info;
        private String store_keywords;
        private String store_description;
        private String description;
        private String timestamp;
        private String xclearingprice;
        private String city_id;
        private String longitude;
        private String latitude;
        private String kilometer_x;
        private String kilometer_y;
        private String store_type;
        private String service_time;
        private String bus_station;
        private String business_type;
        private String shop_owner;
        private String shop_owner_phone;
        private String province;
        private String province_id;
        private String city;
        private String district;
        private String district_id;
        private int distance;
        private List<MainPhotoBean> main_photo;
        private List<StorePhotoBean> store_photo;

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getStore_company_name() {
            return store_company_name;
        }

        public void setStore_company_name(String store_company_name) {
            this.store_company_name = store_company_name;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getStore_contact_name() {
            return store_contact_name;
        }

        public void setStore_contact_name(String store_contact_name) {
            this.store_contact_name = store_contact_name;
        }

        public String getStore_contact_phone() {
            return store_contact_phone;
        }

        public void setStore_contact_phone(String store_contact_phone) {
            this.store_contact_phone = store_contact_phone;
        }

        public String getStore_owner_card() {
            return store_owner_card;
        }

        public void setStore_owner_card(String store_owner_card) {
            this.store_owner_card = store_owner_card;
        }

        public Object getStore_contact_qq() {
            return store_contact_qq;
        }

        public void setStore_contact_qq(Object store_contact_qq) {
            this.store_contact_qq = store_contact_qq;
        }

        public Object getStore_contact_weixin() {
            return store_contact_weixin;
        }

        public void setStore_contact_weixin(Object store_contact_weixin) {
            this.store_contact_weixin = store_contact_weixin;
        }

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public Object getArea_info() {
            return area_info;
        }

        public void setArea_info(Object area_info) {
            this.area_info = area_info;
        }

        public String getStore_address() {
            return store_address;
        }

        public void setStore_address(String store_address) {
            this.store_address = store_address;
        }

        public String getStore_zip() {
            return store_zip;
        }

        public void setStore_zip(String store_zip) {
            this.store_zip = store_zip;
        }

        public String getStore_tel() {
            return store_tel;
        }

        public void setStore_tel(String store_tel) {
            this.store_tel = store_tel;
        }

        public String getStore_state() {
            return store_state;
        }

        public void setStore_state(String store_state) {
            this.store_state = store_state;
        }

        public String getStore_close_info() {
            return store_close_info;
        }

        public void setStore_close_info(String store_close_info) {
            this.store_close_info = store_close_info;
        }

        public String getStore_keywords() {
            return store_keywords;
        }

        public void setStore_keywords(String store_keywords) {
            this.store_keywords = store_keywords;
        }

        public String getStore_description() {
            return store_description;
        }

        public void setStore_description(String store_description) {
            this.store_description = store_description;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getXclearingprice() {
            return xclearingprice;
        }

        public void setXclearingprice(String xclearingprice) {
            this.xclearingprice = xclearingprice;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getKilometer_x() {
            return kilometer_x;
        }

        public void setKilometer_x(String kilometer_x) {
            this.kilometer_x = kilometer_x;
        }

        public String getKilometer_y() {
            return kilometer_y;
        }

        public void setKilometer_y(String kilometer_y) {
            this.kilometer_y = kilometer_y;
        }

        public String getStore_type() {
            return store_type;
        }

        public void setStore_type(String store_type) {
            this.store_type = store_type;
        }

        public String getService_time() {
            return service_time;
        }

        public void setService_time(String service_time) {
            this.service_time = service_time;
        }

        public String getBus_station() {
            return bus_station;
        }

        public void setBus_station(String bus_station) {
            this.bus_station = bus_station;
        }

        public String getBusiness_type() {
            return business_type;
        }

        public void setBusiness_type(String business_type) {
            this.business_type = business_type;
        }

        public String getShop_owner() {
            return shop_owner;
        }

        public void setShop_owner(String shop_owner) {
            this.shop_owner = shop_owner;
        }

        public String getShop_owner_phone() {
            return shop_owner_phone;
        }

        public void setShop_owner_phone(String shop_owner_phone) {
            this.shop_owner_phone = shop_owner_phone;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getProvince_id() {
            return province_id;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getDistrict_id() {
            return district_id;
        }

        public void setDistrict_id(String district_id) {
            this.district_id = district_id;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public List<MainPhotoBean> getMain_photo() {
            return main_photo;
        }

        public void setMain_photo(List<MainPhotoBean> main_photo) {
            this.main_photo = main_photo;
        }

        public List<StorePhotoBean> getStore_photo() {
            return store_photo;
        }

        public void setStore_photo(List<StorePhotoBean> store_photo) {
            this.store_photo = store_photo;
        }

        public static class MainPhotoBean implements Serializable {
            /**
             * img_id : 67408
             * file_name : 846a4043cb417190e45db79e8e59ea49.jpg
             * img_onlineurl : http:\/\/img.4008289828.com\/images\/0\/34\/206\/846a4043cb417190e45db79e8e59ea49.jpg
             */

            private String img_id;
            private String file_name;
            private String img_onlineurl;

            public String getImg_id() {
                return img_id;
            }

            public void setImg_id(String img_id) {
                this.img_id = img_id;
            }

            public String getFile_name() {
                return file_name;
            }

            public void setFile_name(String file_name) {
                this.file_name = file_name;
            }

            public String getImg_onlineurl() {
                return img_onlineurl;
            }

            public void setImg_onlineurl(String img_onlineurl) {
                this.img_onlineurl = img_onlineurl;
            }
        }

        public static class StorePhotoBean implements Serializable{
            /**
             * img_id : 67409
             * file_name : e1fb4c42d48520c0ba13c7770aa4cd4e.jpg
             * img_onlineurl : http:\/\/img.4008289828.com\/images\/0\/21\/206\/e1fb4c42d48520c0ba13c7770aa4cd4e.jpg
             */

            private String img_id;
            private String file_name;
            private String img_onlineurl;

            public String getImg_id() {
                return img_id;
            }

            public void setImg_id(String img_id) {
                this.img_id = img_id;
            }

            public String getFile_name() {
                return file_name;
            }

            public void setFile_name(String file_name) {
                this.file_name = file_name;
            }

            public String getImg_onlineurl() {
                return img_onlineurl;
            }

            public void setImg_onlineurl(String img_onlineurl) {
                this.img_onlineurl = img_onlineurl;
            }
        }
    }
}
