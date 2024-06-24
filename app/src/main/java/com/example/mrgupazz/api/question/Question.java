package com.example.mrgupazz.api.question;

import java.util.ArrayList;

public class Question {
    protected int id;
    protected String content;

    protected String answer;
    protected ArrayList<Option> options;
    protected String imageUrl;


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void addOption(Option option){
        this.options.add(option);
    }

    public void setOptions(ArrayList<Option> options){
        this.options = options;
    }
    public  ArrayList<Option> getOptions(){
        return  this.options;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
