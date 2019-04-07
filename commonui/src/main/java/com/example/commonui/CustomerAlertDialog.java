package com.example.commonui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by GaryCao on 2019/01/10.
 */
public class CustomerAlertDialog extends AlertDialog {
    protected CustomerAlertDialog(Context context) {
        super(context);

        Window window = getWindow();
        window.setWindowAnimations(R.style.CustomAlertDialog);
        window.setGravity(Gravity.BOTTOM);
    }
}
