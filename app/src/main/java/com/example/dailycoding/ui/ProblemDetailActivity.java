package com.example.dailycoding.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.dailycoding.R;
import com.example.dailycoding.util.BaseActivity;



public class ProblemDetailActivity extends BaseActivity {

    private ImageButton ImageButton_back, ImageButton_leftProblems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_detail);

        init();
    }

    private void init(){
        ImageButton_back=findViewById(R.id.ImageButton_problemDetail_back);
        ImageButton_leftProblems=findViewById(R.id.ImageButton_problemDetail_leftProblems);

        Button.OnClickListener onClickListener= new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    //뒤로가기
                    case R.id.ImageButton_problemDetail_back:
                        ProblemDetailActivity.super.onBackPressed();
                        break;
                     //남은 문제 보기
                    case R.id.ImageButton_problemDetail_leftProblems:
                        break;
                }
            }
        };

        ImageButton_back.setOnClickListener(onClickListener);
        ImageButton_leftProblems.setOnClickListener(onClickListener);
    }

}