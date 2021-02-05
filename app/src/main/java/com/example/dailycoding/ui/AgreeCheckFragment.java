package com.example.dailycoding.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailycoding.R;
import com.example.dailycoding.util.BaseFragment;

import java.util.ArrayList;

public class AgreeCheckFragment extends BaseFragment {
    private ArrayList<CheckModel> item_list=new ArrayList<CheckModel>();
    private CheckBox check_all;
    private AgreeCheckAdapter mainAdapter;
    private RecyclerView recyclerView;
    private Button btn;
    private TextView textView_expanded;
    private LinearLayoutManager linearLayoutManager;


    public static AgreeCheckFragment newInstance() {
        return new AgreeCheckFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_agree_check, container, false);

        recyclerView=(RecyclerView)view.findViewById((R.id.agree_recyclerView));
        check_all=(CheckBox)view.findViewById(R.id.profile_checkBox);
        btn=(Button)view.findViewById(R.id.agree_btn);

        initControls();
        return view;
    }

    private void initControls(){


        item_list.add(new CheckModel(R.string.agree1, false,R.string.agree_check_cont1));
        item_list.add(new CheckModel(R.string.agree2, false,R.string.agree_check_cont2));
        //일단 cont1 넣어둠
        item_list.add(new CheckModel(R.string.agree3, false,R.string.agree_check_cont3));


        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);
        mainAdapter=new AgreeCheckAdapter(item_list,getContext());
        recyclerView.setAdapter(mainAdapter);

        //모두 동의 버튼의 클릭 이벤트
        check_all.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (check_all.isChecked()){
                    for (CheckModel model:item_list){
                        model.setSelected(true);
                        //'다음'버튼을 '시작하기'버튼으로 변경
                        btn.setText("시작하기");
                        btn.setTextColor(getResources().getColor(R.color.black));
                        btn.setBackgroundColor(getResources().getColor(R.color.color_primary_light));
                    }
                }
                else{
                    for (CheckModel model: item_list){
                        model.setSelected(false);
                    }
                }
                mainAdapter.notifyDataSetChanged();
            }
        });
    }
}
