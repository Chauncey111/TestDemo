package test.com.mylibrary.http;

/**
 * Created by 任聪 on 2018/7/11.
 */

public class RequestFactory {
    public HttpInterface getManger(){
        return OkHttpManger.getInstance();
    }
}
