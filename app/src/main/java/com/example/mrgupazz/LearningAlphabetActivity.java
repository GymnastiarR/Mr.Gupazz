package com.example.mrgupazz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mrgupazz.adapter.ListWordAdapter;
import com.example.mrgupazz.api.wordapi.Word;
import com.example.mrgupazz.api.wordapi.WordData;
import java.util.ArrayList;
import androidx.appcompat.widget.SearchView;

public class LearningAlphabetActivity extends AppCompatActivity {
    private RecyclerView rvWord;
    private ImageView imgBack;
    private ArrayList<Word> list = new ArrayList<>();
    private ListWordAdapter listWordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_alphabet);

        rvWord = findViewById(R.id.rv_word);
        rvWord.setHasFixedSize(true);

        list.addAll(WordData.getListDataC());
        showRecyclerList();

        imgBack = findViewById(R.id.img_back);
        // imgBack.setOnClickListener(v -> {
        //     Intent moveWithNoData = new Intent(LearningAlphabetActivity.this, LearningActivity.class);
        //     startActivity(moveWithNoData);
        // });

        SearchView searchView = findViewById(R.id.sv_word);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                listWordAdapter.filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listWordAdapter.filter(newText);
                return false;
            }
        });
    }

    private void showRecyclerList() {
        rvWord.setLayoutManager(new LinearLayoutManager(this));
        listWordAdapter = new ListWordAdapter(list);
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
