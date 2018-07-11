package test.com.mylibrary.base;

import android.content.Context;

/**
 * Created by 任聪 on 2018/7/10.
 */

public class CoreInit {
    private static CoreInit mCoreInit;
    private Context mContext;

    public synchronized void init(Context context){
        this.mContext=context.getApplicationContext();
    }


    public static synchronized CoreInit getInstance(){
        if(mCoreInit==null){
            mCoreInit=new CoreInit();
        }
        return mCoreInit;
    }
    public Context getContext(){
        return this.mContext;
    }
}
