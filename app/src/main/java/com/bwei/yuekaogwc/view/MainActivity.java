package com.bwei.yuekaogwc.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.bwei.yuekaogwc.R;
import com.bwei.yuekaogwc.adapter.MyAdapter;
import com.bwei.yuekaogwc.bean.PuLaBean;
import com.bwei.yuekaogwc.presenter.Mainpresent;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainActivity{

    private RecyclerView mRcv;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        Mainpresent mainpresent = new Mainpresent(this);
        mainpresent.getImage();

    }

    private void initView() {
        mRcv = (RecyclerView) findViewById(R.id.rcv);
    }



    @Override
        public void showData(List<PuLaBean.DataBean> list) {
        mRcv.setHasFixedSize(true);
        mRcv.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        adapter = new MyAdapter(list, MainActivity.this);
        mRcv.setAdapter(adapter);
    }
}
