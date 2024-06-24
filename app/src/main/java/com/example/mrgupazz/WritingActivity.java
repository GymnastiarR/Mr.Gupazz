package com.example.mrgupazz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mrgupazz.api.question.Question;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class WritingActivity extends AppCompatActivity {
    protected EditText editTextAnswer;
    protected TextView question;
    protected ImageButton playSoundButton;
    protected ImageView playSoundSlowlyButton;
    protected ArrayList<Question> questions;
    protected Button checkButton;
    protected int currentQuestion = 0;
    protected TextToSpeech tts;
    protected LinearLayout alert;
    protected Button nextCorrectButton;
    protected Button nextWrongButton;
    protected ImageButton backButton;
    protected MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);
        getData();
        getComponent();
    }

    protected void getComponent(){
        playSoundButton = findViewById(R.id.play_sound);
        playSoundSlowlyButton = findViewById(R.id.play_sound_slowly);
        checkButton = findViewById(R.id.check_button);
        editTextAnswer = findViewById(R.id.answer);
        nextCorrectButton = findViewById(R.id.next_correct_button);
        nextWrongButton = findViewById(R.id.next_wrong_button);
        backButton = findViewById(R.id.back_button);

        tts = new TextToSpeech(this, (int status)->{
            if(status == TextToSpeech.SUCCESS){
                tts.setLanguage(Locale.US);
            }
        });

        playSoundButton.setOnClickListener((View view)->{
            Question question = questions.get(currentQuestion);
            tts.setSpeechRate((float)1);
            tts.speak(question.getContent(), TextToSpeech.QUEUE_FLUSH, null, null);
        });

        playSoundSlowlyButton.setOnClickListener((View view)->{
            Question question = questions.get(currentQuestion);
            tts.setSpeechRate((float)0.5);
            tts.speak(question.getContent(), TextToSpeech.QUEUE_FLUSH, null, null);
        });

        checkButton.setOnClickListener((View view)->{
            String answer = editTextAnswer.getText().toString().toLowerCase().replace(" ","");
            String correctAnswer = questions.get(currentQuestion).getContent().toLowerCase().replace(" ", "");
            if(answer.equals(correctAnswer)){
                alert = findViewById(R.id.correct_notif);
                mediaPlayer = MediaPlayer.create(this, R.raw.correct);
            }else{
                mediaPlayer = MediaPlayer.create(this, R.raw.wrong);
                alert = findViewById(R.id.wrong_notif);
            }
            mediaPlayer.start();
            alert.setVisibility(View.VISIBLE);
        });

        backButton.setOnClickListener((View view)->backToMain());
        nextCorrectButton.setOnClickListener((View view)->nextQuestion());
        nextWrongButton.setOnClickListener((View view)->nextQuestion());
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
                        question.setContent(jsonObject.getString("content"));
                        question.setId(jsonObject.getInt("id"));

                        questions.add(question);
                    }
                    Question question = questions.get(currentQuestion);
                    tts.speak(question.getContent(), TextToSpeech.QUEUE_FLUSH, null, null);
                }catch (Exception e){
                    e.printStackTrace();
                }
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

    protected void nextQuestion(){
        this.currentQuestion++;
        if(questions.size() > currentQuestion){
            editTextAnswer.setText("");
            this.alert.setVisibility(View.INVISIBLE);
            return;
        }
        this.backToMain();
    }

    protected void backToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
}