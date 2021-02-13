package com.example.dailycoding.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.dailycoding.R;

import java.util.List;

import retrofit2.http.PATCH;

public class SpinnerAdapter extends BaseAdapter {

    Context context;
    List<String> data;
    LayoutInflater inflater;
    public View itemView;
    int currentPos;


    public SpinnerAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        if (data != null) return data.size();
        else return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.spinner_item, parent, false);
        }

        if (data != null) {
            //데이터세팅
            String text = data.get(position);
            currentPos = position;
            ((TextView) convertView.findViewById(R.id.spinnerText)).setText(text);

        }

        itemView = convertView;
        return itemView;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.spinner_dropdown, parent, false);

        }

        //데이터세팅
        String text = data.get(position);
        ((TextView) convertView.findViewById(R.id.spinnerText)).setText(text);

        if (((TextView) convertView.findViewById(R.id.spinnerText)).getText() == "JAVA") {
            if(currentPos == 0) {
                ((TextView) convertView.findViewById(R.id.spinnerText)).setBackgroundResource(R.drawable.round_border_clicked);
                ((TextView) convertView.findViewById(R.id.spinnerText)).setTextColor(ContextCompat.getColor(context, R.color.click_font));
            }
            else {
                ((TextView) convertView.findViewById(R.id.spinnerText)).setBackgroundResource(R.drawable.round_border_unclicked);
                ((TextView) convertView.findViewById(R.id.spinnerText)).setTextColor(ContextCompat.getColor(context, R.color.black));
            }
        }

        if (((TextView) convertView.findViewById(R.id.spinnerText)).getText() == "C++") {
            if(currentPos == 1) {
                ((TextView) convertView.findViewById(R.id.spinnerText)).setBackgroundResource(R.drawable.round_border_clicked);
                ((TextView) convertView.findViewById(R.id.spinnerText)).setTextColor(ContextCompat.getColor(context, R.color.click_font));
            }
            else {
                ((TextView) convertView.findViewById(R.id.spinnerText)).setBackgroundResource(R.drawable.round_border_unclicked);
                ((TextView) convertView.findViewById(R.id.spinnerText)).setTextColor(ContextCompat.getColor(context, R.color.black));
            }
        }

        if (((TextView) convertView.findViewById(R.id.spinnerText)).getText() == "Python") {
            if(currentPos == 2) {
                ((TextView) convertView.findViewById(R.id.spinnerText)).setBackgroundResource(R.drawable.round_border_clicked);
                ((TextView) convertView.findViewById(R.id.spinnerText)).setTextColor(ContextCompat.getColor(context, R.color.click_font));
            }
            else {
                ((TextView) convertView.findViewById(R.id.spinnerText)).setBackgroundResource(R.drawable.round_border_unclicked);
                ((TextView) convertView.findViewById(R.id.spinnerText)).setTextColor(ContextCompat.getColor(context, R.color.black));
            }
        }

        return convertView;

    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
