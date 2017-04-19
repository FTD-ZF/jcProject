package com.example.host.jsnewmall.model;

/**
 * Created by host on 2017/3/17.
 */

public class ZhifubaoinfoaEntry {

    /**
     * res : 1
     * msg : \u67e5\u8be2\u6210\u529f
     * data : {"id":"14","supplier_id":"1","supplier_name":"\\u5e38\\u5dde\\u6625\\u79cb\\u56fd\\u9645\\u65c5\\u884c\\u793e\\u6709\\u9650\\u516c\\u53f8","pay_type":"21","pay_config":"","pay_config_format":{"partner":"2088801243718770","key":"xwqnqizl6r3o6wp52preyprhc1lblvfe","seller_email":"czcqgl@163.com","rsa_private_key":"MIICXQIBAAKBgQDZbiaOzpAsTqqCGXFvglJ8aaRmFz9+FHRCuq33IMmcN2OzPLmQ\\n7OwwlHsn9ZCJ4mNJzTFDLJvJCwunkjPugc5HKAhuXyAg3poodNfWtMQFJbRi9EIg\\nUfvZfWLEgcLn\\/nkt4\\/j2zoKfITyByMj2YCfYvXI6Nd1rqZil4JhLp376FQIDAQAB\\nAoGBALMXXKESGYS1QxKoHFxJzuF1Lpl8mqqh9GUmWLaOhiGrKe7vnLV3wM\\/+7TYr\\nuWMpWrjmT6ZR7vt4op\\/i9+2Dc8B5KM15iovbIUk0dLfXkFL9Mg6\\/lz0nBR2qRADa\\nw6\\/uMRyQVHdoFvf4Zn6evQRZwWkl2jnxbjeAQDCchN+Sx\\/QdAkEA+nfZtioFSyl2\\nbndyDnC4Rqrhjooc2NhzWi6yJOtUUAQvr72Z+3na3E4nwWQnq\\/hOPLbAi+88vY+6\\ng1z56wg50wJBAN47gOzahOzkD\\/pefqpMemLsXkzpTENhvUvj3haTdCBFF1AgaHID\\nIxxnrAFL+JSVWzSf1mOV4ntMvIszzCFC43cCQFn56gzlpYctuuzOHfwQBDLmuiaR\\nePFxOGciNT7MBzrDdjoEu6c6TCQuM1vm1dWSWtC2ug7K\\/5\\/9XPxJz0Heb58CQQDI\\nm75OERRdI6KoyBRfdpokImsd0pvdV6WhlBkvF\\/yEBNp+okKmaN6RDaTfpszGk+y5\\nHHBThUHlO+gXYLKsc80RAkBtqZn+Q\\/tj+kDm9\\/o8quunkv8fzwL2qjsudMa89DcV\\nIAau+mGc5RxJoRq8gOzniF34MQ\\/UKjs0kUDMxcDqwasv"}}
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
         * id : 14
         * supplier_id : 1
         * supplier_name : \u5e38\u5dde\u6625\u79cb\u56fd\u9645\u65c5\u884c\u793e\u6709\u9650\u516c\u53f8
         * pay_type : 21
         * pay_config :
         * pay_config_format : {"partner":"2088801243718770","key":"xwqnqizl6r3o6wp52preyprhc1lblvfe","seller_email":"czcqgl@163.com","rsa_private_key":"MIICXQIBAAKBgQDZbiaOzpAsTqqCGXFvglJ8aaRmFz9+FHRCuq33IMmcN2OzPLmQ\\n7OwwlHsn9ZCJ4mNJzTFDLJvJCwunkjPugc5HKAhuXyAg3poodNfWtMQFJbRi9EIg\\nUfvZfWLEgcLn\\/nkt4\\/j2zoKfITyByMj2YCfYvXI6Nd1rqZil4JhLp376FQIDAQAB\\nAoGBALMXXKESGYS1QxKoHFxJzuF1Lpl8mqqh9GUmWLaOhiGrKe7vnLV3wM\\/+7TYr\\nuWMpWrjmT6ZR7vt4op\\/i9+2Dc8B5KM15iovbIUk0dLfXkFL9Mg6\\/lz0nBR2qRADa\\nw6\\/uMRyQVHdoFvf4Zn6evQRZwWkl2jnxbjeAQDCchN+Sx\\/QdAkEA+nfZtioFSyl2\\nbndyDnC4Rqrhjooc2NhzWi6yJOtUUAQvr72Z+3na3E4nwWQnq\\/hOPLbAi+88vY+6\\ng1z56wg50wJBAN47gOzahOzkD\\/pefqpMemLsXkzpTENhvUvj3haTdCBFF1AgaHID\\nIxxnrAFL+JSVWzSf1mOV4ntMvIszzCFC43cCQFn56gzlpYctuuzOHfwQBDLmuiaR\\nePFxOGciNT7MBzrDdjoEu6c6TCQuM1vm1dWSWtC2ug7K\\/5\\/9XPxJz0Heb58CQQDI\\nm75OERRdI6KoyBRfdpokImsd0pvdV6WhlBkvF\\/yEBNp+okKmaN6RDaTfpszGk+y5\\nHHBThUHlO+gXYLKsc80RAkBtqZn+Q\\/tj+kDm9\\/o8quunkv8fzwL2qjsudMa89DcV\\nIAau+mGc5RxJoRq8gOzniF34MQ\\/UKjs0kUDMxcDqwasv"}
         */

        private String id;
        private String supplier_id;
        private String supplier_name;
        private String pay_type;
        private String pay_config;
        private PayConfigFormatBean pay_config_format;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public String getPay_config() {
            return pay_config;
        }

        public void setPay_config(String pay_config) {
            this.pay_config = pay_config;
        }

        public PayConfigFormatBean getPay_config_format() {
            return pay_config_format;
        }

        public void setPay_config_format(PayConfigFormatBean pay_config_format) {
            this.pay_config_format = pay_config_format;
        }

        public static class PayConfigFormatBean {
            /**
             * partner : 2088801243718770
             * key : xwqnqizl6r3o6wp52preyprhc1lblvfe
             * seller_email : czcqgl@163.com
             * rsa_private_key : MIICXQIBAAKBgQDZbiaOzpAsTqqCGXFvglJ8aaRmFz9+FHRCuq33IMmcN2OzPLmQ\n7OwwlHsn9ZCJ4mNJzTFDLJvJCwunkjPugc5HKAhuXyAg3poodNfWtMQFJbRi9EIg\nUfvZfWLEgcLn\/nkt4\/j2zoKfITyByMj2YCfYvXI6Nd1rqZil4JhLp376FQIDAQAB\nAoGBALMXXKESGYS1QxKoHFxJzuF1Lpl8mqqh9GUmWLaOhiGrKe7vnLV3wM\/+7TYr\nuWMpWrjmT6ZR7vt4op\/i9+2Dc8B5KM15iovbIUk0dLfXkFL9Mg6\/lz0nBR2qRADa\nw6\/uMRyQVHdoFvf4Zn6evQRZwWkl2jnxbjeAQDCchN+Sx\/QdAkEA+nfZtioFSyl2\nbndyDnC4Rqrhjooc2NhzWi6yJOtUUAQvr72Z+3na3E4nwWQnq\/hOPLbAi+88vY+6\ng1z56wg50wJBAN47gOzahOzkD\/pefqpMemLsXkzpTENhvUvj3haTdCBFF1AgaHID\nIxxnrAFL+JSVWzSf1mOV4ntMvIszzCFC43cCQFn56gzlpYctuuzOHfwQBDLmuiaR\nePFxOGciNT7MBzrDdjoEu6c6TCQuM1vm1dWSWtC2ug7K\/5\/9XPxJz0Heb58CQQDI\nm75OERRdI6KoyBRfdpokImsd0pvdV6WhlBkvF\/yEBNp+okKmaN6RDaTfpszGk+y5\nHHBThUHlO+gXYLKsc80RAkBtqZn+Q\/tj+kDm9\/o8quunkv8fzwL2qjsudMa89DcV\nIAau+mGc5RxJoRq8gOzniF34MQ\/UKjs0kUDMxcDqwasv
             */

            private String partner;
            private String key;
            private String seller_email;
            private String rsa_private_key;

            public String getPartner() {
                return partner;
            }

            public void setPartner(String partner) {
                this.partner = partner;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getSeller_email() {
                return seller_email;
            }

            public void setSeller_email(String seller_email) {
                this.seller_email = seller_email;
            }

            public String getRsa_private_key() {
                return rsa_private_key;
            }

            public void setRsa_private_key(String rsa_private_key) {
                this.rsa_private_key = rsa_private_key;
            }
        }
    }
}
