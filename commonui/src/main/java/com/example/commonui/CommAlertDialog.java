package com.example.commonui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;

/**
 * Created by GaryCao on 2019/01/10.
 */
public class CommAlertDialog extends AlertDialog {
    protected CommAlertDialog(Context context) {
        super(context);
    }

    protected CommAlertDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected CommAlertDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    public void show() {
        Window window = getWindow();
        window.setWindowAnimations(R.style.CustomAlertDialog);
        window.setGravity(Gravity.CENTER);//BOTTOM);
        super.show();
    }
}
