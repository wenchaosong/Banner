package com.test.banner.demo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.banner.Banner;
import com.banner.listener.OnBannerListener;
import com.banner.transformer.AccordionTransformer;
import com.banner.transformer.BackgroundToForegroundTransformer;
import com.banner.transformer.CubeInTransformer;
import com.banner.transformer.CubeOutTransformer;
import com.banner.transformer.DefaultTransformer;
import com.banner.transformer.DepthPageTransformer;
import com.banner.transformer.FlipHorizontalTransformer;
import com.banner.transformer.FlipVerticalTransformer;
import com.banner.transformer.ForegroundToBackgroundTransformer;
import com.banner.transformer.RotateDownTransformer;
import com.banner.transformer.RotateUpTransformer;
import com.banner.transformer.ScaleInOutTransformer;
import com.banner.transformer.StackTransformer;
import com.banner.transformer.TabletTransformer;
import com.banner.transformer.ZoomInTransformer;
import com.banner.transformer.ZoomOutSlideTransformer;
import com.banner.transformer.ZoomOutTranformer;
import com.test.banner.App;
import com.test.banner.R;
import com.test.banner.SampleAdapter;
import com.test.banner.loader.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

public class BannerAnimationActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, OnBannerListener {

    Banner banner;
    List<Class<? extends ViewPager.PageTransformer>> transformers = new ArrayList<>();

    public void initData() {
        transformers.add(DefaultTransformer.class);
        transformers.add(AccordionTransformer.class);
        transformers.add(BackgroundToForegroundTransformer.class);
        transformers.add(ForegroundToBackgroundTransformer.class);
        transformers.add(CubeInTransformer.class);//兼容问题，慎用
        transformers.add(CubeOutTransformer.class);
        transformers.add(DepthPageTransformer.class);
        transformers.add(FlipHorizontalTransformer.class);
        transformers.add(FlipVerticalTransformer.class);
        transformers.add(RotateDownTransformer.class);
        transformers.add(RotateUpTransformer.class);
        transformers.add(ScaleInOutTransformer.class);
        transformers.add(StackTransformer.class);
        transformers.add(TabletTransformer.class);
        transformers.add(ZoomInTransformer.class);
        transformers.add(ZoomOutTranformer.class);
        transformers.add(ZoomOutSlideTransformer.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_animation);
        initData();
        banner = (Banner) findViewById(R.id.banner);
        ListView listView = (ListView) findViewById(R.id.list);
        String[] data = getResources().getStringArray(R.array.anim);
        listView.setAdapter(new SampleAdapter(this, data));
        listView.setOnItemClickListener(this);

        banner.setImages(App.images)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(this)
                .start();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        banner.setBannerAnimation(transformers.get(position));
    }

    @Override
    public void onBannerClick(int position) {
        Toast.makeText(getApplicationContext(), "你点击了：" + position, Toast.LENGTH_SHORT).show();
    }
}
