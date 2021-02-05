package com.example.dailycoding.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dailycoding.R;
import java.util.ArrayList;

public class WrongAnswerActivity extends AppCompatActivity {
    private ArrayList<WrongAnswerData> arrayList;
    private WrongAnswerAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<WrongAnswerCorrect> correctArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_answer);


        initData();

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

}
