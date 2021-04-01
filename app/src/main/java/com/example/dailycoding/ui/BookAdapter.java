package com.example.dailycoding.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dailycoding.R;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> implements Filterable{
    private ArrayList<NewsData> mDataset;
    private ArrayList<NewsData> unfilterList;

    private Context context;

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String str=constraint.toString();
                if (str.isEmpty()){
                    mDataset=unfilterList;
                }
                else{
                    ArrayList<NewsData> filteringList=new ArrayList<>();

                    for (NewsData newsData : unfilterList){
                        if (newsData.getContent().toLowerCase().contains(str)){
                            filteringList.add(newsData);
                        }
                    }
                    mDataset=filteringList;
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=mDataset;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mDataset=(ArrayList<NewsData>)results.values;
                notifyDataSetChanged();
            }
        };
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        protected ImageView imageView;
        protected TextView textView_title;
        protected TextView textView_content;
        protected TextView textView_hash;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView=itemView.findViewById(R.id.news_item_imageView);
            this.textView_title=itemView.findViewById(R.id.news_item_textView);
            this.textView_content=itemView.findViewById(R.id.news_item_textView2);
            this.textView_hash=itemView.findViewById(R.id.news_item_textView3);
//            this.button=itemView.findViewById(R.id.news_item_button);


            //아이템 클릭하면 새로운 창 뜨도록
            itemView.setClickable(true);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //클릭한 아이템의 포지션
                    int pos=getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        Intent intent=new Intent(context, BookNewActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        intent.putExtra("title",mDataset.get(pos).getTitle());
                        intent.putExtra("content",mDataset.get(pos).getContent());
                        intent.putExtra("hash",mDataset.get(pos).getHash());
                        intent.putExtra("review",mDataset.get(pos).getReview());
                        intent.putExtra("course",mDataset.get(pos).getCourse());
                        intent.putExtra("image",mDataset.get(pos).getImageUrl());
                        intent.putExtra("link",mDataset.get(pos).getLink());
                        //액티비티 전환
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
    public BookAdapter(Context context, ArrayList<NewsData> myDataset){
        this.context=context;
        mDataset=myDataset;
        this.unfilterList=myDataset;
        //this.filterList=myDataset;
    }
    @NonNull
    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout view= (LinearLayout)LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
        ViewHolder holder=new ViewHolder(view);//view로 생성자 호출-> 각 내용 저장됨
        //CustomViewHolder 클래스 객체의 holder 리턴됨
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.ViewHolder holder, int position) {
        holder.textView_title.setText(mDataset.get(position).getTitle());
        holder.textView_content.setText(mDataset.get(position).getContent());
        holder.textView_hash.setText(mDataset.get(position).getHash());
        Glide.with(context)
                .load(mDataset.get(position).getImageUrl())
                .apply(new RequestOptions().circleCrop())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mDataset ==null ? 0 : mDataset.size();
    }



}