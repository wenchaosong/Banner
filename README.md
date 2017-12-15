# 轮播图

## 使用

在 gradle 添加: 
```
dependencies {
    compile 'com.github.wenchaosong:banner:2.0.0'
}
```

        mBannerView = (BannerView) findViewById(R.id.banner);

        mBannerView.setIndicatorVisible(false);
        mBannerView.setDelayedTime(3000);
        mBannerView.setDuration(1500);
        mBannerView.setBannerPageClickListener(new BannerView.BannerPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {

            }
        });

        // 最后调用
        mBannerView.setPages(Arrays.asList(images), new BannerHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new MyViewHolder();
            }
        });
        
        
        public static class MyViewHolder implements BannerViewHolder<Integer> {

        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = (ImageView) view.findViewById(R.id.image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, Integer data) {
            // 数据绑定
            Glide.with(context).load(data).into(mImageView);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        mBannerView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mBannerView.pause();
    }

    <com.banner.BannerView
            android:id="@+id/banner"
            android:layout_width="match_parent"
            android:layout_height="160dp"
            android:paddingBottom="10dp"
            android:paddingTop="10dp"
            app:canLoop="true"
            app:middle_page_cover="false"
            app:open_scale_mode="true"
            app:pageMargin="20dp"
            app:scale="0.1" />

