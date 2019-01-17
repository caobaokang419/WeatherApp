package com.example.commonui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by GaryCao on 2018/11/10.
 */
public class ActionBar extends RelativeLayout {
    private LayoutInflater inflater;
    private RelativeLayout actionBarView;
    private TextView titleView;
    private View leftActionView;
    private View rightActionView;

    public ActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        actionBarView = (RelativeLayout) inflater.inflate(R.layout.actionbar, null);
        addView(actionBarView);

        titleView = (TextView) actionBarView.findViewById(R.id.tv_title);
        leftActionView = (ImageView) actionBarView.findViewById(R.id.iv_left);
        rightActionView = (ImageView) actionBarView.findViewById(R.id.iv_right);

        /*custom attrs*/
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.actionBar);
        if (typedArray != null) {
            CharSequence title = typedArray.getString(R.styleable.actionBar_tv_title_str);
            setTitle(title);

            Drawable iv_left = typedArray.getDrawable(R.styleable.actionBar_iv_left_icon);
            if (iv_left != null) {
                leftActionView.setBackground(iv_left);
            }

            Drawable iv_right = typedArray.getDrawable(R.styleable.actionBar_iv_right_icon);
            if (iv_right != null) {
                rightActionView.setBackground(iv_right);
            }

            typedArray.recycle();
        }
    }

    public void setLeftOnClickListener(View.OnClickListener listener) {
        if (null != leftActionView) {
            leftActionView.setOnClickListener(listener);
        }
    }

    public void setRightOnClickListener(View.OnClickListener listener) {
        if (null != rightActionView) {
            rightActionView.setOnClickListener(listener);
        }
    }

    public void setTitle(CharSequence title) {
        if (title != null) {
            titleView.setText(title);
        }
    }

    public void setTitle(int resid) {
        titleView.setText(resid);
    }
}
