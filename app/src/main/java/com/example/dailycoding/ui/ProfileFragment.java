package com.example.dailycoding.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.example.dailycoding.R;
import com.example.dailycoding.util.BaseActivity;
import com.example.dailycoding.util.BaseFragment;

public class ProfileFragment extends BaseFragment {

    private CheckBox checkBox;
    private TextView textView;
    private Button button;
    private ImageView imageView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        textView = (TextView) view.findViewById((R.id.profileText3));
        checkBox = (CheckBox) view.findViewById((R.id.profile_checkBox));
        button = (Button)view.findViewById((R.id.profile_agree_btn));

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    button.setTextColor(getResources().getColor(R.color.black));
                    button.setBackgroundColor(getResources().getColor(R.color.color_primary_light));
                }
                else{
                    button.setTextColor(getResources().getColor(R.color.white));
                    button.setBackgroundColor(getResources().getColor(R.color.black));
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_agreeCheckFragment);
            }
        });

        return view;
    }

}