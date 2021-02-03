package com.example.dailycoding.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
        private CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.wrong_answer_item_image);
            textView=(TextView)itemView.findViewById(R.id.wrong_answer_item_textView);
            imageButton=(ImageButton)itemView.findViewById(R.id.wrong_answer_item_button);
            contentText=(TextView)itemView.findViewById(R.id.wrong_answer_content);
            constraintLayout=(ConstraintLayout)itemView.findViewById(R.id.wrong_answer_bar);
            cardView=(CardView)itemView.findViewById(R.id.wrong_answer_item_bar);
        }
        public void onBind(WrongAnswerData data, int position){
            this.data=data;
            this.position=position;

            textView.setText(data.getText());
            contentText.setText(data.getContent());

            constraintLayout.setOnClickListener(this);

        }
        //오답노트 문제 클릭 시 레이아웃 확장 및 색 변경
        @Override
        public void onClick(View v) {
            WrongAnswerData data=arrayList.get(position);
            //data.setExpanded(!data.isExpanded());
            //notifyItemChanged(getAdapterPosition());

            //펼쳐진 상태에서 클릭한 경우
            if(data.isExpanded()==true) {

                imageButton.setBackground(context.getDrawable(R.drawable.arrow_down));
                //접으면 색상 다시 하얗게 돌아오기
                cardView.setCardBackgroundColor(v.getResources().getColor(R.color.white));
                textView.setTextColor(v.getResources().getColor(R.color.black));
                contentText.setTextColor(v.getResources().getColor(R.color.black));
                contentText.setVisibility(View.GONE);
                Log.d("tag","펼쳐진 상태를 클릭");
            }
            //접혀있는 상태를 클릭한 경우-> 펼쳐져야 함
            else    {

                imageButton.setBackground(context.getDrawable(R.drawable.arrow_up));

                if(data.isCorrect()==true){
                    cardView.setCardBackgroundColor(v.getResources().getColor(R.color.color_primary_light));
                }
                else if (data.isCorrect()==false){
                    cardView.setCardBackgroundColor(v.getResources().getColor(R.color.black));
                    textView.setTextColor(v.getResources().getColor(R.color.color_primary_light));
                    contentText.setTextColor(v.getResources().getColor(R.color.color_primary_light));
                }
                //내용 펼치기
                contentText.setVisibility(View.VISIBLE);
                Log.d("tag","접힌 상태를 클릭");
            }

            data.setExpanded(!data.isExpanded());


        }
    }
    @NonNull
    @Override
    public WrongAnswerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wrong_answer,null);
        WrongAnswerAdapter.ViewHolder viewHolder=new WrongAnswerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WrongAnswerAdapter.ViewHolder holder, int position) {
        boolean isExpanded=arrayList.get(position).isExpanded();
        boolean isCorrect=arrayList.get(position).isCorrect();
        holder.onBind(arrayList.get(position),position);
        //holder.contentText.setVisibility(isExpanded?View.VISIBLE: View.GONE);
        //뷰홀더 안의 내용이 맞은 문제라면
        if(isCorrect==true){
            holder.imageView.setImageResource(R.drawable.correct);
        }
        else{
            holder.imageView.setImageResource(R.drawable.uncorrect);
        }
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }
}
