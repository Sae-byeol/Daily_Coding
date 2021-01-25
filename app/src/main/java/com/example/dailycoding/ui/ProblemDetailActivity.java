package com.example.dailycoding.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dailycoding.R;
import com.example.dailycoding.util.BaseActivity;

import org.w3c.dom.Text;


public class ProblemDetailActivity extends BaseActivity {

    private ImageButton ImageButton_back, ImageButton_leftProblems;
    private ProgressBar progressBar;
    private TextView TextView_leftProblems;

    private boolean isLeftProblem=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_detail);

        init();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void init(){
        ImageButton_back=findViewById(R.id.ImageButton_problemDetail_back);
        ImageButton_leftProblems=findViewById(R.id.ImageButton_problemDetail_leftProblems);
        progressBar=findViewById(R.id.ProgressBar_problemDetail);
        TextView_leftProblems=findViewById(R.id.TextView_problemDetail_leftProblems);

        if(!isLeftProblem) {
            TextView_leftProblems.setText("남은 문제 수 보기");
            progressBar.setVisibility(View.GONE);
            ImageButton_leftProblems.setBackground(getDrawable(R.drawable.arrow_down));
        }
        else {
            TextView_leftProblems.setText("5문제 남았습니다.");
            progressBar.setVisibility(View.VISIBLE);
            ImageButton_leftProblems.setBackground(getDrawable(R.drawable.arrow_up));
        }

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
                        if(isLeftProblem){
                            TextView_leftProblems.setText("남은 문제 수 보기");
                            isLeftProblem=false;
                            progressBar.setVisibility(View.GONE);
                            ImageButton_leftProblems.setBackground(getDrawable(R.drawable.arrow_down));
                        }
                        else{
                            TextView_leftProblems.setText("5문제 남았습니다.");
                            isLeftProblem=true;
                            progressBar.setVisibility(View.VISIBLE);
                            ImageButton_leftProblems.setBackground(getDrawable(R.drawable.arrow_up));
                        }
                        break;
                }
            }
        };

        ImageButton_back.setOnClickListener(onClickListener);
        ImageButton_leftProblems.setOnClickListener(onClickListener);
    }

}