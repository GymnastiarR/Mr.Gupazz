package com.example.mrgupazz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mrgupazz.adapter.ListWordAdapter;
import com.example.mrgupazz.api.wordapi.Word;
import com.example.mrgupazz.api.wordapi.WordData;
import java.util.ArrayList;
import androidx.appcompat.widget.SearchView;

public class LearningAlphabetActivity extends AppCompatActivity {
    public static final String EXTRA_LETTER = "extra_letter";
    private RecyclerView rvWord;
    private TextView tvAlphabet;
    private ImageView imgBack;
    private ArrayList<Word> list = new ArrayList<>();
    private ListWordAdapter listWordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_alphabet);

        String letter = getIntent().getStringExtra(EXTRA_LETTER);

        tvAlphabet = findViewById(R.id.tv_alphabet);
        tvAlphabet.setText(letter);

        rvWord = findViewById(R.id.rv_word);
        rvWord.setHasFixedSize(true);

        switch (letter) {
            case "A":
                list.addAll(WordData.getListDataA());
                break;
            case "B":
                list.addAll(WordData.getListDataB());
                break;
            case "C":
                list.addAll(WordData.getListDataC());
                break;
            case "D":
                list.addAll(WordData.getListDataD());
                break;
            case "E":
                list.addAll(WordData.getListDataE());
                break;
            case "F":
                list.addAll(WordData.getListDataF());
                break;
            case "G":
                list.addAll(WordData.getListDataG());
                break;
            case "H":
                list.addAll(WordData.getListDataH());
                break;
            case "I":
                list.addAll(WordData.getListDataI());
                break;
            case "J":
                list.addAll(WordData.getListDataJ());
                break;
            case "K":
                list.addAll(WordData.getListDataK());
                break;
            case "L":
                list.addAll(WordData.getListDataL());
                break;
            case "M":
                list.addAll(WordData.getListDataM());
                break;
            case "N":
                list.addAll(WordData.getListDataN());
                break;
            case "O":
                list.addAll(WordData.getListDataO());
                break;
            case "P":
                list.addAll(WordData.getListDataP());
                break;
            case "Q":
                list.addAll(WordData.getListDataQ());
                break;
            case "R":
                list.addAll(WordData.getListDataR());
                break;
            case "S":
                list.addAll(WordData.getListDataS());
                break;
            case "T":
                list.addAll(WordData.getListDataT());
                break;
            case "U":
                list.addAll(WordData.getListDataU());
                break;
            case "V":
                list.addAll(WordData.getListDataV());
                break;
            case "W":
                list.addAll(WordData.getListDataW());
                break;
            case "X":
                list.addAll(WordData.getListDataX());
                break;
            case "Y":
                list.addAll(WordData.getListDataY());
                break;
            case "Z":
                list.addAll(WordData.getListDataZ());
                break;
            default:
                Log.e("LearningAlphabet", "Invalid letter: " + letter);
                break;
        }

        showRecyclerList();


        imgBack = findViewById(R.id.img_back);
         imgBack.setOnClickListener(v -> {
             Intent moveWithNoData = new Intent(this, MainActivity.class);
             moveWithNoData.putExtra("targetFragment", "LearningFragment");
             startActivity(moveWithNoData);
         });

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
