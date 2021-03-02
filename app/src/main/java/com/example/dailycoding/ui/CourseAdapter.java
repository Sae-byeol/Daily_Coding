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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailycoding.R;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> {

    private static final String TAG="CourseAdapter";

    private static ArrayList<Course> dataList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private Context context;
    //    isCourse:true이면 기출문제 선택, false이면 언어 소개
    private int type;
    private String language;

    public CourseAdapter(ArrayList<Course> dataList, Context context, int type) {
        this.context=context;
        this.dataList = dataList;
        this.type=type;
    }

    public CourseAdapter(ArrayList<Course> dataList, Context context, int type, String language) {
        this.context=context;
        this.dataList = dataList;
        this.type=type;
        this.language=language;
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

        }
    }

    @Override
    public CourseAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View v;
//        Log.d(TAG, ""+type);
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
            holder.imageView.setBackground(context.getDrawable(R.drawable.ic_arrow_right));
        }
        else{
            holder.TextView_content.setVisibility(isExpanded?View.VISIBLE: View.GONE);
            if(isExpanded){
                holder.constraintLayout.setBackground(context.getDrawable(R.drawable.round_border_black));
                holder.TextView_title.setTextColor(ContextCompat.getColor(context, R.color.color_primary_light));
                holder.TextView_content.setTextColor(ContextCompat.getColor(context, R.color.white));
            }
            else{
                holder.TextView_title.setTextColor(ContextCompat.getColor(context, R.color.black));
//                    holder.TextView_content.setTextColor(context.getColor(R.color.white));
                holder.constraintLayout.setBackground(context.getDrawable(R.drawable.round_border_white));
            }
//            holder.TextView_title.setLayoutParams(layoutParams);
        }

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                //코스 페이지
                if(type==0){
                    Intent intent=new Intent(context, ProblemListActivity.class);
                    // 언어 파라미터 추가 필요
//                    intent.putExtra()
                    // 카테고리 파라미터
                    intent.putExtra("category", dataList.get(position).getTitle());
                    intent.putExtra("language", language);
                    context.startActivity(intent);
                }
                //문제 리스트 페이지
                else if(type==1){
//                    for(Course course:dataList){
//                        Log.d(TAG, "dataList:");
//
//                    }
                    Intent intent=new Intent(context, ProblemDetailActivity.class);
                    intent.putExtra("id", dataList.get(position).getId());
                    intent.putExtra("dataList", dataList);
                    context.startActivity(intent);
                }
                //언어 소개 페이지
                else if(type==2){
                    Course course = dataList.get(position);
                    course.setExpanded(!course.isExpanded());
                    notifyItemChanged(position);
                    if(course.isExpanded()) {
                        holder.imageView.setBackground(context.getDrawable(R.drawable.ic_arrow_down_small));
                        holder.constraintLayout.setBackground(context.getDrawable(R.drawable.round_border_white));
                        holder.TextView_title.setTextColor(ContextCompat.getColor(context, R.color.black));
                    }
                    else    {
                        holder.imageView.setBackground(context.getDrawable(R.drawable.ic_arrow_up_small));
                        holder.constraintLayout.setBackground(context.getDrawable(R.drawable.round_border_black));

                        holder.TextView_title.setTextColor(ContextCompat.getColor(context, R.color.color_primary_light));
                        holder.TextView_content.setTextColor(ContextCompat.getColor(context, R.color.white));
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


}
