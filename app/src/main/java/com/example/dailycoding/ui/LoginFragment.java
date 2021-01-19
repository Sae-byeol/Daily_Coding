package com.example.dailycoding.ui;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.dailycoding.R;
import com.google.android.material.navigation.NavigationView;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;



import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class LoginFragment extends AppCompatActivity {


    private ImageButton btnMenu;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private View header;
    private TextView textView;
    private Button button;

    private void loginUi() {
        textView = findViewById(R.id.programmers_textview);
        textView = findViewById(R.id.programmers_number);
        textView = findViewById(R.id.course_textview);
        textView = findViewById(R.id.course_number);
        btnMenu = findViewById(R.id.login_kakao);
        btnMenu = findViewById(R.id.login_google);
        button = findViewById(R.id.login_register);

        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                if (user != null){
                    Log.i(TAG, "invoke: id=" + user.getId());
                    Log.i(TAG, "invoke: email=" + user.getKakaoAccount().getEmail());
                    Log.i(TAG, "invoke: nickname=" + user.getKakaoAccount().getProfile().getNickname());
                    Log.i(TAG, "invoke: gender=" + user.getKakaoAccount().getGender());
                    Log.i(TAG, "invoke: age=" + user.getKakaoAccount().getAgeRange());

                    replaceFragment(HomeFragment.newInstance());
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                return null;
            }
        });
    }
}
