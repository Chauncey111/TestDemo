package test.com.mylibrary.base;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 任聪 on 2018/7/10.
 */

public abstract class BaseFragment extends Fragment {
    public Activity mActivity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(getContentId(),container,false);
        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=getActivity();
    }

    /**
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    /**
     * 获取布局ID
     * @return
     */
    protected abstract int getContentId();
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

}
