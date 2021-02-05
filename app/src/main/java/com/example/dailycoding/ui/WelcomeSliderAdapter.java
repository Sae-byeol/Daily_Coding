package com.example.dailycoding.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dailycoding.R;

public class WelcomeSliderAdapter extends RecyclerView.Adapter<WelcomeSliderAdapter.MyViewHolder> {
    private Context context;
    private int[] sliderImage;
    private String[] sliderText;

    public WelcomeSliderAdapter(Context context, int[] sliderImage, String[] sliderText) {
        this.context = context;
        this.sliderImage = sliderImage;
        this.sliderText = sliderText;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_welcomeimg, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindSliderImage(sliderImage[position]);
        holder.bindText(sliderText[position]);
    }

    @Override
    public int getItemCount() {
        return sliderImage.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.slide_imageview);
            mTextView = itemView.findViewById(R.id.slide_textview);
        }

        public void bindSliderImage(int imageURL) {
            Glide.with(context)
                    .load(imageURL)
                    .into(mImageView);
        }

        public void bindText(String text) {
            mTextView.setText(text);
        }
    }
}