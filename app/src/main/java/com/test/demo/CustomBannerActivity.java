package com.test.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ms.banner.Banner;
import com.ms.banner.BannerConfig;
import com.test.App;
import com.test.R;
import com.test.ui.CustomViewHolder;

public class CustomBannerActivity extends AppCompatActivity {

    Banner banner1, banner2, banner3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_banner);
        banner1 = (Banner) findViewById(R.id.banner1);
        banner2 = (Banner) findViewById(R.id.banner2);
        banner3 = (Banner) findViewById(R.id.banner3);

        banner1.setAutoPlay(true)
                .setPages(App.images, new CustomViewHolder())
                .start();

        banner2.setAutoPlay(true)
                .setPages(App.images, new CustomViewHolder())
                .start();

        banner3.setBannerTitles(App.titles)
                .setAutoPlay(true)
                .setPages(App.images, new CustomViewHolder())
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .start();
    }
}
