package com.zhifeng.cattle.adapters;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.lgh.huanglib.util.config.GlideApp;
import com.zhifeng.cattle.R;

import cn.bingoogolapple.bgabanner.BGABanner;

public class BannerHome2 implements BGABanner.Adapter<ImageView, String> {


    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
//        itemView.setScaleType(ImageView.ScaleType.FIT_XY);
        ViewGroup.LayoutParams params = banner.getLayoutParams();
        params.height = (int) (banner.getWidth()*(0.4f));
        banner.setLayoutParams(params);
        GlideApp.with(itemView.getContext()).load(model).dontAnimate()
                .error(R.drawable.icon_selfnav_banner)
                .override(banner.getWidth(), (int) (banner.getWidth()*(0.4f)))
                .into(itemView);
    }

}
