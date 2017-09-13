package com.example.abhinav.customdialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.view.animation.LayoutAnimationController;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Abhinav on 8/29/2017.
 */

public class CustomDialog extends Dialog implements AdapterView.OnItemClickListener, ExpandableListView.OnGroupClickListener {

    ExpandableListView mexpandableListView;
    private SparseArray<Group> groups=new SparseArray<>();
    String genre[]={"HORROR","DRAMA","COMEDY"};
    String year[]={"1990-2000","2000-2010","2010-Present"};
    MainActivity mainActivity;
    Animation a;
    public CustomDialog(@NonNull MainActivity context){
        super(context);
        mainActivity=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);
        createData();
        mexpandableListView = (ExpandableListView) findViewById(R.id.list_view);
        CustomAdapter customAdapter = new CustomAdapter(mainActivity, groups);
        mexpandableListView.setAdapter(customAdapter);
        final int targtetHeight = mexpandableListView.getMeasuredHeight();
         a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                mexpandableListView.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int)(targtetHeight * interpolatedTime);
                mexpandableListView.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        a.setDuration((int)(targtetHeight / mexpandableListView.getContext().getResources().getDisplayMetrics().density));
    }

    public void createData() {
        ArrayList<String> x= new ArrayList<String>();
        x.addAll(Arrays.asList(genre));
        Group group=new Group("GENRE",x);
        groups.append(0, group);
        x=new ArrayList<>();
        x.addAll(Arrays.asList(year));
        group=new Group("YEAR",x);
        groups.append(1, group);
           // groups.append(j, group);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.v("onitemclick","click");
        mexpandableListView.expandGroup(position,true);

    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        Log.v("ongroupclick","click");

        mexpandableListView.expandGroup(groupPosition,true);
        return false;
    }
    public void expandGroup(final int position){

      mexpandableListView.expandGroup(position,true);

    }

    public void collapseGroup(int position)
    {
        mexpandableListView.collapseGroup(position);
        if(position==0)
        {
            groups.get(1).setSelectable(false);
        }
        else if(position==1)
        {
            groups.get(0).setSelectable(false);
        }

    }
}


