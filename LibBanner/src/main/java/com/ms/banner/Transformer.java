package com.ms.banner;

import android.support.v4.view.ViewPager.PageTransformer;

import com.ms.banner.transformer.AccordionTransformer;
import com.ms.banner.transformer.BackgroundToForegroundTransformer;
import com.ms.banner.transformer.CubeInTransformer;
import com.ms.banner.transformer.CubeOutTransformer;
import com.ms.banner.transformer.DefaultTransformer;
import com.ms.banner.transformer.DepthPageTransformer;
import com.ms.banner.transformer.FlipHorizontalTransformer;
import com.ms.banner.transformer.FlipVerticalTransformer;
import com.ms.banner.transformer.ForegroundToBackgroundTransformer;
import com.ms.banner.transformer.RotateDownTransformer;
import com.ms.banner.transformer.RotateUpTransformer;
import com.ms.banner.transformer.ScaleInOutTransformer;
import com.ms.banner.transformer.ScaleRightTransformer;
import com.ms.banner.transformer.ScaleTransformer;
import com.ms.banner.transformer.StackTransformer;
import com.ms.banner.transformer.TabletTransformer;
import com.ms.banner.transformer.ZoomInTransformer;
import com.ms.banner.transformer.ZoomOutSlideTransformer;
import com.ms.banner.transformer.ZoomOutTransformer;

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
    public static Class<? extends PageTransformer> Scale = ScaleTransformer.class;
    public static Class<? extends PageTransformer> ScaleRight = ScaleRightTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTransformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;

}
