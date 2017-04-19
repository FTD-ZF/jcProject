package com.example.host.jsnewmall.model;

import java.util.List;

/**
 * Created by host on 2017/3/30.
 */

public class FreeTravelEntry {

    /**
     * res : 1
     * msg : \u67e5\u8be2\u6210\u529f
     * list : {"banner":[{"id":"1104","ads_id":"210","title":"123","status":"1","date_start":"2017-03-20 10:36:02","date_end":"2017-04-08 10:35:02","sort":"1","link_type":"0","link":"","city_id":null,"line_id":null,"liner_id":null,"ad_type":"1","data_id":null,"market_prices":"0.00","discount":"","source":"1","is_ad_label":"0","img":"http:\\/\\/img.4008289828.com\\/images\\/0\\/1\\/1104\\/1e7fbdf54f7b5fbbd55ff0aba5df759e.png","line_data":null,"liner_data":null,"ticket_data":null}],"lindes_list":[{"id":"1110","ads_id":"211","title":"1","status":"1","date_start":"2017-02-16 16:24:37","date_end":"2017-08-11 16:24:37","sort":"1","link_type":"0","link":"","city_id":null,"line_id":"13510","liner_id":null,"ad_type":"2","data_id":null,"market_prices":"0.00","discount":"","source":"1","is_ad_label":"0","img":"","line_data":{"id":"13510","image":"http:\\/\\/img1.uu1.com\\/images\\/0\\/14\\/7472\\/8951732c65ddfdfd639443f6d621cdc5.jpg","title":"\\u81ea\\u7ec4\\u56e2\\uff0c\\u8d60\\u9001\\u63a5\\u9001\\uff0c\\u4e0d\\u542b\\u7b7e\\u8bc1","lines_num":"CL201701241015","name":"\\u3010\\u6e05\\u65b0\\u6cf0\\u56fd\\u3011\\u6cf0\\u56fd\\u66fc\\u8c37\\u82ad\\u63d0\\u96c5\\u4e94\\u665a\\u516d\\u65e5\\u6e38 \\uff08\\u5e38\\u5dde\\u5f80\\u8fd4\\uff0c\\u81ea\\u7ec4\\u56e2\\uff09","price":"3099.00","rangs":null,"date":null,"type":"lines","can_sale":0,"lines_days":"6","lines_daysnight":"5","travelmode":"\\u5176\\u4ed6","go_place":"\\u5e38\\u5dde","back_place":null},"liner_data":null,"ticket_data":null},{"id":"1111","ads_id":"211","title":"23","status":"1","date_start":"2017-03-29 16:25:11","date_end":"2017-07-05 16:25:11","sort":"1","link_type":"0","link":"","city_id":null,"line_id":"13511","liner_id":null,"ad_type":"2","data_id":null,"market_prices":"0.00","discount":"","source":"0","is_ad_label":"0","img":"","line_data":{"id":"13511","image":"http:\\/\\/img1.uu1.com\\/images\\/0\\/14\\/14936\\/930849cb4655e223f92dc091c0e8b4ec.jpg","title":null,"lines_num":"CL201701241016","name":"\\u80af\\u5c3c\\u4e9a\\uff08\\u4e09\\u5927\\u56fd\\u5bb6\\u516c\\u56ed+\\u4e24\\u5927\\u6e56\\u6cca+\\u80af\\u5c3c\\u4e9a\\u5c71\\u8d39\\u5c14\\u8499\\u72e9\\u730e\\u5ea6\\u5047\\u6751\\uff0912\\u5929\\u6df1\\u5ea6\\u6444\\u5f71\\u4e4b\\u65c5","price":"36999.00","rangs":null,"date":null,"type":"lines","can_sale":0,"lines_days":"12","lines_daysnight":"11","travelmode":"\\u5176\\u4ed6","go_place":"\\u4e0a\\u6d77","back_place":null},"liner_data":null,"ticket_data":null}]}
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
        private List<LindesListBean> lindes_list;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<LindesListBean> getLindes_list() {
            return lindes_list;
        }

        public void setLindes_list(List<LindesListBean> lindes_list) {
            this.lindes_list = lindes_list;
        }

        public static class BannerBean {
            /**
             * id : 1104
             * ads_id : 210
             * title : 123
             * status : 1
             * date_start : 2017-03-20 10:36:02
             * date_end : 2017-04-08 10:35:02
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
             * img : http:\/\/img.4008289828.com\/images\/0\/1\/1104\/1e7fbdf54f7b5fbbd55ff0aba5df759e.png
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
             * id : 1110
             * ads_id : 211
             * title : 1
             * status : 1
             * date_start : 2017-02-16 16:24:37
             * date_end : 2017-08-11 16:24:37
             * sort : 1
             * link_type : 0
             * link :
             * city_id : null
             * line_id : 13510
             * liner_id : null
             * ad_type : 2
             * data_id : null
             * market_prices : 0.00
             * discount :
             * source : 1
             * is_ad_label : 0
             * img :
             * line_data : {"id":"13510","image":"http:\\/\\/img1.uu1.com\\/images\\/0\\/14\\/7472\\/8951732c65ddfdfd639443f6d621cdc5.jpg","title":"\\u81ea\\u7ec4\\u56e2\\uff0c\\u8d60\\u9001\\u63a5\\u9001\\uff0c\\u4e0d\\u542b\\u7b7e\\u8bc1","lines_num":"CL201701241015","name":"\\u3010\\u6e05\\u65b0\\u6cf0\\u56fd\\u3011\\u6cf0\\u56fd\\u66fc\\u8c37\\u82ad\\u63d0\\u96c5\\u4e94\\u665a\\u516d\\u65e5\\u6e38 \\uff08\\u5e38\\u5dde\\u5f80\\u8fd4\\uff0c\\u81ea\\u7ec4\\u56e2\\uff09","price":"3099.00","rangs":null,"date":null,"type":"lines","can_sale":0,"lines_days":"6","lines_daysnight":"5","travelmode":"\\u5176\\u4ed6","go_place":"\\u5e38\\u5dde","back_place":null}
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
            private String line_id;
            private Object liner_id;
            private String ad_type;
            private Object data_id;
            private String market_prices;
            private String discount;
            private String source;
            private String is_ad_label;
            private String img;
            private LineDataBean line_data;
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

            public String getLine_id() {
                return line_id;
            }

            public void setLine_id(String line_id) {
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

            public LineDataBean getLine_data() {
                return line_data;
            }

            public void setLine_data(LineDataBean line_data) {
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

            public static class LineDataBean {
                /**
                 * id : 13510
                 * image : http:\/\/img1.uu1.com\/images\/0\/14\/7472\/8951732c65ddfdfd639443f6d621cdc5.jpg
                 * title : \u81ea\u7ec4\u56e2\uff0c\u8d60\u9001\u63a5\u9001\uff0c\u4e0d\u542b\u7b7e\u8bc1
                 * lines_num : CL201701241015
                 * name : \u3010\u6e05\u65b0\u6cf0\u56fd\u3011\u6cf0\u56fd\u66fc\u8c37\u82ad\u63d0\u96c5\u4e94\u665a\u516d\u65e5\u6e38 \uff08\u5e38\u5dde\u5f80\u8fd4\uff0c\u81ea\u7ec4\u56e2\uff09
                 * price : 3099.00
                 * rangs : null
                 * date : null
                 * type : lines
                 * can_sale : 0
                 * lines_days : 6
                 * lines_daysnight : 5
                 * travelmode : \u5176\u4ed6
                 * go_place : \u5e38\u5dde
                 * back_place : null
                 */

                private String id;
                private String image;
                private String title;
                private String lines_num;
                private String name;
                private String price;
                private Object rangs;
                private Object date;
                private String type;
                private int can_sale;
                private String lines_days;
                private String lines_daysnight;
                private String travelmode;
                private String go_place;
                private Object back_place;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getLines_num() {
                    return lines_num;
                }

                public void setLines_num(String lines_num) {
                    this.lines_num = lines_num;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public Object getRangs() {
                    return rangs;
                }

                public void setRangs(Object rangs) {
                    this.rangs = rangs;
                }

                public Object getDate() {
                    return date;
                }

                public void setDate(Object date) {
                    this.date = date;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public int getCan_sale() {
                    return can_sale;
                }

                public void setCan_sale(int can_sale) {
                    this.can_sale = can_sale;
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

                public String getTravelmode() {
                    return travelmode;
                }

                public void setTravelmode(String travelmode) {
                    this.travelmode = travelmode;
                }

                public String getGo_place() {
                    return go_place;
                }

                public void setGo_place(String go_place) {
                    this.go_place = go_place;
                }

                public Object getBack_place() {
                    return back_place;
                }

                public void setBack_place(Object back_place) {
                    this.back_place = back_place;
                }
            }
        }
    }
}
