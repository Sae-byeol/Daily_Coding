package com.example.dailycoding.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dailycoding.R;
import com.example.dailycoding.model.UserRank;
import com.example.dailycoding.util.App;
import com.example.dailycoding.util.BaseFragment;

import java.util.ArrayList;

public class RankAdapter extends RecyclerView.Adapter<RankViewHolder> {

    private ArrayList<UserRank> data;
    private Context context;

    public RankAdapter(ArrayList<UserRank> data) {
        this.data = data;
    }


    @Override
    public RankViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rank, parent, false);
        RankViewHolder vh = new RankViewHolder(v);
        context = parent.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(RankViewHolder holder, int position) {

        holder.tv_rank.setText(position+4+"위");
        holder.tv_name.setText(data.get(position).getName());

        if(data.get(position).getStar() != null) {
            holder.tv_star.setText(data.get(position).getStar());
        } else {
            holder.tv_star.setText(0+"");
        }
        if(data.get(position).getProfileUrl() != null) {
            Glide.with(context).load(data.get(position).getProfileUrl()).into(holder.iv);
        } else {
            // 기본 이미지 설정?
        }

//        App.getInstance().progressOFF();


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}

class RankViewHolder extends RecyclerView.ViewHolder {
    public View view;
    public ImageView iv;
    public TextView tv_rank;
    public TextView tv_name;
    public TextView tv_star;

    public RankViewHolder(View itemView) {
        super(itemView);

        view = itemView.findViewById(R.id.item_rank_temp);
        iv = itemView.findViewById(R.id.item_rank_imageview);
        tv_rank = itemView.findViewById(R.id.item_rank_textview_rank);
        tv_name = itemView.findViewById(R.id.item_rank_textview_name);
        tv_star = itemView.findViewById(R.id.item_rank_textview_star);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
            }
        });

    }
}


