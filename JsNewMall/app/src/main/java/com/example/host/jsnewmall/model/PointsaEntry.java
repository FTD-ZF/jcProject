package com.example.host.jsnewmall.model;

import java.util.List;

/**
 * Created by host on 2017/3/23.
 */

public class PointsaEntry {

    /**
     * res : 1
     * msg : \u67e5\u8be2\u6210\u529f
     * count : 37
     * data : {"all":"-450","list":[{"id":"2448","point_item_id":"36","relation_code":"","point_num":"4","add_date":"2017-03-21 09:59:58","user_id":"24025","user_name":"15961167765","user_grade_id":"14","user_grade_name":"\\u91d1\\u724c\\u4f1a\\u5458","remark":"\\u5e73\\u53f0\\u6bcf\\u65e5\\u767b\\u5f55\\u79ef\\u5206","client_source":"1"},{"id":"2446","point_item_id":"36","relation_code":"","point_num":"4","add_date":"2017-03-20 08:56:44","user_id":"24025","user_name":"15961167765","user_grade_id":"14","user_grade_name":"\\u91d1\\u724c\\u4f1a\\u5458","remark":"\\u5e73\\u53f0\\u6bcf\\u65e5\\u767b\\u5f55\\u79ef\\u5206","client_source":"1"},{"id":"2445","point_item_id":"36","relation_code":"","point_num":"4","add_date":"2017-03-17 10:25:28","user_id":"24025","user_name":"15961167765","user_grade_id":"14","user_grade_name":"\\u91d1\\u724c\\u4f1a\\u5458","remark":"\\u5e73\\u53f0\\u6bcf\\u65e5\\u767b\\u5f55\\u79ef\\u5206","client_source":"1"},{"id":"2444","point_item_id":"36","relation_code":"","point_num":"4","add_date":"2017-03-16 16:03:12","user_id":"24025","user_name":"15961167765","user_grade_id":"14","user_grade_name":"\\u91d1\\u724c\\u4f1a\\u5458","remark":"\\u5e73\\u53f0\\u6bcf\\u65e5\\u767b\\u5f55\\u79ef\\u5206","client_source":"1"},{"id":"2437","point_item_id":"36","relation_code":"","point_num":"4","add_date":"2017-03-13 09:11:09","user_id":"24025","user_name":"15961167765","user_grade_id":"14","user_grade_name":"\\u91d1\\u724c\\u4f1a\\u5458","remark":"\\u5e73\\u53f0\\u6bcf\\u65e5\\u767b\\u5f55\\u79ef\\u5206","client_source":"1"},{"id":"2434","point_item_id":"36","relation_code":"","point_num":"4","add_date":"2017-03-10 11:25:32","user_id":"24025","user_name":"15961167765","user_grade_id":"14","user_grade_name":"\\u91d1\\u724c\\u4f1a\\u5458","remark":"\\u5e73\\u53f0\\u6bcf\\u65e5\\u767b\\u5f55\\u79ef\\u5206","client_source":"1"},{"id":"2429","point_item_id":"36","relation_code":"","point_num":"4","add_date":"2017-03-09 09:03:56","user_id":"24025","user_name":"kkkkk","user_grade_id":"14","user_grade_name":"\\u91d1\\u724c\\u4f1a\\u5458","remark":"\\u5e73\\u53f0\\u6bcf\\u65e5\\u767b\\u5f55\\u79ef\\u5206","client_source":"1"},{"id":"2426","point_item_id":"36","relation_code":"","point_num":"4","add_date":"2017-03-08 14:12:55","user_id":"24025","user_name":"15961167765","user_grade_id":"14","user_grade_name":"\\u91d1\\u724c\\u4f1a\\u5458","remark":"\\u5e73\\u53f0\\u6bcf\\u65e5\\u767b\\u5f55\\u79ef\\u5206","client_source":"2"},{"id":"2425","point_item_id":"36","relation_code":"","point_num":"4","add_date":"2017-03-07 10:35:56","user_id":"24025","user_name":"15961167765","user_grade_id":"14","user_grade_name":"\\u91d1\\u724c\\u4f1a\\u5458","remark":"\\u5e73\\u53f0\\u6bcf\\u65e5\\u767b\\u5f55\\u79ef\\u5206","client_source":"1"},{"id":"2423","point_item_id":"36","relation_code":"","point_num":"4","add_date":"2017-03-06 08:52:23","user_id":"24025","user_name":"15961167765","user_grade_id":"14","user_grade_name":"\\u91d1\\u724c\\u4f1a\\u5458","remark":"\\u5e73\\u53f0\\u6bcf\\u65e5\\u767b\\u5f55\\u79ef\\u5206","client_source":"1"}]}
     */

    private int res;
    private String msg;
    private String count;
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * all : -450
         * list : [{"id":"2448","point_item_id":"36","relation_code":"","point_num":"4","add_date":"2017-03-21 09:59:58","user_id":"24025","user_name":"15961167765","user_grade_id":"14","user_grade_name":"\\u91d1\\u724c\\u4f1a\\u5458","remark":"\\u5e73\\u53f0\\u6bcf\\u65e5\\u767b\\u5f55\\u79ef\\u5206","client_source":"1"},{"id":"2446","point_item_id":"36","relation_code":"","point_num":"4","add_date":"2017-03-20 08:56:44","user_id":"24025","user_name":"15961167765","user_grade_id":"14","user_grade_name":"\\u91d1\\u724c\\u4f1a\\u5458","remark":"\\u5e73\\u53f0\\u6bcf\\u65e5\\u767b\\u5f55\\u79ef\\u5206","client_source":"1"},{"id":"2445","point_item_id":"36","relation_code":"","point_num":"4","add_date":"2017-03-17 10:25:28","user_id":"24025","user_name":"15961167765","user_grade_id":"14","user_grade_name":"\\u91d1\\u724c\\u4f1a\\u5458","remark":"\\u5e73\\u53f0\\u6bcf\\u65e5\\u767b\\u5f55\\u79ef\\u5206","client_source":"1"},{"id":"2444","point_item_id":"36","relation_code":"","point_num":"4","add_date":"2017-03-16 16:03:12","user_id":"24025","user_name":"15961167765","user_grade_id":"14","user_grade_name":"\\u91d1\\u724c\\u4f1a\\u5458","remark":"\\u5e73\\u53f0\\u6bcf\\u65e5\\u767b\\u5f55\\u79ef\\u5206","client_source":"1"},{"id":"2437","point_item_id":"36","relation_code":"","point_num":"4","add_date":"2017-03-13 09:11:09","user_id":"24025","user_name":"15961167765","user_grade_id":"14","user_grade_name":"\\u91d1\\u724c\\u4f1a\\u5458","remark":"\\u5e73\\u53f0\\u6bcf\\u65e5\\u767b\\u5f55\\u79ef\\u5206","client_source":"1"},{"id":"2434","point_item_id":"36","relation_code":"","point_num":"4","add_date":"2017-03-10 11:25:32","user_id":"24025","user_name":"15961167765","user_grade_id":"14","user_grade_name":"\\u91d1\\u724c\\u4f1a\\u5458","remark":"\\u5e73\\u53f0\\u6bcf\\u65e5\\u767b\\u5f55\\u79ef\\u5206","client_source":"1"},{"id":"2429","point_item_id":"36","relation_code":"","point_num":"4","add_date":"2017-03-09 09:03:56","user_id":"24025","user_name":"kkkkk","user_grade_id":"14","user_grade_name":"\\u91d1\\u724c\\u4f1a\\u5458","remark":"\\u5e73\\u53f0\\u6bcf\\u65e5\\u767b\\u5f55\\u79ef\\u5206","client_source":"1"},{"id":"2426","point_item_id":"36","relation_code":"","point_num":"4","add_date":"2017-03-08 14:12:55","user_id":"24025","user_name":"15961167765","user_grade_id":"14","user_grade_name":"\\u91d1\\u724c\\u4f1a\\u5458","remark":"\\u5e73\\u53f0\\u6bcf\\u65e5\\u767b\\u5f55\\u79ef\\u5206","client_source":"2"},{"id":"2425","point_item_id":"36","relation_code":"","point_num":"4","add_date":"2017-03-07 10:35:56","user_id":"24025","user_name":"15961167765","user_grade_id":"14","user_grade_name":"\\u91d1\\u724c\\u4f1a\\u5458","remark":"\\u5e73\\u53f0\\u6bcf\\u65e5\\u767b\\u5f55\\u79ef\\u5206","client_source":"1"},{"id":"2423","point_item_id":"36","relation_code":"","point_num":"4","add_date":"2017-03-06 08:52:23","user_id":"24025","user_name":"15961167765","user_grade_id":"14","user_grade_name":"\\u91d1\\u724c\\u4f1a\\u5458","remark":"\\u5e73\\u53f0\\u6bcf\\u65e5\\u767b\\u5f55\\u79ef\\u5206","client_source":"1"}]
         */

        private String all;
        private List<ListBean> list;

        public String getAll() {
            return all;
        }

        public void setAll(String all) {
            this.all = all;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 2448
             * point_item_id : 36
             * relation_code :
             * point_num : 4
             * add_date : 2017-03-21 09:59:58
             * user_id : 24025
             * user_name : 15961167765
             * user_grade_id : 14
             * user_grade_name : \u91d1\u724c\u4f1a\u5458
             * remark : \u5e73\u53f0\u6bcf\u65e5\u767b\u5f55\u79ef\u5206
             * client_source : 1
             */

            private String id;
            private String point_item_id;
            private String relation_code;
            private String point_num;
            private String add_date;
            private String user_id;
            private String user_name;
            private String user_grade_id;
            private String user_grade_name;
            private String remark;
            private String client_source;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPoint_item_id() {
                return point_item_id;
            }

            public void setPoint_item_id(String point_item_id) {
                this.point_item_id = point_item_id;
            }

            public String getRelation_code() {
                return relation_code;
            }

            public void setRelation_code(String relation_code) {
                this.relation_code = relation_code;
            }

            public String getPoint_num() {
                return point_num;
            }

            public void setPoint_num(String point_num) {
                this.point_num = point_num;
            }

            public String getAdd_date() {
                return add_date;
            }

            public void setAdd_date(String add_date) {
                this.add_date = add_date;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
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

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getClient_source() {
                return client_source;
            }

            public void setClient_source(String client_source) {
                this.client_source = client_source;
            }
        }
    }
}
