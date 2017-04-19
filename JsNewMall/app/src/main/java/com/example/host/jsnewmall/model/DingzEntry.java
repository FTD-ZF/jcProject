package com.example.host.jsnewmall.model;

import java.util.List;

/**
 * Created by host on 2017/3/30.
 */

public class DingzEntry {

    /**
     * res : 1
     * msg : \u67e5\u8be2\u6210\u529f
     * list : {"banner":[{"id":"1117","ads_id":"205","title":"1","status":"1","date_start":"2017-03-21 17:30:34","date_end":"2017-10-11 17:30:34","sort":"1","link_type":"0","link":"","city_id":null,"line_id":null,"liner_id":null,"ad_type":"1","data_id":null,"market_prices":"0.00","discount":"","source":"1","is_ad_label":"0","img":"http:\\/\\/img.4008289828.com\\/images\\/0\\/1\\/1117\\/731a03723af8eafba0dc1d6e234cdbf2.png","line_data":null,"liner_data":null,"ticket_data":null}],"top_nav":[{"id":"1118","ads_id":"206","title":"1","status":"1","date_start":"2017-03-14 17:33:51","date_end":"2017-07-13 17:33:51","sort":"1","link_type":"0","link":"","city_id":null,"line_id":null,"liner_id":null,"ad_type":"1","data_id":null,"market_prices":"0.00","discount":"","source":"1","is_ad_label":"0","img":"http:\\/\\/img.4008289828.com\\/images\\/0\\/1\\/1118\\/4dd3459cb84666012deae1b85f2d3eb6.png","line_data":null,"liner_data":null,"ticket_data":null},{"id":"1120","ads_id":"206","title":"3","status":"1","date_start":"2017-03-29 17:34:36","date_end":"2017-08-17 17:34:36","sort":"1","link_type":"0","link":"","city_id":null,"line_id":null,"liner_id":null,"ad_type":"1","data_id":null,"market_prices":"0.00","discount":"","source":"1","is_ad_label":"0","img":"http:\\/\\/img.4008289828.com\\/images\\/0\\/1\\/1120\\/f732b0611d159a6274fbee005736f207.png","line_data":null,"liner_data":null,"ticket_data":null},{"id":"1119","ads_id":"206","title":"2","status":"1","date_start":"2017-03-29 17:34:16","date_end":"2017-07-13 17:34:16","sort":"2","link_type":"0","link":"","city_id":null,"line_id":null,"liner_id":null,"ad_type":"1","data_id":null,"market_prices":"0.00","discount":"","source":"1","is_ad_label":"0","img":"http:\\/\\/img.4008289828.com\\/images\\/0\\/1\\/1119\\/b2d9efa41868f2729b91fd7c0dac32ca.png","line_data":null,"liner_data":null,"ticket_data":null}],"lindes_list":[{"id":"1121","ads_id":"207","title":"1","status":"1","date_start":"2017-03-15 17:46:53","date_end":"2017-06-30 17:46:53","sort":"1","link_type":"0","link":"","city_id":null,"line_id":null,"liner_id":null,"ad_type":"1","data_id":null,"market_prices":"0.00","discount":"","source":"1","is_ad_label":"0","img":"http:\\/\\/img.4008289828.com\\/images\\/0\\/1\\/1121\\/6f838c3af7285db29a53e0d8bdc65106.jpg","line_data":null,"liner_data":null,"ticket_data":null},{"id":"1122","ads_id":"207","title":"2","status":"1","date_start":"2017-03-14 17:47:29","date_end":"2017-05-17 17:47:29","sort":"1","link_type":"0","link":"","city_id":null,"line_id":null,"liner_id":null,"ad_type":"1","data_id":null,"market_prices":"0.00","discount":"","source":"0","is_ad_label":"0","img":"http:\\/\\/img.4008289828.com\\/images\\/0\\/1\\/1122\\/2b2e55a198a2061c6902a5be57e8b539.jpg","line_data":null,"liner_data":null,"ticket_data":null}]}
     */

    private int res;
    private String msg;
    private ListBean list;

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

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }

    public static class ListBean {
        private List<BannerBean> banner;
        private List<TopNavBean> top_nav;
        private List<LindesListBean> lindes_list;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<TopNavBean> getTop_nav() {
            return top_nav;
        }

        public void setTop_nav(List<TopNavBean> top_nav) {
            this.top_nav = top_nav;
        }

        public List<LindesListBean> getLindes_list() {
            return lindes_list;
        }

        public void setLindes_list(List<LindesListBean> lindes_list) {
            this.lindes_list = lindes_list;
        }

        public static class BannerBean {
            /**
             * id : 1117
             * ads_id : 205
             * title : 1
             * status : 1
             * date_start : 2017-03-21 17:30:34
             * date_end : 2017-10-11 17:30:34
             * sort : 1
             * link_type : 0
             * link :
             * city_id : null
             * line_id : null
             * liner_id : null
             * ad_type : 1
             * data_id : null
             * market_prices : 0.00
             * discount :
             * source : 1
             * is_ad_label : 0
             * img : http:\/\/img.4008289828.com\/images\/0\/1\/1117\/731a03723af8eafba0dc1d6e234cdbf2.png
             * line_data : null
             * liner_data : null
             * ticket_data : null
             */

            private String id;
            private String ads_id;
            private String title;
            private String status;
            private String date_start;
            private String date_end;
            private String sort;
            private String link_type;
            private String link;
            private Object city_id;
            private Object line_id;
            private Object liner_id;
            private String ad_type;
            private Object data_id;
            private String market_prices;
            private String discount;
            private String source;
            private String is_ad_label;
            private String img;
            private Object line_data;
            private Object liner_data;
            private Object ticket_data;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAds_id() {
                return ads_id;
            }

            public void setAds_id(String ads_id) {
                this.ads_id = ads_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getDate_start() {
                return date_start;
            }

            public void setDate_start(String date_start) {
                this.date_start = date_start;
            }

            public String getDate_end() {
                return date_end;
            }

            public void setDate_end(String date_end) {
                this.date_end = date_end;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getLink_type() {
                return link_type;
            }

            public void setLink_type(String link_type) {
                this.link_type = link_type;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public Object getCity_id() {
                return city_id;
            }

            public void setCity_id(Object city_id) {
                this.city_id = city_id;
            }

            public Object getLine_id() {
                return line_id;
            }

            public void setLine_id(Object line_id) {
                this.line_id = line_id;
            }

            public Object getLiner_id() {
                return liner_id;
            }

            public void setLiner_id(Object liner_id) {
                this.liner_id = liner_id;
            }

            public String getAd_type() {
                return ad_type;
            }

            public void setAd_type(String ad_type) {
                this.ad_type = ad_type;
            }

            public Object getData_id() {
                return data_id;
            }

            public void setData_id(Object data_id) {
                this.data_id = data_id;
            }

            public String getMarket_prices() {
                return market_prices;
            }

            public void setMarket_prices(String market_prices) {
                this.market_prices = market_prices;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getIs_ad_label() {
                return is_ad_label;
            }

            public void setIs_ad_label(String is_ad_label) {
                this.is_ad_label = is_ad_label;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public Object getLine_data() {
                return line_data;
            }

            public void setLine_data(Object line_data) {
                this.line_data = line_data;
            }

            public Object getLiner_data() {
                return liner_data;
            }

            public void setLiner_data(Object liner_data) {
                this.liner_data = liner_data;
            }

            public Object getTicket_data() {
                return ticket_data;
            }

            public void setTicket_data(Object ticket_data) {
                this.ticket_data = ticket_data;
            }
        }

        public static class TopNavBean {
            /**
             * id : 1118
             * ads_id : 206
             * title : 1
             * status : 1
             * date_start : 2017-03-14 17:33:51
             * date_end : 2017-07-13 17:33:51
             * sort : 1
             * link_type : 0
             * link :
             * city_id : null
             * line_id : null
             * liner_id : null
             * ad_type : 1
             * data_id : null
             * market_prices : 0.00
             * discount :
             * source : 1
             * is_ad_label : 0
             * img : http:\/\/img.4008289828.com\/images\/0\/1\/1118\/4dd3459cb84666012deae1b85f2d3eb6.png
             * line_data : null
             * liner_data : null
             * ticket_data : null
             */

            private String id;
            private String ads_id;
            private String title;
            private String status;
            private String date_start;
            private String date_end;
            private String sort;
            private String link_type;
            private String link;
            private Object city_id;
            private Object line_id;
            private Object liner_id;
            private String ad_type;
            private Object data_id;
            private String market_prices;
            private String discount;
            private String source;
            private String is_ad_label;
            private String img;
            private Object line_data;
            private Object liner_data;
            private Object ticket_data;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAds_id() {
                return ads_id;
            }

            public void setAds_id(String ads_id) {
                this.ads_id = ads_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getDate_start() {
                return date_start;
            }

            public void setDate_start(String date_start) {
                this.date_start = date_start;
            }

            public String getDate_end() {
                return date_end;
            }

            public void setDate_end(String date_end) {
                this.date_end = date_end;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getLink_type() {
                return link_type;
            }

            public void setLink_type(String link_type) {
                this.link_type = link_type;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public Object getCity_id() {
                return city_id;
            }

            public void setCity_id(Object city_id) {
                this.city_id = city_id;
            }

            public Object getLine_id() {
                return line_id;
            }

            public void setLine_id(Object line_id) {
                this.line_id = line_id;
            }

            public Object getLiner_id() {
                return liner_id;
            }

            public void setLiner_id(Object liner_id) {
                this.liner_id = liner_id;
            }

            public String getAd_type() {
                return ad_type;
            }

            public void setAd_type(String ad_type) {
                this.ad_type = ad_type;
            }

            public Object getData_id() {
                return data_id;
            }

            public void setData_id(Object data_id) {
                this.data_id = data_id;
            }

            public String getMarket_prices() {
                return market_prices;
            }

            public void setMarket_prices(String market_prices) {
                this.market_prices = market_prices;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getIs_ad_label() {
                return is_ad_label;
            }

            public void setIs_ad_label(String is_ad_label) {
                this.is_ad_label = is_ad_label;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public Object getLine_data() {
                return line_data;
            }

            public void setLine_data(Object line_data) {
                this.line_data = line_data;
            }

            public Object getLiner_data() {
                return liner_data;
            }

            public void setLiner_data(Object liner_data) {
                this.liner_data = liner_data;
            }

            public Object getTicket_data() {
                return ticket_data;
            }

            public void setTicket_data(Object ticket_data) {
                this.ticket_data = ticket_data;
            }
        }

        public static class LindesListBean {
            /**
             * id : 1121
             * ads_id : 207
             * title : 1
             * status : 1
             * date_start : 2017-03-15 17:46:53
             * date_end : 2017-06-30 17:46:53
             * sort : 1
             * link_type : 0
             * link :
             * city_id : null
             * line_id : null
             * liner_id : null
             * ad_type : 1
             * data_id : null
             * market_prices : 0.00
             * discount :
             * source : 1
             * is_ad_label : 0
             * img : http:\/\/img.4008289828.com\/images\/0\/1\/1121\/6f838c3af7285db29a53e0d8bdc65106.jpg
             * line_data : null
             * liner_data : null
             * ticket_data : null
             */

            private String id;
            private String ads_id;
            private String title;
            private String status;
            private String date_start;
            private String date_end;
            private String sort;
            private String link_type;
            private String link;
            private Object city_id;
            private Object line_id;
            private Object liner_id;
            private String ad_type;
            private Object data_id;
            private String market_prices;
            private String discount;
            private String source;
            private String is_ad_label;
            private String img;
            private Object line_data;
            private Object liner_data;
            private Object ticket_data;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAds_id() {
                return ads_id;
            }

            public void setAds_id(String ads_id) {
                this.ads_id = ads_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getDate_start() {
                return date_start;
            }

            public void setDate_start(String date_start) {
                this.date_start = date_start;
            }

            public String getDate_end() {
                return date_end;
            }

            public void setDate_end(String date_end) {
                this.date_end = date_end;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getLink_type() {
                return link_type;
            }

            public void setLink_type(String link_type) {
                this.link_type = link_type;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public Object getCity_id() {
                return city_id;
            }

            public void setCity_id(Object city_id) {
                this.city_id = city_id;
            }

            public Object getLine_id() {
                return line_id;
            }

            public void setLine_id(Object line_id) {
                this.line_id = line_id;
            }

            public Object getLiner_id() {
                return liner_id;
            }

            public void setLiner_id(Object liner_id) {
                this.liner_id = liner_id;
            }

            public String getAd_type() {
                return ad_type;
            }

            public void setAd_type(String ad_type) {
                this.ad_type = ad_type;
            }

            public Object getData_id() {
                return data_id;
            }

            public void setData_id(Object data_id) {
                this.data_id = data_id;
            }

            public String getMarket_prices() {
                return market_prices;
            }

            public void setMarket_prices(String market_prices) {
                this.market_prices = market_prices;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getIs_ad_label() {
                return is_ad_label;
            }

            public void setIs_ad_label(String is_ad_label) {
                this.is_ad_label = is_ad_label;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public Object getLine_data() {
                return line_data;
            }

            public void setLine_data(Object line_data) {
                this.line_data = line_data;
            }

            public Object getLiner_data() {
                return liner_data;
            }

            public void setLiner_data(Object liner_data) {
                this.liner_data = liner_data;
            }

            public Object getTicket_data() {
                return ticket_data;
            }

            public void setTicket_data(Object ticket_data) {
                this.ticket_data = ticket_data;
            }
        }
    }
}
