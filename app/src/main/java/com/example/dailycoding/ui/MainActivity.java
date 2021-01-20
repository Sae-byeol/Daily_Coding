package com.example.dailycoding.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dailycoding.R;
import com.example.dailycoding.util.BaseActivity;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    
    private ImageButton btnMenu;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private View header;
    private TextView textView;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setListener();

    }

    private void init() {
        btnMenu = findViewById(R.id.main_imageview_menu);
        drawerLayout = findViewById(R.id.main_drawerlayout);
        navigationView = findViewById(R.id.main_navigation);
        navigationView.setItemIconTintList(null);
        header = LayoutInflater.from(this).inflate(R.layout.navigationview_header, null);
        navigationView.addHeaderView(header);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.main_framelayout, new HomeFragment());
        fragmentTransaction.commit();

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
                        replaceFragment(HomeFragment.newInstance());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.drawer_course:
                        replaceFragment(CourseFragment.newInstance());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.drawer_news:
                        replaceFragment(NewsFragment.newInstance());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.drawer_languages:
                        replaceFragment(LanguageIntroductionFragment.newInstance());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                }
                return false;
            }
        });

    }


}