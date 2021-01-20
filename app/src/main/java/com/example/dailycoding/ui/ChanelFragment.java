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


public class ChanelFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private String[] mDataset={"chanel 1","chanel 2","chanel 3"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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