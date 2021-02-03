package com.example.dailycoding.ui;

import android.os.Bundle;

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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_answer);


        arrayList=new ArrayList<>();
        arrayList.add(new WrongAnswerData("변수 생성 예제 1번",true,"오답 문제 1",false));
        arrayList.add(new WrongAnswerData("변수 생성 예제 2번",false,"집합의 특징은 중복이 안되며 순서가 없다는 것입니다. \n s1=set('banana') \n s2=set('hello') \n print(s1) \n print(s2)\n\n  #결과값\n {'a','n','b'}\n",false));
        arrayList.add(new WrongAnswerData("변수 생성 예제 3번",false,"오답 문제 3",false));
        arrayList.add(new WrongAnswerData("변수 생성 예제 4번",true,"집합의 특징은 중복이 안되며 순서가 없다는 것입니다. \n s1=set('banana') \n s2=set('hello') \n print(s1) \n print(s2)\n\n  #결과값\n {'a','n','b'}\n",false));
        arrayList.add(new WrongAnswerData("변수 생성 예제 5번",false,"오답 문제 5",false));
        arrayList.add(new WrongAnswerData("변수 생성 예제 6번",false,"오답 문제 6",false));
        arrayList.add(new WrongAnswerData("변수 생성 예제 7번",true,"오답 문제 7",false));
        arrayList.add(new WrongAnswerData("변수 생성 예제 8번",true,"오답 문제 8",false));

        recyclerView=(RecyclerView)findViewById(R.id.wrong_answer_recyclerView);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        mainAdapter=new WrongAnswerAdapter(arrayList,this);
        recyclerView.setAdapter(mainAdapter);



    }
}
