package com.example.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.ContactsListAdapter;
import com.example.chatapp.entities.Contact;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChatsRoom extends AppCompatActivity {
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

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("User");
            try {
                JSONObject obj = new JSONObject(value);
                JSONArray str =  obj.getJSONArray("contacts");

                if (str != null) {
                    for(int i = 0; i < str.length(); i++) {
                        //Log.d("lol",);
                        contacts.add(new Contact(str.getJSONObject(i).getString("id"), str.getJSONObject(i).getString("name")));
                    }
                }
                else {
                    contacts.add(new Contact("Artiom", "artten"));
                    contacts.add(new Contact("Saar", "sarr"));
                    contacts.add(new Contact("Ilona", "iloni"));
                }
            } catch (JSONException e) {
                contacts.add(new Contact("Artiom", "artten"));
                contacts.add(new Contact("Saar", "sarr"));
                contacts.add(new Contact("Ilona", "iloni"));
                e.printStackTrace();
            }
            //The key argument here must match that used in the other activity
        }




        adapter.setContacts(contacts);
    }

    private void setOnClickListner() {
        listener = new ContactsListAdapter.RecyclerViewListener() {
            @Override
            public void onClick(View v, int position) {
                //Log.d("here", ""+position);
                // Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                // intent.putextra
                // startActivity(intent);
            }
        };
    }

}
