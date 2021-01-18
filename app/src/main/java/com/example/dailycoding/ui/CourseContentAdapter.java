package com.example.dailycoding.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dailycoding.R;

import java.util.ArrayList;

public class CourseContentAdapter extends RecyclerView.Adapter<CourseContentAdapter.MyViewHolder> {

    private ArrayList<String> dataList;

    public CourseContentAdapter(ArrayList<String> dataList) {
        this.dataList = dataList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.TextView_item_courseContent);
        }
    }

    @Override
    public CourseContentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course_content, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(dataList.get(position));
//        holder.textView.setText("으아아아");
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


}
