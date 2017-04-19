package com.example.host.jsnewmall.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.uu1.nmw.R;


/**
 * Created by host on 2017/3/15.
 */

public class LoadingDialog {
    private Context mContext;

    public View mDialogView;

    private Dialog mDialog;

    public LoadingDialog(Context context) {

        this.mContext = context;

        mDialogView = LayoutInflater.from(context).inflate(
                R.layout.dialog_loading, null);

        initDialog();


    }

    private void initDialog() {
        mDialog = new Dialog(mContext, R.style.loading_dialog);

        mDialog.setContentView(mDialogView);
        mDialog.setCanceledOnTouchOutside(false);

    }

    public void show() {

        if (mDialog != null)
            mDialog.show();
    }

    public void dismiss() {

        if (mDialog != null)
            mDialog.dismiss();
    }

    public boolean isShowing() {

        return mDialog.isShowing();
    }

}
