package com.example.chatapp;

//import static com.example.chatapp.MyApp.context;

import android.content.Context;
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

public class ChatContacts extends AppCompatActivity {
    private ContactsListAdapter.RecyclerViewListener listener;

    private  JSONArray str;
    private String UserID;
    private String value;
    private String value2;
    private Context context;
    private  ArrayList<String> contactsID = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constact_list);
        setOnClickListner();
        context = this;
        RecyclerView listContacts = (RecyclerView) findViewById(R.id.lastContacts);
        final ContactsListAdapter adapter = new ContactsListAdapter(this,listener);
        listContacts.setAdapter(adapter);
        listContacts.setLayoutManager(new LinearLayoutManager(this));
        List<Contact> contacts = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
           value = extras.getString("User");
           value2 = extras.getString("Contacts");
            try {
                JSONObject obj = new JSONObject(value);

                //str = new JSONArray(obj2);

                UserID = obj.getString("id").toString();

                if (value2 != null) {
                    String[] separated = value2.split("id=");
                    for (int i =1 ; i < separated.length; i++){

                        String id = separated[i].split(",")[0];
                        contactsID.add( id);
                        String name = separated[i].split(",")[1].split("name=")[1];
                        contacts.add(new Contact(id, name));
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
                MessagesAPI messagesAPI = new MessagesAPI(context);
                messagesAPI.getMessages(UserID, contactsID.get(position), value, value2);
                //Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                //intent.putextra
                //startActivity(intent);
            }
        };
    }

}
