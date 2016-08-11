package com.ctwl.lzq.howmuchanimation.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;

import com.ctwl.lzq.howmuchanimation.R;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by h0nes1pr09rammer on 2016/8/10.
 */
public class ImageLoaderUtils {
    /**
     * 记录所有正在下载或等待下载的任务。
     */
    private static Set<BitmapWorkerTask> taskCollection;
    private static LruCache<String, Bitmap> mMemoryCache;
    private static ImageLoaderUtils mImageLoaderUtils;
    private Handler mHandler;
    public static ImageLoaderUtils getInstance(){
        if (mImageLoaderUtils == null){
            mImageLoaderUtils = new ImageLoaderUtils();
            taskCollection = new HashSet<>();
            int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
            // 使用最大可用内存值的1/8作为缓存的大小。
            int cacheSize = maxMemory / 8;
            mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    // 重写此方法来衡量每张图片的大小，默认返回图片数量。
                    return bitmap.getByteCount() / 1024;
                }
            };
        }
        return mImageLoaderUtils;
    }
    public void dispalyNativeImg(String imgPath, ImageView mImageView){
//        mImageView.setImageBitmap(decodeSampledBitmapFromNative(imgPath,100,100));
//        if (isScroll){
            loadBitmap(imgPath,mImageView);
//        }else{
//            mImageView.setImageResource(R.mipmap.down_load_ing);
//        }
    }
    private int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }
    public Bitmap decodeSampledBitmapFromNative(String path,
                                                int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }
    public void loadBitmap(final String path, final ImageView imageView) {
        final String imageKey = EncryptUtils.getMD5(path);
        final Bitmap bitmap = getBitmapFromMemCache(imageKey);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            imageView.setImageResource(R.mipmap.down_load_ing);
//            addBitmapToMemoryCache(String.valueOf(path), bitmap);
//            mHandler = new Handler(){
//                @Override
//                public void handleMessage(Message msg) {
//                    super.handleMessage(msg);
//                    switch (msg.what){
//                        case 0x12:
//                            imageView.setImageBitmap((Bitmap) msg.obj);
//                            break;
//                    }
//                }
//            };
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    if (path!=null){
//                        Bitmap bitmap = decodeSampledBitmapFromNative(
//                                path, 100, 100);
//                        addBitmapToMemoryCache(path, bitmap);
//                        Message message = new Message();
//                        message.what = 0x12;
//                        message.obj = bitmap;
//                        mHandler.sendMessage(message);
//                    }
//                }
//            }).start();
            Log.v("path",path);
            BitmapWorkerTask task = new BitmapWorkerTask(imageView);
            taskCollection.add(task);
            task.execute(path);
        }
    }
    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }
    class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {
        // 在后台加载图片。
        ImageView imageView;
        public BitmapWorkerTask(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            final Bitmap bitmap = decodeSampledBitmapFromNative(
                    params[0], 100, 100);
            if (bitmap!=null){
                addBitmapToMemoryCache(EncryptUtils.getMD5(params[0]), bitmap);
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            // 根据Tag找到相应的ImageView控件，将下载好的图片显示出来。
            if (imageView != null && bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
            taskCollection.remove(this);
        }
    }
    public void cancelAllTasks() {
        if (taskCollection != null) {
            for (BitmapWorkerTask task : taskCollection) {
                task.cancel(false);
            }
        }
    }
//    public static Bitmap createCircleImage(Bitmap source, int min)
//    {
//        final Paint paint = new Paint();
//        paint.setAntiAlias(true);
//        Bitmap target = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
//        /**
//         * 产生一个同样大小的画布
//         */
//        Canvas canvas = new Canvas(target);
//        /**
//         * 首先绘制圆形
//         */
//        canvas.drawCircle(min / 2, min / 2, min / 2, paint);
//        /**
//         * 使用SRC_IN
//         */
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        /**
//         * 绘制图片
//         */
//        canvas.drawBitmap(source, 0, 0, paint);
//        return target;
//    }
}

