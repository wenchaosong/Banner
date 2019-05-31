package com.ms.banner.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

public abstract class ABaseTransformer implements ViewPager.PageTransformer {

    protected abstract void onTransform(View page, float position);

    @Override
    public void transformPage(View page, float position) {
        onPreTransform(page, position);
        onTransform(page, position);
        onPostTransform(page, position);
    }

    /**
     * If the position offset of a fragment is less than negative one or greater than one, returning true will set the
     * fragment alpha to 0f. Otherwise fragment alpha is always defaulted to 1f.
     *
     * @return
     */
    protected boolean hideOffscreenPages() {
        return true;
    }

    /**
     * Indicates if the default animations of the view pager should be used.
     *
     * @return
     */
    protected boolean isPagingEnabled() {
        return false;
    }

    protected void onPreTransform(View page, float position) {
        final float width = page.getWidth();

        page.setRotationX(0);
        page.setRotationY(0);
        page.setRotation(0);
        page.setScaleX(1);
        page.setScaleY(1);
        page.setPivotX(0);
        page.setPivotY(0);
        page.setTranslationY(0);
        page.setTranslationX(isPagingEnabled() ? 0f : -width * position);

        if (hideOffscreenPages()) {
            page.setAlpha(position <= -1f || position >= 1f ? 0f : 1f);
            //			page.setEnabled(false);
        } else {
            //			page.setEnabled(true);
            page.setAlpha(1f);
        }
    }

    protected void onPostTransform(View page, float position) {
    }

    protected static final float min(float val, float min) {
        return val < min ? min : val;
    }

}
