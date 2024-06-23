package com.example.mrgupazz.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mrgupazz.R;
import com.example.mrgupazz.api.wordapi.Word;

import java.util.ArrayList;
import java.util.List;

public class ListWordAdapter extends RecyclerView.Adapter<ListWordAdapter.ListViewHolder> {
    private ArrayList<Word> listWord;
    private ArrayList<Word> listWordFull;
    private OnItemClickCallback onItemClickCallback;

    public ListWordAdapter(ArrayList<Word> list){
        this.listWord = list;
        this.listWordFull = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_list_item,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Word word = listWord.get(position);
        holder.tvKata.setText(word.getWord());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        TextView tvKata;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvKata = itemView.findViewById(R.id.tv_kata);
        }
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(Word data);
    }

    // Method to filter the list
    public void filter(String text) {
        listWord.clear();
        if (text.isEmpty()) {
            listWord.addAll(listWordFull);
        } else {
            text = text.toLowerCase();
            for (Word item : listWordFull) {
                if (item.getWord().toLowerCase().contains(text)) {
                    listWord.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}
