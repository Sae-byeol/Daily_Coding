package com.example.dailycoding.ui;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dailycoding.R;
import com.example.dailycoding.api.ApiUtils;
import com.example.dailycoding.api.ServiceUserApi;
import com.example.dailycoding.model.UserRank;
import com.example.dailycoding.model.UserRankResponse;
import com.example.dailycoding.util.BaseFragment;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankFragment extends BaseFragment {

    private Spinner mSpinner;
    private SpinnerAdapter spinnerAdapter;
    private LineChart lineChart;
    private LineChart _lineChart;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<String> data;

    private TextView tv_toplate;
    private TextView tv_temp;

    private int selected;
    private ImageButton btnJava;
    private ImageButton btnC;
    private ImageButton btnPython;

    //Rank 1~3
    private ImageView ivRank1;
    private TextView tvNameRank1;
    private TextView tvStarRank1;

    private ImageView ivRank2;
    private TextView tvNameRank2;
    private TextView tvStarRank2;

    private ImageView ivRank3;
    private TextView tvNameRank3;
    private TextView tvStarRank3;

    // retrofit2
    private ServiceUserApi userService;
    private ArrayList<UserRank> rankData = new ArrayList<>();

    public static RankFragment newInstance() {
        return new RankFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //retrofit2 객체 할당
        userService = ApiUtils.getServiceUserApi();

//        init();
        //initSpinner();
        init();
        initListener();
        initChart();
        initMultiline();

        loadData();

//        progressOff();
//
//        // Retrofit2 Test
//        loadData("python");
    }

    private void init() {
        selected = 0;

        btnJava = getView().findViewById(R.id.rank_btn_java);
        btnC = getView().findViewById(R.id.rank_btn_c);
        btnPython = getView().findViewById(R.id.rank_btn_python);

        btnJava.setBackgroundResource(R.drawable.bg_black_java);

        ivRank1 = getView().findViewById(R.id.rank_imageview_profile);
        ivRank2 = getView().findViewById(R.id.rank_imageview_profile2);
        ivRank3 = getView().findViewById(R.id.rank_imageview_profile3);

        tvNameRank1 = getView().findViewById(R.id.rank_textview_name);
        tvNameRank2 = getView().findViewById(R.id.rank_textview_name2);
        tvNameRank3 = getView().findViewById(R.id.rank_textview_name3);

        tvStarRank1 = getView().findViewById(R.id.rank_textview_star);
        tvStarRank2 = getView().findViewById(R.id.rank_textview_star2);
        tvStarRank3 = getView().findViewById(R.id.rank_textview_star3);
    }

    private void initListener() {

        btnJava.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                selected = 0;
                btnJava.setBackgroundResource(R.drawable.bg_black_java);
                btnC.setBackgroundResource(R.drawable.bg_green_c__);
                btnPython.setBackgroundResource(R.drawable.bg_green_python);
            }
        });

        btnC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                selected = 1;
                btnJava.setBackgroundResource(R.drawable.bg_green_java);
                btnC.setBackgroundResource(R.drawable.bg_black_c__);
                btnPython.setBackgroundResource(R.drawable.bg_green_python);
            }
        });

        btnPython.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                selected = 2;
                btnJava.setBackgroundResource(R.drawable.bg_green_java);
                btnC.setBackgroundResource(R.drawable.bg_green_c__);
                btnPython.setBackgroundResource(R.drawable.bg_black_python);
            }
        });
    }

    private void initAdapter() {

        recyclerView = (RecyclerView) getView().findViewById(R.id.rank_recyclerview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new RankAdapter(rankData);
        recyclerView.setAdapter(mAdapter);

    }
//
//    private void initSpinner() {
//        // spinner init
//        mSpinner = getView().findViewById(R.id.rank_spinner);
//
//        List<String> data = new ArrayList<>();
//        data.add("JAVA");
//        data.add("C++");
//        data.add("Python");
//
//        spinnerAdapter = new SpinnerAdapter(requireContext(), data);
//        mSpinner.setAdapter(spinnerAdapter);
//        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getContext(), data.get(position), Toast.LENGTH_SHORT).show();
//
////                switch (position) {
////                    case 0:
////                        Toast.makeText(getContext(), "JAVA", Toast.LENGTH_SHORT).show();
////                    case 1:
////                        Toast.makeText(getContext(), "C++", Toast.LENGTH_SHORT).show();
////                    case 2:
////                        Toast.makeText(getContext(), "Python", Toast.LENGTH_SHORT).show();
////                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//    }

    private void initChart() {
        lineChart = (LineChart) getView().findViewById(R.id.rank_chart);

        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0f, 27f));
        entries.add(new Entry(1f, 19f));
        entries.add(new Entry(2f, 22f));
        entries.add(new Entry(3f, 24f));
        entries.add(new Entry(4f, 20f));
        entries.add(new Entry(5f, 18f));
        entries.add(new Entry(6f, 13f));

        LineDataSet lineDataSet = new LineDataSet(entries, null);
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(6);

        int circleColor = ContextCompat.getColor(getContext(),R.color.primary_chart);
        int firstLineColor = ContextCompat.getColor(getContext(),R.color.secondary_font);

        lineDataSet.setColor(firstLineColor);

        int[] colors = {circleColor,circleColor,circleColor,circleColor,circleColor,circleColor,ContextCompat.getColor(getContext(),R.color.color_primary_light)};
        lineDataSet.setCircleColors(colors);
        lineDataSet.setCircleHoleRadius(100);

        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawValues(true);
        lineDataSet.setDrawValues(false); // 값 숨기기

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.getLegend().setEnabled(false); // x label 제거
        lineChart.getDescription().setEnabled(false); // description label 제거
        lineChart.getAxisLeft().setInverted(true); // y축 reverse
        lineChart.setScaleEnabled(false); // zoom disable
        //lineChart.setXAxisRenderer(new CustomXAxisRenderer(lineChart.getViewPortHandler(), lineChart.getXAxis(), lineChart.getTransformer(YAxis.AxisDependency.LEFT) ));

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.GRAY);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        //xAxis.setAxisMaximum(100+0.1f);

        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);
        yLAxis.setDrawLabels(false);
        yLAxis.setDrawAxisLine(false);
        yLAxis.setDrawGridLines(false);

        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        final String[] date = {"01.01", "01.02", "01.03", "01.04", "01.05", "01.06", "01.07"};
        xAxis.setValueFormatter(new IndexAxisValueFormatter(date));

    }

    private void initMultiline() {
        lineChart = (LineChart) getView().findViewById(R.id.rank_chart_multiline);

        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0f, 0));
        entries.add(new Entry(1f, 0));
        entries.add(new Entry(2f, 0));
        entries.add(new Entry(3f, 0));
        entries.add(new Entry(4f, 0));
        entries.add(new Entry(5f, 0));
        entries.add(new Entry(6f, 0));

        LineDataSet lineDataSet = new LineDataSet(entries, null);
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setColor(Color.TRANSPARENT);
        lineDataSet.setCircleColor(Color.GRAY);
        lineDataSet.setCircleHoleColor(Color.GRAY);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawValues(false); // 값 숨기기

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.getLegend().setEnabled(false); // x label 제거
        lineChart.getDescription().setEnabled(false); // description label 제거
        lineChart.getAxisLeft().setInverted(true); // y축 reverse
        lineChart.setScaleEnabled(false); // zoom disable
        //lineChart.setXAxisRenderer(new CustomXAxisRenderer(lineChart.getViewPortHandler(), lineChart.getXAxis(), lineChart.getTransformer(YAxis.AxisDependency.LEFT) ));

        int firstLineColor = ContextCompat.getColor(getContext(),R.color.secondary_chart);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setTextColor(firstLineColor);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);

        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);
        yLAxis.setAxisMaximum(100);
        yLAxis.setDrawLabels(false);
        yLAxis.setDrawAxisLine(false);
        yLAxis.setDrawGridLines(false);

        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        final String[] date = {"27위", "19위", "22위", "24위", "20위", "18위", "13위"};
        xAxis.setValueFormatter(new IndexAxisValueFormatter(date));

        tv_toplate = getView().findViewById(R.id.rank_textview_toplate);
        tv_toplate.setText("13위");
    }


    // retrofit2 사용예시
    private void loadData() {
        progressOn();
        userService.getRank().enqueue(new Callback<UserRankResponse>() {
            @Override
            public void onResponse(Call<UserRankResponse> call, Response<UserRankResponse> response) {
                if(response.isSuccessful()) {
                    ArrayList<UserRank> result = response.body().getData();
//                    tv_temp.setText(result.get(0).getCategory()); // 임시로 텍스트 변경
//                    rankData.add(result);

                    Glide.with(getActivity()).load(result.get(0).getProfileUrl()).apply(new RequestOptions().circleCrop()).into(ivRank1);
                    Glide.with(getActivity()).load(result.get(1).getProfileUrl()).apply(new RequestOptions().circleCrop()).into(ivRank2);
                    Glide.with(getActivity()).load(result.get(2).getProfileUrl()).apply(new RequestOptions().circleCrop()).into(ivRank3);

                    tvNameRank1.setText(result.get(0).getName());
                    tvNameRank2.setText(result.get(1).getName());
                    tvNameRank3.setText(result.get(2).getName());

                    tvStarRank1.setText(result.get(0).getStar());
                    tvStarRank2.setText(result.get(1).getStar());
                    tvStarRank3.setText(result.get(2).getStar());

                    for(int i = 3; i<result.size(); i++) {
                        rankData.add(result.get(i));
                    }
                    initAdapter();
                    progressOff();
                }
            }

            @Override
            public void onFailure(Call<UserRankResponse> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
                progressOff();
            }
        });
    }

}