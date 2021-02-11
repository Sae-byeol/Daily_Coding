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
import com.example.dailycoding.api.ApiUtils;
import com.example.dailycoding.api.ServiceProblemApi;
import com.example.dailycoding.model.CategoryResponse;
import com.example.dailycoding.util.BaseFragment;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LanguageIntroductionFragment extends BaseFragment {

//    public final static int PAGES=3;
//    public final static int LOOPS=100000;
//    public final static int FIRST_PAGE=PAGES*LOOPS/2;
    private static final String TAG="LanguageIntroductionFragment";

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

        loadData("python");
    }

    private void showCourseTitle(){
        viewPager2=getView().findViewById(R.id.ViewPager2_languageSelect);
//        viewPager2.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        viewPager2.setAdapter(new CourseSelectAdapter(list_courseTitle, viewPager2));

        viewPager2.setCurrentItem(1);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        OverlapTransformer overlapTransformer=new OverlapTransformer(getContext());
        viewPager2.setPageTransformer(overlapTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
//                Log.d(TAG, "현재 선택된 포지션:"+position);
            }

        });

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


//        scrollView = getView().findViewById(R.id.DiscreteScrollView_language);
//        CourseSelectAdapter courseSelectAdapter=new CourseSelectAdapter(list_courseTitle, LOOPS);
////        InfiniteScrollAdapter wrapper = InfiniteScrollAdapter.wrap(courseSelectAdapter);
////        scrollView.setAdapter(wrapper);
//        scrollView.setAdapter(courseSelectAdapter);
//        scrollView.scrollToPosition(LOOPS*3/2);
//        scrollView.setItemTransformer(new ScaleTransformer.Builder()
//                .setMaxScale(1.05f)
//                .setMinScale(0.8f)
//                .setPivotX(Pivot.X.CENTER) // CENTER is a default one
//                .setPivotY(Pivot.Y.CENTER) // CENTER is a default one
//                .build());
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
        list_courseTitle.add("JAVA");
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

    private void loadData(String language){

        serviceProblemApi =ApiUtils.getServiceProblemApi();
        //        로딩
        //progressOn();
        serviceProblemApi.getData("python").enqueue(new Callback<ArrayList<CategoryResponse>>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<ArrayList<CategoryResponse>> call, Response<ArrayList<CategoryResponse>> response) {
                //progressOff();
                ArrayList<CategoryResponse> result=response.body();

                Log.d(TAG, result.size()+"");
                for(int i=0;i<result.size();i++){
                    Log.d(TAG, result.get(i).getCategory()+"");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CategoryResponse>> call, Throwable t) {

            }
        });
    }

}
