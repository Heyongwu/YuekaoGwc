package com.bwei.yuekaogwc.model;

import com.bwei.yuekaogwc.bean.PuLaBean;
import com.bwei.yuekaogwc.net.OnNetListener;



public interface IMainModel {
    public void getImag(OnNetListener<PuLaBean> onNetListener);

}
