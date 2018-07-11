package test.com.mylibrary.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by 任聪 on 2018/7/10.
 */

public class ImageCacheUtils {
    private static LruCache<String ,Bitmap> cache=new LruCache<String ,Bitmap>((int) (Runtime.getRuntime().maxMemory()/8L)){
        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getRowBytes()*value.getHeight();
        }
    };
    private ImageCacheUtils(){}
    public static void put(String key,Bitmap value){
         cache.put(key,value);
    }

    public static Bitmap get(String key){
        return (Bitmap) cache.get(key);
    }
}
