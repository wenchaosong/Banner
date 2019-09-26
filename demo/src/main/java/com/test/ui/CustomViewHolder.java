package com.test.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ms.banner.holder.BannerViewHolder;

/**
 * Created by songwenchao
 * on 2018/5/17 0017.
 * <p>
 * 类名
 * 需要 --
 * 可以 --
 */
public class CustomViewHolder implements BannerViewHolder<Object> {

    @Override
    public View createView(Context context, int position, Object data) {
        // 返回mImageView页面布局
        ImageView imageView = new ImageView(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        );
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        Glide.with(context).load(data).into(imageView);

        return imageView;
    }

}
