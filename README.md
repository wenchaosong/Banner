# Android图片轮播控件

在原项目[banner](https://github.com/youth5201314/banner) 基础上
增加布局设置，可以自定义 adapter 中的 view

## 效果图

![image](/pic/1.gif )

## 使用步骤

#### Step 1.依赖banner [![Download](https://api.bintray.com/packages/wenchaosong/maven/Banner/images/download.svg)](https://bintray.com/wenchaosong/maven/Banner/_latestVersion)

Gradle
```groovy
dependencies{
    compile 'com.github.wenchaosong:banner:2.1.3'  //最新版本
}
```
或者引用本地lib
```groovy
compile project(':rollbanner')
```

#### Step 2.在布局文件中添加Banner，可以设置自定义属性
！！！此步骤可以省略，直接在Activity或者Fragment中new Banner();
```xml
<com.ms.banner.Banner
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/banner"
    android:layout_width="match_parent"
    android:layout_height="高度自己设置" />
```

#### Step 4.设置布局
```java
.setPages(list, new HolderCreator<BannerViewHolder>() {
                    @Override
                    public BannerViewHolder createViewHolder() {
                        return new CustomViewHolder();
                    }
                })

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

#### Step 5.在Activity或者Fragment中配置Banner

- 注意！start()方法必须放到最后执行，点击事件请放到start()前

```java
--------------------------简单使用-------------------------------
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Banner banner = (Banner) findViewById(R.id.banner);
    banner.setAutoPlay(true)
            .setPages(list, new HolderCreator<BannerViewHolder>() {
                @Override
                public BannerViewHolder createViewHolder() {
                    return new CustomViewHolder();
                }
            })
            .start();
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

