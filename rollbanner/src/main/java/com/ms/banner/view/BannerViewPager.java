package com.ms.banner.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.lang.reflect.Field;

public class BannerViewPager extends ViewPager {

    private boolean scrollable = true;
    private boolean isShow;

    public BannerViewPager(Context context) {
        super(context);
        isShow = true;
    }

    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        isShow = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return this.scrollable && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.scrollable && super.onInterceptTouchEvent(ev);
    }

    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        try {
            Field mFirstLayout = ViewPager.class.getDeclaredField("mFirstLayout");
            mFirstLayout.setAccessible(true);
            if (isShow) {
                isShow = false;
                mFirstLayout.set(this, true);
            } else {
                mFirstLayout.set(this, false);
            }
            getAdapter().notifyDataSetChanged();
            setCurrentItem(getCurrentItem());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
