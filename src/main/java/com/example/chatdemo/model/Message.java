package com.example.chatdemo.model;

import org.hibernate.validator.constraints.NotEmpty;

public class Message {
    
    @NotEmpty(message="invalid message")
    private String contentMsg;

    public String getContentMsg() {
        return contentMsg;
    }

    public void setContentMsg(String contentMsg) {
        this.contentMsg = contentMsg;
    }
    
}
