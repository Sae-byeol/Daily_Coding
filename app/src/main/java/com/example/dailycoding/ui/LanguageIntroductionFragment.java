package com.example.dailycoding.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailycoding.R;
import com.example.dailycoding.util.BaseFragment;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;

public class LanguageIntroductionFragment extends BaseFragment {

    private static final int LOOPS=1000;

    private DiscreteScrollView scrollView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

//    private static final String[] DATA={"변수활용1", "변수활용2", "변수활용3"};
    private static ArrayList<String> list_courseTitle;
    private static ArrayList<Course> list_course;

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
    }

    private void showCourseTitle(){
//        scrollView = getView().findViewById(R.id.DiscreteScrollView_language);
//        scrollView.setAdapter(new CourseSelectAdapter(list_courseTitle));
//        scrollView.setOffscreenItems(3);
//        scrollView.setOverScrollEnabled(false);
//        scrollView.setItemTransformer(new ScaleTransformer.Builder()
//                .setMaxScale(1.05f)
//                .setMinScale(0.8f)
//                .setPivotX(Pivot.X.CENTER) // CENTER is a default one
//                .setPivotY(Pivot.Y.CENTER) // CENTER is a default one
//                .build());
        scrollView = getView().findViewById(R.id.DiscreteScrollView_language);
        CourseSelectAdapter courseSelectAdapter=new CourseSelectAdapter(list_courseTitle, LOOPS);
//        InfiniteScrollAdapter wrapper = InfiniteScrollAdapter.wrap(courseSelectAdapter);
//        scrollView.setAdapter(wrapper);
        scrollView.setAdapter(courseSelectAdapter);
        scrollView.scrollToPosition(LOOPS*3/2);
        scrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMaxScale(1.05f)
                .setMinScale(0.8f)
                .setPivotX(Pivot.X.CENTER) // CENTER is a default one
                .setPivotY(Pivot.Y.CENTER) // CENTER is a default one
                .build());
    }

    private void showCourseList(){
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
    }

    private void initData(){
        list_courseTitle = new ArrayList<>();
        list_course=new ArrayList<>();

        list_courseTitle.add("Python");
        list_courseTitle.add("Java");
        list_courseTitle.add("C++");

        ArrayList tempList=new ArrayList();
        tempList.add("변수활용11");
        tempList.add("변수활용12");
        tempList.add("변수활용13");
        tempList.add("변수활용14");

        list_course.add(new Course(getString(R.string.languageIntro_title1), tempList,false));
        list_course.add(new Course(getString(R.string.languageIntro_title2), tempList,false));
        list_course.add(new Course(getString(R.string.languageIntro_title3), tempList,false));
        list_course.add(new Course(getString(R.string.languageIntro_title4), tempList,false));

    }


}
