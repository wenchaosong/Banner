package com.ms.banner.transformer;

import android.view.View;

public class FlipVerticalTransformer extends ABaseTransformer {

    @Override
    protected void onTransform(View view, float position) {
        final float rotation = -180f * position;

        view.setAlpha(rotation > 90f || rotation < -90f ? 0f : 1f);
        view.setPivotX(view.getWidth() * 0.5f);
        view.setPivotY(view.getHeight() * 0.5f);
        view.setRotationX(rotation);
    }

    @Override
    protected void onPostTransform(View page, float position) {
        super.onPostTransform(page, position);

        if (position > -0.5f && position < 0.5f) {
            page.setVisibility(View.VISIBLE);
        } else {
            page.setVisibility(View.INVISIBLE);
        }
    }
}
