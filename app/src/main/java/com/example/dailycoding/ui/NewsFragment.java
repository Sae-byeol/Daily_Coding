package com.example.dailycoding.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailycoding.R;

public class NewsFragment extends Fragment {
    private RecyclerView recyclerView,recyclerView2;
    private RecyclerView.Adapter mAdapter;
    private String[] mDataset={"news 1","news 2","news3"};

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=(View)inflater.inflate(R.layout.fragment_news, container, false);

        recyclerView=(RecyclerView)rootView.findViewById(R.id.recyclerView1);
        LinearLayoutManager LayoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(LayoutManager);
        mAdapter=new NewsAdapter(getActivity(),mDataset);
        recyclerView.setAdapter(mAdapter);

        /*recyclerView2 = (RecyclerView) rootView.findViewById(R.id.recyclerView2);
        LinearLayoutManager horizontalLayoutManager2=new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(horizontalLayoutManager2);
        mAdapter=new NewsAdapter(getActivity(),mDataset);
        recyclerView2.setAdapter(mAdapter);*/

        return rootView;
    }

}
