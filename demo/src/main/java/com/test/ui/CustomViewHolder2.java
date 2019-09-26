package com.test.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

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
public class CustomViewHolder2 implements BannerViewHolder<CustomData> {

    @SuppressLint("InflateParams")
    @Override
    public View createView(Context context, int position, CustomData data) {
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
        TextView title = view.findViewById(R.id.title);
        TextView position1 = view.findViewById(R.id.position);

        title.setText(data.getName());
        position1.setText(String.valueOf(position));

        return view;
    }

}
