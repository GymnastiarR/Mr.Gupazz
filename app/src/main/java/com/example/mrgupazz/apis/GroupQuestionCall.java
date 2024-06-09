package com.example.mrgupazz.apis;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mrgupazz.model.GroupQuestionModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GroupQuestionCall {
//    protected ArrayList<GroupQuestionModel> groupQuestionModels;
//    public void getData(){
//        RequestQueue requestQueue;
//        requestQueue = Volley.newRequestQueue(this);
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,"https://jsonplaceholder.typicode.com/todos",null,new Response.Listener<JSONObject>(){
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    JSONArray result = response.getJSONArray("groupQuestions");
//                    for (int i=0;i<result.length();i++){
//                        JSONObject jsonObject = result.getJSONObject(i);
//                        Log.d("my-api","==== "+jsonObject.getString("title"));
//                        Log.d("my-api","==== "+jsonObject.getString("userId"));
//                        Log.d("my-api","==== "+jsonObject.getString("id"));
//                        GroupQuestionModel groupQuestionModel = new GroupQuestionModel();
//                        groupQuestionModel.setId(Integer.parseInt(jsonObject.getString("id")));
//                        groupQuestionModel.setType(jsonObject.getString("type"));
//                        groupQuestionModels.add(groupQuestionModel);
//                        arrayList.add(new DBHelper(Integer.parseInt(jsonObject.getString("userId")),Integer.parseInt(jsonObject.getString("id")),jsonObject.getString("title")));
//                    }
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        },new Response.ErrorListener(){
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("my-api","went Wrong");
//            }
//        });
//        requestQueue.add(jsonArrayRequest);
//    }
}
