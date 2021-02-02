package com.example.dailycoding.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.dailycoding.R;

import java.util.ArrayList;

//public class CourseSelectAdapter extends RecyclerView.Adapter<CourseSelectAdapter.ViewHolder> {
//
////    private List<Item> data;
////    private String[] data;
//    private ArrayList<String> dataList;
//
//    public CourseSelectAdapter(ArrayList<String> dataList) {
//        this.dataList = dataList;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View v = inflater.inflate(R.layout.item_course_title, parent, false);
//        return new ViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.textView.setText(dataList.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        return dataList.size();
//    }
//
//    static class ViewHolder extends RecyclerView.ViewHolder {
//
//        private TextView textView;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            textView = itemView.findViewById(R.id.TextView_course_title);
//        }
//    }
//}

public class CourseSelectAdapter extends RecyclerView.Adapter<CourseSelectAdapter.SliderViewHolder>{

    private ArrayList<String> dataList;
//    private ViewPager2 viewPager2;

    public CourseSelectAdapter(ArrayList<String> dataList, int loop) {
        this.dataList = new ArrayList<>();
//        this.dataList=dataList;
        for(int i=0;i<loop;i++){
            this.dataList.addAll(dataList);
        }
//        this.viewPager2=viewPager2;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_course_title,
                        parent,
                        false
                )
        );
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View v = inflater.inflate(R.layout.item_course_title, parent, false);
////       Pages must fill the whole ViewPager2 (use match_parent) => 이 에러 해결
//        v.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        return new SliderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.textView.setText(dataList.get(position));
        holder.textView.bringToFront();
//        if (position == dataList.size() - 2) {
//            viewPager2.post(runnable);
//        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private ConstraintLayout constraintLayout;
        SliderViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.TextView_course_title);
            constraintLayout=itemView.findViewById(R.id.ConstraintLayout_item_select);
        }
    }

    private Runnable runnable=new Runnable(){
        @Override
        public void run() {
            dataList.addAll(dataList);
            notifyDataSetChanged();
        }
    };

}
