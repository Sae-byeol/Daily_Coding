package com.example.dailycoding.util;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class BaseActivity extends AppCompatActivity {

    public void progressOn() {
        App.getInstance().progressON(this);
    }

    public void progressOff() {
        App.getInstance().progressOFF();
    }


}
