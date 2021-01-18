package com.example.dailycoding.ui;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class KakaoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        KakaoSdk.init(this, "b8635a4704737dec5e7018a56f0fa289");
    }
}
