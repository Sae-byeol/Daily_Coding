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

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }


    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=(View)inflater.inflate(R.layout.fragment_news, container, false);
        recyclerView=(RecyclerView)rootView.findViewById(R.id.recyclerView1);
        LinearLayoutManager horizontalLayoutManager=new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);

        recyclerView.setLayoutManager(horizontalLayoutManager);

        return rootView;

    }

}
