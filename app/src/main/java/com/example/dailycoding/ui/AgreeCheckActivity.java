package com.example.dailycoding.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailycoding.R;
import com.example.dailycoding.util.BaseActivity;

import java.util.ArrayList;

public class AgreeCheckActivity extends BaseActivity {
    private ArrayList<CheckModel> item_list=new ArrayList<CheckModel>();
    private CheckBox check_all;
    private AgreeCheckAdapter mainAdapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agree_check);

        initControls();
    }
    private void initControls(){
        recyclerView=(RecyclerView)findViewById((R.id.agree_recyclerView));
        check_all=(CheckBox)findViewById(R.id.agree_checkBox);

        item_list.add(new CheckModel("서비스 이용약관 (필수)", false));
        item_list.add(new CheckModel("개인정보 처리방침 (필수)", false));
        item_list.add(new CheckModel("이벤트 마케팅 수신동의 (선택)", false));

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainAdapter=new AgreeCheckAdapter(item_list);
        recyclerView.setAdapter(mainAdapter);

        //모두 동의 버튼의 클릭 이벤트
        check_all.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (check_all.isChecked()){
                    for (CheckModel model:item_list){
                        model.setSelected(true);
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
