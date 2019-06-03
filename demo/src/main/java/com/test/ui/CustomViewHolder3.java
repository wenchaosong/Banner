package com.test.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ms.banner.holder.BannerViewHolder;
import com.test.CustomData;
import com.test.R;

/**
 * Created by songwenchao
 * on 2018/5/17 0017.
 * <p>
 * 类名
 * 需要 --
 * 可以 --
 */
public class CustomViewHolder3 implements BannerViewHolder<CustomData> {

    private TextView mPosition;
    private LinearLayout ll;
    private ImageView image1;
    private ImageView image2;

    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item2, null);
        mPosition = (TextView) view.findViewById(R.id.position);
        ll = (LinearLayout) view.findViewById(R.id.ll_group);
        image1 = (ImageView) view.findViewById(R.id.image1);
        image2 = (ImageView) view.findViewById(R.id.image2);
        return view;
    }

    @Override
    public void onBind(Context context, int position, CustomData data) {
        // 数据绑定
        mPosition.setText(position + "");
        if (data.isMovie()) {
            ll.setVisibility(View.GONE);
            image2.setVisibility(View.VISIBLE);
            Glide.with(context).load(R.mipmap.b1).into(image1);
            Glide.with(context).load(R.mipmap.movie).into(image2);
        } else {
            ll.setVisibility(View.VISIBLE);
            image2.setVisibility(View.GONE);
            Glide.with(context).load(data.getUrl()).into(image1);
            Glide.with(context).load(R.mipmap.b1).into(image2);
        }
    }
}
