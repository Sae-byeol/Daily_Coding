package com.example.dailycoding.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailycoding.R;

public class NewsFragment extends Fragment {


    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=(View)inflater.inflate(R.layout.fragment_news, container, false);
        //처음 childfragment 지정
        getFragmentManager().beginTransaction().add(R.id.child_fragment, new PromotionFragment()).commit();

        Button subButton1=(Button)rootView.findViewById(R.id.news_promotionButton);
        Button subButton2=(Button)rootView.findViewById(R.id.news_chanelButton);

        //클릭이벤트
        //해당버튼 누르면 child_fragment 부분이 다른 자식프래그먼트로 대체 되도록
        subButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.child_fragment,new PromotionFragment()).commit();

            }
        });
        subButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.child_fragment,new ChanelFragment()).commit();

            }
        });


        return rootView;
    }

}
