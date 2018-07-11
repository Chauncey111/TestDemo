package test.com.mylibrary.http;

import android.os.Build;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import test.com.mylibrary.entity.Param;

/**
 * Created by 任聪 on 2018/7/11.
 */

public class OkHttpManger implements HttpInterface{
    private static OkHttpManger okHttpManger;
    private OkHttpClient okHttpClient;


    public OkHttpManger(){
        okHttpClient=new OkHttpClient();
    }

    public static OkHttpManger getInstance(){
        if(okHttpManger==null){
            synchronized (OkHttpManger.class){
                if(okHttpManger==null){
                    okHttpManger=new OkHttpManger();
                }
            }
        }
        return okHttpManger;
    }
    /**
     * get普通请求
     * @param url
     * @param mList
     * @param iCallback
     */
    @Override
    public void get(String url, List<Param> mList, final ICallback iCallback) {
        String constractUrl=constructUrl(url,mList);
        Request request=new Request.Builder()
                .get()
                .url(constractUrl)
                .build();
        stratRequest(request,iCallback);



    }

    /**
     * post普通请求
     * @param url
     * @param mList
     * @param iCallback
     */
    @Override
    public void post(String url, List<Param> mList, ICallback iCallback) {
        FormBody.Builder builder=new FormBody.Builder();
        if(mList.size()>0) {
            for (Param param : mList){
                builder.add(param.key,param.value);
            }
        }
        RequestBody requestBody=builder.build();
        Request request=new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        stratRequest(request,iCallback);
    }

    @Override
    public void delete(String url, List<Param> mList, ICallback iCallback) {

    }

    /**
     * 文件上传
     * @param url
     * @param list
     * @param files
     * @param iCallback
     */
    @Override
    public void post(String url, List<Param> list, List<File> files, ICallback iCallback) {

    }


    /**
     * 构造get请求的url
     *
     * @param url    不带参数的url
     * @param params 参数
     * @return 带参数的url
     */
    private String constructUrl(String url,List<Param> params) {
        StringBuilder sb = new StringBuilder();
        sb.append(url);
        if (params.size() > 0) {
            sb.append("?");
        } else {
            return sb.toString();
        }

        for (Param p :
                params) {
            sb.append(p.key + "=" + p.value + "&");
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    private void stratRequest(Request request, final ICallback iCallback){
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iCallback.fail(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                iCallback.sucess(response.body().toString());
            }
        });
    }
}
