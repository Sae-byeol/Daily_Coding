package com.example.dailycoding.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailycoding.R;

import java.util.ArrayList;

public class WrongAnswerAdapter extends RecyclerView.Adapter<WrongAnswerAdapter.ViewHolder>{

    private ArrayList<WrongAnswerData> arrayList;

    public WrongAnswerAdapter(ArrayList<WrongAnswerData> arrayList) {
        this.arrayList = arrayList;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;
        private ImageButton imageButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.wrong_answer_item_image);
            textView=(TextView)itemView.findViewById(R.id.wrong_answer_item_textView);
            imageButton=(ImageButton)itemView.findViewById(R.id.wrong_answer_item_button);
        }
    }
    @NonNull
    @Override
    public WrongAnswerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wrong_answer_item,null);
        WrongAnswerAdapter.ViewHolder viewHolder=new WrongAnswerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WrongAnswerAdapter.ViewHolder holder, int position) {
        holder.textView.setText(arrayList.get(position).getText());
        if(arrayList.get(position).isCorrect()==true){
            holder.imageView.setImageResource(R.drawable.ic_correct);
        }
        else{
            holder.imageView.setImageResource(R.drawable.ic_wrong);
        }
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }
}
