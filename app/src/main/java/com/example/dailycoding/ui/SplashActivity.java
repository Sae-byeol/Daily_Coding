package com.example.dailycoding.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dailycoding.R;
import com.example.dailycoding.util.BaseActivity;

import org.w3c.dom.Text;

public class SplashActivity extends BaseActivity {
    private Animation anim;
    private ImageView Image;
    private TextView Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Image = (ImageView)findViewById(R.id.Splash_Image);
        Text = (TextView)findViewById(R.id.Splash_Text);
        anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        });
        Image.startAnimation(anim);
        Text.startAnimation(anim);
    }
}