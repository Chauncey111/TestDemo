package test.com.mylibrary.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by 任聪 on 2018/7/10.
 * activity基类
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        BaseApplication.getInstance().addActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        ButterKnife.bind(this);
        initView();
        initObject();
        initData();
        setListener();
    }

    /**
     * 设置布局
     * @return
     */
    protected abstract int setLayoutId();

    /**
     * 开启新的activity
     * @param activity
     */
    public void startActivity(Class<?> activity){
        Intent intent=new Intent(this,activity);
        startActivity(intent);
    }

    /**
     * 开启新的activity带参数bundle
     * @param activity
     * @param bundle
     */
    public void startActivity(Class<?> activity,Bundle bundle){
        Intent intent=new Intent(this,activity);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * @param activity
     * @param requestCode
     */
    public void startActivityForResult(Class<?> activity,int requestCode){
        Intent intent=new Intent(this,activity);
        startActivityForResult(intent,requestCode);
    }

    /**
     * @param activity
     * @param requestCode
     * @param bundle
     */
    public void startActivityForResult(Class<?> activity,int requestCode,Bundle bundle){
        Intent intent=new Intent(this,activity);
        intent.putExtras(bundle);
        startActivityForResult(intent,requestCode);
    }
    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 初始化对象
     */
    protected abstract void initObject();
    /**
     * 初始化数据
     */
    protected abstract void initData();
    /**
     * 设置监听
     */
    protected abstract void setListener();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApplication.getInstance().removeActivity(this);
    }
}
