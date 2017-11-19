package com.bwei.yuekaogwc.model;

import com.bwei.yuekaogwc.bean.PuLaBean;
import com.bwei.yuekaogwc.net.Api;
import com.bwei.yuekaogwc.net.HttpUtils;
import com.bwei.yuekaogwc.net.OnNetListener;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;



public class MainModel extends BeanModel implements IMainModel {
    @Override
    public void getImag(final OnNetListener<PuLaBean> onNetListener) {
        HttpUtils.getHttpUtils().doGet(Api.url2, new Callback() {
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

                final PuLaBean puLaBean = new Gson().fromJson(string, PuLaBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.OnSuccess(puLaBean);
                    }
                });

            }
        });
    }
}
