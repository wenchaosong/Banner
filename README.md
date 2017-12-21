# Android图片轮播控件

现在的绝大数app都有banner界面，实现循环播放多个广告图片和手动滑动循环等功能。因为ViewPager并不支持循环翻页，
所以要实现循环还得需要自己去动手，我就把项目中的控件剔了出来，希望大家觉得有用。目前框架可以进行不同样式、不同动画设置，
以及完善的api方法能满足大部分的需求了。

## 效果图

|模式|图片
|---|---|
|两端缩进模式|![image](/pic/1.png )|
|指示器模式|![效果示例](http://oceh51kku.bkt.clouddn.com/banner_example1.png)|
|数字模式|![效果示例](http://oceh51kku.bkt.clouddn.com/banner_example2.png)|
|数字加标题模式|![效果示例](http://oceh51kku.bkt.clouddn.com/banner_example3.png)|
|指示器加标题模式<br>垂直显示|![效果示例](http://oceh51kku.bkt.clouddn.com/banner_example4.png)|
|指示器加标题模式<br>水平显示|![效果示例](http://oceh51kku.bkt.clouddn.com/banner_example5.png)|

## 常量
|常量名称|描述|所属方法
|---|---|---|
|BannerConfig.NOT_INDICATOR| 不显示指示器和标题|setBannerStyle
|BannerConfig.CIRCLE_INDICATOR| 显示圆形指示器|setBannerStyle
|BannerConfig.NUM_INDICATOR| 显示数字指示器|setBannerStyle
|BannerConfig.NUM_INDICATOR_TITLE| 显示数字指示器和标题|setBannerStyle
|BannerConfig.CIRCLE_INDICATOR_TITLE| 显示圆形指示器和标题（垂直显示）|setBannerStyle
|BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE| 显示圆形指示器和标题（水平显示）|setBannerStyle
|BannerConfig.LEFT| 指示器居左|setIndicatorGravity
|BannerConfig.CENTER| 指示器居中|setIndicatorGravity
|BannerConfig.RIGHT| 指示器居右|setIndicatorGravity

## 动画常量类（setBannerAnimation方法调用）
[ViewPagerTransforms](https://github.com/ToxicBakery/ViewPagerTransforms) `动画时集成的第三方库，可能有兼容问题导致position位置不准确，你可以选择参考动画然后自定义动画`

|常量类名|
|---|
|Transformer.Default|
|Transformer.Accordion|
|Transformer.BackgroundToForeground|
|Transformer.ForegroundToBackground|
|Transformer.CubeIn|
|Transformer.CubeOut|
|Transformer.DepthPage|
|Transformer.FlipHorizontal|
|Transformer.FlipVertical|
|Transformer.RotateDown|
|Transformer.RotateUp|
|Transformer.ScaleInOut|
|Transformer.Stack|
|Transformer.Tablet|
|Transformer.ZoomIn|
|Transformer.ZoomOut|
|Transformer.ZoomOutSlide|

## 方法
|方法名|描述|版本限制
|---|---|---|
|setBannerStyle(int bannerStyle)| 设置轮播样式（默认为CIRCLE_INDICATOR）|无
|setIndicatorGravity(int type)| 设置指示器位置（没有标题默认为右边,有标题时默认左边）|无
|isAutoPlay(boolean isAutoPlay)| 设置是否自动轮播（默认自动）|无
|setViewPagerIsScroll(boolean isScroll)| 设置是否允许手动滑动轮播图（默认true）|1.4.5开始
|update(List<?> imageUrls,List<String> titles)| 更新图片和标题 |1.4.5开始
|update(List<?> imageUrls)| 更新图片 |1.4.5开始
|startAutoPlay()|开始轮播|1.4开始，此方法只作用于banner加载完毕-->需要在start()后执行
|stopAutoPlay()|结束轮播|1.4开始，此方法只作用于banner加载完毕-->需要在start()后执行
|start()|开始进行banner渲染（必须放到最后执行）|1.4开始
|setOffscreenPageLimit(int limit)|同viewpager的方法作用一样|1.4.2开始
|setBannerTitle(String[] titles)| 设置轮播要显示的标题和图片对应（如果不传默认不显示标题）|1.3.3结束
|setBannerTitleList(List<String> titles)| 设置轮播要显示的标题和图片对应（如果不传默认不显示标题）|1.3.3结束
|setBannerTitles(List<String> titles)| 设置轮播要显示的标题和图片对应（如果不传默认不显示标题）|1.4开始
|setDelayTime(int time)| 设置轮播图片间隔时间（单位毫秒，默认为2000）|无
|setImages(Object[]/List<?> imagesUrl)| 设置轮播图片(所有设置参数方法都放在此方法之前执行)|1.4后去掉数组传参
|setImages(Object[]/List<?> imagesUrl,OnLoadImageListener listener)| 设置轮播图片，并且自定义图片加载方式|1.3.3结束
|setOnBannerClickListener(this)|设置点击事件，下标是从1开始|无（1.4.9以后废弃了）
|setOnBannerListener(this)|设置点击事件，下标是从0开始|1.4.9以后
|setOnLoadImageListener(this)|设置图片加载事件，可以自定义图片加载方式|1.3.3结束
|setImageLoader(Object implements ImageLoader)|设置图片加载器|1.4开始
|setOnPageChangeListener(this)|设置viewpager的滑动监听|无
|setBannerAnimation(Class<? extends PageTransformer> transformer)|设置viewpager的默认动画,传值见动画表|无
|setPageTransformer(boolean reverseDrawingOrder, ViewPager.PageTransformer transformer)|设置viewpager的自定义动画|无

## Attributes属性（banner布局文件中调用）
|Attributes|forma|describe
|---|---|---|
|pageMargin| dimension|两端缩进距离，默认0
|delay_time| integer|轮播间隔时间，默认2000
|scroll_time| integer|轮播滑动执行时间，默认800
|is_auto_play| boolean|是否自动轮播，默认true
|title_background| color|reference|标题栏的背景色
|title_textcolor| color|标题字体颜色
|title_textsize| dimension|标题字体大小
|title_height| dimension|标题栏高度
|indicator_width| dimension|指示器圆形按钮的宽度
|indicator_height| dimension|指示器圆形按钮的高度
|indicator_margin| dimension|指示器之间的间距
|indicator_drawable_selected| reference|指示器选中效果
|indicator_drawable_unselected| reference|指示器未选中效果
|image_scale_type| enum |和imageview的ScaleType作用一样
|banner_default_image| reference | 当banner数据为空是显示的默认图片
|banner_layout| reference |自定义banner布局文件，但是必须保证id的名称一样（你可以将banner的布局文件复制出来进行修改）

### <a href="http://youth5201314.github.io/2016/08/24/ViewPager%E5%88%87%E6%8D%A2%E5%8A%A8%E7%94%BBPageTransformer%E4%BD%BF%E7%94%A8/" target="_blank"> [ 点击查看 ViewPager的PageTransformer用法 ]


## 使用步骤

#### Step 1.依赖banner
Gradle
```groovy
dependencies{
    compile 'com.github.wenchaosong:banner:2.1.0'  //最新版本
}
```
或者引用本地lib
```groovy
compile project(':banner')
```


#### Step 2.添加权限到你的 AndroidManifest.xml
```xml
<!-- if you want to load images from the internet -->
<uses-permission android:name="android.permission.INTERNET" />

<!-- if you want to load images from a file OR from the internet -->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```

#### Step 3.在布局文件中添加Banner，可以设置自定义属性
！！！此步骤可以省略，直接在Activity或者Fragment中new Banner();
```xml
<com.banner.Banner
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/banner"
    android:layout_width="match_parent"
    android:layout_height="高度自己设置" />
```

#### Step 4.重写图片加载器
```java
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        /**
          注意：
          1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
          2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
          传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
          切记不要胡乱强转！
         */
        eg：

        //Glide 加载图片简单用法
        Glide.with(context).load(path).into(imageView);

        //Picasso 加载图片简单用法
        Picasso.with(context).load(path).into(imageView);

        //用fresco加载图片简单用法，记得要写下面的createImageView方法
        Uri uri = Uri.parse((String) path);
        imageView.setImageURI(uri);
    }

    //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
    @Override
    public ImageView createImageView(Context context) {
        //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
        SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
        return simpleDraweeView;
    }
}
```

#### Step 5.在Activity或者Fragment中配置Banner

- 注意！start()方法必须放到最后执行，点击事件请放到start()前，每次都提交问题问为什么点击没有反应？需要轮播一圈才能点击？点击第一个怎么返回1？麻烦仔细阅读文档。

```java
--------------------------简单使用-------------------------------
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Banner banner = (Banner) findViewById(R.id.banner);
    //设置图片加载器
    banner.setImageLoader(new GlideImageLoader());
    //设置图片集合
    banner.setImages(images);
    //banner设置方法全部调用完毕时最后调用
    banner.start();
}
--------------------------详细使用-------------------------------
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Banner banner = (Banner) findViewById(R.id.banner);
    //设置banner样式
    banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
    //设置图片加载器
    banner.setImageLoader(new GlideImageLoader());
    //设置图片集合
    banner.setImages(images);
    //设置banner动画效果
    banner.setBannerAnimation(Transformer.DepthPage);
    //设置标题集合（当banner样式有显示title时）
    banner.setBannerTitles(titles);
    //设置自动轮播，默认为true
    banner.isAutoPlay(true);
    //设置轮播时间
    banner.setDelayTime(1500);
    //设置指示器位置（当banner模式中有指示器时）
    banner.setIndicatorGravity(BannerConfig.CENTER);
    //banner设置方法全部调用完毕时最后调用
    banner.start();
}

-----------------布局文件--------------------
<com.banner.Banner
        android:id="@+id/banner"
        android:layout_width="match_parent"
        android:layout_height="160dp"
        app:banner_default_image="@mipmap/ic_launcher"
        app:banner_layout="@layout/banner"
        app:delay_time="3000"
        app:image_scale_type="center_crop"
        app:indicator_drawable_selected="@drawable/selected_radius"
        app:indicator_drawable_unselected="@drawable/unselected_radius"
        app:indicator_height="10dp"
        app:indicator_margin="3dp"
        app:indicator_width="10dp"
        app:is_auto_play="true"
        app:pageMargin="20dp"
        app:scroll_time="1500"
        app:title_background="#fff"
        app:title_height="50dp"
        app:title_textcolor="#000"
        app:title_textsize="15sp" />

-----------------当然如果你想偷下懒也可以这么用--------------------
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Banner banner = (Banner) findViewById(R.id.banner);
    banner.setImages(images).setImageLoader(new GlideImageLoader()).start();
}
```

#### Step 6.（可选）增加体验
```java
//如果你需要考虑更好的体验，可以这么操作
@Override
protected void onStart() {
    super.onStart();
    //开始轮播
    banner.startAutoPlay();
}

@Override
protected void onStop() {
    super.onStop();
    //结束轮播
    banner.stopAutoPlay();
}
```

## 混淆代码
```java
# glide 的混淆代码
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
# banner 的混淆代码
-keep class com.banner.** {
    *;
 }

```

## 常见问题

* 问：eclipse怎么使用banner？

    * 答：`在历史版本列表中下载你想要版本的aar包提取最新资源/也可以自己把工程转成eclipse的` <br>
          eclipse的集成demo群文件里有共享！

* 问：怎么显示的一片空白？
    * 答：<br>
        1、没有添加网络权限<br>
        2、检查图片链接是否能打开。
* 问：怎么加载其他图片资源（资源文件、文件、Uri、assets、raw、ContentProvider、sd卡资源）？
    * 答：列如！如果你使用的是glide，那么可以如下操作，其他图片图片加载框架可能有不同
        ```java
        //资源文件
        Integer[] images={R.mipmap.a,R.mipmap.b,R.mipmap.c};
        //Uri
        Uri uri = resourceIdToUri(context, R.mipmap.ic_launcher);
        Uri[] images={uri};
        //文件对象
        File[] images={"文件对象","文件对象"};
        //raw 两种方式
        String[] images={"Android.resource://com.frank.glide/raw/raw_1"};
        String[] images={"android.resource://com.frank.glide/raw/"+R.raw.raw_1"};
        //ContentProvider
        String[] images={"content://media/external/images/media/139469"};
        //assets
        String[] images={"file:///android_asset/f003.gif"};
        //sd卡资源
        String[] images={"file://"+ Environment.getExternalStorageDirectory().getPath()+"/test.jpg"};

        banner.setImages(images);//这里接收集合,上面写成集合太占地方，这个大家举一反三就行了啊
        ```

* 问：设置banner指示器颜色怎么变成方的了？

    * 答：首先我先要说很多软件的指示器也是矩形的，然后banner的指示器可以设置color、资源图片、drawable文件夹自定义shape ，
    所以形状你自己可以根据需求定义哦！

* 问：为什么banner的点击事件没有反应，需要下一次轮播才行？点击第一个图片怎么返回1？

     * 答：请将点击事件放在start方法之前执行，start必须放到最后执行，详情可以看demo。

## Thanks

- [ViewPagerTransforms](https://github.com/ToxicBakery/ViewPagerTransforms)
- [banner](https://github.com/youth5201314/banner)
