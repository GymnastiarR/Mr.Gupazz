package com.example.mrgupazz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mrgupazz.api.question.Option;
import com.example.mrgupazz.api.question.Question;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CompletingActivity extends AppCompatActivity {
    protected ArrayList<RadioXOption> radioXOptions = new ArrayList<>();
    protected ArrayList<Question> questions;
    protected TextView question;
    protected int currentQuestion = 0;
    protected Button checkButton;
    protected RadioGroup radioGroup;
    protected Button nextCorrectButton;
    protected Button nextWrongButton;
    protected LinearLayout notif;
    protected RadioButton checkedRadioButton;
    protected MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completing);
        this.getComponent();
        getData();
    }

    protected void getComponent(){
        RadioXOption option_one = new RadioXOption(findViewById(R.id.radio_option_one), findViewById(R.id.option_one));
        RadioXOption option_two = new RadioXOption(findViewById(R.id.radio_option_two), findViewById(R.id.option_two));

        radioXOptions.add(option_one);
        radioXOptions.add(option_two);

        nextCorrectButton = findViewById(R.id.next_correct_button);
        nextWrongButton = findViewById(R.id.next_wrong_button);

        question = findViewById(R.id.question);
        checkButton = findViewById(R.id.check_button);
        radioGroup = findViewById(R.id.radio_option);

        for(RadioXOption radioXOption: radioXOptions){
            radioXOption.optionButton.setOnClickListener((View v) ->
                    radioXOption.getRadioButton().setChecked(true)
            );
        }

        notif = findViewById(R.id.correct_notif);

        radioGroup.setOnCheckedChangeListener((RadioGroup group, int checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            checkedRadioButton = radioButton;
            for(RadioXOption radioXOption: radioXOptions){
                if(radioXOption.getRadioButton() == radioButton){
                    changeButtonColor(radioXOption.getOptionButton());
                }
            }
        });


        checkButton.setOnClickListener((View v) -> {
            if(checkedRadioButton == null){
                Toast.makeText(this, "This is my Toast message!",
                        Toast.LENGTH_LONG).show();
                return;
            }

            for(RadioXOption radioXOption: radioXOptions){
                if(radioXOption.getRadioButton() == checkedRadioButton){
                    Option option = radioXOption.getOption();
                    if(option.isTrue()){
                        notif = findViewById(R.id.correct_notif);
                        mediaPlayer = MediaPlayer.create(this, R.raw.correct);
                    }else{
                        notif = findViewById(R.id.wrong_notif);
                        mediaPlayer = MediaPlayer.create(this, R.raw.wrong);
                    }
                    mediaPlayer.start();
                    notif.setVisibility(View.VISIBLE);
                }
            }
        });

        nextCorrectButton.setOnClickListener((View view)->nextQuestion());

        nextWrongButton.setOnClickListener((View view)->nextQuestion());

        findViewById(R.id.back_button).setOnClickListener((View view)->backToMain());
    }

    protected void changeButtonColor(Button option){
        for(RadioXOption radioXOption: radioXOptions){
            radioXOption.optionButton.setSelected(false);
        }
        option.setSelected(true);
    }

    protected void nextQuestion(){
        this.currentQuestion = this.currentQuestion + 1;
        if(currentQuestion < this.questions.size()){
            setView(questions.get(this.currentQuestion));
            return;
        }
        backToMain();
    }

    protected void setView(Question question){
        ArrayList<Option> options = question.getOptions();
        for(int i = 0; i < options.size(); i++){
            radioXOptions.get(i).setOption(options.get(i));
        }
        this.question.setText(question.getContent());
        this.resetView();
    }

    protected void resetView(){
        notif.setVisibility(View.INVISIBLE);
        for(RadioXOption radioXOption : radioXOptions){
            radioXOption.getRadioButton().setChecked(false);
            radioXOption.getOptionButton().setSelected(false);
        }
    }

    protected void getData(){
        Intent intent = getIntent();
        int groupQuestionId = intent.getIntExtra("group_question_id", 0);

        if(groupQuestionId == 0){
            this.backToMain();
        }

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        String url = "https://mrgupazzapi.pribadi-gymnas-2911.workers.dev/group-question/" + String.valueOf(groupQuestionId);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    questions = new ArrayList<>();
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
                setView(questions.get(0));
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

    protected void backToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    static class RadioXOption{
        protected RadioButton radioButton;
        protected Option option;
        protected Button optionButton;

        RadioXOption(RadioButton radioButton, Button optionButton){
            this.radioButton = radioButton;
            this.optionButton = optionButton;
        }

        public void setOption(Option option) {
            this.optionButton.setText(option.getContent());
            this.option = option;
        }

        public RadioButton getRadioButton() {
            return radioButton;
        }

        public Button getOptionButton() {
            return optionButton;
        }

        public Option getOption() {
            return option;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}