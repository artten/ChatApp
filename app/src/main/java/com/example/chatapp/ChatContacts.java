package com.example.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.ContactsListAdapter;
import com.example.chatapp.entities.Contact;

import java.util.ArrayList;
import java.util.List;

public class ChatContacts extends AppCompatActivity {
    private ContactsListAdapter.RecyclerViewListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constact_list);
        setOnClickListner();
        RecyclerView listContacts = (RecyclerView) findViewById(R.id.lastContacts);
        final ContactsListAdapter adapter = new ContactsListAdapter(this,listener);
        listContacts.setAdapter(adapter);
        listContacts.setLayoutManager(new LinearLayoutManager(this));

        List<Contact> contacts = new ArrayList<>();

        contacts.add(new Contact("Artiom", "artten"));
        contacts.add(new Contact("Saar", "sarr"));
        contacts.add(new Contact("Ilona", "iloni"));
        adapter.setContacts(contacts);
    }

    private void setOnClickListner() {
        listener = new ContactsListAdapter.RecyclerViewListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                //intent.putextra
                startActivity(intent);
            }
        };
    }

}
