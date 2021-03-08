package com.example.dailycoding.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

import com.example.dailycoding.R;
import com.example.dailycoding.util.BaseActivity;
import com.example.dailycoding.util.ThemeUtil;

public class SettingActivity extends BaseActivity {

    private Switch modeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        init();
        initListener();
    }

    private void init() {
        modeSwitch = findViewById(R.id.setting_switch_mode);
    }

    private void initListener() {
        modeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    ThemeUtil.applyTheme(ThemeUtil.DARK_MODE);
                else
                    ThemeUtil.applyTheme(ThemeUtil.LIGHT_MODE);
            }
        });
    }
}