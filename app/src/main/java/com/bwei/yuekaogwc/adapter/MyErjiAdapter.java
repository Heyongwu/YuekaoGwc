package com.bwei.yuekaogwc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.yuekaogwc.EventMangges.EventMange;
import com.bwei.yuekaogwc.R;
import com.bwei.yuekaogwc.bean.HqshBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class MyErjiAdapter extends BaseExpandableListAdapter {
    private List<List<HqshBean.DataBean.ListBean>> childList;
    private List<HqshBean.DataBean> groupList;
    private Context context;
    private LayoutInflater inflater;

    public MyErjiAdapter(List<List<HqshBean.DataBean.ListBean>> childList, List<HqshBean.DataBean> groupList, Context context) {
        this.childList = childList;
        this.groupList = groupList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return groupList.size();

    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return childList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {

        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {

        return childPosition;
    }

    @Override
    public boolean hasStableIds() {

        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view;
        final MyViewHolde_group myViewHolde_group;
        if(convertView == null){
            myViewHolde_group = new MyViewHolde_group();
            view = inflater.inflate(R.layout.item_group,null);
            myViewHolde_group.group_cb = (CheckBox) view.findViewById(R.id.group_cb);
            myViewHolde_group.group_id = (TextView) view.findViewById(R.id.group_id);
            myViewHolde_group.group_shangjia = (TextView) view.findViewById(R.id.group_shangjia);
            view.setTag(myViewHolde_group);
        }else{
            view =convertView;
            myViewHolde_group = (MyViewHolde_group) view.getTag();
        }
        final HqshBean.DataBean dataBean = groupList.get(groupPosition);
        myViewHolde_group.group_cb.setChecked(dataBean.isChecked());
        myViewHolde_group.group_id.setText(dataBean.getSellerid());
        myViewHolde_group.group_shangjia.setText(dataBean.getSellerName());
        myViewHolde_group.group_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myViewHolde_group.group_cb.isChecked()){
                    isAllchildchecked(groupPosition,true);
                }else{
                    isAllchildchecked(groupPosition,false);
                }
                dataBean.setChecked(myViewHolde_group.group_cb.isChecked());

                if(isAllgroupchecked()){

                    Quanx(true);
                }else{
                    Quanx(false);
                }
                notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view;
        final MyViewHolde_child myViewHolde_child;
        if(convertView == null){
            myViewHolde_child = new MyViewHolde_child();
            view = inflater.inflate(R.layout.item_child,null);
            myViewHolde_child.child_cb = (CheckBox) view.findViewById(R.id.child_cb);
            myViewHolde_child.child_iv = (ImageView) view.findViewById(R.id.child_iv);
            myViewHolde_child.child_name = (TextView) view.findViewById(R.id.child_name);
            myViewHolde_child.child_price = (TextView) view.findViewById(R.id.child_price);
            view.setTag(myViewHolde_child);
        }else{
            view =convertView;
            myViewHolde_child = (MyViewHolde_child) view.getTag();
        }
        final HqshBean.DataBean.ListBean listBean = childList.get(groupPosition).get(childPosition);
        myViewHolde_child.child_cb.setChecked(listBean.isChecked());
//        myViewHolde_child.child_iv.setImageURI(Uri.parse(listBean.getImages()));
        myViewHolde_child.child_name.setText(listBean.getTitle());
        myViewHolde_child.child_price.setText(listBean.getPrice()+"");
        myViewHolde_child.child_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myViewHolde_child.child_cb.isChecked()){
                    listBean.setChecked(true);
                    if(isAllchildLischecked(groupPosition)){
                        HqshBean.DataBean dataBean = groupList.get(groupPosition);
                        dataBean.setChecked(true);
                    }
                    if(isAllgroupchecked()){
                        Quanx(true);
                    }
                    notifyDataSetChanged();
                }else{
                    listBean.setChecked(false);
                    HqshBean.DataBean dataBean = groupList.get(groupPosition);
                    dataBean.setChecked(false);
                    Quanx(false);
                    notifyDataSetChanged();
                }

            }


        });
        return view;

    }



    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {

        return true;
    }


    class MyViewHolde_group{
        CheckBox group_cb;
        TextView group_id;
        TextView group_shangjia;
    }
    class MyViewHolde_child{
        CheckBox child_cb;
        TextView child_name;
        ImageView child_iv;
        TextView child_price;
    }
    //
    //计算二级列表是否全选
    private boolean isAllchildLischecked(int groupPistion) {

        List<HqshBean.DataBean.ListBean> listBeen = childList.get(groupPistion);

        for (int i = 0; i < listBeen.size(); i++) {
            HqshBean.DataBean.ListBean listBean = listBeen.get(i);
            if(!listBean.isChecked()){
                return false;
            }

        }
        return true;

    }
    //判断一级列表是否全选
    public boolean isAllgroupchecked() {
        for (int i = 0; i < groupList.size(); i++) {
            HqshBean.DataBean dataBean = groupList.get(i);
            if(!dataBean.isChecked()){
                return false;
            }
        }
        return true;
    }
    //设置二级列表是否全选
    private boolean isAllchildchecked(int groupPostion,boolean b){
        List<HqshBean.DataBean.ListBean> listBeen = childList.get(groupPostion);
        for (int i = 0; i < listBeen.size() ; i++) {
            HqshBean.DataBean.ListBean listBean = listBeen.get(i);
            listBean.setChecked(b);
        }
        return true;


    }
    //如果一级二级都选那么就全选
    private void Quanx(boolean b) {
        EventMange eventMange = new EventMange();
        eventMange.setChecked(b);
        EventBus.getDefault().post(eventMange);
    }


    //如果点击全选
    public  void selectAllChecked(boolean isChecked){
        for (int i = 0; i < groupList.size() ; i++) {
            HqshBean.DataBean dataBean = groupList.get(i);
            dataBean.setChecked(isChecked);
            isAllchildchecked(i,isChecked);
        }
        notifyDataSetChanged();
    }

}
