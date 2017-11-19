package com.bwei.yuekaogwc.presenter;

import com.bwei.yuekaogwc.bean.PuLaBean;
import com.bwei.yuekaogwc.model.IMainModel;
import com.bwei.yuekaogwc.model.MainModel;
import com.bwei.yuekaogwc.net.OnNetListener;
import com.bwei.yuekaogwc.view.IMainActivity;

import java.util.List;



public class Mainpresent {
    private IMainActivity iMainActivity;
    private final IMainModel imainModel;

    public Mainpresent(IMainActivity iMainActivity) {
        this.iMainActivity = iMainActivity;
        imainModel = new MainModel();
    }
    public void getImage(){
        imainModel.getImag(new OnNetListener<PuLaBean>() {

            @Override
            public void Onfailure(Exception e) {

            }

            @Override
            public void OnSuccess(PuLaBean puLaBean) {

                List<PuLaBean.DataBean> data = puLaBean.getData();



                iMainActivity.showData(data);
            }
        });
    }
}
