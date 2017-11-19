package com.bwei.yuekaogwc.presenter;

import com.bwei.yuekaogwc.bean.HqshBean;
import com.bwei.yuekaogwc.model.GwcModel;
import com.bwei.yuekaogwc.model.IGwcModel;
import com.bwei.yuekaogwc.net.OnNetListener;
import com.bwei.yuekaogwc.view.IGwcActivity;

import java.util.ArrayList;
import java.util.List;



public class GwcPresenter {
    private IGwcActivity iGwcActivity;
    private final IGwcModel igwcModel;

    public GwcPresenter(IGwcActivity iGwcActivity) {
        this.iGwcActivity = iGwcActivity;
        igwcModel = new GwcModel();
    }
    public void getGwc(){
        igwcModel.getGwc(new OnNetListener<HqshBean>() {
            @Override
            public void Onfailure(Exception e) {

            }

            @Override
            public void OnSuccess(HqshBean hqshBean) {
                List<HqshBean.DataBean> groupList = hqshBean.getData();
                List<List<HqshBean.DataBean.ListBean>> childList = new ArrayList<>();
                for(int i = 0 ; i<groupList.size() ; i++){
                    List<HqshBean.DataBean.ListBean> list1 = groupList.get(i).getList();
                    childList.add(list1);
                }
                iGwcActivity.showDate(groupList,childList);
            }
        });
    }
}
