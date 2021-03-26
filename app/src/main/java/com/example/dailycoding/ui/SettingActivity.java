package com.example.dailycoding.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

import com.example.dailycoding.R;
import com.example.dailycoding.util.BaseActivity;
import com.example.dailycoding.util.PreferenceManager;
import com.example.dailycoding.util.ThemeUtil;

public class SettingActivity extends BaseActivity {

    private Switch modeSwitch;
    private Context mContext;

    private ImageButton backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mContext = this;

        init();
        initListener();
    }

    private void init() {

        backbtn = findViewById(R.id.setting_imagebutton_back);

        modeSwitch = findViewById(R.id.setting_switch_mode);

        // 다크모드 적용 여부 sharedPref에서 확인
        Boolean onDark;
        onDark = PreferenceManager.getBoolean(mContext, "appTheme");
        if(onDark.equals(true)) {
            modeSwitch.setChecked(true); //이미 다크모드 체크 했으면 스위치 on
        }
        else {
            modeSwitch.setChecked(false);
        }
    }

    private void initListener() {

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        modeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    PreferenceManager.setBoolean(mContext,"appTheme",true);
                    ThemeUtil.applyTheme(ThemeUtil.DARK_MODE);
                }
                else {
                    PreferenceManager.setBoolean(mContext,"appTheme",false);
                    ThemeUtil.applyTheme(ThemeUtil.LIGHT_MODE);
                }
            }
        });
    }
}