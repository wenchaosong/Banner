package ms.test.banner.demo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ms.banner.Banner;
import com.ms.banner.listener.OnBannerListener;
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
import com.ms.banner.transformer.StackTransformer;
import com.ms.banner.transformer.TabletTransformer;
import com.ms.banner.transformer.ZoomInTransformer;
import com.ms.banner.transformer.ZoomOutSlideTransformer;
import com.ms.banner.transformer.ZoomOutTranformer;
import com.test.banner.R;

import java.util.ArrayList;
import java.util.List;

import ms.test.banner.SampleAdapter;

public class BannerAnimationActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, OnBannerListener {

    Banner banner;
    List<Class<? extends ViewPager.PageTransformer>> transformers = new ArrayList<>();

    public void initData() {
        transformers.add(DefaultTransformer.class);
        transformers.add(AccordionTransformer.class);
        transformers.add(BackgroundToForegroundTransformer.class);
        transformers.add(ForegroundToBackgroundTransformer.class);
        transformers.add(CubeInTransformer.class);//兼容问题，慎用
        transformers.add(CubeOutTransformer.class);
        transformers.add(DepthPageTransformer.class);
        transformers.add(FlipHorizontalTransformer.class);
        transformers.add(FlipVerticalTransformer.class);
        transformers.add(RotateDownTransformer.class);
        transformers.add(RotateUpTransformer.class);
        transformers.add(ScaleInOutTransformer.class);
        transformers.add(StackTransformer.class);
        transformers.add(TabletTransformer.class);
        transformers.add(ZoomInTransformer.class);
        transformers.add(ZoomOutTranformer.class);
        transformers.add(ZoomOutSlideTransformer.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_animation);
        initData();
        banner = (Banner) findViewById(R.id.banner);
        ListView listView = (ListView) findViewById(R.id.list);
        String[] data = getResources().getStringArray(R.array.anim);
        listView.setAdapter(new SampleAdapter(this, data));
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        banner.setBannerAnimation(transformers.get(position));
    }

    @Override
    public void onBannerClick(int position) {
        Toast.makeText(getApplicationContext(), "你点击了：" + position, Toast.LENGTH_SHORT).show();
    }
}
