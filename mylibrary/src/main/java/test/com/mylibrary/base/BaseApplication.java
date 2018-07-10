package test.com.mylibrary.base;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;

import test.com.mylibrary.log.CrashHandler;

/**
 * Created by 任聪 on 2018/7/10.
 */

public class BaseApplication extends Application{
    private ArrayList<Activity> activityList=new ArrayList<>();
    private static BaseApplication instance=null;

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);
    }

    public static BaseApplication getInstance(){
        if(instance==null){
            instance=new BaseApplication();
        }
        return instance;
    }

    /**
     * 添加activity入栈
     */
    public void addActivity(Activity activity){
        this.activityList.add(activity);
    }

    /**
     * 删除activity
     */
    public void removeActivity(Activity activity){
        this.activityList.remove(activity);
    }
    /**
     * 清空栈内activity
     */
    public void clearActivity(){
        if(activityList.size()>0) {
            for (Activity activity : activityList) {
                activity.finish();
            }
        }
    }

    /**
     * 程序结束时回调函数
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
