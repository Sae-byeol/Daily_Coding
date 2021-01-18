package com.example.dailycoding.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dailycoding.R;

import java.util.ArrayList;

public class RankAdapter extends RecyclerView.Adapter<RankViewHolder> {

    private ArrayList<String> data;
    private Context context;

    public RankAdapter(ArrayList<String> data) {
        this.data = data;
    }


    @Override
    public RankViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rank, parent, false);
        RankViewHolder vh = new RankViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RankViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}

class RankViewHolder extends RecyclerView.ViewHolder {
    public View view;

    public RankViewHolder(View itemView) {
        super(itemView);
        view = itemView.findViewById(R.id.item_rank_temp);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
            }
        });

    }
}


