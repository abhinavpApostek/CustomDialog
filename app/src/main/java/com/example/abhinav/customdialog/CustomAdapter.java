package com.example.abhinav.customdialog;

import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Abhinav on 8/29/2017.
 */

public class CustomAdapter extends BaseExpandableListAdapter {

    private final SparseArray<Group> groups;
    public LayoutInflater mInflater;
    public MainActivity mActivity;
    int which=-1;
    RadioButton radioButton;
    public CustomAdapter(MainActivity act, SparseArray<Group> groups){
        this.groups=groups;
        mActivity=act;
        mInflater = act.getLayoutInflater();
    }
    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).children.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public String getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).children.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
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
        Log.v("getgroupview","called");
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.dialog_group, null);
        }
        Group group = (Group) getGroup(groupPosition);
        final RadioButton radioButton=((RadioButton)convertView.findViewById(R.id.radio));
        radioButton.setText(group.mradioName);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("onradioclick","click");
                mActivity.customDialog.expandGroup(groupPosition);
                if(CustomAdapter.this.radioButton!=null && CustomAdapter.this.radioButton!=radioButton)
                {
                    CustomAdapter.this.radioButton.setChecked(false);
                }
                CustomAdapter.this.radioButton=radioButton;
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String children=(String)getChild(groupPosition,childPosition);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.dialog_child, null);
        }
        CheckBox checkBox= (CheckBox) convertView.findViewById(R.id.check_box);
        checkBox.setText(children);
        if(!groups.get(groupPosition).isChecked.get(childPosition))
        {
            checkBox.setChecked(false);
        }
        else{
            checkBox.setChecked(true);
        }
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groups.get(groupPosition).isChecked.set(childPosition,true);
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
       if(groupPosition==0){
           mActivity.customDialog.collapseGroup(1);
       }else {
           mActivity.customDialog.collapseGroup(0);
       }
    }
}
