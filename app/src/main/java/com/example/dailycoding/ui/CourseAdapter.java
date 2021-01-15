package com.example.dailycoding.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailycoding.R;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> {

    private static ArrayList<Course> dataList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private Context context;

    public CourseAdapter(ArrayList<Course> dataList, Context context) {
        this.context=context;
        this.dataList = dataList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public RecyclerView recyclerView;
        public ConstraintLayout constraintLayout;
        public ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.TextView_course_list);
            recyclerView=itemView.findViewById(R.id.RecyclerView_course_content);
            constraintLayout=itemView.findViewById(R.id.ConstraintLayout_item_course);
            imageView=itemView.findViewById(R.id.ImageVIew_itemCourse_arrow);

            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("UseCompatLoadingForDrawables")
                @Override
                public void onClick(View v) {
                    Course course = dataList.get(getAdapterPosition());
                    course.setExpanded(!course.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                    if(course.isExpanded()) imageView.setBackground(context.getDrawable(R.drawable.arrow_down));
                    else    imageView.setBackground(context.getDrawable(R.drawable.arrow_up));
                }
            });
        }
    }

    @Override
    public CourseAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(dataList.get(position).getTitle());
        holder.recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(context);
        holder.recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new CourseContentAdapter(dataList.get(position).getContent());
        holder.recyclerView.setAdapter(mAdapter);
        boolean isExpanded = dataList.get(position).isExpanded();
        holder.recyclerView.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


}
