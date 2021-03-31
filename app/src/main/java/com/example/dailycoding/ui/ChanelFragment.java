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


public class ChanelFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<NewsData> mDataset=new ArrayList<>();

    //retrofit2
    private static ServiceProblemApi problemService;
    private static ArrayList<News> chanelData=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //retrofit2 객체 할당
        problemService = ApiUtils.getServiceProblemApi();


        loadData();

        View view=inflater.inflate(R.layout.fragment_chanel, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.chanel_fragment);
        LinearLayoutManager LayoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(LayoutManager);


        return view;

    }
    private void loadData(){
        progressOn();
        problemService.getNews(false).enqueue(new Callback<ArrayList<News>>() {
            @Override
            public void onResponse(Call<ArrayList<News>> call, Response<ArrayList<News>> response) {
                if(response.isSuccessful()){
                    chanelData=response.body();
                    for (int i=0;i<chanelData.size();i++){
                        mDataset.add(new NewsData(chanelData.get(i).getTitle(),chanelData.get(i).getIntroduction(),chanelData.get(i).getHashTag(),chanelData.get(i).getContentOrder(),
                                chanelData.get(i).getRecommendation(),chanelData.get(i).getImageUrl(),chanelData.get(i).getLink()));
                    }
                    mAdapter=new ChanelAdapter(getActivity(),mDataset);
                    recyclerView.setAdapter(mAdapter);
                }
                else{
                    Log.d("CHANEL","실패");
                }
                progressOff();
            }

            @Override
            public void onFailure(Call<ArrayList<News>> call, Throwable t) {
                Log.d("CHANEL","아예 실패");
                progressOff();
            }
        });

    }
}