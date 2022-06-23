package com.example.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.ContactsListAdapter;
import com.example.adapter.MessagesListAdapter;
import com.example.chatapp.entities.Contact;
import com.example.chatapp.entities.MessagePost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.time.*;

public class ChatsRoom extends AppCompatActivity {
    private ContactsListAdapter.RecyclerViewListener listener;
    private String contact;
    private String UserID;
    private JSONArray str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_chat);
        RecyclerView messageList = (RecyclerView) findViewById(R.id.chatRecyclerView);

        Bundle extras = getIntent().getExtras();
        String value = extras.getString("User");


        JSONObject obj = null;
        try {
            obj = new JSONObject(value);
            UserID = obj.getString("Owner").toString();
            contact = obj.getString("Contact").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final MessagesListAdapter adapter = new MessagesListAdapter(this, UserID); // need to get userID before
        messageList.setAdapter(adapter);
        messageList.setLayoutManager(new LinearLayoutManager(this));
        List<MessagePost> messagePosts = new ArrayList<>();
        FrameLayout send = findViewById(R.id.framelayoutSend);
        AppCompatImageView back = findViewById(R.id.backImg);
        TextView contactName = findViewById(R.id.contactName);
        EditText chatBox = findViewById(R.id.messageBox);
        LocalDateTime dateTime = LocalDateTime.now();
        contactName.setText("receiverID");



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MessagePost m = new MessagePost(0, UserID, "receiverId", chatBox.getText().toString(), dateTime.toString());
                messagePosts.add(m);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChatContacts.class);
                intent.putExtra("User", value);
                intent.putExtra("Contact", contact);
                startActivity(intent);
                finish();
            }
        });



        if (extras != null) {

            try {

                JSONArray str =  obj.getJSONArray("Messages");



                if (str != null) {
                    for(int i = 0; i < str.length(); i++) {
                        //Log.d("lol",);
                        if (str.getJSONObject(i).getBoolean("sent") == true) {
                            messagePosts.add(new MessagePost(str.getJSONObject(i).getInt("id"),
                                    UserID, contact, str.getJSONObject(i).getString("content"), str.getJSONObject(i).getString("created")));
                        }
                        else {
                            messagePosts.add(new MessagePost(str.getJSONObject(i).getInt("id"),
                                    contact , UserID, str.getJSONObject(i).getString("content"), str.getJSONObject(i).getString("created")));
                        }

                    }
                }
                else {
                    messagePosts.add(new MessagePost(1, "Artiom", "Saar", "hello1", "2021-04-01T19:46:01"));
                    messagePosts.add(new MessagePost(2, "Saar", "Artiom", "hi2", "2021-04-01T19:47:01"));
                    messagePosts.add(new MessagePost(3, "Artiom", "Saar", "sup3", "2021-04-01T19:48:01"));
                }
            } catch (JSONException e) {
                messagePosts.add(new MessagePost(1, "Artiom", "Saar", "hello1", "2021-04-01T19:46:01"));
                messagePosts.add(new MessagePost(2, "Saar", "Artiom", "hi2", "2021-04-01T19:47:01"));
                messagePosts.add(new MessagePost(3, "Artiom", "Saar", "sup3", "2021-04-01T19:48:01"));
                e.printStackTrace();
            }
            //The key argument here must match that used in the other activity
        }




        adapter.setMessagePosts(messagePosts);
    }

}
