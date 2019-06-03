package com.test;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App extends Application {
    public static List<?> images = new ArrayList<>();
    public static List<String> titles = new ArrayList<>();
    public static int H, W;
    public static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        getScreen(this);
        String[] urls = getResources().getStringArray(R.array.url);
        String[] tips = getResources().getStringArray(R.array.title);
        List list = Arrays.asList(urls);
        images = new ArrayList(list);
        List list1 = Arrays.asList(tips);
        titles = new ArrayList(list1);
    }

    public void getScreen(Context aty) {
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();
        H = dm.heightPixels;
        W = dm.widthPixels;
    }
}
