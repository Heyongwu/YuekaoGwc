package com.bwei.yuekaogwc.view;

import com.bwei.yuekaogwc.bean.HqshBean;

import java.util.List;


public interface IGwcActivity {
    void showDate(List<HqshBean.DataBean> groupList,List<List<HqshBean.DataBean.ListBean>> childList);
}
