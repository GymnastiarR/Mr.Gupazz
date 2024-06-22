// ListLearningAdapter.java
package com.example.mrgupazz.adapter;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mrgupazz.CompletingActivity;
import com.example.mrgupazz.R;
import com.example.mrgupazz.api.learningapi.Learning;

import java.util.ArrayList;

public class ListLearningAdapter extends RecyclerView.Adapter<ListLearningAdapter.ListViewHolder> {

    private ArrayList<Learning> listWord;
    private OnItemClickCallback onItemClickCallback;

    public ListLearningAdapter(ArrayList<Learning> list) {
        this.listWord = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item_learning, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Learning learning = listWord.get(position);
        holder.letter.setText(learning.getNames());
        holder.wordCount.setText(learning.getDetails());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CompletingActivity.class);
                intent.putExtra("EXTRA_LETTER", learning.getNames());
//                intent.putExtra("EXTRA_WORD_COUNT", learning.getDetails());
                v.getContext().startActivity(intent);

                if (onItemClickCallback != null) {
                    onItemClickCallback.onItemClicked(listWord.get(holder.getAdapterPosition()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listWord.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView letter;
        TextView wordCount;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            letter = itemView.findViewById(R.id.letter);
            wordCount = itemView.findViewById(R.id.word_count);
        }
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(Learning data);
    }
}
