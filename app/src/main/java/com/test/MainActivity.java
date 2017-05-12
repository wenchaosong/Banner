package com.test;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private String[] url = {"http://api.hzg9999.com/vw/images/advert_demo1.jpg",
            "http://api.hzg9999.com/vw/images/advert_demo2.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RollImageBanner rollImageBanner = (RollImageBanner) findViewById(R.id.yt_sib_anim);

        ArrayList<BannerItem> list = new ArrayList<>();

        for (String anUrl : url) {

            BannerItem item = new BannerItem();
            item.picUrl = anUrl;

            list.add(item);
        }

        if (list.size() == 0)
            return;

        rollImageBanner.setSelectAnimClass(ZoomInEnter.class)
                .setSource(list)
                .setDelay(3)
                .setPeriod(3)
                .startScroll();
    }
}
