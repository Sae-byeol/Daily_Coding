package com.example.dailycoding.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.dailycoding.R;
import com.example.dailycoding.api.ApiUtils;
import com.example.dailycoding.api.ServiceProblemApi;
import com.example.dailycoding.api.ServiceUserApi;
import com.example.dailycoding.model.CategoryResponse;
import com.example.dailycoding.model.Course;
import com.example.dailycoding.model.LastProblemData;
import com.example.dailycoding.model.LastProblemResponse;
import com.example.dailycoding.model.RecentProblem;
import com.example.dailycoding.util.BaseFragment;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseFragment extends BaseFragment
//        implements
//    DiscreteScrollView.ScrollListener<CourseSelectAdapter.SliderViewHolder>,
//    DiscreteScrollView.OnItemChangedListener<CourseSelectAdapter.SliderViewHolder>
{

    public final static int PAGES=3;
//    public final static int LOOPS=100000;
//    public final static int FIRST_PAGE=PAGES*LOOPS/2;
    private final static String TAG="CourseFragment";

    public String getCurrentLanguage() {
        return currentLanguage;
    }

    public void setCurrentLanguage(String currentLanguage) {
        this.currentLanguage = currentLanguage;
    }

    private String currentLanguage="java";

    private DiscreteScrollView scrollView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ViewPager2 viewPager2;
    private ServiceProblemApi serviceProblemApi;
    private ServiceUserApi serviceUserApi;

    private RecentProblem recentProblems;

    private TextView TextView_noProblems;

//    private static final String[] DATA={"변수활용1", "변수활용2", "변수활용3"};
    private static ArrayList<String> list_courseTitle;
    private static ArrayList<Course> list_course;

    public static CourseFragment newInstance() {
        return new CourseFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
        initData();

        showCourseTitle();
//        showCourseList();
//        scrollEvent();

//        loadData();

//        Log.d(TAG, "현재 아이템: "+scrollView.getCurrentItem());
    }

    @Override
    public void onResume() {
        super.onResume();
//        viewPager2.setCurrentItem(1);
        /**
         * set init viewpager2 item
         * */
//        viewPager2.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                viewPager2.setCurrentItem(1, true);
//            }
//        },10);

//        new Handler().postDelayed(new Runnable(){
//            @Override
//            public void run() {
//                viewPager2.setCurrentItem(1, false);
//            }
//        }, 100);

    }

    //    private void scrollEvent(){
//        scrollView.addScrollListener(listener);
//        scrollView.removeScrollListener(listener);
//
//        public interface ScrollListener<T extends ViewHolder> {
//            //The same as ScrollStateChangeListener, but for the cases when you are interested only in onScroll()
//            void onScroll(float scrollPosition, int currentIndex, int newIndex, @Nullable T currentHolder, @Nullable T newCurrentHolder);
//        }
//    }

    private void init(){
        viewPager2=getView().findViewById(R.id.ViewPager2_course);
        recyclerView = getView().findViewById(R.id.RecyclerView_course);
        TextView_noProblems=getView().findViewById(R.id.TextView_course_noProblems);

        list_courseTitle = new ArrayList<>();
        list_course=new ArrayList<>();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        recentProblems=new RecentProblem();
    }

    private void loadData(){
        Log.d(TAG, "loadData 호출");
        serviceProblemApi=ApiUtils.getServiceProblemApi();
        serviceUserApi= ApiUtils.getServiceUserApi();

        progressOn();
//        list_course=new ArrayList<>();
        list_course.clear();

        serviceProblemApi.getData(getCurrentLanguage()).enqueue(new Callback<ArrayList<CategoryResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<CategoryResponse>> call, Response<ArrayList<CategoryResponse>> response) {
                if(response.isSuccessful()){
                    if(response.body().size()>0){
                        for(int i=0;i<response.body().size();i++){
                            Log.d(TAG, "response successful:"+response.body().get(i).getCategory());
                            list_course.add(new Course(response.body().get(i).getCategory(), false));
//                            list_course.add();

                        }
                        showCourseList();
                        recyclerView.setVisibility(View.VISIBLE);
                        TextView_noProblems.setVisibility(View.INVISIBLE);
                    }
                    else{
                        Log.d(TAG, currentLanguage+" 언어 하위에 저장된 카테고리 정보가 없습니다.");
                        recyclerView.setVisibility(View.INVISIBLE);
                        TextView_noProblems.setVisibility(View.VISIBLE);
                    }
                    progressOff();

                    showCourseList();
                }
                else{
                    Log.d(TAG, "카테고리 데이터 로드에 실패하였습니다.");
                    progressOff();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CategoryResponse>> call, Throwable t) {
                Log.d(TAG, "카테고리 데이터 로드에 실패하였습니다.\n");
                progressOff();
            }
        });

        serviceUserApi.getLastProblem().enqueue(new Callback<LastProblemResponse>() {
            @Override
            public void onResponse(Call<LastProblemResponse> call, Response<LastProblemResponse> response) {
                Log.d(TAG, "getLastProblem response: "+response.body());
                if(response.code()==200){
                    for(LastProblemData data : response.body().getList()){
                        Log.d(TAG, "최근 푼 문제: "+data.getCategory()+" / "+data.getQuest()+" / "+data.getCreatedate()+" / "+data.getQuestIdx());
                        Log.d(TAG, "최근 문제 카테고리: "+data.getCategory()+"/ 타입: "+data.getCategory().getClass().getName());
                        String currentCategory=data.getCategory();
                        switch(currentCategory){
                            case "python":
                                recentProblems.setRecentPython(data.getQuest());
                                recentProblems.setRecentPythonIdx(data.getQuestIdx());
                                Log.d(TAG, "최근 인덱스 Python:"+recentProblems.getRecentJavaIdx());
                                break;
                            case "java":
                                recentProblems.setRecentJava(data.getQuest());
                                recentProblems.setRecentJavaIdx(data.getQuestIdx());
                                Log.d(TAG, "최근 인덱스 Java:"+recentProblems.getRecentJavaIdx());
                                break;
                            case "cplus":
                                recentProblems.setRecentCpp(data.getQuest());
                                recentProblems.setRecentCppIdx(data.getQuestIdx());
                                Log.d(TAG, "최근 인덱스 C++:"+recentProblems.getRecentCppIdx());
                                break;
                            default:
                                Log.d(TAG, "최근 인덱스 x");
                                break;
                        }
                    }

//                    Log.d(TAG, "최근 파이썬 index, before showCourseList2: "+recentProblems.getRecentPythonIdx());

                    showCourseList();

                }
                else{
                    Toast.makeText(getContext(), "최근에 푼 문제 데이터 로드에 실패하였습니다.\n에러코드: "+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LastProblemResponse> call, Throwable t) {
                Log.d(TAG, "getLastProblem failed!\nError:"+t.getMessage());
                Toast.makeText(getContext(), "최근 푼 문제 조회에 실패하였습니다.", Toast.LENGTH_SHORT);
            }
        });
    }

    private void showCourseTitle(){
//        scrollView = getView().findViewById(R.id.DiscreteScrollView_course);
//        CourseSelectAdapter courseSelectAdapter=new CourseSelectAdapter(list_courseTitle, LOOPS);
//        InfiniteScrollAdapter wrapper = InfiniteScrollAdapter.wrap(courseSelectAdapter);
//        scrollView.setAdapter(wrapper);
////        scrollView.setAdapter(courseSelectAdapter);
//        scrollView.scrollToPosition(LOOPS*3/2);
//        scrollView.setItemTransformer(new ScaleTransformer.Builder()
//                .setMaxScale(1.0f)
//                .setMinScale(3f/4f)
//                .setPivotX(Pivot.X.CENTER) // CENTER is a default one
//                .setPivotY(Pivot.Y.CENTER) // CENTER is a default one
//                .build());
//        scrollView.setOffscreenItems(3);
//
//        scrollView.addScrollListener(this);
//        scrollView.addOnItemChangedListener(this);
//        scrollView.setOverScrollEnabled(false);

//        viewPager2.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        viewPager2.setCurrentItem(1, true);

        viewPager2.setAdapter(new CourseSelectAdapter(list_courseTitle, viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);

//        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

//        CompositePageTransformer compositePageTransformer=new CompositePageTransformer();
////        compositePageTransformer.addTransformer(new MarginPageTransformer(0));
//        compositePageTransformer.addTransformer((page, position) -> {
//            float r=1-Math.abs(position);
//            page.setScaleY(0.85f+r*0.15f);
//        });
//        compositePageTransformer.addTransformer(new MarginPageTransformer(150));
//        viewPager2.setPageTransformer(compositePageTransformer);
//        DepthPageTransformer depthPageTransformer=new DepthPageTransformer();
//        viewPager2.setPageTransformer(depthPageTransformer);
        OverlapTransformer overlapTransformer=new OverlapTransformer(getContext());
        viewPager2.setPageTransformer(overlapTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.d(TAG, "현재 선택된 포지션:"+position);
                switch (position){
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
                loadData();
//                viewPager2.setCurrentItem(1, false);
            }

        });



    }

    private void showCourseList(){

        // specify an adapter (see also next example)

        Log.d(TAG, "최근 파이썬 문제 인덱스:"+recentProblems.getRecentPythonIdx());

        mAdapter = new CourseAdapter(list_course, getContext(), 0, currentLanguage, recentProblems);
        recyclerView.setAdapter(mAdapter);
    }

    private void initData(){



        list_courseTitle.add("Python");
        list_courseTitle.add("JAVA");
        list_courseTitle.add("C++");

//        ArrayList tempList=new ArrayList();
//        tempList.add("변수활용11");
//        tempList.add("변수활용12");
//        tempList.add("변수활용13");
//        tempList.add("변수활용14");

//        list_course.add(new Course("변수활용1", false));
//        list_course.add(new Course("변수활용2", false));
//        list_course.add(new Course("변수활용3", false));

    }


//    @Override
//    public void onScroll(float scrollPosition, int currentPosition, int newPosition, @Nullable CourseSelectAdapter.SliderViewHolder currentHolder, @Nullable CourseSelectAdapter.SliderViewHolder newCurrent) {
////        Log.d(TAG, "scrollPosition"+scrollPosition+", currentPosition: "+currentPosition);
//    }
//
//    @SuppressLint("ResourceAsColor")
//    @Override
//    public void onCurrentItemChanged(@Nullable CourseSelectAdapter.SliderViewHolder viewHolder, int adapterPosition) {
//        Log.d(TAG, "adapterPosition: "+adapterPosition);
//        TextView textview=viewHolder.itemView.findViewById(R.id.TextView_course_title);
//        ConstraintLayout constraintLayout=viewHolder.itemView.findViewById(R.id.ConstraintLayout_item_select);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            textview.setTextColor(getContext().getColor(R.color.color_primary_light));
//            constraintLayout.setBackground(getContext().getDrawable(R.drawable.round_border_black));
//        }
//    }
}
