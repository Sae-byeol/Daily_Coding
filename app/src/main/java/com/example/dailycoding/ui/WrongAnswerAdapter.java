package com.example.dailycoding.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailycoding.R;

import java.util.ArrayList;

public class WrongAnswerAdapter extends RecyclerView.Adapter<WrongAnswerAdapter.ViewHolder>{

    private ArrayList<WrongAnswerData> arrayList;
    private Context context;

    public WrongAnswerAdapter(ArrayList<WrongAnswerData> arrayList,Context context) {
        this.arrayList = arrayList;
        this.context=context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView imageView;
        private TextView textView;
        private ImageButton imageButton;
        private TextView contentText;
        private WrongAnswerData data;
        private int position;
        private ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.wrong_answer_item_image);
            textView=(TextView)itemView.findViewById(R.id.wrong_answer_item_textView);
            imageButton=(ImageButton)itemView.findViewById(R.id.wrong_answer_item_button);
            contentText=(TextView)itemView.findViewById(R.id.wrong_answer_content);
            constraintLayout=(ConstraintLayout)itemView.findViewById(R.id.wrong_answer_item_bar);
        }
        public void onBind(WrongAnswerData data, int position){
            this.data=data;
            this.position=position;

            textView.setText(data.getText());
            contentText.setText(data.getContent());

            constraintLayout.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            WrongAnswerData data=arrayList.get(getAdapterPosition());
            data.setExpanded(!data.isExpanded());
            notifyItemChanged(getAdapterPosition());

            if(data.isExpanded()) imageButton.setBackground(context.getDrawable(R.drawable.arrow_down));
            else    imageButton.setBackground(context.getDrawable(R.drawable.arrow_up));
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
        boolean isExpanded=arrayList.get(position).isExpanded();
        holder.onBind(arrayList.get(position),position);
        holder.contentText.setVisibility(isExpanded?View.VISIBLE: View.GONE);
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
