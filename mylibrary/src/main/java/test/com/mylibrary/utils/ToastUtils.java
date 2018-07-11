package test.com.mylibrary.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 任聪 on 2018/7/10.
 */

public class ToastUtils {
    private static Toast mToast;
    public static void Toast(Context context,String msg){
        if(mToast==null){
            mToast=Toast.makeText(context,msg,Toast.LENGTH_SHORT);
        }else {
            mToast.setText(msg);
        }
        mToast.show();
    }
    public static void Toast(int gravity,Context context,String msg){
        if(mToast==null){
            mToast=Toast.makeText(context,msg,Toast.LENGTH_SHORT);
        }else {
            mToast.setText(msg);
        }
        mToast.setGravity(gravity,0,0);
        mToast.show();
    }
}
