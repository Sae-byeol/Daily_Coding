
package com.example.dailycoding.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.example.dailycoding.api.ApiUtils;
import com.example.dailycoding.api.ServiceProblemApi;
import com.example.dailycoding.model.GetOneProblem;
import com.example.dailycoding.util.BaseActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProblemDetailActivity extends BaseActivity {

    private static final String TAG="ProblemDetailActivity";

    private ImageButton ImageButton_back, ImageButton_leftProblems;
    private ProgressBar progressBar;
    private TextView TextView_leftProblems, TextView_currentProblem, TextView_question, TextView_code;
    private Button Button_option1, Button_option2, Button_option3;
    private ProblemDialog problemDialog;
    private ArrayList<Course> dataList;
    private int currentProblemNumber;

    private ServiceProblemApi serviceProblemApi;

    private int currentId;

    private boolean isLeftProblem=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_detail);

        problemDialog=new ProblemDialog(ProblemDetailActivity.this);
        init();
        loadData();
//        setData();
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
        TextView_code=findViewById(R.id.TextView_problemDetail_code);

        Intent gIntent=getIntent();
        currentId=gIntent.getIntExtra("id", -1);
        dataList=new ArrayList<>();
        dataList= (ArrayList<Course>) gIntent.getSerializableExtra("dataList");

        for(Course course:dataList){
            Log.d(TAG, "course Id:"+course.getId());
        }

        progressBar.setMax(dataList.size());

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
                            int leftProblems=dataList.size()-currentProblemNumber+1;
                            String tempString="앞으로 "+leftProblems+"문제 남았습니다.";

                            final SpannableStringBuilder sb = new SpannableStringBuilder(tempString);

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

    public void loadData(){
        serviceProblemApi= ApiUtils.getServiceProblemApi();
        progressOn();

        serviceProblemApi.getOneProblem(currentId).enqueue(new Callback<ArrayList<GetOneProblem>>() {
            @Override
            public void onResponse(Call<ArrayList<GetOneProblem>> call, Response<ArrayList<GetOneProblem>> response) {
                if(response.isSuccessful()){
                    Log.d(TAG, currentId+"번의 데이터: "+response.body());
                    GetOneProblem getOneProblem=response.body().get(0);

                    TextView_code.setText(getOneProblem.getCode());
                    currentProblemNumber=getOneProblem.getProblemNumber();
                    TextView_currentProblem.setText("Q"+currentProblemNumber);
//                    int leftProblems=dataList.size()-currentProblemNumber+1;
//                    TextView_leftProblems.setText("앞으로 "+leftProblems+"문제 남았습니다.");
                    progressBar.setProgress(currentProblemNumber);
                    TextView_question.setText(getOneProblem.getQuestion());

                    String option=getOneProblem.getOption().replace("|", "`");
                    Log.d(TAG, "option:"+option);
                    String[] options=option.split("`");
                    for(int i=0;i<options.length;i++){
                        Log.d(TAG, i+"번째 보기"+options[i]+"");
                    }
                    Button_option1.setText(options[0].substring(3));
                    Button_option2.setText(options[1].substring(3));
                    Button_option3.setText(options[2].substring(3));



                    if(!isLeftProblem) {
                        TextView_leftProblems.setText("남은 문제 수 보기");
                        progressBar.setVisibility(View.GONE);
                        ImageButton_leftProblems.setBackgroundResource(R.drawable.ic_arrow_down_small);

                    }
                    else {
//            String str="앞으로  <b>5분제</b> 남았습니다.";
//            TextView_leftProblems.setText(Html.fromHtml(str));\
                        int leftProblems=dataList.size()-currentProblemNumber+1;
                        String tempString="앞으로 "+leftProblems+"문제 남았습니다.";
                        final SpannableStringBuilder sb = new SpannableStringBuilder(tempString);

                        final StyleSpan boldText = new StyleSpan(android.graphics.Typeface.BOLD); // Span to make text bold
//            final StyleSpan iss = new StyleSpan(android.graphics.Typeface.ITALIC); //Span to make text italic
                        sb.setSpan(boldText, 4, 6, Spannable.SPAN_INCLUSIVE_INCLUSIVE); // make first 4 characters Bold
//            sb.setSpan(iss, 4, 6, Spannable.SPAN_INCLUSIVE_INCLUSIVE); // make last 2 characters Italic

                        TextView_leftProblems.setText(sb);
                        progressBar.setVisibility(View.VISIBLE);

                        ImageButton_leftProblems.setBackgroundResource(R.drawable.ic_arrow_up_small);
                    }

                    progressOff();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<GetOneProblem>> call, Throwable t) {

            }
        });
    }

    @SuppressLint("ResourceAsColor")
    public void setData(){
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
            int leftProblems=dataList.size()-currentProblemNumber+1;
            String tempString="앞으로 "+leftProblems+"문제 남았습니다.";
            final SpannableStringBuilder sb = new SpannableStringBuilder(tempString);

            final StyleSpan boldText = new StyleSpan(android.graphics.Typeface.BOLD); // Span to make text bold
//            final StyleSpan iss = new StyleSpan(android.graphics.Typeface.ITALIC); //Span to make text italic
            sb.setSpan(boldText, 4, 6, Spannable.SPAN_INCLUSIVE_INCLUSIVE); // make first 4 characters Bold
//            sb.setSpan(iss, 4, 6, Spannable.SPAN_INCLUSIVE_INCLUSIVE); // make last 2 characters Italic

            TextView_leftProblems.setText(sb);
            progressBar.setVisibility(View.VISIBLE);

            ImageButton_leftProblems.setBackgroundResource(R.drawable.ic_arrow_up_small);
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        problemDialog.makeDialog(1);
    }
}