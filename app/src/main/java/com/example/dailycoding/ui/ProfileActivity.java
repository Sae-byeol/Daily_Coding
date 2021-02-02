package com.example.dailycoding.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dailycoding.R;
import com.example.dailycoding.util.BaseActivity;

public class ProfileActivity extends BaseActivity {

    private CheckBox checkBox;
    private TextView textView;
    private Button button;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textView = (TextView) findViewById(R.id.profileText3);
        checkBox = (CheckBox)findViewById(R.id.profile_checkBox);
        button = (Button)findViewById(R.id.profile_agree_btn);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    button.setTextColor(getResources().getColor(R.color.black));
                    button.setBackgroundColor(getResources().getColor(R.color.color_primary_light));
                }
                else{
                    button.setTextColor(getResources().getColor(R.color.white));
                    button.setBackgroundColor(getResources().getColor(R.color.black));
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, AgreeCheckActivity.class));
            }
        });
    }
}