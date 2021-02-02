package com.example.dailycoding.ui;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.os.Build;
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
    private TextView TextView_leftProblems, TextView_currentProblem;
    private Button Button_option1, Button_option2, Button_option3;

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
        TextView_currentProblem=findViewById(R.id.TextView_detail_currentProblem);
        Button_option1=findViewById(R.id.Button_problemDetail_option1);
        Button_option2=findViewById(R.id.Button_problemDetail_option2);
        Button_option3=findViewById(R.id.Button_problemDetail_option3);


//        밑줄
        TextView_currentProblem.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        if(!isLeftProblem) {
            TextView_leftProblems.setText("남은 문제 수 보기");
            progressBar.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                ImageButton_leftProblems.setBackground(getDrawable(R.drawable.arrow_down));
            }
        }
        else {
            TextView_leftProblems.setText("5문제 남았습니다.");
            progressBar.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                ImageButton_leftProblems.setBackground(getDrawable(R.drawable.arrow_up));
            }
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
                    case R.id.Button_problemDetail_option1:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            Button_option1.setTextColor(getColor(R.color.color_primary_light));
                            Button_option1.setBackground(getDrawable(R.drawable.button_detail_option_black));
                            Button_option2.setTextColor(getColor(R.color.black));
                            Button_option2.setBackground(getDrawable(R.drawable.button_detail_option));
                            Button_option3.setTextColor(getColor(R.color.black));
                            Button_option3.setBackground(getDrawable(R.drawable.button_detail_option));
                        }
                        break;
                    case R.id.Button_problemDetail_option2:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            Button_option2.setTextColor(getColor(R.color.color_primary_light));
                            Button_option2.setBackground(getDrawable(R.drawable.button_detail_option_black));
                            Button_option1.setTextColor(getColor(R.color.black));
                            Button_option1.setBackground(getDrawable(R.drawable.button_detail_option));
                            Button_option3.setTextColor(getColor(R.color.black));
                            Button_option3.setBackground(getDrawable(R.drawable.button_detail_option));
                        }
                        break;
                    case R.id.Button_problemDetail_option3:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            Button_option3.setTextColor(getColor(R.color.color_primary_light));
                            Button_option3.setBackground(getDrawable(R.drawable.button_detail_option_black));
                            Button_option1.setTextColor(getColor(R.color.black));
                            Button_option1.setBackground(getDrawable(R.drawable.button_detail_option));
                            Button_option2.setTextColor(getColor(R.color.black));
                            Button_option2.setBackground(getDrawable(R.drawable.button_detail_option));
                        }
                        break;
                }
            }
        };

        ImageButton_back.setOnClickListener(onClickListener);
        ImageButton_leftProblems.setOnClickListener(onClickListener);
        Button_option1.setOnClickListener(onClickListener);
        Button_option2.setOnClickListener(onClickListener);
        Button_option3.setOnClickListener(onClickListener);

    }

}