package com.example.mrgupazz;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mrgupazz.adapter.GroupQuestionAdapter;
import com.example.mrgupazz.api.groupquestion.GroupQuestion;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExerciseFragment extends Fragment {
    ArrayList<GroupQuestion> groupQuestionList = new ArrayList<>();
    GroupQuestionAdapter groupQuestionAdapter;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise, container, false);
        recyclerView = view.findViewById(R.id.group_question);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getData();
        return view;
    }

    public void getData(){
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(getContext());
        String url = "https://mrgupazzapi.pribadi-gymnas-2911.workers.dev/group-question";
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET,url,null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray result = response.getJSONArray("groupQuestions");
                    for (int i=0;i<result.length();i++){
                        JSONObject jsonObject = result.getJSONObject(i);
                        Log.d("my-api","==== "+jsonObject.getString("type"));
                        Log.d("my-api","==== "+jsonObject.getString("id"));
                        GroupQuestion groupQuestion = new GroupQuestion();
                        groupQuestion.setId(Integer.parseInt(jsonObject.getString("id")));
                        groupQuestion.setType(jsonObject.getString("type"));
                        groupQuestionList.add(groupQuestion);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                groupQuestionAdapter = new GroupQuestionAdapter(groupQuestionList);
                recyclerView.setAdapter(groupQuestionAdapter);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("my-api", error.getMessage());
                Log.d("my-api","went Wrong");
            }
        }){
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json; charset=UTF-8");
                params.put("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6Imd5bW5hc0BnbWFpbC5jb20iLCJyb2xlIjoidXNlciJ9.wg-RBSQNGjzo21GwpPWbaFqU0QEudhw8twq9b2g6PxA");
                return params;
            }

        };
        requestQueue.add(jsonArrayRequest);
    }
}