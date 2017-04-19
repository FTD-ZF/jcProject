package com.example.host.jsnewmall.model;

import java.util.List;

/**
 * Created by host on 2017/4/18.
 */

public class CenterCommentEntry {


    /**
     * res : 1
     * msg : \u67e5\u8be2\u6210\u529f
     * count : 6
     * CommentList : [{"order_code":"w170417170221741311","id":"89","uid":"24025","oid":"3094","comment":"\\u7ebf\\u8def\\u5b89\\u6392\\u5408\\u7406\\uff0c\\u666f\\u8272\\u5b9c\\u4eba\\uff0c\\u5bfc\\u6e38\\u670d\\u52a1\\u4e5f\\u4e0d\\u9519","star":"4","travel_type":"4","addtime":"2017-04-18 10:11:06","is_hide":"1","hide_reason":"","mod_uid":"0","mod_name":"","is_reply":"2","is_essence":"0","realname":"haha58","pic_data":[{"img_id":"1314","img_url":"http:\\/\\/img1.uu1.com\\/images\\/112\\/11\\/1160\\/d64c57ea69b6b3e476a3ee8be092a91d.jpg"},{"img_id":"1313","img_url":"http:\\/\\/img1.uu1.com\\/images\\/112\\/11\\/1160\\/06d2bcbb56c87665409f19000b02fcf9.jpg"},{"img_id":"1307","img_url":"http:\\/\\/img1.uu1.com\\/images\\/113\\/11\\/1133\\/fbd9f066eccdbe7461e4f81b5c3a4179.jpg"}],"line":{"day":"12\\u65e510\\u665a","lines_id":"12909","lines_name":"\\u6fb3\\u65b0\\u51ef\\u58a812\\u5929\\u5b8c\\u7f8e\\u4e4b\\u65c5  \\u914d\\u5957\\u822a\\u73ed\\uff1aHX\\u8f6c\\u673a","lines_img":null},"answer":[{"id":"7","com_id":"89","uid":"24025","aid":"1","answer_name":"admin","content":"ddddddddddddddd","addtime":"2017-04-18 10:13:32","status":"1","is_hide":"1","hide_reason":"","mod_uid":"0","mod_name":""}],"order_total_money":"17590.00"}]
     */

    private int res;
    private String msg;
    private int count;
    private List<CommentListBean> CommentList;

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

    public List<CommentListBean> getCommentList() {
        return CommentList;
    }

    public void setCommentList(List<CommentListBean> CommentList) {
        this.CommentList = CommentList;
    }

    public static class CommentListBean {
        /**
         * order_code : w170417170221741311
         * id : 89
         * uid : 24025
         * oid : 3094
         * comment : \u7ebf\u8def\u5b89\u6392\u5408\u7406\uff0c\u666f\u8272\u5b9c\u4eba\uff0c\u5bfc\u6e38\u670d\u52a1\u4e5f\u4e0d\u9519
         * star : 4
         * travel_type : 4
         * addtime : 2017-04-18 10:11:06
         * is_hide : 1
         * hide_reason :
         * mod_uid : 0
         * mod_name :
         * is_reply : 2
         * is_essence : 0
         * realname : haha58
         * pic_data : [{"img_id":"1314","img_url":"http:\\/\\/img1.uu1.com\\/images\\/112\\/11\\/1160\\/d64c57ea69b6b3e476a3ee8be092a91d.jpg"},{"img_id":"1313","img_url":"http:\\/\\/img1.uu1.com\\/images\\/112\\/11\\/1160\\/06d2bcbb56c87665409f19000b02fcf9.jpg"},{"img_id":"1307","img_url":"http:\\/\\/img1.uu1.com\\/images\\/113\\/11\\/1133\\/fbd9f066eccdbe7461e4f81b5c3a4179.jpg"}]
         * line : {"day":"12\\u65e510\\u665a","lines_id":"12909","lines_name":"\\u6fb3\\u65b0\\u51ef\\u58a812\\u5929\\u5b8c\\u7f8e\\u4e4b\\u65c5  \\u914d\\u5957\\u822a\\u73ed\\uff1aHX\\u8f6c\\u673a","lines_img":null}
         * answer : [{"id":"7","com_id":"89","uid":"24025","aid":"1","answer_name":"admin","content":"ddddddddddddddd","addtime":"2017-04-18 10:13:32","status":"1","is_hide":"1","hide_reason":"","mod_uid":"0","mod_name":""}]
         * order_total_money : 17590.00
         */

        private String order_code;
        private String id;
        private String uid;
        private String oid;
        private String comment;
        private String star;
        private String travel_type;
        private String addtime;
        private String is_hide;
        private String hide_reason;
        private String mod_uid;
        private String mod_name;
        private String is_reply;
        private String is_essence;
        private String realname;
        private LineBean line;
        private String order_total_money;
        private List<PicDataBean> pic_data;
        private List<AnswerBean> answer;

        public String getOrder_code() {
            return order_code;
        }

        public void setOrder_code(String order_code) {
            this.order_code = order_code;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getOid() {
            return oid;
        }

        public void setOid(String oid) {
            this.oid = oid;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public String getTravel_type() {
            return travel_type;
        }

        public void setTravel_type(String travel_type) {
            this.travel_type = travel_type;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getIs_hide() {
            return is_hide;
        }

        public void setIs_hide(String is_hide) {
            this.is_hide = is_hide;
        }

        public String getHide_reason() {
            return hide_reason;
        }

        public void setHide_reason(String hide_reason) {
            this.hide_reason = hide_reason;
        }

        public String getMod_uid() {
            return mod_uid;
        }

        public void setMod_uid(String mod_uid) {
            this.mod_uid = mod_uid;
        }

        public String getMod_name() {
            return mod_name;
        }

        public void setMod_name(String mod_name) {
            this.mod_name = mod_name;
        }

        public String getIs_reply() {
            return is_reply;
        }

        public void setIs_reply(String is_reply) {
            this.is_reply = is_reply;
        }

        public String getIs_essence() {
            return is_essence;
        }

        public void setIs_essence(String is_essence) {
            this.is_essence = is_essence;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public LineBean getLine() {
            return line;
        }

        public void setLine(LineBean line) {
            this.line = line;
        }

        public String getOrder_total_money() {
            return order_total_money;
        }

        public void setOrder_total_money(String order_total_money) {
            this.order_total_money = order_total_money;
        }

        public List<PicDataBean> getPic_data() {
            return pic_data;
        }

        public void setPic_data(List<PicDataBean> pic_data) {
            this.pic_data = pic_data;
        }

        public List<AnswerBean> getAnswer() {
            return answer;
        }

        public void setAnswer(List<AnswerBean> answer) {
            this.answer = answer;
        }

        public static class LineBean {
            /**
             * day : 12\u65e510\u665a
             * lines_id : 12909
             * lines_name : \u6fb3\u65b0\u51ef\u58a812\u5929\u5b8c\u7f8e\u4e4b\u65c5  \u914d\u5957\u822a\u73ed\uff1aHX\u8f6c\u673a
             * lines_img : null
             */

            private String day;
            private String lines_id;
            private String lines_name;
            private Object lines_img;

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public String getLines_id() {
                return lines_id;
            }

            public void setLines_id(String lines_id) {
                this.lines_id = lines_id;
            }

            public String getLines_name() {
                return lines_name;
            }

            public void setLines_name(String lines_name) {
                this.lines_name = lines_name;
            }

            public Object getLines_img() {
                return lines_img;
            }

            public void setLines_img(Object lines_img) {
                this.lines_img = lines_img;
            }
        }

        public static class PicDataBean {
            /**
             * img_id : 1314
             * img_url : http:\/\/img1.uu1.com\/images\/112\/11\/1160\/d64c57ea69b6b3e476a3ee8be092a91d.jpg
             */

            private String img_id;
            private String img_url;

            public String getImg_id() {
                return img_id;
            }

            public void setImg_id(String img_id) {
                this.img_id = img_id;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }
        }

        public static class AnswerBean {
            /**
             * id : 7
             * com_id : 89
             * uid : 24025
             * aid : 1
             * answer_name : admin
             * content : ddddddddddddddd
             * addtime : 2017-04-18 10:13:32
             * status : 1
             * is_hide : 1
             * hide_reason :
             * mod_uid : 0
             * mod_name :
             */

            private String id;
            private String com_id;
            private String uid;
            private String aid;
            private String answer_name;
            private String content;
            private String addtime;
            private String status;
            private String is_hide;
            private String hide_reason;
            private String mod_uid;
            private String mod_name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCom_id() {
                return com_id;
            }

            public void setCom_id(String com_id) {
                this.com_id = com_id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getAid() {
                return aid;
            }

            public void setAid(String aid) {
                this.aid = aid;
            }

            public String getAnswer_name() {
                return answer_name;
            }

            public void setAnswer_name(String answer_name) {
                this.answer_name = answer_name;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getIs_hide() {
                return is_hide;
            }

            public void setIs_hide(String is_hide) {
                this.is_hide = is_hide;
            }

            public String getHide_reason() {
                return hide_reason;
            }

            public void setHide_reason(String hide_reason) {
                this.hide_reason = hide_reason;
            }

            public String getMod_uid() {
                return mod_uid;
            }

            public void setMod_uid(String mod_uid) {
                this.mod_uid = mod_uid;
            }

            public String getMod_name() {
                return mod_name;
            }

            public void setMod_name(String mod_name) {
                this.mod_name = mod_name;
            }
        }
    }
}
