package com.test.ui;

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

    private TextView mTitle;
    private TextView mPosition;

    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
        mTitle = (TextView) view.findViewById(R.id.title);
        mPosition = (TextView) view.findViewById(R.id.position);
        return view;
    }

    @Override
    public void onBind(Context context, int position, CustomData data) {
        // 数据绑定
        mTitle.setText(data.getTitle());
        mPosition.setText(position + "");
    }
}
