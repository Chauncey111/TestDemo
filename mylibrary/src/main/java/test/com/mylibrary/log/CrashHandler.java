package test.com.mylibrary.log;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by 任聪 on 2018/7/10.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private static CrashHandler mInstance = new CrashHandler();
    private Context mContext;
    private Map<String, String> mLogInfo = new HashMap();
    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyyMMdd_HH-mm-ss");

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return mInstance;
    }

    public void init(Context paramContext) {
        this.mContext = paramContext;
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
        if(!this.handleException(paramThrowable) && this.mDefaultHandler != null) {
            this.mDefaultHandler.uncaughtException(paramThread, paramThrowable);
        } else {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException var4) {
                var4.printStackTrace();
            }

            Process.killProcess(Process.myPid());
            System.exit(1);
        }

    }

    public boolean handleException(Throwable paramThrowable) {
        if(paramThrowable == null) {
            return false;
        } else {
            (new Thread() {
                public void run() {
                    Looper.prepare();
                    Toast.makeText(CrashHandler.this.mContext, "很抱歉,程序出现异常,即将退出", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }).start();
            this.getDeviceInfo(this.mContext);
            this.saveCrashLogToFile(paramThrowable);
            return true;
        }
    }

    public void getDeviceInfo(Context paramContext) {
        try {
            PackageManager mPackageManager = paramContext.getPackageManager();
            PackageInfo mPackageInfo = mPackageManager.getPackageInfo(paramContext.getPackageName(), PackageManager.GET_ACTIVITIES);
            if(mPackageInfo != null) {
                String versionName = mPackageInfo.versionName == null?"null":mPackageInfo.versionName;
                String versionCode = String.valueOf(mPackageInfo.versionCode);
                this.mLogInfo.put("versionName", versionName);
                this.mLogInfo.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException var10) {
            var10.printStackTrace();
        }

        Field[] mFields = Build.class.getDeclaredFields();
        Field[] var6 = mFields;
        int var14 = mFields.length;

        for(int var13 = 0; var13 < var14; ++var13) {
            Field field = var6[var13];

            try {
                field.setAccessible(true);
                this.mLogInfo.put(field.getName(), field.get("").toString());
                Log.d("NorrisInfo", field.getName() + ":" + field.get(""));
            } catch (IllegalArgumentException var8) {
                var8.printStackTrace();
            } catch (IllegalAccessException var9) {
                var9.printStackTrace();
            }
        }

    }

    private String saveCrashLogToFile(Throwable paramThrowable) {
        StringBuffer mStringBuffer = new StringBuffer();
        Iterator var4 = this.mLogInfo.entrySet().iterator();

        String mResult;
        while(var4.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry)var4.next();
            String key = (String)entry.getKey();
            mResult = (String)entry.getValue();
            mStringBuffer.append(key + "=" + mResult + "\r\n");
        }

        Writer mWriter = new StringWriter();
        PrintWriter mPrintWriter = new PrintWriter(mWriter);
        paramThrowable.printStackTrace(mPrintWriter);
        paramThrowable.printStackTrace();

        for(Throwable mThrowable = paramThrowable.getCause(); mThrowable != null; mThrowable = mThrowable.getCause()) {
            mThrowable.printStackTrace(mPrintWriter);
            mPrintWriter.append("\r\n");
        }

        mPrintWriter.close();
        mResult = mWriter.toString();
        mStringBuffer.append(mResult);
        String mTime = this.mSimpleDateFormat.format(new Date());
        String mFileName = "CrashLog-" + mTime + ".log";
        if(Environment.getExternalStorageState().equals("mounted")) {
            try {
                File mDirectory = new File(Environment.getExternalStorageDirectory() + "/CrashInfos");
                Log.v("NorrisInfo", mDirectory.toString());
                if(!mDirectory.exists()) {
                    mDirectory.mkdir();
                }

                FileOutputStream mFileOutputStream = new FileOutputStream(mDirectory + "/" + mFileName);
                mFileOutputStream.write(mStringBuffer.toString().getBytes());
                mFileOutputStream.close();
                return mFileName;
            } catch (FileNotFoundException var11) {
                var11.printStackTrace();
            } catch (IOException var12) {
                var12.printStackTrace();
            }
        }

        return null;
    }
}
