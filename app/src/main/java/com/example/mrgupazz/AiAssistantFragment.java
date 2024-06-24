package com.example.mrgupazz;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mrgupazz.adapter.ChatAdapter;
import com.example.mrgupazz.api.ai_assistant.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class AiAssistantFragment extends Fragment {
    private RecyclerView rvChat;
    private EditText etMessage;
    private Button btnSend;
    private ChatAdapter chatAdapter;
    private List<ChatMessage> chatMessages;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ai_assistant, container, false);

        rvChat = view.findViewById(R.id.rv_chat);
        etMessage = view.findViewById(R.id.et_message);
        btnSend = view.findViewById(R.id.btn_send);

        chatMessages = new ArrayList<>();
        chatAdapter = new ChatAdapter(chatMessages);
        rvChat.setLayoutManager(new LinearLayoutManager(getContext()));
        rvChat.setAdapter(chatAdapter);

        btnSend.setOnClickListener(v -> sendMessage());

        return view;
    }

    private void sendMessage() {
        String message = etMessage.getText().toString().trim();
        if (!message.isEmpty()) {
            chatMessages.add(new ChatMessage(message, true));
            chatAdapter.notifyItemInserted(chatMessages.size() - 1);
            rvChat.scrollToPosition(chatMessages.size() - 1);
            etMessage.setText("");

            // Simulate AI response (in a real app, you would call your API here)
            new Handler().postDelayed(() -> receiveMessage("I did my homework when I was sick yesterday."), 1000);
        }
    }

    private void receiveMessage(String message) {
        chatMessages.add(new ChatMessage(message, false));
        chatAdapter.notifyItemInserted(chatMessages.size() - 1);
        rvChat.scrollToPosition(chatMessages.size() - 1);
    }
}
