package com.test.banner.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.banner.Banner;
import com.test.banner.R;
import com.test.banner.loader.GlideImageLoader;
import com.test.banner.ui.CustomTransformer;

import java.util.ArrayList;
import java.util.List;

public class BannerLocalActivity extends AppCompatActivity {

    Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_local);
        initView();
    }

    private void initView() {
        banner = (Banner) findViewById(R.id.banner);
        //本地图片数据（资源文件）
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.b1);
        list.add(R.mipmap.b2);
        list.add(R.mipmap.b3);

        banner.setImages(list)
                .setPageTransformer(false, new CustomTransformer())
                .setImageLoader(new GlideImageLoader())
                .start();
    }
}
