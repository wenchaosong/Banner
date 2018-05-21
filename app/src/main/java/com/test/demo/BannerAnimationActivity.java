package com.test.demo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ms.banner.Banner;
import com.ms.banner.Transformer;
import com.ms.banner.holder.BannerViewHolder;
import com.ms.banner.holder.HolderCreator;
import com.ms.banner.listener.OnBannerListener;
import com.test.App;
import com.test.R;
import com.test.SampleAdapter;
import com.test.ui.CustomViewHolder;

import java.util.ArrayList;
import java.util.List;

public class BannerAnimationActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,
        OnBannerListener {

    Banner banner;
    List<Class<? extends ViewPager.PageTransformer>> transformers = new ArrayList<>();

    public void initData() {
        transformers.add(Transformer.Default);
        transformers.add(Transformer.Accordion);
        transformers.add(Transformer.BackgroundToForeground);
        transformers.add(Transformer.ForegroundToBackground);
        transformers.add(Transformer.CubeIn);//兼容问题，慎用
        transformers.add(Transformer.CubeOut);
        transformers.add(Transformer.DepthPage);
        transformers.add(Transformer.FlipHorizontal);
        transformers.add(Transformer.FlipVertical);
        transformers.add(Transformer.RotateDown);
        transformers.add(Transformer.RotateUp);
        transformers.add(Transformer.ScaleInOut);
        transformers.add(Transformer.Scale);
        transformers.add(Transformer.Stack);
        transformers.add(Transformer.Tablet);
        transformers.add(Transformer.ZoomIn);
        transformers.add(Transformer.ZoomOut);
        transformers.add(Transformer.ZoomOutSlide);
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

        //简单使用
        banner.setOnBannerListener(this)
                .setPages(App.images, new HolderCreator<BannerViewHolder>() {
                    @Override
                    public BannerViewHolder createViewHolder() {
                        return new CustomViewHolder();
                    }
                })
                .setAutoPlay(true)
                .setDelayTime(3000)
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
