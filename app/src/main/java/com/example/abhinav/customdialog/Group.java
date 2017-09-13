package com.example.abhinav.customdialog;

import java.util.ArrayList;

/**
 * Created by Abhinav on 8/29/2017.
 */

public class Group {

    String mradioName;
    ArrayList<String> children;
    ArrayList<Boolean> isChecked=new ArrayList<>();
    boolean selectable=false;
    public Group(String radioName,ArrayList<String> children)
    {
        this.mradioName=radioName;
        this.children=children;
        for(int i=0;i<children.size();i++)
        {
            isChecked.add(false);
        }
    }

    public void setSelectable(boolean selectable)
    {
        this.selectable=selectable;
    }
}
