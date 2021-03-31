package com.example.dailycoding.ui;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dailycoding.R;
import com.example.dailycoding.api.ApiUtils;
import com.example.dailycoding.api.ServiceProblemApi;
import com.example.dailycoding.model.News;
import com.example.dailycoding.util.BaseFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BookFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private static ArrayList<NewsData> mDataset=new ArrayList<>();

    //retrofit2
    private static ServiceProblemApi problemService;
    private static ArrayList<News> bookData=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //retrofit2 객체 할당
        problemService = ApiUtils.getServiceProblemApi();

        loadData();


        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_book, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.book_fragment);
        LinearLayoutManager LayoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(LayoutManager);


        return view;
    }
    private void loadData(){
        progressOn();
        mDataset.clear();
        problemService.getNews(true).enqueue(new Callback<ArrayList<News>>() {
            @Override
            public void onResponse(Call<ArrayList<News>> call, Response<ArrayList<News>> response) {
                if(response.isSuccessful()){
                    bookData=response.body();
                    for (int i=0;i<bookData.size();i++){
                        mDataset.add(new NewsData(bookData.get(i).getTitle(),bookData.get(i).getIntroduction(),bookData.get(i).getHashTag(),bookData.get(i).getContentOrder(),
                                bookData.get(i).getRecommendation(),bookData.get(i).getImageUrl(),bookData.get(i).getLink()));

                    }
                    //Log.d("BOOK",mDataset.toString());
                    mAdapter=new BookAdapter(getActivity(),mDataset);
                    recyclerView.setAdapter(mAdapter);
                }
                else{
                    Log.d("BOOK","실패");
                }
                progressOff();
            }

            @Override
            public void onFailure(Call<ArrayList<News>> call, Throwable t) {
                Log.d("BOOK","아예 실패");
                progressOff();
            }
        });
    }
}