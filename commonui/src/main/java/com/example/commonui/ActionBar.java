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
    private LayoutInflater mInflater;
    private RelativeLayout mActionBarView;
    private TextView mTitleView;
    private View mLeftActionView;
    private View mRightActionView;

    public ActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mActionBarView = (RelativeLayout) mInflater.inflate(R.layout.actionbar, null);
        addView(mActionBarView);

        mTitleView = (TextView) mActionBarView.findViewById(R.id.tv_title);
        mLeftActionView = (ImageView) mActionBarView.findViewById(R.id.iv_left);
        mRightActionView = (ImageView) mActionBarView.findViewById(R.id.iv_right);

        /*custom attrs*/
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.actionBar);
        if (typedArray != null) {
            CharSequence title = typedArray.getString(R.styleable.actionBar_tv_title_str);
            setTitle(title);

            Drawable iv_left = typedArray.getDrawable(R.styleable.actionBar_iv_left_icon);
            if (iv_left != null) {
                mLeftActionView.setBackground(iv_left);
            }

            Drawable iv_right = typedArray.getDrawable(R.styleable.actionBar_iv_right_icon);
            if (iv_right != null) {
                mRightActionView.setBackground(iv_right);
            }

            typedArray.recycle();
        }
    }

    public void setLeftOnClickListener(View.OnClickListener listener) {
        if (null != mLeftActionView) {
            mLeftActionView.setOnClickListener(listener);
        }
    }

    public void setRightOnClickListener(View.OnClickListener listener) {
        if (null != mRightActionView) {
            mRightActionView.setOnClickListener(listener);
        }
    }

    public void setTitle(CharSequence title) {
        if (title != null) {
            mTitleView.setText(title);
        }
    }

    public void setTitle(int resid) {
        mTitleView.setText(resid);
    }
}
