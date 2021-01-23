package com.example.dailycoding.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.dailycoding.R;
import com.example.dailycoding.util.BaseActivity;
import com.kakao.sdk.auth.LoginClient;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;



import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginFragment";

    private ImageButton kakaoLogin, googleLogin;
    private DrawerLayout drawerLayout;
    private TextView textView;
    private Button register;

    Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>(){
        @Override
        public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
            if (oAuthToken != null){

            }
            else if(throwable != null){

            }
            kakaoLoginUi();
            return null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void loginUi() {

        textView = findViewById(R.id.programmers_textview);
        textView = findViewById(R.id.programmers_number);
        textView = findViewById(R.id.course_textview);
        textView = findViewById(R.id.course_number);
        kakaoLogin = findViewById(R.id.login_kakao);
        googleLogin = findViewById(R.id.login_google);
        register = findViewById(R.id.login_register);

        kakaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)){
                    LoginClient.getInstance().loginWithKakaoAccount(LoginActivity.this, callback);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
                else {
                    LoginClient.getInstance().loginWithKakaoAccount(LoginActivity.this, callback);
                }
            }
        });
    }

    private void kakaoLoginUi() {
        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                if (user != null){
                    Log.i(TAG, "invoke: id=" + user.getId());
                    Log.i(TAG, "invoke: email=" + user.getKakaoAccount().getEmail());
                    Log.i(TAG, "invoke: nickname=" + user.getKakaoAccount().getProfile().getNickname());
                    Log.i(TAG, "invoke: gender=" + user.getKakaoAccount().getGender());
                    Log.i(TAG, "invoke: age=" + user.getKakaoAccount().getAgeRange());

                    //가져온 정보를 화면에 표시

                    /*profile image loading with glide
                    Glide.with(profileImage).load(user.getKakaoAccount().getProfile().getThumbnailImageUrl()).circleCrop().into(profileImage);*/
                }
                else{
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                    //replaceFragment(HomeFragment.newInstance());
                    drawerLayout.closeDrawer(GravityCompat.START);

                }
                return null;
            }
        });
    }
}
