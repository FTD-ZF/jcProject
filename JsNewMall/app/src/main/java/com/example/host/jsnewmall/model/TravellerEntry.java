package com.example.host.jsnewmall.model;

import java.util.List;

/**
 * Created by host on 2017/3/20.
 */

public class TravellerEntry {

    /**
     * res : 1
     * count : 3
     * msg : \u67e5\u8be2\u6210\u529f
     * data : [{"contact_id":"284","uid":"24025","true_name":"\\u54c8\\u54c877","paper_type":"1","paper_num":"320404199111111111","contact_email":"","contact_phone":"15961167765","sex":"0","birthday":"1970-01-01","validdate":"1970-01-01","country_id":"1"}]
     */

    private int res;
    private String count;
    private String msg;
    private List<DataBean> data;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * contact_id : 284
         * uid : 24025
         * true_name : \u54c8\u54c877
         * paper_type : 1
         * paper_num : 320404199111111111
         * contact_email :
         * contact_phone : 15961167765
         * sex : 0
         * birthday : 1970-01-01
         * validdate : 1970-01-01
         * country_id : 1
         */

        private String contact_id;
        private String uid;
        private String true_name;
        private String paper_type;
        private String paper_num;
        private String contact_email;
        private String contact_phone;
        private String sex;
        private String birthday;
        private String validdate;
        private String country_id;

        public String getContact_id() {
            return contact_id;
        }

        public void setContact_id(String contact_id) {
            this.contact_id = contact_id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getTrue_name() {
            return true_name;
        }

        public void setTrue_name(String true_name) {
            this.true_name = true_name;
        }

        public String getPaper_type() {
            return paper_type;
        }

        public void setPaper_type(String paper_type) {
            this.paper_type = paper_type;
        }

        public String getPaper_num() {
            return paper_num;
        }

        public void setPaper_num(String paper_num) {
            this.paper_num = paper_num;
        }

        public String getContact_email() {
            return contact_email;
        }

        public void setContact_email(String contact_email) {
            this.contact_email = contact_email;
        }

        public String getContact_phone() {
            return contact_phone;
        }

        public void setContact_phone(String contact_phone) {
            this.contact_phone = contact_phone;
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

        public String getValiddate() {
            return validdate;
        }

        public void setValiddate(String validdate) {
            this.validdate = validdate;
        }

        public String getCountry_id() {
            return country_id;
        }

        public void setCountry_id(String country_id) {
            this.country_id = country_id;
        }
    }
}
