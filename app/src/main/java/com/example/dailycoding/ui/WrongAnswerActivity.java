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
import com.google.gson.internal.bind.ArrayTypeAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WrongAnswerActivity extends AppCompatActivity {
    private static ArrayList<WrongAnswerData> arrayList;
    private WrongAnswerAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private  ArrayList<WrongAnswerCorrect> correctArrayList=new ArrayList<>();
    private Integer []ids=new Integer[50];
    private Context context;
    private WrongAnswerData wrongAnswerData;

    // retrofit2
    private static ServiceProblemApi problemService, problemService2;
    private static ArrayList<TheoryProblem> problemData = new ArrayList<>();
    private static ArrayList<GetOneProblem> getOneProblems=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_answer);
        arrayList=new ArrayList<>();

        //retrofit2 객체 할당
        problemService = ApiUtils.getServiceProblemApi();

        //세개의 선택지 생성 (일단 빈 내용으로 초기화)
        for (int i=0;i<3;i++){
            correctArrayList.add(new WrongAnswerCorrect("",true,false));
        }
        recyclerView=(RecyclerView)findViewById(R.id.wrong_answer_recyclerView);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        context=this;

        loadData();

        mainAdapter=new WrongAnswerAdapter(arrayList,this);
        recyclerView.setAdapter(mainAdapter);

    }

    private void loadData(){
        Log.d("처음 시작","처음 시작");

        problemService.getProblem("python","자료형").enqueue(new Callback<ArrayList<TheoryProblem>>() {
            @Override
            public void onResponse(Call<ArrayList<TheoryProblem>> call, Response<ArrayList<TheoryProblem>> response) {
                if(response.isSuccessful()) {
                    problemData = response.body();
                    Log.d("!!!", "성공" + problemData.toString());
                    for (int i = 0; i < problemData.size(); i++) {
                        ids[i] = problemData.get(i).getId();
                        //Log.d("ids","ids"+ids[i]);
                    }
                    for (int i=0;i<problemData.size();i++) {
                        int finalI = i;
                        problemService.getOneProblem(ids[i]).enqueue(new Callback<ArrayList<GetOneProblem>>() {
                            @Override
                            public void onResponse(Call<ArrayList<GetOneProblem>> call, Response<ArrayList<GetOneProblem>> response) {
                                if (response.isSuccessful()) {
                                    //그래도 getOneProblems는 '한' 문제
                                    getOneProblems = response.body();
                                    Log.d("getOne!!", "성공" + getOneProblems.toString());
                                    wrongAnswerData=new WrongAnswerData("예제"+getOneProblems.get(0).getProblemNumber(),true,getOneProblems.get(0).getQuestion()+getOneProblems.get(0).getCode(),
                                            false,correctArrayList);
                                    arrayList.add(wrongAnswerData);
                                    Log.d("arrayList",arrayList.toString());
                                    if (finalI ==problemData.size()-1){
                                        Log.d("마지막","마지막");
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
