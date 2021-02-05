package com.example.dailycoding.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dailycoding.R;
import com.example.dailycoding.util.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class WelcomeFragment extends BaseFragment {

    private ViewPager2 viewPager2;

    private Button startBtn;

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

        viewPager2=view.findViewById(R.id.viewPagerImageSlider);
        List<Slideritem> sliderItems=new ArrayList<>();
        sliderItems.add(new Slideritem(R.drawable.image_welcome03,getString(R.string.welcome05)));
        sliderItems.add(new Slideritem(R.drawable.image_welcome02,getString(R.string.welcome04)));
        sliderItems.add(new Slideritem(R.drawable.image_welcome01,getString(R.string.welcome03)));

        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer=new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        viewPager2.setPageTransformer(compositePageTransformer);
    }

    private void init() {
        startBtn = getView().findViewById(R.id.welcome_button_start);
    }

    private void initListener() {
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });
    }



}