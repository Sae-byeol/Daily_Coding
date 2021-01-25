package com.example.dailycoding.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dailycoding.R;
import com.example.dailycoding.util.BaseFragment;

import java.util.ArrayList;


public class PromotionFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<NewsData> mDataset=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDataset.add(new NewsData("promotion 1 title","promotion 1 content","promotion 1 review"));
        mDataset.add(new NewsData("promotion 2 title","promotion 2 content","promotion 2 review"));
        mDataset.add(new NewsData("promotion 3 title","promotion 3 content","promotion 3 review"));
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_promotion, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.promotion_fragment);
        LinearLayoutManager LayoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(LayoutManager);
        mAdapter=new NewsAdapter(getActivity(),mDataset);
        recyclerView.setAdapter(mAdapter);

        return view;
    }
}