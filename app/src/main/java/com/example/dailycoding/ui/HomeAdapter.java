package com.example.dailycoding.ui;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dailycoding.R;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> {

    private ArrayList<String> data;
    private Context context;

    public HomeAdapter(ArrayList<String> data) {
        this.data = data;
    }


    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_news, parent, false);
        HomeViewHolder vh = new HomeViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}

class HomeViewHolder extends RecyclerView.ViewHolder {
    public View view;

    public HomeViewHolder(View itemView) {
        super(itemView);
        view = itemView.findViewById(R.id.item_temp);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
            }
        });

    }
}
