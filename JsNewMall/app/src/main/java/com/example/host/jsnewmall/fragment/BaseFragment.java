package com.example.host.jsnewmall.fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.WindowManager;

import com.example.host.jsnewmall.view.LoadingDialog;

/**
 * Created by host on 2017/2/15.
 */

public abstract class BaseFragment extends Fragment {
    public static LoadingDialog mDialig;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initDialog();
    }


    public void initDialog() {
        if (mDialig != null && mDialig.isShowing()) {
            mDialig.dismiss();
        }
        mDialig = new LoadingDialog(getActivity());
    }

    private void showLoading(int msg) {
        if (mDialig != null && !mDialig.isShowing()) {
            mDialig.show();
        }
    }

    private void dismissLoading() {
        if (mDialig != null && mDialig.isShowing())
            mDialig.dismiss();
    }

}
