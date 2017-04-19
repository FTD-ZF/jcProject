package com.example.host.jsnewmall.view.starpoints;

import android.graphics.drawable.StateListDrawable;

/**
 * Created by host on 2017/4/17.
 */

public class DrawableStateListCreator {
    private DrawableAttrs mAttrs;

    public DrawableStateListCreator(DrawableAttrs attrs) {
        mAttrs = attrs;
    }

    public StateListDrawable createStateListDrawable() {
        StateListDrawable state = new StateListDrawable();
        state.addState(new int[]{-android.R.attr.state_enabled}, mAttrs.getDisableDrawable());
        state.addState(new int[]{android.R.attr.state_checked}, mAttrs.getCheckedDrawable());
        state.addState(new int[]{android.R.attr.state_selected}, mAttrs.getSelectedDrawable());
        state.addState(new int[]{android.R.attr.state_pressed}, mAttrs.getPressedDrawable());
        state.addState(new int[]{}, mAttrs.getNormalDrawable());
        return state;
    }
}
