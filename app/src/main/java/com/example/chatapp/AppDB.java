package com.example.chatapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.chatapp.entities.Post;

@Database(entities = {Post.class}, version = 1)
public abstract class AppDB extends RoomDatabase {
    public abstract PostDao postDao();
}
