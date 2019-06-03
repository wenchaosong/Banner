package com.test.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.ms.banner.Banner;
import com.ms.banner.BannerConfig;
import com.test.App;
import com.test.R;
import com.test.ui.CustomViewHolder;

public class IndicatorPositionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Banner banner;
    Spinner spinnerPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicator_position);
        banner = (Banner) findViewById(R.id.banner);
        spinnerPosition = (Spinner) findViewById(R.id.spinnerPosition);
        spinnerPosition.setOnItemSelectedListener(this);

        banner.setAutoPlay(true)
                .setDelayTime(3000)
                .setPages(App.images, new CustomViewHolder())
                .start();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                banner.setIndicatorGravity(BannerConfig.LEFT);
                break;
            case 1:
                banner.setIndicatorGravity(BannerConfig.CENTER);
                break;
            case 2:
                banner.setIndicatorGravity(BannerConfig.RIGHT);
                break;
        }
        banner.start();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
