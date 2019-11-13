package com.ms.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ms.banner.holder.BannerViewHolder;
import com.ms.banner.listener.OnBannerClickListener;
import com.ms.banner.view.ArcShapeView;
import com.ms.banner.view.BannerViewPager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.IntRange;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import androidx.viewpager.widget.ViewPager.PageTransformer;

public class Banner extends FrameLayout implements OnPageChangeListener {

    private int mIndicatorPadding = BannerConfig.PADDING_SIZE;
    private int mIndicatorMargin = BannerConfig.MARGIN_BOTTOM;
    private int mIndicatorWidth;
    private int mIndicatorHeight;
    private int indicatorSize;
    private int bannerBackgroundImage;
    private int bannerStyle = BannerConfig.CIRCLE_INDICATOR;
    private int delayTime = BannerConfig.TIME;
    private int scrollTime = BannerConfig.DURATION;
    private boolean isAutoPlay = BannerConfig.IS_AUTO_PLAY;
    private boolean isStart = false;
    private boolean isPrepare = false;
    private boolean isScroll = BannerConfig.IS_SCROLL;
    private boolean isLoop = BannerConfig.IS_LOOP;
    private int mIndicatorSelectedResId = R.drawable.gray_radius;
    private int mIndicatorUnselectedResId = R.drawable.white_radius;
    private Drawable mIndicatorSelectedDrawable;
    private Drawable mIndicatorUnselectedDrawable;
    private int titleHeight;
    private int titleBackground;
    private int titleTextColor;
    private int titleTextSize;
    private int count = 0;
    private int currentItem = -1;
    private int mCurrentPage = 0;
    private int gravity = -1;
    private int lastPosition;
    private List<String> titles;
    private List mDatas;
    private BannerViewHolder creator;
    private List<ImageView> indicatorImages;
    private Context context;
    private BannerViewPager viewPager;
    private TextView bannerTitle, numIndicatorInside, numIndicator;
    private LinearLayout indicator, indicatorInside, titleView;
    private ImageView bannerDefaultImage;
    private BannerPagerAdapter adapter;
    private OnPageChangeListener mOnPageChangeListener;
    private OnBannerClickListener listener;
    private int mPageLeftMargin;
    private int mPageRightMargin;
    private int mArcHeight;
    private int mArcStartColor, mArcEndColor;
    private int mArcDirection;
    private static final int NUM = 5000;
    private WeakHandler handler = new WeakHandler();

    public Banner(Context context) {
        this(context, null);
    }

    public Banner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Banner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        titles = new ArrayList<>();
        mDatas = new ArrayList<>();
        indicatorImages = new ArrayList<>();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        indicatorSize = dm.widthPixels / 80;
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        handleTypedArray(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.banner, this, true);
        bannerDefaultImage = view.findViewById(R.id.bannerDefaultImage);
        ArcShapeView arcShapeView = view.findViewById(R.id.bannerArcView);
        if (mArcHeight <= 0) {
            arcShapeView.setVisibility(GONE);
        } else {
            arcShapeView.setVisibility(VISIBLE);
            arcShapeView.setArcHeight(mArcHeight);
            arcShapeView.setBackground(mArcStartColor, mArcEndColor);
            arcShapeView.setDirection(mArcDirection);
        }
        viewPager = view.findViewById(R.id.bannerViewPager);
        viewPager.setPadding(mPageLeftMargin, 0, mPageRightMargin, 0);
        titleView = view.findViewById(R.id.titleView);
        indicator = view.findViewById(R.id.circleIndicator);
        RelativeLayout.LayoutParams indicatorParam = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        indicatorParam.bottomMargin = mIndicatorMargin;
        indicator.setLayoutParams(indicatorParam);
        indicatorInside = view.findViewById(R.id.indicatorInside);
        bannerTitle = view.findViewById(R.id.bannerTitle);
        numIndicator = view.findViewById(R.id.numIndicator);
        numIndicatorInside = view.findViewById(R.id.numIndicatorInside);
        bannerDefaultImage.setImageResource(bannerBackgroundImage);
        initViewPagerScroll();
    }

    private void handleTypedArray(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Banner);
        mIndicatorWidth = typedArray.getDimensionPixelSize(R.styleable.Banner_indicator_width, indicatorSize);
        mIndicatorHeight = typedArray.getDimensionPixelSize(R.styleable.Banner_indicator_height, indicatorSize);
        mIndicatorPadding = typedArray.getDimensionPixelSize(R.styleable.Banner_indicator_padding, BannerConfig.PADDING_SIZE);
        mIndicatorMargin = typedArray.getDimensionPixelSize(R.styleable.Banner_indicator_margin, BannerConfig.MARGIN_BOTTOM);
        mIndicatorSelectedResId = typedArray.getResourceId(R.styleable.Banner_indicator_drawable_selected, R.drawable.gray_radius);
        mIndicatorUnselectedResId = typedArray.getResourceId(R.styleable.Banner_indicator_drawable_unselected, R.drawable.white_radius);
        delayTime = typedArray.getInt(R.styleable.Banner_delay_time, BannerConfig.TIME);
        scrollTime = typedArray.getInt(R.styleable.Banner_scroll_time, BannerConfig.DURATION);
        isAutoPlay = typedArray.getBoolean(R.styleable.Banner_is_auto_play, BannerConfig.IS_AUTO_PLAY);
        isLoop = typedArray.getBoolean(R.styleable.Banner_is_loop, BannerConfig.IS_LOOP);
        titleBackground = typedArray.getColor(R.styleable.Banner_title_background, BannerConfig.TITLE_BACKGROUND);
        titleHeight = typedArray.getDimensionPixelSize(R.styleable.Banner_title_height, BannerConfig.TITLE_HEIGHT);
        titleTextColor = typedArray.getColor(R.styleable.Banner_title_textcolor, BannerConfig.TITLE_TEXT_COLOR);
        titleTextSize = typedArray.getDimensionPixelSize(R.styleable.Banner_title_textsize, BannerConfig.TITLE_TEXT_SIZE);
        bannerBackgroundImage = typedArray.getResourceId(R.styleable.Banner_banner_default_image, R.drawable.no_banner);
        mPageLeftMargin = typedArray.getDimensionPixelSize(R.styleable.Banner_page_left_margin, BannerConfig.PAGE_MARGIN);
        mPageRightMargin = typedArray.getDimensionPixelSize(R.styleable.Banner_page_right_margin, BannerConfig.PAGE_MARGIN);
        mArcHeight = typedArray.getDimensionPixelSize(R.styleable.Banner_arc_height, BannerConfig.ARC_HEIGHT);
        mArcStartColor = typedArray.getColor(R.styleable.Banner_arc_start_color, BannerConfig.ARC_BACKGROUND);
        mArcEndColor = typedArray.getColor(R.styleable.Banner_arc_end_color, BannerConfig.ARC_BACKGROUND);
        mArcDirection = typedArray.getInt(R.styleable.Banner_arc_direction, BannerConfig.ARC_DIRECTION);
        typedArray.recycle();
    }

    private void initViewPagerScroll() {
        try {
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            BannerScroller scroller = new BannerScroller(viewPager.getContext());
            scroller.setDuration(scrollTime);
            mField.set(viewPager, scroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Banner setAutoPlay(boolean isAutoPlay) {
        this.isAutoPlay = isAutoPlay;
        return this;
    }

    public Banner setLoop(boolean isLoop) {
        this.isLoop = isLoop;
        return this;
    }

    public Banner setDelayTime(int delayTime) {
        this.delayTime = delayTime;
        return this;
    }

    public Banner setIndicatorGravity(int type) {
        switch (type) {
            case BannerConfig.LEFT:
                this.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
                break;
            case BannerConfig.CENTER:
                this.gravity = Gravity.CENTER;
                break;
            case BannerConfig.RIGHT:
                this.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
                break;
        }
        return this;
    }

    public Banner setBannerAnimation(Class<? extends PageTransformer> transformer) {
        try {
            viewPager.setPageTransformer(true, transformer.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public Banner setPageTransformer(boolean reverseDrawingOrder, PageTransformer transformer) {
        viewPager.setPageTransformer(reverseDrawingOrder, transformer);
        return this;
    }

    public Banner setBannerTitles(List<String> titles) {
        this.titles = titles;
        return this;
    }

    public Banner setBannerStyle(int bannerStyle) {
        this.bannerStyle = bannerStyle;
        return this;
    }

    public Banner setViewPagerIsScroll(boolean isScroll) {
        this.isScroll = isScroll;
        return this;
    }

    public Banner setCurrentPage(@IntRange(from = 0) int page) {
        mCurrentPage = page;
        return this;
    }

    public Banner setPages(List<?> datas, BannerViewHolder creator) {
        this.mDatas.clear();
        this.mDatas.addAll(datas);
        this.creator = creator;
        this.count = datas.size();
        return this;
    }

    public void update(List<?> imageUrls, List<String> titles) {
        if (imageUrls == null || titles == null || imageUrls.size() != titles.size()) {
            update(null);
            return;
        }
        this.titles.clear();
        this.titles.addAll(titles);
        update(imageUrls);
    }

    public void update(List<?> imageUrls) {
        if (imageUrls == null) {
            imageUrls = new ArrayList<>();
        }
        this.mDatas.clear();
        this.indicatorImages.clear();
        if (imageUrls.size() == 0) {
            bannerDefaultImage.setVisibility(VISIBLE);
            this.count = 0;
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        } else {
            this.mDatas.addAll(imageUrls);
            this.count = this.mDatas.size();
            start();
        }
    }

    public void updateBannerStyle(int bannerStyle) {
        indicator.setVisibility(GONE);
        numIndicator.setVisibility(GONE);
        numIndicatorInside.setVisibility(GONE);
        indicatorInside.setVisibility(GONE);
        bannerTitle.setVisibility(View.GONE);
        titleView.setVisibility(View.GONE);
        this.bannerStyle = bannerStyle;
        start();
    }

    public Banner start() {
        if (count > 0) {
            setStyleUI();
            setImageList();
            setData();
        } else {
            bannerDefaultImage.setVisibility(VISIBLE);
        }
        isPrepare = true;
        return this;
    }

    public boolean isPrepare() {
        return isPrepare;
    }

    public boolean isStart() {
        return isStart;
    }

    public Banner setIndicatorRes(int select, int unSelect) {
        if (select < 0)
            throw new RuntimeException("[Banner] --> The select res is not exist");
        if (unSelect < 0)
            throw new RuntimeException("[Banner] --> The unSelect res is not exist");

        mIndicatorSelectedResId = select;
        mIndicatorUnselectedResId = unSelect;
        return this;
    }

    public Banner setIndicatorRes(Drawable select, Drawable unSelect) {
        if (select == null || unSelect == null)
            throw new RuntimeException("[Banner] --> The Drawable res is null");

        mIndicatorSelectedDrawable = select;
        mIndicatorUnselectedDrawable = unSelect;
        return this;
    }

    private void setStyleUI() {
        int visibility = count > 1 ? View.VISIBLE : View.GONE;
        switch (bannerStyle) {
            case BannerConfig.CIRCLE_INDICATOR:
                indicator.setVisibility(visibility);
                break;
            case BannerConfig.CUSTOM_INDICATOR:
                indicator.setVisibility(visibility);
                break;
            case BannerConfig.NUM_INDICATOR:
                numIndicator.setVisibility(visibility);
                break;
            case BannerConfig.NUM_INDICATOR_TITLE:
                numIndicatorInside.setVisibility(visibility);
                setTitleStyleUI();
                break;
            case BannerConfig.CIRCLE_INDICATOR_TITLE:
                indicator.setVisibility(visibility);
                setTitleStyleUI();
                break;
            case BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE:
                indicatorInside.setVisibility(visibility);
                setTitleStyleUI();
                break;
        }
    }

    private void setTitleStyleUI() {
        if (titles.size() != mDatas.size()) {
            throw new RuntimeException("[Banner] --> The number of titles and images is different");
        }
        if (titleBackground != -1) {
            titleView.setBackgroundColor(titleBackground);
        }
        if (titleHeight != -1) {
            titleView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, titleHeight));
        }
        if (titleTextColor != -1) {
            bannerTitle.setTextColor(titleTextColor);
        }
        if (titleTextSize != -1) {
            bannerTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);
        }
        if (titles != null && titles.size() > 0) {
            bannerTitle.setText(titles.get(0));
            bannerTitle.setVisibility(View.VISIBLE);
            titleView.setVisibility(View.VISIBLE);
        }
    }

    private void setImageList() {
        bannerDefaultImage.setVisibility(GONE);

        if (bannerStyle == BannerConfig.CIRCLE_INDICATOR ||
                bannerStyle == BannerConfig.CIRCLE_INDICATOR_TITLE ||
                bannerStyle == BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE ||
                bannerStyle == BannerConfig.CUSTOM_INDICATOR) {
            createIndicator();
        } else if (bannerStyle == BannerConfig.NUM_INDICATOR_TITLE) {
            numIndicatorInside.setText("1/" + count);
        } else if (bannerStyle == BannerConfig.NUM_INDICATOR) {
            numIndicator.setText("1/" + count);
        }
    }

    private void createIndicator() {
        indicatorImages.clear();
        indicator.removeAllViews();
        indicatorInside.removeAllViews();
        for (int i = 0; i < count; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mIndicatorWidth, mIndicatorHeight);
            params.leftMargin = mIndicatorPadding;
            params.rightMargin = mIndicatorPadding;
            LinearLayout.LayoutParams custom_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            custom_params.leftMargin = mIndicatorPadding;
            custom_params.rightMargin = mIndicatorPadding;
            if (i == 0) {
                if (mIndicatorSelectedDrawable != null) {
                    imageView.setImageDrawable(mIndicatorSelectedDrawable);
                } else {
                    imageView.setImageResource(mIndicatorSelectedResId);
                }
            } else {
                if (mIndicatorUnselectedDrawable != null) {
                    imageView.setImageDrawable(mIndicatorUnselectedDrawable);
                } else {
                    imageView.setImageResource(mIndicatorUnselectedResId);
                }
            }
            indicatorImages.add(imageView);
            if (bannerStyle == BannerConfig.CIRCLE_INDICATOR ||
                    bannerStyle == BannerConfig.CIRCLE_INDICATOR_TITLE)
                indicator.addView(imageView, params);
            else if (bannerStyle == BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                indicatorInside.addView(imageView, params);
            else if (bannerStyle == BannerConfig.CUSTOM_INDICATOR)
                indicator.addView(imageView, custom_params);
        }
        if (gravity != -1)
            indicator.setGravity(gravity);
    }

    private void setData() {
        if (isLoop) {
            if (mCurrentPage > 0 && mCurrentPage < count) {
                currentItem = NUM / 2 - ((NUM / 2) % count) + 1 + mCurrentPage;
            } else {
                currentItem = NUM / 2 - ((NUM / 2) % count) + 1;
            }
            lastPosition = 1;
        } else {
            if (mCurrentPage > 0 && mCurrentPage < count) {
                currentItem = mCurrentPage;
            } else {
                currentItem = 0;
            }
            lastPosition = 0;
        }
        if (adapter == null) {
            adapter = new BannerPagerAdapter();
            viewPager.addOnPageChangeListener(this);
        }
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(count);
        viewPager.setCurrentItem(currentItem);
        if (isScroll && count > 1) {
            viewPager.setScrollable(true);
        } else {
            viewPager.setScrollable(false);
        }
        startAutoPlay();
    }

    public void startAutoPlay() {
        if (isAutoPlay) {
            handler.removeCallbacks(task);
            handler.postDelayed(task, delayTime);
            isStart = true;
        }
    }

    public void stopAutoPlay() {
        if (isAutoPlay) {
            handler.removeCallbacks(task);
            isStart = false;
        }
    }

    private final Runnable task = new Runnable() {
        @Override
        public void run() {
            if (count > 1) {
                currentItem = viewPager.getCurrentItem() + 1;
                if (isLoop) {
                    if (currentItem == adapter.getCount() - 1) {
                        currentItem = 0;
                        viewPager.setCurrentItem(currentItem, false);
                        handler.post(task);
                    } else {
                        viewPager.setCurrentItem(currentItem);
                        handler.postDelayed(task, delayTime);
                    }
                } else {
                    if (currentItem >= adapter.getCount()) {
                        stopAutoPlay();
                    } else {
                        viewPager.setCurrentItem(currentItem);
                        handler.postDelayed(task, delayTime);
                    }
                }
            }
        }
    };

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_OUTSIDE:
                startAutoPlay();
                break;
            case MotionEvent.ACTION_DOWN:
                stopAutoPlay();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private int toRealPosition(int position) {
        //int realPosition = (position - 1) % count;
        int realPosition;
        if (count <= 0)
            return 0;
        if (isLoop) {
            realPosition = (position - 1 + count) % count;
        } else {
            realPosition = (position + count) % count;
        }
        if (realPosition < 0)
            realPosition += count;
        return realPosition;
    }

    private class BannerPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            if (mDatas.size() == 1) {
                return mDatas.size();
            } else if (mDatas.size() < 1) {
                return 0;
            } else {
                if (isLoop)
                    //return mDatas.size() + 2;
                    return NUM;
                else
                    return mDatas.size();
            }
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            if (creator == null) {
                throw new RuntimeException("[Banner] --> The layout is not specified,please set holder");
            }
            View view = creator.createView(container.getContext(), toRealPosition(position), mDatas.get(toRealPosition(position)));
            container.addView(view);

            if (listener != null) {
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onBannerClick(mDatas, toRealPosition(position));
                    }
                });
            }
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageScrollStateChanged(state);
        }
        if (!isLoop)
            return;
        switch (state) {
            case 0://No operation
                /*if (currentItem == 0) {
                    viewPager.setCurrentItem(count, false);
                } else if (currentItem == count + 1) {
                    viewPager.setCurrentItem(1, false);
                }*/
                /*if (currentItem == 0) {
                    viewPager.setCurrentItem(NUM - 1, false);
                } else if (currentItem == NUM - 1) {
                    viewPager.setCurrentItem(0, false);
                }*/
                break;
            case 1://start Sliding
                break;
            case 2://end Sliding
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageScrolled(toRealPosition(position), positionOffset, positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        currentItem = position;
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageSelected(toRealPosition(position));
        }
        if (bannerStyle == BannerConfig.CIRCLE_INDICATOR ||
                bannerStyle == BannerConfig.CIRCLE_INDICATOR_TITLE ||
                bannerStyle == BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE ||
                bannerStyle == BannerConfig.CUSTOM_INDICATOR) {
            if (isLoop) {
                if (mIndicatorSelectedDrawable != null && mIndicatorUnselectedDrawable != null) {
                    indicatorImages.get((lastPosition - 1 + count) % count).setImageDrawable(mIndicatorUnselectedDrawable);
                    indicatorImages.get((position - 1 + count) % count).setImageDrawable(mIndicatorSelectedDrawable);
                } else {
                    indicatorImages.get((lastPosition - 1 + count) % count).setImageResource(mIndicatorUnselectedResId);
                    indicatorImages.get((position - 1 + count) % count).setImageResource(mIndicatorSelectedResId);
                }
            } else {
                if (mIndicatorSelectedDrawable != null && mIndicatorUnselectedDrawable != null) {
                    indicatorImages.get((lastPosition + count) % count).setImageDrawable(mIndicatorUnselectedDrawable);
                    indicatorImages.get((toRealPosition(position) + count) % count).setImageDrawable(mIndicatorSelectedDrawable);
                } else {
                    indicatorImages.get((lastPosition + count) % count).setImageResource(mIndicatorUnselectedResId);
                    indicatorImages.get((toRealPosition(position) + count) % count).setImageResource(mIndicatorSelectedResId);
                }
            }
            lastPosition = position;
        }
        /*if (position == 0)
            position = count;
        if (position > count)
            position = 1;*/

        switch (bannerStyle) {
            case BannerConfig.CIRCLE_INDICATOR:
                break;
            case BannerConfig.CUSTOM_INDICATOR:
                break;
            case BannerConfig.NUM_INDICATOR:
                //numIndicator.setText(position + "/" + count);
                numIndicator.setText((toRealPosition(position) + 1) + "/" + count);
                break;
            case BannerConfig.NUM_INDICATOR_TITLE:
                //numIndicatorInside.setText(position + "/" + count);
                //bannerTitle.setText(titles.get(position - 1));
                numIndicatorInside.setText((toRealPosition(position) + 1) + "/" + count);
                bannerTitle.setText(titles.get(toRealPosition(position)));
                break;
            case BannerConfig.CIRCLE_INDICATOR_TITLE:
                //bannerTitle.setText(titles.get(position - 1));
                bannerTitle.setText(titles.get(toRealPosition(position)));
                break;
            case BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE:
                //bannerTitle.setText(titles.get(position - 1));
                bannerTitle.setText(titles.get(toRealPosition(position)));
                break;
        }

    }

    public Banner setOnBannerClickListener(OnBannerClickListener listener) {
        this.listener = listener;
        return this;
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        mOnPageChangeListener = onPageChangeListener;
    }

    public void releaseBanner() {
        handler.removeCallbacksAndMessages(null);
    }
}
