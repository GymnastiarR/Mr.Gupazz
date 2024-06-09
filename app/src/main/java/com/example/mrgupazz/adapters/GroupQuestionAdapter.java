package com.example.mrgupazz.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mrgupazz.R;
import com.example.mrgupazz.model.GroupQuestionModel;

import java.util.ArrayList;

public class GroupQuestionAdapter extends RecyclerView.Adapter<GroupQuestionAdapter.ViewHolder> {
    protected ArrayList<GroupQuestionModel> datas;

    public GroupQuestionAdapter(ArrayList<GroupQuestionModel> datas){
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_question_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GroupQuestionModel data = datas.get(position);
        holder.type.setText(data.getType());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void CustomAdapter(ArrayList<GroupQuestionModel> datas) {
        this.datas = datas;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView type;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            type = (TextView) view.findViewById(R.id.type);
        }

        public TextView getTextView() {
            return type;
        }
    }
}
