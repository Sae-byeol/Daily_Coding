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

    @Override
    public void onBackPressed() {

        FragmentManager fm = getSupportFragmentManager();
        for (Fragment frag : fm.getFragments()) {
            if (frag == null) {
                super.onBackPressed();
                return;
            }
            if (frag.isVisible()) {
                FragmentManager childFm = frag.getChildFragmentManager();
                if (childFm.getFragments() == null) {
                    super.onBackPressed();
                    finish();
                    return;
                }
                if (childFm.getBackStackEntryCount() > 0) {
                    childFm.popBackStack();
                    return;
                }
                else {

                    fm.popBackStack();
                    if (fm.getFragments().size() <= 1) {
                        finish();
                    }
                    return;
                }

            }
        }
    }
}
