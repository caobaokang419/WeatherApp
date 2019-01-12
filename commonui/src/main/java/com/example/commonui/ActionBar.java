package com.example.commonui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by GaryCao on 2018/11/10.
 */
public class ActionBar extends RelativeLayout implements View.OnClickListener {
    private LayoutInflater inflater;
    private RelativeLayout actionBarView;
    private TextView titleView;
    private View leftActionView;
    private View rightActionView;
    private IActionBarOnClickListener iActionBarOnClickListener;

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

    public void setOnClickListener(IActionBarOnClickListener listener) {
        iActionBarOnClickListener = listener;
        if (null != leftActionView) {
            leftActionView.setOnClickListener(this);
        }

        if (null != rightActionView) {
            rightActionView.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.iv_left) {
            if (null != iActionBarOnClickListener) {
                Log.d("","onClickedActBarLeftBtn()");
                iActionBarOnClickListener.onClickedActBarLeftBtn();
            }
        } else if (viewId == R.id.iv_right) {
            if (null != iActionBarOnClickListener) {
                Log.d("","onClickedActBarRightBtn()");
                iActionBarOnClickListener.onClickedActBarRightBtn();
            }
        }
    }
}
