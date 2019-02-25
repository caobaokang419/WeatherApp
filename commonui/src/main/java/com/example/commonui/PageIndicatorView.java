package com.example.commonui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by GaryCao on 2019/01/10.
 */
public class PageIndicatorView extends LinearLayout {
    private int mPageCount;
    private int mPageSelectedIndex;
    private Drawable mSelectItem;
    private Drawable mUnSelectItem;

    private PageChangeListener mPageChangeListener = new PageChangeListener();

    public PageIndicatorView(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        final TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.viewPagerIndicator, 0, 0);
        try {
            mSelectItem = attributes.getDrawable(R.styleable.viewPagerIndicator_iv_select_item_icon);
            mUnSelectItem = attributes.getDrawable(R.styleable.viewPagerIndicator_iv_unselect_item_icon);
        } finally {
            attributes.recycle();
        }

        if (mSelectItem == null) {
            mSelectItem = getResources().getDrawable(R.drawable.indicator_select_item);
        }
        if (mUnSelectItem == null) {
            mUnSelectItem = getResources().getDrawable(R.drawable.indicator_unselect_item);
        }
        setGravity(Gravity.CENTER);
        setOrientation(HORIZONTAL);
    }

    private void updateCurPageIndex(int index) {
        mPageSelectedIndex = index;
        updateView();
    }

    private void updatePageCount(int count) {
        mPageCount = count;
        updateView();
    }

    private void updateView() {
        this.removeAllViews();

        for (int i = 0; i < mPageCount; i++) {
            ImageView itemImage = generatorViewItem();
            if (mPageSelectedIndex == i) {
                itemImage.setBackground(mSelectItem);
            } else {
                itemImage.setBackground(mUnSelectItem);
            }
            this.addView(itemImage);
        }
    }

    private ImageView generatorViewItem(){
        ImageView itemImage = new ImageView(getContext());
        int radios =(int)getResources().getDimension(R.dimen.pager_indicator_item_radius);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(radios,radios);
        layoutParams.setMarginStart((int)getResources().getDimension(R.dimen.pager_indicator_item_start_magrin));
        itemImage.setLayoutParams(layoutParams);
        return itemImage;
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
        /*GoF23 设计模式 6：观察者模式: 订阅&通知UI刷新*/
        viewPager.addOnPageChangeListener(mPageChangeListener);

        updatePageCount(viewPager.getAdapter().getCount());
    }
}
