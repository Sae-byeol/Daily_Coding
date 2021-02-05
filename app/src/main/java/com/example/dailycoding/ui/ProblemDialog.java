package com.example.dailycoding.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dailycoding.R;

public class ProblemDialog {

    private Context context;

    public ProblemDialog(Context context){
        this.context=context;
    }

//    type:0 => course 하위 페이지에서 호출 ( 다 풀지 않은 문제)
//    type:1=> course 문제풀이 화면 (뒤로가기 호출 시)
    public void makeDialog(int type){
        final Dialog dialog=new Dialog(context);

        dialog.setContentView(R.layout.problem_dialog);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.show();

        ImageButton ImageButton_close=dialog.findViewById(R.id.ImageButton_problemDialog_close);
        TextView message0=dialog.findViewById(R.id.TextView_problemDialog_message0);
        TextView message1=dialog.findViewById(R.id.TextView_problemDialog_message1);
        //이어서 풀기, 바로종료
        TextView btn_left=dialog.findViewById(R.id.TextView_problemDialog_left);
        //처음부터풀기, 저장하고 종료하기
        TextView btn_right=dialog.findViewById(R.id.TextView_problemDialog_right);

        ImageButton_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        if(type==0){
//            message.setText(Html.fromHtml(context.getString(R.string.problemDialog_message0)));
            message0.setVisibility(View.VISIBLE);
            message1.setVisibility(View.INVISIBLE);
            btn_left.setText("이어서 풀기");
            btn_right.setText("처음부터 풀기");
//            message.setText(context.getString(R.string.problemDialog_message0));
            btn_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //이어서 풀기
                }
            });
            btn_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //처음부터 풀기
                }
            });
        }
        else if(type==1){
//            message.setText(Html.fromHtml(context.getString(R.string.problemDialog_message1)));
            message0.setVisibility(View.INVISIBLE);
            message1.setVisibility(View.VISIBLE);
            btn_left.setText("바로 종료하기");
            btn_right.setText("저장하고 종료하기");
//            message.setText(context.getString(R.string.problemDialog_message1));
            btn_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //바로 종료하기
                    ((Activity)context).finish();
                }
            });
            btn_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //저장하고 종료하기
                    ((Activity)context).finish();

                }
            });
        }
    }
}
