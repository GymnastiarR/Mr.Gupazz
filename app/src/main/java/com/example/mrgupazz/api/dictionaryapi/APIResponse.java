package com.example.mrgupazz.api.dictionaryapi;

import java.util.List;

public class APIResponse {
    String word = "";
    String phonetic = "";
    List<Meanings> meanings= null;

    public List<Meanings> getMeanings() {
        return meanings;
    }

    public void setMeanings(List<Meanings> meanings) {
        this.meanings = meanings;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }
}
