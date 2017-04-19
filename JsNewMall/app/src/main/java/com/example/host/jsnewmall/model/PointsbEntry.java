package com.example.host.jsnewmall.model;

import java.util.List;

/**
 * Created by host on 2017/3/23.
 */

public class PointsbEntry {

    /**
     * res : 1
     * msg : \u67e5\u8be2\u6210\u529f
     * count : 1
     * data : {"all":"-450","list":[{"id":"2260","point_item_id":"3","relation_code":"w161227184043243881","point_num":"-600","add_date":"2016-12-27 18:45:40","user_id":"24025","user_name":"15961167765","user_grade_id":"12","user_grade_name":"\\u94dc\\u724c\\u4f1a\\u5458","remark":"\\u5151\\u6362\\u79ef\\u5206","client_source":"1"}]}
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
         * list : [{"id":"2260","point_item_id":"3","relation_code":"w161227184043243881","point_num":"-600","add_date":"2016-12-27 18:45:40","user_id":"24025","user_name":"15961167765","user_grade_id":"12","user_grade_name":"\\u94dc\\u724c\\u4f1a\\u5458","remark":"\\u5151\\u6362\\u79ef\\u5206","client_source":"1"}]
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
             * id : 2260
             * point_item_id : 3
             * relation_code : w161227184043243881
             * point_num : -600
             * add_date : 2016-12-27 18:45:40
             * user_id : 24025
             * user_name : 15961167765
             * user_grade_id : 12
             * user_grade_name : \u94dc\u724c\u4f1a\u5458
             * remark : \u5151\u6362\u79ef\u5206
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
