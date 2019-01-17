package com.example.commonui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by GaryCao on 2019/01/10.
 */
public class PageIndicatorView extends LinearLayout {
    private int pageCount;
    private int pageSelectedIndex;
    private Drawable selectItemImg;
    private Drawable unselectItemImg;
    private PageChangeListener pageChangeListener = new PageChangeListener();

    public PageIndicatorView(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        final TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.viewPagerIndicator, 0, 0);
        try {
            selectItemImg = attributes.getDrawable(R.styleable.viewPagerIndicator_select_item_icon);
            unselectItemImg = attributes.getDrawable(R.styleable.viewPagerIndicator_unselect_item_icon);
        } finally {
            attributes.recycle();
        }

        if (selectItemImg == null) {
            selectItemImg = getResources().getDrawable(R.drawable.indicator_select_item);
        }
        if (unselectItemImg == null) {
            unselectItemImg = getResources().getDrawable(R.drawable.indicator_unselect_item);
        }
        setOrientation(HORIZONTAL);
    }

    private void updateCurPageIndex(int index) {
        pageSelectedIndex = index;
        updateView();
    }

    private void updatePageCount(int count) {
        pageCount = count;
        updateView();
    }

    private void updateView() {
        this.removeAllViews();

        for (int i = 0; i < pageCount; i++) {
            ImageView itemImage = new ImageView(getContext());
            if (pageSelectedIndex == i) {
                itemImage.setBackground(selectItemImg);
            } else {
                itemImage.setBackground(unselectItemImg);
            }
            this.addView(itemImage);
        }
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

    public void registerPageChangeListener(ViewPager viewPager) {
        viewPager.addOnPageChangeListener(pageChangeListener);
        updatePageCount(viewPager.getAdapter().getCount());
    }
}
