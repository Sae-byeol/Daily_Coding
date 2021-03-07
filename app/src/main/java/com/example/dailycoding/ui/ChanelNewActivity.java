package com.example.dailycoding.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.dailycoding.R;
import com.example.dailycoding.util.BaseActivity;

public class ChanelNewActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chanel);
        //전환될 액티비티의 텍스트뷰에 데이터 전달하기
        TextView activity_title=findViewById(R.id.news_activity_title);
        TextView activity_content=findViewById(R.id.news_activity_code_it_content);

        Intent intent=getIntent();

        activity_title.setText(intent.getStringExtra("title"));
        activity_content.setText(intent.getStringExtra("content"));

        //뒤로 가기 버튼
        ImageButton closeBtn=findViewById(R.id.news_activity_left);
        closeBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        this.finish();
    }
}
