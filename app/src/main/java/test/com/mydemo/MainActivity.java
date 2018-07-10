package test.com.mydemo;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import test.com.mylibrary.base.BaseActivity;


public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_test)
    TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initObject() {

    }

    @Override
    protected void setListener() {

    }


}
