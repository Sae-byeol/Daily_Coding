package com.example.dailycoding.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailycoding.R;

import java.util.ArrayList;

public class AgreeCheckAdapter extends RecyclerView.Adapter<AgreeCheckAdapter.ViewHolder> {

    //리사이클러뷰로 보여줄 리스트
    public ArrayList<CheckModel> item_list;
    public AgreeCheckAdapter(ArrayList<CheckModel> item_list) {
        this.item_list = item_list;
    }

    //뷰홀더 그릇에 담을 내용
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView item_name;
        public CheckBox chkSelected;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_name=(TextView)itemView.findViewById(R.id.item_textView);
            chkSelected=(CheckBox)itemView.findViewById(R.id.item_checkBox);
        }
    }
    @NonNull
    @Override
    public AgreeCheckAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.agree_check_item,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull AgreeCheckAdapter.ViewHolder holder, int position) {
        final int pos=position;
        holder.item_name.setText(item_list.get(position).getItemName());
        holder.chkSelected.setChecked(item_list.get(position).isSelected());
        holder.chkSelected.setTag(item_list.get(position));

        //각 아이템의 체크박스 클릭 이벤트
        holder.chkSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb=(CheckBox)v;
                //누른 체크박스에 해당하는 모델 객체 가지고오기
                CheckModel model=(CheckModel)cb.getTag();
                //모델객체의 멤버인 isSelected 값 변경
                model.setSelected(cb.isChecked());
                item_list.get(pos).setSelected(cb.isChecked());


            }
        });
    }

    @Override
    public int getItemCount() {
        return item_list.size();
    }
}
