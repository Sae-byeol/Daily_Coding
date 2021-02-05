package com.example.dailycoding.ui;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.dailycoding.R;

public class OverlapTransformer implements ViewPager2.PageTransformer {

    private Context context;

    private static float MIN_SCALE=0.6f; //배경 뷰 크기
    private static float MAX_SCALE=0.6f; //최상단 뷰 크기
    private static float MIN_FADE=0.6f;

    OverlapTransformer(Context context){
        this.context=context;
    }

    @Override
    public void transformPage(@NonNull View page, float position) {

        ConstraintLayout constraintLayout=page.findViewById(R.id.ConstraintLayout_item_select);
        TextView textView=page.findViewById(R.id.TextView_course_title);
        ImageView imageView=page.findViewById(R.id.ImageView_course_title);

        float pageWidth=page.getWidth();

        if(position<-1){
//            page.setAlpha(MIN_FADE);
            float scaleFactor =
                    (MIN_SCALE + (MAX_SCALE - MIN_SCALE) * (1 - Math.abs(position)));
            page.setAlpha(MIN_FADE);
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
        }
        else if(position==-1f){
            constraintLayout.setBackgroundResource(R.drawable.round_border_light);
            textView.setTextColor(ContextCompat.getColor(context, R.color.black));
            imageView.setImageResource(R.drawable.ic_black);
            page.setAlpha(MIN_FADE);
            float scaleFactor=(MIN_SCALE + (MAX_SCALE - MIN_SCALE) * (1 - Math.abs(position)));
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
        }
        else if(position<0){
            page.setAlpha(1 + position * (1 - MIN_FADE));
            page.setTranslationX(-pageWidth * MAX_SCALE * position * (1f));
            ViewCompat.setTranslationZ(page, position);
            float scaleFactorX=(MIN_SCALE + (MAX_SCALE - MIN_SCALE) * (1 - Math.abs(position)));
            float scaleFactorY=(MIN_SCALE + (MAX_SCALE + 0.3f - MIN_SCALE) * (1 - Math.abs(position)));
            page.setScaleX(scaleFactorX);
            page.setScaleY(scaleFactorY);
        }
        else if(position==0f){
            constraintLayout.setBackgroundResource(R.drawable.round_border_black);
            textView.setTextColor(ContextCompat.getColor(context, R.color.color_primary_light));
            imageView.setImageResource(R.drawable.ic_light);
            page.setAlpha(1f);
            page.setTranslationX(0f);
            ViewCompat.setTranslationZ(page, 0f);
            page.setScaleX(MAX_SCALE);
            page.setScaleY(MAX_SCALE+0.3f);
        }
        else if(position<1){
            page.setAlpha(1 - position * (1 - MIN_FADE));
            page.setTranslationX(-pageWidth * MAX_SCALE * position * (1f));
            float scaleFactorX=(MIN_SCALE + (MAX_SCALE - MIN_SCALE) * (1 - Math.abs(position)));
            float scaleFactorY=(MIN_SCALE + (MAX_SCALE + 0.3f - MIN_SCALE) * (1 - Math.abs(position)));
            ViewCompat.setTranslationZ(page,-position);
            page.setScaleX(scaleFactorX);
            page.setScaleY(scaleFactorY);
        }
        else if(position==1f){
            constraintLayout.setBackgroundResource(R.drawable.round_border_light);
            textView.setTextColor(ContextCompat.getColor(context, R.color.black));
            imageView.setImageResource(R.drawable.ic_black);
            page.setAlpha(1 - position * (1 - MIN_FADE));
            page.setTranslationX(-pageWidth * MAX_SCALE * position * (1f));
            ViewCompat.setTranslationZ(page, -position);
            float scaleFactorX=(MIN_SCALE + (MAX_SCALE - MIN_SCALE) * (1 - Math.abs(position)));
            float scaleFactorY=(MIN_SCALE + (MAX_SCALE + 0.3f - MIN_SCALE) * (1 - Math.abs(position)));
            page.setScaleX(scaleFactorX);
            page.setScaleY(scaleFactorY);
        }
        else{
            page.setAlpha(MIN_FADE);
            float scaleFactor=(MIN_SCALE
                    + (MAX_SCALE - MIN_SCALE) * (1 - Math.abs(position)));
            ViewCompat.setTranslationZ(page, -position);
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
        }
    }
}
