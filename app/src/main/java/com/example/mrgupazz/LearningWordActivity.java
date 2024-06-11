package com.example.mrgupazz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mrgupazz.api.dictionaryapi.APIResponse;
import com.example.mrgupazz.api.dictionaryapi.Meanings;
import com.example.mrgupazz.retrofit.dictionaryapi.OnFetchDataListener;
import com.example.mrgupazz.retrofit.dictionaryapi.RequestManager;

import java.util.List;

public class LearningWordActivity extends AppCompatActivity {
    public static final String EXTRA_WORD = "extra_word";
    TextView tv_title, tv_word, tv_clause, tv_pronounce;
    ImageView imgBack;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_word);

        String word = getIntent().getStringExtra(EXTRA_WORD);

        tv_title = findViewById(R.id.tv_title);
        tv_word = findViewById(R.id.tv_word);
        tv_clause = findViewById(R.id.tv_clause);
        tv_pronounce = findViewById(R.id.tv_pronounce);
        imgBack = findViewById(R.id.img_back_toAlphabet);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveWithNoData = new Intent(LearningWordActivity.this, LearningAlphabetActivity.class);
                startActivity(moveWithNoData);
            }
        });

        RequestManager manager = new RequestManager(LearningWordActivity.this);
        manager.getWordMeaning(listener, word);
    }

    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(APIResponse apiResponse, String message) {
            progressDialog.dismiss();
            if (apiResponse == null) {
                Toast.makeText(LearningWordActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                return;
            }
            showData(apiResponse);
        }

        @Override
        public void onError(String message) {
            progressDialog.dismiss();
            Toast.makeText(LearningWordActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private void showData(APIResponse apiResponse) {
        tv_word.setText(apiResponse.getWord());
        tv_title.setText(apiResponse.getWord());
        tv_pronounce.setText(apiResponse.getPhonetic());

        List<Meanings> meanings = apiResponse.getMeanings();

        if (meanings != null && !meanings.isEmpty()) {
            String partOfSpeechAtIndex1 = meanings.get(0).getPartOfSpeech();
            tv_clause.setText(partOfSpeechAtIndex1);
        } else {
            tv_clause.setText("-");
        }
    }
}
