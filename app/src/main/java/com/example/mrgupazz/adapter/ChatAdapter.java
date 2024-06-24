package com.example.mrgupazz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mrgupazz.R;
import com.example.mrgupazz.api.ai_assistant.ChatMessage;

import java.util.List;

public class ChatAdapter extends ArrayAdapter<ChatMessage> {
    public ChatAdapter(@NonNull Context context, List<ChatMessage> resource) {
        super(context, 0, resource);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ChatMessage item = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.chat_item, parent, false);
        }

        // Lookup view for data population
        TextView textView = convertView.findViewById(R.id.chat_bubble);

        // Populate the data into the template view using the data object
        textView.setText(item.getMessage());

        // Return the completed view to render on screen
        return convertView;
    }
//    private List<ChatMessage> chatMessages;
//
//    public ChatAdapter(List<ChatMessage> chatMessages) {
//        this.chatMessages = chatMessages;
//    }
//
//    @NonNull
//    @Override
//    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(viewType == 0 ? R.layout.item_chat_sent : R.layout.item_chat_received, parent, false);
//        return new ChatViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
//        ChatMessage chatMessage = chatMessages.get(position);
//        holder.bind(chatMessage);
//    }
//
//    @Override
//    public int getItemCount() {
//        return chatMessages.size();
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return chatMessages.get(position).isSentByUser() ? 0 : 1;
//    }
//
//    public static class ChatViewHolder extends RecyclerView.ViewHolder {
//        private TextView tvMessage;
//
//        public ChatViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tvMessage = itemView.findViewById(R.id.tv_message);
//        }
//
//        public void bind(ChatMessage chatMessage) {
//            tvMessage.setText(chatMessage.getMessage());
//        }
//    }
}

