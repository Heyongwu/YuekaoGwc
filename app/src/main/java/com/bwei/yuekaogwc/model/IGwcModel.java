package com.bwei.yuekaogwc.model;

import com.bwei.yuekaogwc.bean.HqshBean;
import com.bwei.yuekaogwc.net.OnNetListener;



public interface IGwcModel {
    public void getGwc(OnNetListener<HqshBean> onNetListener);
}
