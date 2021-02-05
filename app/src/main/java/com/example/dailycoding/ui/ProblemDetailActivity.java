package com.example.dailycoding.ui;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.dailycoding.R;
import com.example.dailycoding.util.BaseActivity;

import org.w3c.dom.Text;


public class ProblemDetailActivity extends BaseActivity {

    private static final String TAG="ProblemDetailActivity";

    private ImageButton ImageButton_back, ImageButton_leftProblems;
    private ProgressBar progressBar;
    private TextView TextView_leftProblems, TextView_currentProblem, TextView_question;
    private Button Button_option1, Button_option2, Button_option3;
    private ProblemDialog problemDialog;

    private boolean isLeftProblem=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_detail);

        problemDialog=new ProblemDialog(ProblemDetailActivity.this);
        init();
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "ResourceAsColor"})
    private void init(){
        ImageButton_back=findViewById(R.id.ImageButton_problemDetail_back);
        ImageButton_leftProblems=findViewById(R.id.ImageButton_problemDetail_leftProblems);
        progressBar=findViewById(R.id.ProgressBar_problemDetail);
        TextView_leftProblems=findViewById(R.id.TextView_problemDetail_leftProblems);
        TextView_currentProblem=findViewById(R.id.TextView_detail_currentProblem);
        Button_option1=findViewById(R.id.Button_problemDetail_option1);
        Button_option2=findViewById(R.id.Button_problemDetail_option2);
        Button_option3=findViewById(R.id.Button_problemDetail_option3);
        TextView_question=findViewById(R.id.TextView_problemDetail_question);

        String question="다음 결과물을 갖기 위해서 빈칸에 어떤 코드가 들어가야 할까?\n          (Hello world)";
        int startIndex=question.indexOf("          ");
        String temp="          ";
        int lastIndex=startIndex+temp.length();
        Log.d(TAG, "startIndex: "+startIndex+" / lastIndex: "+lastIndex);
        SpannableString str = new SpannableString(question);
        str.setSpan(new BackgroundColorSpan(R.color.color_primary_gray), startIndex, lastIndex-1, 0);
        TextView_question.setText(str);

//        밑줄
//        TextView_currentProblem.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        if(!isLeftProblem) {
            TextView_leftProblems.setText("남은 문제 수 보기");
            progressBar.setVisibility(View.GONE);
            ImageButton_leftProblems.setBackgroundResource(R.drawable.ic_arrow_down_small);

        }
        else {
//            String str="앞으로  <b>5분제</b> 남았습니다.";
//            TextView_leftProblems.setText(Html.fromHtml(str));
            final SpannableStringBuilder sb = new SpannableStringBuilder("앞으로 5문제 남았습니다.");

            final StyleSpan boldText = new StyleSpan(android.graphics.Typeface.BOLD); // Span to make text bold
//            final StyleSpan iss = new StyleSpan(android.graphics.Typeface.ITALIC); //Span to make text italic
            sb.setSpan(boldText, 4, 6, Spannable.SPAN_INCLUSIVE_INCLUSIVE); // make first 4 characters Bold
//            sb.setSpan(iss, 4, 6, Spannable.SPAN_INCLUSIVE_INCLUSIVE); // make last 2 characters Italic

            TextView_leftProblems.setText(sb);
            progressBar.setVisibility(View.VISIBLE);
            ImageButton_leftProblems.setBackgroundResource(R.drawable.ic_arrow_up_small);
        }

        Button.OnClickListener onClickListener= new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    //뒤로가기
                    case R.id.ImageButton_problemDetail_back:
//                        ProblemDetailActivity.super.onBackPressed();
                        problemDialog.makeDialog(1);
                        break;
                     //남은 문제 보기
                    case R.id.ImageButton_problemDetail_leftProblems:
                        if(isLeftProblem){
                            TextView_leftProblems.setText("남은 문제 수 보기");
                            isLeftProblem=false;
                            progressBar.setVisibility(View.GONE);
                            ImageButton_leftProblems.setBackgroundResource(R.drawable.ic_arrow_down_small);
                        }
                        else{
                            final SpannableStringBuilder sb = new SpannableStringBuilder("앞으로 5문제 남았습니다.");

                            final StyleSpan boldText = new StyleSpan(android.graphics.Typeface.BOLD); // Span to make text bold
//            final StyleSpan iss = new StyleSpan(android.graphics.Typeface.ITALIC); //Span to make text italic
                            sb.setSpan(boldText, 4, 6, Spannable.SPAN_INCLUSIVE_INCLUSIVE); // make first 4 characters Bold
//            sb.setSpan(iss, 4, 6, Spannable.SPAN_INCLUSIVE_INCLUSIVE); // make last 2 characters Italic

                            TextView_leftProblems.setText(sb);
                            progressBar.setVisibility(View.VISIBLE);
                            ImageButton_leftProblems.setBackgroundResource(R.drawable.ic_arrow_up_small);

                            isLeftProblem=true;
                        }
                        break;
                    case R.id.Button_problemDetail_option1:
                        Button_option1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.color_primary_light));
                        Button_option1.setBackgroundResource(R.drawable.button_detail_option_black);
                        Button_option2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                        Button_option2.setBackgroundResource(R.drawable.button_detail_option);
                        Button_option3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                        Button_option3.setBackgroundResource(R.drawable.button_detail_option);
                        break;
                    case R.id.Button_problemDetail_option2:
                        Button_option2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.color_primary_light));
                        Button_option2.setBackgroundResource(R.drawable.button_detail_option_black);
                        Button_option1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                        Button_option1.setBackgroundResource(R.drawable.button_detail_option);
                        Button_option3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                        Button_option3.setBackgroundResource(R.drawable.button_detail_option);
                        break;
                    case R.id.Button_problemDetail_option3:
                        Button_option3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.color_primary_light));
                        Button_option3.setBackgroundResource(R.drawable.button_detail_option_black);
                        Button_option1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                        Button_option1.setBackgroundResource(R.drawable.button_detail_option);
                        Button_option2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                        Button_option2.setBackgroundResource(R.drawable.button_detail_option);
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

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        problemDialog.makeDialog(1);
    }
}