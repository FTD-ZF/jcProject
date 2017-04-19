package com.example.host.jsnewmall.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by host on 2017/3/16.
 */

public class OrderdetailsEntry {


    /**
     * res : 1
     * msg : \u67e5\u8be2\u6210\u529f
     * orderdata : {"id":"2925","guid":"22017032209443607655205369","nw_guid":null,"order_code":"w170322094436076271","order_total_money":"6198.00","order_paid_money":"0.00","order_notpaid_money":"6198.00","order_coupon_money":"0.00","order_insurance_money":"0.00","order_product_money":"6198.00","order_freight_money":"0.00","order_pay_state":"0","link_man":"as","link_mobile":"13861156684","link_email":"dog","user_id":"24025","user_name":"15961167765","order_state":"1","add_date":"2017-03-22 09:44:36","order_remark":"test","order_type":"1","order_source":"3","is_sync":"2","timestamp":"0000-00-00 00:00:00","storeid":"0","cityid":"115","disid":"0","order_code_old":"","sales_type":"0","interface_mark":"1","saleprice":"3099.00","original_price":"0.00","need_contract":0}
     * orderaddress : false
     * orderattach : []
     * ordercontact : [{"id":"648","guid":null,"orderid":"2925","true_name":"\\u554a\\u554a22\\u554a","paper_type":"5","paper_num":"588008","contact_email":"","contact_phone":"2345668","sex":"0","birthday":"1970-01-01","validdate":"1970-01-01","country_id":"1","original_price":"0.00"}]
     * orderinvoice : false
     * orderpay : []
     * orderproduct : [{"id":"3362","guid":null,"orderid":"2925","order_code":"w170322094436076271","teamguid":"8629","subteamguid":"8629115","teampriceguid":"862911516479","pricetype":"1","pricetypeid":"16479","pricetypename":"\\u6210\\u4eba","saleprice":"3099.00","crqty":"2","rtqty":"0","lrqty":"0","xsqty":"0","timestamp":"2017-03-22 09:44:36","clearingtype":"E","clearingprice":"3099.00","line_id":"13510","is_source":"1"}]
     * orderteam : {"id":"2635","guid":null,"orderid":"2925","order_code":"w170322094436076271","linestype":"1","linesid":"13510","teamid":"76606","subteamid":"142221","linesguid":"ddc19cce-b357-4b1e-b751-3f3510670e11","teamguid":"8629","subteamguid":"8629115","teamno":"CCJ-S017170323024","team_title":"\\u3010\\u6e05\\u65b0\\u6cf0\\u56fd\\u3011\\u6cf0\\u56fd\\u66fc\\u8c37\\u82ad\\u63d0\\u96c5\\u4e94\\u665a\\u516d\\u65e5\\u6e38 \\uff08\\u5e38\\u5dde\\u5f80\\u8fd4\\uff0c\\u81ea\\u7ec4\\u56e2\\uff09","godate":"2017-03-23","backdate":"2017-03-28","day_count":"6","timestamp":"0000-00-00 00:00:00","ab_name":"\\u5e38\\u5dde","order_startcity":"\\u5e38\\u5dde"}
     */

    private int res;
    private String msg;
    private OrderdataBean orderdata;
    private boolean orderaddress;
    private boolean orderinvoice;
    private OrderteamBean orderteam;
    private List<?> orderattach;
    private List<OrdercontactBean> ordercontact;
    private List<?> orderpay;
    private List<OrderproductBean> orderproduct;

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

    public OrderdataBean getOrderdata() {
        return orderdata;
    }

    public void setOrderdata(OrderdataBean orderdata) {
        this.orderdata = orderdata;
    }

    public boolean isOrderaddress() {
        return orderaddress;
    }

    public void setOrderaddress(boolean orderaddress) {
        this.orderaddress = orderaddress;
    }

    public boolean isOrderinvoice() {
        return orderinvoice;
    }

    public void setOrderinvoice(boolean orderinvoice) {
        this.orderinvoice = orderinvoice;
    }

    public OrderteamBean getOrderteam() {
        return orderteam;
    }

    public void setOrderteam(OrderteamBean orderteam) {
        this.orderteam = orderteam;
    }

    public List<?> getOrderattach() {
        return orderattach;
    }

    public void setOrderattach(List<?> orderattach) {
        this.orderattach = orderattach;
    }

    public List<OrdercontactBean> getOrdercontact() {
        return ordercontact;
    }

    public void setOrdercontact(List<OrdercontactBean> ordercontact) {
        this.ordercontact = ordercontact;
    }

    public List<?> getOrderpay() {
        return orderpay;
    }

    public void setOrderpay(List<?> orderpay) {
        this.orderpay = orderpay;
    }

    public List<OrderproductBean> getOrderproduct() {
        return orderproduct;
    }

    public void setOrderproduct(List<OrderproductBean> orderproduct) {
        this.orderproduct = orderproduct;
    }

    public static class OrderdataBean {
        /**
         * id : 2925
         * guid : 22017032209443607655205369
         * nw_guid : null
         * order_code : w170322094436076271
         * order_total_money : 6198.00
         * order_paid_money : 0.00
         * order_notpaid_money : 6198.00
         * order_coupon_money : 0.00
         * order_insurance_money : 0.00
         * order_product_money : 6198.00
         * order_freight_money : 0.00
         * order_pay_state : 0
         * link_man : as
         * link_mobile : 13861156684
         * link_email : dog
         * user_id : 24025
         * user_name : 15961167765
         * order_state : 1
         * add_date : 2017-03-22 09:44:36
         * order_remark : test
         * order_type : 1
         * order_source : 3
         * is_sync : 2
         * timestamp : 0000-00-00 00:00:00
         * storeid : 0
         * cityid : 115
         * disid : 0
         * order_code_old :
         * sales_type : 0
         * interface_mark : 1
         * saleprice : 3099.00
         * original_price : 0.00
         * need_contract : 0
         */

        private String id;
        private String guid;
        private Object nw_guid;
        private String order_code;
        private String order_total_money;
        private String order_paid_money;
        private String order_notpaid_money;
        private String order_coupon_money;
        private String order_insurance_money;
        private String order_product_money;
        private String order_freight_money;
        private String order_pay_state;
        private String link_man;
        private String link_mobile;
        private String link_email;
        private String user_id;
        private String user_name;
        private String order_state;
        private String add_date;
        private String order_remark;
        private String order_type;
        private String order_source;
        private String is_sync;
        private String timestamp;
        private String storeid;
        private String cityid;
        private String disid;
        private String order_code_old;
        private String sales_type;
        private String interface_mark;
        private String saleprice;
        private String original_price;
        private int need_contract;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public Object getNw_guid() {
            return nw_guid;
        }

        public void setNw_guid(Object nw_guid) {
            this.nw_guid = nw_guid;
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

        public String getOrder_paid_money() {
            return order_paid_money;
        }

        public void setOrder_paid_money(String order_paid_money) {
            this.order_paid_money = order_paid_money;
        }

        public String getOrder_notpaid_money() {
            return order_notpaid_money;
        }

        public void setOrder_notpaid_money(String order_notpaid_money) {
            this.order_notpaid_money = order_notpaid_money;
        }

        public String getOrder_coupon_money() {
            return order_coupon_money;
        }

        public void setOrder_coupon_money(String order_coupon_money) {
            this.order_coupon_money = order_coupon_money;
        }

        public String getOrder_insurance_money() {
            return order_insurance_money;
        }

        public void setOrder_insurance_money(String order_insurance_money) {
            this.order_insurance_money = order_insurance_money;
        }

        public String getOrder_product_money() {
            return order_product_money;
        }

        public void setOrder_product_money(String order_product_money) {
            this.order_product_money = order_product_money;
        }

        public String getOrder_freight_money() {
            return order_freight_money;
        }

        public void setOrder_freight_money(String order_freight_money) {
            this.order_freight_money = order_freight_money;
        }

        public String getOrder_pay_state() {
            return order_pay_state;
        }

        public void setOrder_pay_state(String order_pay_state) {
            this.order_pay_state = order_pay_state;
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

        public String getLink_email() {
            return link_email;
        }

        public void setLink_email(String link_email) {
            this.link_email = link_email;
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

        public String getOrder_state() {
            return order_state;
        }

        public void setOrder_state(String order_state) {
            this.order_state = order_state;
        }

        public String getAdd_date() {
            return add_date;
        }

        public void setAdd_date(String add_date) {
            this.add_date = add_date;
        }

        public String getOrder_remark() {
            return order_remark;
        }

        public void setOrder_remark(String order_remark) {
            this.order_remark = order_remark;
        }

        public String getOrder_type() {
            return order_type;
        }

        public void setOrder_type(String order_type) {
            this.order_type = order_type;
        }

        public String getOrder_source() {
            return order_source;
        }

        public void setOrder_source(String order_source) {
            this.order_source = order_source;
        }

        public String getIs_sync() {
            return is_sync;
        }

        public void setIs_sync(String is_sync) {
            this.is_sync = is_sync;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getStoreid() {
            return storeid;
        }

        public void setStoreid(String storeid) {
            this.storeid = storeid;
        }

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public String getDisid() {
            return disid;
        }

        public void setDisid(String disid) {
            this.disid = disid;
        }

        public String getOrder_code_old() {
            return order_code_old;
        }

        public void setOrder_code_old(String order_code_old) {
            this.order_code_old = order_code_old;
        }

        public String getSales_type() {
            return sales_type;
        }

        public void setSales_type(String sales_type) {
            this.sales_type = sales_type;
        }

        public String getInterface_mark() {
            return interface_mark;
        }

        public void setInterface_mark(String interface_mark) {
            this.interface_mark = interface_mark;
        }

        public String getSaleprice() {
            return saleprice;
        }

        public void setSaleprice(String saleprice) {
            this.saleprice = saleprice;
        }

        public String getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(String original_price) {
            this.original_price = original_price;
        }

        public int getNeed_contract() {
            return need_contract;
        }

        public void setNeed_contract(int need_contract) {
            this.need_contract = need_contract;
        }
    }

    public static class OrderteamBean {
        /**
         * id : 2635
         * guid : null
         * orderid : 2925
         * order_code : w170322094436076271
         * linestype : 1
         * linesid : 13510
         * teamid : 76606
         * subteamid : 142221
         * linesguid : ddc19cce-b357-4b1e-b751-3f3510670e11
         * teamguid : 8629
         * subteamguid : 8629115
         * teamno : CCJ-S017170323024
         * team_title : \u3010\u6e05\u65b0\u6cf0\u56fd\u3011\u6cf0\u56fd\u66fc\u8c37\u82ad\u63d0\u96c5\u4e94\u665a\u516d\u65e5\u6e38 \uff08\u5e38\u5dde\u5f80\u8fd4\uff0c\u81ea\u7ec4\u56e2\uff09
         * godate : 2017-03-23
         * backdate : 2017-03-28
         * day_count : 6
         * timestamp : 0000-00-00 00:00:00
         * ab_name : \u5e38\u5dde
         * order_startcity : \u5e38\u5dde
         */

        private String id;
        private Object guid;
        private String orderid;
        private String order_code;
        private String linestype;
        private String linesid;
        private String teamid;
        private String subteamid;
        private String linesguid;
        private String teamguid;
        private String subteamguid;
        private String teamno;
        private String team_title;
        private String godate;
        private String backdate;
        private String day_count;
        private String timestamp;
        private String ab_name;
        private String order_startcity;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getGuid() {
            return guid;
        }

        public void setGuid(Object guid) {
            this.guid = guid;
        }

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

        public String getTeamid() {
            return teamid;
        }

        public void setTeamid(String teamid) {
            this.teamid = teamid;
        }

        public String getSubteamid() {
            return subteamid;
        }

        public void setSubteamid(String subteamid) {
            this.subteamid = subteamid;
        }

        public String getLinesguid() {
            return linesguid;
        }

        public void setLinesguid(String linesguid) {
            this.linesguid = linesguid;
        }

        public String getTeamguid() {
            return teamguid;
        }

        public void setTeamguid(String teamguid) {
            this.teamguid = teamguid;
        }

        public String getSubteamguid() {
            return subteamguid;
        }

        public void setSubteamguid(String subteamguid) {
            this.subteamguid = subteamguid;
        }

        public String getTeamno() {
            return teamno;
        }

        public void setTeamno(String teamno) {
            this.teamno = teamno;
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

        public String getBackdate() {
            return backdate;
        }

        public void setBackdate(String backdate) {
            this.backdate = backdate;
        }

        public String getDay_count() {
            return day_count;
        }

        public void setDay_count(String day_count) {
            this.day_count = day_count;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getAb_name() {
            return ab_name;
        }

        public void setAb_name(String ab_name) {
            this.ab_name = ab_name;
        }

        public String getOrder_startcity() {
            return order_startcity;
        }

        public void setOrder_startcity(String order_startcity) {
            this.order_startcity = order_startcity;
        }
    }

    public static class OrdercontactBean implements Serializable{
        /**
         * id : 648
         * guid : null
         * orderid : 2925
         * true_name : \u554a\u554a22\u554a
         * paper_type : 5
         * paper_num : 588008
         * contact_email :
         * contact_phone : 2345668
         * sex : 0
         * birthday : 1970-01-01
         * validdate : 1970-01-01
         * country_id : 1
         * original_price : 0.00
         */

        private String id;
        private Object guid;
        private String orderid;
        private String true_name;
        private String paper_type;
        private String paper_num;
        private String contact_email;
        private String contact_phone;
        private String sex;
        private String birthday;
        private String validdate;
        private String country_id;
        private String original_price;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getGuid() {
            return guid;
        }

        public void setGuid(Object guid) {
            this.guid = guid;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
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

        public String getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(String original_price) {
            this.original_price = original_price;
        }
    }

    public static class OrderproductBean {
        /**
         * id : 3362
         * guid : null
         * orderid : 2925
         * order_code : w170322094436076271
         * teamguid : 8629
         * subteamguid : 8629115
         * teampriceguid : 862911516479
         * pricetype : 1
         * pricetypeid : 16479
         * pricetypename : \u6210\u4eba
         * saleprice : 3099.00
         * crqty : 2
         * rtqty : 0
         * lrqty : 0
         * xsqty : 0
         * timestamp : 2017-03-22 09:44:36
         * clearingtype : E
         * clearingprice : 3099.00
         * line_id : 13510
         * is_source : 1
         */

        private String id;
        private Object guid;
        private String orderid;
        private String order_code;
        private String teamguid;
        private String subteamguid;
        private String teampriceguid;
        private String pricetype;
        private String pricetypeid;
        private String pricetypename;
        private String saleprice;
        private String crqty;
        private String rtqty;
        private String lrqty;
        private String xsqty;
        private String timestamp;
        private String clearingtype;
        private String clearingprice;
        private String line_id;
        private String is_source;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getGuid() {
            return guid;
        }

        public void setGuid(Object guid) {
            this.guid = guid;
        }

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

        public String getTeamguid() {
            return teamguid;
        }

        public void setTeamguid(String teamguid) {
            this.teamguid = teamguid;
        }

        public String getSubteamguid() {
            return subteamguid;
        }

        public void setSubteamguid(String subteamguid) {
            this.subteamguid = subteamguid;
        }

        public String getTeampriceguid() {
            return teampriceguid;
        }

        public void setTeampriceguid(String teampriceguid) {
            this.teampriceguid = teampriceguid;
        }

        public String getPricetype() {
            return pricetype;
        }

        public void setPricetype(String pricetype) {
            this.pricetype = pricetype;
        }

        public String getPricetypeid() {
            return pricetypeid;
        }

        public void setPricetypeid(String pricetypeid) {
            this.pricetypeid = pricetypeid;
        }

        public String getPricetypename() {
            return pricetypename;
        }

        public void setPricetypename(String pricetypename) {
            this.pricetypename = pricetypename;
        }

        public String getSaleprice() {
            return saleprice;
        }

        public void setSaleprice(String saleprice) {
            this.saleprice = saleprice;
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

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getClearingtype() {
            return clearingtype;
        }

        public void setClearingtype(String clearingtype) {
            this.clearingtype = clearingtype;
        }

        public String getClearingprice() {
            return clearingprice;
        }

        public void setClearingprice(String clearingprice) {
            this.clearingprice = clearingprice;
        }

        public String getLine_id() {
            return line_id;
        }

        public void setLine_id(String line_id) {
            this.line_id = line_id;
        }

        public String getIs_source() {
            return is_source;
        }

        public void setIs_source(String is_source) {
            this.is_source = is_source;
        }
    }
}
