package com.example.dailycoding.util;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    public void progressOn() { App.getInstance().progressON(getActivity()); }

    public void progressOff() {
        App.getInstance().progressOFF();
    }
}
