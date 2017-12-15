package com.banner.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

public class ScaleYTransformer implements ViewPager.PageTransformer {

    private ViewPager viewPager;
    private float scale;

    public ScaleYTransformer(float scale) {
        this.scale = scale;
    }

    @Override
    public void transformPage(View view, float position) {
        if (viewPager == null) {
            viewPager = (ViewPager) view.getParent();
        }

        int leftInScreen = view.getLeft() - viewPager.getScrollX();
        int centerXInViewPager = leftInScreen + view.getMeasuredWidth() / 2;
        int offsetX = centerXInViewPager - viewPager.getMeasuredWidth() / 2;
        float offsetRate = (float) offsetX * scale / viewPager.getMeasuredWidth();
        float scaleFactor = 1 - Math.abs(offsetRate);
        if (scaleFactor > 0) {
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
        }
    }
}
