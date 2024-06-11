package com.example.mrgupazz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mrgupazz.adapter.ListWordAdapter;
import com.example.mrgupazz.api.wordapi.Word;
import com.example.mrgupazz.api.wordapi.WordData;

import java.util.ArrayList;

public class LearningAlphabetActivity extends AppCompatActivity {
    private RecyclerView rvWord;
    private ImageView imgBack;
    private ArrayList<Word> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_alphabet);

        rvWord = findViewById(R.id.rv_word);
        rvWord.setHasFixedSize(true);

        list.addAll(WordData.getListDataC());
        showRecyclerList();

        imgBack = findViewById(R.id.img_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveWithNoData = new Intent(LearningAlphabetActivity.this, LearningActivity.class);
                startActivity(moveWithNoData);
            }
        });

    }

    private void showRecyclerList() {
        rvWord.setLayoutManager(new LinearLayoutManager(this));
        ListWordAdapter listWordAdapter = new ListWordAdapter(list);
        rvWord.setAdapter(listWordAdapter);

        listWordAdapter.setOnItemClickCallback(new ListWordAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Word data) {
                Intent moveWithDataIntent = new Intent(LearningAlphabetActivity.this, LearningWordActivity.class);
                moveWithDataIntent.putExtra(LearningWordActivity.EXTRA_WORD, data.getWord());
                startActivity(moveWithDataIntent);
            }
        });
    }
}