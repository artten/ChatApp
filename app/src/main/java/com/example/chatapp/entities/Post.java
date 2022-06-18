package com.example.chatapp.entities;

import android.media.Image;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Post {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String userName;
    private String password;
    private String nickName;
    private String image;

    public Post(int id, String userName, String password, String nickName, String image) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.nickName = nickName;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
