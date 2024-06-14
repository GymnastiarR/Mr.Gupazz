package com.example.mrgupazz.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListLearningAdapter extends RecyclerView.Adapter<ListLearningAdapter.ListViewHolder> {


        private ArrayList<Alphabet> listWord;
        private OnItemClickCallback onItemClickCallback;

        public ListLearningAdapter(ArrayList<Alphabet> list){
            this.listWord = list;
        }

        @NonNull
        @Override
        public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_list_item,parent,false);
            return new ListViewHolder(view);
        }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
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
            void onItemClicked(Word data);
    }
    }
}
