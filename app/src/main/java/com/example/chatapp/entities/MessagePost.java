package com.example.chatapp.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MessagePost {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String senderId;
    private String receiverId;
    private String message;
    private String dateTime;

    public MessagePost(int id, String senderId, String receiverId, String message, String dateTime) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
