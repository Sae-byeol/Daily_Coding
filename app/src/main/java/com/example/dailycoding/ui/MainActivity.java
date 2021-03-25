package com.example.dailycoding.ui;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.dailycoding.R;
import com.example.dailycoding.util.BaseActivity;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    
    private ImageButton btnMenu;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private View mainView;
    private View header;

    private Context context;

    //잠시 추가
    private ImageButton btn;

    private int selectedDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        init();
        initNavigation();
        setListener();

        //잠시 추가
        btn=findViewById(R.id.main_imageview_account);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, WrongAnswerActivity.class);
                startActivity(intent);
            }
        });

    }


    private void init() {
        btnMenu = findViewById(R.id.main_imageview_menu);
        context = this;

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.main_framelayout, new HomeFragment());
        fragmentTransaction.commit();

    }

    private void initNavigation() {

        drawerLayout = findViewById(R.id.main_drawerlayout);
        navigationView = findViewById(R.id.main_navigation);
        navigationView.setItemIconTintList(null);
        header = LayoutInflater.from(this).inflate(R.layout.navigationview_header, null);
        navigationView.addHeaderView(header);
        navigationView.setBackground(ContextCompat.getDrawable(this,R.drawable.round_border_black));
        navigationView.setItemTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,R.color.color_primary_light)));

    }

    public void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_framelayout, fragment).commit();      // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
    }


    private void setListener(){

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.drawer_home:
                        navigationView.setCheckedItem(R.id.drawer_home);
                        selectedDrawer = R.id.drawer_home;
                        replaceFragment(HomeFragment.newInstance());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.drawer_course:
                        navigationView.setCheckedItem(R.id.drawer_course);
                        selectedDrawer = R.id.drawer_course;
                        replaceFragment(CourseFragment.newInstance());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.drawer_news:
                        navigationView.setCheckedItem(R.id.drawer_news);
                        selectedDrawer = R.id.drawer_news;
                        replaceFragment(NewsFragment.newInstance());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.drawer_languages:
                        navigationView.setCheckedItem(R.id.drawer_languages);
                        selectedDrawer = R.id.drawer_languages;
                        replaceFragment(LanguageIntroductionFragment.newInstance());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.drawer_setting:
                        navigationView.setCheckedItem(selectedDrawer);
//                        replaceFragment(WelcomeFragment.newInstance());
                        drawerLayout.closeDrawer(GravityCompat.START);

                        Intent tempintent = new Intent(getApplicationContext(), SettingActivity.class);
                        startActivity(tempintent);
                        break;

                }
                return false;
            }
        });

    }


}