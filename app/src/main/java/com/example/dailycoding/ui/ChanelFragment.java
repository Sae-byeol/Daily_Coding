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


public class ChanelFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<NewsData> mDataset=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mDataset.add(new NewsData("chanel 1 title","chanel 1 content","chanel 1 review"));
        mDataset.add(new NewsData("chanel 2 title","chanel 2 content","chanel 2 review"));
        mDataset.add(new NewsData("chanel 3 title","chanel 3 content","chanel 3 review"));


        View view=inflater.inflate(R.layout.fragment_chanel, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.chanel_fragment);
        LinearLayoutManager LayoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(LayoutManager);

        mAdapter=new NewsAdapter(getActivity(),mDataset);
        recyclerView.setAdapter(mAdapter);
        return view;

    }
}