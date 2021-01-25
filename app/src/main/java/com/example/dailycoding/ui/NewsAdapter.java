package com.example.dailycoding.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailycoding.R;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private String[] mDataset;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder{
        protected ImageView imageView;
        protected TextView textView_title;
        protected TextView textView_content;
        protected TextView textView_review;
        protected Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView=itemView.findViewById(R.id.news_item_imageView);
            this.textView_title=itemView.findViewById(R.id.news_item_textView);
            this.textView_content=itemView.findViewById(R.id.news_item_textView2);
            this.textView_review=itemView.findViewById(R.id.news_item_textView3);
//            this.button=itemView.findViewById(R.id.news_item_button);
        }
    }
    public NewsAdapter(Context context,String[] myDataset){
        this.context=context;
        mDataset=myDataset;
    }
    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout view= (LinearLayout)LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
        ViewHolder holder=new ViewHolder(view);//view로 생성자 호출-> 각 내용 저장됨
        //CustomViewHolder 클래스 객체의 holder 리턴됨
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        holder.textView_title.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset ==null ? 0 : mDataset.length;
    }

}
