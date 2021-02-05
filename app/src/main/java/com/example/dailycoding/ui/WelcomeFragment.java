package com.example.dailycoding.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dailycoding.R;
import com.example.dailycoding.util.BaseFragment;

public class WelcomeFragment extends BaseFragment {


    private ViewPager2 myViewPager;
    private LinearLayout layoutIndicator;

    private int[] images = new int[] {
            R.drawable.image_welcome01,
            R.drawable.image_welcome02,
            R.drawable.image_welcome03
    };

    private String[] texts = new String[] {
            "다양한 프로그래밍 언어의 소개를 읽어보고\n나에게 맞는 언어를 찾아보세요.",
            "다양한 프로그래밍 예제들을 풀어보며\n코딩에 익숙해져 보세요.",
            "개발자들이 직접 경험해보고\n검증한 강의들을 추천 받을 수 있습니다."
    };
    private TextView tv;
    private Button startBtn;
    private boolean btn_active = false;

    public static WelcomeFragment newInstance() {
        return new WelcomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
        initListener();
        setText();

    }

    private void init() {
        startBtn = getView().findViewById(R.id.welcome_button_start);
        tv = getView().findViewById(R.id.welcome_textview_name);
        layoutIndicator = getView().findViewById(R.id.layoutIndicators);
        myViewPager = getView().findViewById(R.id.viewPagerImageSlider);
        myViewPager.setOffscreenPageLimit(1);
        myViewPager.setAdapter(new WelcomeSliderAdapter(getActivity(),images,texts));

        myViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
                activeBtn(position);
            }
        });

        setupIndicators(images.length);
    }

    private void activeBtn(int pos) {
        if(pos == 2) {
            startBtn.setBackground(ContextCompat.getDrawable(getContext(),R.color.color_primary_light));
            startBtn.setTextColor(ContextCompat.getColor(getContext(),R.color.black));
            btn_active = true;
        } else {
            startBtn.setBackground(ContextCompat.getDrawable(getContext(),R.color.black));
            startBtn.setTextColor(ContextCompat.getColor(getContext(),R.color.white));
            btn_active = false;
        }
    }

    private void setupIndicators(int count) {
        ImageView[] indicators = new ImageView[count];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.setMargins(16, 8, 16, 8);

        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(getContext(),
                    R.drawable.bg_indicator_inactive));
            indicators[i].setLayoutParams(params);
            layoutIndicator.addView(indicators[i]);
        }
        setCurrentIndicator(0);
    }

    private void setCurrentIndicator(int position) {
        int childCount = layoutIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutIndicator.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        getContext(),
                        R.drawable.bg_indicator_active
                ));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        getContext(),
                        R.drawable.bg_indicator_inactive
                ));
            }
        }
    }

    private void initListener() {
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_active){
                    Intent intent = new Intent(getActivity(),MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void setText() {

        /*
        * 실제로는 user의 이름의 length를 구해서 start,end 지정해야함
        * */
        String text = String.format(getResources().getString(R.string.welcome02),"어스");
        int len = text.length();
        SpannableStringBuilder ssb = new SpannableStringBuilder(text);
        ssb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(),R.color.color_primary_light)),6,10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new StyleSpan(Typeface.BOLD),6,10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(ssb);
    }
    



}