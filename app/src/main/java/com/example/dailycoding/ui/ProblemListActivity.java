package com.example.dailycoding.ui;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dailycoding.R;
import com.example.dailycoding.api.ApiUtils;
import com.example.dailycoding.api.ServiceProblemApi;
import com.example.dailycoding.api.ServiceUserApi;
import com.example.dailycoding.model.LastProblemResponse;
import com.example.dailycoding.model.TheoryProblem;
import com.example.dailycoding.util.BaseActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;


public class ProblemListActivity extends BaseActivity {

    private static final String TAG="ProblemListActivity";

    private RecyclerView recyclerView;
    private ImageButton ImageButton_back;
    private TextView TextView_title;

    private ArrayList<Course> dataList;
    private ArrayList<Integer> arr_id;
    private ServiceProblemApi serviceProblemApi;


    private String currentCategory;
    private String currentLanguage;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_list);

        init();
        loadData();
        showDialog();
//        initData();
//        showList();
    }

    private void loadData(){

        Log.d(TAG, "currentCategory, "+currentCategory);
//        Course course=new Course();
//        course.setTitle();
//        dataList.add(course);
        serviceProblemApi= ApiUtils.getServiceProblemApi();

        progressOn();
        dataList.clear();

        serviceProblemApi.getProblem(currentLanguage, currentCategory).enqueue(new Callback<ArrayList<TheoryProblem>>() {
            @Override
            public void onResponse(Call<ArrayList<TheoryProblem>> call, Response<ArrayList<TheoryProblem>> response) {
                if(response.isSuccessful()){
                    Log.d(TAG, response.body()+"");

//                    arr_id=new ArrayList<>();

                    for(TheoryProblem theoryProblem:response.body()){
                        Course course=new Course();
                        course.setTitle(theoryProblem.getCategory()+" "+theoryProblem.getProblemNumber()+"번");
                        course.setId(theoryProblem.getId());
                        course.setProblemNumber(theoryProblem.getProblemNumber());
                        dataList.add(course);

//                        arr_id.add();
                    }
                    Collections.sort(dataList, new Comparator<Course>() {
                        @Override
                        public int compare(Course o1, Course o2) {
                            return o1.getProblemNumber()-o2.getProblemNumber();
                        }
                    });

                    showList();
                    progressOff();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TheoryProblem>> call, Throwable t) {
                Toast.makeText(ProblemListActivity.this, "문제 데이터를 로드하는 데 실패하였습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDialog(){
        ProblemDialog problemDialog=new ProblemDialog(this);
        problemDialog.makeDialog(0);
    }

    private void init(){
        recyclerView=findViewById(R.id.RecyclerView_problemList);
        ImageButton_back=findViewById(R.id.ImageButton_problemList_back);
        TextView_title=findViewById(R.id.TextView_problemList_title);
        dataList=new ArrayList<>();

        //현재 카테고리 받아오기기
        Intent gIntent=getIntent();
        currentCategory=gIntent.getStringExtra("category");
        currentLanguage=gIntent.getStringExtra("language");

        TextView_title.setText(currentLanguage+" "+currentCategory);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ImageButton_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProblemListActivity.super.onBackPressed();
            }
        });
    }

    private void showList(){
        // specify an adapter (see also next example)
        Log.d(TAG, "showList currentLanguage:"+currentLanguage);
        mAdapter = new CourseAdapter(dataList, this, 1, currentLanguage);
        recyclerView.setAdapter(mAdapter);
    }

    private void initData(){
        for(int i=0;i<10;i++){
            Course course=new Course();
            course.setTitle("변수 생성 예제 "+(i+1)+"번");
            dataList.add(course);
        }
    }

}