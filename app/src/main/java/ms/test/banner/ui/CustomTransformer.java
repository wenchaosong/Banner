package ms.test.banner.ui;

import android.support.v4.view.ViewPager;
import android.view.View;

public class CustomTransformer implements ViewPager.PageTransformer {

    /*final float SCALE_MAX = 0.8f;
    final float ALPHA_MAX = 0.5f;

    public void transformPage(View view, float position) {
        float scale = (position < 0)
                ? ((1 - SCALE_MAX) * position + 1)
                : ((SCALE_MAX - 1) * position + 1);
        float alpha = (position < 0)
                ? ((1 - ALPHA_MAX) * position + 1)
                : ((ALPHA_MAX - 1) * position + 1);
        if (position < 0) {
            view.setPivotX(view.getWidth());
            view.setPivotY(view.getHeight() / 2);
        } else {
            view.setPivotX(0);
            view.setPivotY(view.getHeight() / 2);
        }
        view.setScaleX(scale);
        view.setScaleY(scale);
        view.setAlpha(Math.abs(alpha));
    }*/
    private static final float MIN_SCALE = 0.9F;

    @Override
    public void transformPage(View page, float position) {

        if (position < -1) {
            page.setScaleY(MIN_SCALE);
        } else if (position <= 1) {
            //
            float scale = Math.max(MIN_SCALE, 1 - Math.abs(position));
            page.setScaleY(scale);
            /*page.setScaleX(scale);
            if(position<0){
                page.setTranslationX(width * (1 - scale) /2);
            }else{
                page.setTranslationX(-width * (1 - scale) /2);
            }*/

        } else {
            page.setScaleY(MIN_SCALE);
        }
    }
}
