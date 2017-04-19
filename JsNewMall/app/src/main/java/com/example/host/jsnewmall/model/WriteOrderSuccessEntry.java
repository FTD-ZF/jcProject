package com.example.host.jsnewmall.model;

/**
 * Created by host on 2017/3/22.
 */

public class WriteOrderSuccessEntry {


    /**
     * res : 1
     * msg : 下单成功
     * data : {"is_pay":0,"user_id":"24025","order_id":"2926","order_sn":"w170322133001601446","order_total_money":"9597","crqty":"3","rtqty":"0","lrqty":"0","xsqty":"0","order_type":"1"}
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
         * is_pay : 0
         * user_id : 24025
         * order_id : 2926
         * order_sn : w170322133001601446
         * order_total_money : 9597
         * crqty : 3
         * rtqty : 0
         * lrqty : 0
         * xsqty : 0
         * order_type : 1
         */

        private int is_pay;
        private String user_id;
        private String order_id;
        private String order_sn;
        private String order_total_money;
        private String crqty;
        private String rtqty;
        private String lrqty;
        private String xsqty;
        private String order_type;

        public int getIs_pay() {
            return is_pay;
        }

        public void setIs_pay(int is_pay) {
            this.is_pay = is_pay;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getOrder_total_money() {
            return order_total_money;
        }

        public void setOrder_total_money(String order_total_money) {
            this.order_total_money = order_total_money;
        }

        public String getCrqty() {
            return crqty;
        }

        public void setCrqty(String crqty) {
            this.crqty = crqty;
        }

        public String getRtqty() {
            return rtqty;
        }

        public void setRtqty(String rtqty) {
            this.rtqty = rtqty;
        }

        public String getLrqty() {
            return lrqty;
        }

        public void setLrqty(String lrqty) {
            this.lrqty = lrqty;
        }

        public String getXsqty() {
            return xsqty;
        }

        public void setXsqty(String xsqty) {
            this.xsqty = xsqty;
        }

        public String getOrder_type() {
            return order_type;
        }

        public void setOrder_type(String order_type) {
            this.order_type = order_type;
        }
    }
}
