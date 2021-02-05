package com.example.dailycoding.ui;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.dailycoding.R;
import com.example.dailycoding.util.BaseFragment;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;

public class CourseFragment extends BaseFragment implements
    DiscreteScrollView.ScrollListener<CourseSelectAdapter.SliderViewHolder>,
    DiscreteScrollView.OnItemChangedListener<CourseSelectAdapter.SliderViewHolder>{

    public final static int PAGES=5;
    public final static int LOOPS=1;
    public final static int FIRST_PAGE=PAGES*LOOPS/2;
    private final static String TAG="CourseFragment";

    private DiscreteScrollView scrollView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ViewPager2 viewPager2;

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

        Log.d(TAG, "onViewCreated");

        initData();
        showCourseTitle();
        showCourseList();
//        scrollEvent();

        Log.d(TAG, "현재 아이템: "+scrollView.getCurrentItem());
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

    private void showCourseTitle(){
        scrollView = getView().findViewById(R.id.DiscreteScrollView_course);
        CourseSelectAdapter courseSelectAdapter=new CourseSelectAdapter(list_courseTitle, LOOPS);
        InfiniteScrollAdapter wrapper = InfiniteScrollAdapter.wrap(courseSelectAdapter);
        scrollView.setAdapter(wrapper);
//        scrollView.setAdapter(courseSelectAdapter);
        scrollView.scrollToPosition(LOOPS*3/2);
        scrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMaxScale(1.0f)
                .setMinScale(3f/4f)
                .setPivotX(Pivot.X.CENTER) // CENTER is a default one
                .setPivotY(Pivot.Y.CENTER) // CENTER is a default one
                .build());
        scrollView.setOffscreenItems(3);

        scrollView.addScrollListener(this);
        scrollView.addOnItemChangedListener(this);
//        scrollView.setOverScrollEnabled(false);

//        viewPager2=getView().findViewById(R.id.ViewPager2_course);
//        viewPager2.setAdapter(new CourseSelectAdapter(list_courseTitle, viewPager2, LOOPS));
//
//        viewPager2.setCurrentItem(FIRST_PAGE);
//        viewPager2.setClipToPadding(false);
//        viewPager2.setClipChildren(false);
//        viewPager2.setOffscreenPageLimit(3);
//        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
//
//        CompositePageTransformer compositePageTransformer=new CompositePageTransformer();
////        compositePageTransformer.addTransformer(new MarginPageTransformer(0));
//        compositePageTransformer.addTransformer((page, position) -> {
//            float r=1-Math.abs(position);
//            page.setScaleY(0.85f+r*0.15f);
//        });
////        compositePageTransformer.addTransformer(new MarginPageTransformer(-150));
//        viewPager2.setPageTransformer(compositePageTransformer);

    }

    private void showCourseList(){
        recyclerView = getView().findViewById(R.id.RecyclerView_course);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new CourseAdapter(list_course, getContext(), 0);
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

        list_course.add(new Course("변수활용1", tempList,false));
        list_course.add(new Course("변수활용2", tempList,false));
        list_course.add(new Course("변수활용3", tempList,false));

    }


    @Override
    public void onScroll(float scrollPosition, int currentPosition, int newPosition, @Nullable CourseSelectAdapter.SliderViewHolder currentHolder, @Nullable CourseSelectAdapter.SliderViewHolder newCurrent) {
//        Log.d(TAG, "scrollPosition"+scrollPosition+", currentPosition: "+currentPosition);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onCurrentItemChanged(@Nullable CourseSelectAdapter.SliderViewHolder viewHolder, int adapterPosition) {
        Log.d(TAG, "adapterPosition: "+adapterPosition);
        TextView textview=viewHolder.itemView.findViewById(R.id.TextView_course_title);
        ConstraintLayout constraintLayout=viewHolder.itemView.findViewById(R.id.ConstraintLayout_item_select);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            textview.setTextColor(getContext().getColor(R.color.color_primary_light));
            constraintLayout.setBackground(getContext().getDrawable(R.drawable.round_border_black));
        }
    }
}
