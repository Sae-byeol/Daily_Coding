package com.example.dailycoding.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.dailycoding.R;
import com.example.dailycoding.api.ServiceProblemApi;
import com.example.dailycoding.model.Course;
import com.example.dailycoding.util.BaseFragment;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;

public class LanguageIntroductionFragment extends BaseFragment {

//    public final static int PAGES=3;
//    public final static int LOOPS=100000;
//    public final static int FIRST_PAGE=PAGES*LOOPS/2;
    private static final String TAG="LanguageIntroductionFragment";

    private String currentLanguage;

    public String getCurrentLanguage() {
        return currentLanguage;
    }

    public void setCurrentLanguage(String currentLanguage) {
        this.currentLanguage = currentLanguage;
    }

    private DiscreteScrollView scrollView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

//    private static final String[] DATA={"변수활용1", "변수활용2", "변수활용3"};
    private static ArrayList<String> list_courseTitle;
    private static ArrayList<Course> list_course;

    private ViewPager2 viewPager2;

//    private Retrofit retrofit;

//    private RetrofitClient retrofitClient;
//    private Retrofit retrofit;
//    private ApiUtils apiUtils;
    private ServiceProblemApi serviceProblemApi;

    public static LanguageIntroductionFragment newInstance() {
        return new LanguageIntroductionFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_language_introduction, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initData();
        showCourseTitle();
        showCourseList();
        loadData(currentLanguage);

//        showCourseList();
//        showCourseTitle();

//        loadData("python");
    }

//    @Override
//    public void onResume() {
//        super.onResume();
////        viewPager2.setCurrentItem(1);
//        /**
//         * set init viewpager2 item
//         * */
//        viewPager2.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                viewPager2.setCurrentItem(1, true);
//            }
//        },10);
//
//    }

    @SuppressLint("LongLogTag")
    private void showCourseTitle(){

        Log.d(TAG, "showCourseTitle 실행 시작");

        viewPager2=getView().findViewById(R.id.ViewPager2_languageSelect);
//        viewPager2.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        viewPager2.setAdapter(new CourseSelectAdapter(list_courseTitle, viewPager2));

//        viewPager2.setCurrentItem(1);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        OverlapTransformer overlapTransformer=new OverlapTransformer(getContext());
        viewPager2.setPageTransformer(overlapTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @SuppressLint("LongLogTag")
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.d(TAG, "현재 선택된 포지션:"+position);
                switch(position){
                    case 0:
                        setCurrentLanguage("python");
                        break;
                    case 1:
                        setCurrentLanguage("java");
                        break;
                    case 2:
                        setCurrentLanguage("c++");
                        break;
                }
                loadData(currentLanguage);
            }

        });

        Log.d(TAG, "showCourseTitle 실행 완료");
    }

    @SuppressLint("LongLogTag")
    private void showCourseList(){

        Log.d(TAG, "showCourseList 실행 시작");

        recyclerView = getView().findViewById(R.id.RecyclerView_language);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new CourseAdapter(list_course, getContext(), 2);
        recyclerView.setAdapter(mAdapter);

        Log.d(TAG, "showCourseList 실행 완료");

    }

    @SuppressLint("LongLogTag")
    private void initData(){
        Log.d(TAG, "initData 실행");

        list_courseTitle = new ArrayList<>();
        list_course=new ArrayList<>();

        list_courseTitle.add("Python");
        list_courseTitle.add("JAVA");
        list_courseTitle.add("C++");

        setCurrentLanguage("python");

        Log.d(TAG, "initData 실행 완료");

//        ArrayList tempList=new ArrayList();
//        tempList.add("변수활용11");
//        tempList.add("변수활용12");
//        tempList.add("변수활용13");
//        tempList.add("변수활용14");

    }

    @SuppressLint("LongLogTag")
    private void loadData(String language){
        list_course.clear();
        Log.d(TAG, "loadData 실행, "+language);
        if(language=="python"){
            list_course.add(new Course(getString(R.string.language_intro_python_def_title), getString(R.string.language_intro_python_def), false));
            list_course.add(new Course(getString(R.string.language_intro_python_prosCons_title), getString(R.string.language_intro_python_prosCons),false));
            list_course.add(new Course(getString(R.string.language_intro_python_feature_title), getString(R.string.language_intro_python_feature),false));
            list_course.add(new Course(getString(R.string.language_intro_python_usage_title), getString(R.string.language_intro_python_usage),false));
        }
        else if(language=="java"){
            list_course.add(new Course(getString(R.string.language_intro_java_def_title), getString(R.string.language_intro_java_def), false));
            list_course.add(new Course(getString(R.string.language_intro_java_prosCons_title), getString(R.string.language_intro_java_prosCons),false));
            list_course.add(new Course(getString(R.string.language_intro_java_feature_title), getString(R.string.language_intro_java_feature),false));
            list_course.add(new Course(getString(R.string.language_intro_java_usage_title), getString(R.string.language_intro_java_usage),false));
        }
        else if(language=="c++"){
            list_course.add(new Course(getString(R.string.language_intro_cpp_def_title), getString(R.string.language_intro_cpp_def), false));
            list_course.add(new Course(getString(R.string.language_intro_cpp_prosCons_title), getString(R.string.language_intro_cpp_propsCons),false));
            list_course.add(new Course(getString(R.string.language_intro_cpp_feature_title), getString(R.string.language_intro_cpp_feature),false));
            list_course.add(new Course(getString(R.string.language_intro_cpp_usage_title), getString(R.string.language_intro_cpp_usage),false));
        }
        mAdapter = new CourseAdapter(list_course, getContext(), 2);
        recyclerView.setAdapter(mAdapter);

    }

}
