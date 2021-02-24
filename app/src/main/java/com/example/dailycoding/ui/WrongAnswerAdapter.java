package com.example.dailycoding.ui;

import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailycoding.R;
import com.example.dailycoding.model.GetOneProblem;

import java.util.ArrayList;

public class WrongAnswerAdapter extends RecyclerView.Adapter<WrongAnswerAdapter.ViewHolder>{

    private ArrayList<WrongAnswerData> arrayList;
    private Context context;

    //각 문제의 정보 담는 arrayList를 어댑터의 인수로 주기 -> 이 데이터로 Bind 하게 됨
    public WrongAnswerAdapter(ArrayList<WrongAnswerData> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context=context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView imageView;
        private TextView textView,contentText,tv1,tv2,tv3;
        private ImageButton imageButton,closebtn;
        private WrongAnswerData data;
        private int position;
        private ConstraintLayout constraintLayout;
        private CardView cardView;
        private LinearLayout btns;
        private TextView[] tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.wrong_answer_item_image);
            textView=(TextView)itemView.findViewById(R.id.wrong_answer_item_textView);
            imageButton=(ImageButton)itemView.findViewById(R.id.wrong_answer_item_button);
            contentText=(TextView)itemView.findViewById(R.id.wrong_answer_content);
            constraintLayout=(ConstraintLayout)itemView.findViewById(R.id.wrong_answer_bar);
            cardView=(CardView)itemView.findViewById(R.id.wrong_answer_item_bar);
            btns=(LinearLayout)itemView.findViewById(R.id.wrong_answer_btn);
            closebtn=(ImageButton)itemView.findViewById(R.id.wrong_answer_close);
            tv1=(TextView)itemView.findViewById(R.id.wrong_answer_tv1);
            tv2=(TextView)itemView.findViewById(R.id.wrong_answer_tv2);
            tv3=(TextView)itemView.findViewById(R.id.wrong_answer_tv3);
            tv=new TextView[3];
            tv[0]=tv1; tv[1]=tv2; tv[2]=tv3;

        }
        public void onBind(WrongAnswerData data, int position){
            this.data=data;
            this.position=position;
            //data 중에서 제목과 내용, 세개의 선택지 붙이기 -> 다 붙임
            textView.setText(data.getText());
            contentText.setText(data.getContent());
            for (int i=0;i<3;i++){
                tv[i].setText(data.getCorrectArrayList().get(i).getAnswer());
            }

            //애니메이션, 클릭 이벤트
            applyLayoutTransition();
            constraintLayout.setOnClickListener(this);
            closebtn.setOnClickListener(this);
        }


        private void applyLayoutTransition() {
            LayoutTransition transition = new LayoutTransition();
            transition.setDuration(300);
            transition.enableTransitionType(LayoutTransition.CHANGING);
            cardView.setLayoutTransition(transition);
        }

        //오답노트 문제 클릭 시 레이아웃 확장 및 색 변경
        @Override
        public void onClick(View v) {
            WrongAnswerData data=arrayList.get(position);
            //data.setExpanded(!data.isExpanded());
            //notifyItemChanged(getAdapterPosition());

            //펼쳐진 상태에서 클릭한 경우
            if(data.isExpanded()==true) {

                imageButton.setBackground(context.getDrawable(R.drawable.ic_arrow_down_small));
                //접으면 색상 다시 하얗게 돌아오기
                cardView.setCardBackgroundColor(v.getResources().getColor(R.color.white));
                textView.setTextColor(v.getResources().getColor(R.color.black));
                contentText.setTextColor(v.getResources().getColor(R.color.black));
                //내용 접기
                contentText.setVisibility(View.GONE);
                btns.setVisibility(View.GONE);
                closebtn.setVisibility(View.GONE);

            }
            //접혀있는 상태를 클릭한 경우-> 펼쳐져야 함
            else    {
                //맞은 문제인 경우의 색
                if(data.isCorrect()==true){
                    cardView.setCardBackgroundColor(v.getResources().getColor(R.color.color_primary_light));
                    imageButton.setBackground(context.getDrawable(R.drawable.ic_arrow_up_small));
                }
                //틀린문제인 경우의 색
                else if (data.isCorrect()==false){
                    cardView.setCardBackgroundColor(v.getResources().getColor(R.color.black));
                    textView.setTextColor(v.getResources().getColor(R.color.color_primary_light));
                    contentText.setTextColor(v.getResources().getColor(R.color.white));
                    imageButton.setBackground(context.getDrawable(R.drawable.ic_arrow_up_light));
                    closebtn.setImageResource(R.drawable.ic_arrow_up_light);

                }
                //내용 펼치기
                contentText.setVisibility(View.VISIBLE);
                btns.setVisibility(View.VISIBLE);
                closebtn.setVisibility(View.VISIBLE);

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
        boolean isCorrectAnswer,isChosen;
        holder.onBind(arrayList.get(position),position);
        //holder.contentText.setVisibility(isExpanded?View.VISIBLE: View.GONE);
        //뷰홀더 안의 내용이 맞은 문제라면
        if(isCorrect==true){
            holder.imageView.setImageResource(R.drawable.ic_correct);
            for(int i=0;i<3;i++){
                //맞은 문제의 선택지 세개 하나씩 확인하며 색 결정
                isCorrectAnswer=arrayList.get(position).getCorrectArrayList().get(i).isCorrect();
                isChosen=arrayList.get(position).getCorrectArrayList().get(i).isChosen();
                if (isCorrectAnswer && isChosen){
                    holder.tv[i].setBackgroundResource(R.drawable.round_border_blue_stroke);
                    holder.tv[i].setTextColor(ContextCompat.getColor(context, R.color.white));
                }
                else{
                    holder.tv[i].setBackgroundResource(R.drawable.round_border_transparent_stroke);
                    holder.tv[i].setTextColor(ContextCompat.getColor(context, R.color.black));
                }

            }
        }
        //틀렸던 문제라면
        else{
            holder.imageView.setImageResource(R.drawable.uncorrect);
            for (int i=0;i<3;i++) {
                isCorrectAnswer=arrayList.get(position).getCorrectArrayList().get(i).isCorrect();
                isChosen=arrayList.get(position).getCorrectArrayList().get(i).isChosen();
                if (isChosen ){
                    holder.tv[i].setBackgroundResource(R.drawable.round_border_red_stroke);
                    holder.tv[i].setTextColor(ContextCompat.getColor(context, R.color.white));
                }
                else if (isCorrectAnswer){
                    holder.tv[i].setBackgroundResource(R.drawable.round_border_light_stroke);
                    holder.tv[i].setTextColor(ContextCompat.getColor(context, R.color.black));
                }
                else {
                    holder.tv[i].setBackgroundResource(R.drawable.round_border_gray_stroke);
                    holder.tv[i].setTextColor(ContextCompat.getColor(context, R.color.white));
                }

            }
            //activity에서 준 값들을 잘 받긴 하는데 이 부분에서 자꾸 틀린문제들도 액티비티에서 준 arrayList의 첫번째 정보를 가져와서 색칠됨
            //계속 안되니까 일단은 하드코딩 하겠음
            /*holder.tv[0].setBackgroundResource(R.drawable.round_border_light_stroke);
            holder.tv[0].setTextColor(ContextCompat.getColor(context, R.color.black));
            holder.tv[1].setBackgroundResource(R.drawable.round_border_gray_stroke);
            holder.tv[1].setTextColor(ContextCompat.getColor(context, R.color.white));
            holder.tv[2].setBackgroundResource(R.drawable.round_border_red_stroke);
            holder.tv[2].setTextColor(ContextCompat.getColor(context, R.color.white));*/
        }
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }
}
