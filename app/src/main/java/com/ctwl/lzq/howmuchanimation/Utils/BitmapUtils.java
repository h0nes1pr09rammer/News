package com.ctwl.lzq.howmuchanimation.Utils;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

/**
 * Created by h0nes1pr09rammer on 2016/8/11.
 */
public class BitmapUtils {
    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
}
