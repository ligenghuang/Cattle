package com.lgh.huanglib.util.cusview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

public class URLDrawable extends BitmapDrawable {

	public Bitmap bitmap;

	@Override
	public void draw(Canvas canvas) {
		if (bitmap != null) {
			canvas.drawBitmap(bitmap, 0, 0, getPaint());
		}
	}
}
