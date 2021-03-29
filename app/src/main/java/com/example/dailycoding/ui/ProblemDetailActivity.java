
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
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.dailycoding.R;
import com.example.dailycoding.api.ApiUtils;
import com.example.dailycoding.api.ServiceProblemApi;
import com.example.dailycoding.api.ServiceUserApi;
import com.example.dailycoding.model.CheckAnswerBody;
import com.example.dailycoding.model.CheckAnswerResponse;
import com.example.dailycoding.model.GetOneProblem;
import com.example.dailycoding.util.BaseActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProblemDetailActivity extends BaseActivity {

    private static final String TAG="ProblemDetailActivity";

    private ImageButton ImageButton_back, ImageButton_leftProblems, ImageButton_previous, ImageButton_next;
    private ProgressBar progressBar;
    private TextView TextView_leftProblems, TextView_currentProblem, TextView_question, TextView_code, TextView_result;
    private Button Button_option1, Button_option2, Button_option3;

    private ProblemDialog problemDialog;
    private ArrayList<Course> dataList;
    private int currentProblemNumber, currentSelect, currentAnswer, currentPosition;
    private String currentCode;

    private ServiceProblemApi serviceProblemApi;
    private ServiceUserApi serviceUserApi;

    private int currentId;
    private String currentLanguage;

    private boolean isLeftProblem=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_detail);

        problemDialog=new ProblemDialog(ProblemDetailActivity.this);
        init();
        loadProblemData(currentId);
//        setData();
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "ResourceAsColor"})
    private void init(){
        ImageButton_back=findViewById(R.id.ImageButton_problemDetail_back);
        ImageButton_leftProblems=findViewById(R.id.ImageButton_problemDetail_leftProblems);
        progressBar=findViewById(R.id.ProgressBar_problemDetail);
        TextView_leftProblems=findViewById(R.id.TextView_problemDetail_leftProblems);
        TextView_currentProblem=findViewById(R.id.TextView_detail_currentProblem);
        TextView_result=findViewById(R.id.TextView_problemDetail_result_content);
        Button_option1=findViewById(R.id.Button_problemDetail_option1);
        Button_option2=findViewById(R.id.Button_problemDetail_option2);
        Button_option3=findViewById(R.id.Button_problemDetail_option3);
        TextView_question=findViewById(R.id.TextView_problemDetail_question);
        TextView_code=findViewById(R.id.TextView_problemDetail_code);
        ImageButton_previous=findViewById(R.id.ImageButton_problemDetail_previous);
        ImageButton_next=findViewById(R.id.ImageButton_problemDetail_next);


        Intent gIntent=getIntent();
        currentId=gIntent.getIntExtra("id", -1);
        currentPosition=gIntent.getIntExtra("position", -1);
//        currentLanguage=gIntent.getStringExtra("language");
        Log.d(TAG, "currentId: "+currentId+" / currentPosition: "+currentPosition+" / currentLang: "+currentLanguage);
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
                        Log.d(TAG, "문제 정답:"+currentAnswer);
//                        Toast.makeText(getApplicationContext(), "1번 버튼 클릭", Toast.LENGTH_SHORT).show();

                        Button_option1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.color_primary_light));
                        Button_option1.setBackgroundResource(R.drawable.button_detail_option_black);
                        Button_option2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                        Button_option2.setBackgroundResource(R.drawable.button_detail_option);
                        Button_option3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                        Button_option3.setBackgroundResource(R.drawable.button_detail_option);

//                        currentSelect=1;
                        if(currentAnswer==1){
                            Toast.makeText(ProblemDetailActivity.this, "정답입니다.", Toast.LENGTH_SHORT).show();
                            checkProblem(currentId, 1, currentLanguage);
                        }
                        else{
                            Toast.makeText(ProblemDetailActivity.this, "오답입니다.", Toast.LENGTH_SHORT).show();
                            checkProblem(currentId, 0, currentLanguage);
                        }
                        break;
                    case R.id.Button_problemDetail_option2:
                        Log.d(TAG, "문제 정답:"+currentAnswer);

                        Button_option2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.color_primary_light));
                        Button_option2.setBackgroundResource(R.drawable.button_detail_option_black);
                        Button_option1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                        Button_option1.setBackgroundResource(R.drawable.button_detail_option);
                        Button_option3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                        Button_option3.setBackgroundResource(R.drawable.button_detail_option);

//                        currentSelect=2;
                        if(currentAnswer==2){
                            Toast.makeText(ProblemDetailActivity.this, "정답입니다.", Toast.LENGTH_SHORT).show();
                            checkProblem(currentId, 1, currentLanguage);
                        }
                        else{
                            Toast.makeText(ProblemDetailActivity.this, "오답입니다.", Toast.LENGTH_SHORT).show();
                            checkProblem(currentId, 0, currentLanguage);

                        }
                        break;
                    case R.id.Button_problemDetail_option3:
                        Log.d(TAG, "문제 정답:"+currentAnswer);

                        Button_option3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.color_primary_light));
                        Button_option3.setBackgroundResource(R.drawable.button_detail_option_black);
                        Button_option1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                        Button_option1.setBackgroundResource(R.drawable.button_detail_option);
                        Button_option2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                        Button_option2.setBackgroundResource(R.drawable.button_detail_option);

                        if(currentAnswer==3){
                            Toast.makeText(ProblemDetailActivity.this, "정답입니다.", Toast.LENGTH_SHORT).show();
                            checkProblem(currentId, 1, currentLanguage);
                        }
                        else{
                            Toast.makeText(ProblemDetailActivity.this, "오답입니다.", Toast.LENGTH_SHORT).show();
                            checkProblem(currentId, 0, currentLanguage);
                        }
                        break;
                        //다음문제 버튼
                    case R.id.ImageButton_problemDetail_next:
//                        loadData(++currentId);
                        if(currentPosition==dataList.size()-1){
                            Toast.makeText(getApplicationContext(), "다음 문제가 없습니다.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            currentPosition++;
                            currentId=dataList.get(currentPosition).getId();
                            loadProblemData(currentId);
                        }
                        break;
                        //이전문제 버튼
                    case R.id.ImageButton_problemDetail_previous:
                        if(currentPosition==0){
                            Toast.makeText(getApplicationContext(), "이전 문제가 없습니다.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            currentPosition--;
                            currentId=dataList.get(currentPosition).getId();
                            loadProblemData(currentId);
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
        ImageButton_previous.setOnClickListener(onClickListener);
        ImageButton_next.setOnClickListener(onClickListener);

    }

    public void loadProblemData(int id){
        serviceProblemApi= ApiUtils.getServiceProblemApi();
        progressOn();

        Button_option1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
        Button_option1.setBackgroundResource(R.drawable.button_detail_option);
        Button_option2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
        Button_option2.setBackgroundResource(R.drawable.button_detail_option);
        Button_option3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
        Button_option3.setBackgroundResource(R.drawable.button_detail_option);

        serviceProblemApi.getOneProblem(id).enqueue(new Callback<ArrayList<GetOneProblem>>() {
            @Override
            public void onResponse(Call<ArrayList<GetOneProblem>> call, Response<ArrayList<GetOneProblem>> response) {
                if(response.isSuccessful()){
                    Log.d(TAG, currentId+"번의 데이터: "+response.body());
                    GetOneProblem getOneProblem=response.body().get(0);

                    currentLanguage=getOneProblem.getLanguage();
                    currentCode=getOneProblem.getCode();
                    String[] myCode=currentCode.split("___");

                    Log.d(TAG, "currentCode:"+currentCode);
                    TextView_code.setText(" "+currentCode);

//                    Log.d(TAG, "myCode[0]:"+myCode[0]+", myCode[1]:"+myCode[1]);
//                    if(myCode.length>1){
//                        TextView_code.setText(" "+myCode[0]);
//                        TextView_result.setText(myCode[1]);
//                        TextView_result.setVisibility(View.VISIBLE);
//                    }
//                    else {
//                        TextView_code.setText(" "+currentCode);
//                        TextView_result.setText("");
//                        TextView_result.setVisibility(View.INVISIBLE);
//                    };

                    currentProblemNumber=getOneProblem.getProblemNumber();
                    TextView_currentProblem.setText("Q"+currentProblemNumber);
//                    int leftProblems=dataList.size()-currentProblemNumber+1;
//                    TextView_leftProblems.setText("앞으로 "+leftProblems+"문제 남았습니다.");
                    progressBar.setProgress(currentProblemNumber);
                    TextView_question.setText(getOneProblem.getQuestion());
                    currentAnswer=Integer.parseInt(String.valueOf(getOneProblem.getAnswer().charAt(0)));

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

    //채점
    public void checkProblem(int question, int answer, String language){
        Log.d(TAG, "checkProblem question: "+question+" answer: "+answer+" language: "+language);
        serviceUserApi=ApiUtils.getServiceUserApi();
        progressOn();

        serviceUserApi.postCheckAnswer(new CheckAnswerBody(question, answer, language)).enqueue(new Callback<CheckAnswerResponse>() {
            @Override
            public void onResponse(Call<CheckAnswerResponse> call, Response<CheckAnswerResponse> response) {
                if(response.isSuccessful()){
                    Log.d(TAG, "채점 status"+response.body().getStatus());
                    Toast.makeText(ProblemDetailActivity.this, "채점에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ProblemDetailActivity.this, "채점에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "response isn't successful, response error code "+response.code());
                }
            }

            @Override
            public void onFailure(Call<CheckAnswerResponse> call, Throwable t) {
                Toast.makeText(ProblemDetailActivity.this, "채점에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "채점 실패 메시지"+t.getMessage());
            }
        });
        progressOff();

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