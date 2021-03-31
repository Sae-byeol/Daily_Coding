package com.example.dailycoding.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.dailycoding.R;
import com.example.dailycoding.util.BaseActivity;

public class BookNewActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        //전환될 액티비티의 텍스트뷰에 데이터 전달하기
        TextView activity_title=findViewById(R.id.book_activity_title);
        ImageView activity_image=findViewById(R.id.book_activity_image);
        TextView activity_intro=findViewById(R.id.book_activity_intro_content);
        TextView activity_hash=findViewById(R.id.book_activity_code_it_content);
        TextView activity_review=findViewById(R.id.book_introduce_content);
        TextView activity_course=findViewById(R.id.book_course_content);
        Button activity_btn=findViewById(R.id.news_item_button);
        activity_course.setMovementMethod(new ScrollingMovementMethod());

        Intent intent=getIntent();

        activity_title.setText(intent.getStringExtra("title"));
        activity_intro.setText(intent.getStringExtra("content"));
        activity_hash.setText(intent.getStringExtra("hash"));
        activity_review.setText(intent.getStringExtra("review"));
        activity_course.setText(intent.getStringExtra("course"));

        MultiTransformation option3 = new MultiTransformation(new FitCenter(), new RoundedCorners(30));

        Glide.with(this)
                .load(intent.getStringExtra("image"))
                .apply(RequestOptions.bitmapTransform(option3))
                .into(activity_image);

        activity_btn.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent newIntent=new Intent(Intent.ACTION_VIEW, Uri.parse(intent.getStringExtra("link")));
                startActivity(newIntent);
            }
        });

        //뒤로 가기 버튼
        ImageButton closeBtn=findViewById(R.id.book_activity_left);
        closeBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        this.finish();
    }

}
