package com.example.mrgupazz.api.ai_assistant;

public class ChatMessage {
    private String message;
    private boolean isSentByUser;

    public ChatMessage(String message, boolean isSentByUser) {
        this.message = message;
        this.isSentByUser = isSentByUser;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSentByUser() {
        return isSentByUser;
    }
}
