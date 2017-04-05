package com.test;

public class BannerItem {
    /** 广告开始时间 */
    public String starttime;

    /** 广告结束时间 */
    public String endtime;

    public String intervaltime;

    /** 广告标题 */
    public String advtTitle;

    public String showtime;

    /** 广告展示是否需要原生Title */
    public String src_TitleVisibility;

    /**
     * 广告文字内容
     */
    public String advtContent;

    /**
     * 广告链接
     */
    public String advtLinked;

    /**
     * 广告类型
     */
    public String advtType;

    /**
     * 广告图片地址
     */
    public String picUrl;

    /**
     * 广告展示跳转的登录方式 0:无需任何登录注册；1：仅仅手机号码注册； 大于等于2：必须要手机号码和资金账号登录
     */
    public String webViewLoginFlag;

    public String ordernum;

    /**
     * 广告位置
     */
    public String advtPosition;
}
