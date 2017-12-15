package com.test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.banner.BannerView;
import com.banner.holder.BannerHolderCreator;
import com.banner.holder.BannerViewHolder;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {

    private BannerView mBannerView;
    private Integer[] images = {R.mipmap.banner1, R.mipmap.banner2, R.mipmap.banner3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }

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
}
