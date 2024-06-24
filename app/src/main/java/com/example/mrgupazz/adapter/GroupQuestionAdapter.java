package com.example.mrgupazz.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mrgupazz.CompletingActivity;
import com.example.mrgupazz.R;
import com.example.mrgupazz.WritingActivity;
import com.example.mrgupazz.api.groupquestion.GroupQuestion;
import com.example.mrgupazz.api.question.Option;
import com.example.mrgupazz.api.question.Question;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GroupQuestionAdapter extends RecyclerView.Adapter<GroupQuestionAdapter.ViewHolder> {
    protected ArrayList<GroupQuestion> datas;
    protected Context context;
    protected ArrayList<Question> questions;

    public GroupQuestionAdapter(ArrayList<GroupQuestion> datas){
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_question_list, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GroupQuestion data = datas.get(position);
        String type = data.getType();
        String cap = type.substring(0, 1).toUpperCase() + type.substring(1);
        holder.type.setText(cap);
        holder.setId(data.getId());

        Drawable drawable;
        if(data.getType().equals("listening")){
            drawable = AppCompatResources.getDrawable(context, R.drawable.ic_ear);
        } else if (data.getType().equals("writing")) {
            drawable = AppCompatResources.getDrawable(context, R.drawable.ic_speaker);
        }else{
            drawable = AppCompatResources.getDrawable(context, R.drawable.ic_abc);
        }
        holder.groupQuestionImage.setImageDrawable(drawable);

        holder.question.setOnClickListener((View v)->{
            int id = holder.getId();
            Class<?> cls;

            if(data.getType().equals("writing")){
                cls = WritingActivity.class;
            } else if (data.getType().equals("completing")) {
                cls = CompletingActivity.class;
            }else{
                cls = CompletingActivity.class;
            }

            Intent intent = new Intent(context, cls);
            intent.putExtra("group_question_id", holder.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void CustomAdapter(ArrayList<GroupQuestion> datas) {
        this.datas = datas;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView groupQuestionImage;
        private final TextView type;
        private final CardView question;
        protected int id;
        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            groupQuestionImage = view.findViewById(R.id.group_question_img);
            type = view.findViewById(R.id.type);
            question = view.findViewById(R.id.question);
        }

        public TextView getTextView() {
            return type;
        }
        public int getId(){
            return id;
        }

        public void setId(int id){
            this.id = id;
        }
    }

    public void getDataQuestion(int groupQuestionId){
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(context);
        String url = "https://mrgupazzapi.pribadi-gymnas-2911.workers.dev/group-question/" + String.valueOf(groupQuestionId);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray result = response.getJSONArray("data");
                    for (int i=0;i<result.length();i++){
                        JSONObject jsonObject = result.getJSONObject(i);
                        Question question = new Question();
                        ArrayList<Option> options = new ArrayList<>();
                        question.setContent(jsonObject.getString("content"));
                        question.setId(jsonObject.getInt("id"));
                        JSONArray rsOptions = jsonObject.getJSONArray("option");

                        JSONObject option_one = rsOptions.getJSONObject(0);
                        JSONObject option_two = rsOptions.getJSONObject(1);

                        Option opt_one = new Option();
                        Option opt_two = new Option();

                        opt_one.setTrue(option_one.getBoolean("isTrue"));
                        opt_one.setContent(option_one.getString("content"));
                        opt_one.setId(option_one.getInt("id"));

                        opt_two.setTrue(option_two.getBoolean("isTrue"));
                        opt_two.setContent(option_two.getString("content"));
                        opt_two.setId(option_two.getInt("id"));

                        options.add(opt_one);
                        options.add(opt_two);
                        question.setOptions(options);

                        questions.add(question);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
//                groupQuestionAdapter = new GroupQuestionAdapter(groupQuestionList);
//                recyclerView.setAdapter(groupQuestionAdapter);
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
        requestQueue.add(jsonObjectRequest);
    }
}
