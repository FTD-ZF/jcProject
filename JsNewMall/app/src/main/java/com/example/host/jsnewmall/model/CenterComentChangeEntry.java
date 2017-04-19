package com.example.host.jsnewmall.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by host on 2017/4/18.
 */

public class CenterComentChangeEntry {

    /**
     * data : {"line_score":null,"comment_info":[{"id":"89","uid":"24025","oid":"3094","line_id":"12909","line_type":"1","comment":"\\u7ebf\\u8def\\u5b89\\u6392\\u5408\\u7406\\uff0c\\u666f\\u8272\\u5b9c\\u4eba\\uff0c\\u5bfc\\u6e38\\u670d\\u52a1\\u4e5f\\u4e0d\\u9519","star":"4","travel_type":"4","addtime":"2017-04-18 10:11:06","is_hide":"1","hide_reason":"","mod_uid":"0","mod_name":"","is_reply":"2","is_essence":"0","user":[{"headimgurl":"http:\\/\\/open.4008289828.com\\/upload\\/458f5c1dd9896b439e5fe366b70d64a1.jpg","user_name":"15961167765","nickname":"66677700"}],"answer":[{"id":"7","com_id":"89","uid":"24025","aid":"1","answer_name":"admin","content":"ddddddddddddddd","addtime":"2017-04-18 10:13:32","status":"1","is_hide":"1","hide_reason":"","mod_uid":"0","mod_name":""}],"picture":[{"img_id":"1314","img_url":"http:\\/\\/img1.uu1.com\\/images\\/112\\/11\\/1160\\/d64c57ea69b6b3e476a3ee8be092a91d.jpg"},{"img_id":"1313","img_url":"http:\\/\\/img1.uu1.com\\/images\\/112\\/11\\/1160\\/06d2bcbb56c87665409f19000b02fcf9.jpg"},{"img_id":"1307","img_url":"http:\\/\\/img1.uu1.com\\/images\\/113\\/11\\/1133\\/fbd9f066eccdbe7461e4f81b5c3a4179.jpg"}]},{"id":"88","uid":"24025","oid":"3043","line_id":"16152","line_type":"1","comment":"556","star":"4","travel_type":"1","addtime":"2017-04-17 17:49:55","is_hide":"1","hide_reason":"","mod_uid":"0","mod_name":"","is_reply":"1","is_essence":"0","user":[{"headimgurl":"http:\\/\\/open.4008289828.com\\/upload\\/458f5c1dd9896b439e5fe366b70d64a1.jpg","user_name":"15961167765","nickname":"66677700"}],"answer":[],"picture":null},{"id":"87","uid":"24025","oid":"2979","line_id":"16150","line_type":"1","comment":"Gdfhjjutrsdghtdsf","star":"4","travel_type":"1","addtime":"2017-04-17 08:59:01","is_hide":"1","hide_reason":"","mod_uid":"0","mod_name":"","is_reply":"1","is_essence":"0","user":[{"headimgurl":"http:\\/\\/open.4008289828.com\\/upload\\/458f5c1dd9896b439e5fe366b70d64a1.jpg","user_name":"15961167765","nickname":"66677700"}],"answer":[],"picture":null},{"id":"85","uid":"24025","oid":"2528","line_id":"2760","line_type":"1","comment":"dddddddddddddddddddd","star":"5","travel_type":"6","addtime":"2017-04-12 13:11:20","is_hide":"1","hide_reason":"","mod_uid":"0","mod_name":"","is_reply":"1","is_essence":"0","user":[{"headimgurl":"http:\\/\\/open.4008289828.com\\/upload\\/458f5c1dd9896b439e5fe366b70d64a1.jpg","user_name":"15961167765","nickname":"66677700"}],"answer":[],"picture":null},{"id":"78","uid":"24025","oid":"2978","line_id":"14363","line_type":"1","comment":"\\u6bd4\\u8f83\\u6ee1\\u610f\\uff0c\\u5e0c\\u671b\\u8d35\\u793e\\u518d\\u63a5\\u518d\\u5389","star":"4","travel_type":"1","addtime":"2017-04-11 09:02:11","is_hide":"1","hide_reason":"","mod_uid":"0","mod_name":"","is_reply":"1","is_essence":"0","user":[{"headimgurl":"http:\\/\\/open.4008289828.com\\/upload\\/458f5c1dd9896b439e5fe366b70d64a1.jpg","user_name":"15961167765","nickname":"66677700"}],"answer":[],"picture":null},{"id":"74","uid":"24025","oid":"2581","line_id":"9959","line_type":"1","comment":"\\u65c5\\u884c\\u6109\\u5feb\\uff0c\\u559c\\u6b22\\u8fd9\\u6837\\u7684\\u611f\\u89c9\\uff0c\\u4e0b\\u6b21\\u7ee7\\u7eed\\u4f53\\u9a8c","star":"4","travel_type":"2","addtime":"2017-04-07 11:26:45","is_hide":"1","hide_reason":"","mod_uid":"0","mod_name":"","is_reply":"1","is_essence":"0","user":[{"headimgurl":"http:\\/\\/open.4008289828.com\\/upload\\/458f5c1dd9896b439e5fe366b70d64a1.jpg","user_name":"15961167765","nickname":"66677700"}],"answer":[],"picture":null}],"count":"0"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * line_score : null
         * comment_info : [{"id":"89","uid":"24025","oid":"3094","line_id":"12909","line_type":"1","comment":"\\u7ebf\\u8def\\u5b89\\u6392\\u5408\\u7406\\uff0c\\u666f\\u8272\\u5b9c\\u4eba\\uff0c\\u5bfc\\u6e38\\u670d\\u52a1\\u4e5f\\u4e0d\\u9519","star":"4","travel_type":"4","addtime":"2017-04-18 10:11:06","is_hide":"1","hide_reason":"","mod_uid":"0","mod_name":"","is_reply":"2","is_essence":"0","user":[{"headimgurl":"http:\\/\\/open.4008289828.com\\/upload\\/458f5c1dd9896b439e5fe366b70d64a1.jpg","user_name":"15961167765","nickname":"66677700"}],"answer":[{"id":"7","com_id":"89","uid":"24025","aid":"1","answer_name":"admin","content":"ddddddddddddddd","addtime":"2017-04-18 10:13:32","status":"1","is_hide":"1","hide_reason":"","mod_uid":"0","mod_name":""}],"picture":[{"img_id":"1314","img_url":"http:\\/\\/img1.uu1.com\\/images\\/112\\/11\\/1160\\/d64c57ea69b6b3e476a3ee8be092a91d.jpg"},{"img_id":"1313","img_url":"http:\\/\\/img1.uu1.com\\/images\\/112\\/11\\/1160\\/06d2bcbb56c87665409f19000b02fcf9.jpg"},{"img_id":"1307","img_url":"http:\\/\\/img1.uu1.com\\/images\\/113\\/11\\/1133\\/fbd9f066eccdbe7461e4f81b5c3a4179.jpg"}]},{"id":"88","uid":"24025","oid":"3043","line_id":"16152","line_type":"1","comment":"556","star":"4","travel_type":"1","addtime":"2017-04-17 17:49:55","is_hide":"1","hide_reason":"","mod_uid":"0","mod_name":"","is_reply":"1","is_essence":"0","user":[{"headimgurl":"http:\\/\\/open.4008289828.com\\/upload\\/458f5c1dd9896b439e5fe366b70d64a1.jpg","user_name":"15961167765","nickname":"66677700"}],"answer":[],"picture":null},{"id":"87","uid":"24025","oid":"2979","line_id":"16150","line_type":"1","comment":"Gdfhjjutrsdghtdsf","star":"4","travel_type":"1","addtime":"2017-04-17 08:59:01","is_hide":"1","hide_reason":"","mod_uid":"0","mod_name":"","is_reply":"1","is_essence":"0","user":[{"headimgurl":"http:\\/\\/open.4008289828.com\\/upload\\/458f5c1dd9896b439e5fe366b70d64a1.jpg","user_name":"15961167765","nickname":"66677700"}],"answer":[],"picture":null},{"id":"85","uid":"24025","oid":"2528","line_id":"2760","line_type":"1","comment":"dddddddddddddddddddd","star":"5","travel_type":"6","addtime":"2017-04-12 13:11:20","is_hide":"1","hide_reason":"","mod_uid":"0","mod_name":"","is_reply":"1","is_essence":"0","user":[{"headimgurl":"http:\\/\\/open.4008289828.com\\/upload\\/458f5c1dd9896b439e5fe366b70d64a1.jpg","user_name":"15961167765","nickname":"66677700"}],"answer":[],"picture":null},{"id":"78","uid":"24025","oid":"2978","line_id":"14363","line_type":"1","comment":"\\u6bd4\\u8f83\\u6ee1\\u610f\\uff0c\\u5e0c\\u671b\\u8d35\\u793e\\u518d\\u63a5\\u518d\\u5389","star":"4","travel_type":"1","addtime":"2017-04-11 09:02:11","is_hide":"1","hide_reason":"","mod_uid":"0","mod_name":"","is_reply":"1","is_essence":"0","user":[{"headimgurl":"http:\\/\\/open.4008289828.com\\/upload\\/458f5c1dd9896b439e5fe366b70d64a1.jpg","user_name":"15961167765","nickname":"66677700"}],"answer":[],"picture":null},{"id":"74","uid":"24025","oid":"2581","line_id":"9959","line_type":"1","comment":"\\u65c5\\u884c\\u6109\\u5feb\\uff0c\\u559c\\u6b22\\u8fd9\\u6837\\u7684\\u611f\\u89c9\\uff0c\\u4e0b\\u6b21\\u7ee7\\u7eed\\u4f53\\u9a8c","star":"4","travel_type":"2","addtime":"2017-04-07 11:26:45","is_hide":"1","hide_reason":"","mod_uid":"0","mod_name":"","is_reply":"1","is_essence":"0","user":[{"headimgurl":"http:\\/\\/open.4008289828.com\\/upload\\/458f5c1dd9896b439e5fe366b70d64a1.jpg","user_name":"15961167765","nickname":"66677700"}],"answer":[],"picture":null}]
         * count : 0
         */

        private Object line_score;
        private String count;
        private List<CommentInfoBean> comment_info;

        public Object getLine_score() {
            return line_score;
        }

        public void setLine_score(Object line_score) {
            this.line_score = line_score;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public List<CommentInfoBean> getComment_info() {
            return comment_info;
        }

        public void setComment_info(List<CommentInfoBean> comment_info) {
            this.comment_info = comment_info;
        }

        public static class CommentInfoBean {
            /**
             * id : 89
             * uid : 24025
             * oid : 3094
             * line_id : 12909
             * line_type : 1
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
             * user : [{"headimgurl":"http:\\/\\/open.4008289828.com\\/upload\\/458f5c1dd9896b439e5fe366b70d64a1.jpg","user_name":"15961167765","nickname":"66677700"}]
             * answer : [{"id":"7","com_id":"89","uid":"24025","aid":"1","answer_name":"admin","content":"ddddddddddddddd","addtime":"2017-04-18 10:13:32","status":"1","is_hide":"1","hide_reason":"","mod_uid":"0","mod_name":""}]
             * picture : [{"img_id":"1314","img_url":"http:\\/\\/img1.uu1.com\\/images\\/112\\/11\\/1160\\/d64c57ea69b6b3e476a3ee8be092a91d.jpg"},{"img_id":"1313","img_url":"http:\\/\\/img1.uu1.com\\/images\\/112\\/11\\/1160\\/06d2bcbb56c87665409f19000b02fcf9.jpg"},{"img_id":"1307","img_url":"http:\\/\\/img1.uu1.com\\/images\\/113\\/11\\/1133\\/fbd9f066eccdbe7461e4f81b5c3a4179.jpg"}]
             */

            private String id;
            private String uid;
            private String oid;
            private String line_id;
            private String line_type;
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
            private List<UserBean> user;
            private List<AnswerBean> answer;
            private List<PictureBean> picture;

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

            public String getLine_id() {
                return line_id;
            }

            public void setLine_id(String line_id) {
                this.line_id = line_id;
            }

            public String getLine_type() {
                return line_type;
            }

            public void setLine_type(String line_type) {
                this.line_type = line_type;
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

            public List<UserBean> getUser() {
                return user;
            }

            public void setUser(List<UserBean> user) {
                this.user = user;
            }

            public List<AnswerBean> getAnswer() {
                return answer;
            }

            public void setAnswer(List<AnswerBean> answer) {
                this.answer = answer;
            }

            public List<PictureBean> getPicture() {
                return picture;
            }

            public void setPicture(List<PictureBean> picture) {
                this.picture = picture;
            }

            public static class UserBean {
                /**
                 * headimgurl : http:\/\/open.4008289828.com\/upload\/458f5c1dd9896b439e5fe366b70d64a1.jpg
                 * user_name : 15961167765
                 * nickname : 66677700
                 */

                private String headimgurl;
                private String user_name;
                private String nickname;

                public String getHeadimgurl() {
                    return headimgurl;
                }

                public void setHeadimgurl(String headimgurl) {
                    this.headimgurl = headimgurl;
                }

                public String getUser_name() {
                    return user_name;
                }

                public void setUser_name(String user_name) {
                    this.user_name = user_name;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
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

            public static class PictureBean implements Serializable{
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
        }
    }
}
