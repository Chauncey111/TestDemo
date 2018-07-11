package test.com.mylibrary.http;

/**
 * Created by 任聪 on 2018/7/11.
 * http回调接口
 */

public interface ICallback {
    void sucess(Object object);
    void fail(String error);
}
