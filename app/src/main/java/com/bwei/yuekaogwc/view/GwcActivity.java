package com.bwei.yuekaogwc.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.bwei.yuekaogwc.EventMangges.EventMange;
import com.bwei.yuekaogwc.R;
import com.bwei.yuekaogwc.adapter.MyErjiAdapter;
import com.bwei.yuekaogwc.bean.HqshBean;
import com.bwei.yuekaogwc.presenter.GwcPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class GwcActivity extends AppCompatActivity implements IGwcActivity{

    private ExpandableListView mElv;
    private CheckBox mCheckbox2;
    /**
     * 0
     */
    private TextView mTvPrice;
    /**
     * 结算(0)
     */
    private TextView mTvNum;
    private MyErjiAdapter erjiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gwc);
        EventBus.getDefault().register(this);
        initView();
        new GwcPresenter(this).getGwc();
        mCheckbox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                erjiAdapter.selectAllChecked(mCheckbox2.isChecked());
            }
        });


    }

    private void initView() {
        mElv = (ExpandableListView) findViewById(R.id.elv);
        mCheckbox2 = (CheckBox) findViewById(R.id.checkbox2);
        mTvPrice = (TextView) findViewById(R.id.tv_price);
        mTvNum = (TextView) findViewById(R.id.tv_num);
    }


    @Override
    public void showDate(List<HqshBean.DataBean> groupList, List<List<HqshBean.DataBean.ListBean>> childList) {
        erjiAdapter = new MyErjiAdapter(childList,groupList,GwcActivity.this);
        mElv.setAdapter(erjiAdapter);
        mElv.setGroupIndicator(null);
        //默认让其全部展开
        for (int i = 0; i < groupList.size(); i++) {
            mElv.expandGroup(i);
        }
    }
    @Subscribe
    public void onMessageEvent(EventMange event){
        mCheckbox2.setChecked(event.isChecked());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
