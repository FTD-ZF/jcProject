package com.example.host.jsnewmall.model;

/**
 * Created by host on 2017/3/24.
 */

public class SetUpEntry {

    /**
     * res : 1
     * msg : \u67e5\u8be2\u6210\u529f
     * data : {"uid":"24025","user_name":"15961167765","phone":"15961167765","headimgurl":"","nickname":"123456","realname":"haha","last_login_time":"2017-03-24 09:03:44","last_login_ip":"192.168.0.142","email":"","reg_time":"2016-08-09 10:24:50","status":"1","reg_source":"2","card_number":"2468865433","workid":"10172","sex":"1","birthday":"2017-03-03 00:00:00","age":0,"province_id":"11","province_name":"\\u6c5f\\u82cf","city_id":"115","city_name":"\\u5e38\\u5dde","city_area_id":"0","city_area_name":"","profession_name":"\\u9500\\u552e","inconme":null,"point_num":"49516","grown_value_num":"50058","user_grade_id":"14","user_grade_name":"\\u91d1\\u724c\\u4f1a\\u5458","get_grade_date":"2016-12-28 16:41:48","userid":"24025","roleid":"2","keyid":"2162","type":"distributor","iscan_be_dis":0}
     */

    private int res;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uid : 24025
         * user_name : 15961167765
         * phone : 15961167765
         * headimgurl :
         * nickname : 123456
         * realname : haha
         * last_login_time : 2017-03-24 09:03:44
         * last_login_ip : 192.168.0.142
         * email :
         * reg_time : 2016-08-09 10:24:50
         * status : 1
         * reg_source : 2
         * card_number : 2468865433
         * workid : 10172
         * sex : 1
         * birthday : 2017-03-03 00:00:00
         * age : 0
         * province_id : 11
         * province_name : \u6c5f\u82cf
         * city_id : 115
         * city_name : \u5e38\u5dde
         * city_area_id : 0
         * city_area_name :
         * profession_name : \u9500\u552e
         * inconme : null
         * point_num : 49516
         * grown_value_num : 50058
         * user_grade_id : 14
         * user_grade_name : \u91d1\u724c\u4f1a\u5458
         * get_grade_date : 2016-12-28 16:41:48
         * userid : 24025
         * roleid : 2
         * keyid : 2162
         * type : distributor
         * iscan_be_dis : 0
         */

        private String uid;
        private String user_name;
        private String phone;
        private String headimgurl;
        private String nickname;
        private String realname;
        private String last_login_time;
        private String last_login_ip;
        private String email;
        private String reg_time;
        private String status;
        private String reg_source;
        private String card_number;
        private String workid;
        private String sex;
        private String birthday;
        private int age;
        private String province_id;
        private String province_name;
        private String city_id;
        private String city_name;
        private String city_area_id;
        private String city_area_name;
        private String profession_name;
        private Object inconme;
        private String point_num;
        private String grown_value_num;
        private String user_grade_id;
        private String user_grade_name;
        private String get_grade_date;
        private String userid;
        private String roleid;
        private String keyid;
        private String type;
        private int iscan_be_dis;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getLast_login_time() {
            return last_login_time;
        }

        public void setLast_login_time(String last_login_time) {
            this.last_login_time = last_login_time;
        }

        public String getLast_login_ip() {
            return last_login_ip;
        }

        public void setLast_login_ip(String last_login_ip) {
            this.last_login_ip = last_login_ip;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getReg_time() {
            return reg_time;
        }

        public void setReg_time(String reg_time) {
            this.reg_time = reg_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getReg_source() {
            return reg_source;
        }

        public void setReg_source(String reg_source) {
            this.reg_source = reg_source;
        }

        public String getCard_number() {
            return card_number;
        }

        public void setCard_number(String card_number) {
            this.card_number = card_number;
        }

        public String getWorkid() {
            return workid;
        }

        public void setWorkid(String workid) {
            this.workid = workid;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getProvince_id() {
            return province_id;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getCity_area_id() {
            return city_area_id;
        }

        public void setCity_area_id(String city_area_id) {
            this.city_area_id = city_area_id;
        }

        public String getCity_area_name() {
            return city_area_name;
        }

        public void setCity_area_name(String city_area_name) {
            this.city_area_name = city_area_name;
        }

        public String getProfession_name() {
            return profession_name;
        }

        public void setProfession_name(String profession_name) {
            this.profession_name = profession_name;
        }

        public Object getInconme() {
            return inconme;
        }

        public void setInconme(Object inconme) {
            this.inconme = inconme;
        }

        public String getPoint_num() {
            return point_num;
        }

        public void setPoint_num(String point_num) {
            this.point_num = point_num;
        }

        public String getGrown_value_num() {
            return grown_value_num;
        }

        public void setGrown_value_num(String grown_value_num) {
            this.grown_value_num = grown_value_num;
        }

        public String getUser_grade_id() {
            return user_grade_id;
        }

        public void setUser_grade_id(String user_grade_id) {
            this.user_grade_id = user_grade_id;
        }

        public String getUser_grade_name() {
            return user_grade_name;
        }

        public void setUser_grade_name(String user_grade_name) {
            this.user_grade_name = user_grade_name;
        }

        public String getGet_grade_date() {
            return get_grade_date;
        }

        public void setGet_grade_date(String get_grade_date) {
            this.get_grade_date = get_grade_date;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getRoleid() {
            return roleid;
        }

        public void setRoleid(String roleid) {
            this.roleid = roleid;
        }

        public String getKeyid() {
            return keyid;
        }

        public void setKeyid(String keyid) {
            this.keyid = keyid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getIscan_be_dis() {
            return iscan_be_dis;
        }

        public void setIscan_be_dis(int iscan_be_dis) {
            this.iscan_be_dis = iscan_be_dis;
        }
    }
}
