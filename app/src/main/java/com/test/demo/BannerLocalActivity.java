package com.test.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ms.banner.Banner;
import com.test.R;
import com.test.ui.CustomViewHolder;

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
        list.add(R.mipmap.b2);
        list.add(R.mipmap.b1);
        list.add(R.mipmap.b3);

        banner.setAutoPlay(true)
                .setPages(list, new CustomViewHolder())
                .start();
    }
}
