package com.bwei.yuekaogwc.net;

import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;



public class HttpUtils {
    private static HttpUtils httpUtils;
    private final OkHttpClient client;

    public HttpUtils() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
    }
    public static HttpUtils getHttpUtils(){
        if(httpUtils == null){
            synchronized (HttpUtils.class){
                if(httpUtils == null){
                    httpUtils = new HttpUtils();
                }
            }
        }
        return httpUtils;
    }
    public void doGet(String url, Callback callback){
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }
    public  void doPost(String url, Map<String,String> params,Callback callback){
        FormBody.Builder builder = new FormBody.Builder();
        for(Map.Entry<String ,String> entry : params.entrySet()){
            builder.add(entry.getKey(),entry.getValue());
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        client.newCall(request).enqueue(callback);
    }

}
