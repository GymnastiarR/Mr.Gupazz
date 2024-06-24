package com.example.mrgupazz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mrgupazz.adapter.ChatAdapter;
import com.example.mrgupazz.api.ai_assistant.ChatMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AiAssistantFragment extends Fragment {
    private RecyclerView rvChat;
    private EditText etMessage;
    private Button btnSend;
    private ChatAdapter chatAdapter;
    private List<ChatMessage> chatMessages = new ArrayList<>();

    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ai_assistant, container, false);

        listView = view.findViewById(R.id.chats);

        chatAdapter = new ChatAdapter(getContext(), chatMessages);

//        rvChat = view.findViewById(R.id.rv_chat);
        etMessage = view.findViewById(R.id.et_message);
        btnSend = view.findViewById(R.id.btn_send);

        btnSend.setOnClickListener(v -> sendMessage());

        listView.setAdapter(chatAdapter);
        return view;
    }

    private void updateView(){
        chatAdapter = new ChatAdapter(getContext(), chatMessages);
        listView.setAdapter(chatAdapter);
    }

    private void sendMessage(){
        String message = etMessage.getText().toString();
        chatMessages.add(new ChatMessage(message));
        etMessage.setText("");
        updateView();
        requestToGPT(message);
    }

    private void requestToGPT(String question) {
        String url = "https://api.openai.com/v1/chat/completions";
        RequestQueue queue = Volley.newRequestQueue(getContext());

        btnSend.setEnabled(false);

        JSONObject postData = new JSONObject();
        JSONArray messages = new JSONArray();
        JSONObject message = new JSONObject();
//        postData.put("model", "");
        try {
            message.put("role", "user");
            message.put("content", question);
            messages.put(message);
            postData.put("messages", messages);
            postData.put("model", "gpt-3.5-turbo");
            postData.put("temperature", 0.7);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, postData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("test", String.valueOf(response));
                            JSONArray choices = response.getJSONArray("choices");
                            JSONObject choice = choices.getJSONObject(0);
                            String content = choice.getJSONObject("message").getString("content");
                            chatMessages.add(new ChatMessage(content));
                            updateView();
                        } catch (JSONException e) {
                            Toast.makeText(getContext(), "Failed to parse response", Toast.LENGTH_SHORT).show();
                        }
                        btnSend.setEnabled(true);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle login error
                        Log.d("Test", error.getMessage());
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer ");
                return headers;
            }
        };
        queue.add(request);
    }
}
