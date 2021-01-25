package com.example.dailycoding.ui;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.dailycoding.R;
import com.example.dailycoding.util.BaseActivity;

import java.util.ArrayList;


public class ProblemListActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private ImageButton ImageButton_back;
    private ArrayList<Course> dataList;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_list);

        initData();
        init();
    }

    private void init(){
        recyclerView=findViewById(R.id.RecyclerView_problemList);
        ImageButton_back=findViewById(R.id.ImageButton_problemList_back);

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new CourseAdapter(dataList, this, 1);
        recyclerView.setAdapter(mAdapter);

        ImageButton_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProblemListActivity.super.onBackPressed();
            }
        });
    }

    private void initData(){
        dataList=new ArrayList<>();
        for(int i=0;i<10;i++){
            Course course=new Course();
            course.setTitle("변수 생성 예제 "+(i+1)+"번");
            dataList.add(course);
        }
    }

}