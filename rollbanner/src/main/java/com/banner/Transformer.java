package com.banner;

import android.support.v4.view.ViewPager.PageTransformer;

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

public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
