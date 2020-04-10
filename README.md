# Android 图片轮播控件

[![](https://api.bintray.com/packages/songwenchao0714/maven/Banner/images/download.svg)](https://bintray.com/songwenchao0714/maven/Banner)
[![](https://img.shields.io/github/stars/wenchaosong/Banner?color=green)](https://github.com/wenchaosong/Banner)
[![](https://img.shields.io/github/issues/wenchaosong/Banner?color=red)](https://github.com/wenchaosong/Banner)
[![](https://img.shields.io/github/last-commit/wenchaosong/Banner?color=yellow)](https://github.com/wenchaosong/Banner)
[![](https://img.shields.io/github/release-date/wenchaosong/Banner?color=orange)](https://github.com/wenchaosong/Banner)

图片轮播类似控件比较多,但是真正好用的比较少,大家公认的项目[banner](https://github.com/youth5201314/banner)是比较好用的,
但是作者已经很久没维护了,所以我在他的基础上优化了一部分,满足大家项目中常用的一些需求.
> 具体优化点:<br>
1.优化了自定义布局,不仅仅是一张图片;<br>
2.优化了 onPageSelected 方法调用两次的 bug;<br>
3.增加了多种 banner 样式

## 关于本库的优化点

目前存在一个优化点,就是实现原理上,为了兼容卡片模式,无限轮播的实现方式采用的是 adapter 的 count 取一个比较大的数值,这种方式
其实我是不太想用的,另外一个实现方式是 count 总数加 2,这其实才是真正意义的无限轮播,在这里我也是搜集了各种 banner 的实现源码,
发现第二个方式总有瑕疵.具体就是:卡片模式滑动的时候,最后一张到第一张或者第一张到最后一张图片有个延迟的白边,造成的原因是调用
的方法执行的比较慢,所以有个延迟(第二种方式可以参照 <a href="rollbanner/src/main/java/com/ms/banner/BannerNew.java"> BannerNew.java </a> 里面的代码)

所以在此希望大家有好的处理方式能提 pr,或者 issue,我会认真看,认真解决

- [有问题先看注意事项](#注意事项)
- [有问题先看注意事项](#注意事项)
- [有问题先看注意事项](#注意事项)

## 效果图

### apk 下载及动态展示

[![](https://img.shields.io/badge/downloadAPK-Banner-ff69b4)](https://github.com/wenchaosong/Banner/releases/download/2.3.17/demo.apk)

![效果示例](/pic/GIF.gif)

### 部分效果图

|模式|图片
|---|---|
|指示器模式|![效果示例](/pic/1.png)|
|数字模式|![效果示例](/pic/2.png)|
|数字加标题模式|![效果示例](/pic/3.png)|
|标题模式|![效果示例](/pic/4.png)|
|指示器加标题模式|![效果示例](/pic/5.png)|
|卡片模式|![效果示例](/pic/6.png)|
|自定义指示器模式|![效果示例](/pic/7.png)|
|自定义混合模式|![效果示例](/pic/8.gif)|
|底部弧形模式|![效果示例](/pic/9.png)|
|指示器在外部|![效果示例](/pic/10.png)|
|右端缩进模式|![效果示例](/pic/11.png)|

## 使用步骤

#### Step 1.依赖banner
```
dependencies{
    implementation 'com.ms:banner:1.0.0'
    implementation 'com.ms:banner-androidx:1.0.0'
}
```
或者引用本地lib
```
compile project(':rollbanner')
```

#### Step 2.在布局文件中添加Banner,可以设置自定义属性
```
<com.ms.banner.Banner
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/banner"
    android:layout_width="match_parent"
    android:layout_height="高度自己设置" />
```

#### Step 3.设置布局
```
.setPages(arrList, new CustomViewHolder())

class CustomViewHolder implements BannerViewHolder<String> {

    private CardView mCardView;
    private TextView mTextView;

    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
        mCardView = (CardView) view.findViewById(R.id.group);
        mTextView = (TextView) view.findViewById(R.id.position);
        return view;
    }

    @Override
    public void onBind(Context context, int position, String data) {
        // 数据绑定
        mCardView.setCardBackgroundColor(Color.parseColor(data));
        mTextView.setText(position + "");
    }
}
```

#### Step 4.在Activity或者Fragment中配置Banner

- 注意！start()方法必须放到最后执行

```
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Banner banner = (Banner) findViewById(R.id.banner);
    banner.setAutoPlay(true)
            .setPages(arrList, new CustomViewHolder())
            .start();
}
```

#### Step 5.（可选）
```
@Override
protected void onStart() {
    super.onStart();
    //开始轮播
    if (banner != null && banner.isPrepare() && !banner.isStart()) {
        banner.startAutoPlay();
    }
}

@Override
protected void onStop() {
    super.onStop();
    //结束轮播
    if (banner != null && banner.isPrepare() && banner.isStart()) {
        banner.stopAutoPlay();
    }
}
```

#### Step 6.混淆
```
-keep class com.ms.banner.** {*;}
```

## 注意事项

- [setCurrentPage 方法不建议调用,因为使用的是成员变量保存,除非再次初始化,否则每次初始化都会先显示设定位置的图片](#注意事项)
- [有问题先参考 demo](#注意事项)

另只要是 banner 中布局中可以定义的,都可以重写,包括但不限于指示器,图片,文字等.各个模式相应的代码 demo 中已经有了,可以作为参考,如果有问题可以提 issue

### 属性和方法介绍

##### xml 文件可配置的属性

|属性|值|描述
|---|---|---|
|delay_time|integer|轮播下一张图片的延迟时间|
|scroll_time|integer|滚动到下一张图片的时间|
|is_auto_play|boolean|是否自动轮播|
|is_loop|boolean|是否循环|
|title_background|color、reference|title 的背景|
|title_textcolor|color|title 的字体颜色|
|title_textsize|dimension|title 的字体大小|
|title_height|dimension|title 的高度|
|indicator_width|dimension|指示器的宽度|
|indicator_height|dimension|指示器的高度|
|indicator_margin|dimension|指示器到底部的距离|
|indicator_padding|dimension|指示器之间的间距|
|indicator_drawable_selected|reference|选中的指示器图片|
|indicator_drawable_unselected|reference|未选中的指示器图片|
|banner_default_image|reference|默认的图片|
|page_left_margin|dimension|左边缩进的距离|
|page_right_margin|dimension|右边缩进的距离|
|arc_height|dimension|底部弧形的高度|
|arc_start_color|reference|底部弧形的起始颜色|
|arc_end_color|reference|底部弧形的结束颜色|
|arc_direction|enum|底部弧形的方向|

##### java 文件可调用的方法

```
setDelayTime                设置延迟时间
setAutoPlay                 设置是否自动轮播
setLoop                     设置是否循环
setIndicatorGravity         设置指示器位置
setBannerAnimation          设置滚动动画
setBannerTitles             设置 title 数据
setBannerStyle              设置样式
setViewPagerIsScroll        设置是否可以滚动
setPages                    设置数据源
setCurrentPage              设置当前页
update                      刷新
updateBannerStyle           刷新样式
start                       开始使用
isPrepare                   是否加载完成
isStart                     是否轮播中
setIndicatorRes             设置指示器资源
startAutoPlay               开始自动轮播
stopAutoPlay                停止轮播
setOnBannerClickListener    监听点击事件
setOnPageChangeListener     监听页面变化事件
```
