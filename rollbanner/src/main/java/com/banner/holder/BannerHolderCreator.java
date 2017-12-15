package com.banner.holder;

public interface BannerHolderCreator<VH extends BannerViewHolder> {
    /**
     * 创建ViewHolder
     */
    VH createViewHolder();
}
