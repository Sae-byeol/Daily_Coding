package com.example.dailycoding.ui;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dailycoding.R;
import com.example.dailycoding.util.BaseFragment;

import java.util.ArrayList;


public class BookFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<NewsData> mDataset=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDataset.add(new NewsData("book 1 title","book 1 content","book 1 review"));
        mDataset.add(new NewsData("book 2 title","book 2 content","book 2 review"));
        mDataset.add(new NewsData("book 3 title","book 3 content","book 3 review"));
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_book, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.book_fragment);
        LinearLayoutManager LayoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(LayoutManager);
        mAdapter=new BookAdapter(getActivity(),mDataset);
        recyclerView.setAdapter(mAdapter);

        return view;
    }
}