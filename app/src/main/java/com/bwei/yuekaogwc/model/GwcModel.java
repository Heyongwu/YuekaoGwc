package com.bwei.yuekaogwc.model;

import com.bwei.yuekaogwc.bean.HqshBean;
import com.bwei.yuekaogwc.net.Api;
import com.bwei.yuekaogwc.net.HttpUtils;
import com.bwei.yuekaogwc.net.OnNetListener;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;



public class GwcModel extends BeanModel implements IGwcModel{
    @Override
    public void getGwc(final OnNetListener<HqshBean> onNetListener) {
        HttpUtils.getHttpUtils().doGet(Api.ur3, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.Onfailure(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final HqshBean hqshBean = new Gson().fromJson(string, HqshBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.OnSuccess(hqshBean);
                    }
                });
            }
        });
    }
}
