package com.example.host.jsnewmall.view.starpoints;

import android.content.Context;

/**
 * Created by host on 2017/4/17.
 */

public class BaseAttrs {
    public static final int INVAILD_VALUE = -1;

    protected Context mContext;

    protected BaseAttrs() {
        mContext = Baseappview.instance();
    }
}
