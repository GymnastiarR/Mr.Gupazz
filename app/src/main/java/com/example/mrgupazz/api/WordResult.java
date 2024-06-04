package com.example.mrgupazz.api;

public class WordResult {
    private String word;
    private String phonetic;

    public WordResult(String word, String phonetic) {
        this.word = word;
        this.phonetic = phonetic;
    }

    public String getWord() {
        return this.word;
    }

    public String getPhonetic() {
        return this.phonetic;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }
}
