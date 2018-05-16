package ms.test.banner.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ms.banner.Banner;
import com.test.banner.R;

public class CustomBannerActivity extends AppCompatActivity {

    Banner banner1, banner2, banner3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_banner);
        banner1 = (Banner) findViewById(R.id.banner1);
        banner2 = (Banner) findViewById(R.id.banner2);
        banner3 = (Banner) findViewById(R.id.banner3);
    }
}
