package com.lgh.huanglib.util.cusview.richtxtview;

import android.graphics.Bitmap;

import java.io.IOException;

/**
 * Created by wanglei on 2016/11/02.
 */

public interface ImageLoader {
    Bitmap getBitmap(String url) throws IOException;
}
