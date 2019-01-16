package com.example.commonui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by GaryCao on 2019/01/10.
 */
public class PageIndicatorView extends LinearLayout {
    private int pageCount;
    private int pageSelectedIndex = 0;
    private Drawable selectItemIcon;
    private Drawable unselectItemIcon;
    private PageChangeListener pageChangeListener = new PageChangeListener();

    public PageIndicatorView(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        final TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.viewPagerIndicator, 0, 0);
        try {
            selectItemIcon = attributes.getDrawable(R.styleable.viewPagerIndicator_select_item_icon);
            unselectItemIcon = attributes.getDrawable(R.styleable.viewPagerIndicator_unselect_item_icon);
        } finally {
            attributes.recycle();
        }
        setOrientation(HORIZONTAL);
    }

    public void registerPageChangeListener(ViewPager viewPager) {
        viewPager.addOnPageChangeListener(pageChangeListener);
        updatePageCount(viewPager.getAdapter().getCount());
    }

    private void updateCurPageIndex(int index) {
        pageSelectedIndex = index;
        //TODO
    }

    private void updatePageCount(int count) {
        pageCount = count;
        //TODO
    }

    class PageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

        @Override
        public void onPageSelected(int position) {
            updateCurPageIndex(position);
        }
    }
}
