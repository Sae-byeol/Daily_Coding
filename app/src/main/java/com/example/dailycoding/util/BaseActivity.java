package com.example.dailycoding.util;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    public void progressOn() {
        App.getInstance().progressON(this);
    }

    public void progressOff() {
        App.getInstance().progressOFF();
    }
}
