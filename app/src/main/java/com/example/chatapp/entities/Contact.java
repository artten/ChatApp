package com.example.chatapp.entities;

import android.media.Image;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Contact {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String nickName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact(String name, String nickName) {
        this.name = name;
        this.nickName = nickName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
