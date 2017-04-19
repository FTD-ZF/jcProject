package com.example.host.jsnewmall.utils;

/**
 * Created by host on 2017/3/3.
 */

public class UrlUtils {
    //http://open.4008289828.com/pay/pay.php?payment_type=APPalipay&order_code=w170308142240160954&order_source=1

    public static final String APP_URL="app";
    public static final String ROUTE_LINE="lines";
    public static final String USER="user";
    public static final String CONTENT="content";
    public static final String PAY="pay/pay.php";
    public static final String SEARCH="search";

    private  static final String  BASE_TEST_JS="http://open.4008289828.com/";
    private  static final String  BASE_JS="http://open.uu1.com/";

    public static String getBaseUrl() {
        String baseUrl = "";
        if (DebugUtils.isDebug) {
            baseUrl = BASE_TEST_JS;
        } else {
            baseUrl = BASE_JS;
        }
        return baseUrl;
    }
}
