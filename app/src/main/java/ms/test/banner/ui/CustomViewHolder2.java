package ms.test.banner.ui;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;

import com.ms.banner.holder.BannerViewHolder;
import com.test.banner.R;

/**
 * Created by songwenchao
 * on 2018/5/17 0017.
 * <p>
 * 类名
 * 需要 --
 * 可以 --
 */
public class CustomViewHolder2 implements BannerViewHolder<String> {

    private CardView mCardView;

    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
        mCardView = (CardView) view.findViewById(R.id.group);
        return view;
    }

    @Override
    public void onBind(Context context, int position, String data) {
        // 数据绑定
        mCardView.setCardBackgroundColor(Color.parseColor(data));
    }
}
