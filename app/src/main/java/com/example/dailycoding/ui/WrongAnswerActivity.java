package com.example.dailycoding.ui;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dailycoding.R;
import com.example.dailycoding.api.ApiUtils;
import com.example.dailycoding.api.ServiceProblemApi;
import com.example.dailycoding.api.ServiceUserApi;
import com.example.dailycoding.model.GetOneProblem;
import com.example.dailycoding.model.TheoryProblem;
import com.example.dailycoding.model.UserRank;
import com.example.dailycoding.util.BaseActivity;
import com.google.gson.internal.bind.ArrayTypeAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WrongAnswerActivity extends BaseActivity {
    private static ArrayList<WrongAnswerData> arrayList;
    private WrongAnswerAdapter mainAdapter;
    private ArrayList<WrongAnswerCorrect> correctArrayList;
    private final Integer []ids=new Integer[50];
    private WrongAnswerData wrongAnswerData;

    // retrofit2
    private static ServiceProblemApi problemService;
    private static ArrayList<TheoryProblem> problemData = new ArrayList<>();
    private static ArrayList<GetOneProblem> getOneProblems=new ArrayList<>();

    compare Compare=new compare();
    class compare implements Comparator<WrongAnswerData>{

        @Override
        public int compare(WrongAnswerData o1, WrongAnswerData o2) {
            return o1.getId().compareTo(o2.getId());
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_answer);
        arrayList=new ArrayList<>();

        //retrofit2 객체 할당
        problemService = ApiUtils.getServiceProblemApi();

        //세개의 선택지 생성 (일단 빈 내용으로 초기화)
        /*for (int i=0;i<3;i++){
            correctArrayList.add(new WrongAnswerCorrect("",true,false));
        }*/

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.wrong_answer_recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        loadData();

        mainAdapter=new WrongAnswerAdapter(arrayList,this);
        recyclerView.setAdapter(mainAdapter);

    }

    private void loadData(){
        Log.d("처음 시작","처음 시작");
        progressOn();
        problemService.getProblem("python","자료형").enqueue(new Callback<ArrayList<TheoryProblem>>() {
            @Override
            public void onResponse(Call<ArrayList<TheoryProblem>> call, Response<ArrayList<TheoryProblem>> response) {
                if(response.isSuccessful()) {
                    problemData = response.body();
                    assert problemData != null;
                    Log.d("!!!", "성공" + problemData.toString());
                    for (int i = 0; i < problemData.size(); i++) {
                        ids[i] = problemData.get(i).getId();

                    }
                    for (int i=0;i<problemData.size();i++) {
                        int finalI = i;
                        Log.d("ids","ids"+ids[i]);
                        problemService.getOneProblem(ids[i]).enqueue(new Callback<ArrayList<GetOneProblem>>() {
                            @Override
                            public void onResponse(Call<ArrayList<GetOneProblem>> call, Response<ArrayList<GetOneProblem>> response) {
                                if (response.isSuccessful()) {
                                    getOneProblems = response.body();
                                    Log.d("getOne!!", "성공" + getOneProblems.toString());
                                    String [] answerOption=new String[3];

                                    answerOption=getOneProblems.get(0).getOption().split("\\|",3);

                                    correctArrayList=new ArrayList<>();
                                    for (int i=0;i<3;i++){
                                        correctArrayList.add(new WrongAnswerCorrect(answerOption[i],true,false));
                                        Log.d("선택지",correctArrayList.get(i).toString());
                                    }

                                    //Log.d("선택지",getOneProblems.get(0).getOption());
                                    if (finalI==1){
                                        //임의로 틀린 문제 만들기
                                        wrongAnswerData=new WrongAnswerData("예제 "+getOneProblems.get(0).getProblemNumber(),false,getOneProblems.get(0).getQuestion()+getOneProblems.get(0).getCode(),
                                                false,correctArrayList,getOneProblems.get(0).getId());
                                        /*wrongAnswerData.getCorrectArrayList().get(0).setAnswer("틀린답1");
                                        wrongAnswerData.getCorrectArrayList().get(0).setChosen(false);
                                        wrongAnswerData.getCorrectArrayList().get(0).setCorrect(true);
                                        wrongAnswerData.getCorrectArrayList().get(1).setAnswer("h,e,l,o");
                                        wrongAnswerData.getCorrectArrayList().get(1).setChosen(false);
                                        wrongAnswerData.getCorrectArrayList().get(1).setCorrect(false);
                                        wrongAnswerData.getCorrectArrayList().get(2).setAnswer("{'hello'}");
                                        wrongAnswerData.getCorrectArrayList().get(2).setChosen(true);
                                        wrongAnswerData.getCorrectArrayList().get(2).setCorrect(false);*/
                                    }
                                    else{
                                        wrongAnswerData=new WrongAnswerData("예제 "+getOneProblems.get(0).getProblemNumber(),true,getOneProblems.get(0).getQuestion()+getOneProblems.get(0).getCode(),
                                                false,correctArrayList,getOneProblems.get(0).getId());
                                        /*wrongAnswerData.getCorrectArrayList().get(0).setAnswer("{'h','e','l','o'}");
                                        wrongAnswerData.getCorrectArrayList().get(0).setChosen(true);
                                        wrongAnswerData.getCorrectArrayList().get(0).setCorrect(true);
                                        wrongAnswerData.getCorrectArrayList().get(1).setAnswer("h,e,l,o");
                                        wrongAnswerData.getCorrectArrayList().get(1).setChosen(false);
                                        wrongAnswerData.getCorrectArrayList().get(1).setCorrect(false);
                                        wrongAnswerData.getCorrectArrayList().get(2).setAnswer("{'hello'}");
                                        wrongAnswerData.getCorrectArrayList().get(2).setChosen(false);
                                        wrongAnswerData.getCorrectArrayList().get(2).setCorrect(false);*/
                                    }
                                    arrayList.add(wrongAnswerData);


                                    //for문의 마지막에서 어댑터 붙이기
                                    if (finalI ==problemData.size()-1){
                                        //마지막에 arrayList 한번 정렬 필요
                                        Collections.sort(arrayList,Compare);
                                        progressOff();
                                        mainAdapter.notifyDataSetChanged();
                                    }
                                } else {
                                    Log.d("getOne!!", "실패");
                                }
                            }
                            @Override
                            public void onFailure(Call<ArrayList<GetOneProblem>> call, Throwable t) {
                                Log.d("getOne!!", "아예 실패");
                            }
                        });
                    }
                }
                else
                    Log.d("!!!","실패");
            }
            @Override
            public void onFailure(Call<ArrayList<TheoryProblem>> call, Throwable t) {
                Log.d("!!!","아예 실패");
            }
        });
    }
}
