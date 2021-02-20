package com.example.dailycoding.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WrongAnswerActivity extends AppCompatActivity {
    private ArrayList<WrongAnswerData> arrayList;
    private WrongAnswerAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<WrongAnswerCorrect> correctArrayList;
    //onResponse 아닌 부분에서도 null아닌 상태로 사용하려면 아마 static??
    private Integer []ids=new Integer[50];

    // retrofit2
    private ServiceProblemApi problemService;
    private ArrayList<TheoryProblem> problemData = new ArrayList<>();
    private ArrayList<GetOneProblem> getOneProblems=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_answer);

        //retrofit2 객체 할당
        problemService = ApiUtils.getServiceProblemApi();


        initData();

        loadData();

        recyclerView=(RecyclerView)findViewById(R.id.wrong_answer_recyclerView);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        mainAdapter=new WrongAnswerAdapter(arrayList,this);
        recyclerView.setAdapter(mainAdapter);
    }
    private void initData(){
        //디폴트
        correctArrayList=new ArrayList<>();
        for (int i=0;i<3;i++){
            correctArrayList.add(new WrongAnswerCorrect("",true,false));
        }
        arrayList=new ArrayList<>();
        arrayList.add(new WrongAnswerData("변수 생성 예제 1번",true,"집합의 특징은 중복이 안되며 순서가 없다는 것입니다. \n s1=set('banana') \n s2=set('hello') \n print(s1) \n print(s2)\n\n  #결과값\n {'a','n','b'}\n",false,correctArrayList));
        arrayList.add(new WrongAnswerData("변수 생성 예제 2번",false,"집합의 특징은 중복이 안되며 순서가 없다는 것입니다. \n s1=set('banana') \n s2=set('hello') \n print(s1) \n print(s2)\n\n  #결과값\n {'a','n','b'}\n",false,correctArrayList));
        arrayList.add(new WrongAnswerData("변수 생성 예제 3번",false,"집합의 특징은 중복이 안되며 순서가 없다는 것입니다. \n s1=set('banana') \n s2=set('hello') \n print(s1) \n print(s2)\n\n  #결과값\n {'a','n','b'}\n",false,correctArrayList));
        arrayList.add(new WrongAnswerData("변수 생성 예제 4번",true,"집합의 특징은 중복이 안되며 순서가 없다는 것입니다. \n s1=set('banana') \n s2=set('hello') \n print(s1) \n print(s2)\n\n  #결과값\n {'a','n','b'}\n",false,correctArrayList));
        arrayList.add(new WrongAnswerData("변수 생성 예제 5번",false,"집합의 특징은 중복이 안되며 순서가 없다는 것입니다. \n s1=set('banana') \n s2=set('hello') \n print(s1) \n print(s2)\n\n  #결과값\n {'a','n','b'}\n",false,correctArrayList));
        arrayList.add(new WrongAnswerData("변수 생성 예제 6번",false,"집합의 특징은 중복이 안되며 순서가 없다는 것입니다. \n s1=set('banana') \n s2=set('hello') \n print(s1) \n print(s2)\n\n  #결과값\n {'a','n','b'}\n",false,correctArrayList));
        arrayList.add(new WrongAnswerData("변수 생성 예제 7번",true,"집합의 특징은 중복이 안되며 순서가 없다는 것입니다. \n s1=set('banana') \n s2=set('hello') \n print(s1) \n print(s2)\n\n  #결과값\n {'a','n','b'}\n",false,correctArrayList));
        arrayList.add(new WrongAnswerData("변수 생성 예제 8번",true,"집합의 특징은 중복이 안되며 순서가 없다는 것입니다. \n s1=set('banana') \n s2=set('hello') \n print(s1) \n print(s2)\n\n  #결과값\n {'a','n','b'}\n",false,correctArrayList));
        //각 문제마다 임시로 선택지 줌
        for (int i=0;i<arrayList.size();i++){
            if(arrayList.get(i).isCorrect()==true){
                //맞은 문제인경우의 임시 선택지
                arrayList.get(i).getCorrectArrayList().get(0).setAnswer("{'h','e','l','o'}");
                arrayList.get(i).getCorrectArrayList().get(0).setChosen(true);
                arrayList.get(i).getCorrectArrayList().get(0).setCorrect(true);
                arrayList.get(i).getCorrectArrayList().get(1).setAnswer("h,e,l,o");
                arrayList.get(i).getCorrectArrayList().get(1).setChosen(false);
                arrayList.get(i).getCorrectArrayList().get(1).setCorrect(false);
                arrayList.get(i).getCorrectArrayList().get(2).setAnswer("{'hello'}");
                arrayList.get(i).getCorrectArrayList().get(2).setChosen(false);
                arrayList.get(i).getCorrectArrayList().get(2).setCorrect(false);
            }
            else if (arrayList.get(i).isCorrect()==false){
                //Log.d("틀린 문제","값 주기 "+i);
                //틀렸던 문제인 경우
                arrayList.get(i).getCorrectArrayList().get(0).setAnswer("{'h','e','l','o'}");
                arrayList.get(i).getCorrectArrayList().get(0).setChosen(false);
                arrayList.get(i).getCorrectArrayList().get(0).setCorrect(true);
                arrayList.get(i).getCorrectArrayList().get(1).setAnswer("h,e,l,o");
                arrayList.get(i).getCorrectArrayList().get(1).setChosen(false);
                arrayList.get(i).getCorrectArrayList().get(1).setCorrect(false);
                arrayList.get(i).getCorrectArrayList().get(2).setAnswer("{'hello'}");
                arrayList.get(i).getCorrectArrayList().get(2).setChosen(true);
                arrayList.get(i).getCorrectArrayList().get(2).setCorrect(false);
            }
        }

    }
    private void loadData(){
        problemService.getProblem("python","자료형").enqueue(new Callback<ArrayList<TheoryProblem>>() {
            @Override
            public void onResponse(Call<ArrayList<TheoryProblem>> call, Response<ArrayList<TheoryProblem>> response) {
                if(response.isSuccessful()){
                    problemData=response.body();
                    Log.d("!!!","성공"+problemData.toString());
                    //데이터의 멤버 중 id 값만 필요
                    //Log.d("!!!","성공"+problemData.get(0).getId());
                    for (int i=0;i<problemData.size();i++){
                        ids[i]=problemData.get(i).getId();
                        Log.d("ids","ids"+ids[i]);
                    }
                    //위에서 저장한 id들을 가지고 단일문제 api 사용하여 문제 내용 가져오기
                    for (int i=0;i<problemData.size();i++){
                        problemService.getOneProblem(ids[i]).enqueue(new Callback<ArrayList<GetOneProblem>>() {
                            @Override
                            public void onResponse(Call<ArrayList<GetOneProblem>> call, Response<ArrayList<GetOneProblem>> response) {
                                if (response.isSuccessful()){
                                    getOneProblems=response.body();
                                    Log.d("getOne!!","성공"+getOneProblems.toString());
                                }
                                else{
                                    Log.d("getOne!!","실패");
                                }
                            }

                            @Override
                            public void onFailure(Call<ArrayList<GetOneProblem>> call, Throwable t) {
                                Log.d("getOne!!","아예 실패");
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
