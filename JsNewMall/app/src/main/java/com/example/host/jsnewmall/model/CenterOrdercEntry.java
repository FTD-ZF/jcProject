package com.example.host.jsnewmall.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by host on 2017/3/15.
 */

public class CenterOrdercEntry {

    /**
     * res : 1
     * msg : \u8ba2\u5355\u4fe1\u606f\u67e5\u8be2\u6210\u529f
     * count : 13
     * orderlist : [{"orderid":"2809","order_code":"w170309134039039703","order_total_money":"6498.00","order_state":"2","order_type":"1","team_title":"\\u3010\\u6e05\\u65b0\\u6cf0\\u56fd\\u3011\\u6cf0\\u56fd\\u66fc\\u8c37\\u82ad\\u63d0\\u96c5\\u4e94\\u665a\\u516d\\u65e5\\u6e38 \\uff08\\u5e38\\u5dde\\u5f80\\u8fd4\\uff0c\\u81ea\\u7ec4\\u56e2\\uff09","godate":"2017-03-07","link_man":"15961167765","link_mobile":"15961167765","add_date":"2017-03-09 13:40:39","ranges":"3","linestype":"1","linesid":"13510","photo":"http:\\/\\/img1.uu1.com\\/images\\/0\\/14\\/7472\\/8951732c65ddfdfd639443f6d621cdc5.jpg","is_contract":0,"saleprice":"3099.00","crqty":2,"rtqty":0,"lrqty":0,"xsqty":0,"totalnum":2,"liner_saleprice":null,"liner_totalnum":0},{"orderid":"2696","order_code":"w170308142240160954","order_total_money":"6498.00","order_state":"2","order_type":"1","team_title":"\\u3010\\u6e05\\u65b0\\u6cf0\\u56fd\\u3011\\u6cf0\\u56fd\\u66fc\\u8c37\\u82ad\\u63d0\\u96c5\\u4e94\\u665a\\u516d\\u65e5\\u6e38 \\uff08\\u5e38\\u5dde\\u5f80\\u8fd4\\uff0c\\u81ea\\u7ec4\\u56e2\\uff09","godate":"2017-03-07","link_man":"123","link_mobile":"13777777777","add_date":"2017-03-08 14:22:40","ranges":"3","linestype":"1","linesid":"13510","photo":"http:\\/\\/img1.uu1.com\\/images\\/0\\/14\\/7472\\/8951732c65ddfdfd639443f6d621cdc5.jpg","is_contract":0,"saleprice":"3099.00","crqty":2,"rtqty":0,"lrqty":0,"xsqty":0,"totalnum":2,"liner_saleprice":null,"liner_totalnum":0},{"orderid":"2677","order_code":"w170307104308588428","order_total_money":"0.01","order_state":"2","order_type":"1","team_title":"\\u3010\\u6e05\\u65b0\\u6cf0\\u56fd\\u3011\\u6cf0\\u56fd\\u66fc\\u8c37\\u82ad\\u63d0\\u96c5\\u4e94\\u665a\\u516d\\u65e5\\u6e38 \\uff08\\u5e38\\u5dde\\u5f80\\u8fd4\\uff0c\\u81ea\\u7ec4\\u56e2\\uff09","godate":"2017-03-28","link_man":"123","link_mobile":"123","add_date":"2017-03-07 10:43:08","ranges":"3","linestype":"1","linesid":"13510","photo":"http:\\/\\/img1.uu1.com\\/images\\/0\\/14\\/7472\\/8951732c65ddfdfd639443f6d621cdc5.jpg","is_contract":0,"saleprice":null,"crqty":null,"rtqty":null,"lrqty":null,"xsqty":null,"totalnum":0,"liner_saleprice":null,"liner_totalnum":null},{"orderid":"2667","order_code":"w170303171403443181","order_total_money":"9897.00","order_state":"2","order_type":"1","team_title":"\\u3010\\u6e05\\u65b0\\u6cf0\\u56fd\\u3011\\u6cf0\\u56fd\\u66fc\\u8c37\\u82ad\\u63d0\\u96c5\\u4e94\\u665a\\u516d\\u65e5\\u6e38 \\uff08\\u5e38\\u5dde\\u5f80\\u8fd4\\uff0c\\u81ea\\u7ec4\\u56e2\\uff09","godate":"2017-03-28","link_man":"zhaok","link_mobile":"13861156683","add_date":"2017-03-03 17:14:03","ranges":"3","linestype":"1","linesid":"13510","photo":"http:\\/\\/img1.uu1.com\\/images\\/0\\/14\\/7472\\/8951732c65ddfdfd639443f6d621cdc5.jpg","is_contract":0,"saleprice":"3.01","crqty":3,"rtqty":0,"lrqty":0,"xsqty":0,"totalnum":3,"liner_saleprice":null,"liner_totalnum":0},{"orderid":"2664","order_code":"w170302114003003993","order_total_money":"9897.00","order_state":"2","order_type":"1","team_title":"\\u3010\\u6e05\\u65b0\\u6cf0\\u56fd\\u3011\\u6cf0\\u56fd\\u66fc\\u8c37\\u82ad\\u63d0\\u96c5\\u4e94\\u665a\\u516d\\u65e5\\u6e38 \\uff08\\u5e38\\u5dde\\u5f80\\u8fd4\\uff0c\\u81ea\\u7ec4\\u56e2\\uff09","godate":"2017-03-09","link_man":"zhaok","link_mobile":"13861156683","add_date":"2017-03-02 11:40:03","ranges":"3","linestype":"1","linesid":"13510","photo":"http:\\/\\/img1.uu1.com\\/images\\/0\\/14\\/7472\\/8951732c65ddfdfd639443f6d621cdc5.jpg","is_contract":0,"saleprice":"3099.00","crqty":3,"rtqty":0,"lrqty":0,"xsqty":0,"totalnum":3,"liner_saleprice":null,"liner_totalnum":0},{"orderid":"2652","order_code":"w170228140851131151","order_total_money":"550.06","order_state":"2","order_type":"1","team_title":"\\u3010\\u6e38\\u5c71\\u73a9\\u6c34\\uff0c\\u4e70\\u4e00\\u9001\\u4e00\\u3011\\u6e56\\u5357\\u97f6\\u5c71\\u3001\\u5f20\\u5bb6\\u754c\\u73bb\\u7483\\u6865\\u3001\\u9ec4\\u9f99\\u6d1e\\u3001\\u571f\\u53f8\\u738b\\u5e9c\\u3001\\u58a8\\u620e\\u82d7\\u5be8\\u3001\\u51e4\\u51f0\\u53e4\\u57ce\\u3001\\u6e56\\u5317\\u4e09\\u5ce1\\u3001\\u897f\\u9675\\u5ce1\\u5168\\u666f\\u4e03\\u65e5\\u6e38\\uff08\\u6c7d\\u8f66\\u73ed\\uff09","godate":"2017-03-04","link_man":"aa","link_mobile":"15961167765","add_date":"2017-02-28 14:08:51","ranges":"2","linestype":"1","linesid":"13549","photo":"","is_contract":0,"saleprice":null,"crqty":"1","rtqty":null,"lrqty":null,"xsqty":null,"totalnum":1,"liner_saleprice":null,"liner_totalnum":null},{"orderid":"2602","order_code":"w161118142541341352","order_total_money":"0.02","order_state":"2","order_type":"1","team_title":"\\u5929\\u6d25-\\u9999\\u6cb3-\\u5317\\u4eac\\u8003\\u5bdf\\u53cc\\u9ad84\\u65e5\\u6e38","godate":"2016-11-30","link_man":"\\u6d4b\\u8bd5\\u6d4b\\u8bd5","link_mobile":"15151940085","add_date":"2016-11-18 14:25:41","ranges":"0","linestype":"1","linesid":"10436","photo":"","is_contract":0,"saleprice":null,"crqty":"1","rtqty":null,"lrqty":null,"xsqty":null,"totalnum":1,"liner_saleprice":null,"liner_totalnum":null},{"orderid":"2588","order_code":"w161110083620180556","order_total_money":"0.01","order_state":"2","order_type":"1","team_title":"\\u3010\\u4e1c\\u822a\\u3011\\u6d77\\u9646\\u7a7a\\u6fb3\\u65b0\\u51ef\\u58a812\\u65e5 \\u5fc3\\u52a8\\u4e4b\\u65c5   \\u3010\\u7ebf\\u8def2\\u3011","godate":"2016-11-17","link_man":"111","link_mobile":"111111111111","add_date":"2016-11-10 08:36:20","ranges":"3","linestype":"1","linesid":"9959","photo":"","is_contract":0,"saleprice":null,"crqty":"1","rtqty":null,"lrqty":null,"xsqty":null,"totalnum":1,"liner_saleprice":null,"liner_totalnum":null},{"orderid":"2587","order_code":"w161109172315395348","order_total_money":"0.01","order_state":"2","order_type":"1","team_title":"\\u3010\\u4e1c\\u822a\\u3011\\u6d77\\u9646\\u7a7a\\u6fb3\\u65b0\\u51ef\\u58a812\\u65e5 \\u5fc3\\u52a8\\u4e4b\\u65c5   \\u3010\\u7ebf\\u8def2\\u3011","godate":"2016-11-17","link_man":"\\u6d4b\\u8bd5","link_mobile":"15151940085","add_date":"2016-11-09 17:23:15","ranges":"3","linestype":"1","linesid":"9959","photo":"","is_contract":0,"saleprice":null,"crqty":"1","rtqty":null,"lrqty":null,"xsqty":null,"totalnum":1,"liner_saleprice":null,"liner_totalnum":null},{"orderid":"2586","order_code":"w161109171237757289","order_total_money":"0.01","order_state":"2","order_type":"1","team_title":"\\u3010\\u4e1c\\u822a\\u3011\\u6d77\\u9646\\u7a7a\\u6fb3\\u65b0\\u51ef\\u58a812\\u65e5 \\u5fc3\\u52a8\\u4e4b\\u65c5   \\u3010\\u7ebf\\u8def2\\u3011","godate":"2016-11-17","link_man":"\\u6d4b\\u8bd5","link_mobile":"15151940085","add_date":"2016-11-09 17:12:37","ranges":"3","linestype":"1","linesid":"9959","photo":"","is_contract":0,"saleprice":null,"crqty":"1","rtqty":null,"lrqty":null,"xsqty":null,"totalnum":1,"liner_saleprice":null,"liner_totalnum":null}]
     */

    private int res;
    private String msg;
    private int count;
    private List<OrderlistBean> orderlist;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<OrderlistBean> getOrderlist() {
        return orderlist;
    }

    public void setOrderlist(List<OrderlistBean> orderlist) {
        this.orderlist = orderlist;
    }

    public static class OrderlistBean  implements Serializable {
        /**
         * orderid : 2809
         * order_code : w170309134039039703
         * order_total_money : 6498.00
         * order_state : 2
         * order_type : 1
         * team_title : \u3010\u6e05\u65b0\u6cf0\u56fd\u3011\u6cf0\u56fd\u66fc\u8c37\u82ad\u63d0\u96c5\u4e94\u665a\u516d\u65e5\u6e38 \uff08\u5e38\u5dde\u5f80\u8fd4\uff0c\u81ea\u7ec4\u56e2\uff09
         * godate : 2017-03-07
         * link_man : 15961167765
         * link_mobile : 15961167765
         * add_date : 2017-03-09 13:40:39
         * ranges : 3
         * linestype : 1
         * linesid : 13510
         * photo : http:\/\/img1.uu1.com\/images\/0\/14\/7472\/8951732c65ddfdfd639443f6d621cdc5.jpg
         * is_contract : 0
         * saleprice : 3099.00
         * crqty : 2
         * rtqty : 0
         * lrqty : 0
         * xsqty : 0
         * totalnum : 2
         * liner_saleprice : null
         * liner_totalnum : 0
         */

        private String orderid;
        private String order_code;
        private String order_total_money;
        private String order_state;
        private String order_type;
        private String team_title;
        private String godate;
        private String link_man;
        private String link_mobile;
        private String add_date;
        private String ranges;
        private String linestype;
        private String linesid;
        private String photo;
        private int is_contract;
        private String saleprice;
        private int crqty;
        private int rtqty;
        private int lrqty;
        private int xsqty;
        private int totalnum;
        private Object liner_saleprice;
        private int liner_totalnum;

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getOrder_code() {
            return order_code;
        }

        public void setOrder_code(String order_code) {
            this.order_code = order_code;
        }

        public String getOrder_total_money() {
            return order_total_money;
        }

        public void setOrder_total_money(String order_total_money) {
            this.order_total_money = order_total_money;
        }

        public String getOrder_state() {
            return order_state;
        }

        public void setOrder_state(String order_state) {
            this.order_state = order_state;
        }

        public String getOrder_type() {
            return order_type;
        }

        public void setOrder_type(String order_type) {
            this.order_type = order_type;
        }

        public String getTeam_title() {
            return team_title;
        }

        public void setTeam_title(String team_title) {
            this.team_title = team_title;
        }

        public String getGodate() {
            return godate;
        }

        public void setGodate(String godate) {
            this.godate = godate;
        }

        public String getLink_man() {
            return link_man;
        }

        public void setLink_man(String link_man) {
            this.link_man = link_man;
        }

        public String getLink_mobile() {
            return link_mobile;
        }

        public void setLink_mobile(String link_mobile) {
            this.link_mobile = link_mobile;
        }

        public String getAdd_date() {
            return add_date;
        }

        public void setAdd_date(String add_date) {
            this.add_date = add_date;
        }

        public String getRanges() {
            return ranges;
        }

        public void setRanges(String ranges) {
            this.ranges = ranges;
        }

        public String getLinestype() {
            return linestype;
        }

        public void setLinestype(String linestype) {
            this.linestype = linestype;
        }

        public String getLinesid() {
            return linesid;
        }

        public void setLinesid(String linesid) {
            this.linesid = linesid;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getIs_contract() {
            return is_contract;
        }

        public void setIs_contract(int is_contract) {
            this.is_contract = is_contract;
        }

        public String getSaleprice() {
            return saleprice;
        }

        public void setSaleprice(String saleprice) {
            this.saleprice = saleprice;
        }

        public int getCrqty() {
            return crqty;
        }

        public void setCrqty(int crqty) {
            this.crqty = crqty;
        }

        public int getRtqty() {
            return rtqty;
        }

        public void setRtqty(int rtqty) {
            this.rtqty = rtqty;
        }

        public int getLrqty() {
            return lrqty;
        }

        public void setLrqty(int lrqty) {
            this.lrqty = lrqty;
        }

        public int getXsqty() {
            return xsqty;
        }

        public void setXsqty(int xsqty) {
            this.xsqty = xsqty;
        }

        public int getTotalnum() {
            return totalnum;
        }

        public void setTotalnum(int totalnum) {
            this.totalnum = totalnum;
        }

        public Object getLiner_saleprice() {
            return liner_saleprice;
        }

        public void setLiner_saleprice(Object liner_saleprice) {
            this.liner_saleprice = liner_saleprice;
        }

        public int getLiner_totalnum() {
            return liner_totalnum;
        }

        public void setLiner_totalnum(int liner_totalnum) {
            this.liner_totalnum = liner_totalnum;
        }
    }
}
