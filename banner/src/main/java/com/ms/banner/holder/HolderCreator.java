package com.ms.banner.holder;

/**
 * Created by songwenchao
 * on 2018/5/16 0016.
 */
public interface HolderCreator<VH extends BannerViewHolder> {

    VH createViewHolder();
}
