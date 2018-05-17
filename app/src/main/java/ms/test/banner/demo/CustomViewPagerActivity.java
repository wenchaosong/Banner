package ms.test.banner.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ms.banner.Banner;
import com.ms.banner.holder.BannerViewHolder;
import com.ms.banner.holder.HolderCreator;
import com.ms.banner.listener.OnBannerListener;
import com.test.banner.R;

import java.util.ArrayList;
import java.util.List;

import ms.test.banner.ui.CustomViewHolder2;

public class CustomViewPagerActivity extends AppCompatActivity implements OnBannerListener {

    Banner banner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_pager);

        banner1 = (Banner) findViewById(R.id.banner1);

        List<String> list = new ArrayList<>();
        list.add("#0000ff");
        list.add("#ff0000");
        list.add("#00ff00");

        banner1.setAutoPlay(false)
                .setPages(list, new HolderCreator<BannerViewHolder>() {
                    @Override
                    public BannerViewHolder createViewHolder() {
                        return new CustomViewHolder2();
                    }
                })
                .setOnBannerListener(this)
                .start();
    }

    @Override
    public void onBannerClick(int position) {

    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner1.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner1.stopAutoPlay();
    }
}
