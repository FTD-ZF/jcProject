package com.example.host.jsnewmall.model;

/**
 * Created by host on 2017/3/17.
 */

public class ZhifubaoinfoEntry {


    /**
     * service : mobile.securitypay.pay
     * partner : 2088801243718770
     * seller_id : czcqgl@163.com
     * payment_type : 1
     * notify_url : http://open.4008289828.com/pay/notify_pcalipay.php
     * return_url : http://open.4008289828.com/pay/return_pcalipay.php
     * out_trade_no : 170317161418458486
     * subject : 景尚旅业商品_APP订单：w170308142240160954
     * total_fee : 6477.90
     * body : test
     * show_url : http://www.uu1.com
     * anti_phishing_key :
     * exter_invoke_ip :
     * _input_charset : utf-8
     * it_b_pay : 120m
     */

    private String service;
    private String partner;
    private String seller_id;
    private String payment_type;
    private String notify_url;
    private String return_url;
    private String out_trade_no;
    private String subject;
    private String total_fee;
    private String body;
    private String show_url;
    private String anti_phishing_key;
    private String exter_invoke_ip;
    private String _input_charset;
    private String it_b_pay;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getReturn_url() {
        return return_url;
    }

    public void setReturn_url(String return_url) {
        this.return_url = return_url;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getShow_url() {
        return show_url;
    }

    public void setShow_url(String show_url) {
        this.show_url = show_url;
    }

    public String getAnti_phishing_key() {
        return anti_phishing_key;
    }

    public void setAnti_phishing_key(String anti_phishing_key) {
        this.anti_phishing_key = anti_phishing_key;
    }

    public String getExter_invoke_ip() {
        return exter_invoke_ip;
    }

    public void setExter_invoke_ip(String exter_invoke_ip) {
        this.exter_invoke_ip = exter_invoke_ip;
    }

    public String get_input_charset() {
        return _input_charset;
    }

    public void set_input_charset(String _input_charset) {
        this._input_charset = _input_charset;
    }

    public String getIt_b_pay() {
        return it_b_pay;
    }

    public void setIt_b_pay(String it_b_pay) {
        this.it_b_pay = it_b_pay;
    }
}
