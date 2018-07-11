package test.com.mylibrary.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

import test.com.mylibrary.base.CoreInit;

/**
 * Created by 任聪 on 2018/7/10.
 */

public class PreferenceUtils {
    private static final String Preference_Name="preference_name";
    private static Context mContext= CoreInit.getInstance().getContext();
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public static boolean putString(String key,String value){
        editor.putString(key,value);
        return editor.commit();
    }

    public static String getString(String key){
        return getString(key,(String)null);
    }

    public static String getString(String key,String defultString){
        return  sharedPreferences.getString(key,defultString);
    }

    public static boolean putInt(String key, int value) {
        editor.putInt(key, value);
        return editor.commit();
    }

    public static int getInt(String key) {
        return getInt(key, -1);
    }

    public static int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public static boolean putLong(String key, long value) {
        editor.putLong(key, value);
        return editor.commit();
    }

    public static long getLong(String key) {
        return getLong(key, -1L);
    }

    public static long getLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    public static boolean putFloat(String key, float value) {
        editor.putFloat(key, value);
        return editor.commit();
    }

    public static float getFloat(String key) {
        return getFloat(key, -1.0F);
    }

    public static float getFloat(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    public static boolean putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        return editor.commit();
    }

    @TargetApi(11)
    public static boolean putStringSet(String key, Set<String> value) {
        editor.putStringSet(key, value);
        return editor.commit();
    }

    @TargetApi(11)
    public static Set<String> getStringSet(String key) {
        return sharedPreferences.getStringSet(key, (Set)null);
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    static {
        sharedPreferences = mContext.getSharedPreferences("preferences_file", 0);
        editor = sharedPreferences.edit();
    }
}
