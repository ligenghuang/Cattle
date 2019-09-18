package com.zhifeng.cattle.adapters;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.lgh.huanglib.util.config.GlideApp;
import com.zhifeng.cattle.R;

import cn.bingoogolapple.bgabanner.BGABanner;

public class BannerGoods implements BGABanner.Adapter<ImageView, String> {


    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
        itemView.setScaleType(ImageView.ScaleType.FIT_XY);
        ViewGroup.LayoutParams params = banner.getLayoutParams();
//        params.height = (int) (banner.getWidth()*(0.56f));
//        banner.setLayoutParams(params);
        GlideApp.with(itemView.getContext()).load(model).dontAnimate()
                .error(R.drawable.icon_goods_detail)
//                .override(banner.getWidth(), (int) (banner.getWidth()*(0.56f)))
                .into(itemView);
    }

}
