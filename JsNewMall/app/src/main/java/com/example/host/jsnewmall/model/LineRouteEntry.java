package com.example.host.jsnewmall.model;

import java.util.List;

/**
 * Created by host on 2017/3/6.
 */

public class LineRouteEntry {

    /**
     * res : 1
     * msg : 查询成功
     * data_base : {"id":"14363","guid":"20170213154238-488593","lines_name":"测试线路","lines_title":"测试线路","lines_num":"11111111xxxxxxxxx","lines_supname":null,"lines_filetext":null,"respon_id":"0","respon_unitmaxid":"0","supplier_id":"25","supplier_name":"常州春秋国旅运营部(内部供应商)","lines_days":"1","lines_daysnight":"2","parent_lineid":"0","companyid":null,"company_name":null,"lines_validitybegin":"2017-02-13 00:00:00","lines_validityend":"2017-02-28 00:00:00","lines_modetype":null,"lines_from":"3","original_price":"0.00","scenic_spots_alongtheway":null,"isshow":"10","islogicdelete":"1","createuserid":"1","createtime":"2017-02-13 15:42:38","travelmode":"3","ranges":"0","treepath":null,"voucherstate":"0","lines_leaveaddress":"0","timestamp":"2017-03-31 15:09:11","iscustomized":"0","is_sync_scenic":"1","lines_erp_name":"","saleprice":"","lines_leavecityid":null,"lines_leavecityname":null,"lines_backcityid":null,"lines_backcityname":null}
     * data_contract : null
     * data_team : [{"id":"209","lines_id":"14363","lines_team_date":"2017-04-29 00:00:00","lines_team_price":"0.01","create_date_time":"2017-03-31 15:09:13","create_user_id":"1","create_user_name":"admin"}]
     * data_journey : [{"id":"77661","linesid":"14363","guid":"","linesguid":null,"journey_day":"1","journey_details":"自理","journey_basic_info":"测试","journey_break_fast":"酒店内","journey_lunch":"酒店内","journey_dinner":"酒店内","journey_lodging":"自理","journey_day_sight":"自理","journey_day_shopping":"自理","journey_day_sightself":"自理","journey_day_activity":"自理","createuserid":"1","createtime":"2017-03-31 15:09:11","timestamp":"0000-00-00 00:00:00","islogicdelete":"1","journey_details_listall":[]}]
     * data_related : {"id":"14461","linesid":"14363","guid":"","linesguid":null,"feature":"自理","feenotincluded":"自理","feeincluded":"自理","owncharge":"自理","notice":"自理","tip":"自理","shopping":"自理","activityaddress":"自理","featureeat":"自理","remarks":"自理","createuserid":null,"createtime":"2017-03-31 15:09:12","timestamp":"2017-03-31 15:09:12","islogicdelete":"1"}
     * data_service : {"id":"9315","linesid":"14363","guid":"","linesguid":null,"service_attrid":null,"service_levelattrname":"标准等级","service_eatattrname":"测试标准","service_liveattrname":"","service_gotrafficattrname":"飞机","service_backtrafficattrname":"飞机","service_sighttrafficattrname":"飞机","service_insuranceattrname":"5元/人/天","service_sightattrname":"含行程内非自费景点第一大门票","service_guideattrname":"全程导游服务","islogicdelete":"1","createtime":"2017-03-31 15:09:11","createuserid":"1","timestamp":"2017-03-31 15:09:12"}
     * data_show : [{"id":"33354","typeid":"104","title":"sad","content":"","pic_data":[{"link_title":"dsff","link_sort":"0","city_id":"0","cover":"0","img_name":"dsff","img_onlineurl":"http://img.4008289828.com/images/0/34/208/0cd7b5804bc8f315fe70005fed43f115.jpg","img_url":"http://img.4008289828.com/images/0/34/208/0cd7b5804bc8f315fe70005fed43f115.jpg"},{"link_title":"Barstow Tanger Outlets","link_sort":"0","city_id":"0","cover":"0","img_name":"Barstow Tanger Outlets","img_onlineurl":"http://img1.uu1.com/images/0/14/11647/1b56c6d74a8938f7c6765e23ac41639e.jpg","img_url":"http://img1.uu1.com/images/0/14/11647/1b56c6d74a8938f7c6765e23ac41639e.jpg"}]}]
     * data_img : [{"link_title":"dsff","link_sort":"0","city_id":"0","cover":"0","img_name":"dsff","img_onlineurl":"http://img.4008289828.com/images/0/34/208/0cd7b5804bc8f315fe70005fed43f115.jpg","img_url":"http://img.4008289828.com/images/0/34/208/0cd7b5804bc8f315fe70005fed43f115.jpg"},{"link_title":"Barstow Tanger Outlets","link_sort":"0","city_id":"0","cover":"0","img_name":"Barstow Tanger Outlets","img_onlineurl":"http://img1.uu1.com/images/0/14/11647/1b56c6d74a8938f7c6765e23ac41639e.jpg","img_url":"http://img1.uu1.com/images/0/14/11647/1b56c6d74a8938f7c6765e23ac41639e.jpg"}]
     */

    private int res;
    private String msg;
    private DataBaseBean data_base;
    private Object data_contract;
    private DataRelatedBean data_related;
    private DataServiceBean data_service;
    private List<DataTeamBean> data_team;
    private List<DataJourneyBean> data_journey;
    private List<DataShowBean> data_show;
    private List<DataImgBean> data_img;

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

    public DataBaseBean getData_base() {
        return data_base;
    }

    public void setData_base(DataBaseBean data_base) {
        this.data_base = data_base;
    }

    public Object getData_contract() {
        return data_contract;
    }

    public void setData_contract(Object data_contract) {
        this.data_contract = data_contract;
    }

    public DataRelatedBean getData_related() {
        return data_related;
    }

    public void setData_related(DataRelatedBean data_related) {
        this.data_related = data_related;
    }

    public DataServiceBean getData_service() {
        return data_service;
    }

    public void setData_service(DataServiceBean data_service) {
        this.data_service = data_service;
    }

    public List<DataTeamBean> getData_team() {
        return data_team;
    }

    public void setData_team(List<DataTeamBean> data_team) {
        this.data_team = data_team;
    }

    public List<DataJourneyBean> getData_journey() {
        return data_journey;
    }

    public void setData_journey(List<DataJourneyBean> data_journey) {
        this.data_journey = data_journey;
    }

    public List<DataShowBean> getData_show() {
        return data_show;
    }

    public void setData_show(List<DataShowBean> data_show) {
        this.data_show = data_show;
    }

    public List<DataImgBean> getData_img() {
        return data_img;
    }

    public void setData_img(List<DataImgBean> data_img) {
        this.data_img = data_img;
    }

    public static class DataBaseBean {
        /**
         * id : 14363
         * guid : 20170213154238-488593
         * lines_name : 测试线路
         * lines_title : 测试线路
         * lines_num : 11111111xxxxxxxxx
         * lines_supname : null
         * lines_filetext : null
         * respon_id : 0
         * respon_unitmaxid : 0
         * supplier_id : 25
         * supplier_name : 常州春秋国旅运营部(内部供应商)
         * lines_days : 1
         * lines_daysnight : 2
         * parent_lineid : 0
         * companyid : null
         * company_name : null
         * lines_validitybegin : 2017-02-13 00:00:00
         * lines_validityend : 2017-02-28 00:00:00
         * lines_modetype : null
         * lines_from : 3
         * original_price : 0.00
         * scenic_spots_alongtheway : null
         * isshow : 10
         * islogicdelete : 1
         * createuserid : 1
         * createtime : 2017-02-13 15:42:38
         * travelmode : 3
         * ranges : 0
         * treepath : null
         * voucherstate : 0
         * lines_leaveaddress : 0
         * timestamp : 2017-03-31 15:09:11
         * iscustomized : 0
         * is_sync_scenic : 1
         * lines_erp_name :
         * saleprice :
         * lines_leavecityid : null
         * lines_leavecityname : null
         * lines_backcityid : null
         * lines_backcityname : null
         */

        private String id;
        private String guid;
        private String lines_name;
        private String lines_title;
        private String lines_num;
        private Object lines_supname;
        private Object lines_filetext;
        private String respon_id;
        private String respon_unitmaxid;
        private String supplier_id;
        private String supplier_name;
        private String lines_days;
        private String lines_daysnight;
        private String parent_lineid;
        private Object companyid;
        private Object company_name;
        private String lines_validitybegin;
        private String lines_validityend;
        private Object lines_modetype;
        private String lines_from;
        private String original_price;
        private Object scenic_spots_alongtheway;
        private String isshow;
        private String islogicdelete;
        private String createuserid;
        private String createtime;
        private String travelmode;
        private String ranges;
        private Object treepath;
        private String voucherstate;
        private String lines_leaveaddress;
        private String timestamp;
        private String iscustomized;
        private String is_sync_scenic;
        private String lines_erp_name;
        private String saleprice;
        private Object lines_leavecityid;
        private Object lines_leavecityname;
        private Object lines_backcityid;
        private Object lines_backcityname;

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

        public String getLines_name() {
            return lines_name;
        }

        public void setLines_name(String lines_name) {
            this.lines_name = lines_name;
        }

        public String getLines_title() {
            return lines_title;
        }

        public void setLines_title(String lines_title) {
            this.lines_title = lines_title;
        }

        public String getLines_num() {
            return lines_num;
        }

        public void setLines_num(String lines_num) {
            this.lines_num = lines_num;
        }

        public Object getLines_supname() {
            return lines_supname;
        }

        public void setLines_supname(Object lines_supname) {
            this.lines_supname = lines_supname;
        }

        public Object getLines_filetext() {
            return lines_filetext;
        }

        public void setLines_filetext(Object lines_filetext) {
            this.lines_filetext = lines_filetext;
        }

        public String getRespon_id() {
            return respon_id;
        }

        public void setRespon_id(String respon_id) {
            this.respon_id = respon_id;
        }

        public String getRespon_unitmaxid() {
            return respon_unitmaxid;
        }

        public void setRespon_unitmaxid(String respon_unitmaxid) {
            this.respon_unitmaxid = respon_unitmaxid;
        }

        public String getSupplier_id() {
            return supplier_id;
        }

        public void setSupplier_id(String supplier_id) {
            this.supplier_id = supplier_id;
        }

        public String getSupplier_name() {
            return supplier_name;
        }

        public void setSupplier_name(String supplier_name) {
            this.supplier_name = supplier_name;
        }

        public String getLines_days() {
            return lines_days;
        }

        public void setLines_days(String lines_days) {
            this.lines_days = lines_days;
        }

        public String getLines_daysnight() {
            return lines_daysnight;
        }

        public void setLines_daysnight(String lines_daysnight) {
            this.lines_daysnight = lines_daysnight;
        }

        public String getParent_lineid() {
            return parent_lineid;
        }

        public void setParent_lineid(String parent_lineid) {
            this.parent_lineid = parent_lineid;
        }

        public Object getCompanyid() {
            return companyid;
        }

        public void setCompanyid(Object companyid) {
            this.companyid = companyid;
        }

        public Object getCompany_name() {
            return company_name;
        }

        public void setCompany_name(Object company_name) {
            this.company_name = company_name;
        }

        public String getLines_validitybegin() {
            return lines_validitybegin;
        }

        public void setLines_validitybegin(String lines_validitybegin) {
            this.lines_validitybegin = lines_validitybegin;
        }

        public String getLines_validityend() {
            return lines_validityend;
        }

        public void setLines_validityend(String lines_validityend) {
            this.lines_validityend = lines_validityend;
        }

        public Object getLines_modetype() {
            return lines_modetype;
        }

        public void setLines_modetype(Object lines_modetype) {
            this.lines_modetype = lines_modetype;
        }

        public String getLines_from() {
            return lines_from;
        }

        public void setLines_from(String lines_from) {
            this.lines_from = lines_from;
        }

        public String getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(String original_price) {
            this.original_price = original_price;
        }

        public Object getScenic_spots_alongtheway() {
            return scenic_spots_alongtheway;
        }

        public void setScenic_spots_alongtheway(Object scenic_spots_alongtheway) {
            this.scenic_spots_alongtheway = scenic_spots_alongtheway;
        }

        public String getIsshow() {
            return isshow;
        }

        public void setIsshow(String isshow) {
            this.isshow = isshow;
        }

        public String getIslogicdelete() {
            return islogicdelete;
        }

        public void setIslogicdelete(String islogicdelete) {
            this.islogicdelete = islogicdelete;
        }

        public String getCreateuserid() {
            return createuserid;
        }

        public void setCreateuserid(String createuserid) {
            this.createuserid = createuserid;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getTravelmode() {
            return travelmode;
        }

        public void setTravelmode(String travelmode) {
            this.travelmode = travelmode;
        }

        public String getRanges() {
            return ranges;
        }

        public void setRanges(String ranges) {
            this.ranges = ranges;
        }

        public Object getTreepath() {
            return treepath;
        }

        public void setTreepath(Object treepath) {
            this.treepath = treepath;
        }

        public String getVoucherstate() {
            return voucherstate;
        }

        public void setVoucherstate(String voucherstate) {
            this.voucherstate = voucherstate;
        }

        public String getLines_leaveaddress() {
            return lines_leaveaddress;
        }

        public void setLines_leaveaddress(String lines_leaveaddress) {
            this.lines_leaveaddress = lines_leaveaddress;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getIscustomized() {
            return iscustomized;
        }

        public void setIscustomized(String iscustomized) {
            this.iscustomized = iscustomized;
        }

        public String getIs_sync_scenic() {
            return is_sync_scenic;
        }

        public void setIs_sync_scenic(String is_sync_scenic) {
            this.is_sync_scenic = is_sync_scenic;
        }

        public String getLines_erp_name() {
            return lines_erp_name;
        }

        public void setLines_erp_name(String lines_erp_name) {
            this.lines_erp_name = lines_erp_name;
        }

        public String getSaleprice() {
            return saleprice;
        }

        public void setSaleprice(String saleprice) {
            this.saleprice = saleprice;
        }

        public Object getLines_leavecityid() {
            return lines_leavecityid;
        }

        public void setLines_leavecityid(Object lines_leavecityid) {
            this.lines_leavecityid = lines_leavecityid;
        }

        public Object getLines_leavecityname() {
            return lines_leavecityname;
        }

        public void setLines_leavecityname(Object lines_leavecityname) {
            this.lines_leavecityname = lines_leavecityname;
        }

        public Object getLines_backcityid() {
            return lines_backcityid;
        }

        public void setLines_backcityid(Object lines_backcityid) {
            this.lines_backcityid = lines_backcityid;
        }

        public Object getLines_backcityname() {
            return lines_backcityname;
        }

        public void setLines_backcityname(Object lines_backcityname) {
            this.lines_backcityname = lines_backcityname;
        }
    }

    public static class DataRelatedBean {
        /**
         * id : 14461
         * linesid : 14363
         * guid :
         * linesguid : null
         * feature : 自理
         * feenotincluded : 自理
         * feeincluded : 自理
         * owncharge : 自理
         * notice : 自理
         * tip : 自理
         * shopping : 自理
         * activityaddress : 自理
         * featureeat : 自理
         * remarks : 自理
         * createuserid : null
         * createtime : 2017-03-31 15:09:12
         * timestamp : 2017-03-31 15:09:12
         * islogicdelete : 1
         */

        private String id;
        private String linesid;
        private String guid;
        private Object linesguid;
        private String feature;
        private String feenotincluded;
        private String feeincluded;
        private String owncharge;
        private String notice;
        private String tip;
        private String shopping;
        private String activityaddress;
        private String featureeat;
        private String remarks;
        private Object createuserid;
        private String createtime;
        private String timestamp;
        private String islogicdelete;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLinesid() {
            return linesid;
        }

        public void setLinesid(String linesid) {
            this.linesid = linesid;
        }

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public Object getLinesguid() {
            return linesguid;
        }

        public void setLinesguid(Object linesguid) {
            this.linesguid = linesguid;
        }

        public String getFeature() {
            return feature;
        }

        public void setFeature(String feature) {
            this.feature = feature;
        }

        public String getFeenotincluded() {
            return feenotincluded;
        }

        public void setFeenotincluded(String feenotincluded) {
            this.feenotincluded = feenotincluded;
        }

        public String getFeeincluded() {
            return feeincluded;
        }

        public void setFeeincluded(String feeincluded) {
            this.feeincluded = feeincluded;
        }

        public String getOwncharge() {
            return owncharge;
        }

        public void setOwncharge(String owncharge) {
            this.owncharge = owncharge;
        }

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }

        public String getShopping() {
            return shopping;
        }

        public void setShopping(String shopping) {
            this.shopping = shopping;
        }

        public String getActivityaddress() {
            return activityaddress;
        }

        public void setActivityaddress(String activityaddress) {
            this.activityaddress = activityaddress;
        }

        public String getFeatureeat() {
            return featureeat;
        }

        public void setFeatureeat(String featureeat) {
            this.featureeat = featureeat;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public Object getCreateuserid() {
            return createuserid;
        }

        public void setCreateuserid(Object createuserid) {
            this.createuserid = createuserid;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getIslogicdelete() {
            return islogicdelete;
        }

        public void setIslogicdelete(String islogicdelete) {
            this.islogicdelete = islogicdelete;
        }
    }

    public static class DataServiceBean {
        /**
         * id : 9315
         * linesid : 14363
         * guid :
         * linesguid : null
         * service_attrid : null
         * service_levelattrname : 标准等级
         * service_eatattrname : 测试标准
         * service_liveattrname :
         * service_gotrafficattrname : 飞机
         * service_backtrafficattrname : 飞机
         * service_sighttrafficattrname : 飞机
         * service_insuranceattrname : 5元/人/天
         * service_sightattrname : 含行程内非自费景点第一大门票
         * service_guideattrname : 全程导游服务
         * islogicdelete : 1
         * createtime : 2017-03-31 15:09:11
         * createuserid : 1
         * timestamp : 2017-03-31 15:09:12
         */

        private String id;
        private String linesid;
        private String guid;
        private Object linesguid;
        private Object service_attrid;
        private String service_levelattrname;
        private String service_eatattrname;
        private String service_liveattrname;
        private String service_gotrafficattrname;
        private String service_backtrafficattrname;
        private String service_sighttrafficattrname;
        private String service_insuranceattrname;
        private String service_sightattrname;
        private String service_guideattrname;
        private String islogicdelete;
        private String createtime;
        private String createuserid;
        private String timestamp;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLinesid() {
            return linesid;
        }

        public void setLinesid(String linesid) {
            this.linesid = linesid;
        }

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public Object getLinesguid() {
            return linesguid;
        }

        public void setLinesguid(Object linesguid) {
            this.linesguid = linesguid;
        }

        public Object getService_attrid() {
            return service_attrid;
        }

        public void setService_attrid(Object service_attrid) {
            this.service_attrid = service_attrid;
        }

        public String getService_levelattrname() {
            return service_levelattrname;
        }

        public void setService_levelattrname(String service_levelattrname) {
            this.service_levelattrname = service_levelattrname;
        }

        public String getService_eatattrname() {
            return service_eatattrname;
        }

        public void setService_eatattrname(String service_eatattrname) {
            this.service_eatattrname = service_eatattrname;
        }

        public String getService_liveattrname() {
            return service_liveattrname;
        }

        public void setService_liveattrname(String service_liveattrname) {
            this.service_liveattrname = service_liveattrname;
        }

        public String getService_gotrafficattrname() {
            return service_gotrafficattrname;
        }

        public void setService_gotrafficattrname(String service_gotrafficattrname) {
            this.service_gotrafficattrname = service_gotrafficattrname;
        }

        public String getService_backtrafficattrname() {
            return service_backtrafficattrname;
        }

        public void setService_backtrafficattrname(String service_backtrafficattrname) {
            this.service_backtrafficattrname = service_backtrafficattrname;
        }

        public String getService_sighttrafficattrname() {
            return service_sighttrafficattrname;
        }

        public void setService_sighttrafficattrname(String service_sighttrafficattrname) {
            this.service_sighttrafficattrname = service_sighttrafficattrname;
        }

        public String getService_insuranceattrname() {
            return service_insuranceattrname;
        }

        public void setService_insuranceattrname(String service_insuranceattrname) {
            this.service_insuranceattrname = service_insuranceattrname;
        }

        public String getService_sightattrname() {
            return service_sightattrname;
        }

        public void setService_sightattrname(String service_sightattrname) {
            this.service_sightattrname = service_sightattrname;
        }

        public String getService_guideattrname() {
            return service_guideattrname;
        }

        public void setService_guideattrname(String service_guideattrname) {
            this.service_guideattrname = service_guideattrname;
        }

        public String getIslogicdelete() {
            return islogicdelete;
        }

        public void setIslogicdelete(String islogicdelete) {
            this.islogicdelete = islogicdelete;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getCreateuserid() {
            return createuserid;
        }

        public void setCreateuserid(String createuserid) {
            this.createuserid = createuserid;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }

    public static class DataTeamBean {
        /**
         * id : 209
         * lines_id : 14363
         * lines_team_date : 2017-04-29 00:00:00
         * lines_team_price : 0.01
         * create_date_time : 2017-03-31 15:09:13
         * create_user_id : 1
         * create_user_name : admin
         */

        private String id;
        private String lines_id;
        private String lines_team_date;
        private String lines_team_price;
        private String create_date_time;
        private String create_user_id;
        private String create_user_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLines_id() {
            return lines_id;
        }

        public void setLines_id(String lines_id) {
            this.lines_id = lines_id;
        }

        public String getLines_team_date() {
            return lines_team_date;
        }

        public void setLines_team_date(String lines_team_date) {
            this.lines_team_date = lines_team_date;
        }

        public String getLines_team_price() {
            return lines_team_price;
        }

        public void setLines_team_price(String lines_team_price) {
            this.lines_team_price = lines_team_price;
        }

        public String getCreate_date_time() {
            return create_date_time;
        }

        public void setCreate_date_time(String create_date_time) {
            this.create_date_time = create_date_time;
        }

        public String getCreate_user_id() {
            return create_user_id;
        }

        public void setCreate_user_id(String create_user_id) {
            this.create_user_id = create_user_id;
        }

        public String getCreate_user_name() {
            return create_user_name;
        }

        public void setCreate_user_name(String create_user_name) {
            this.create_user_name = create_user_name;
        }
    }

    public static class DataJourneyBean {
        /**
         * id : 77661
         * linesid : 14363
         * guid :
         * linesguid : null
         * journey_day : 1
         * journey_details : 自理
         * journey_basic_info : 测试
         * journey_break_fast : 酒店内
         * journey_lunch : 酒店内
         * journey_dinner : 酒店内
         * journey_lodging : 自理
         * journey_day_sight : 自理
         * journey_day_shopping : 自理
         * journey_day_sightself : 自理
         * journey_day_activity : 自理
         * createuserid : 1
         * createtime : 2017-03-31 15:09:11
         * timestamp : 0000-00-00 00:00:00
         * islogicdelete : 1
         * journey_details_listall : []
         */

        private String id;
        private String linesid;
        private String guid;
        private Object linesguid;
        private String journey_day;
        private String journey_details;
        private String journey_basic_info;
        private String journey_break_fast;
        private String journey_lunch;
        private String journey_dinner;
        private String journey_lodging;
        private String journey_day_sight;
        private String journey_day_shopping;
        private String journey_day_sightself;
        private String journey_day_activity;
        private String createuserid;
        private String createtime;
        private String timestamp;
        private String islogicdelete;
        private List<?> journey_details_listall;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLinesid() {
            return linesid;
        }

        public void setLinesid(String linesid) {
            this.linesid = linesid;
        }

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public Object getLinesguid() {
            return linesguid;
        }

        public void setLinesguid(Object linesguid) {
            this.linesguid = linesguid;
        }

        public String getJourney_day() {
            return journey_day;
        }

        public void setJourney_day(String journey_day) {
            this.journey_day = journey_day;
        }

        public String getJourney_details() {
            return journey_details;
        }

        public void setJourney_details(String journey_details) {
            this.journey_details = journey_details;
        }

        public String getJourney_basic_info() {
            return journey_basic_info;
        }

        public void setJourney_basic_info(String journey_basic_info) {
            this.journey_basic_info = journey_basic_info;
        }

        public String getJourney_break_fast() {
            return journey_break_fast;
        }

        public void setJourney_break_fast(String journey_break_fast) {
            this.journey_break_fast = journey_break_fast;
        }

        public String getJourney_lunch() {
            return journey_lunch;
        }

        public void setJourney_lunch(String journey_lunch) {
            this.journey_lunch = journey_lunch;
        }

        public String getJourney_dinner() {
            return journey_dinner;
        }

        public void setJourney_dinner(String journey_dinner) {
            this.journey_dinner = journey_dinner;
        }

        public String getJourney_lodging() {
            return journey_lodging;
        }

        public void setJourney_lodging(String journey_lodging) {
            this.journey_lodging = journey_lodging;
        }

        public String getJourney_day_sight() {
            return journey_day_sight;
        }

        public void setJourney_day_sight(String journey_day_sight) {
            this.journey_day_sight = journey_day_sight;
        }

        public String getJourney_day_shopping() {
            return journey_day_shopping;
        }

        public void setJourney_day_shopping(String journey_day_shopping) {
            this.journey_day_shopping = journey_day_shopping;
        }

        public String getJourney_day_sightself() {
            return journey_day_sightself;
        }

        public void setJourney_day_sightself(String journey_day_sightself) {
            this.journey_day_sightself = journey_day_sightself;
        }

        public String getJourney_day_activity() {
            return journey_day_activity;
        }

        public void setJourney_day_activity(String journey_day_activity) {
            this.journey_day_activity = journey_day_activity;
        }

        public String getCreateuserid() {
            return createuserid;
        }

        public void setCreateuserid(String createuserid) {
            this.createuserid = createuserid;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getIslogicdelete() {
            return islogicdelete;
        }

        public void setIslogicdelete(String islogicdelete) {
            this.islogicdelete = islogicdelete;
        }

        public List<?> getJourney_details_listall() {
            return journey_details_listall;
        }

        public void setJourney_details_listall(List<?> journey_details_listall) {
            this.journey_details_listall = journey_details_listall;
        }
    }

    public static class DataShowBean {
        /**
         * id : 33354
         * typeid : 104
         * title : sad
         * content :
         * pic_data : [{"link_title":"dsff","link_sort":"0","city_id":"0","cover":"0","img_name":"dsff","img_onlineurl":"http://img.4008289828.com/images/0/34/208/0cd7b5804bc8f315fe70005fed43f115.jpg","img_url":"http://img.4008289828.com/images/0/34/208/0cd7b5804bc8f315fe70005fed43f115.jpg"},{"link_title":"Barstow Tanger Outlets","link_sort":"0","city_id":"0","cover":"0","img_name":"Barstow Tanger Outlets","img_onlineurl":"http://img1.uu1.com/images/0/14/11647/1b56c6d74a8938f7c6765e23ac41639e.jpg","img_url":"http://img1.uu1.com/images/0/14/11647/1b56c6d74a8938f7c6765e23ac41639e.jpg"}]
         */

        private String id;
        private String typeid;
        private String title;
        private String content;
        private List<PicDataBean> pic_data;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<PicDataBean> getPic_data() {
            return pic_data;
        }

        public void setPic_data(List<PicDataBean> pic_data) {
            this.pic_data = pic_data;
        }

        public static class PicDataBean {
            /**
             * link_title : dsff
             * link_sort : 0
             * city_id : 0
             * cover : 0
             * img_name : dsff
             * img_onlineurl : http://img.4008289828.com/images/0/34/208/0cd7b5804bc8f315fe70005fed43f115.jpg
             * img_url : http://img.4008289828.com/images/0/34/208/0cd7b5804bc8f315fe70005fed43f115.jpg
             */

            private String link_title;
            private String link_sort;
            private String city_id;
            private String cover;
            private String img_name;
            private String img_onlineurl;
            private String img_url;

            public String getLink_title() {
                return link_title;
            }

            public void setLink_title(String link_title) {
                this.link_title = link_title;
            }

            public String getLink_sort() {
                return link_sort;
            }

            public void setLink_sort(String link_sort) {
                this.link_sort = link_sort;
            }

            public String getCity_id() {
                return city_id;
            }

            public void setCity_id(String city_id) {
                this.city_id = city_id;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getImg_name() {
                return img_name;
            }

            public void setImg_name(String img_name) {
                this.img_name = img_name;
            }

            public String getImg_onlineurl() {
                return img_onlineurl;
            }

            public void setImg_onlineurl(String img_onlineurl) {
                this.img_onlineurl = img_onlineurl;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }
        }
    }

    public static class DataImgBean {
        /**
         * link_title : dsff
         * link_sort : 0
         * city_id : 0
         * cover : 0
         * img_name : dsff
         * img_onlineurl : http://img.4008289828.com/images/0/34/208/0cd7b5804bc8f315fe70005fed43f115.jpg
         * img_url : http://img.4008289828.com/images/0/34/208/0cd7b5804bc8f315fe70005fed43f115.jpg
         */

        private String link_title;
        private String link_sort;
        private String city_id;
        private String cover;
        private String img_name;
        private String img_onlineurl;
        private String img_url;

        public String getLink_title() {
            return link_title;
        }

        public void setLink_title(String link_title) {
            this.link_title = link_title;
        }

        public String getLink_sort() {
            return link_sort;
        }

        public void setLink_sort(String link_sort) {
            this.link_sort = link_sort;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getImg_name() {
            return img_name;
        }

        public void setImg_name(String img_name) {
            this.img_name = img_name;
        }

        public String getImg_onlineurl() {
            return img_onlineurl;
        }

        public void setImg_onlineurl(String img_onlineurl) {
            this.img_onlineurl = img_onlineurl;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }
    }
}