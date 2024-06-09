package com.example.mrgupazz.api.dictionaryapi;

import java.util.List;

public class APIResponse {
    String word = "";
    List<Phonetics> phonetics= null;
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

    public List<Phonetics> getPhonetics() {
        return phonetics;
    }

    public void setPhonetics(List<Phonetics> phonetics) {
        this.phonetics = phonetics;
    }

}
