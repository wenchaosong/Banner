package com.banner.holder;

import android.content.Context;
import android.view.View;

public interface BannerViewHolder<T> {
    /**
     * 创建View
     */
    View createView(Context context);

    /**
     * 绑定数据
     */
    void onBind(Context context, int position, T data);
}
