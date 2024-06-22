package com.example.mrgupazz;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LearningWordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_word);

        TextView textView = findViewById(R.id.tv_selected_letter);
        String selectedLetter = getIntent().getStringExtra("SELECTED_LETTER");
        textView.setText("Selected Letter: " + selectedLetter);
    }
}
