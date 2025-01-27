package com.example.dailycoding.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailycoding.R;
import com.example.dailycoding.model.Course;
import com.example.dailycoding.model.RecentProblem;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> {

    private static final String TAG="CourseAdapter";

    private static ArrayList<Course> dataList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private Context context;
    //    isCourse:true이면 기출문제 선택, false이면 언어 소개
    private int type, problemIdx;
    private String language;
    private RecentProblem recentProblems;

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

    public CourseAdapter(ArrayList<Course> dataList, Context context, int type, String language, int problemIdx) {
        this.context=context;
        this.dataList = dataList;
        this.type=type;
        this.language=language;
        this.problemIdx=problemIdx;
    }

    public CourseAdapter(ArrayList<Course> dataList, Context context, int type, String language, RecentProblem recentProblems) {
        this.context=context;
        this.dataList = dataList;
        this.type=type;
        this.language=language;
        this.recentProblems=recentProblems;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView TextView_title, TextView_content;
        public RecyclerView recyclerView;
        public ConstraintLayout constraintLayout;
        public ImageView imageView, ImageView_lock;

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
                ImageView_lock=itemView.findViewById(R.id.ImageView_course_lock);
            }
            //언어소개 페이지
            else if(type==2){
                TextView_title = itemView.findViewById(R.id.TextView_language_list);
                TextView_content=itemView.findViewById(R.id.TextView_languageIntroduction_content);
                imageView=itemView.findViewById(R.id.ImageVIew_language_arrow);
                constraintLayout=itemView.findViewById(R.id.ConstraintLayout_item_language);
            }

//            constraintLayout.setOnClickListener(new View.OnClickListener() {
//                @SuppressLint("UseCompatLoadingForDrawables")
//                @Override
//                public void onClick(View v) {
//                    //코스 페이지
//                    if(type==0){
//                        Intent intent=new Intent(context, ProblemListActivity.class);
//                        context.startActivity(intent);
//                    }
//                    //문제 리스트 페이지
//                    else if(type==1){
//                        Intent intent=new Intent(context, ProblemDetailActivity.class);
//                        context.startActivity(intent);
//                    }
//                    //언어 소개 페이지
//                    else if(type==2){
//                        Course course = dataList.get(getAdapterPosition());
//                        course.setExpanded(!course.isExpanded());
//                        notifyItemChanged(getAdapterPosition());
//                        if(course.isExpanded()) {
//                            imageView.setBackground(context.getDrawable(R.drawable.ic_arrow_down_small));
//                            constraintLayout.setBackground(context.getDrawable(R.drawable.round_border_lang));
//                            TextView_title.setTextColor(ContextCompat.getColor(context, R.color.black));
//                        }
//                        else    {
//                            imageView.setBackground(context.getDrawable(R.drawable.ic_arrow_up_small));
//                            constraintLayout.setBackground(context.getDrawable(R.drawable.round_border_lang02));
//
//
//                            TextView_title.setTextColor(ContextCompat.getColor(context, R.color.color_primary_light));
//                            TextView_content.setTextColor(ContextCompat.getColor(context, R.color.white));
//                        }
//                    }
//                }
//            });
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

        if(type==1||type==0){
            holder.imageView.setBackground(context.getDrawable(R.drawable.ic_arrow_right));
            if(type==1){
                Log.d(TAG, "현재 problemNumber: "+dataList.get(position).getProblemNumber()+"현재 problemIdx: "+problemIdx);

                //잠기지 않은 경우
                if(problemIdx>=dataList.get(position).getProblemNumber()){
                    holder.ImageView_lock.setBackground(context.getDrawable(R.drawable.ic_user));
                }
                //잠긴 경우
                else{
                    holder.ImageView_lock.setBackground(context.getDrawable(R.drawable.ic_lock));
                }
            }
        }
        else{
            holder.TextView_content.setText(dataList.get(position).getContent());
            boolean isExpanded = dataList.get(position).isExpanded();
            holder.TextView_content.setVisibility(isExpanded?View.VISIBLE: View.GONE);
            if(isExpanded){
                holder.constraintLayout.setBackground(context.getDrawable(R.drawable.round_border_lang02));
                holder.TextView_title.setTextColor(ContextCompat.getColor(context, R.color.color_primary_light));
                holder.TextView_content.setTextColor(ContextCompat.getColor(context, R.color.white));
            }
            else{
                holder.TextView_title.setTextColor(ContextCompat.getColor(context, R.color.primary_font));
//                    holder.TextView_content.setTextColor(context.getColor(R.color.white));
                holder.constraintLayout.setBackground(context.getDrawable(R.drawable.round_border_lang));
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
                    switch(language){
                        case "python":
                            intent.putExtra("questionId", recentProblems.getRecentPython());
                            intent.putExtra("questionIdx", recentProblems.getRecentPythonIdx());
                            break;
                        case "java":
                            intent.putExtra("questionId", recentProblems.getRecentJava());
                            intent.putExtra("questionIdx", recentProblems.getRecentJavaIdx());
                            break;
                        case "cpp":
                            intent.putExtra("questionId", recentProblems.getRecentCpp());
                            intent.putExtra("questionIdx", recentProblems.getRecentCppIdx());
                            break;
                    }
                    context.startActivity(intent);
                }
                //문제 리스트 페이지
                else if(type==1){
//                    for(Course course:dataList){
//                        Log.d(TAG, "dataList:");
//
//                    }
                    //잠기지 않은 경우
                    if(problemIdx>=dataList.get(position).getProblemNumber()){
                        Intent intent=new Intent(context, ProblemDetailActivity.class);
                        intent.putExtra("id", dataList.get(position).getId());
                        intent.putExtra("position", position);
                        intent.putExtra("language, ", language);
                        Log.d(TAG, "CourseAdapter type 1 language "+language);
                        intent.putExtra("dataList", dataList);
                        context.startActivity(intent);
                    }
                    //잠긴 경우
                    else{
                        Toast.makeText(context, "문제가 잠겨있습니다. 이전 문제를 풀어주십시오.", Toast.LENGTH_SHORT).show();
                    }

                }
                //언어 소개 페이지
                else if(type==2){
                    Course course = dataList.get(position);
                    course.setExpanded(!course.isExpanded());
                    notifyItemChanged(position);
                    if(course.isExpanded()) {
                        holder.imageView.setBackground(context.getDrawable(R.drawable.ic_arrow_down_small));
                        holder.constraintLayout.setBackground(context.getDrawable(R.drawable.round_border_lang02));
                        holder.TextView_title.setTextColor(ContextCompat.getColor(context, R.color.black));
                    }
                    else    {
                        holder.imageView.setBackground(context.getDrawable(R.drawable.ic_arrow_up_small));
                        holder.constraintLayout.setBackground(context.getDrawable(R.drawable.round_border_lang));

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
