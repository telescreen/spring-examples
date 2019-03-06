package com.buiha.models;

/**
 * Created by tal on 3/19/17.
 */
public class Message {
    private String fromUser;
    private String toChannel;
    private String content;

    public Message(String fromUser, String toChannel, String content) {
        this.fromUser = fromUser;
        this.toChannel = toChannel;
        this.content = content;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToChannel() {
        return toChannel;
    }

    public void setToChannel(String toChannel) {
        this.toChannel = toChannel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
