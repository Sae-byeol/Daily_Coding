package com.example.dailycoding.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
    //    isCourse:true이면 기출문제 선택, false이면 언어 소개
    private int type;

    private static final String TAG="CourseAdapter";

    public CourseAdapter(ArrayList<Course> dataList, Context context, int type) {
        this.context=context;
        this.dataList = dataList;
        this.type=type;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView TextView_title, TextView_content;
        public RecyclerView recyclerView;
        public ConstraintLayout constraintLayout;
        public ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            //코스 페이지
            if(type==0){
                TextView_title = itemView.findViewById(R.id.TextView_course_list);
//                recyclerView=itemView.findViewById(R.id.RecyclerView_course_content);
                constraintLayout=itemView.findViewById(R.id.ConstraintLayout_item_course);
                imageView=itemView.findViewById(R.id.ImageVIew_itemCourse_arrow);
            }
            //문제 리스트 페이지
            else if(type==1){
                TextView_title = itemView.findViewById(R.id.TextView_course_list);
                constraintLayout=itemView.findViewById(R.id.ConstraintLayout_item_course);
                imageView=itemView.findViewById(R.id.ImageVIew_itemCourse_arrow);
            }
            //언어소개 페이지
            else if(type==2){
                TextView_title = itemView.findViewById(R.id.TextView_language_list);
                TextView_content=itemView.findViewById(R.id.TextView_languageIntroduction_content);
                imageView=itemView.findViewById(R.id.ImageVIew_language_arrow);
                constraintLayout=itemView.findViewById(R.id.ConstraintLayout_item_language);
            }
            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("UseCompatLoadingForDrawables")
                @Override
                public void onClick(View v) {
                    if(type==0){
                        Intent intent=new Intent(context, ProblemListActivity.class);
                        context.startActivity(intent);
                    }
                    else if(type==1){

                    }
                    else if(type==2){
                        Course course = dataList.get(getAdapterPosition());
                        course.setExpanded(!course.isExpanded());
                        notifyItemChanged(getAdapterPosition());
                        if(course.isExpanded()) imageView.setBackground(context.getDrawable(R.drawable.arrow_down));
                        else    imageView.setBackground(context.getDrawable(R.drawable.arrow_up));
                    }
                }
            });
        }
    }

    @Override
    public CourseAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View v;
        Log.d(TAG, ""+type);
        if(type==0){
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_course, parent, false);
        }
        else if(type==1){
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_course, parent, false);
        }
        else{
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_language, parent, false);
        }
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @SuppressLint({"ResourceType", "UseCompatLoadingForDrawables"})
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.TextView_title.setText(dataList.get(position).getTitle());
        boolean isExpanded = dataList.get(position).isExpanded();
        if(type==1||type==0){
            holder.imageView.setBackground(context.getDrawable(R.drawable.news_arrow));

//            holder.recyclerView.setHasFixedSize(true);
//
//            // use a linear layout manager
//            layoutManager = new LinearLayoutManager(context);
//            holder.recyclerView.setLayoutManager(layoutManager);
//
//            // specify an adapter (see also next example)
//            mAdapter = new CourseContentAdapter(dataList.get(position).getContent());
//            holder.recyclerView.setAdapter(mAdapter);

//            ConstraintLayout.LayoutParams layoutParams=new ConstraintLayout.LayoutParams(
//                    ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT
//            );
//            holder.recyclerView.setId(1002);
//            layoutParams.bottomToTop=1002;

//            holder.recyclerView.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
//            holder.TextView_title.setLayoutParams(layoutParams);
        }
        else{
//            ConstraintLayout.LayoutParams layoutParams=new ConstraintLayout.LayoutParams(
//                    ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT
//            );
//            holder.TextView_content.setId(1001);
//            layoutParams.bottomToTop= Integer.parseInt(context.getString(R.id.TextView_languageIntroduction_content));
//            layoutParams.leftToLeft=ConstraintLayout.LayoutParams.PARENT_ID;
//            layoutParams.rightToRight=ConstraintLayout.LayoutParams.PARENT_ID;
//            layoutParams.topToTop=ConstraintLayout.LayoutParams.PARENT_ID;
//            layoutParams.topMargin=20;
//            layoutParams.bottomMargin=20;
            holder.TextView_content.setVisibility(isExpanded?View.VISIBLE: View.GONE);
//            holder.TextView_title.setLayoutParams(layoutParams);
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


}
