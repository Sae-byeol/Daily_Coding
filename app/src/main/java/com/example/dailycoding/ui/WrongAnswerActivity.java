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
        setContentView(R.layout.wrong_answer);
        arrayList=new ArrayList<>();
        arrayList.add(new WrongAnswerData("변수 생성 예제 1",true,"오답 문제 1",false));
        arrayList.add(new WrongAnswerData("변수 생성 예제 2",false,"오답 문제 2",false));
        arrayList.add(new WrongAnswerData("변수 생성 예제 3",false,"오답 문제 3",false));
        arrayList.add(new WrongAnswerData("변수 생성 예제 4",true,"오답 문제 4",false));

        recyclerView=(RecyclerView)findViewById(R.id.wrong_answer_recyclerView);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        mainAdapter=new WrongAnswerAdapter(arrayList,this);
        recyclerView.setAdapter(mainAdapter);



    }
}
