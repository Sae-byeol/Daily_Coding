package com.example.dailycoding.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.ColorSpace;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailycoding.R;

import java.util.ArrayList;

public class AgreeCheckAdapter extends RecyclerView.Adapter<AgreeCheckAdapter.ViewHolder> {

    //Item의 클릭 상태를 저장할 array 객체
    private SparseBooleanArray selectedItems=new SparseBooleanArray();
    //직전에 클릭했던 Item의 position
    private int prePosition=-1;

    //리사이클러뷰로 보여줄 리스트
    public ArrayList<CheckModel> item_list;
    public AgreeCheckAdapter(ArrayList<CheckModel> item_list) {
        this.item_list = item_list;
    }

    //뷰홀더 그릇에 담을 내용
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView item_name;
        public CheckBox chkSelected;
        public ImageButton button;
        public TextView textView_expanded;
        public CheckModel model;
        public int position;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_name=(TextView)itemView.findViewById(R.id.item_textView);
            chkSelected=(CheckBox)itemView.findViewById(R.id.item_checkBox);
            button=(ImageButton)itemView.findViewById(R.id.item_imageButton);
            textView_expanded=(TextView)itemView.findViewById(R.id.cl_item_expanded);
        }
        void onBind(CheckModel model,int position){
            this.model=model;
            this.position=position;

            item_name.setText(model.getItemName());
            chkSelected.setChecked(model.isSelected());

            //일단 ItemName을 펼쳤을때의 내용으로 하도록 하고
            //나중에 약관 내용 찾아서(?) 수정
            textView_expanded.setText(model.getItemName());

            changeVisibility(selectedItems.get(position));

            button.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (selectedItems.get(position)) {
                // 펼쳐진 Item을 클릭 시
                selectedItems.delete(position);
            } else {
                // 직전의 클릭됐던 Item의 클릭상태를 지움
                selectedItems.delete(prePosition);
                // 클릭한 Item의 position을 저장
                selectedItems.put(position, true);
            }
            // 해당 포지션의 변화를 알림
            if (prePosition != -1) notifyItemChanged(prePosition);
            notifyItemChanged(position);
            // 클릭된 position 저장
            prePosition = position;
        }
        private void changeVisibility(final boolean isExpanded) {

            int height = textView_expanded.getHeight();

            // ValueAnimator.ofInt(int... values)는 View가 변할 값을 지정, 인자는 int 배열
            ValueAnimator va = isExpanded ? ValueAnimator.ofInt(0, height) : ValueAnimator.ofInt(height, 0);
            // Animation이 실행되는 시간, n/1000초
            va.setDuration(600);
            va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    // value는 height 값
                    int value = (int) animation.getAnimatedValue();
                    // imageView의 높이 변경
                    textView_expanded.getLayoutParams().height = value;
                    textView_expanded.requestLayout();
                    // imageView가 실제로 사라지게하는 부분
                    textView_expanded.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
                }
            });
            // Animation start
            va.start();
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
        holder.onBind(item_list.get(position),position);
    }

    @Override
    public int getItemCount() {
        return item_list.size();
    }

    //외부에서 item을 추가하기 위한 함수
    void addItem(CheckModel model){
        item_list.add(model);
    }
}
