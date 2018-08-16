package com.kakondey701.kd.csvreader;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by KAKON on 1/23/2018.
 */

public class StudentsDetailsListVIewAdapter extends BaseAdapter {

    Activity context;
    String studentName;
    String rollNumber;

    public StudentsDetailsListVIewAdapter(Activity context, String studentName, String rollNo)
    {
        this.context = context;
        this.studentName = studentName;
        this.rollNumber = rollNo;
    }

    @Override
    public int getCount() {
        return studentName.length();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolder
    {
        TextView name;
        TextView rollnum;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;
        LayoutInflater inflater = context.getLayoutInflater();

        if (view == null)
        {
            view = inflater.inflate(R.layout.student_details_listview,null);
            viewHolder.name = (TextView)view.findViewById(R.id.studentName);
            viewHolder.rollnum = (TextView)view.findViewById(R.id.Rollno);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.name.setText(studentName);
        viewHolder.rollnum.setText(rollNumber);
        return view;
    }
}
