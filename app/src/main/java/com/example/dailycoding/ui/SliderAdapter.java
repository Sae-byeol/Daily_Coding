package com.example.dailycoding.ui;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.dailycoding.R;

import org.w3c.dom.Text;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder>{
    private List<Slideritem> slideritems;
    private ViewPager2 viewPager2;

    SliderAdapter(List<Slideritem> sliderItems, ViewPager2 viewPager2) {
        this.slideritems=sliderItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_slide_container,
                        parent,
                        false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setImage(slideritems.get(position));
        holder.setText(slideritems.get(position));
    }

    @Override
    public int getItemCount() {
        return slideritems.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView tv;

        SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.slide_imageview);
            tv=itemView.findViewById(R.id.slide_textview);
        }
        void setImage(Slideritem slideritem){
            imageView.setImageResource(slideritem.getImage());
        }
        void setText(Slideritem slideritem) {tv.setText(slideritem.getText());}
    }
}