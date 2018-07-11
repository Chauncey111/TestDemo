package test.com.mylibrary.http;

import java.io.File;
import java.util.List;

import test.com.mylibrary.entity.Param;

/**
 * Created by 任聪 on 2018/7/11.
 * http方法接口
 */

public interface HttpInterface {
    void get(String url, List<Param> list, ICallback iCallback);

    void post(String url, List<Param> list,ICallback iCallback);

    void delete(String url, List<Param> list,ICallback iCallback);

    void post(String url, List<Param> list, List<File>files,ICallback iCallback);
}
