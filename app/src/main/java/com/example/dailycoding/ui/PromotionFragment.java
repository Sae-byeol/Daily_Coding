package com.example.dailycoding.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dailycoding.R;


public class PromotionFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private String[] mDataset={"promotion 1","promotion 2","promotion 3"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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