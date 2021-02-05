package com.example.dailycoding.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dailycoding.R;
import com.example.dailycoding.api.ApiUtils;
import com.example.dailycoding.api.ServiceApi;
import com.example.dailycoding.model.CategoryResponse;
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

public class HomeFragment extends BaseFragment {

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
    private ImageButton btn_more;

    private int selected;
    private Button btnJava;
    private Button btnC;
    private Button btnPython;


    // retrofit2
    private ServiceApi service;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //retrofit2 객체 할당
        service = ApiUtils.getServiceApi();

        init();
        initListener();
        initSpinner();
        initChart();
        initMultiline();
        initAdapter();
        
        // Retrofit2 Test
        //loadData("python");
    }

    private void init() {
        tv_temp = getView().findViewById(R.id.home_textview_ready);
        btn_more = getView().findViewById(R.id.home_button_more);
        selected = 0;
    }

    private void initListener() {
        btn_more.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(RankFragment.newInstance());
            }
        });

    }

    private void initAdapter() {

        data = new ArrayList<>();

        data.add("0");
        data.add("0");
        data.add("0");
        data.add("0");
        data.add("0");
        data.add("0");

        recyclerView = (RecyclerView) getView().findViewById(R.id.home_recyclerview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new HomeAdapter(data);
        recyclerView.setAdapter(mAdapter);

    }

    private void initSpinner() {
        // spinner init
        mSpinner = getView().findViewById(R.id.home_spinner);

        List<String> data = new ArrayList<>();
        data.add("JAVA");
        data.add("C++");
        data.add("Python");

        spinnerAdapter = new SpinnerAdapter(requireContext(), data);
        mSpinner.setAdapter(spinnerAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getContext(), data.get(position), Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
                        selected = 0;
                    case 1:
                        selected = 1;
                    case 2:
                        selected = 2;
                }

                switch (selected) {
                    case 0:
                    case 1:
                    case 2:
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initChart() {
        lineChart = (LineChart) getView().findViewById(R.id.home_chart);

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
        lineDataSet.setColor(Color.BLACK);
        int[] colors = {Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,ContextCompat.getColor(getContext(),R.color.color_primary_light)};
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
        yLAxis.setTextColor(ContextCompat.getColor(getContext(),R.color.color_primary_gray));
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
        lineChart = (LineChart) getView().findViewById(R.id.home_chart_multiline);

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

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setTextColor(Color.BLACK);
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

        tv_toplate = getView().findViewById(R.id.home_textview_toplate);
        tv_toplate.setText("13위");
    }


    // retrofit2 사용예시
    private void loadData(String language) {
        progressOn();
        service.getData(language).enqueue(new Callback<ArrayList<CategoryResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<CategoryResponse>> call, Response<ArrayList<CategoryResponse>> response) {
                if(response.isSuccessful()) {
                    progressOff();
                    ArrayList<CategoryResponse> result = response.body();
                    /**
                     * [
                     *     {
                     *         "category": "something python"     //result[0]
                     *     },
                     *     {
                     *         "category": "category?"            //result[1]
                     *     },
                     *     {
                     *         "category": "category01"
                     *     }
                     * ]
                     */
                    tv_temp.setText(result.get(0).getCategory()); // 임시로 텍스트 변경
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CategoryResponse>> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
            }
        });
    }

}