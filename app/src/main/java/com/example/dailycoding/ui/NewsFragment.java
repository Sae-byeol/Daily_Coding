package com.example.dailycoding.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dailycoding.R;
import com.example.dailycoding.api.ApiUtils;
import com.example.dailycoding.api.ServiceProblemApi;
import com.example.dailycoding.util.BaseFragment;

public class NewsFragment extends BaseFragment {



    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView=(View)inflater.inflate(R.layout.fragment_news, container, false);
        //처음 childfragment 지정
        getFragmentManager().beginTransaction().add(R.id.child_fragment, new ChanelFragment()).commit();

        Button subButton1=(Button)rootView.findViewById(R.id.news_chanelButton);
        Button subButton2=(Button)rootView.findViewById(R.id.news_promotionButton);

        //클릭이벤트
        //해당버튼 누르면 child_fragment 부분이 다른 자식프래그먼트로 대체 되도록
        //누른 버튼은 디자인 변경
        subButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.child_fragment,new ChanelFragment()).commit();
                //버튼1 검정색 , 버튼2 하얀색으로
                subButton1.setBackground(getResources().getDrawable(R.drawable.round_border_news02));
                subButton2.setBackground(getResources().getDrawable(R.drawable.round_border_news));
            }
        });
        subButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.child_fragment,new BookFragment()).commit();
                //디자인 변경
                subButton2.setBackground(getResources().getDrawable(R.drawable.round_border_news02));
                subButton1.setBackground(getResources().getDrawable(R.drawable.round_border_news));
            }
        });


        return rootView;
    }

}
